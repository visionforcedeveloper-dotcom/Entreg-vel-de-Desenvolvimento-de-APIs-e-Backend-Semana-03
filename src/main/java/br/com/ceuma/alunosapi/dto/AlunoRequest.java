package br.com.ceuma.alunosapi.dto;

/**
 * DTO de ENTRADA (Request).
 *
 * Representa os dados que o cliente envia no corpo (body) da requisição
 * ao criar ou atualizar um aluno. Separar da Entity garante que campos
 * internos (como o ID gerado pelo banco) nunca sejam recebidos pelo cliente.
 */
public class AlunoRequest {

    private String nome;
    private String email;
    private String curso;

    // -------------------------------------------------------
    // Construtor padrão necessário para desserialização JSON
    // -------------------------------------------------------
    public AlunoRequest() {
    }

    // -------------------------------------------------------
    // Getters e Setters
    // -------------------------------------------------------

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
