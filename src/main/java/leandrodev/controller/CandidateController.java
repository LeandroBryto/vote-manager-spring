package leandrodev.controller;

import leandrodev.model.Candidate;
import leandrodev.service.CandidateService;
import leandrodev.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;


    @GetMapping
    public List<Candidate> getAllCandidates() {
        try {
            return candidateService.getAllCandidates();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Nenhum candidato encontrado");
        }
    }


    @GetMapping("/{id}")
    public Candidate getCandidateById(@PathVariable UUID id) {
        try {
            return candidateService.getCandidateById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Candidato com ID " + id + " não encontrado");
        }
    }


    @PostMapping
    public Candidate createCandidate(@RequestBody Candidate candidate) {
        try {
            return candidateService.createCandidate(candidate);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Erro ao criar candidato: " + e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public Candidate updateCandidate(@PathVariable UUID id, @RequestBody Candidate updatedCandidate) {
        try {
            return candidateService.updateCandidate(id, updatedCandidate);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Candidato com ID " + id + " não encontrado");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Erro ao atualizar candidato: " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable UUID id) {
        try {
            candidateService.deleteCandidate(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Candidato com ID " + id + " não encontrado");
        }
    }
}
