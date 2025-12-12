package br.senac.rj.backend.service;

import java.util.List;

import br.senac.rj.backend.dao.ContatoDao;
import br.senac.rj.backend.entity.Contato;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

/**
 * Regras de negócio e orquestração para o módulo de Contato (Fale Conosco).
 */
public class ContatoService {

    private final ContatoDao contatoDao = new ContatoDao();

    /**
     * Salva uma nova mensagem de contato.
     *
     * @param contato objeto vindo do Controller (JSON do Angular mapeado para a entidade)
     * @return Response com status HTTP e mensagem em JSON
     */
    public Response salvar(Contato contato) {
        try {
            if (contato == null) {
                return Response.status(Status.BAD_REQUEST)
                        .entity("{\"erro\":\"Dados do contato não enviados.\"}")
                        .build();
            }

            if (contato.getNome() == null || contato.getNome().isBlank()) {
                return Response.status(Status.BAD_REQUEST)
                        .entity("{\"erro\":\"Nome é obrigatório.\"}")
                        .build();
            }

            if (contato.getEmail() == null || contato.getEmail().isBlank()) {
                return Response.status(Status.BAD_REQUEST)
                        .entity("{\"erro\":\"E-mail é obrigatório.\"}")
                        .build();
            }

            if (contato.getMensagem() == null || contato.getMensagem().isBlank()) {
                return Response.status(Status.BAD_REQUEST)
                        .entity("{\"erro\":\"Mensagem é obrigatória.\"}")
                        .build();
            }

            contatoDao.salvar(contato);

            return Response.status(Status.CREATED)
                    .entity("{\"mensagem\":\"Contato registrado com sucesso.\"}")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erro\":\"Não foi possível registrar o contato.\"}")
                    .build();
        }
    }

    public Response listarTodos() {
        try {
            List<Contato> contatos = contatoDao.listarTodos();
            return Response.ok(contatos).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erro\":\"Não foi possível listar os contatos.\"}")
                    .build();
        }
    }

    public Response listarPorUsuario(Long usuarioId) {
        try {
            if (usuarioId == null) {
                return Response.status(Status.BAD_REQUEST)
                        .entity("{\"erro\":\"ID do usuário é obrigatório.\"}")
                        .build();
            }

            List<Contato> contatos = contatoDao.listarPorUsuario(usuarioId);
            return Response.ok(contatos).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erro\":\"Não foi possível listar os contatos do usuário.\"}")
                    .build();
        }
    }
}
