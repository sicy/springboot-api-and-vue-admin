package com.sicy.system.service;

import com.sicy.common.ReturnMsg;
import com.sicy.system.dao.AuthMapper;
import com.sicy.system.dao.GroupMapper;
import com.sicy.system.dao.UserMapper;
import com.sicy.system.vo.GroupInfo;
import com.sicy.system.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 分组实现层
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
public class GroupService {

	@Autowired
	private GroupMapper groupMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private AuthMapper authMapper;
	
	public ReturnMsg saveGroup(GroupInfo group) {
		//去重
		int count = groupMapper.checkGroupName(group.getGroup_name(), -1);
		if(count > 0){
			return new ReturnMsg(false,"分组名重复!");
		}
		groupMapper.saveGroup(group);
		return new ReturnMsg(true,"操作成功!");
	}

	public ReturnMsg deleteGroup(int id) {
		//检查分组下是否有人员
		User user = new User();
		user.setGroup_id(id);
		int count = userMapper.queryFilterUserCount(user);
		if(count > 0){
			return new ReturnMsg(false,"分组下存在用户,不能删除...");
		}
		//删除权限
		authMapper.deleteGroupAuth(id);
		//删除分组
		groupMapper.deleteGroup(id);
		return new ReturnMsg(true,"操作成功!");
	}

	public ReturnMsg modifyGroup(GroupInfo group) {
		//去重
		int count = groupMapper.checkGroupName(group.getGroup_name(), group.getId());
		if(count > 0){
			return new ReturnMsg(false,"分组名重复!");
		}
		groupMapper.modifyGroup(group);
		return new ReturnMsg(true,"操作成功!");
	}

	public ReturnMsg queryAllGroup() {
		return new ReturnMsg(true,"", groupMapper.queryAllGroup());
	}

}
