package edu.fdzc.wx1.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.fdzc.wx1.entity.User;
import edu.fdzc.wx1.mapper.UserMapper;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 用户业务逻辑层 - 处理微信登录和用户注册
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.secret}")
    private String secret;

    /**
     * 调用微信接口，通过code换取openid
     * @param code 前端传来的登录凭证
     * @return 用户唯一标识openid
     */
    public String wxLogin(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid
                + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        String result = HttpUtil.get(url);
        JSONObject json = JSONUtil.parseObj(result);
        return json.getStr("openid");
    }
    /**
     * 登录或注册用户
     * 根据openid查询用户是否存在，不存在则新建用户
     * @param openid 微信用户唯一标识
     * @param nickname 用户昵称（未授权时默认"微信用户"）
     * @param avatarUrl 用户头像（未授权时设为空串）
     * @return 用户对象
     */
    public User loginOrRegister(String openid, String nickname, String avatarUrl) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("openid", openid);
        User user = userMapper.selectOne(wrapper);

        if (user == null) {
            user = new User();
            user.setOpenid(openid);
            user.setNickname(nickname != null ? nickname : "微信用户");
            user.setAvatarUrl(avatarUrl != null ? avatarUrl : "");
            userMapper.insert(user);
        }
        return user;
    }
}