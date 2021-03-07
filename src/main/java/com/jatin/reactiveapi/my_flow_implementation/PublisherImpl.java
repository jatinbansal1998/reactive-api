package com.jatin.reactiveapi.my_flow_implementation;

import com.jatin.reactiveapi.domainObject.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.Flow;

public class PublisherImpl<Employee> implements Flow.Publisher<Employee> {
    @Override
    public void subscribe(Flow.Subscriber<? super Employee> subscriber) {

    }
}
