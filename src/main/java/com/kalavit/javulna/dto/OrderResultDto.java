/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.dto;

/**
 *
 * @author peti
 */
public class OrderResultDto {
    private OrderListDto orderList;
    private int sumPriceToPay;

    public OrderListDto getOrderList() {
        return orderList;
    }

    public void setOrderList(OrderListDto orderList) {
        this.orderList = orderList;
    }

    public int getSumPriceToPay() {
        return sumPriceToPay;
    }

    public void setSumPriceToPay(int sumPriceToPay) {
        this.sumPriceToPay = sumPriceToPay;
    }

    
    
    
}
