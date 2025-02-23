package leandrodev.repositery;

import leandrodev.model.Candidate;
import leandrodev.model.User;
import leandrodev.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VoteRepository extends JpaRepository<Vote, UUID> {
    long countByCandidateId(UUID candidateId);
    boolean existsByUserIdAndCandidateId(UUID userId, UUID candidateId);  // Verifica se o voto já foi registrado

    void deleteByCandidate(Candidate candidate);

    void deleteByUser(User user);
}
