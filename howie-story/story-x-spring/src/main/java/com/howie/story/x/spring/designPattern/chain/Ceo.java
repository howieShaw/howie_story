package com.howie.story.x.spring.designPattern.chain;

public class Ceo extends PriceHandler {

    @Override
    protected void priceDiscount(float discount) {
        if (discount < 0.5) {
            System.out.println("ceo 处理了折扣小于"+100*discount+"%的商品");
        }else {
            if (successor == null) {
                System.out.println("ceo successor 为空");
            }
            successor.priceDiscount(discount);
        }
    }
}
