package com.company.tobeace;

import java.util.Scanner;
/**
 * 整除 /
 * 取余 %
 * 队列 Queue<BinaryTree> queue = new LinkedList<BinaryTree>();
 * 数组 List<Character>res=new ArrayList<>();
 * */
public class SaiMaWangDemo {
    public static boolean testNum(int num){
        return num==Math.pow(num/100,3)+Math.pow(num/10%10,3)+Math.pow(num%10,3);
    }
    public static void main(String args[])
    {
        Scanner cin = new Scanner(System.in);
        int N, M;
        // 读取输入，直到没有整型数据可读
        System.out.println(123%100);
        while(cin.hasNextInt())
        {
            N = cin.nextInt();
            M = cin.nextInt();
            // 读取接下来M行
            int num=0;
            for (int i=N; i<=M; i++) {
                if (testNum(i)){
                    if(num==0) System.out.print(i);
                    else System.out.print(" "+i);
                    num++;
                }
            }
            if (num==0)   System.out.println("no");
        }
    }
}
