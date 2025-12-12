package br.senac.rj.backend.service;

import java.util.List;

import br.senac.rj.backend.dao.PedidoDao;
import br.senac.rj.backend.entity.Pedido;
import br.senac.rj.backend.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class PedidoService {

    public Pedido salvar(Pedido pedido) {
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDao dao = new PedidoDao(em);

        try {
            em.getTransaction().begin();
            Pedido salvo = dao.salvar(pedido);
            em.getTransaction().commit();
            return salvo;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Pedido buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDao dao = new PedidoDao(em);

        try {
            return dao.buscarPorId(id);
        } finally {
            em.close();
        }
    }

    public List<Pedido> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDao dao = new PedidoDao(em);

        try {
            return dao.listarTodos();
        } finally {
            em.close();
        }
    }

    public List<Pedido> listarPorUsuario(Long usuarioId) {
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDao dao = new PedidoDao(em);

        try {
            return dao.listarPorUsuario(usuarioId);
        } finally {
            em.close();
        }
    }

    public void atualizarStatus(Long pedidoId, String novoStatus) {
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDao dao = new PedidoDao(em);

        try {
            em.getTransaction().begin();
            dao.atualizarStatus(pedidoId, novoStatus);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void cancelar(Long pedidoId) {
        atualizarStatus(pedidoId, "CANCELADO");
    }
}
