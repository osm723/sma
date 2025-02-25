package com.shds.sma.batch.processor;

import com.shds.sma.batch.entity.OrderEntity;
import org.springframework.batch.item.ItemProcessor;

public class OrderItemProcessor implements ItemProcessor<OrderEntity, OrderEntity> {

    @Override
    public OrderEntity process(OrderEntity orderEntity) throws Exception {
        // 상태가 NEW인 경우 PROCESSED로 변경
        if ("NEW".equalsIgnoreCase(orderEntity.getStatus())) {
            orderEntity.setStatus("PROCESSED");
        }
        return orderEntity;
    }
}
