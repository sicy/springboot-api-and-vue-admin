package com.sicy.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.sicy.common.ReturnMsg;
import com.sicy.common.interceptor.RequestUtils;
import com.sicy.system.service.AuthService;
import com.sicy.system.service.GroupService;
import com.sicy.system.vo.GroupInfo;
import com.sicy.system.vo.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分组控制层
 *
 * @author github.com/sicy
 * @version 1.0
 *          <p/>
 *          <br/>
 *          <br/>修订人    修订时间      描述信息
 *          <br/>-----------------------------------------------------
 *          <br/>github.com/sicy    2017/11/11    初始创建
 */

@RestController
@RequestMapping("/api/group")
public class GroupController {

    private static Log logger = LogFactory.getLog("GroupController");

    private static final String SYMBOL = ",";

    @Autowired
    private GroupService groupService;

    @Autowired
    private AuthService authService;

    /**
     * 保存
     * @param group
     * @return
     */
    @PostMapping("/save")
    public ReturnMsg save(@RequestBody GroupInfo group) {
        return groupService.saveGroup(group);
    }

    /**
     * 删除
     * @param req
     * @return
     */
    @PostMapping("/delete")
    public ReturnMsg delete(@RequestBody JSONObject req) {
        int id = req.getIntValue("id");
        return groupService.deleteGroup(id);
    }

    /**
     * 更新
     * @param vo
     * @return
     */
    @PostMapping("/update")
    public ReturnMsg update(@RequestBody GroupInfo vo) {
        return groupService.modifyGroup(vo);
    }

    /**
     * 查询所有分组
     * @return
     */
    @PostMapping("/querys")
    public ReturnMsg queryAllGroup() {
        return groupService.queryAllGroup();
    }

    /**
     * 保存分组的权限
     * @param req
     * @return
     */
    @PostMapping("/saveGroupAuth")
    public ReturnMsg saveGroupAuth(@RequestBody JSONObject req) {
        int groupId = req.getInteger("groupId");
        User loginUser = RequestUtils.getSessionAttribte(User.SYS_LOGIN_INFO);
        if (loginUser.getGroup_id() != null) {
            if (groupId == loginUser.getGroup_id()) {
                return new ReturnMsg(false, "不能修改自己所属的分组!");
            }
        }
        String menuIds = req.getString("menuIds");
        if (menuIds.endsWith(SYMBOL)) {
            menuIds = menuIds.substring(0, menuIds.length() - 1);
        }
        List<Map<String, Object>> list = new ArrayList<>();
        if (!"".equals(menuIds)) {
            String[] temp = menuIds.split(",");
            for (String aTemp : temp) {
                Map<String, Object> m = new HashMap<>(4);
                m.put("group_id", groupId);
                m.put("menu_id", Integer.parseInt(aTemp));
                list.add(m);
            }
        }
        logger.info("设置分组权限");
        return authService.saveGroupAuth(groupId, list);
    }

    /**
     * 查询分组的菜单
     * @param req
     * @return
     */
    @PostMapping("/queryGroupAuth")
    public ReturnMsg queryGroupAuth(@RequestBody JSONObject req) {
        int groupId = req.getInteger("groupId");
        return authService.queryGroupAuth(groupId);
    }

}
