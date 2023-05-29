package com.raptor.ecommerceproject.repositories;

import com.raptor.ecommerceproject.models.Order;
import com.raptor.ecommerceproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUserOrder(User u);
}
