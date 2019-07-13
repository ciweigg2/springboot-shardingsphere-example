package com.ciwei.util;

import java.util.UUID;

/**
 * @author Ciwei
 */
public class UUIDutil {
    /**
     * 生成id
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
