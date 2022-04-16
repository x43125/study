package com.wx;

import com.wx.redis.PriceDao;
import com.wx.redis.entity.Price;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/09/05
 */
public class SyncTest {
    String username = "ppdream";
    String password = "123ABCdef*";

    PriceDao priceDao = new PriceDao(username, password);

    /**
     * 单线程消费
     */
    @Test
    public void singleConsumerTest1() {
        for (int i = 0; i < 100; i++) {
            Price price = priceDao.selectByPrimaryKey(1);
            int ron = 10;
            price.setFront(price.getFront().subtract(new BigDecimal(ron)));
            price.setEnd(price.getEnd().add(new BigDecimal(ron)));
            price.setTotal(price.getFront().add(price.getEnd()));
            System.out.print(price.toString());
            priceDao.updateByPrimaryKey(price);
            price.setId(null);
            priceDao.insertSelective(price);
            System.out.println(" |||| " + price.toString());
        }
    }

    @Test
    public void testThreadPrice() {
        try {
            for (int i = 0; i < 10; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Price price = priceDao.selectByPrimaryKey(1);
                        int ron = 10;
                        price.setFront(price.getFront().subtract(new BigDecimal(ron)));
                        price.setEnd(price.getEnd().add(new BigDecimal(ron)));
                        priceDao.updateByPrimaryKey(price);
                        System.out.print(Thread.currentThread() + " == " + price.toString());
                        price.setId(null);
                        priceDao.insertSelective(price);
                        System.out.println(" || " + price.getId());
                    }
                }).start();
            }
        } catch (Exception e) {
        }
    }

    @Test
    public void test() {
        Price price = priceDao.selectByPrimaryKey(1);
        System.out.println(price.toString());
    }

}
