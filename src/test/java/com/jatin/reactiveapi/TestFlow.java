package com.jatin.reactiveapi;

import com.jatin.reactiveapi.domainObject.Employee;
import com.jatin.reactiveapi.my_flow_implementation.SubscriberImpl;
import com.jatin.reactiveapi.my_flow_implementation.SubscriberReactorImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Consumer;

public class TestFlow {
    @Test
    public void flowSubmissionPublisher() throws InterruptedException {
        SubmissionPublisher<Employee> publisher = new SubmissionPublisher<>();
        SubscriberImpl subscriber = new SubscriberImpl();
        System.out.println(Thread.currentThread().getId());
        publisher.subscribe(subscriber);
        List<Employee> employees = List.of(
                new Employee(1, "first", "001", new BigDecimal("10000.00")),
                new Employee(2, "second", "002", new BigDecimal("20000.00")),
                new Employee(3, "third", "003", new BigDecimal("30000.00")),
                new Employee(4, "fourth", "004", new BigDecimal("40000.00")),
                new Employee(5, "fifth", "005", new BigDecimal("50000.00")),
                new Employee(6, "sixth", "006", new BigDecimal("60000.00")),
                new Employee(7, "seventh", "007", new BigDecimal("70000.00"))
        );
        employees.forEach(
                employee -> {
                    publisher.submit(employee);
                    //subscriber.onNext(employee);
                    /*try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
        );
        publisher.close();
        var startTime = System.currentTimeMillis();
        int t = 10;
        while (t-- > 0) {
            System.out.println("other string on Thread: " + Thread.currentThread().getId());
            Thread.sleep(1000);
        }
        while (System.currentTimeMillis() - startTime < 50000) {
        }
    }

    @Test
    public void reactorCoreFluxPublisher() throws InterruptedException {
        Thread.currentThread().getId();
        List<Employee> employees = List.of(
                new Employee(1, "first", "001", new BigDecimal("10000.00")),
                new Employee(2, "second", "002", new BigDecimal("20000.00")),
                new Employee(3, "third", "003", new BigDecimal("30000.00")),
                new Employee(4, "fourth", "004", new BigDecimal("40000.00")),
                new Employee(5, "fifth", "005", new BigDecimal("50000.00")),
                new Employee(6, "sixth", "006", new BigDecimal("60000.00")),
                new Employee(7, "seventh", "007", new BigDecimal("70000.00"))
        );
        Flux<Employee> publisher = Flux.fromIterable(employees);
        publisher.delayElements(Duration.ofMillis(1000)).log().subscribe(new Consumer<Employee>() {
            @SneakyThrows
            @Override
            public void accept(Employee employee) {
                //System.out.println("thread going to 💤");
                //Thread.sleep(1000);
                //System.out.println("Got: " + employee + " Thread: " + Thread.currentThread().getId());
            }
        });
        int t = 10;
        while (t-- > 0) {
            System.out.println("other string on Thread: " + Thread.currentThread().getName());
            Thread.sleep(1000);
        }
        var startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 10000) {
        }
    }

    @Test
    public void fluxWithMultipleSubscribers() throws InterruptedException {
        List<Employee> employees = List.of(
                new Employee(1, "first", "001", new BigDecimal("10000.00")),
                new Employee(2, "second", "002", new BigDecimal("20000.00")),
                new Employee(3, "third", "003", new BigDecimal("30000.00")),
                new Employee(4, "fourth", "004", new BigDecimal("40000.00")),
                new Employee(5, "fifth", "005", new BigDecimal("50000.00")),
                new Employee(6, "sixth", "006", new BigDecimal("60000.00")),
                new Employee(7, "seventh", "007", new BigDecimal("70000.00"))
        );
        Flux<Employee> publisher = Flux.fromIterable(employees);
        //publisher.delayElements(Duration.ofMillis(1000));
//        publisher.subscribe(new Consumer<Employee>() {
//            private String subscriberName = new String("Subscriber 1");
//            @SneakyThrows
//            @Override
//            public void accept(Employee employee) {
//                Thread.sleep(1000);
//                System.out.println("Got from " + subscriberName + ": " + employee + " \tFrom thread: " + Thread.currentThread().getId());
//            }
//        });
        publisher.delayElements(Duration.ofMillis(1000)).subscribe(new SubscriberReactorImpl<Employee>());
        int t = 10;
        while (t-- > 0) {
            System.out.println("other string on Thread: " + Thread.currentThread().getId());
            Thread.sleep(1000);
        }
        var startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 10000) {
        }
    }
}
