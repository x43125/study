package com.zf.util;

import org.springframework.stereotype.Component;

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
 * - 5位数据中心ID（基于IP地址自动生成）
 * - 5位机器ID（基于IP地址自动生成）
 * - 12位序列号（同一毫秒内的自增序号）
 * <p>
 * 使用场景：生成订单code，从code中提取时间戳用于分表路由
 * <p>
 * 机器ID生成方案：基于IP地址自动生成
 * - 适用场景：学习环境、测试环境、无状态应用
 * - 优点：无需外部依赖，自动根据IP生成
 * - 缺点：IP变化会导致机器ID变化，可能会冲突
 */
@Component
public class CodeGenerator {

    /**
     * 起始时间戳（2024-01-01 00:00:00）
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
     * 数据中心ID（基于IP地址倒数第二段 % 32）
     */
    private final long datacenterId;

    /**
     * 机器ID（基于IP地址最后一段 % 32）
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
     * 日期格式化器
     */
    private static final DateTimeFormatter YEAR_MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM");

    /**
     * 默认实例
     * 用于简单场景
     */
    private static CodeGenerator DEFAULT_INSTANCE;

    /**
     * 默认构造函数
     * 基于IP地址自动生成机器ID
     */
    public CodeGenerator() {
        WorkerInfo workerInfo = generateWorkerInfo();
        this.datacenterId = workerInfo.getDatacenterId();
        this.workerId = workerInfo.getWorkerId();

        System.out.println("==============================================");
        System.out.println("雪花算法初始化成功");
        System.out.println("数据中心ID: " + this.datacenterId);
        System.out.println("机器ID: " + this.workerId);
        System.out.println("==============================================");
    }

    /**
     * 构造函数
     *
     * @param datacenterId 数据中心ID（0-31）
     * @param workerId     机器ID（0-31）
     * @deprecated 建议使用无参构造函数，自动基于IP生成机器ID
     */
    @Deprecated
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
     * 基于IP地址生成机器ID
     * <p>
     * 算法说明：
     * 1. 获取本机IP地址（如：192.168.1.100）
     * 2. 提取最后两段：1.100
     * 3. datacenterId = 倒数第二段 % 32（确保在0-31之间）
     * 4. workerId = 最后一段 % 32（确保在0-31之间）
     * <p>
     * 示例：
     * - IP: 192.168.1.100 → datacenterId=1, workerId=4
     * - IP: 192.168.2.200 → datacenterId=2, workerId=8
     *
     * @return 机器信息
     */
    private WorkerInfo generateWorkerInfo() {
        try {
            String ip = java.net.InetAddress.getLocalHost().getHostAddress();
            System.out.println("检测到本机IP地址: " + ip);

            String[] parts = ip.split("\\.");

            // IP地址必须有4段
            if (parts.length != 4) {
                throw new RuntimeException("IP地址格式不正确: " + ip);
            }

            // datacenterId: 倒数第二段
            long datacenterId = Long.parseLong(parts[2]) % 32;

            // workerId: 最后一段
            long workerId = Long.parseLong(parts[3]) % 32;

            return new WorkerInfo(datacenterId, workerId);
        } catch (Exception e) {
            System.err.println("基于IP地址生成机器ID失败，使用默认值");
            e.printStackTrace();
            // 异常时使用默认值
            return new WorkerInfo(1L, 1L);
        }
    }

    /**
     * 机器信息内部类
     */
    private static class WorkerInfo {
        private final long datacenterId;
        private final long workerId;

        public WorkerInfo(long datacenterId, long workerId) {
            this.datacenterId = datacenterId;
            this.workerId = workerId;
        }

        public long getDatacenterId() {
            return datacenterId;
        }

        public long getWorkerId() {
            return workerId;
        }
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
            throw new RuntimeException("Clock moved backwards. Refusing to generate id for "
                    + (lastTimestamp - timestamp) + " milliseconds");
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
     * 使用默认实例生成ID
     *
     * @return 雪花算法ID
     */
    public static long generateId() {
        if (DEFAULT_INSTANCE == null) {
            DEFAULT_INSTANCE = new CodeGenerator();
        }
        return DEFAULT_INSTANCE.nextId();
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        // 测试无参构造函数（自动基于IP生成机器ID）
        System.out.println("\n=== 测试无参构造函数 ===");
        CodeGenerator generator1 = new CodeGenerator();

        // 生成10个ID并解析时间
        System.out.println("\n生成10个雪花算法ID:");
        for (int i = 0; i < 10; i++) {
            long code = generator1.nextId();
            String yearMonth = extractYearMonth(code);
            long timestamp = extractTimestamp(code);
            System.out.println("code: " + code + ", yearMonth: " + yearMonth + ", timestamp: " + timestamp);
        }

        // 测试有参构造函数（已废弃，向后兼容）
        System.out.println("\n=== 测试有参构造函数（已废弃）===");
        CodeGenerator generator2 = new CodeGenerator(1L, 1L);
        for (int i = 0; i < 5; i++) {
            long code = generator2.nextId();
            System.out.println("code: " + code);
        }

        // 测试静态方法
        System.out.println("\n=== 测试静态方法 generateId() ===");
        for (int i = 0; i < 3; i++) {
            long code = generateId();
            System.out.println("code: " + code + ", yearMonth: " + extractYearMonth(code));
        }
    }
}