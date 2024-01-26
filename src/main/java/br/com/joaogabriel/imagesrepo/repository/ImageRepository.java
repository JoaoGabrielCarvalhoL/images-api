package br.com.joaogabriel.imagesrepo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joaogabriel.imagesrepo.entity.Image;

public interface ImageRepository extends JpaRepository<Image, UUID> {

}
