package com.howie.story.biz.rpc.reigster;

import com.howie.story.biz.rpc.framewoker.UrlRpc;

import java.util.HashMap;
import java.util.Map;

public class Register {
    private static Map<String, Map<UrlRpc,Class>> REGISTERS = new HashMap<String, Map<UrlRpc, Class>>();

    public static void regist(UrlRpc urlRpc,String interfaceName,Class implClass) {
        Map<UrlRpc,Class> map = new HashMap<UrlRpc, Class>();
        map.put(urlRpc,implClass);
        REGISTERS.put(interfaceName,map);
    }
}
