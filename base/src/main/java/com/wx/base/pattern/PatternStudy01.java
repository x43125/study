package com.wx.base.pattern;

/**
 * @author wangxiang
 * @date 2023/7/7 16:55
 * @description
 */
public class PatternStudy01 {
    public static void main(String[] args) {
//        PatternInterface<String> patternInterface = new PatternClass<>("zhangsan");
//        System.out.println("patternInterface.getType() = " + patternInterface.getType());
        PatternInterface<Integer> patternInterface2 = new PatternClass<>(123);
        PatternInterface<Long> patternInterface3 = new PatternClass<>(123L);
        PatternInterface<Byte> patternInterface4 = new PatternClass<>(new Byte("-127"));
        System.out.println("patternInterface2.getType() = " + patternInterface2.getType());
        System.out.println("patternInterface3.getType() = " + patternInterface3.getType());
        System.out.println("patternInterface4.getType() = " + patternInterface4.getType());
    }
}

interface PatternInterface<T extends Number> {

    <T> T getType();
}

/**
 * 只允许传入数值型
 * @param <T>
 */
class PatternClass<T extends Number> implements PatternInterface<T> {

    private T type;

    public PatternClass(T type) {
        this.type = type;
    }

    public void setType(T type) {
        this.type = type;
    }

    @Override
    public <T> T getType() {
        return (T) this.type;
    }
}

class PatterClass2<T> {

    public void print() {
        System.out.println("123");
    }
    public void func(PatterClass2<? super String> cla) {
        cla.print();
    }


    public static void main(String[] args) {
        PatterClass2<String> patterClass2 = new PatterClass2<>();
        patterClass2.func(patterClass2);
    }
}