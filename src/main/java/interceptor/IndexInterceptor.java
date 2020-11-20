package interceptor;

import dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: homeplus
 * @description:
 * @author: ZEK
 * @create: 2019-04-17 14:22
 **/

public class IndexInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler)
            throws Exception {
        System.out.println("LoginInterceptor.preHandle()");
        // 获取Session
        HttpSession session = request.getSession();
        // 判断session中是否有登录信息
        if (session.getAttribute("username") == null) {
            // 没有登录信息，则：重定向到登录页
            response.sendRedirect("../login.html");
            // 执行拦截
            return false;
        } else {
            String username = session.getAttribute("username").toString();
            Integer role = userMapper.selectRole(username);
            if (role == -1) {
                response.sendRedirect("../admin-index.html");
            } else {
                response.sendRedirect("../login.html");
            }
            // 有登录信息，则：允许正常访问，直接放行
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("LoginInterceptor.postHandle()");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("LoginInterceptor.afterCompletion()");
    }

}

