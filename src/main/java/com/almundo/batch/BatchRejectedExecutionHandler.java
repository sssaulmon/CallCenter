package com.almundo.batch;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Title: BatchRejectedExecutionHandler.java <br>
 * Description: CLase para el manejo un de Clases runneable para que al momento
 * que no se pueda ejecutar lo reinserte en la cola de procesamiento
 *
 * @author Saul Montoya
 */
public class BatchRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if (!executor.isShutdown()) {
            try {
                Thread.sleep(300);
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}