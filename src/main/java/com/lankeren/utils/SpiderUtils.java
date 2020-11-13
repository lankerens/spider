package com.lankeren.utils;

import com.lankeren.MySpringbootApplication;
import com.lankeren.bean.CartoonDaysBroadCast;
import com.lankeren.mapper.SpiderMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author lankeren
 * @ClassName SpiderUtils
 * @Deacription:
 * @create: 2020-11-12 15:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MySpringbootApplication.class)
public class SpiderUtils {

    @PersistenceContext
    EntityManager em ;

    @Autowired
    private SpiderMapper spiderMapper ;

    public String getPageContent(String url) {
        HttpClientBuilder builder = HttpClients.custom();
        CloseableHttpClient client = builder.build();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        String content = "";
        try {
            // 模拟浏览器访问
            httpGet.setHeader("user-agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML," +
                            " like Gecko) Chrome/86.0.4240.193 Safari/537.36");
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            content = EntityUtils.toString(entity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }


    public void parsingContent(String content) {
        List<List<CartoonDaysBroadCast>> list = new ArrayList<>();
        Document document = Jsoup.parse(content);
        Element elementById = document.getElementById("v_cartoon_days_broadcast");
        // 获取到8个周更的 父div
        Element child = elementById.child(1);
        // 集合  >> 大小 8
        Elements children = child.children();

        for (Element e : children) {
            Elements names = e.getElementsByClass("figure_title figure_title_two_row bold");
            Elements descs = e.getElementsByClass("figure_desc");
            Elements captions = e.getElementsByClass("figure_caption");
            List<CartoonDaysBroadCast> temp = new ArrayList<>();

            // 一组
            for (int i = 0; i < names.size(); i++) {
                temp.add(new CartoonDaysBroadCast(names.get(i).text(), captions.get(i).text(), descs.get(i).text()));
            }

            // 这组搞完  >>  添加到 list  >>  下一组
            list.add(temp);

            spiderMapper.saveAll(temp);

        }

//        System.out.println(list);
        System.out.println("数据库插入成功");

    }

    public static void main(String[] args) {
        SpiderUtils su = new SpiderUtils();
        String url = "https://v.qq.com/channel/cartoon";
        String con = su.getPageContent(url);
        su.parsingContent(con);
    }


    @Test
    public void test(){
        String url = "https://v.qq.com/channel/cartoon";
        String con = getPageContent(url);
        parsingContent(con);
    }

    @Test
    public void query(){
        List<CartoonDaysBroadCast> clist = spiderMapper.findCartoonDaysBroadCastsByAnimeName("万界仙踪");

        for (CartoonDaysBroadCast c: clist) {
            System.out.println("播放的周数不一样的： " + c);
        }

        System.out.println("---------");

        CartoonDaysBroadCast car = spiderMapper.findById(50).get();
        System.out.println(car);
    }

}
