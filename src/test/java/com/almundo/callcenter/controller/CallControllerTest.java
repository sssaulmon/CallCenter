package com.almundo.callcenter.controller;

import com.almundo.callcenter.controller.CallController;
import com.almundo.callcenter.service.CallService;
import org.junit.Test;

public class CallControllerTest {

    private CallController callController;

    @org.junit.Before
    public void init() {
        CallService callService = new CallService();
        callController = new CallController(callService);
    }

    @Test
    public void dispatchCall() {

        for (int i = 0; i < 100; i++) {
            callController.dispatchCall();
        }

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
