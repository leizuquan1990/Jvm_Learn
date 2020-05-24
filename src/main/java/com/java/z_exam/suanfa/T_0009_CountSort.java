package com.java.z_exam.suanfa;

/**
 * @Author: Lei
 * @E-mail: 843291011@qq.com
 * @Date: Created in 9:19 下午 2020/5/18
 * @Version: 1.0
 * @Modified By:
 * @Description:
 */

import java.util.Arrays;

/**
 * 计数排序(最大，取值范围小)
 * 非比较排序
 * 桶思想的一种
 *
 * 计数算法思想：
 *      创建新数组，数组大小就是可取值的范围大小；
 *      如果出现0，就在0那个位置加1，如果再出现0，再在0那个位置再加1
 *
 *      最大但是范围小
 *              某大型企业数万名员工年龄排序
 *              如何快速得知高考名次（腾讯面试）
 * 总结
 *      计数排序是非比较排序
 *      适用于特定问题，也就是对源数据有要求
 *      时间复杂度：N (N+K)
 *      空间复杂度：N+K
 *      稳定性：稳定排序
 */
public class T_0009_CountSort {
    public static void main(String[] args) {
        //int[] arr = {2,4,2,3,7,1,1,0,0,5,6,9,8,5,7,4,0,9};
        int[] arr = {1,2,1,1,1};
        int[] result = countSort(arr);

        System.out.println(Arrays.toString(result));
    }

    static int[] countSort(int[] arr){
        int[] result = new int[arr.length]; // 分配新数组，与原数组等长

        int tongLength = findMax(arr) + 1;
        int[] count = new int[tongLength]; // 原数组元素数值范围个数

        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }

        System.out.println("原数组未排序前:\t" + Arrays.toString(arr));
        System.out.println("累加前，桶现状:\t" + Arrays.toString(count));
        /* 因为不稳定
        for (int i = 0, j=0; i < count.length; i++) {
            while (count[i]-- > 0) result[j++] = i;
        } */

        // 因为上述方式不够稳定，升级为累加数组
        // 累加数组：记录着数据值应在数组中的最后位置
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i-1];
        }

        System.out.println("累加后，桶现状:\t" + Arrays.toString(count));
        System.out.println("----------------------");
        // 然后从原数组中倒序遍历取出，从累加数组中数组中的最后位置减减
        for (int i = arr.length-1; i >= 0; i--) {

            int tongIndex = arr[i]; // 查找数在桶中的位置
            int curTongCnt = count[tongIndex]; // 桶中的元素个数；
            int pos = --count[tongIndex];
            //int pos = --count[arr[i]];
            result[pos] = arr[i];

            System.out.print("出数后，桶现状:\t" + Arrays.toString(count));
            System.out.print("\t\t原数组：" + Arrays.toString(arr) + " 出数：" + arr[i]);
            System.out.println("\tpos:" + pos + "\t结果出数:" + Arrays.toString(result));
        }

        return result;
    }

    private static int findMax(int[] a) {
        int max = 0;
        for (int j = 0; j < a.length; j++) {
            if (a[j] > a[max]) max = j;
        }
        return a[max];
    }

}


/*
1,1,2,0

桶排序
 0, 1, 2
[1, 2, 1] 桶统计
[1, 3, 4] 桶累加

for (int i = arr.length-1; i >= 0; i--) {
    int pos = --count[arr[i]];
    result[pos] = arr[i];
    System.out.println("桶出数:\t" + Arrays.toString(count));
    System.out.println("结果出数:\t" + Arrays.toString(result));
}
桶出数:	[0, 3, 4]
结果出数:	[0, 0, 0, 0]
桶出数:	[0, 3, 3]
结果出数:	[0, 0, 0, 2]
桶出数:	[0, 2, 3]
结果出数:	[0, 0, 1, 2]
桶出数:	[0, 1, 3]
结果出数:	[0, 1, 1, 2]

 */