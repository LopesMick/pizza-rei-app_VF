package br.senac.rj.backend.service;

import br.senac.rj.backend.dao.CategoriaDao;
import br.senac.rj.backend.entity.Categoria;

import java.util.List;

public class CategoriaService {

    private final CategoriaDao dao = new CategoriaDao();

    public Categoria salvar(Categoria categoria) {
        if (categoria.getNome() == null || categoria.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome da categoria é obrigatório.");
        }
        return dao.salvar(categoria);
    }

    public List<Categoria> listar() {
        return dao.listar();
    }

    public boolean remover(Long id) {
        return dao.remover(id);
    }
}
