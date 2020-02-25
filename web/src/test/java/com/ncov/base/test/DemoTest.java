package com.ncov.base.test;

import lombok.extern.java.Log;
import org.junit.Test;

@Log
public class DemoTest {

    public static void main(String[] args) {
        int f = 0;
        int g = 1;
        for (int i = 0; i <= 15; i++)
        {
            System.out.println(f);
            f = f + g;
            g = f - g;
        }
    }

    /**
     * <pre>
     *      功能描述: 二维数组倒置
     * </pre>
     * @author ChenJunLin
     * @param
     * @return void
     * @date 2020/2/21 9:01
     */
    /*@Test
    public void arrayTest(){
        Integer a[][]={{1,2,3},{11,12,13},{21,22,23}};
        Integer b[][]=new Integer[a.length][a[0].length];
        print(a);
        for (int i=0;i<a.length;i++){
            for (int j =0;j<b.length;j++){
                if (i != j) {
                    int temp = a[i][j];
                    a[i][j] = a[j][i];
                    a[j][i] = temp;
                }
            }
        }
        print(b);
    }*/
    private static void print(Integer arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "、");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * <pre>
     *      功能描述: 接收一个整形参数N，返回不大于log2N的最大整数
     * </pre>
     * @author ChenJunLin
     * @param null
     * @return
     * @date 2020/2/21 9:02
     */
    public static int lg(double N) {
        //log2|N=K  2^k=N
        int k=0;
        if (N>0&&N<1){
            double count=1;
            while (count>=N){
                count*=1/2.0;
                k-=1;
            }
            return k+1;
        }else if (N>=1){
            int count=1;
            while (count<N){
                count*=2;
                k+=1;
            }
            return k-1;
        }else {
            return -1;
        }
    }

}
