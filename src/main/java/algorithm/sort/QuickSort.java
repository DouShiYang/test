package algorithm.sort;

import java.util.Arrays;
import java.util.List;

/**
 * 快速排序-单边循环法【最简单的快排了】
 * 快速排序的特征是：分而治之
 * 每次取一个基准元素
 * 将小于基准元素的全部移动到左边
 * 将小于基准元素的全部移动到右边
 * end：直到取不出基准元素
 */
public class QuickSort {


    /**
     * 快速排序
     */
    public static void quickSort(List<Integer> list) {
        if (list.size() < 2) {
            return;
        }
        process(list, 0, list.size() - 1);
    }


    /**
     * 分区排序
     *
     * @param list
     * @param start
     * @param end
     */
    private static void process(List<Integer> list, int start, int end) {
        if (start >= end) {
            return;
        }
        //获取比较基准位
        int m = partition(list, start, end);

        process(list, start, m - 1);
        process(list, m + 1, end);
    }


    /**
     * 获取基准元素的方法，并做到两个区域内的元素有序
     *
     * @param list
     * @return
     */
    private static int partition(List<Integer> list, int start, int end) {
        if (start == end) {
            return start;
        }
        //左边区域的起始位置
        int mark = start;
        //将第一个位置的元素 作为基准的比较元素
        Integer pivot = list.get(start);
        //从左边第二个元素开始 判断
        for (int i = start + 1; i <= end; i++) {
            //如果当前元素 比 基准元素 小 则需要往前移动【交换基准元素与当前位置的元素】，标记位往后移动
            if (list.get(i) < pivot) {
                mark++;
                swap(list, mark, i);
            }
        }
        //循环完成后 新的标记位与最开始的最左边元素交换位置
        swap(list, mark, start);
        return mark;
    }

    /**
     * 交换元素
     *
     * @param a    位置a的元素
     * @param b    位置b的元素
     * @param list 要交换的集合
     */
    public static void swap(List<Integer> list, int a, int b) {
        int temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }


    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(12, 234, 5, 4, -1, 10, 3, 39, 10);

        quickSort(integers);
        System.out.println(integers);

    }
}
