package algorithm.sort;

import java.util.Arrays;
import java.util.List;

/**
 * 归并排序
 * [1,4,3,9,8,10,7,6] ---> [1,3,4,9],[6,7,8,10] -->[1,3,4,6,7,8,9,10]
 * 1.先做到区域内有序，
 * 2.在左右区域内取两个指针，对两个指针上的元素进行比较，拷贝较小的元素到辅助数组
 * 3.指针往后移动 直到边界内的最后一个元素比较完成
 */
public class MergeSort {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(12, 234, 5, 4, 10, 3, 39, 10);
        mergeSort(integers);
        System.out.println(integers);
    }

    /**
     * @param integers
     */
    private static void mergeSort(List<Integer> integers) {
        if (integers.size() < 2) {
            return;
        }
        process(integers, 0, integers.size() - 1);
    }

    /**
     * 请让当前list的数有序
     *
     * @param start
     * @param end
     */
    public static void process(List<Integer> arr, Integer start, Integer end) {
        if (start == end) {
            return;
        }
        int mid = start + ((end - start) >> 1);
        process(arr, start, mid);
        process(arr, mid + 1, end);
        merge1(arr, start, mid, end);
    }

    /**
     * merger方法
     *
     * @param list
     * @param start
     * @param mid
     * @param end
     */
    static void merge1(List<Integer> list, int start, int mid, int end) {
        //准备辅助数组
        int[] help = new int[end - start + 1];
        int i = 0;
        //左边区域的起始指针
        int p1 = start;
        //右边区域的起始指针
        int p2 = mid + 1;

        while (p1 <= mid && p2 <= end) {
            help[i++] = list.get(p1) > list.get(p2) ? list.get(p2++) : list.get(p1++);
        }
        while (p2 <= end) {
            help[i++] = list.get(p2++);
        }
        while (p1 <= mid) {
            help[i++] = list.get(p1++);
        }

        for (int j = 0; j < i; j++) {
            list.set(start + j, help[j]);
        }

    }

    /**
     * @param arr
     * @param start
     * @param mid
     * @param end
     */
    private static void merge(List<Integer> arr, Integer start, int mid, Integer end) {

        //准备一个辅助数组，用于保存排好序的数据
        int[] help = new int[end - start + 1];
        //定义辅助数据的下标
        int i = 0;
        //准备左边区域的指针
        int p1 = start;
        //准备右边区域的指针
        int p2 = mid + 1;


        //如果两个指针一直在范围内
        while (p1 <= mid && p2 <= end) {
            help[i++] = arr.get(p1) <= arr.get(p2) ? arr.get(p1++) : arr.get(p2++);
        }
        while (p1 <= mid) {
            help[i++] = arr.get(p1++);
        }
        while (p2 <= end) {
            help[i++] = arr.get(p2++);
        }
        for (int a = 0; a < help.length; a++) {
            arr.set(start + a, help[a]);
        }


    }


}
