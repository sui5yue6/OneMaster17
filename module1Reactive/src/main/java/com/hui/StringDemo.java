package com.hui;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Eirk
 * @Description
 * @Date 2024/1/15 18:20
 */
public class StringDemo {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1, 2, 3);
        Stream<Integer> concat = Stream.concat(Stream.of(2, 3, 4), stream);
        Stream<Object> build = Stream.builder().add("11").add("22").build();

        // 直接从集合容器中获取流
        List<Integer> integers = List.of(1, 2);
        integers.stream();

        Set<Integer> integers1 = Set.of(1, 2);
        integers1.stream();

        Map<Object, Object> of = Map.of();
        of.keySet().stream();

        // 封装成线程安全的.这里所有的操作都加锁了，非常的蠢
        List<Object> objects = Collections.synchronizedList(new ArrayList<>());

        long count = Stream.of(1, 2, 3, 4, 5)
                .parallel()  // 并发之后需要自行解决多线程安全问题
                .filter(i -> {
                    System.out.println(Thread.currentThread());
                    System.out.println();
                    objects.add(i);
                    return i > 2;
                }).count();
        // 流的所有操作都是无状态数据，数据状态仅在此函数有效吗，不溢出函数外
        System.out.println(count);


//        List<String> list = List.of("li si", "zhang san", "wang wu");
        List<Person> list = List.of(
                new Person("zhang san1", "nan", 16),
                new Person("zhang san2", "nan", 17),
                new Person("zhang san3", "nv", 18)
        );
        list.stream().filter(person -> person.age > 16)
                .map(vo -> vo.getName())
                .flatMap(vo -> {
                    String[] s = vo.split(" ");
                    return Arrays.stream(s);
                })
                .forEach(person -> System.out.println(person));
        // 流里面的元素都完整走一个流水线才能轮到下一个元素

        List<Person> collect = list.stream().takeWhile(vo -> vo.getAge() <18).collect(Collectors.toList());
        System.out.println(collect);

        // 这里的分组这么使用么。。。
        Map<String, List<Person>> collect1 = list.stream().collect(Collectors.groupingBy(t -> t.gender));
        System.out.println(collect1);


    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class Person {
        private String name;
        private String gender;
        private Integer age;
    }
}
