package com.almundo.batch;

import java.util.concurrent.Callable;

/**
 * Title: BatchThreadFactoryBuilder.java <br>
 * Description: Clase contenedora de una clase de procesamiento para ejecucion e proceso en hilos
 *
 * @author Saul Montoya
 */
public final class BatchRunnableCommand extends Thread implements Callable {
    private BatchWorker batchWorker;

    public BatchRunnableCommand(BatchWorker batchWorker) {
        this.batchWorker = batchWorker;
    }

    @Override
    public void run() {
        executeTask();
    }

    private void executeTask() {
        this.batchWorker.execute();
    }

    @Override
    public Object call() throws Exception {
        return null;
    }
}