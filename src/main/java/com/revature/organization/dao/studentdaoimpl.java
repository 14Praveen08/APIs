package com.revature.organization.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.organization.exception.DBException;
import com.revature.organization.model.student;

@Repository
public class studentdaoimpl implements studentdao {
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<student> get() throws DBException {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<student> query = currentSession.createQuery("from student", student.class);
		List<student> list = query.getResultList();
		return list;
	}

	@Override
	public void insert(student stud) throws DBException {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(stud);
	}

	@Override
	public void delete(int id) throws DBException {
		Session currentSession = entityManager.unwrap(Session.class);
		student studobj = currentSession.get(student.class, id);
		currentSession.delete(studobj);
	}

	@Override
	public List<student> getstudbyInst(int institutionid) throws DBException {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<student> query = currentSession.createQuery("from student Where institution_id=" + institutionid,
				student.class);
		List<student> list = query.getResultList();
		return list;
	}

	@Override
	public List<student> getstudbyInstYear(int institutionid, int year) throws DBException {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<student> query = currentSession
				.createQuery("from student Where institution_id=" + institutionid + "AND year=" + year, student.class);
		List<student> list = query.getResultList();
		return list;
	}

	@Override
	public List<student> getstudbyYear(int year) throws DBException {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<student> query = currentSession.createQuery("from student Where year=" + year, student.class);
		List<student> list = query.getResultList();
		return list;
	}

	@Override
	public student get(int id) throws DBException {
		Session currentSession = entityManager.unwrap(Session.class);
		student obj = currentSession.get(student.class, id);
		return obj;
	}
}
