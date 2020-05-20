package co.proarena.usik.controller;

import co.proarena.usik.constants.ViewsConstants;
import co.proarena.usik.entity.User;
import co.proarena.usik.service.SalesOrderService;
import co.proarena.usik.service.SecurityService;
import co.proarena.usik.service.UserService;
import co.proarena.usik.utils.RememberUtils;
import co.proarena.usik.utils.StoredAttributeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private SalesOrderService salesOrderService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView userInfo(HttpServletRequest request) {
        User loginUser = StoredAttributeUtils.getLoginedUser(request.getSession());
        if (loginUser != null) {
            ModelAndView mav = new ModelAndView(ViewsConstants.JSP_USER);
            mav.addObject("loginedUser", loginUser);
            mav.addObject("orderList", salesOrderService.findByUserId(loginUser.getId()));
            mav.addObject("setting", "true");
            return mav;
        }
        ModelAndView mav = new ModelAndView(ViewsConstants.REDIRECT + ViewsConstants.JSP_LOGIN);
        mav.addObject("error", "Login first!");
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {
        return ViewsConstants.JSP_LOGIN;
    }


    @GetMapping("/registration")
    public String registrationForm(Map<String, Object> modal) {
        modal.put("regUser", new User());
        return ViewsConstants.JSP_REGISTRATION;
    }


    @PostMapping("/registration")
    public String registrationUser(@ModelAttribute("regUser") User user, HttpServletRequest request,
                                   HttpServletResponse response) {
        User loginUser = userService.findByEmail(user.getEmail());
        boolean remember = "Y".equals(request.getParameter("remember"));
        if (loginUser == null) {
            String oldPass = user.getPassword();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
//            user.setPassword(oldPass);
            securityService.autoLogin(user);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            StoredAttributeUtils.storeLoginedUser(request.getSession(), user);
            RememberUtils.remember(response, remember, user);
            return ViewsConstants.REDIRECT + ViewsConstants.JSP_USER;
        }
        return ViewsConstants.REDIRECT + ViewsConstants.JSP_REGISTRATION + "?error=Email is already registered!";
    }


    @RequestMapping("/setting")
    public ModelAndView edit(HttpServletRequest request) {
        User loginUser = StoredAttributeUtils.getLoginedUser(request.getSession());
        if (loginUser != null) {
            ModelAndView mav = new ModelAndView(ViewsConstants.JSP_SETTING);
            mav.addObject("user", loginUser);
            return mav;
        } else {
            ModelAndView mav = new ModelAndView(ViewsConstants.REDIRECT + ViewsConstants.JSP_LOGIN);
            mav.addObject("error", "Login first!");
            return mav;
        }
    }

    @RequestMapping("/saveSetting")
    public String saveEdit(@ModelAttribute("user") User user, HttpServletRequest request) {
        userService.save(user);
        StoredAttributeUtils.storeLoginedUser(request.getSession(), user);
        return ViewsConstants.REDIRECT + ViewsConstants.JSP_USER;
    }

}
