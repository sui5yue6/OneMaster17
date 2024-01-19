package com.hui;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Eirk
 * @Description
 * @Date 2024/1/17 0:45
 */
public class FluxDemo3 {
    public static void main(String[] args) throws InterruptedException, IOException {
//        new FluxDemo3().create();
//        new FluxDemo3().handle();
        new FluxDemo3().schedule();
        System.in.read();
    }

    public void create() throws InterruptedException {
//        AtomicReference<MyListener> listener = null;
        Flux.create(fluxSink -> {
//            listener.set(new MyListener(fluxSink)   );
            MyListener myListener = new MyListener(fluxSink);
            for (int i = 0; i < 100; i++) {
                myListener.online("zhang" + i);
            }
        }).log().subscribe();
    }

    class MyListener {
        FluxSink<Object> sink;

        public MyListener(FluxSink<Object> sink) {
            this.sink = sink;
        }

        public void online(String s) {
            System.out.println(s + " 登陆");
            sink.next(s);
        }
    }

    // handle自定义流中元素处理规则
    public void handle() {
        Flux.range(1, 10).handle((value, sink) -> {
            System.out.println("拿到的值 " + value);
            String user = getFromCache(value);
            sink.next(user);// 向下发数据的通道
        }).log().subscribe();
    }

    private String getFromCache(Integer value) {
        return value.toString();
    }

    // 自定义线程调度
    public void schedule() {
//        Schedulers.immediate();
        Flux.range(1,1000)
//                .publishOn(Schedulers.immediate())
//                .publishOn(Schedulers.single())
//                .publishOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.fromExecutor(new ThreadPoolExecutor(4,8,60, TimeUnit.SECONDS,new LinkedBlockingDeque<>(1000))))
                .log()
                .parallel()
                .map(integer -> {
//                    try {
//                        Thread.sleep(5);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    return  integer + 10;
                })
//                .subscribeOn(Schedulers.boundedElastic())
//                .log()
                .subscribe();
    }

}
