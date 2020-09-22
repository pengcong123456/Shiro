package com.pcc.entity;



import java.io.Serializable;
import java.util.List;


public class Role implements Serializable {
    private String id;
    private String name;

    //定义权限的集合
    private List<Perms> perms;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Perms> getPerms() {
        return perms;
    }

    public void setPerms(List<Perms> perms) {
        this.perms = perms;
    }
}
