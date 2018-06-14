package com.sicy.system.dao;

import com.sicy.system.vo.MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单接口层
 *
 * @author github.com/sicy
 * @version 1.0
 *          <p/>
 *          <br/>
 *          <br/>修订人    修订时间      描述信息
 *          <br/>-----------------------------------------------------
 *          <br/>github.com/sicy    2017/11/11    初始创建
 */
public interface MenuMapper {
	/**
	 * 保存菜单
	 * @param vo
	 */
	public void saveMenu(MenuVo vo);

	/**
	 * 删除菜单
	 * @param id
	 */
	public void deleteMenu(int id);

	/**
	 * 修改菜单
	 * @param vo
	 */
	public void modifyMenu(MenuVo vo);

	/**
	 * 查询所有菜单
	 * @return
	 */
	public List<MenuVo> queryAll();

	/**
	 * 登录查询菜单
	 * @param fid
	 * @param ids
	 * @return
	 */
	public List<MenuVo> queryForLogin(@Param("fid") Integer fid, @Param("ids") String ids);

	/**
	 * 根据IDS查询
	 * @param ids
	 * @return
	 */
	public List<MenuVo> queryByIds(@Param("ids") String ids);
	
	/**
	 * 检测是否被组绑定
	 * @param groupId
	 * @return
	 */
	public int checkMenuByGroupid(@Param("group_id") int groupId);

	/**
	 * 根据父菜单查询菜单
	 * @param vo
	 * @return
	 */
	public List<MenuVo> queryByCondition(MenuVo vo);

	/**
	 * 查询菜单 返回树形结构
	 * @return
	 */
	public List<MenuVo> queryInTree();

	/**
	 * 按分组查询菜单 返回树形结构
	 * @param groupId
	 * @return
	 */
	public List<MenuVo> queryByGroupId(@Param("group_id") Integer groupId);


}
