package com.sicy.system.vo;

import java.util.List;

/**
 * 菜单VO
 *
 * @author Administrator
 */
public class MenuVo {

    private Integer id;

    /**
     * 菜单名称
     */
    private String menu_name;

    /**
     * 地址
     */
    private String url;

    /**
     * 菜单图标样式
     */
    private String icon_class;

    /**
     * 菜单级别   0父级菜单   1子级菜单
     */
    private Integer level;

    /**
     * 父ID
     */
    private Integer fid;

    /**
     * 父名称
     */
    private String fname;

    /**
     * 排序字段
     */
    private Integer order_code;

    /**
     * 状态  启用标识  1 启动  0 停用
     */
    private Integer state;

    private List<MenuVo> chlid_list;

    public List<MenuVo> getChlid_list() {
        return chlid_list;
    }

    public void setChlid_list(List<MenuVo> chlid_list) {
        this.chlid_list = chlid_list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getIcon_class() {
        return icon_class;
    }

    public void setIcon_class(String icon_class) {
        this.icon_class = icon_class;
    }

    public Integer getOrder_code() {
        return order_code;
    }

    public void setOrder_code(Integer order_code) {
        this.order_code = order_code;
    }
}
