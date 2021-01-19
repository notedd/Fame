package com.zbw.fame.controller.admin;

import com.zbw.fame.model.domain.User;
import com.zbw.fame.model.param.LoginParam;
import com.zbw.fame.model.param.ResetPasswordParam;
import com.zbw.fame.model.param.ResetUserParam;
import com.zbw.fame.service.UserService;
import com.zbw.fame.util.FameUtil;
import com.zbw.fame.util.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 后台用户验证 Controller
 *
 * @author zzzzbw
 * @since 2017/7/11 20:15
 */
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthController {

    private final UserService userService;

    /**
     * 后台登录
     *
     * @return {@link RestResponse#ok()}
     */
    @PostMapping("login")
    public RestResponse<RestResponse.Empty> login(@RequestBody @Valid LoginParam param) {
        User user = userService.login(param);
        FameUtil.setLoginUser(user);
        return RestResponse.ok();
    }

    /**
     * 登出
     *
     * @return {@link RestResponse#ok()}
     */
    @PostMapping("logout")
    public RestResponse<RestResponse.Empty> logout() {
        FameUtil.clearLoginUser();
        return RestResponse.ok();
    }

    /**
     * 修改用户名密码
     *
     * @return {@link RestResponse#ok()}
     */
    @PutMapping("reset/password")
    public RestResponse<RestResponse.Empty> resetPassword(@RequestBody @Valid ResetPasswordParam param) {
        User user = FameUtil.getLoginUser();
        userService.resetPassword(user.getId(), param);
        this.logout();
        return RestResponse.ok();
    }

    /**
     * 修改用户信息
     *
     * @return {@link RestResponse#ok()}
     */
    @PutMapping("reset/user")
    public RestResponse<RestResponse.Empty> resetUser(@RequestBody @Valid ResetUserParam param) {
        User user = FameUtil.getLoginUser();
        userService.resetUser(user.getId(), param);
        this.logout();
        return RestResponse.ok();
    }

    /**
     * 获取当前用户
     *
     * @return {@link RestResponse#ok()}
     */
    @GetMapping("user")
    public RestResponse<User> getUser() {
        User user = FameUtil.getLoginUser();
        return RestResponse.ok(user);
    }

}
