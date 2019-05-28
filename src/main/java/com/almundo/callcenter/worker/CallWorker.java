package com.almundo.callcenter.worker;

import com.almundo.batch.BatchWorker;
import com.almundo.callcenter.util.CallUtil;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Title: CallWorker.java <br>
 * <p>
 * Description: Clase encargada de ejecutar el procesamiento de la llamada
 *
 * @author Saul Montoya
 */
public class CallWorker implements BatchWorker {

    /**
     * El metodo genera un numero aleatorio entre 5 y 10 que define el tiempo de duracion de la llamada
     * en segundos y invoca un utlitario de asignacion de llamada al concluir el tiempo libera la llamada
     */
    @Override
    public void execute() {
        try {
            int randomNum = ThreadLocalRandom.current().nextInt(5, 11);
            String callAssigned = CallUtil.assignCall(randomNum);

            Thread.sleep(randomNum * 1000);

            CallUtil.releaseCall(callAssigned);
            System.out.println("La llamada duro " + randomNum + " segundos");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.out.println("Hubo fallos al colgar la llamada");
        }
    }
}
