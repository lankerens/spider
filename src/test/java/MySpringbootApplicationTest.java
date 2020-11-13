import com.lankeren.MySpringbootApplication;
import com.lankeren.mapper.SpiderMapper;
import com.lankeren.utils.SpiderUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * @author lankeren
 * @ClassName MySpringbootApplicationTest
 * @Deacription:
 * @create: 2020-11-13 14:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MySpringbootApplication.class)
public class MySpringbootApplicationTest {
    @PersistenceContext
    EntityManager em ;

    @Autowired
    SpiderMapper spiderMapper ;

    @Test
    public void p(){
        String url = "https://v.qq.com/channel/cartoon";
//        String con = SpiderUtils.getPageContent(url);
//        SpiderUtils.parsingContent(con);
    }

}
