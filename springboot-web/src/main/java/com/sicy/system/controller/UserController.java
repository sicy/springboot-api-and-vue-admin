package com.sicy.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.sicy.common.ReturnMsg;
import com.sicy.common.interceptor.RequestUtils;
import com.sicy.system.service.UserService;
import com.sicy.system.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 用户控制层
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
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户列表 分页查询
     *
     * @param user
     * @return
     */
    @PostMapping
    public ReturnMsg queryUserList(@RequestBody User user,
                                   @RequestParam(value="pageIndex",required = false,defaultValue = "1")Integer pageIndex,
                                   @RequestParam(value="pageSize",required = false,defaultValue = "10") Integer pageSize) {
        if (user != null && !StringUtils.isEmpty(user.getUser_name())) {
            user.setUser_name("%" + user.getUser_name() + "%");
        }
        return userService.queryFilterUserForPage(user, pageIndex, pageSize);
    }

    @GetMapping("/{id}")
    public User queryUserInfo(@PathVariable int id) {
        User user = new User();
        user.setId(id);
        List<User> list = userService.queryUser(user);
        user = list.get(0);
        return user;
    }

    @PostMapping("/insert")
    public ReturnMsg addUser(@RequestBody User user) throws Exception {
        user.setCreate_time(new Date());
        return userService.insertUser(user);
    }

    @PostMapping("/update")
    public ReturnMsg modifyUser(@RequestBody User user) {
        return userService.modifyUser(user);
    }

    @PostMapping("/delete")
    public ReturnMsg deleteUser(@RequestBody JSONObject requestBody) {
        int id = requestBody.getInteger("id");
        User user = RequestUtils.getSessionAttribte(User.SYS_LOGIN_INFO);
        if (id == user.getId()) {
            return new ReturnMsg(false, "不能删除自己!");
        }
        return userService.deleteUser(id);
    }

}
