package com.wx.algorithm.algorithm4.uf;

import java.util.Scanner;

/**
 * @author Shawn
 * @date 2025/2/27 22:05
 * @description
 */
public class WeightedQuickUnionUF {

    // 分量的大小
    private int[] sz;

    // 分量id
    private int[] id;

    // 分量数量
    private int count;

    // 初始化n个触点
    public WeightedQuickUnionUF(int n) {
        this.count = n;
        id = new int[n];
        sz = new int[n];
        // 初始化
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 1;
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

    // 3、weighted-quick-union方式
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
        if (sz[pRoot] < sz[qRoot]) {
            // 将权重小的连接到大的
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        count--;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
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
