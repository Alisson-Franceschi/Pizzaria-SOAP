package br.unipar.trabalho1B.services;

import br.unipar.trabalho1B.exceptions.PizzariaException;
import br.unipar.trabalho1B.models.Borda;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public interface BordaSEI {

    @WebMethod
    String salvarNovaBorda(@WebParam(name = "sabor") String sabor) throws PizzariaException;

    @WebMethod
    String editarBorda(@WebParam Borda borda) throws PizzariaException;

    @WebMethod
    List<Borda> listarBordas() throws PizzariaException;

    @WebMethod
    String excluirBordas(@WebParam(name = "id") Integer id) throws PizzariaException;
}
