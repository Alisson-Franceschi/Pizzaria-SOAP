package br.unipar.trabalho1B.exceptions;

import jakarta.xml.ws.WebFault;

@WebFault (name = "PizzariaException")
public class PizzariaException extends Exception {

    public PizzariaException(String message) {
        super(message);
    }
}
