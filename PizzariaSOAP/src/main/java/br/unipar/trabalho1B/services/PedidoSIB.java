package br.unipar.trabalho1B.services;

import br.unipar.trabalho1B.daos.PedidoDAO;
import br.unipar.trabalho1B.exceptions.PizzariaException;
import br.unipar.trabalho1B.models.Cliente;
import br.unipar.trabalho1B.models.Pedido;
import jakarta.jws.WebService;

import java.util.List;

@WebService(endpointInterface = "br.unipar.trabalho1B.services.PedidoSEI")

public class PedidoSIB implements PedidoSEI {
    @Override
    public String salvarNovoPedido(Double valorTotal, String observacoes, String status, Cliente cliente) throws PizzariaException {
        Pedido pedido = new Pedido();

        PedidoDAO dao = new PedidoDAO();
        dao.salvar(pedido);

        return "Pedido salvo com sucesso!";
    }

    @Override
    public String editarPedido(Pedido pedido) throws PizzariaException {
        PedidoDAO dao = new PedidoDAO();
        Pedido pedidoEditado = dao.buscarPorId(pedido.getId());

        if(pedidoEditado != null){
            pedidoEditado.setValorTotal(pedido.getValorTotal());
            pedidoEditado.setObservacoes(pedido.getObservacoes());
            pedidoEditado.setStatus(pedido.getStatus());
            pedidoEditado.setCliente(pedido.getCliente());

            PedidoDAO daoEditar = new PedidoDAO();
            daoEditar.atualizar(pedidoEditado);
            return "Pedido editado com sucesso!";
        }

        return "Erro ao encontrar o pedido!";
    }

    @Override
    public List<Pedido> listarPedidos() throws PizzariaException {
        PedidoDAO dao = new PedidoDAO();

        return dao.buscarTodos();
    }

    @Override
    public String excluirPedido(Integer id) throws PizzariaException {
        PedidoDAO dao = new PedidoDAO();
        Pedido pedido = dao.buscarPorId(id);

        PedidoDAO daoExcluir = new PedidoDAO();
        if(!daoExcluir.excluir(id)){
            throw new PizzariaException("Erro ao excluir o pedido");
        }

        return "Pedido excluído com sucesso!";
    }
}
