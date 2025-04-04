package br.unipar.trabalho1B.daos;

import br.unipar.trabalho1B.models.Cliente;
import br.unipar.trabalho1B.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ClienteDAO {

    EntityManager em = EntityManagerUtil.getEm();

    public void salvar(Cliente cliente) {
        try{
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void atualizar(Cliente cliente) {
        try{
            em.getTransaction().begin();
            cliente = em.merge(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public boolean excluir(Integer id) {
        try{
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, id);
            em.remove(cliente);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public Cliente buscarPorId(Integer id) {
        try{
            return em.find(Cliente.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public Cliente buscarPorNome(String nome) {
        try{
            return em.createQuery("from Cliente where nome = :nome", Cliente.class)
                    .setParameter("nome", nome)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public List<Cliente> buscarTodos() {
        try{
            return em.createQuery("from Cliente", Cliente.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}
