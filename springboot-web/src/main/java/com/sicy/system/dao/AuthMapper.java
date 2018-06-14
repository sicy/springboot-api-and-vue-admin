package com.sicy.system.dao;

import com.sicy.system.vo.MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 权限接口层
 *
 * @author github.com/sicy
 * @version 1.0
 *          <p/>
 *          <br/>
 *          <br/>修订人    修订时间      描述信息
 *          <br/>-----------------------------------------------------
 *          <br/>github.com/sicy    2017/11/11    初始创建
 */
public interface AuthMapper {
	/**
	 * 保存分组权限
	 * @param auth
	 */
	public void saveGroupAuth(List<Map<String, Object>> auth);

	/**
	 * 查询分组权限
	 * @param groupId
	 * @return
	 */
	public List<Map<String,Object>> queryGroupAuth(@Param("group_id") int groupId);

	/**
	 * 删除分组权限
	 * @param groupId
	 */
	public void deleteGroupAuth(@Param("group_id") int groupId);

	/**
	 * 保存用户权限
	 * @param auth
	 */
	public void saveUserAuth(List<Map<String, Object>> auth);

	/**
	 * 查询用户权限
	 * @param userId
	 * @return
	 */
	public List<Map<String,Object>> queryUserAuth(@Param("user_id") int userId);

	/**
	 * 简单地只取id和fid
	 * @param userId
	 * @return
	 */
	public List<MenuVo> queryUserAuthSimple(@Param("user_id") int userId);

	/**
	 * 删除用户权限
	 * @param userId
	 */
	public void deleteUserAuth(@Param("user_id") int userId);

	/**
	 * 根据菜单ID查询权限
	 * @param menuId
	 * @return
	 */
	public int checkAuthByMenuId(@Param("menu_id") int menuId);

	/**
	 * 根据ID查询用户权限
	 * @param groupId
	 * @return
	 */
	public List<Map<String,Object>> queryUserAuthByGid(@Param("group_id") int groupId);

	/**
	 * 删除用户权限
	 * @param auth
	 */
	public void deleteUserAuthByMap(Map<String, Object> auth);

	/**
	 * 查询子菜单
	 * @param userID
	 * @param menuId
	 * @return
	 */
	public List<Long> queryChildMenus(@Param("user_id") Integer userID, @Param("menu_id") Integer menuId);

	/**
	 * 查询权限
	 * @param userId
	 * @return
	 */
	public List<Map<String,Object>> queryAuth(@Param(value = "user_id") int userId);

}
