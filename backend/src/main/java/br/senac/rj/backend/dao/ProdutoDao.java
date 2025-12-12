package br.senac.rj.backend.dao;

import br.senac.rj.backend.entity.Produto;
import br.senac.rj.backend.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProdutoDao {

    public Produto salvar(Produto produto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            if (produto.getId() == null) {
                em.persist(produto);
            } else {
                produto = em.merge(produto);
            }
            em.getTransaction().commit();
            return produto;
        } finally {
            em.close();
        }
    }

    public List<Produto> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("FROM Produto", Produto.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Produto buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Produto.class, id);
        } finally {
            em.close();
        }
    }

    public boolean remover(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Produto p = em.find(Produto.class, id);
            if (p == null) return false;

            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
            return true;
        } finally {
            em.close();
        }
    }
}

