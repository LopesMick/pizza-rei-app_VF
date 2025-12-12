package br.senac.rj.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidade para a tabela de Fale Conosco (contato).
 */
@Entity
@Table(name = "contato")
@Data
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Opcional: se quiser vincular a um usuário, guarde o id ou email
    // Neste primeiro momento vou deixar só como campo simples
    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(length = 120, nullable = false)
    private String nome;

    @Column(length = 160, nullable = false)
    private String email;

    @Column(length = 120)
    private String assunto;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensagem;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
}
