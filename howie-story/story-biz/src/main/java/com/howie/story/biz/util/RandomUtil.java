package com.howie.story.biz.util;

import java.util.Random;

public class RandomUtil {
    private RandomUtil () {}

    public static int getRandom(int min,int max) {
        Random random =new Random();
        return random.nextInt(max)%(max-min+1) + min;
    }
}
