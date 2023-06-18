package com.wx.base.claload;

/**
 * @author wangxiang
 * @date 2023/6/11 10:35
 * @description
 */
public class Study04DoubleParent {
    public static void main(String[] args) throws ClassNotFoundException {
        // 类加载器
        // 应用加载器：AppClassLoader
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        // 系统类加载器: SystemClassLoader
        ClassLoader parent = systemClassLoader.getParent();
        // 根加载器: RootClassLoader
        ClassLoader ancestor = parent.getParent();

        System.out.println("systemClassLoader = " + systemClassLoader);
        System.out.println("parent = " + parent);
        System.out.println("ancestor = " + ancestor);

        // 查看当前类的类加载器
        ClassLoader study04 = Class.forName("com.shawn.base.claload.Study04DoubleParent").getClassLoader();
        // 查看object的类加载器
        ClassLoader objectLoader = Class.forName("java.lang.Object").getClassLoader();

        System.out.println("study04 = " + study04);
        System.out.println("objectLoader = " + objectLoader);

        //
        System.out.println(System.getProperty("java.class.path"));
        /*
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/jre/lib/cat.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/jre/lib/charsets.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/jre/lib/ext/cldrdata.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/jre/lib/ext/crs-agent.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/jre/lib/ext/dnsns.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/jre/lib/ext/jaccess.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/jre/lib/ext/legacy8ujsse.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/jre/lib/ext/localedata.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/jre/lib/ext/nashorn.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/jre/lib/ext/openjsse.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/jre/lib/ext/sunec.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/jre/lib/ext/zipfs.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/jre/lib/jce.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/jre/lib/jfr.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/jre/lib/jsse.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/jre/lib/management-agent.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/jre/lib/resources.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/jre/lib/rt.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/lib/dt.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/lib/jconsole.jar:
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/lib/tools.jar:
        /Users/wangxiang/workspace/self/Java/java-study/target/classes:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar:
        /Users/wangxiang/Library/Caches/JetBrains/IntelliJIdea2023.1/captureAgent/debugger-agent.jar
         */
    }
}
