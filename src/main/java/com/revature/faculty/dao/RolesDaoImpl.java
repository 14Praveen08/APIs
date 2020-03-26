package com.revature.faculty.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.faculty.Exception.DBException;
import com.revature.faculty.model.Roles;

@Repository

public class RolesDaoImpl implements RolesDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Roles> get() throws DBException {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Roles> query = currentSession.createQuery("from Roles", Roles.class);
		List<Roles> list = query.getResultList();
		return list;
	}

	@Override
	public Roles get(Long id) throws DBException {
		Session currentSession = entityManager.unwrap(Session.class);
		Roles rolesobj = currentSession.get(Roles.class, id);
		return rolesobj;
	}

	@Override
	public void save(Roles role) throws DBException {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(role);
	}

	@Override
	public void delete(Long id) throws DBException {
		Session currentSession = entityManager.unwrap(Session.class);
		Roles roles = currentSession.get(Roles.class, id);
		currentSession.delete(roles);

	}

}
