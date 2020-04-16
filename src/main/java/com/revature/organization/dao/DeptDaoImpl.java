package com.revature.organization.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.organization.exception.DBException;
import com.revature.organization.model.Department;

@Repository
public class DeptDaoImpl implements DeptDao {
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Department> get() throws DBException {

		Session currentSession = entityManager.unwrap(Session.class);
		Query<Department> query = currentSession.createQuery("from Department", Department.class);
		List<Department> list = query.getResultList();
		return list;
	}

	@Override
	public Department get(Long id) throws DBException {
		Session currentSession = entityManager.unwrap(Session.class);
		Department dept = currentSession.get(Department.class, id);
		return dept;
	}

}
