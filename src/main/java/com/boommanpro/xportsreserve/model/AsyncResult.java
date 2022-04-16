package com.boommanpro.xportsreserve.model;

import java.util.concurrent.Future;
import java.util.function.Supplier;

public class AsyncResult {

    public static <T> Future<T> build(Supplier<T> supplier) {
        return new com.netflix.hystrix.contrib.javanica.command.AsyncResult<T>() {
            @Override
            public T invoke() {
                return supplier.get();
            }
        };
    }
}
