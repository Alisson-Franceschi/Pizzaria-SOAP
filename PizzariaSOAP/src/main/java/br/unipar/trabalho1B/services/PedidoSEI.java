package br.unipar.trabalho1B.services;

import br.unipar.trabalho1B.exceptions.PizzariaException;
import br.unipar.trabalho1B.models.Cliente;
import br.unipar.trabalho1B.models.Pedido;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public interface PedidoSEI {

    @WebMethod
    String salvarNovoPedido(@WebParam(name = "valorTotal") Double valorTotal,
                            @WebParam(name = "observacoes") String observacoes,
                            @WebParam(name = "status") String status,
                            @WebParam(name = "cliente")Cliente cliente) throws PizzariaException;

    @WebMethod
    String editarPedido(@WebParam Pedido pedido) throws PizzariaException;

    @WebMethod
    List<Pedido> listarPedidos() throws PizzariaException;

    @WebMethod
    String excluirPedido(@WebParam(name = "id") Integer id) throws PizzariaException;
}
