package com.wx.base.juc.advance;

/**
 * @Author: x43125
 * @Date: 22/03/02
 */
public class ThreadLocalStudy05 {

    private static final ThreadLocal<User> USER_CONTEXT = new ThreadLocal<>();
    private static final ThreadLocal<User> USER2_CONTEXT = new ThreadLocal<>();

    public static void main(String[] args) {
        User user = new User();
        user.name = "张三";
        User user2 = new User();
        user2.name = "李四";
        USER_CONTEXT.set(user);
        System.out.println(USER_CONTEXT.get().name);
        new Thread(() -> {
            USER_CONTEXT.set(user2);
            USER2_CONTEXT.set(user);
            System.out.println(USER_CONTEXT.get().name);
            System.out.println(USER2_CONTEXT.get().name);
        }).start();
    }

    static class User {
        public String name;
    }
}

