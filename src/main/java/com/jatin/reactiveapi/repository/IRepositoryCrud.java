package com.jatin.reactiveapi.repository;

import com.jatin.reactiveapi.domainObject.Reservation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IRepositoryCrud extends ReactiveCrudRepository<Reservation, String> {
}
