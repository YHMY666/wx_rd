package edu.fdzc.wx1.controller;

import edu.fdzc.wx1.entity.User;
import edu.fdzc.wx1.dto.LoginRequest;
import edu.fdzc.wx1.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
/**
 * 用户控制器 - 处理用户登录相关请求
 */
@RestController // 标记为RESTful控制器，返回JSON数据
@RequestMapping("/api/user")  // 接口基础路径
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户登录接口
     * 接收前端code和用户信息，完成微信登录
     *
     * @param req 登录请求参数（code, nickname, avatarUrl）
     * @return 登录结果（成功返回用户信息，失败返回错误信息）
     */
    @PostMapping("/login")
    public Result<User> login(@RequestBody LoginRequest req) {
        try {
            // 1. 通过code换取openid
            String openid = userService.wxLogin(req.getCode());
            // 2. 登录或注册用户
            User user = userService.loginOrRegister(openid, req.getNickname(), req.getAvatarUrl());
            return Result.success(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("登录失败：" + e.getMessage());
        }
    }
}