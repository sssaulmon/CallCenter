package com.almundo.batch;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Title: BatchThreadFactoryBuilder.java <br>
 * Description: Clase para contruccion de un ThreadFactory
 *
 * @author Saul Montoya
 */
class BatchThreadFactoryBuilder {
    private String namePrefix = null;
    private boolean daemon = false;
    private int priority = Thread.NORM_PRIORITY;

    BatchThreadFactoryBuilder setNamePrefix(String namePrefix) {
        if (namePrefix == null) {
            throw new NullPointerException();
        }
        this.namePrefix = namePrefix;
        return this;
    }

    BatchThreadFactoryBuilder setDaemon(boolean daemon) {
        this.daemon = daemon;
        return this;
    }

    BatchThreadFactoryBuilder setPriority(int priority) {
        if (priority < Thread.MIN_PRIORITY) {
            throw new IllegalArgumentException(String.format(
                    "Thread priority (%s) must be >= %s", priority,
                    Thread.MIN_PRIORITY));
        }

        if (priority > Thread.MAX_PRIORITY) {
            throw new IllegalArgumentException(String.format(
                    "Thread priority (%s) must be <= %s", priority,
                    Thread.MAX_PRIORITY));
        }

        this.priority = priority;
        return this;
    }

    ThreadFactory build() {
        return build(this);
    }

    private static ThreadFactory build(BatchThreadFactoryBuilder builder) {
        final String namePrefix = builder.namePrefix;
        final Boolean daemon = builder.daemon;
        final Integer priority = builder.priority;

        final AtomicLong count = new AtomicLong(0);

        return runnable -> {
            Thread thread = new Thread(runnable);
            if (namePrefix != null) {
                thread.setName(namePrefix + "-" + count.getAndIncrement());
            }
            thread.setDaemon(daemon);
            thread.setPriority(priority);
            return thread;
        };
    }
}
