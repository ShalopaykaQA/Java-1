package ru.kostikov;

import java.lang.ref.WeakReference;

/**
 * Created by Алексей on 25.10.2016.
 */
public class User {

    private String name;
    private Integer age;
    private int id;


    public User(int id, String name, int age) {
        this.name = name;
        this.age = age;
        this.id = id;
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println(String.format("User %s is finalize",this));

    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", age=" + age;
    }

    public static void main(String[] args) {
//        for (int index = 0; index < 10000; index++){
//            System.out.println(new User(index, String.valueOf(index),index));
//        }
        int mb = 1024 * 1024;
        User user = new User(1, "1", 2);

        WeakReference<User> weakUser = new WeakReference<User>(user);

        user = null;

        System.gc();

        Runtime runtime = Runtime.getRuntime();

        System.out.println("##### Heap utilization statistics [MB] #####");
        System.out.println("Used memory: "+ (runtime.totalMemory() - runtime.freeMemory())/mb + " Mbyte");
        System.out.println("Free memory: "+ (runtime.freeMemory())/mb + " Mbyte");
        System.out.println("Total memory: "+ (runtime.totalMemory()/mb) + " Mbyte");
        System.out.println("Max memory: "+ (runtime.maxMemory()/mb) + " Mbyte");

    }
}
