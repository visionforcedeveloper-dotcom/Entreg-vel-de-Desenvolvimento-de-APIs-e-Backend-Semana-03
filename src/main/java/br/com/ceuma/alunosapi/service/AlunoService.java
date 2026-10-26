package br.com.ceuma.alunosapi.service;

import br.com.ceuma.alunosapi.dto.AlunoRequest;
import br.com.ceuma.alunosapi.dto.AlunoResponse;
import br.com.ceuma.alunosapi.entity.Aluno;
import br.com.ceuma.alunosapi.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Camada de serviço responsável pela lógica de negócio do CRUD de Alunos.
 *
 * Responsabilidades:
 *   - Converter AlunoRequest  → Entity Aluno  (antes de persistir)
 *   - Converter Entity Aluno  → AlunoResponse (antes de retornar ao cliente)
 *   - Orquestrar as chamadas ao AlunoRepository
 *   - Tratar o Optional de forma explícita com if/else (sem lambda)
 */
@Service
public class AlunoService {

    // Injeção via construtor (boa prática: facilita testes unitários)
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    // ================================================================
    // CREATE – POST /alunos
    // ================================================================

    /**
     * Recebe um AlunoRequest, cria uma nova Entity, persiste no banco
     * e retorna o AlunoResponse com o ID gerado.
     */
    public AlunoResponse create(AlunoRequest request) {

        // 1. Converte o DTO de entrada para a Entity
        Aluno novoAluno = new Aluno();
        novoAluno.setNome(request.getNome());
        novoAluno.setEmail(request.getEmail());
        novoAluno.setCurso(request.getCurso());

        // 2. Persiste no banco – o JPA preenche o ID automaticamente
        Aluno alunoSalvo = alunoRepository.save(novoAluno);

        // 3. Converte a Entity para o DTO de saída e retorna
        return converterParaResponse(alunoSalvo);
    }

    // ================================================================
    // READ BY ID – GET /alunos/{id}
    // ================================================================

    /**
     * Busca um aluno pelo ID.
     * Usa Optional com if/else explícito (sem lambda) para tratar
     * o caso em que o ID não existe no banco.
     *
     * @return AlunoResponse se encontrado, ou null se não encontrado
     *         (o Controller decide o status HTTP adequado)
     */
    public AlunoResponse getById(Long id) {

        // findById retorna Optional<Aluno>
        Optional<Aluno> optional = alunoRepository.findById(id);

        // Verificação explícita do Optional — SEM uso de lambda
        if (optional.isPresent()) {
            Aluno aluno = optional.get();
            return converterParaResponse(aluno);
        } else {
            return null; // Controller retornará 404 Not Found
        }
    }

    // ================================================================
    // UPDATE – PUT /alunos/{id}
    // ================================================================

    /**
     * Localiza o aluno pelo ID, atualiza seus campos com os dados do
     * AlunoRequest e persiste novamente.
     *
     * @return true se a atualização ocorreu, false se o ID não existe
     */
    public boolean update(Long id, AlunoRequest request) {

        Optional<Aluno> optional = alunoRepository.findById(id);

        if (optional.isPresent()) {
            Aluno alunoExistente = optional.get();

            // Atualiza apenas os campos enviados pelo cliente
            alunoExistente.setNome(request.getNome());
            alunoExistente.setEmail(request.getEmail());
            alunoExistente.setCurso(request.getCurso());

            // save() com ID preenchido executa UPDATE no banco
            alunoRepository.save(alunoExistente);

            return true;
        } else {
            return false; // Controller retornará 404 Not Found
        }
    }

    // ================================================================
    // DELETE – DELETE /alunos/{id}
    // ================================================================

    /**
     * Verifica se o aluno existe antes de removê-lo, evitando exceções
     * ao tentar excluir um ID inexistente.
     *
     * @return true se a exclusão ocorreu, false se o ID não existe
     */
    public boolean delete(Long id) {

        // existsById evita uma busca completa apenas para checar existência
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            return true;
        } else {
            return false; // Controller retornará 404 Not Found
        }
    }

    // ================================================================
    // Método auxiliar privado – converte Entity → DTO de saída
    // ================================================================

    /**
     * Centraliza a conversão de Aluno (Entity) para AlunoResponse (DTO).
     * Evita duplicação de código nos métodos create e getById.
     */
    private AlunoResponse converterParaResponse(Aluno aluno) {
        AlunoResponse response = new AlunoResponse();
        response.setId(aluno.getId());
        response.setNome(aluno.getNome());
        response.setEmail(aluno.getEmail());
        response.setCurso(aluno.getCurso());
        return response;
    }
}
