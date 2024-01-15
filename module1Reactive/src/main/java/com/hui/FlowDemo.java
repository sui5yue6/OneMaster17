package com.hui;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * @author Eirk
 * @Description
 * @Date 2024/1/15 23:57
 */
public class FlowDemo {
    public static void main(String[] args) {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        for (int i = 0; i < 10; i++) {
            publisher.submit("p-" + i);
        }

        Flow.Subscriber<String> subscriber  = new Flow.Subscriber<>() {
            @Override
            public void onSubscribe(Flow.Subscription subscription) {

            }

            @Override
            public void onNext(String item) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        };



    }
}
