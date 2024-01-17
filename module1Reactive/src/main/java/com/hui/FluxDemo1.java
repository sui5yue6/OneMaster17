package com.hui;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

/**
 * @author Eirk
 * @Description
 * @Date 2024/1/16 22:02
 */
public class FluxDemo1 {
    public static void main(String[] args) {
//        Flux.range(1, 7)
//                .log()
//                .filter(vo -> vo > 3)
//                .map(i -> "haha-" + i).subscribe(vo -> System.out.println(vo));

        Flux<String> flux = Flux.range(1, 7)
                .filter(vo -> vo > 3)
                .map(i -> "haha-" + i);

        flux.subscribe(new BaseSubscriber<String>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                super.hookOnSubscribe(subscription);
            }

            @Override
            protected void hookOnNext(String value) {
                if(value.equals("haha-5")){
                    cancel();
                }
                request(1);
            }

            @Override
            protected void hookOnComplete() {
                super.hookOnComplete();
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                super.hookOnError(throwable);
            }

            @Override
            protected void hookOnCancel() {
                System.out.println("hookOnCancel");
            }

            @Override
            protected void hookFinally(SignalType type) {
                super.hookFinally(type);
            }
        });
    }
}
