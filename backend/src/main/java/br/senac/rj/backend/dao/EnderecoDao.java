package br.senac.rj.backend.dao;

import br.senac.rj.backend.entity.Endereco;
import jakarta.persistence.*;

import java.util.List;

public class EnderecoDao {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("backendPU");

    public Endereco salvar(Endereco endereco) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            if (endereco.getId() == null) {
                em.persist(endereco);
            } else {
                endereco = em.merge(endereco);
            }

            tx.commit();
            return endereco;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            return null;
        } finally {
            em.close();
        }
    }

    public List<Endereco> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT e FROM Endereco e ORDER BY e.id DESC", Endereco.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Endereco> listarPorUsuario(Long usuarioId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT e FROM Endereco e WHERE e.usuarioId = :uid ORDER BY e.principal DESC, e.id DESC",
                            Endereco.class)
                    .setParameter("uid", usuarioId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Endereco buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Endereco.class, id);
        } finally {
            em.close();
        }
    }

    public boolean remover(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Endereco e = em.find(Endereco.class, id);
            if (e == null) {
                tx.rollback();
                return false;
            }

            em.remove(e);
            tx.commit();
            return true;
        } catch (Exception ex) {
            if (tx.isActive()) tx.rollback();
            return false;
        } finally {
            em.close();
        }
    }
}
