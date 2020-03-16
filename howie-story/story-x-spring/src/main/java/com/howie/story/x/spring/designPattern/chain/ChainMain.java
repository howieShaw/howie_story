package com.howie.story.x.spring.designPattern.chain;

import java.util.Arrays;
import java.util.List;

public class ChainMain {
    public static void main(String[] args) {
        List<PriceHandler> priceHandlers = Arrays.asList(new Sales(),new Manager(),new Ceo());

        PriceChain priceChain = new PriceChain(priceHandlers);
        priceChain.execute(0.2f);

    }
}
