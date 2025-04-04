package br.unipar.trabalho1B.services;

import br.unipar.trabalho1B.daos.BordaDAO;
import br.unipar.trabalho1B.exceptions.PizzariaException;
import br.unipar.trabalho1B.models.Borda;
import jakarta.jws.WebService;

import java.util.List;

@WebService(endpointInterface = "br.unipar.trabalho1B.services.BordaSEI")

public class BordaSIB implements BordaSEI {

    @Override
    public String salvarNovaBorda(String sabor) throws PizzariaException {
        Borda borda = new Borda(sabor);

        BordaDAO dao = new BordaDAO();
        dao.salvar(borda);

        return "Borda salva com sucesso!";
    }

    @Override
    public String editarBorda(Borda borda) throws PizzariaException {
        BordaDAO dao = new BordaDAO();
        Borda bordaEditada = dao.buscarPorId(borda.getId());

        if(bordaEditada != null) {
            bordaEditada.setSabor(borda.getSabor());

            BordaDAO daoEditar = new BordaDAO();
            daoEditar.atualizar(bordaEditada);
            return "Borda editada com sucesso!";
        }

        return "Erro ao encontrar a borda!";
    }

    @Override
    public List<Borda> listarBordas() throws PizzariaException {
        BordaDAO dao = new BordaDAO();

        return dao.buscarTodos();
    }

    @Override
    public String excluirBordas(Integer id) throws PizzariaException {
        BordaDAO dao = new BordaDAO();
        Borda borda = dao.buscarPorId(id);

        BordaDAO daoExcluir = new BordaDAO();
        if(!daoExcluir.excluir(id)){
            throw new PizzariaException("Erro ao excluir a borda");
        }

        return "Borda excluída com sucesso!";
    }
}
