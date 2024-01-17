package com.hui;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author Eirk
 * @Description
 * @Date 2024/1/17 0:45
 */
public class FluxDemo2 {
    public static void main(String[] args) {
//        new FluxDemo2().buffer();
        new FluxDemo2().limit();
    }

    public void buffer() {
        Flux<List<Integer>> flux = Flux.range(1, 10).buffer(3).log();
        flux.subscribe(vo -> System.out.println(vo));
    }
    public void limit (){
        Flux.range(1,100)
                .log()
                .limitRate(30)
                .subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                System.out.println("订阅");
                request(1);
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("钩子"+value);
                request(1);
            }
        });
    }
}
