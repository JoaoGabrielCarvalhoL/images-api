package br.com.joaogabriel.imagesrepo.entity;

import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user_info")
@EntityListeners(AuditingEntityListener.class)
public class UserInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false)
    private String userAgent;

    @Column(nullable = false)
    private String internetProtocol;

    @Column(nullable = false)
    private String locale;
    
    @OneToOne(mappedBy = "userInfo")
    private User user;
    
    public UserInfo() { }
    
    public UserInfo(String userAgent, String internetProtocol, String locale) {
    	this.userAgent = userAgent;
    	this.internetProtocol = internetProtocol;
    	this.locale = locale;
    }

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getInternetProtocol() {
		return internetProtocol;
	}

	public void setInternetProtocol(String internetProtocol) {
		this.internetProtocol = internetProtocol;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    
}
