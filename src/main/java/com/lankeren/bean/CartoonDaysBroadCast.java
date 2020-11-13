package com.lankeren.bean;


import javax.persistence.*;

/**
 * @author lankeren
 * @ClassName CartoonDaysBroadCast
 * @Deacription:
 * @create: 2020-11-13 12:23
 */
@Entity
@Table(name = "sys_anmie")
public class CartoonDaysBroadCast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     *  动漫名
     */
    @Column
    private String animeName;
    /**
     *  集数情况
     */
    @Column
    private String blues;
    /**
     *  描述
     */
    @Column
    private String desc;


    public CartoonDaysBroadCast() {
    }


    public CartoonDaysBroadCast(String animeName, String blues, String desc) {
        this.animeName = animeName;
        this.blues = blues;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }

    public String getBlues() {
        return blues;
    }

    public void setBlues(String blues) {
        this.blues = blues;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "CartoonDaysBroadCast{" +
                "animeName='" + animeName + '\'' +
                ", blues=" + blues +
                ", desc='" + desc + '\'' +
                '}';
    }
}

