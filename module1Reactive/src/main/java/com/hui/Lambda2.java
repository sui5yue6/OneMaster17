package com.hui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author Eirk
 * @Description
 * @Date 2024/1/15 0:56
 */

public class Lambda2 {
    public static void main(String[] args) {
        var list = new ArrayList<String>();
        list.add("a");
        list.add("a1");
        list.add("a2");
        list.add("a3");
        list.add("a4");
        list.add("a5");
        list.add("a6");
        Collections.sort(list, (v1, v2) -> {
            return v1.compareTo(v2);
        });
        Collections.sort(list, String::compareTo);
        System.out.println(list);
        new Thread(() -> {
            System.out.println("hello");
        }).start();

        // 有入参，有出参
        Function<String, Integer> function = (x) -> Integer.parseInt(x);
        Integer apply = function.apply("2");
        System.out.println(apply);

        // 有入参，无出参
        // bi表示两个
        BiConsumer<String, String> function2 = (a, b) -> {
            System.out.println("haha " + a + " " + b);
        };
        function2.accept("a", "b");

        Supplier<String> supplier = () -> UUID.randomUUID().toString();
        System.out.println(supplier.get());

        Predicate<Integer> even = (t) -> t % 2 == 0;
        // 反向判断
        System.out.println(even.negate().test(2));
    }
}
