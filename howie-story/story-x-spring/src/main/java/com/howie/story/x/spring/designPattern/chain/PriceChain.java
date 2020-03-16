package com.howie.story.x.spring.designPattern.chain;

import java.util.Collections;
import java.util.List;

public class PriceChain {

    private List<PriceHandler> priceHandlers;

    public PriceChain (List<PriceHandler> priceHandlers) {
        this.priceHandlers = priceHandlers;
    }

    public void setPriceHandlers(List<PriceHandler> priceHandlers) {
        this.priceHandlers = priceHandlers;
    }

    private int index =0;

    public void execute(float discount) {
        if (priceHandlers.isEmpty()) {
            return;
        }
        priceHandlers.get(index++).priceDiscount(discount);
    }
}
