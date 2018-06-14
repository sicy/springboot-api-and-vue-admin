package com.sicy.common.pagination;

import java.util.List;

/**
 * (填写类功能描述)
 *
 * @author SiChunyang
 * @version 1.0
 *          <p>
 *          <p>
 *          <p>修订人		修订时间			描述信息
 *          <p>-----------------------------------------------------
 *          <p>SiChunyang		2018/6/14		初始创建
 */
public class ForePage<T> {

    /**
     * 总数
     */
    private int totalCount;

    /**
     * 页码
     */
    private Integer pageIndex;

    /**
     * 数据列表
     */
    private List<T> list;

    /**
     * 构造函数
     * @param totalCount 总数
     * @param pageIndex 页码
     * @param list 列表
     */
    public ForePage(int totalCount, Integer pageIndex, List<T> list){
        this.totalCount = totalCount;
        this.pageIndex = pageIndex;
        this.list = list;
    }

    /**
     * 获取总数
     * @return  查询返回数量
     */
    public int getTotalCount(){
        return totalCount;
    }

    /**
     * 设置总数
     * @param totalCount 总数
     */
    public void setTotalCount(int totalCount){
        this.totalCount = totalCount;
    }

    /**
     * 获取页码
     * @return 返回页码
     */
    public Integer getPageIndex(){
        return pageIndex;
    }

    /**
     * 设置页码
     * @param pageIndex 页码
     */
    public void setPageIndex(Integer pageIndex){
        this.pageIndex = pageIndex;
    }

    /**
     * 获取列表
     * @return 获取列表
     */
    public List<T> getList(){
        return list;
    }

    /**
     * 设置列表
     * @param list 列表对象
     */
    public void setList(List<T> list){
        this.list = list;
    }

    /**
     * 获取分页的起始索引
     *
     * @param pageIndex 页数
     * @param pageSize 每页条目数
     * @return 返回搜索起始页
     */
    public static int getStartIndex(int pageIndex, int pageSize) {
        return (pageIndex - 1) * pageSize;
    }

}
