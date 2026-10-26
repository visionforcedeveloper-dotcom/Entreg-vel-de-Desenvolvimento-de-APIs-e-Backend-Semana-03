package br.com.ceuma.alunosapi.dto;

/**
 * DTO de SAÍDA (Response).
 *
 * Representa os dados que a API devolve ao cliente após uma operação.
 * Neste caso expõe o ID (gerado pelo banco) junto com os demais campos,
 * mas nunca expõe dados sensíveis que porventura existam na Entity.
 */
public class AlunoResponse {

    private Long id;
    private String nome;
    private String email;
    private String curso;

    // -------------------------------------------------------
    // Construtor padrão necessário para serialização JSON
    // -------------------------------------------------------
    public AlunoResponse() {
    }

    // -------------------------------------------------------
    // Getters e Setters
    // -------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
