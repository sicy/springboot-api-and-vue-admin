package com.sicy.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.sicy.common.ReturnMsg;
import com.sicy.common.interceptor.RequestUtils;
import com.sicy.system.service.AuthService;
import com.sicy.system.service.MenuService;
import com.sicy.system.vo.MenuVo;
import com.sicy.system.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 菜单控制层
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
@RequestMapping("/api/menu")
public class MenuController {

    private static final String SYMBOL = ",";

    @Autowired
    private MenuService menuService;

    @Autowired
    private AuthService authService;

    @PostMapping("/save")
    public ReturnMsg save(@RequestBody MenuVo vo) {
        return menuService.saveMenu(vo);
    }

    @PostMapping("/delete")
    public ReturnMsg delete(@RequestBody JSONObject req) {
        int id = req.getIntValue("id");
        return menuService.deleteMenu(id);
    }

    @PostMapping("/update")
    public ReturnMsg update(@RequestBody MenuVo vo) {
        return menuService.updateMenu(vo);
    }

    @PostMapping("/query")
    public ReturnMsg query(@RequestBody JSONObject req) {
        MenuVo menu = new MenuVo();
        Integer level = req.getInteger("level");
        if(level != null){
            menu.setLevel(level);
        }
        Integer fid = req.getInteger("fid");
        if (fid != null && fid != -1) {
            menu.setFid(fid);
        }
        return new ReturnMsg(true, "", menuService.queryByCondition(menu));
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/queryForLogin")
    public ReturnMsg queryForLogin() {
        User user = RequestUtils.getSessionAttribte(User.SYS_LOGIN_INFO);
        int userId = user.getId();
        int groupId = user.getGroup_id();
        StringBuilder modules = new StringBuilder();
        if (groupId != 0) {
            List<Map<String, Object>> auths = (List<Map<String, Object>>) authService.queryAuth(userId).getData();
            for (Map<String, Object> m : auths) {
                modules.append(Integer.parseInt(m.get("menu_id") + "")).append(",");
            }
            if (modules.toString().endsWith(SYMBOL)) {
                modules = new StringBuilder(modules.substring(0, modules.length() - 1));
            }
        }
        if (StringUtils.isEmpty(modules.toString()) && groupId != 0) {
            //没有权限且不是管理员时，不返回菜单
            return new ReturnMsg(true, "", null);
        }
        return new ReturnMsg(true, "", menuService.queryForLogin(modules.toString()));
    }

    @PostMapping("/queryMenusInTree")
    public ReturnMsg queryMenusInTree(@RequestBody JSONObject req) {
        Integer group_id = req.getInteger("group_id");
        return menuService.queryMenuInTree(group_id);
    }

}
