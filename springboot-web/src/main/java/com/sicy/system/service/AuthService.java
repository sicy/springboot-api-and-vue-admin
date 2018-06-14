package com.sicy.system.service;

import com.sicy.common.ReturnMsg;
import com.sicy.system.dao.AuthMapper;
import com.sicy.system.dao.UserMapper;
import com.sicy.system.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 权限实现层
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
public class AuthService {

    @Autowired
    private AuthMapper authMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    public ReturnMsg saveUserAuth(int userId, List<Map<String, Object>> list) {
        //删除一次
        authMapper.deleteUserAuth(userId);
        if(list.size() > 0){
          //添加
            authMapper.saveUserAuth(list);
        }
        return new ReturnMsg(true,"操作成功!");
    }

    public Map<String,List<Map<String,Object>>> queryUserAuth(int userId) {
        List<Map<String,Object>> auths = authMapper.queryUserAuth(userId);
        Map<String,List<Map<String,Object>>> authUser = new HashMap<>(16);
        for(Map<String,Object> auth : auths){
            if(auth.get("level") == Integer.valueOf(1)){
                String fid = auth.get("fid")+"";
                if(authUser.get(fid) == null){
                    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
                    list.add(auth);
                    authUser.put(fid, list);
                }else{
                    authUser.get(fid).add(auth);
                }
            }else{
                
                if(authUser.get("btn") == null){
                    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
                    list.add(auth);
                    authUser.put("btn", list);
                }else{
                    authUser.get("btn").add(auth);
                }
                
            }
        }
        //返回的是一个以父菜单ID做key的map
        return authUser;
    }

    public ReturnMsg saveGroupAuth(int groupId, List<Map<String, Object>> newList) {
        //先查询出原有的权限
        List<Map<String,Object>> oldAuths = authMapper.queryGroupAuth(groupId);
        authMapper.deleteGroupAuth(groupId);
        if(newList.size() > 0){
            authMapper.saveGroupAuth(newList);
            //将分组下所有用户的权限取出,依次进行比较
            //1 原有分组没有的,后来加上的,用户也要加上
            //2 原有分组存在的而修改后没有的,如果用户有,需要删除
            
            //比较old_auths 与  new_list,找出新增的和减少的
            //先取得交集
            List<Integer> oldAuthsId = new ArrayList<>();
            for(Map<String,Object> m : oldAuths){
                oldAuthsId.add(Integer.parseInt(m.get("menu_id")+""));
            }
            List<Integer> newListId = new ArrayList<>();
            //交集
            List<Integer> unionId = new ArrayList<>();
            for(Map<String, Object> m : newList){
                newListId.add(Integer.parseInt(m.get("menu_id")+""));
                unionId.add(Integer.parseInt(m.get("menu_id")+""));
            }
            unionId.retainAll(oldAuthsId);
            //补集
            //待删除的
            oldAuthsId.removeAll(unionId);
            //待增加的
            newListId.removeAll(unionId);
            
            User user = new User();
            user.setGroup_id(groupId);
            List<User> users = userMapper.queryUser(user);
            List<Map<String,Object>> addUserAuths = new ArrayList<>();
            for(User u:users){
                int userId = u.getId();
                //增加操作
                for(Integer menuId : newListId){
                    Map<String,Object> m = new HashMap<>(4);
                    m.put("user_id", userId);
                    m.put("menu_id", menuId);
                    addUserAuths.add(m);
                }
                //删除操作
                for(Integer menuId : oldAuthsId){
                    Map<String,Object> m = new HashMap<>(4);
                    m.put("user_id", userId);
                    m.put("menu_id", menuId);
                    authMapper.deleteUserAuthByMap(m);
                }
            }
            if(addUserAuths.size() > 0){
                authMapper.saveUserAuth(addUserAuths);
            }
        }else{
            //如果权限为空,则删除所有用户的权限
            User user = new User();
            user.setGroup_id(groupId);
            List<User> users = userMapper.queryUser(user);
            for(User u:users){
                int userId = u.getId();
                authMapper.deleteUserAuth(userId);
            }
        }
        return new ReturnMsg(true,"操作成功!");
    }

    public ReturnMsg queryGroupAuth(int groupId) {
        List<Map<String,Object>> auths = authMapper.queryGroupAuth(groupId);
        return new ReturnMsg(true,"",auths);
    }

    public ReturnMsg changePWD(int userId, String oldPWD, String newPWD) {
        //检查原密码是否正确
        User user = userMapper.queryById(userId);
        if (user != null){
            if(!oldPWD.equals(user.getPassword())){
                return new ReturnMsg(false, "原密码错误!!!!");
            }
            user.setPassword(newPWD);
            userMapper.modifyUser(user);
            return new ReturnMsg(true, "密码成功修改,下次登录生效!");
        }
        return new ReturnMsg(false, "用户ID错误!!!!");
    }

    public ReturnMsg queryAuth(int userId) {
        return new ReturnMsg(true,"",authMapper.queryAuth(userId));
    }

}
