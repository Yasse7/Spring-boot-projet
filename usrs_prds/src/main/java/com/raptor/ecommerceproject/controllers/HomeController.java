package com.raptor.ecommerceproject.controllers;

import com.raptor.ecommerceproject.models.Order;
import com.raptor.ecommerceproject.models.OrderDetail;
import com.raptor.ecommerceproject.models.Product;
import com.raptor.ecommerceproject.models.User;
import com.raptor.ecommerceproject.services.IOrderDetailService;
import com.raptor.ecommerceproject.services.IOrderService;
import com.raptor.ecommerceproject.services.ProductService;
import com.raptor.ecommerceproject.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {
    private final Logger log= LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;


    List<OrderDetail> details=new ArrayList<OrderDetail>();


    Order order=new Order();

    @GetMapping("")
    public String home(Model model, HttpSession session){
        log.info(": {}",session.getAttribute("idUser"));
        List<Product> productList=productService.findAll();
        model.addAttribute("products",productList);

        model.addAttribute("sesion",session.getAttribute("idUser"));

        return "User/home";
    }

    @GetMapping("productohome/{id}")
    public String productHome(@PathVariable Long id,Model model){
        log.info("produit{}",id);
        Product product=new Product();
        Optional<Product> productOptional=productService.get(id);
        product=productOptional.get();
        model.addAttribute("product",product);
        return "User/productohome";
    }

     @PostMapping("/cart")
    public String addCart(@RequestParam Long id,@RequestParam Integer cantidad,Model model){
        OrderDetail orderDetail=new OrderDetail();
        Product product=new Product();
        double sumTotal=0;

        Optional<Product> optionalProduct=productService.get(id);
        log.info("le produit est: {}",optionalProduct.get());
        log.info("Cantidad: {}",cantidad);
        product=optionalProduct.get();
        orderDetail.setQuantityOrder(cantidad.doubleValue());
        orderDetail.setPriceOrder(product.getPrice());
        orderDetail.setNameOrder(product.getNameProduct());
        orderDetail.setTotalOrder(product.getPrice()*cantidad);
        orderDetail.setProduct(product);


         Long idProduct=product.getId();
         boolean entered=details.stream().anyMatch(p-> p.getProduct().getId()==idProduct);
         if (!entered){
             details.add(orderDetail);
         }

        sumTotal=details.stream().mapToDouble(dt->dt.getTotalOrder()).sum();
        order.setTotal(sumTotal);
        model.addAttribute("cart",details);
        model.addAttribute("orden",order);
        return "User/carrito";
     }


    @GetMapping("/delete/cart/{id}")
    public String deleteProductCar(@PathVariable Long id,Model model){
        //Lista nueva de productos
        List<OrderDetail>orderNew=new ArrayList<OrderDetail>();
        for (OrderDetail orderDetail:details){
            if(orderDetail.getProduct().getId()!=id){
                orderNew.add(orderDetail);
            }
        }

        details=orderNew;
        double sumTotal=0;
        sumTotal=details.stream().mapToDouble(dt->dt.getTotalOrder()).sum();
        order.setTotal(sumTotal);
        model.addAttribute("cart",details);
        model.addAttribute("orden",order);

        return "User/carrito";
    }

    @GetMapping("/getCart")
    public String getCart(Model model,HttpSession session){
        model.addAttribute("cart",details);
        model.addAttribute("orden",order);


        model.addAttribute("sesion",session.getAttribute("idUser"));
        return "/User/carrito";
    }

    @GetMapping("/order")
    public String orderUser(Model model,HttpSession session){
        User user=userService.findById(Long.parseLong(session.getAttribute("idUser").toString())).get();
        model.addAttribute("usuario",user);
        model.addAttribute("cart",details);
        model.addAttribute("orden",order);
        return "User/resumenorden";
    }


    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session){
        Date dateCreated=new Date();
        order.setDateCreation(dateCreated);
        order.setNumberOrder(orderService.generateNumberOrder());


        User user=userService.findById(Long.parseLong(session.getAttribute("idUser").toString())).get();
        order.setUserOrder(user);
        orderService.save(order);


        for (OrderDetail dt:details){
            dt.setOrder(order);
            log.info("Esta es la orden: {}",dt);
            orderDetailService.save(dt);
        }

        order=new Order();
        details.clear();

        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchProduct(@RequestParam String nombre,Model model){
        log.info("Nombre del Producto: {}",nombre);
        List<Product> productList=productService.findAll().stream().filter(p->p.getNameProduct().toLowerCase().contains(nombre.toLowerCase())).collect(Collectors.toList());
        model.addAttribute("products",productList);
        return"User/home";
    }





}
