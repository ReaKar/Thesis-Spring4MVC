package com.websystique.springmvc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
@Table(name="Project")
public class Project implements Serializable{
	
	@Id 
	@Column(name="idProject",nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idProject;

////	@NotEmpty
//	@Column(name="idCourse", nullable=false)
//	private Integer idCourse;
//	
	@NotEmpty
	@Column(name="title", nullable=false)
	private String title;
		
	@Lob
	@Column(name="description", nullable=false)
	private String description;

	
	@Column(name="opendate", nullable=false)
  
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date opendate; 
	

	
	@Column(name="closedate", nullable=false)
   
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date closedate;  
	
//	@NotEmpty
//	@ManyToMany(fetch = FetchType.LAZY) //cascade = CascadeType.ALL
//	@JoinTable(name = "User_submits_Projects", 
//             joinColumns = { @JoinColumn(name = "idUser") }, 
//             inverseJoinColumns = { @JoinColumn(name = "idProject") })
//	private Set<User> user = new HashSet<User>();
	
	@JoinColumn(name = "idCourse", referencedColumnName = "idCourse")
    @ManyToOne(optional = false)
    private Courses idCourse;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Submit> submitList;

	public Integer getIdProject() {
		return idProject;
	}

	public void setIdProject(Integer idProject) {
		this.idProject = idProject;
	}

	public Courses getIdCourse(){   //sthn thesi t courses eixa integer
		return idCourse;
	}

	public void setIdCourse(Courses idCourse) {
		this.idCourse = idCourse;
	}
	
	public List<Submit> getSubmitList() {
        return submitList;
    }

    public void setSubmitList(List<Submit> submitList) {
        this.submitList = submitList;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getOpendate() {
		return opendate;
	}

	public void setOpendate(Date opendate) {
		this.opendate = opendate;
	}

	public Date getClosedate() {
		return closedate;
	}

	public void setClosedate(Date closedate) {
		this.closedate = closedate;
	}

//	public Set<User> getUser() {
//		return user;
//	}
//
//	public void setUser(Set<User> user) {
//		this.user = user;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProject == null) ? 0 : idProject.hashCode());
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
		Project other = (Project) obj;
		if (idProject == null) {
			if (other.idProject != null)
				return false;
		} else if (!idProject.equals(other.idProject))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Project [idProject=" + idProject + ", , title=" + title + ", description="
				+ description + ", opendate=" + opendate + ", closedate=" + closedate + "]";
	}

	
	
	
	
	

	    
	

	
	
} 
