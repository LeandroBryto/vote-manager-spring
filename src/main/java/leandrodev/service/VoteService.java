package leandrodev.service;

import leandrodev.exception.ResourceNotFoundException;
import leandrodev.model.Candidate;
import leandrodev.model.User;
import leandrodev.model.Vote;
import leandrodev.repositery.CandidateRepository;
import leandrodev.repositery.UserRepository;
import leandrodev.repositery.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CandidateRepository candidateRepository;


    public Vote createVote(UUID userId, UUID candidateId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));


        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidato não encontrado"));

        Vote vote = new Vote();
        vote.setUser(user);
        vote.setCandidate(candidate);

        return voteRepository.save(vote);
    }


    public List<Vote> getAllVotes() {

        List<Vote> votes = voteRepository.findAll();
        if (votes.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum voto encontrado");
        }
        return votes;
    }
}
