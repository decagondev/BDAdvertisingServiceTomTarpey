package com.amazon.ata.advertising.service.util;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Futures {

    /**
     * Helper method that behaves exactly like Future.get(), except that it translates
     * all exceptions to RuntimeException, making it usable in streams.
     * Blocks until completion, just like Future.get().
     *
     * @param future The Future to retrieve the value from.
     * @param <T> The generic type of the Future.
     * @return The value of the future, as retrieved by get.
     * @throws RuntimeException When Future.get() would throw an ExecutionException:
     *                          if the process underlying the Future threw an exception.
     * @see Future#get()
     */
    public static <T> T getUnchecked(Future<T> future) {
        T result;
        if (future == null) {
            throw new RuntimeException("Null future?!?");
        }
        try {
            result = future.get();
        } catch (InterruptedException | CancellationException | ExecutionException e) {
            // Wrap in a RuntimeException and rethrow
            throw new RuntimeException("Exception in ExecutorService!", e);
        }
        return result;
    }
}
