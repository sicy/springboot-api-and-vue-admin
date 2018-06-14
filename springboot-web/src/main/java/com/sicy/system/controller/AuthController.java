package com.sicy.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.sicy.common.ReturnMsg;
import com.sicy.common.interceptor.RequestUtils;
import com.sicy.system.service.AuthService;
import com.sicy.system.service.UserService;
import com.sicy.system.vo.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 权限控制层
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
@RequestMapping("/api/auth")
public class AuthController {

    private static Log logger = LogFactory.getLog("AuthController");

    private static final String SYMBOL = ",";

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    /**
     * 登录接口
     * @param user 用户名和密码会封装为一个User对象
     * @return 返回登录结果
     */
    @PostMapping("/login")
    public ReturnMsg login(@RequestBody User user) {
        List<User> userList = userService.queryUser(user);
        if (userList == null || userList.size() == 0) {
            logger.info("登录系统失败：用户名或密码输入错误");
            return new ReturnMsg(false, "用户名或密码错误");
        } else {
            User loginUser = userList.get(0);
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            if (null != loginUser.getExpire_time()) {
                if (Integer.parseInt(sdf.format(date)) > Integer.parseInt(sdf.format(loginUser.getExpire_time()))) {
                    return new ReturnMsg(false, "账号已过期");
                }
            }
            RequestUtils.setSessionAttribte(User.SYS_LOGIN_INFO, loginUser);
            //查询一次人物对应的菜单权限
            int userId = loginUser.getId();
            Map<String, List<Map<String, Object>>> auths = authService.queryUserAuth(userId);
            loginUser.setPrivilege(auths);
            return new ReturnMsg(true, "登录成功", loginUser);
        }
    }

    /**
     * 登出
     * @param request HttpServletRequest对象
     * @return 结果
     */
    @PostMapping("/logout")
    public ReturnMsg logout(HttpServletRequest request) {
        RequestUtils.setSessionAttribte(User.SYS_LOGIN_INFO, null);
        HttpSession se = request.getSession();
        se.removeAttribute(User.SYS_LOGIN_INFO);
        return new ReturnMsg(true);
    }

    /**
     * 保存和修改用户权限
     *
     * @param requestBody 用户权限请求体
     * @return 操作结果
     */
    @PostMapping("/saveAuths")
    public ReturnMsg saveAuths(@RequestBody JSONObject requestBody) {
        int userId = requestBody.getInteger("userId");
        String modules = requestBody.getString("menuIds");
        List<Map<String, Object>> list = new ArrayList<>();
        if (modules.endsWith(SYMBOL)) {
            modules = modules.substring(0, modules.length() - 1);
        }
        if (!"".equals(modules)) {
            String[] temp = modules.split(",");
            for (String aTemp : temp) {
                Map<String, Object> m = new HashMap<>(4);
                m.put("user_id", userId);
                m.put("menu_id", aTemp);
                list.add(m);
            }
        }
        return authService.saveUserAuth(userId, list);
    }

}