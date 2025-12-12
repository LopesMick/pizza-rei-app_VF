package br.senac.rj.backend.dao;

import java.util.List;

import br.senac.rj.backend.entity.Contato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Acesso a dados para a entidade Contato.
 */
public class ContatoDao {

    // Usa a mesma persistence-unit do restante do projeto
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("backendPU");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void salvar(Contato contato) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (contato.getId() == null) {
                em.persist(contato);
            } else {
                em.merge(contato);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Contato> listarTodos() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                    "SELECT c FROM Contato c ORDER BY c.id DESC",
                    Contato.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Contato> listarPorUsuario(Long usuarioId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                    "SELECT c FROM Contato c " +
                    "WHERE c.usuarioId = :usuarioId " +
                    "ORDER BY c.id DESC",
                    Contato.class
            )
            .setParameter("usuarioId", usuarioId)
            .getResultList();
        } finally {
            em.close();
        }
    }
}
