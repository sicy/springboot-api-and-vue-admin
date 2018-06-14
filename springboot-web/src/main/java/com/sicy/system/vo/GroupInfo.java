package com.sicy.system.vo;

/**
 * 用户分组
 *
 * @author Administrator
 */
public class GroupInfo {

    private Integer id;

    /**
     * 用户组名
     */
    private String group_name;

    /**
     * 用户组描述
     */
    private String group_desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_desc() {
        return group_desc;
    }

    public void setGroup_desc(String group_desc) {
        this.group_desc = group_desc;
    }
}
