package com.sicy.system.vo;

import java.util.Date;
import java.util.Map;

/**
 * 用户表
 *
 * @author github.com/sicy
 */
public class User {

    /**
     * 登录用户常量
     */
    public static final String SYS_LOGIN_INFO = "login_user";

    private Integer id;

    /**
     * 用户名
     */
    private String user_name;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 真实姓名
     */
    private String real_name;

    /**
     * 手机
     */
    private String mobilephone_number;

    /**
     * 所属分组
     */
    private Integer group_id;

    /**
     * 所属分组vo
     */
    private GroupInfo groupInfo;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 角色id
     */
    private Integer role_id;

    /**
     * 创建日期
     */
    private Date create_time;

    /**
     * 失效日期
     */
    private Date expire_time;

    /**
     * 菜单权限
     */
    private Map privilege;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getMobilephone_number() {
        return mobilephone_number;
    }

    public void setMobilephone_number(String mobilephone_number) {
        this.mobilephone_number = mobilephone_number;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public GroupInfo getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(GroupInfo groupInfo) {
        this.groupInfo = groupInfo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(Date expire_time) {
        this.expire_time = expire_time;
    }

    public Map getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Map privilege) {
        this.privilege = privilege;
    }
}
