package com.driver;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order){
        orderRepository.addOrderToRepo(order);
    }

    public void addPartner(String partnerId){
        orderRepository.addPartnerToRepo(partnerId);
    }

    public void addOrderPartnerPair(String orderId,String partnerId){
        orderRepository.addOrderPartnerPairToRepo(orderId,partnerId);
    }

    public Order getOrder(String orderId){
        return orderRepository.getOrderfromRepo(orderId);
    }

    public DeliveryPartner getDeliveryPartner(String partnerId){
        return orderRepository.getDeliveryPartnerfromRepo(partnerId);
    }

    public int getNumberOfOrderForPartner(String partnerId){
        return orderRepository.getNumOfOrderForPartnerfromRepo(partnerId);
    }

    public List<String> getOrdersByPartnerId(String partnerId){
        return orderRepository.getOrdersByPartnerIdfromRepo(partnerId);
    }

    public List<String> getAllOrder(){
        return orderRepository.getAllOrderfromRepo();
    }

    public int getCountOfUnassigned(){
        return orderRepository.getCountOfUnassignedfromRepo();
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(int time,String partnerId){
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerIdfromRepo(time,partnerId);
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId){
        return orderRepository.getLastDeliveryTimeByPartnerIdfromRepo(partnerId);
    }

    public void deletePartnerById(String partnerId){
        orderRepository.getOrdersByPartnerIdfromRepo(partnerId);
    }

    public void deleteOrderById(String orderId){
        orderRepository.deleteOrderByIdfromrRepo(orderId);
    }








}
