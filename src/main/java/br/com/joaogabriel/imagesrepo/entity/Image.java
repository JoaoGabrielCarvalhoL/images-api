package br.com.joaogabriel.imagesrepo.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.joaogabriel.imagesrepo.enumerations.ImageExtension;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Table(name= "tb_image")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Image implements Serializable {

	@Serial
	private static final long serialVersionUID = 0L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false)
	private String name; 
	
	@Column(nullable = false)
	private Long size;
	
	@Enumerated(EnumType.STRING)
	private ImageExtension extension;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@CreatedDate
	private LocalDateTime uploadIn;
	
	private String tags;

	@Column(nullable = false)
	@Lob
	private byte[] file;
	
	public Image() {	}
	
	public Image(String name, Long size, ImageExtension extension, String tags, byte[] file) {
		this.name = name; 
		this.size = size; 
		this.extension = extension; 
		this.tags = tags; 
		this.file = file;
	}
	
	public Image(UUID id, String name, Long size, ImageExtension extension, String tags, byte[] file) {
		this(name, size, extension, tags, file);
		this.id = id;
	}

	
	public UUID getId() {
		return id;
	}

	
	public void setId(UUID id) {
		this.id = id;
	}
	

	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public Long getSize() {
		return size;
	}

	
	public void setSize(Long size) {
		this.size = size;
	}

	
	public ImageExtension getExtension() {
		return extension;
	}

	
	public void setExtension(ImageExtension extension) {
		this.extension = extension;
	}

	
	public LocalDateTime getUploadIn() {
		return uploadIn;
	}

	
	public void setUploadIn(LocalDateTime uploadIn) {
		this.uploadIn = uploadIn;
	}

	
	public String getTags() {
		return tags;
	}

	
	public void setTags(String tags) {
		this.tags = tags;
	}

	
	public byte[] getFile() {
		return file;
	}

	
	public void setFile(byte[] file) {
		this.file = file;
	}
	
	public String getOriginalFileName() {
		return String.format("%s.%s", this.name, this.extension.name().toLowerCase());
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
