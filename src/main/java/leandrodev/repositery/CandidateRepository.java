package leandrodev.repositery;

import leandrodev.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<Candidate,UUID> {
    Optional<Candidate> findByName(String name);
}
