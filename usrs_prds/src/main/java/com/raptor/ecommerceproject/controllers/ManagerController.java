package com.raptor.ecommerceproject.controllers;

import com.raptor.ecommerceproject.models.Order;
import com.raptor.ecommerceproject.models.Product;
import com.raptor.ecommerceproject.services.IOrderService;
import com.raptor.ecommerceproject.services.IUserService;
import com.raptor.ecommerceproject.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ProductService productService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService iOrderService;

    private Logger logger= LoggerFactory.getLogger(ManagerController.class);


    @GetMapping("")
    public String home(Model model){
        List<Product> products=productService.findAll();
        model.addAttribute("products",products);
        return "manager/home";
    }

    @GetMapping("/users")
    public String userAll(Model model){
            model.addAttribute("users",userService.findAll());
        return "manager/Users";
    }


    @GetMapping("/orders")
    public String ordersAll(Model model){
        model.addAttribute("orders",iOrderService.findAll());
        return "manager/Orders";
    }

    @GetMapping("/detail/{id}")
    public String detailOrder(@PathVariable Long id,Model model){
        logger.info("This is the id of the order",id);
        Order order=iOrderService.findById(id).get();
        model.addAttribute("details",order.getDetails());

        return "manager/detailsOrders";
    }
}
