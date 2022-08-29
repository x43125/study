package com.wx.algorithm.leetcode.normal;


public class T_793_KZero {

    public int preimageSizeFZF(int k) {
        int ans = 0;
        int left = -1;
        int right;

        while (true) {
            int zeroNoNo = trailingZeroes(ans);
            if (zeroNoNo == k) {
                left = ans;
            }
            if (zeroNoNo > k) {
                right = ans;
                break;
            }
            ans += 5;
        }

        if (left == -1) {
            return 0;
        } else {
            return right - left;
        }
    }

    public int trailingZeroes(int n) {
        int res = 0;
        while (n > 0) {
            n /= 5;
            res += n;
        }

        return res;
    }

    public int preimageSizeFZF_2(int k) {
        long l = k - 1, r = k * 10L + 1;
        while (l + 1 < r) {
            long m = l + r >> 1, t = f(m);
            if (t == k) {
                return 5;
            } else if (t < k) {
                l = m;
            } else {
                r = m;
            }
        }
        return 0;
    }

    long f(long n) {
        if (n == 0) {
            return 0;
        }
        return n / 5 + f(n / 5);
    }

    public static void main(String[] args) {
        int k = 98918711;
        T_793_KZero kZero = new T_793_KZero();
        int res = kZero.preimageSizeFZF(k);
        System.out.println("res = " + res);
//        int res2 = kZero.preimageSizeFZF_2(k);
//        System.out.println("res2 = " + res2);
    }
}