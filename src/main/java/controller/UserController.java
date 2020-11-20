package controller;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.ResponseResult;
import pojo.User;
import service.UserService;
import service.exception.UserNoLoginException;
import utils.VertifyCode;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: homeplus
 * @description: 用户操作
 * @author: ZEK
 * @create: 2019-03-03 16:50
 **/
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 显示注册页面
     * @return 注册页面
     */
    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    /**
     * 显示登录页面
     * @return 登录页面
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    /**
     * 用户注册
     * @return 实体类
     */
    @RequestMapping("/register")
    @ResponseBody
    public ResponseResult<Void> userRegister(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("role") int role,
            HttpSession session
    ) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setStatus(1);
        userService.insert(user);
        session.setAttribute("username", username);
        return new ResponseResult<Void>();
    }

    /**
     * 用户登录
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResponseResult<Integer> userLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("vertifyCode") String vertifyCode,
            HttpSession session
    ) {
        Boolean result = userService.login(username, password);
        ResponseResult<Integer> response = new ResponseResult<>();
        if (result) {
            System.out.println("用户存在");
            response.setData(userService.getLoginRole(username));
        }

        String frontVertifyCode = vertifyCode.toUpperCase();
        String behindVertifyCode = session.getAttribute("vertifyCode").toString().toUpperCase();

        // System.out.println("前端的验证码" + frontVertifyCode);
        // System.out.println("后端的验证码" + behindVertifyCode);

        if (!frontVertifyCode.equals(behindVertifyCode)) {
            System.out.println(frontVertifyCode.equals(behindVertifyCode));
            response.setState(502);
        }
        session.setAttribute("username", username);
        return response;
    }

    @RequestMapping("/loginSuccess")
    @ResponseBody
    public ResponseResult<Void> loginSuccess (
        HttpSession session
    ) throws UserNoLoginException {
        String username = (String) session.getAttribute("username");
        ResponseResult<Void> result = new ResponseResult<>();
        if (StringUtils.isEmpty(username)) {
            throw new UserNoLoginException("用户未登录");
        } else {
            if (session.getAttribute("username").toString() != null) {
                result.setState(200);
            }
        }
        return result;
    }

    @RequestMapping("/logout")
    public String userLogout (HttpSession session) {
        session.removeAttribute("username");
        session.invalidate();
        return "redirect:../login.html";
    }

    /**
     * 刷新验证码
     * @param session 用户的session
     * @return 验证前后台的验证码是否相同
     */
    @RequestMapping("/refresh")
    @ResponseBody
    public ResponseResult<String> getVertifyCode (
            HttpSession session
    ) {
        ResponseResult<String> result = new ResponseResult<String>();
        String vertifyCode = VertifyCode.create();
        session.setAttribute("vertifyCode", vertifyCode);
        result.setData(vertifyCode);
        System.out.println("后台生成的验证码：" + vertifyCode);
        return result;
    }

}
