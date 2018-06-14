package com.sicy.system.dao;

import com.sicy.system.vo.GroupInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 分组接口层
 *
 * @author github.com/sicy
 * @version 1.0
 *          <p/>
 *          <br/>
 *          <br/>修订人    修订时间      描述信息
 *          <br/>-----------------------------------------------------
 *          <br/>github.com/sicy    2017/11/11    初始创建
 */
public interface GroupMapper {
	/**
	 * 保存分组
	 * @param group
	 */
	public void saveGroup(GroupInfo group);

	/**
	 * 修改分组
	 * @param group
	 */
	public void modifyGroup(GroupInfo group);

	/**
	 * 删除分组
	 * @param id
	 */
	public void deleteGroup(int id);

	/**
	 * 查询分组
	 * @return
	 */
	public List<GroupInfo> queryAllGroup();

	/**
	 * 检查分组名
	 * @param groupName
	 * @param id
	 * @return
	 */
	public int checkGroupName(@Param("group_name") String groupName, @Param("id") int id);
	
}
