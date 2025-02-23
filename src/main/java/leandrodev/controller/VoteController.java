package leandrodev.controller;

import leandrodev.model.Vote;
import leandrodev.service.VoteService;
import leandrodev.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;


    @PostMapping("/{userId}/{candidateId}")
    public Vote createVote(@PathVariable UUID userId, @PathVariable UUID candidateId) {
        try {
            return voteService.createVote(userId, candidateId);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Usuário ou Candidato não encontrado.");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Erro ao criar voto: " + e.getMessage());
        }
    }


    @GetMapping
    public List<Vote> getAllVotes() {
        try {
            return voteService.getAllVotes();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar votos: " + e.getMessage());
        }
    }
}
