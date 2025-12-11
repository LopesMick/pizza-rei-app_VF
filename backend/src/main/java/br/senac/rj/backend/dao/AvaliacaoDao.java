package br.senac.rj.backend.dao;

import br.senac.rj.backend.entity.Avaliacao;
import jakarta.persistence.*;

import java.util.List;

public class AvaliacaoDao {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("backendPU");

    public Avaliacao salvar(Avaliacao avaliacao) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            if (avaliacao.getId() == null) {
                em.persist(avaliacao);
            } else {
                avaliacao = em.merge(avaliacao);
            }

            tx.commit();
            return avaliacao;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            return null;
        } finally {
            em.close();
        }
    }

    public List<Avaliacao> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Avaliacao a ORDER BY a.id DESC", Avaliacao.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Avaliacao buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Avaliacao.class, id);
        } finally {
            em.close();
        }
    }

    public boolean remover(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Avaliacao a = em.find(Avaliacao.class, id);
            if (a == null) {
                tx.rollback();
                return false;
            }
            em.remove(a);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            return false;
        } finally {
            em.close();
        }
    }
}
