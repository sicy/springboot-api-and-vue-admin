package com.sicy.system.service.impl;

import com.sicy.common.ReturnMsg;
import com.sicy.system.dao.AuthMapper;
import com.sicy.system.dao.MenuMapper;
import com.sicy.system.service.MenuService;
import com.sicy.system.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单实现层
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
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private AuthMapper authMapper;

	@Override
	public ReturnMsg deleteMenu(int id) {
		//检测菜单是否被占用,是否存在于t_group_auth
		int count = authMapper.checkAuthByMenuId(id);
		if(count > 0){
			return new ReturnMsg(false,"菜单已经绑定为权限,不能删除!!");
		}
		menuMapper.deleteMenu(id);
		return new ReturnMsg(true,"操作成功");
	}

	@Override
	public ReturnMsg saveMenu(MenuVo vo) {
		menuMapper.saveMenu(vo);
		return new ReturnMsg(true,"操作成功");
	}

	@Override
	public ReturnMsg updateMenu(MenuVo vo) {
		menuMapper.modifyMenu(vo);
		return new ReturnMsg(true,"操作成功");
	}

	@Override
	public List<MenuVo> queryForLogin(String ids) {
		//查询所有的1级的，然后在依次查询二级的，查询二级的时候，把IDS带入
		MenuVo vo = new MenuVo();
		vo.setLevel(0);
		//设置菜单的状态为有效
		vo.setState(1);
		List<MenuVo> level1List = queryByCondition(vo);
		List<MenuVo> authList = new ArrayList<>();
		for(MenuVo m1 : level1List){
			List<MenuVo> level2List = menuMapper.queryForLogin(m1.getId(), ids);
			if(level2List.size() > 0){
				m1.setChlid_list(level2List);
				authList.add(m1);
			}
		}
		return authList;
	}


	@Override
	public ReturnMsg queryMenuInTree(Integer group_id) {
		if(group_id != null){
			return new ReturnMsg(true, "", menuMapper.queryByGroupId(group_id));
		}
		return new ReturnMsg(true, "", menuMapper.queryInTree());
	}

	@Override
	public List<MenuVo> queryByCondition(MenuVo vo) {
		return menuMapper.queryByCondition(vo);
	}

}
