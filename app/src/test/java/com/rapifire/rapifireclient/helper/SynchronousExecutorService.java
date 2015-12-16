package com.rapifire.rapifireclient.helper;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by ktomek on 15.11.15.
 */
public class SynchronousExecutorService implements ExecutorService {

    private boolean mShutdown;

    @Override
    public void shutdown() {
        mShutdown = true;
    }

    @Override
    public List<Runnable> shutdownNow() {
        mShutdown = true;
        return Collections.emptyList();
    }

    @Override
    public boolean isShutdown() {
        mShutdown = true;
        return mShutdown;
    }

    @Override
    public boolean isTerminated() {
        return mShutdown;
    }

    @Override
    public boolean awaitTermination(final long timeout, final TimeUnit unit) {
        return true;
    }

    @NonNull
    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return null;
    }

    @NonNull
    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return null;
    }

    @NonNull
    @Override
    public Future<?> submit(Runnable task) {
        return null;
    }

    @NonNull
    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
            throws InterruptedException {
        return null;
    }

    @NonNull
    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout,
                                         TimeUnit unit) throws InterruptedException {
        return null;
    }

    @NonNull
    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException,
            ExecutionException {
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public void execute(final Runnable command) {
        command.run();
    }
}
