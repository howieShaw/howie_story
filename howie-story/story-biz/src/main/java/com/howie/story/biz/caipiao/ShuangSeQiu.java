package com.howie.story.biz.caipiao;

import com.howie.story.biz.util.RandomUtil;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ShuangSeQiu {

    public static String getRandomOne () {
        Set<Integer> redSet = new LinkedHashSet<>();
        int redSize = 6;
        int blueSize = 1;

        Set<Integer> blueSet = new LinkedHashSet<>();
        while (redSet.size() < redSize) {
            int num = RandomUtil.getRandom(1,33);
            redSet.add(num);
        }

        redSet = redSet.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toCollection(LinkedHashSet::new));

        while (blueSet.size() < blueSize) {
            int num = RandomUtil.getRandom(1,16);
            blueSet.add(num);
        }

        blueSet = blueSet.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toCollection(LinkedHashSet::new));

        return redSet.toString()+blueSet.toString();

    }

    public static void main(String[] args) {
        System.out.println(getRandomOne());
        System.out.println(getRandomOne());
        System.out.println(getRandomOne());

    }
}
