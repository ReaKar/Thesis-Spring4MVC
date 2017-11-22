package com.websystique.springmvc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "User_submits_Project")
public class Submit implements Serializable {

	@Id
	@Column(name = "idSubmission", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSubmission;
	
	@Column(name = "`when`", columnDefinition="DATETIME")
	@DateTimeFormat ( pattern = "yyyy-MM-dd HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date when;
	
	@Column(name = "type", nullable = false)
	private String type;
	
	@Column(name = "name", nullable = false)
	private String name;

	@Lob
	@Column(name = "filepath", nullable = false)
	private String filepath;


	@ManyToOne(optional = false)
	@JoinColumn(name = "idUser")
	private User user;

	@ManyToOne(optional = false)
	@JoinColumn(name = "idProject")	
	private Project project;

	public Integer getIdSubmission() {
		return idSubmission;
	}

	public void setIdSubmission(Integer idSubmission) {
		this.idSubmission = idSubmission;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Date getWhen() {
		return when;
	}

	public void setWhen(Date when) {
		this.when = when;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idSubmission == null) ? 0 : idSubmission.hashCode());
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
		Submit other = (Submit) obj;
		if (idSubmission == null) {
			if (other.idSubmission != null)
				return false;
		} else if (!idSubmission.equals(other.idSubmission))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Submit [idSubmission=" + idSubmission + ", idProject=" + project.getIdProject() + ", filepath=" + filepath + ", when=" + when + "]";
	}

}
