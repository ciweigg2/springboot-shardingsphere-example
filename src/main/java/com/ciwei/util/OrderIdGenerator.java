package com.ciwei.util;

import org.apache.commons.lang3.time.FastDateFormat;

import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生成订单编号
 *
 * @Author Ciwei
 * @Date 2019/7/13/013
 */
public class OrderIdGenerator {

    private static final FastDateFormat pattern = FastDateFormat.getInstance("yyyyMMddHHmmss");

    private static final AtomicInteger atomicInteger = new AtomicInteger(1);

    private static ThreadLocal<StringBuilder> threadLocal = new ThreadLocal<StringBuilder>();

    /**
     * 【长码生成策略】
     *
     * @param lock 生成的UUID32位参数
     * @return 长码机制
     * @时间20180511231532
     * @二位随机数
     * @lock的hash-code编码
     */
    public static String getLongOrderId(String lock) {
        StringBuilder builder = new StringBuilder(pattern.format(Instant.now().toEpochMilli()));// 取系统当前时间作为订单号前半部分
        builder.append(Math.abs(lock.hashCode()));// HASH-CODE
        builder.append(atomicInteger.getAndIncrement());// 自增顺序
        threadLocal.set(builder);
        return threadLocal.get().toString();
    }

    /**
     * 【短码生成策略】
     *
     * @param lock
     * @return
     */
    public static String getShortOrderId(String lock) {
        StringBuilder builder = new StringBuilder(ThreadLocalRandom.current().nextInt(0, 999));// 随机数
        builder.append(Math.abs(lock.hashCode()));// HASH-CODE
        builder.append(atomicInteger.getAndIncrement());// 自增顺序
        threadLocal.set(builder);
        return threadLocal.get().toString();
    }

}
