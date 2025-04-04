package br.unipar.trabalho1B.services;

import br.unipar.trabalho1B.daos.ClienteDAO;
import br.unipar.trabalho1B.exceptions.PizzariaException;
import br.unipar.trabalho1B.util.EntityManagerUtil;
import br.unipar.trabalho1B.models.Cliente;
import jakarta.jws.WebService;

import java.util.Date;
import java.util.List;

@WebService(endpointInterface = "br.unipar.trabalho1B.services.ClienteSEI")

public class ClienteSIB implements ClienteSEI {

    @Override
    public String boasVindas(String nome) throws PizzariaException {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);

        if(clienteExiste(cliente.getNome())){
            return "Bem vindo(a) de volta " + nome + ", seu nome já está salvo na nossa base de dados!";
        } else {
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.salvar(cliente);
            return "Bem vindo(a) " + nome + ", seu nome foi salvo em nossa base de dados com sucesso!";
        }
    }

    @Override
    public String salvarNovoCiente(String nome, String telefone, String cpf, String endereco, Date dataNascimento) {
        Cliente cliente = new Cliente(nome, telefone, cpf, endereco, dataNascimento);

        ClienteDAO dao = new ClienteDAO();
        dao.salvar(cliente);

        return "Cliente salvo com sucesso!";
    }

    @Override
    public String editarCliente(Cliente cliente) throws PizzariaException {
        ClienteDAO dao = new ClienteDAO();
        Cliente clienteEditado = dao.buscarPorId(cliente.getId());

        if(clienteEditado != null){
            clienteEditado.setNome(cliente.getNome());
            clienteEditado.setTelefone(cliente.getTelefone());
            clienteEditado.setCpf(cliente.getCpf());
            clienteEditado.setEndereco(cliente.getEndereco());
            clienteEditado.setDataNascimento(cliente.getDataNascimento());

            ClienteDAO daoEditar = new ClienteDAO();
            daoEditar.atualizar(clienteEditado);
            return "Cliente editado com sucesso!";
        }

        return "Erro ao encontrar o cliente!";
    }

    @Override
    public List<Cliente> listarClientes() {
        ClienteDAO dao = new ClienteDAO();

        return dao.buscarTodos();
    }

    @Override
    public String excluirCliente(Integer id) throws PizzariaException {
        ClienteDAO dao = new ClienteDAO();
        Cliente cliente = dao.buscarPorId(id);

        ClienteDAO daoExcluir = new ClienteDAO();
        if(!daoExcluir.excluir(id)){
            throw new PizzariaException("Erro ao excluir cliente");
        }

        return "Cliente excluído com sucesso!";
    }

    private boolean clienteExiste(String nome){
        ClienteDAO clienteDAO = new ClienteDAO();

        Cliente cliente = clienteDAO.buscarPorNome(nome);

        if(cliente == null){
            return false;
        } else {
            return true;
        }
    }
}
