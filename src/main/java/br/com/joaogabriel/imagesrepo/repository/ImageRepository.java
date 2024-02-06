package br.com.joaogabriel.imagesrepo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.joaogabriel.imagesrepo.entity.Image;
import br.com.joaogabriel.imagesrepo.enumerations.ImageExtension;

//@Transactional
public interface ImageRepository extends JpaRepository<Image, UUID> {
	
	@Query("Select i from Image i where i.extension =:extension")
	List<Image> findByExtension(@Param("extension") ImageExtension extension);
	
	@Query("Select i from Image i where i.name =:parameter")
	Image findByName(@Param("parameter") String parameter);

}
