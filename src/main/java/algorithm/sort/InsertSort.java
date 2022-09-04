package algorithm.sort;

import java.util.Arrays;
import java.util.List;

/**
 * 插入排序
 * 做到当前节点之前的元素是有序的
 * 插入排序的由来：一个新记录插入到已经排好序的有序表
 */
public class InsertSort {


    /**
     * 插入排序
     */
    public static void insertSort(List<Integer> list) {
        if (list.size() < 2) {
            return;
        }

//        for (int i = 1; i < list.size(); i++) {
//            //指针每往后边移动一次，则需要指针之前的元素 进行排序
//            for (int j = i - 1; j >= 0; j--) {
//                //如果当前元素比后一个元素大 则进行交换 将小值 放在前边
//                if (list.get(j) > list.get(j + 1)) {
//                    swap(list, j, j + 1);
//                }
//            }
//        }

        for (int i = 1; i < list.size(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                if(list.get(j)> list.get(j+1)){
                    swap(list,j,j+1);
                }
            }
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
        insertSort(integers);
        System.out.println(integers);
    }
}
