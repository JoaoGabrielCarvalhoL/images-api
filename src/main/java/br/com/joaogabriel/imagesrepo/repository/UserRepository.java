package br.com.joaogabriel.imagesrepo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joaogabriel.imagesrepo.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

	Optional<User> findByEmail(String email);
	
	Optional<User> findByUsername(String email);
}
