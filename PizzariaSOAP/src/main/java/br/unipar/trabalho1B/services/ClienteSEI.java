package br.unipar.trabalho1B.services;

import br.unipar.trabalho1B.exceptions.PizzariaException;
import br.unipar.trabalho1B.models.Cliente;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.Date;
import java.util.List;

@WebService
public interface ClienteSEI {

    @WebMethod
    String boasVindas (@WebParam(name = "nome") String nome) throws PizzariaException;

    @WebMethod
    String salvarNovoCiente(@WebParam(name = "nome") String nome,
                            @WebParam(name = "telefone") String telefone,
                            @WebParam(name = "cpf") String cpf,
                            @WebParam(name = "endereco") String endereco,
                            @WebParam(name = "dataNascimento") Date dataNascimento) throws PizzariaException;

    @WebMethod
    String editarCliente(@WebParam Cliente cliente) throws PizzariaException;

    @WebMethod
    List<Cliente> listarClientes() throws PizzariaException;

    @WebMethod
    String excluirCliente(@WebParam(name = "id") Integer id) throws PizzariaException;
}
