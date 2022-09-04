package list;

import java.util.ArrayList;

public class ArrayListTest {

    public static void change(ArrayList<Integer> list){

        list = new ArrayList<>();
        System.out.println(list.hashCode());
    }

    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        System.out.println(integers.hashCode());
        change(integers);
        System.out.println(integers.hashCode());





    }


}
