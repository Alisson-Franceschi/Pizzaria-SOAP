package br.unipar.trabalho1B.daos;

import br.unipar.trabalho1B.models.Borda;
import br.unipar.trabalho1B.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BordaDAO {
    EntityManager em = EntityManagerUtil.getEm();

    public void salvar(Borda borda) {
        try{
            em.getTransaction().begin();
            em.persist(borda);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void atualizar(Borda borda) {
        try{
            em.getTransaction().begin();
            borda = em.merge(borda);
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
            Borda borda = em.find(Borda.class, id);
            em.remove(borda);
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

    public Borda buscarPorId(Integer id) {
        try{
            return em.find(Borda.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public Borda buscarPorSabor(String sabor) {
        try{
            return em.createQuery("from Borda where sabor = :sabor", Borda.class)
                    .setParameter("sabor", sabor)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public List<Borda> buscarTodos() {
        try{
            return em.createQuery("from Borda", Borda.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}
