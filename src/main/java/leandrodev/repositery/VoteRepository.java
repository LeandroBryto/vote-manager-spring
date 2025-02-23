package leandrodev.repositery;

import leandrodev.model.Candidate;
import leandrodev.model.User;
import leandrodev.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VoteRepository extends JpaRepository<Vote, UUID> {

    void deleteByCandidate(Candidate candidate);

    void deleteByUser(User user);
}
