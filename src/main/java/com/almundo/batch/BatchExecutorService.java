package com.almundo.batch;

import java.util.concurrent.*;

/**
 * Title: BatchExecutorService.java <br>
 * Description: Clase para creacion generica de procesos Multihilos, se encarga de construir
 * un ThreadPoolExecutor con una capacidad definida y nombre de Threads especificos
 *
 * @author Saul Montoya
 */
public class BatchExecutorService {
    private ThreadPoolExecutor executorService;

    public BatchExecutorService(String threadNamePrefix, int poolSize) {
        ThreadFactory customThreadFactory = new BatchThreadFactoryBuilder()
                .setNamePrefix(threadNamePrefix).setDaemon(true)
                .setPriority(Thread.NORM_PRIORITY).build();

        BatchRejectedExecutionHandler batchRejectedExecutionHandler = new BatchRejectedExecutionHandler();
        executorService = new ThreadPoolExecutor(poolSize,
                poolSize, 0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                customThreadFactory, batchRejectedExecutionHandler);
    }

    /**
     * Metodo para la ejecucion de un proceso runable dentro del executor service
     */
    public Future<?> execute(Runnable command) {
        return executorService.submit(command);
    }
}
