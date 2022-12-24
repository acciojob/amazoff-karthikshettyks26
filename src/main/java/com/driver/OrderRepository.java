package com.driver;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OrderRepository {

    HashMap<String,Order> orders = new HashMap<>();
    Map<String,DeliveryPartner> deliveryPartner = new HashMap<>();
    Map<String, List<String>> orderPartnerPair = new HashMap<>();

    HashSet<String> assigned = new HashSet<>();

    public void addOrderToRepo(Order order){
        orders.put(order.getId(),order);
    }

    public void addPartnerToRepo(String partnerId){
        deliveryPartner.put(partnerId,new DeliveryPartner(partnerId));
    }

    public void addOrderPartnerPairToRepo(String orderId,String partnerId){
        if(orderPartnerPair.containsKey(partnerId)){
            orderPartnerPair.get(partnerId).add(orderId);
        }else{
            List<String> list = new ArrayList<>();
            list.add(orderId);
            orderPartnerPair.put(partnerId,list);
        }
        assigned.add(orderId);
    }
    public Order getOrderfromRepo(String orderId){
        if(orders.containsKey(orderId)){
            return orders.get(orderId);
        }else{
            return null;
        }
    }

    public DeliveryPartner getDeliveryPartnerfromRepo(String partnerId){
        if(deliveryPartner.containsKey(partnerId)){
            return deliveryPartner.get(partnerId);
        }else{
            return null;
        }
    }

    public int getNumOfOrderForPartnerfromRepo(String partnerId){
        if(orderPartnerPair.containsKey(partnerId)){
            return orderPartnerPair.get(partnerId).size();
        }else{
            return 0;
        }
    }

    public List<String> getOrdersByPartnerIdfromRepo(String partnerId){
        if(orderPartnerPair.containsKey(partnerId)){
            return orderPartnerPair.get(partnerId);
        }else{
            return null;
        }
    }

    public List<String> getAllOrderfromRepo(){
        List<String> list = new ArrayList<>();
        for(String orderId:orders.keySet()){
            list.add(orderId);
        }
        return list;
    }

    public int getCountOfUnassignedfromRepo(){
        int num = 0;
        for(String orderId:orders.keySet()){
            if(!assigned.contains(orderId)){
                num++;
            }
        }
        return num;
    }

    public int getOrdersLeftAfterGivenTimeByPartnerIdfromRepo(int time,String partnerId){
        int count = 0;
        List<String> orderList = orderPartnerPair.get(partnerId);

        for(int i=0;i<orderList.size();i++){
            Order curOrder = orders.get(orderList.get(i));
            if(curOrder.getDeliveryTime()>time){
                count++;
            }
        }
        return count;
    }


    public String getLastDeliveryTimeByPartnerIdfromRepo(String partnerId){
        int lastTime = 0;
        List<String> orderList = orderPartnerPair.get(partnerId);

        for(int i=0;i<orderList.size();i++){
            Order curOrder = orders.get(orderList.get(i));
            lastTime = Math.max(lastTime,curOrder.getDeliveryTime());
        }

        int hour = lastTime/60;
        int min = lastTime%60;

        String timeInHHMM = "";

        if(hour<=9){
            timeInHHMM += "0" + hour;
        }else if(hour>9){
            timeInHHMM+= hour + "";
        }

        timeInHHMM += ":";

        if(min<=9){
            timeInHHMM += "0" + min;
        }else if(hour>9){
            timeInHHMM+= min + "";
        }

        return timeInHHMM;
    }

    public void deletePartnerByIdfromRepo(String partnerId){
        deliveryPartner.remove(partnerId);
        List<String> list = orderPartnerPair.get(partnerId);
        orderPartnerPair.remove(partnerId);

        for(int i=0;i<list.size();i++){
            assigned.remove(list.get(i));
        }

    }

    public void deleteOrderByIdfromrRepo(String orderId){
        orders.remove(orderId);

        for(String partnerId:orderPartnerPair.keySet()){
            List<String> curOrder = orderPartnerPair.get(partnerId);
            for(int i=0;i<curOrder.size();i++){
                if(curOrder.get(i).equals(orderId))
                    curOrder.remove(orderId);
            }
        }

        assigned.remove(orderId);
    }

}
