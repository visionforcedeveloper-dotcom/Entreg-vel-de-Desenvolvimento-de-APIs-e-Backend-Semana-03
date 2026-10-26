package br.com.ceuma.alunosapi.service;

import br.com.ceuma.alunosapi.dto.AlunoRequest;
import br.com.ceuma.alunosapi.dto.AlunoResponse;
import br.com.ceuma.alunosapi.entity.Aluno;
import br.com.ceuma.alunosapi.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    // cria um novo aluno no banco
    public AlunoResponse create(AlunoRequest request) {
        Aluno novoAluno = new Aluno();
        novoAluno.setNome(request.getNome());
        novoAluno.setEmail(request.getEmail());
        novoAluno.setCurso(request.getCurso());

        Aluno alunoSalvo = alunoRepository.save(novoAluno);
        return converterParaResponse(alunoSalvo);
    }

    // busca aluno pelo id, retorna null se nao encontrar
    public AlunoResponse getById(Long id) {
        Optional<Aluno> optional = alunoRepository.findById(id);

        if (optional.isPresent()) {
            Aluno aluno = optional.get();
            return converterParaResponse(aluno);
        } else {
            return null;
        }
    }

    // atualiza os dados do aluno, retorna false se o id nao existir
    public boolean update(Long id, AlunoRequest request) {
        Optional<Aluno> optional = alunoRepository.findById(id);

        if (optional.isPresent()) {
            Aluno alunoExistente = optional.get();
            alunoExistente.setNome(request.getNome());
            alunoExistente.setEmail(request.getEmail());
            alunoExistente.setCurso(request.getCurso());
            alunoRepository.save(alunoExistente);
            return true;
        } else {
            return false;
        }
    }

    // deleta o aluno, retorna false se o id nao existir
    public boolean delete(Long id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // converte a entity para o DTO de resposta
    private AlunoResponse converterParaResponse(Aluno aluno) {
        AlunoResponse response = new AlunoResponse();
        response.setId(aluno.getId());
        response.setNome(aluno.getNome());
        response.setEmail(aluno.getEmail());
        response.setCurso(aluno.getCurso());
        return response;
    }
}
