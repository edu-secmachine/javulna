/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.dto;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author peti
 */
public class OrderListDto {
    
    @Valid
    private List<OrderItemDto> orderItems;

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }
    
    
}
