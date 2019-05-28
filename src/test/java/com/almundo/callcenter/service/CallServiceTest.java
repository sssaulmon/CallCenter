package com.almundo.callcenter.service;

import com.almundo.callcenter.model.CallModel;
import com.almundo.callcenter.service.impl.CallServiceImpl;
import com.almundo.callcenter.util.CallUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CallServiceTest {
    private CallServiceImpl callService;

    @org.junit.Before
    public void init() {
        callService = new CallServiceImpl();
    }

    @org.junit.After
    public void clear() {
        CallUtil.clearCalls();
    }

    /**
     * Metodo de prueba que valida que al hacer una llamada se le asigna al operador por estar disponible
     */
    @Test
    public void dispatchOneCall() {
        List<Future> futuresToWatit = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Future future = callService.dispatchCall();
            futuresToWatit.add(future);
        }

        for (Future future : futuresToWatit) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                Thread.currentThread().interrupt();
            }
        }

        List<CallModel> calls = CallUtil.getCalls();
        Assert.assertEquals(calls.size(), 1);

        CallModel callModel = calls.get(0);

        Assert.assertEquals(callModel.getAssignedCall(), CallUtil.OPERATOR_CAPACITY_VALUE);

        System.out.println("Terminaron todas las llamadas " + calls.size());
    }

    /**
     * Metodo de prueba que valida que al execeder el limite de llamadas igual son asiganadas todas
     */
    @Test
    public void dispatchCall() {
        List<Future> futuresToWatit = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Future future = callService.dispatchCall();
            futuresToWatit.add(future);
        }

        for (Future future : futuresToWatit) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                Thread.currentThread().interrupt();
            }
        }

        List<CallModel> calls = CallUtil.getCalls();
        Assert.assertEquals(calls.size(), 12);

        System.out.println("Terminaron todas las llamadas " + calls.size());
    }
}
