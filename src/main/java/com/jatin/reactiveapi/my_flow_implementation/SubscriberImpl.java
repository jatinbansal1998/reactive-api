package com.jatin.reactiveapi.my_flow_implementation;

import com.jatin.reactiveapi.domainObject.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

public class SubscriberImpl<Employee> implements Flow.Subscriber<Employee> {
    private Flow.Subscription subscription;

    public List<Employee> consumedEmployeeList = new ArrayList<>();

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Employee item) {
        System.out.println("Got: " + item + " \tFrom thread: " + Thread.currentThread().getId());
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
