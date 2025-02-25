package com.shds.sma.batch.processor;

import com.shds.sma.ip.entity.Ip;
import org.springframework.batch.item.ItemProcessor;


public class ExpiredIpProcessor implements ItemProcessor<Ip, Ip> {
    @Override
    public Ip process(Ip item) {
        item.setValidityN();
        return item;
    }
}
