package br.senac.rj.backend.dao;

import br.senac.rj.backend.entity.Categoria;
import br.senac.rj.backend.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CategoriaDao {

    public Categoria salvar(Categoria categoria) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            if (categoria.getId() == null) {
                em.persist(categoria);
            } else {
                categoria = em.merge(categoria);
            }
            em.getTransaction().commit();
            return categoria;
        } finally {
            em.close();
        }
    }

    public List<Categoria> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("FROM Categoria", Categoria.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Categoria buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Categoria.class, id);
        } finally {
            em.close();
        }
    }

    public boolean remover(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Categoria c = em.find(Categoria.class, id);
            if (c == null) return false;

            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
            return true;
        } finally {
            em.close();
        }
    }
}
