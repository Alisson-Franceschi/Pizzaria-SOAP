package br.unipar.trabalho1B.daos;

import br.unipar.trabalho1B.models.Itens_Pedido;
import br.unipar.trabalho1B.models.Pedido;
import br.unipar.trabalho1B.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class Itens_PedidoDAO {
    EntityManager em = EntityManagerUtil.getEm();

    public void salvar(Itens_Pedido itens) {
        try{
            em.getTransaction().begin();
            em.persist(itens);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void atualizar(Itens_Pedido itens) {
        try{
            em.getTransaction().begin();
            itens = em.merge(itens);
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
            Itens_Pedido itens = em.find(Itens_Pedido.class, id);
            em.remove(itens);
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

    public Itens_Pedido buscarPorId(Integer id) {
        try{
            return em.find(Itens_Pedido.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public Itens_Pedido buscarPorPedido(Pedido pedido) {
        try{
            return em.createQuery("from Itens_Pedido where pedido = :pedido", Itens_Pedido.class)
                    .setParameter("pedido", pedido)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public List<Itens_Pedido> buscarTodos() {
        try{
            return em.createQuery("from Itens_Pedido", Itens_Pedido.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}
