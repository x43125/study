package com.zf.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 雪花算法ID生成器
 * <p>
 * 生成64位长整型ID，结构如下：
 * - 1位符号位（始终为0）
 * - 41位时间戳（毫秒级，从自定义起始时间算起）
 * - 5位数据中心ID
 * - 5位机器ID
 * - 12位序列号（同一毫秒内的自增序号）
 * <p>
 * 使用场景：生成订单code，从code中提取时间戳用于分表路由
 */
public class CodeGenerator {

    /**
     * 起始时间戳（2024-01-01 00:00:00）
     * 用于计算相对时间戳，延长可用年限
     */
    private static final long START_TIMESTAMP = 1704067200000L;

    /**
     * 数据中心ID所占位数
     */
    private static final long DATACENTER_ID_BITS = 5L;

    /**
     * 机器ID所占位数
     */
    private static final long WORKER_ID_BITS = 5L;

    /**
     * 序列号所占位数
     */
    private static final long SEQUENCE_BITS = 12L;

    /**
     * 机器ID最大值（31）
     */
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    /**
     * 数据中心ID最大值（31）
     */
    private static final long MAX_DATACENTER_ID = ~(-1L << DATACENTER_ID_BITS);

    /**
     * 序列号掩码（4095）
     */
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    /**
     * 机器ID左移位数（12）
     */
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;

    /**
     * 数据中心ID左移位数（17）
     */
    private static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /**
     * 时间戳左移位数（22）
     */
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;

    /**
     * 数据中心ID
     */
    private final long datacenterId;

    /**
     * 机器ID
     */
    private final long workerId;

    /**
     * 序列号
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间戳
     */
    private long lastTimestamp = -1L;

    /**
     * 日期格式化器，用于从code提取年月
     */
    private static final DateTimeFormatter YEAR_MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM");

    /**
     * 构造函数
     *
     * @param datacenterId 数据中心ID（0-31）
     * @param workerId     机器ID（0-31）
     */
    public CodeGenerator(long datacenterId, long workerId) {
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException("Datacenter ID can't be greater than " + MAX_DATACENTER_ID + " or less than 0");
        }
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException("Worker ID can't be greater than " + MAX_WORKER_ID + " or less than 0");
        }
        this.datacenterId = datacenterId;
        this.workerId = workerId;
    }

    /**
     * 生成下一个雪花算法ID
     *
     * @return 雪花算法ID
     */
    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();

        // 时钟回拨检测
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id for " + (lastTimestamp - timestamp) + " milliseconds");
        }

        // 同一毫秒内，序列号自增
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            // 序列号溢出，等待下一毫秒
            if (sequence == 0) {
                timestamp = waitNextMillis(lastTimestamp);
            }
        } else {
            // 不同毫秒，序列号重置
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        // 拼接各部分生成最终ID
        return ((timestamp - START_TIMESTAMP) << TIMESTAMP_LEFT_SHIFT)
                | (datacenterId << DATACENTER_ID_SHIFT)
                | (workerId << WORKER_ID_SHIFT)
                | sequence;
    }

    /**
     * 等待下一毫秒
     *
     * @param lastTimestamp 上次时间戳
     * @return 新的时间戳
     */
    private long waitNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    /**
     * 从雪花算法ID中提取时间戳（毫秒）
     *
     * @param code 雪花算法ID
     * @return 时间戳（毫秒）
     */
    public static long extractTimestamp(long code) {
        return (code >> TIMESTAMP_LEFT_SHIFT) + START_TIMESTAMP;
    }

    /**
     * 从雪花算法ID中提取年月字符串（如：202603）
     * 用于分表路由
     *
     * @param code 雪花算法ID
     * @return 年月字符串，格式：yyyyMM
     */
    public static String extractYearMonth(long code) {
        long timestamp = extractTimestamp(code);
        LocalDate date = Instant.ofEpochMilli(timestamp)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return date.format(YEAR_MONTH_FORMATTER);
    }

    /**
     * 从雪花算法ID中提取年月数字（如：202603）
     * 用于分表路由的数字比较
     *
     * @param code 雪花算法ID
     * @return 年月数字
     */
    public static int extractYearMonthAsInt(long code) {
        return Integer.parseInt(extractYearMonth(code));
    }

    /**
     * 默认实例（datacenterId=1, workerId=1）
     * 用于简单场景
     */
    private static final CodeGenerator DEFAULT_INSTANCE = new CodeGenerator(1, 1);

    /**
     * 使用默认实例生成ID
     *
     * @return 雪花算法ID
     */
    public static long generateId() {
        return DEFAULT_INSTANCE.nextId();
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        CodeGenerator generator = new CodeGenerator(1, 1);

        // 生成10个ID并解析时间
        for (int i = 0; i < 10; i++) {
            long code = generator.nextId();
            String yearMonth = extractYearMonth(code);
            long timestamp = extractTimestamp(code);
            System.out.println("code: " + code + ", yearMonth: " + yearMonth + ", timestamp: " + timestamp);
        }
    }
}