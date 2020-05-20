package co.proarena.usik.controller;


import co.proarena.usik.constants.ViewsConstants;
import co.proarena.usik.entity.Product;
import co.proarena.usik.service.ProductService;
import co.proarena.usik.utils.RedirectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = {"/", "/home"})
    public ModelAndView home() {
        List<Product> productList = productService.listAll();
        ModelAndView mav = new ModelAndView(ViewsConstants.JSP_HOME);
        mav.addObject("productList", productList);
        return mav;
    }

    @RequestMapping("/accessDenied")
    public ModelAndView accessDenied() {
        ModelAndView mav = new ModelAndView(ViewsConstants.JSP_ACCESS_DENIED);
        mav.addObject("error", "Sorry. This page not found!");
        return mav;
    }

    @RequestMapping(value = "/newProduct", method = RequestMethod.POST)
    public String newProductForm(Map<String, Object> model) {
        Product product = new Product();
        model.put("product", product);
        return ViewsConstants.JSP_NEW_PRODUCT;
    }

    @RequestMapping(value = "/newProduct", method = RequestMethod.GET)
    public String newProductFormInvalid(HttpServletRequest request) {
        return RedirectUtils.invalidRedirect(request);
    }

    @RequestMapping(value = "/editProduct", method = RequestMethod.POST)
    public ModelAndView editProductForm(@RequestParam long id) {
        Product product = productService.get(id);
        ModelAndView mav = new ModelAndView(ViewsConstants.JSP_EDIT_PRODUCT);
        mav.addObject("product", product);
        return mav;
    }

    @RequestMapping(value = "/editProduct", method = RequestMethod.GET)
    public String editProductFormInvalid(HttpServletRequest request) {
        return RedirectUtils.invalidRedirect(request);
    }

    @RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("product") Product product) {
        productService.save(product);
        return ViewsConstants.REDIRECT;
    }

    @RequestMapping(value = "/delProduct", method = RequestMethod.POST)
    public String delProduct(@RequestParam long id) {
        productService.delete(id);
        return ViewsConstants.REDIRECT;
    }

    @RequestMapping(value = "/delProduct", method = RequestMethod.GET)
    public String delProductInvalid(HttpServletRequest request) {
        return RedirectUtils.invalidRedirect(request);
    }

    @RequestMapping("/search")
    public ModelAndView search(@RequestParam String keyword) {
        List<Product> productList = productService.search(keyword);
        ModelAndView mav = new ModelAndView(ViewsConstants.JSP_HOME);
        mav.addObject("productList", productList);
        return mav;
    }
}
