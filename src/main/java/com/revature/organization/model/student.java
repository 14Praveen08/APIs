package com.revature.organization.model;

import java.sql.Date;
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
@Table(name = "Student")
public class student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "institution_id", referencedColumnName = "id")
	private Organization org;
	@Column(name = "register_number", unique = true)
	private Long redgno;
	@Column(name = "first_name")
	private String fname;
	@Column(name = "last_name")
	private String lname;
	@Column(name = "date_of_birth")
	private Date dob;
	@Column(name = "year")
	private Integer year;
	@OneToOne
	@JoinColumn(name = "department_id", referencedColumnName = "id")
	private Department department;
	@Column(name = "Mobile_number")
	private Long mobileno;
	@Column(name = "email", unique = true)
	private String email;
	@Column(name = "created_on")
	private LocalDateTime createdon;
	@Column(name = "modified_on")
	private LocalDateTime modifiedon;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public Long getRedgno() {
		return redgno;
	}

	public void setRedgno(Long redgno) {
		this.redgno = redgno;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Long getMobileno() {
		return mobileno;
	}

	public void setMobileno(Long mobileno) {
		this.mobileno = mobileno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public String toString() {
		return "student [id=" + id + ", org=" + org + ", redgno=" + redgno + ", fname=" + fname + ", lname=" + lname
				+ ", dob=" + dob + ", year=" + year + ", department=" + department + ", mobileno=" + mobileno
				+ ", email=" + email + ", createdon=" + createdon + ", modifiedon=" + modifiedon + "]";
	}

}
