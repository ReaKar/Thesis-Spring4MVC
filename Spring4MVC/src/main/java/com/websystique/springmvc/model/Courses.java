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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Courses")
public class Courses implements Serializable{
	
	@Id 
	@Column(name="idCourse",nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idCourse;

	@NotEmpty
	@Column(name="semester", nullable=false)
	private String semester;
	
	@NotEmpty
	@Column(name="title", nullable=false)
	private String title;
		
	@Lob
	@Column(name="description", nullable=false)
	private String description;

	
	@Column(name="filepath", nullable=false)
	private String filepath;
	
	
	@Column(name="active", nullable=false)
	private boolean active;
	
	
	
	@Column(name = "opendate", columnDefinition="DATETIME")
	@DateTimeFormat ( pattern = "yyyy-MM-dd HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date opendate;
	
	@Column(name = "closedate", columnDefinition="DATETIME")
	@DateTimeFormat ( pattern = "yyyy-MM-dd HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date closedate;
	
	
//	@Column(name="opendate", nullable=false)
	   // @Type(type="date")
//		@DateTimeFormat ( pattern = "yyyy-MM-dd HH:mm")
//		private Date opendate; 

//	@Column(name="closedate", nullable=false)
    //@Type(type="date")
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
//	private Date closedate;  
	
	//@OneToMany(mappedBy="Courses")
	//private Set<Project> projects;
	
//	@OneToMany
//    @JoinTable(
//            name="Project",
//            joinColumns = @JoinColumn( name="idCourse"),
//            inverseJoinColumns = @JoinColumn( name="idProject")
//    )
//   
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idCourse")
	@OrderBy("closedate DESC") //me ayto tn tropo taksinomw ta project auta p lhgoyn prvta einai pio panv
    private List<Project> projectList;

	public Integer getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(Integer idCourse) {
		this.idCourse = idCourse;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
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

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	//public Set<Project> getProjects() {
	//	return projects;
	//}

	//public void setProjects(Set<Project> projects) {
	//	this.projects = projects;
	//}
	public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCourse == null) ? 0 : idCourse.hashCode());
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
		Courses other = (Courses) obj;
		if (idCourse == null) {
			if (other.idCourse != null)
				return false;
		} else if (!idCourse.equals(other.idCourse))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Courses [idCourse=" + idCourse + ", semester=" + semester + ", title=" + title + ", description="
				+ description + ", filepath=" + filepath + ", active=" + active + ", opendate=" + opendate
				+ ", closedate=" + closedate + "]";
	}

	    
	

	
	
} 
