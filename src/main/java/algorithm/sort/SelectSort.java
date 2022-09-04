package algorithm.sort;

import java.util.Arrays;
import java.util.List;

/**
 * 排序算法
 * 核心：找出当前位置之后的最小值 放在当前位置
 */
public class SelectSort {


    /**
     * 排序算法
     */
    public static void selectSort(List<Integer> list) {
        if (list.size() < 2) {
            return;
        }

        for (int i = 0; i < list.size()-1; i++) {
            int mindex = i;
            for (int j = i+1; j < list.size(); j++) {
                mindex = list.get(j) < list.get(mindex) ? j : mindex;
            }
            swap(list, mindex, i);
        }
    }

    /**
     * 交换元素
     *
     * @param a    位置a的元素
     * @param b    位置b的元素
     * @param list 要交换的集合
     */
    public static void swap(List<Integer> list, int a, int b) {
        //取出位置a的元素
        int temp = list.get(a);

        list.set(a, list.get(b));

        list.set(b, temp);
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(12, 234, 5, 4, 10, 3, 39, 10);
        selectSort(integers);
        System.out.println(integers);
    }
}
