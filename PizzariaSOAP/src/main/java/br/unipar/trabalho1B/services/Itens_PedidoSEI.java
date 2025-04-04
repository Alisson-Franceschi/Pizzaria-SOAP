package br.unipar.trabalho1B.services;

import br.unipar.trabalho1B.exceptions.PizzariaException;
import br.unipar.trabalho1B.models.Borda;
import br.unipar.trabalho1B.models.Itens_Pedido;
import br.unipar.trabalho1B.models.Pedido;
import br.unipar.trabalho1B.models.Pizza;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public interface Itens_PedidoSEI {

    @WebMethod
    String salvarNovosItens(@WebParam(name = "tamanho") String tamanho,
                            @WebParam(name = "quantidade") int quantidade,
                            @WebParam(name = "valor_unitario") Double valor_unitario,
                            @WebParam(name = "valor_total") Double valor_total,
                            @WebParam(name = "pedido") Pedido pedido,
                            @WebParam(name = "pizza") Pizza pizza,
                            @WebParam(name = "borda") Borda borda) throws PizzariaException;

    @WebMethod
    String editarItens(@WebParam Itens_Pedido itens) throws PizzariaException;

    @WebMethod
    List<Itens_Pedido> listarItens() throws PizzariaException;

    @WebMethod
    String excluirItens(@WebParam(name = "id") Integer id) throws PizzariaException;
}
