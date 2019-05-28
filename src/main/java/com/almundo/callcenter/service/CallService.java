package com.almundo.callcenter.service;

import java.util.concurrent.Future;

/**
 * Title: CallService.java <br>
 * <p>
 * Description: Clase spring para recibir una llamada
 *
 * @author Saul Montoya
 */
public interface CallService {
    Future dispatchCall();
}
