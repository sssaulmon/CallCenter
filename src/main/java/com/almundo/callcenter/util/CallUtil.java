package com.almundo.callcenter.util;

import com.almundo.callcenter.model.CallModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Title: CallUtil.java <br>
 * <p>
 * Description: Clase utilitaria para la gestión de la llamada
 *
 * @author Saul Montoya
 */
public class CallUtil {
    private static List<CallModel> calls = new ArrayList<>();
    private static AtomicLong count = new AtomicLong(0);
    private static int OPERATOR_CAPACITY = 3;
    private static int SUPERVISOR_CAPACITY = 2;
    private static int DIRECTOR_CAPACITY = 1;

    public static String OPERATOR_CAPACITY_VALUE = "Operador";
    public static String SUPERVISOR_CAPACITY_VALUE = "Supervisor";
    public static String DIRECTOR_CAPACITY_VALUE = "Director";

    private static AtomicInteger operatorCount = new AtomicInteger(0);
    private static AtomicInteger supervisorCount = new AtomicInteger(0);
    private static AtomicInteger directorCount = new AtomicInteger(0);

    /**
     * Metodo sincronizado para la asignación de la llamada al esponsable segun disponibilidad
     *
     * @param duration valor que indica el tiempo que va a durar la llamada
     */
    public synchronized static String assignCall(int duration) {
        while (true) {
            if (operatorCount.get() < OPERATOR_CAPACITY) {
                operatorCount.incrementAndGet();
                saveCall(OPERATOR_CAPACITY_VALUE, duration);
                return OPERATOR_CAPACITY_VALUE;
            } else if (supervisorCount.get() < SUPERVISOR_CAPACITY) {
                supervisorCount.incrementAndGet();
                saveCall(SUPERVISOR_CAPACITY_VALUE, duration);
                return SUPERVISOR_CAPACITY_VALUE;
            } else if (directorCount.get() < DIRECTOR_CAPACITY) {
                directorCount.incrementAndGet();
                saveCall(DIRECTOR_CAPACITY_VALUE, duration);
                return DIRECTOR_CAPACITY_VALUE;
            }
        }
    }

    /**
     * Metodo para la liberación del responsable al terminar una llamada
     *
     * @param value representa el encargado que concluyo la llamada
     */
    public static void releaseCall(String value) throws Exception {
        if (value.equalsIgnoreCase(OPERATOR_CAPACITY_VALUE)) {
            operatorCount.decrementAndGet();
        } else if (value.equalsIgnoreCase(SUPERVISOR_CAPACITY_VALUE)) {
            supervisorCount.decrementAndGet();
        } else if (value.equalsIgnoreCase(DIRECTOR_CAPACITY_VALUE)) {
            directorCount.decrementAndGet();
        } else {
            throw new Exception("Invalid Value");
        }

        System.out.println("Se libero una llamada hacia el " + value);
    }

    /**
     * Metodo para registrar la llamada y quien la atendio
     *
     * @param assigned representa el encargado al que se le asigno la llamada
     * @param duration valor que indica el tiempo que va a durar la llamada
     */
    private static void saveCall(String assigned, int duration) {
        CallModel callModel = new CallModel();
        callModel.setId(count.incrementAndGet());
        callModel.setAssignedCall(assigned);
        callModel.setDuration(duration);
        callModel.setDuration(duration);
        calls.add(callModel);
    }

    /**
     * Metodo para registrar la llamada y quien la atendio
     */
    public static void clearCalls() {
        calls.clear();
    }

    /**
     * Metodo get que retorna las llamadas
     */
    public static List<CallModel> getCalls() {
        return calls;
    }
}
