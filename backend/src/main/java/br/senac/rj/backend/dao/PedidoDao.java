package br.senac.rj.backend.dao;

import java.util.List;

import br.senac.rj.backend.entity.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class PedidoDao {

    private final EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public Pedido salvar(Pedido pedido) {
        if (pedido.getId() == null) {
            em.persist(pedido);
            return pedido;
        } else {
            return em.merge(pedido);
        }
    }

    public Pedido buscarPorId(Long id) {
        return em.find(Pedido.class, id);
    }

    public List<Pedido> listarTodos() {
        TypedQuery<Pedido> query = em.createQuery(
                "SELECT p FROM Pedido p ORDER BY p.criadoEm DESC", Pedido.class);
        return query.getResultList();
    }

    public List<Pedido> listarPorUsuario(Long usuarioId) {
        TypedQuery<Pedido> query = em.createQuery(
                "SELECT p FROM Pedido p WHERE p.usuario.id = :usuarioId ORDER BY p.criadoEm DESC",
                Pedido.class);
        query.setParameter("usuarioId", usuarioId);
        return query.getResultList();
    }

    public void atualizarStatus(Long pedidoId, String novoStatus) {
        Pedido pedido = em.find(Pedido.class, pedidoId);
        if (pedido != null) {
            pedido.setStatus(novoStatus);
            em.merge(pedido);
        }
    }

    public void remover(Long pedidoId) {
        Pedido pedido = em.find(Pedido.class, pedidoId);
        if (pedido != null) {
            em.remove(pedido);
        }
    }
}
