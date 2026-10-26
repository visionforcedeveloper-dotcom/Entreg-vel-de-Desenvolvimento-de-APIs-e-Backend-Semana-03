package br.com.ceuma.alunosapi.dto;

// dados que chegam na requisicao (POST e PUT)
// nao tem id porque quem gera o id e o banco
public class AlunoRequest {

    private String nome;
    private String email;
    private String curso;

    public AlunoRequest() {
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
