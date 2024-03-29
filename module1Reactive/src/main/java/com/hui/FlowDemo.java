package com.hui;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * @author Eirk
 * @Description
 * @Date 2024/1/15 23:57
 */
public class FlowDemo {
    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();


        Flow.Subscriber<String> subscriber = new Flow.Subscriber<>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println(Thread.currentThread() + "订阅开始了  " + subscription);
                this.subscription = subscription;
                this.subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                System.out.println(Thread.currentThread() + "订阅者接受到数据" + item);
//                this.subscription.request(Long.MAX_VALUE);
                this.subscription.request(1);
                if (item.equals("p-7")) {
                    subscription.cancel();// 取消订阅
                }
            }

            @Override
            public void onError(Throwable throwable) { // 在错误发生时
                System.out.println(Thread.currentThread() + "订阅者接收到错误" + throwable);
            }

            @Override
            public void onComplete() {
                System.out.println(Thread.currentThread() + "订阅者完成");

            }
        };

        publisher.subscribe(subscriber);
        for (int i = 0; i < 10; i++) {
            publisher.submit("p-" + i);
        }
//         publisher.closeExceptionally(new RuntimeException("数字大于9 不想干了"));

        publisher.close();
        Thread.sleep(20000);
    }
}
