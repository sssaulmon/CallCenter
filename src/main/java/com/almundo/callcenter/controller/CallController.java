package com.almundo.callcenter.controller;

import com.almundo.callcenter.service.impl.CallServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: CallController.java <br>
 * <p>
 * Description: Clase para exponer un servicio rest para recibir la llamada
 *
 * @author Saul Montoya
 */
@RestController
public class CallController {
    private final CallServiceImpl callService;

    @Autowired
    public CallController(CallServiceImpl callService) {
        this.callService = callService;
    }

    @GetMapping("/dispatchCall")
    @ResponseBody
    public String dispatchCall() {
        callService.dispatchCall();
        return "Se ejecuto Satisfactoriamente";
    }
}
