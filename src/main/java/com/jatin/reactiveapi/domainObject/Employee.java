package com.jatin.reactiveapi.domainObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    private long id;
    private String name;
    private String accountNumber;
    private BigDecimal salary;
}
