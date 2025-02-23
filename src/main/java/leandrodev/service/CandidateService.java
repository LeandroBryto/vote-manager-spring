package leandrodev.service;

import leandrodev.exception.ResourceNotFoundException;
import leandrodev.model.Candidate;
import leandrodev.repositery.CandidateRepository;
import leandrodev.repositery.VoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private VoteRepository voteRepository;

    // 🔍 Buscar candidato por ID
    public Candidate getCandidateById(UUID id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidato com ID " + id + " não encontrado!"));
    }

    // 🔍 Listar todos os candidatos
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    // ✅ Criar candidato (não pode ter nome repetido)
    public Candidate createCandidate(Candidate candidate) {
        Optional<Candidate> existingCandidate = candidateRepository.findByName(candidate.getName());
        if (existingCandidate.isPresent()) {
            throw new IllegalArgumentException("Já existe um candidato com esse nome!");
        }
        return candidateRepository.save(candidate);
    }

    // ✏️ Atualizar candidato (não pode ter nome repetido)
    public Candidate updateCandidate(UUID id, Candidate updatedCandidate) {
        return candidateRepository.findById(id).map(candidate -> {
            Optional<Candidate> existingCandidate = candidateRepository.findByName(updatedCandidate.getName());
            if (existingCandidate.isPresent() && !existingCandidate.get().getId().equals(id)) {
                throw new IllegalArgumentException("Já existe um candidato com esse nome!");
            }
            candidate.setName(updatedCandidate.getName());
            return candidateRepository.save(candidate);
        }).orElseThrow(() -> new ResourceNotFoundException("Candidato com ID " + id + " não encontrado!"));
    }

    // 🗑️ Deletar candidato (Remove os votos antes)
    public void deleteCandidate(UUID id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidato com ID " + id + " não encontrado!"));

        voteRepository.deleteByCandidate(candidate);
        candidateRepository.delete(candidate);
    }
}
