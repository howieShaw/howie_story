package com.howie.story.biz.rpc.privoder.impl;

import com.howie.story.biz.rpc.privoder.HelloService;

public class HelloServiceImpl implements HelloService {
    public String sayHello(String string) {
        return "hello" + string;
    }
}
