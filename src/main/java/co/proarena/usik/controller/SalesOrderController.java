package co.proarena.usik.controller;

import co.proarena.usik.constants.ViewsConstants;
import co.proarena.usik.entity.Product;
import co.proarena.usik.entity.SalesOrder;
import co.proarena.usik.entity.User;
import co.proarena.usik.service.ProductService;
import co.proarena.usik.service.SalesOrderService;
import co.proarena.usik.utils.RedirectUtils;
import co.proarena.usik.utils.StoredAttributeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;


@Controller
public class SalesOrderController {

    @Autowired
    private SalesOrderService salesOrderService;
    @Autowired
    private ProductService productService;

    @RequestMapping("/addToCart")
    public String addSalesOrder(@RequestParam Long productId, HttpServletRequest request) {
        User user = StoredAttributeUtils.getLoginedUser(request.getSession());
        Product product = productService.get(productId);

        SalesOrder salesOrder = salesOrderService.findByUserIdAndProductId(user.getId(), product.getId());

        if (salesOrder == null) {
            salesOrder = new SalesOrder();
            salesOrder.setDate(Date.valueOf(LocalDate.now()));
            salesOrder.setPrice(product.getPrice());
            salesOrder.setProductId(product.getId());
            salesOrder.setUserId(user.getId());
            salesOrder.setQuantity(1);
        } else {
            salesOrder.setQuantity(salesOrder.getQuantity() + 1);
        }

        product.setAmount(product.getAmount() - salesOrder.getQuantity());
        product.setSoldNumber(product.getSoldNumber() + salesOrder.getQuantity());

        salesOrder.setTotal(salesOrder.getPrice() * salesOrder.getQuantity());
        productService.save(product);
        salesOrderService.save(salesOrder);
        return ViewsConstants.REDIRECT;
    }

    @RequestMapping(value = "/editOrder", method = RequestMethod.POST)
    public ModelAndView editOrder(@RequestParam long id) {
        SalesOrder order = salesOrderService.get(id);
        Product product = productService.get(order.getProductId());
        ModelAndView mav = new ModelAndView(ViewsConstants.JSP_EDIT_ORDER);
        mav.addObject("order", order);
        mav.addObject("product", product);
        return mav;
    }

    @RequestMapping(value = "/editOrder", method = RequestMethod.GET)
    public String editOrderInvalid(HttpServletRequest request) {
        return RedirectUtils.invalidRedirect(request);
    }

    @RequestMapping("/saveOrder")
    public String saveOrder(@ModelAttribute("order") SalesOrder salesOrder) {
        Product product = productService.get(salesOrder.getProductId());
        if (salesOrder.getQuantity().equals(product.getSoldNumber())) {
            return ViewsConstants.REDIRECT + ViewsConstants.JSP_USER;
        }
        product.setAmount((product.getAmount() + product.getSoldNumber()) - salesOrder.getQuantity());
        product.setSoldNumber(salesOrder.getQuantity());
        salesOrder.setTotal(salesOrder.getPrice() * salesOrder.getQuantity());
        productService.save(product);
        salesOrderService.save(salesOrder);
        return ViewsConstants.REDIRECT + ViewsConstants.JSP_USER;
    }


}
