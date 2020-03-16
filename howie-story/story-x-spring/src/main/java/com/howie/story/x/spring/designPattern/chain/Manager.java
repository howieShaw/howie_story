package com.howie.story.x.spring.designPattern.chain;

public class Manager extends PriceHandler {
    @Override
    protected void priceDiscount(float discount) {
        if (discount < 0.2) {
            System.out.println("manager 经理处理了折扣为"+discount*100+"%的价格");
        } else {
            if (successor == null) {
                System.out.println("manager successor 为空");
                return;
            }
            successor.priceDiscount(discount);
        }
    }
}
