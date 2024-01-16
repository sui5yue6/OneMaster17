package com.hui;

import reactor.core.publisher.Flux;

/**
 * @author Eirk
 * @Description
 * @Date 2024/1/16 22:02
 */
public class FluxDemo3 {
    public static void main(String[] args) {
        Flux.range(1,7)
                .log()
                .filter(vo->vo>3)
                .map(i->"haha-"+i).subscribe(vo-> System.out.println(vo      ));
    }
}
