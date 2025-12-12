package br.senac.rj.backend.service;

import br.senac.rj.backend.dao.EnderecoDao;
import br.senac.rj.backend.entity.Endereco;

import java.util.List;

public class EnderecoService {

    private final EnderecoDao dao = new EnderecoDao();

    public Endereco salvar(Endereco endereco) {
        if (endereco == null) return null;

        // Validações mínimas
        if (endereco.getUsuarioId() == null) return null;
        if (isBlank(endereco.getCep())) return null;
        if (isBlank(endereco.getLogradouro())) return null;
        if (isBlank(endereco.getNumero())) return null;
        if (isBlank(endereco.getBairro())) return null;
        if (isBlank(endereco.getCidade())) return null;
        if (isBlank(endereco.getEstado())) return null;

        // Normaliza estado (RJ, SP...)
        endereco.setEstado(endereco.getEstado().trim().toUpperCase());

        // Se veio null, assume false
        if (endereco.getPrincipal() == null) {
            endereco.setPrincipal(false);
        }

        // Regra simples: se marcou como principal,
        // você pode no futuro desmarcar os outros endereços desse usuário.
        // Por ora vamos manter simples.
        return dao.salvar(endereco);
    }

    public List<Endereco> listar() {
        return dao.listar();
    }

    public List<Endereco> listarPorUsuario(Long usuarioId) {
        if (usuarioId == null) return List.of();
        return dao.listarPorUsuario(usuarioId);
    }

    public Endereco buscarPorId(Long id) {
        if (id == null) return null;
        return dao.buscarPorId(id);
    }

    public boolean remover(Long id) {
        if (id == null) return false;
        return dao.remover(id);
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
