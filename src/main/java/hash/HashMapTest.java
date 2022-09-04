package hash;


public class HashMapTest {

    public static void main(String[] args) {

        ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();

        objectObjectConcurrentHashMap.put("a",28);
        Object a = objectObjectConcurrentHashMap.put("a", 29);
        System.out.println(a);


        objectObjectConcurrentHashMap.put("c",28);
        objectObjectConcurrentHashMap.put("d",28);
        objectObjectConcurrentHashMap.put("aa",28);
        objectObjectConcurrentHashMap.put("aaa",29);
        objectObjectConcurrentHashMap.put("陈雨",28);

//        java.util.HashMap<String, Object> stringObjectHashMap = new java.util.HashMap<>();

//        stringObjectHashMap.put("abc",123);






    }
}
