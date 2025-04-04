package br.unipar.trabalho1B;

import jakarta.xml.ws.Endpoint;

public class pizzariaSoap {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/pizzaria", new pizzariaSoap());

        System.out.println("A pizzaria está aberta e pronta para entregas!");
    }

}
