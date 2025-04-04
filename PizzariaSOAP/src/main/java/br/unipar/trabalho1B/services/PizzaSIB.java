package br.unipar.trabalho1B.services;

import br.unipar.trabalho1B.daos.PizzaDAO;
import br.unipar.trabalho1B.exceptions.PizzariaException;
import br.unipar.trabalho1B.models.Pizza;
import jakarta.jws.WebService;

import java.util.List;

@WebService(endpointInterface = "br.unipar.trabalho1B.services.PizzaSEI")

public class PizzaSIB implements PizzaSEI {
    @Override
    public String salvarNovaPizza(String sabor) throws PizzariaException {
        Pizza pizza = new Pizza(sabor);

        PizzaDAO dao = new PizzaDAO();
        dao.salvar(pizza);

        return "Pizza salva com sucesso!";
    }

    @Override
    public String editarPizza(Pizza pizza) throws PizzariaException {
        PizzaDAO dao = new PizzaDAO();
        Pizza pizzaEditada = dao.buscarPorId(pizza.getId());

        if(pizzaEditada != null) {
            pizzaEditada.setSabor(pizza.getSabor());

            PizzaDAO daoEditar = new PizzaDAO();
            daoEditar.atualizar(pizzaEditada);
            return "Pizza editada com sucesso!";
        }

        return "Erro ao encontrar a pizza!";
    }

    @Override
    public List<Pizza> listarPizzas() throws PizzariaException {
        PizzaDAO dao = new PizzaDAO();

        return dao.buscarTodos();
    }

    @Override
    public String excluirPizza(Integer id) throws PizzariaException {
        PizzaDAO dao = new PizzaDAO();
        Pizza pizza = dao.buscarPorId(id);

        PizzaDAO daoExcluir = new PizzaDAO();
        if(!daoExcluir.excluir(id)){
            throw new PizzariaException("Erro ao excluir a pizza");
        }

        return "Pizza excluída com sucesso!";
    }
}
