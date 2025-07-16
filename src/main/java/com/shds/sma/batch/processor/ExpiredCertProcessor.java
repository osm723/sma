package com.shds.sma.batch.processor;

import com.shds.sma.apps.cert.entity.Cert;
import org.springframework.batch.item.ItemProcessor;

public class ExpiredCertProcessor implements ItemProcessor<Cert, Cert> {
    @Override
    public Cert process(Cert item) {
        item.setValidityN();
        return item;
    }
}
