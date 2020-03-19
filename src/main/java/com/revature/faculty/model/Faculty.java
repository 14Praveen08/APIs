package com.revature.faculty.model;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "`faculty`")
public class Faculty {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "`employee_id`")
	private Integer employee_id;
	@ManyToOne
	@JoinColumn(name="institution_id", referencedColumnName ="id")
	private Organization org;
	@Column(name = "`first_name`")
	private String first_name;
	@Column(name = "`last_name`")
	private String last_name;
	@Column(name = "`dob`")
	private Date dob;
	@Column(name = "`email_id`")
	private String email;
	@Column(name = "`mobile_number`")
	private Long mobile_no;
	@OneToOne
	@JoinColumn(name="role_id", referencedColumnName = "id")
	private Roles roles;	
	@Column(name = "`created_on`")
	private LocalDateTime createdon;
	@Column(name = "`modified_on`")
	private LocalDateTime modifiedon;
	@Column(name = "`created_by`")
	private String createdby;
	@Column(name = "`modified_by`")
	private String modifiedby;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public Long getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(Long mobile_no) {
		this.mobile_no = mobile_no;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public LocalDateTime getCreatedon() {
		return createdon;
	}

	public void setCreatedon(LocalDateTime createdon) {
		this.createdon = createdon;
	}

	public LocalDateTime getModifiedon() {
		return modifiedon;
	}

	public void setModifiedon(LocalDateTime modifiedon) {
		this.modifiedon = modifiedon;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}


	
	

}
