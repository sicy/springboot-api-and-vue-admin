package com.sicy.system.service;

import com.sicy.common.ReturnMsg;
import com.sicy.system.vo.MenuVo;

import java.util.List;

/**
 * (填写类功能描述)
 *
 * @author github.com/sicy
 * @version 1.0
 *          <p>
 *          <br/>
 *          <br/>修订人		修订时间			描述信息
 *          <br/>-----------------------------------------------------
 *          <br/>github.com/sicy		2018/5/10		初始创建
 */
public interface MenuService  {

    /**
     * 保存菜单
     * @param menu
     * @return
     */
    public ReturnMsg saveMenu(MenuVo menu);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    public ReturnMsg deleteMenu(int id);

    /**
     * 只允许修改名称
     * @param menu
     * @return
     */
    public ReturnMsg updateMenu(MenuVo menu);

    /**
     * 按条件查询
     * @param condition
     * @return
     */
    public List<MenuVo> queryByCondition(MenuVo condition);

    /**
     * 按IDS查询菜单
     * @param ids
     * @return
     */
    public List<MenuVo> queryForLogin(String ids);

    /**
     * 查询菜单 返回树形结构
     * 如果group_id 不为null，则返回分组下的菜单，以树形式
     * @param group_id
     * @return
     */
    public ReturnMsg queryMenuInTree(Integer group_id);

}
