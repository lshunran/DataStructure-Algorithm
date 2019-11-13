/*
 * Author: shunran.li
 * Time: 11/13/19 6:08 PM
 * Email: shunran.li@guanshantech.com
 */

package com.ashun.Heap;

public class Heap {

    private int[] a; //从1开始存储数据，方便计算
    private int n; //堆最大容量
    private int count; //堆目前容量

    public Heap(int capacity){
        this.a = new int[capacity + 1];
        this.n = capacity;
        this.count = 0;
    }

    public void insert(int data){
        if(this.count >= n) return; //堆满
        this.count += 1;
        a[this.count] = data;

        int n = count;

        while (n/2 > 0){
            if(a[n] > a[n/2]){
                this.swap(a, n, n/2);
            }else{
                break;
            }
            n = n/2;
        }
    }

    public void removeMax(){
        if(this.count == 0) return;
        a[1] = a[this.count];
        this.count -= 1;
        this.heapify(a, this.count, 1);
    }

    public void printTree(){
        int level = (int) (Math.log(this.count) / Math.log(2));
        for(int i = 0; i <= level; i++) {
            for (int j = (int) Math.pow(2, i); j < Math.pow(2, i + 1); j++) {
                if (j <= this.count) {
                    System.out.print(this.a[j]);
                } else {
                    break;
                }
            }
            System.out.println();
        }

    }

    private void swap(int[] a, int p1, int p2){
        int temp = a[p1];
        a[p1] = a[p2];
        a[p2] = temp;
    }

    private void heapify(int[] a, int count, int i){
        int pos = i;

        while(true){
            if(i * 2 <= count && a[i] < a[i*2]) pos = 1*2;

            if(i * 2 + 1 <= count && a[pos] < a[i*2+1]) pos = i*2+1;

            if(pos == i) break;

            this.swap(a, pos, i);

            i = pos;

        }
    }

    public static void main(String[] args) {
        Heap maxTopHeap = new Heap(10);

        maxTopHeap.insert(1);
        maxTopHeap.insert(2);
        maxTopHeap.insert(3);
        maxTopHeap.insert(4);
        maxTopHeap.insert(5);

        maxTopHeap.printTree();

    }
}
