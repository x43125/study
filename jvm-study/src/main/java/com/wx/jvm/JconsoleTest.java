package main.java.com.wx.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: x43125
 * @Date: 22/03/27
 */
public class JconsoleTest {
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }
    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<OOMObject>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }
    public static void main(String[] args) throws Exception {
        fillHeap(1000);
    }
}
