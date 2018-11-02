package com.howie.story.api.enums;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 6:40 PM 2018/10/29
 * @Modified by:xiaohaoyun
 */
public enum ColorEnum {

    RED("red"),
    BLUE("blue"),
    YELLOW("yellow"),
    GREEN("green"),
    LIGHT_SEA_GREEN("#20b2aa"),
    LIGHT_PINK("#ffb6c1"),
    PALE_VIOLET_RED("#db7093"),
    DEFAULT("")

    ;
    private String name;

    ColorEnum(String name) {
        this.name = name;
    }



    public String getName() {
        return name;
    }
}
