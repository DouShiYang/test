package base;

public class TestA {


    public static void main(String[] args) {

        Person person = new Person(10);

        test(person);
        System.out.println(person.toString());


    }

    public static void test(Person p){
        System.out.println(p.toString());

        p.setAge(11);
        System.out.println(p.toString());

    }


}

class Person{

    int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }
}