package com.sicy.system.dao;

import com.sicy.system.vo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户接口层
 *
 * @author github.com/sicy
 * @version 1.0
 *          <p/>
 *          <br/>
 *          <br/>修订人    修订时间      描述信息
 *          <br/>-----------------------------------------------------
 *          <br/>github.com/sicy    2017/11/11    初始创建
 */
public interface UserMapper {

	/**
	 * 通过名称查询用户
	 * @param username
	 * @return
	 */
	public User loadUserByUsername(@Param("username") String username);

	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public int insertUser(User user);

	/**
	 * 修改用户
	 * @param user
	 */
	public void modifyUser(User user);

	/**
	 * 删除用户
	 * @param id
	 */
	public void deleteUser(int id);

	/**
	 * 查询用户
	 * @param user
	 * @return
	 */
	public List<User> queryUser(User user);

	/**
	 * 查询用户总数
	 * @param user
	 * @return
	 */
	public int queryUserCount(User user);

	/**
	 * 分页查询用户
	 * @param user
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> queryFilterUserForPage(@Param("user") User user,
                                                           @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

	/**
	 * 查询用户名
	 * @param userName
	 * @param id
	 * @return
	 */
	public int queryUserName(@Param("user_name") String userName, @Param("id") int id);

	/**
	 * 查询用户姓名
	 * @param realName
	 * @param id
	 * @return
	 */
	public int queryRealName(@Param("real_name") String realName, @Param("id") int id);

	/**
	 * 根据ID查询用户
	 * @param id
	 * @return
	 */
	public User queryById(int id);

	/**
	 * 检查用户名
	 * @param newName
	 * @param id
	 * @return
	 */
	public int checkName(@Param("newName") String newName, @Param("id") int id);

	/**
	 * 查询用户总数
	 * @param user
	 * @return
	 */
	public int queryFilterUserCount(User user);

	/**
	 * 根据用户名查询用户
	 * @param userName
	 * @return
	 */
	public List<User> queryUserByUserName(@Param("user_name") String userName);

	/**
	 * 查询用户
	 * @param userName
	 */
	public void lockCustomUser(@Param("user_name") String userName);

	/**
	 * 查询所有用户不过滤超级管理员
	 * @return List<User>
	 */
	public List<User> queryAllUser();

	/**
	 * 检查用户名唯一
	 * @param userName
	 * @param id
	 * @return
	 */
	public int checkUserName(@Param("userName") String userName, @Param("id") int id);

	/**
	 * 检查用户名唯一
	 * @param email
	 * @param id
	 * @return
	 */
	public int checkEmail(@Param("email") String email, @Param("id") int id);

	/**
	 * 检查电话号码唯一
	 * @param mobilePhone
	 * @param id
	 * @return
	 */
	public int checkMobilePhone(@Param("mobilePhone") String mobilePhone, @Param("id") int id);

}
