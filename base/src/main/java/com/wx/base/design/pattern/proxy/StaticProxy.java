package com.wx.base.design.pattern.proxy;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/28
 */
public class StaticProxy {
    /**
     * 接口
     */
    interface SmsService {
        String send(String msg);
    }

    /**
     * 实现类
     */
    static class SmsServiceImpl implements SmsService {

        public String send(String msg) {
            System.out.println("msg = " + msg);
            return msg;
        }
    }


    static class SmsServiceProxyImpl implements SmsService {

        private SmsService smsService;

        public SmsServiceProxyImpl(SmsService smsService) {
            this.smsService = smsService;
        }

        public String send(String msg) {
            System.out.println("before method send()");
            smsService.send(msg);
            System.out.println("after method send()");
            return null;
        }
    }

    public static void main(String[] args) {
        SmsService smsService = new SmsServiceImpl();
        SmsServiceProxyImpl smsProxy = new SmsServiceProxyImpl(smsService);
        smsProxy.send("hello reflect");
    }
}
