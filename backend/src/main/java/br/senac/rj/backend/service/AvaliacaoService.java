package br.senac.rj.backend.service;

import br.senac.rj.backend.dao.AvaliacaoDao;
import br.senac.rj.backend.entity.Avaliacao;

import java.util.List;

public class AvaliacaoService {

    private final AvaliacaoDao dao = new AvaliacaoDao();

    public Avaliacao salvar(Avaliacao avaliacao) {
        // Regras simples de validação
        if (avaliacao == null) return null;
        if (avaliacao.getNota() == null) return null;
        if (avaliacao.getNota() < 1 || avaliacao.getNota() > 5) return null;

        return dao.salvar(avaliacao);
    }

    public List<Avaliacao> listar() {
        return dao.listar();
    }

    public Avaliacao buscarPorId(Long id) {
        if (id == null) return null;
        return dao.buscarPorId(id);
    }

    public boolean remover(Long id) {
        if (id == null) return false;
        return dao.remover(id);
    }
}

