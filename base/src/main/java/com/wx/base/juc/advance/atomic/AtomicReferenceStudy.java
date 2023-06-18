package com.wx.base.juc.advance.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/04
 */
public class AtomicReferenceStudy {

    static AtomicReference<User> userAtomicReference = new AtomicReference<>();

    public static void main(String[] args) {
        User user = new User("zhangsan", 15);
        userAtomicReference.set(user);
        User userUpdate = new User("lisi", 20);
        userAtomicReference.compareAndSet(user, userUpdate);
//        System.out.println(user);
//        System.out.println(userUpdate);
        System.out.println(userAtomicReference.get());
    }

    static class User {
        private String name;
        public volatile int age;

        public User() {
        }

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
