package com.howie.story.x.spring.designPattern.chain;


public class Sales extends PriceHandler{

    @Override
    protected void priceDiscount(float discount) {
        if (discount < 0.05) {
            System.out.println("sales 销售处理了折扣为"+discount*100+"%的商品价格");
        } else {
            if (successor == null) {
                System.out.println("sales successor is null");
                return;
            }
            successor.priceDiscount(discount);
        }
    }
}
