package com.jatin.reactiveapi.my_flow_implementation;

import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;

import java.util.ArrayList;
import java.util.List;

public class SubscriberReactorImpl<Employee> implements CoreSubscriber<Employee> {
    private Subscription subscription;

    private String subscriberName = new String("Subscriber 2");

    public List<Employee> consumedEmployeeList = new ArrayList<>();

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Employee item) {
        System.out.println("Got from " + subscriberName + ": " + item + " \tFrom thread: " + Thread.currentThread().getId());
        subscription.request(1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("DONE");
    }
}
