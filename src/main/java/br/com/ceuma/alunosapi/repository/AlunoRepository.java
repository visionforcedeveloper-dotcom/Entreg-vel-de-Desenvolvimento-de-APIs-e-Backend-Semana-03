package br.com.ceuma.alunosapi.repository;

import br.com.ceuma.alunosapi.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository responsável pelo acesso ao banco de dados para a Entity Aluno.
 *
 * Ao estender JpaRepository, ganhamos gratuitamente os métodos:
 *   - save(entity)       → INSERT / UPDATE
 *   - findById(id)       → SELECT por PK  (retorna Optional<Aluno>)
 *   - existsById(id)     → SELECT EXISTS
 *   - deleteById(id)     → DELETE por PK
 *   - findAll()          → SELECT *
 */
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    // Nenhum método extra necessário para o CRUD básico
}
