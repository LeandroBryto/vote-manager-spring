package leandrodev.controller;

import leandrodev.model.Candidate;
import leandrodev.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    // 🔍 Buscar todos os candidatos
    @GetMapping
    public List<Candidate> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    // 🔍 Buscar candidato por ID
    @GetMapping("/{id}")
    public Candidate getCandidateById(@PathVariable UUID id) {
        return candidateService.getCandidateById(id);
    }

    // ✅ Criar candidato
    @PostMapping
    public Candidate createCandidate(@RequestBody Candidate candidate) {
        return candidateService.createCandidate(candidate);
    }

    // ✏️ Atualizar candidato
    @PutMapping("/{id}")
    public Candidate updateCandidate(@PathVariable UUID id, @RequestBody Candidate updatedCandidate) {
        return candidateService.updateCandidate(id, updatedCandidate);
    }

    // 🗑️ Deletar candidato
    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable UUID id) {
        candidateService.deleteCandidate(id);
    }
}
