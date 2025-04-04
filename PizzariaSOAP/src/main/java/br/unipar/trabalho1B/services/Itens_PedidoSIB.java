package br.unipar.trabalho1B.services;

import br.unipar.trabalho1B.daos.Itens_PedidoDAO;
import br.unipar.trabalho1B.exceptions.PizzariaException;
import br.unipar.trabalho1B.models.Borda;
import br.unipar.trabalho1B.models.Itens_Pedido;
import br.unipar.trabalho1B.models.Pedido;
import br.unipar.trabalho1B.models.Pizza;
import jakarta.jws.WebService;

import java.util.List;

@WebService(endpointInterface = "br.unipar.trabalho1B.services.Itens_PedidoSEI")

public class Itens_PedidoSIB implements Itens_PedidoSEI {
    @Override
    public String salvarNovosItens(String tamanho, int quantidade, Double valor_unitario, Double valor_total, Pedido pedido, Pizza pizza, Borda borda) throws PizzariaException {
        Itens_Pedido itens = new Itens_Pedido();

        Itens_PedidoDAO dao = new Itens_PedidoDAO();
        dao.salvar(itens);

        return "Itens do pedido foram salvos com sucesso!";
    }

    @Override
    public String editarItens(Itens_Pedido itens) throws PizzariaException {
        Itens_PedidoDAO dao = new Itens_PedidoDAO();
        Itens_Pedido itensEditados = dao.buscarPorId(itens.getId());

        if(itensEditados == null){
            itensEditados.setTamanho(itens.getTamanho());
            itensEditados.setQuantidade(itens.getQuantidade());
            itensEditados.setValor_unitario(itens.getValor_unitario());
            itensEditados.setValor_total(itens.getValor_total());
            itensEditados.setPedido(itens.getPedido());
            itensEditados.setPizza(itens.getPizza());
            itensEditados.setBorda(itens.getBorda());

            Itens_PedidoDAO daoEditar = new Itens_PedidoDAO();
            daoEditar.atualizar(itensEditados);
            return "Itens do pedido foram editados com sucesso!";
        }

        return "Erro ao editar itens do pedido!";
    }

    @Override
    public List<Itens_Pedido> listarItens() throws PizzariaException {
        Itens_PedidoDAO dao = new Itens_PedidoDAO();

        return dao.buscarTodos();
    }

    @Override
    public String excluirItens(Integer id) throws PizzariaException {
        Itens_PedidoDAO dao = new Itens_PedidoDAO();
        Itens_Pedido itens = new Itens_Pedido();

        Itens_PedidoDAO daoExcluir = new Itens_PedidoDAO();
        if(!daoExcluir.excluir(id)){
            throw new PizzariaException("Erro ao excluir os itens do pedido!");
        }

        return "Itens do pedido foram excluidos com sucesso!";
    }
}
