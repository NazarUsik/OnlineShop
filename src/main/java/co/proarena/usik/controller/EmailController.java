package co.proarena.usik.controller;

import co.proarena.usik.constants.ViewsConstants;
import co.proarena.usik.entity.ConfirmationToken;
import co.proarena.usik.entity.Product;
import co.proarena.usik.entity.SalesOrder;
import co.proarena.usik.entity.User;
import co.proarena.usik.service.*;
import co.proarena.usik.utils.ReceiptUtils;
import co.proarena.usik.utils.RememberUtils;
import co.proarena.usik.utils.StoredAttributeUtils;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class EmailController {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SalesOrderService salesOrderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @RequestMapping("/sentReceipt")
    public ModelAndView sentReceipt(@RequestParam("orderId") Long orderId, HttpServletRequest request)
            throws MessagingException, IOException {
        User user = StoredAttributeUtils.getLoginedUser(request.getSession());
        SalesOrder order = salesOrderService.get(orderId);
        Product product = productService.get(order.getProductId());

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(user.getEmail());
        helper.setSubject("Receipt");
        helper.setText("You order:" + orderId);

        String fileName = "receip.pdf";
        try {
            ReceiptUtils.createReceiptPdf(fileName, user, product, order);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        FileSystemResource file = new FileSystemResource(new File(fileName));
        helper.addAttachment("Receipt.pdf", file);

        mailSender.send(message);

        ModelAndView mav = new ModelAndView(ViewsConstants.REDIRECT + ViewsConstants.JSP_USER);
        mav.addObject("status", "successful");
        return mav;
    }

    @RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
    public ModelAndView displayResetPassword() {
        ModelAndView modelAndView = new ModelAndView(ViewsConstants.JSP_FORGE_PASS);
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
    public ModelAndView forgotUserPassword(@ModelAttribute("user") User user, HttpServletRequest request) {
        User existingUser = userService.findByEmail(user.getEmail());
        ModelAndView modelAndView = new ModelAndView();
        if (existingUser != null) {

            ConfirmationToken confirmationToken = new ConfirmationToken(existingUser);

            confirmationTokenService.save(confirmationToken);

            String uri = request.getScheme() +      // "http"
                    "://" +                         // + "://"
                    request.getServerName() +       // "localhost"
                    ":" +                           // ":"
                    request.getServerPort() +       // "8080"
                    "/confirm-reset?token=" +       // "/confirm-reset"
                    confirmationToken.getConfirmationToken();

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(existingUser.getEmail());
            mailMessage.setSubject("Complete Password Reset!");
            mailMessage.setText("To complete the password reset process, please click here: " + uri);

            mailSender.send(mailMessage);

            modelAndView.addObject("message",
                    "Request to reset password received. Check your inbox for the reset link.");

        } else {
            modelAndView.addObject("message", "This email does not exist!");
        }

        modelAndView.setViewName(ViewsConstants.JSP_LOGIN);
        return modelAndView;
    }


    @RequestMapping(value = "/confirm-reset", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView validateResetToken(@RequestParam("token") String confirmationToken) {
        ModelAndView modelAndView = new ModelAndView();
        ConfirmationToken token = confirmationTokenService.findByConfirmationToken(confirmationToken);

        if (token != null) {
            User user = userService.findByEmail(token.getUser().getEmail());
            userService.save(user);
            modelAndView.addObject("user", user);
            modelAndView.addObject("email", user.getEmail());
            modelAndView.setViewName(ViewsConstants.JSP_RESET_PASS);
        } else {
            modelAndView.addObject("error", "The link is invalid or broken!");
            modelAndView.setViewName(ViewsConstants.JSP_ACCESS_DENIED);
        }

        return modelAndView;
    }


    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public ModelAndView resetUserPassword(@ModelAttribute("user") User user, HttpServletRequest request,
                                          HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();

        if (user.getEmail() != null) {
            User tokenUser = userService.findByEmail(user.getEmail());
            securityService.autoLogin(tokenUser);
            tokenUser.setPassword(passwordEncoder.encode(tokenUser.getPassword()));
            userService.save(tokenUser);
            StoredAttributeUtils.storeLoginedUser(request.getSession(), tokenUser);
            RememberUtils.remember(response, true, tokenUser);

            modelAndView.addObject("message",
                    "Password successfully reset. You can now log in with the new credentials.");
            modelAndView.setViewName(ViewsConstants.REDIRECT + ViewsConstants.JSP_USER);
        } else {
            modelAndView.addObject("error", "The link is invalid or broken!");
            modelAndView.setViewName(ViewsConstants.JSP_ACCESS_DENIED);
        }

        return modelAndView;
    }

}
