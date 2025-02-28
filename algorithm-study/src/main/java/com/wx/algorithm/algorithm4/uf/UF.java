package com.wx.algorithm.algorithm4.uf;

import java.util.Scanner;

/**
 * @author Shawn
 * @date 2025/2/27 22:05
 * @description
 */
public class UF {

    // 分量id
    private int[] id;

    // 分量数量
    private int count;

    // 初始化n个触点
    public UF(int n) {
        this.count = n;
        id = new int[n];
        // 初始化
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    // 连通分量的数量
    public int count() {
        return count;
    }

    // 如果p,q存在于同一个分量，则返回true
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // 1、quick-find方式
    // p所在的分量的标识符
//    public int find(int p) {
//        return id[p];
//    }
//
//    // 链接两个点
//    public void union(int p, int q) {
//        int pid = find(p);
//        int qid = find(q);
//
//        // 如果两个值相同则已经在同一个分量中了
//        if (pid == qid) {
//            return;
//        }
//
//        // 否则联通两个点
//        for (int i = 0; i < id.length; i++) {
//            if (id[i] == pid) {
//                id[i] = qid;
//            }
//        }
//
//        // 分量数量
//        count--;
//    }

    // 2、quick-union方式
    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    // 此种方法中，index表示当前节点，而 i[index]表示父节点坐标，所以如果 i[index]=index，则表示指向自己
    public void union(int p, int q) {
        // 将p\q的根结点统一
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        id[pRoot] = qRoot;
        count--;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        UF uf = new UF(n);
        while (sc.hasNext()) {
            int p = sc.nextInt();
            int q = sc.nextInt();

            if (uf.connected(p, q)) {
                continue;
            }

            uf.union(p, q);
            System.out.println(p + " " + q);
        }
        System.out.println(uf.count() + " components");
    }
}
