package com.wx.base.design.pattern;

/**
 * @author Shawn
 * @date 2025/6/4 22:19
 * @description
 */
public class Singleton01 {
    // 非懒加载模式  饿汉式
    static class SingletonNoLazy {
        private static SingletonNoLazy instance = new SingletonNoLazy();
        private SingletonNoLazy() {}

        public static SingletonNoLazy getInstance() {
            return instance;
        }
    }

    static class SingletonLazy {
        // 避免指令重排
        private static volatile SingletonLazy instance;

        private SingletonLazy() {}

        // 双重检测
        public static SingletonLazy getInstance() {
            if (instance == null) {
                synchronized (SingletonLazy.class) {
                    if (instance == null) {
                        instance = new SingletonLazy();
                    }
                }
            }
            return instance;
        }
    }

    static class SingletonStatic {
        private SingletonStatic() {}

        public static SingletonStatic getInstance() {
            return Holder.instance;
        }

        private static class Holder {
            private static SingletonStatic instance = new SingletonStatic();
        }
    }
}

