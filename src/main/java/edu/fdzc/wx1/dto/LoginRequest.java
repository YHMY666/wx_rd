package edu.fdzc.wx1.dto;

import lombok.Data;

/**
 * 登录请求DTO - 接收小程序登录时传递的参数
 */
@Data
public class LoginRequest {

    /**
     * 微信登录凭证（通过 wx.login 获取）
     * 必填，有效期5分钟
     */
    private String code;

    /**
     * 用户昵称（通过 wx.getUserProfile 获取）
     * 可选，用户拒绝授权时可能为空
     */
    private String nickname;

    /**
     * 用户头像URL（通过 wx.getUserProfile 获取）
     * 可选，用户拒绝授权时可能为空
     */
    private String avatarUrl;
}