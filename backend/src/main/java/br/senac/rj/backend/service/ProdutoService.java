package br.senac.rj.backend.service;

import br.senac.rj.backend.dao.ProdutoDao;
import br.senac.rj.backend.entity.Produto;

import java.util.List;

public class ProdutoService {

    private final ProdutoDao dao = new ProdutoDao();

    public Produto salvar(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório.");
        }
        if (produto.getPreco() == null) {
            throw new IllegalArgumentException("Preço do produto é obrigatório.");
        }
        return dao.salvar(produto);
    }

    public List<Produto> listar() {
        return dao.listar();
    }

    public boolean remover(Long id) {
        return dao.remover(id);
    }
}
