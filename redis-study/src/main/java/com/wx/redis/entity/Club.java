package com.wx.redis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/12/18
 */
public class Club implements Serializable {
    private int id;
    private String name;
    private String info;
    private Date createDate;
    private int rank;

    public Club(int id, String name, String info, Date createDate, int rank) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.createDate = createDate;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Club{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", createDate=" + createDate +
                ", rank=" + rank +
                '}';
    }
}
