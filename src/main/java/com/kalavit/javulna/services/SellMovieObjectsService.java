/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.services;

import com.kalavit.javulna.dto.OrderItemDto;
import com.kalavit.javulna.dto.OrderListDto;
import com.kalavit.javulna.dto.OrderResultDto;
import com.kalavit.javulna.exception.InvalidOrderException;
import com.kalavit.javulna.model.MovieObject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author peti
 */
@Service
public class SellMovieObjectsService {
    
    @PersistenceContext
    EntityManager em;

    public List<MovieObject> findAllBuyableObjects() {
        return em.createQuery("select m from MovieObject m").getResultList();
    }

    public OrderResultDto placeOrder(OrderListDto orderList) {
        List<OrderItemDto> orderItems = orderList.getOrderItems();
        if(orderItems.isEmpty()){
            throw new InvalidOrderException("Emtpy order.");
        }
        Set<String> movieObjectIds = new HashSet<>();
        int sumPrice = 0;
        for (OrderItemDto orderItem : orderItems) {
            String movieObjectId = orderItem.getMovieObjectId();
            MovieObject mo = em.find(MovieObject.class, movieObjectId);
            if(mo == null){
                throw new InvalidOrderException("Non existing movieObject in orderItem.");
            }
            if(movieObjectIds.contains(movieObjectId)){
                throw new InvalidOrderException("An order list should contain each movieObject only once.");
            }
            movieObjectIds.add(movieObjectId);
            sumPrice += (mo.getPrice()*orderItem.getNrOfItemsOrdered());
            
        }
        OrderResultDto result = new OrderResultDto();
        result.setOrderList(orderList);
        result.setSumPriceToPay(sumPrice);
        return result;
        
    }
    
}
