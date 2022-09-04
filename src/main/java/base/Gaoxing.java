package base;

public class Gaoxing {


    public static String times(String str, int times) {

        String result = "";
        for (int i = 0; i <= times; i++) {
            result += str;
        }
        return result;
    }


    public static void main(String[] args) {

        String abc = Gaoxing.times("abc", 3);
        System.out.println(abc);

    }

}
