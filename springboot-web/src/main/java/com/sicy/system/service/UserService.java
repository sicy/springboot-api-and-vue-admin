package com.sicy.system.service;

import com.sicy.common.ReturnMsg;
import com.sicy.common.pagination.ForePage;
import com.sicy.system.dao.AuthMapper;
import com.sicy.system.dao.UserMapper;
import com.sicy.system.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户实现层
 *
 * @author github.com/sicy
 * @version 1.0
 *          <p/>
 *          <br/>
 *          <br/>修订人    修订时间      描述信息
 *          <br/>-----------------------------------------------------
 *          <br/>github.com/sicy    2017/11/11    初始创建
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private AuthMapper authMapper;

	public ReturnMsg deleteUser(int id) {
		User user = new User();
		user.setId(id);
		List<User> list = userMapper.queryUser(user);
		if(list == null || list.size() == 0){
			return new ReturnMsg(true,"删除失败,用户不存在!");
		}
		user = list.get(0);
		userMapper.deleteUser(id);
		//删除权限数据
		authMapper.deleteUserAuth(id);
		return new ReturnMsg(true,"删除成功!", user);
	}

	public ReturnMsg insertUser(User user) {
		//检查重复
		int count = userMapper.queryUserName(user.getUser_name(), -1);
		if(count > 0){
			return new ReturnMsg(false,"用户名不能重复");
		}
		//查询分组权限
		List<Map<String,Object>> auths = authMapper.queryGroupAuth(user.getGroup_id());
		if(auths == null || auths.size() == 0){
			return new ReturnMsg(false, "设置的分组没有可供分配的权限,请先设置分组权限!!");
		}
		userMapper.insertUser(user);
		//加入权限
		List<Map<String,Object>> userAuths = new ArrayList<>();
		for (Map<String,Object> m : auths) {
			m.put("user_id", user.getId());
			userAuths.add(m);
		}
		authMapper.saveUserAuth(userAuths);
		return new ReturnMsg(true,"操作成功!");
	}

	public ReturnMsg modifyUser(User user) {
		//查询原用户信息
		User userOld = userMapper.queryById(user.getId());
		if(userOld == null){
			return new ReturnMsg(false,"操作失败,用户不存在!");
		}
		if(user.getGroup_id() != null){
		//修改了分组
			if(user.getGroup_id().equals( userOld.getGroup_id())){
				//添加
				List<Map<String,Object>> auths = authMapper.queryGroupAuth(user.getGroup_id());
				if(auths.size() == 0){
					return new ReturnMsg(false, "角色下未设置权限,角色未设置成功!");
				}
				//所属分组修改,权限需要改变
				//删除用户和菜单关联的关系
				authMapper.deleteUserAuth(user.getId());
				List<Map<String,Object>> userAuths = new ArrayList<>();
				for (Map<String,Object> m : auths) {
					m.put("user_id", user.getId());
					userAuths.add(m);
				}
				//修改分组后，增加用户和菜单的关联关系
				authMapper.saveUserAuth(userAuths);
			}
		}
		if(!userOld.getUser_name().equals(user.getUser_name())){
			//校验新用户名是否重复
			int num = userMapper.checkName(user.getUser_name(), user.getId());
			if(num > 0){
				return new ReturnMsg(false, "用户名不能重复!");
			}
		}
		userMapper.modifyUser(user);
		return new ReturnMsg(true,"操作成功!");
	}

	public List<User> queryUser(User user) {
		if(user!=null){
			return userMapper.queryUser(user);
		}
		return null;
	}

	public ReturnMsg queryFilterUserForPage(User user, int pageIndex, int pageSize) {
		int count = userMapper.queryFilterUserCount(user);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Map<String,Object>> l = userMapper.queryFilterUserForPage(user, ForePage.getStartIndex(pageIndex, pageSize), pageSize);
		for (Map<String,Object> m:l) {
			List<Map<String,Object>> modules = authMapper.queryUserAuth(Integer.parseInt(m.get("id")+""));
			m.put("auths",modules);
			Date expireDate = (Date)m.get("expire_time");
			if(expireDate != null){
				String ds = sdf.format(expireDate);
				m.put("expire_time", ds);
			}
			//移除密码列,防止前台抓包看密码
			m.remove("password");
		}
		ForePage<Map<String,Object>> f = new ForePage<>(count, pageIndex, l);
		return new ReturnMsg(true,"",f);
	}

}
