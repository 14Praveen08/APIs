package com.revature.faculty.model;

import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="institution")
public class Organization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column 
	private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="alias_name")
	private String alias;
	@Column(name="university")
	private String university;
	@Column(name="created_on")
	private Instant createdon;
	@Column(name="modified_on")
	private Instant modifiedon;
	@Column(name="isactive")
	private Boolean isActive=true;

	/*
	 * @OneToMany(mappedBy = "org") private List<Faculty> facultyList; public
	 * List<Faculty> getFacultyList() { return facultyList; } public void
	 * setFacultyList(List<Faculty> facultyList) { this.facultyList = facultyList; }
	 */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public Instant getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Instant createdon) {
		this.createdon = createdon;
	}
	public Instant getModifiedon() {
		return modifiedon;
	}
	public void setModifiedon(Instant modifiedon) {
		this.modifiedon = modifiedon;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + ", alias=" + alias + ", university=" + university
				+ ", createdon=" + createdon + ", modifiedon=" + modifiedon + ", isActive=" + isActive + "]";
	}

	
	

}
