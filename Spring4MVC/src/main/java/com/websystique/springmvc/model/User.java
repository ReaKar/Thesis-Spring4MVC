package com.websystique.springmvc.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="User")
public class User implements Serializable{
	@Id 
	@Column(name="idUser",nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idUser;

	@NotEmpty
	@Column(name="username", unique=true, nullable=false)
	private String username;
	
	@NotEmpty
	@Column(name="password", nullable=false)
	private String password;
		
	@NotEmpty
	@Column(name="firstname", nullable=false)
	private String firstName;

	@NotEmpty
	@Column(name="lastname", nullable=false)
	private String lastName;

	@NotEmpty
	@Column(name="email", nullable=false)
	private String email;
	
	//@NotEmpty
	@Column(name="verified", nullable=false)
	private boolean verified;

	    
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "User_has_Role", 
             joinColumns = { @JoinColumn(name = "idUser") }, 
             inverseJoinColumns = { @JoinColumn(name = "idRole") })
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Submit> submit = new HashSet<Submit>();


	public Integer getIdUser() {
		return idUser;
	}


	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public boolean isVerified() {
		return verified;
	}


	public void setVerified(boolean verified) {
		this.verified = verified;
	}


	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}


	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	   public Set<Submit> getSubmit() {
	        return submit;
	    }
	 
	    public void setSubmit(Set<Submit> submit) {
	        this.submit = submit;
	    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", verified=" + verified
				+ ", userProfiles=" + userProfiles + "]";
	}
}



