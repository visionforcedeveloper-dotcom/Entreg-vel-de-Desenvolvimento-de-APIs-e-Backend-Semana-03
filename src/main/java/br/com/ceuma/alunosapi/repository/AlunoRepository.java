package br.com.ceuma.alunosapi.repository;

import br.com.ceuma.alunosapi.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// interface que faz o acesso ao banco, o Spring já implementa tudo sozinho
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
