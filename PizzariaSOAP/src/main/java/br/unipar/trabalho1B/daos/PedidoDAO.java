package br.unipar.trabalho1B.daos;

import br.unipar.trabalho1B.models.Pedido;
import br.unipar.trabalho1B.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PedidoDAO {
    EntityManager em = EntityManagerUtil.getEm();

    public void salvar(Pedido pedido) {
        try{
            em.getTransaction().begin();
            em.persist(pedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void atualizar(Pedido pedido) {
        try{
            em.getTransaction().begin();
            pedido = em.merge(pedido);
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
            Pedido pedido = em.find(Pedido.class, id);
            em.remove(pedido);
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

    public Pedido buscarPorId(Integer id) {
        try{
            return em.find(Pedido.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public Pedido buscarPorStatus(String status) {
        try{
            return em.createQuery("from Pedido where status = :status", Pedido.class)
                    .setParameter("status", status)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public List<Pedido> buscarTodos() {
        try{
            return em.createQuery("from Pedido", Pedido.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}
