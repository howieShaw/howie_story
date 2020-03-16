package com.howie.story.x.spring.designPattern.chain;

public abstract class PriceHandler {

    /**
     * 创建一个同类型的依赖，也就后继处理者
     */
    protected PriceHandler successor;

    public void setSuccessor(PriceHandler successor) {
        this.successor = successor;
    }

    protected abstract void priceDiscount(float discount);
}
