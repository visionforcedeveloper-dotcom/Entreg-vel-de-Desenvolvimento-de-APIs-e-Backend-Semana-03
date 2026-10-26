package br.com.ceuma.alunosapi.controller;

import br.com.ceuma.alunosapi.dto.AlunoRequest;
import br.com.ceuma.alunosapi.dto.AlunoResponse;
import br.com.ceuma.alunosapi.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Controller REST que expõe os endpoints do CRUD de Alunos.
 *
 * Base path: /alunos
 *
 * Endpoints disponíveis:
 *   POST   /alunos         → cria um novo aluno          (201 Created)
 *   GET    /alunos/{id}    → busca um aluno por ID        (200 OK / 404 Not Found)
 *   PUT    /alunos/{id}    → atualiza um aluno por ID     (204 No Content / 404 Not Found)
 *   DELETE /alunos/{id}    → remove um aluno por ID       (204 No Content / 404 Not Found)
 */
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    // Injeção via construtor
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    // ================================================================
    // POST /alunos
    // Status de sucesso: 201 Created
    // ================================================================

    /**
     * Cria um novo aluno.
     *
     * @RequestBody  captura o JSON enviado no corpo da requisição e
     *               desserializa para AlunoRequest.
     *
     * Retorna 201 com o header Location apontando para o recurso criado
     * e o AlunoResponse (com ID gerado) no corpo.
     */
    @PostMapping
    public ResponseEntity<AlunoResponse> create(@RequestBody AlunoRequest request) {

        AlunoResponse response = alunoService.create(request);

        // Monta a URI do recurso recém-criado: /alunos/{id}
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity.created(location).body(response);
        // ResponseEntity.created(location) → HTTP 201 Created
    }

    // ================================================================
    // GET /alunos/{id}
    // Status de sucesso: 200 OK
    // Status de falha:   404 Not Found
    // ================================================================

    /**
     * Busca um aluno pelo ID informado na URL.
     *
     * @PathVariable captura o {id} diretamente do caminho da URL.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> getById(@PathVariable Long id) {

        AlunoResponse response = alunoService.getById(id);

        if (response != null) {
            return ResponseEntity.ok(response);
            // ResponseEntity.ok() → HTTP 200 OK com o corpo
        } else {
            return ResponseEntity.notFound().build();
            // ResponseEntity.notFound() → HTTP 404 Not Found sem corpo
        }
    }

    // ================================================================
    // PUT /alunos/{id}
    // Status de sucesso: 204 No Content
    // Status de falha:   404 Not Found
    // ================================================================

    /**
     * Atualiza os dados de um aluno existente.
     *
     * @PathVariable captura o {id} da URL.
     * @RequestBody  captura o JSON com os novos dados.
     *
     * Retorna 204 sem corpo quando a atualização ocorre com sucesso.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody AlunoRequest request) {

        boolean atualizado = alunoService.update(id, request);

        if (atualizado) {
            return ResponseEntity.noContent().build();
            // ResponseEntity.noContent() → HTTP 204 No Content
        } else {
            return ResponseEntity.notFound().build();
            // ResponseEntity.notFound() → HTTP 404 Not Found
        }
    }

    // ================================================================
    // DELETE /alunos/{id}
    // Status de sucesso: 204 No Content
    // Status de falha:   404 Not Found
    // ================================================================

    /**
     * Remove um aluno pelo ID.
     *
     * @PathVariable captura o {id} da URL.
     *
     * Retorna 204 sem corpo quando a exclusão ocorre com sucesso.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        boolean removido = alunoService.delete(id);

        if (removido) {
            return ResponseEntity.noContent().build();
            // ResponseEntity.noContent() → HTTP 204 No Content
        } else {
            return ResponseEntity.notFound().build();
            // ResponseEntity.notFound() → HTTP 404 Not Found
        }
    }
}
