package com.hui;

/**
 * @author Eirk
 * @Description
 * @Date 2024/1/15 0:56
 */

interface MyInterface {
    int sum(int i, int j);
}

@FunctionalInterface // 检查注解
interface Wahaha {
    int sum();

    default int heihei() {
        return 2;
    }
}

public class Lambda {
    public static void main(String[] args) {
        MyInterface myInterface = new MyInterface() {
            @Override
            public int sum(int i, int j) {
                return i * i + j * j;
            }
        };

        int sum = myInterface.sum(3, 4);
        System.out.println(sum);
        // lamdba表达式   参数-箭头-方法体
        MyInterface myInterface2 = (int i, int j) -> {
            return i * i + j * j;
        };
        // 参数类型可以不写
        MyInterface myInterface3 = (i, j) -> {
            return i * i + j * j;
        };
        // 没有入参也可以
        Wahaha wahaha = () -> {
            return 100;
        };
        // 函数式接口，接口中只有一句为未实现的话
    }
}
