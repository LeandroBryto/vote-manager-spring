package leandrodev.service;

import leandrodev.exception.ResourceNotFoundException;
import leandrodev.model.User;

import leandrodev.repositery.UserRepository;
import leandrodev.repositery.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VoteRepository voteRepository;

    // 🔍 Buscar usuário por ID
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com ID " + id + " não encontrado!"));
    }

    // 🔍 Listar todos os usuários
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ Criar usuário (não pode ter username repetido)
    public User createUser(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Já existe um usuário com esse username!");
        }
        return userRepository.save(user);
    }

    // ✏️ Atualizar usuário (não pode ter username repetido)
    public User updateUser(UUID id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            Optional<User> existingUser = userRepository.findByUsername(updatedUser.getUsername());
            if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
                throw new IllegalArgumentException("Já existe um usuário com esse username!");
            }
            user.setUsername(updatedUser.getUsername());
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("Usuário com ID " + id + " não encontrado!"));
    }

    // 🗑️ Deletar usuário (Remove os votos antes)
    public void deleteUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com ID " + id + " não encontrado!"));

        voteRepository.deleteByUser(user);
        userRepository.delete(user);
    }
}
