package com.raptor.ecommerceproject.services;

import com.raptor.ecommerceproject.models.Order;
import com.raptor.ecommerceproject.models.User;
import com.raptor.ecommerceproject.repositories.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements IOrderService{

    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
        //formato de la orden 000001
    public String generateNumberOrder(){
        int number=0;
        String numberConcatenated="";
        List<Order> orders=findAll();
        List<Integer> numbers=new ArrayList<Integer>();
        orders.stream().forEach(o->numbers.add(Integer.parseInt(o.getNumberOrder())));
        if(orders.isEmpty()){
            number=1;
        }else{
            number=numbers.stream().max(Integer::compare).get();
            number++;
        }
        if(number<10){
            numberConcatenated="000000000"+String.valueOf(number);
        }else if(number<100){
            numberConcatenated="00000000"+String.valueOf(number);
        }else if(number<1000){
            numberConcatenated="0000000"+String.valueOf(number);
        }else if(number<10000){
            numberConcatenated="000000"+String.valueOf(number);
        }
        return numberConcatenated;
    }

    @Override
    public List<Order> findByUserOrder(User user) {
        return orderRepository.findByUserOrder(user);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

}
