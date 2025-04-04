package br.unipar.trabalho1B.services;

import br.unipar.trabalho1B.exceptions.PizzariaException;
import br.unipar.trabalho1B.models.Pizza;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public interface PizzaSEI {

    @WebMethod
    String salvarNovaPizza(@WebParam(name = "sabor") String sabor) throws PizzariaException;

    @WebMethod
    String editarPizza(@WebParam Pizza pizza) throws PizzariaException;

    @WebMethod
    List<Pizza> listarPizzas() throws PizzariaException;

    @WebMethod
    String excluirPizza(@WebParam(name = "id") Integer id) throws PizzariaException;
}
