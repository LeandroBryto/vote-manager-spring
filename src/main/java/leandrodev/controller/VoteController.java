package leandrodev.controller;

import leandrodev.model.Vote;
import leandrodev.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    // ✅ Criar voto
    @PostMapping("/{userId}/{candidateId}")
    public Vote createVote(@PathVariable UUID userId, @PathVariable UUID candidateId) {
        return voteService.createVote(userId, candidateId);
    }

    
    @GetMapping
    public List<Vote> getAllVotes() {
        return voteService.getAllVotes();
    }
}
