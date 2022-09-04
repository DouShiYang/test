package algorithm.sort;


import java.util.Arrays;
import java.util.List;

/**
 * 冒泡排序
 * 找出较大的元素 然后往右排
 */
public class BubblingSort {


    public static void bubblingSort(List<Integer> list) {

        //当元素个数小于2个时  则不用排序
        if (list.size() < 2) {
            return;
        }

        //之所以需要从list.size()-1 开始是因为内循环 有可能会越界
        for (int i = list.size() - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if(list.get(j) > list.get(j+1)){
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
        bubblingSort(integers);
        System.out.println(integers);
    }

}
