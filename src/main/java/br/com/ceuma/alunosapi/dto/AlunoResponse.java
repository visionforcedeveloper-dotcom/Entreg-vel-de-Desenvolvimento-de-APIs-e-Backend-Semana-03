package br.com.ceuma.alunosapi.dto;

// dados que a API devolve para quem fez a requisicao
// aqui ja tem o id porque o banco ja gerou
public class AlunoResponse {

    private Long id;
    private String nome;
    private String email;
    private String curso;

    public AlunoResponse() {
    }

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
