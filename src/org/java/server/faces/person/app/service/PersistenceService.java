package org.java.server.faces.person.app.service;

import java.util.Set;

import org.java.server.faces.person.app.dao.PersonDAO;
import org.java.server.faces.person.app.model.Person;

public class PersistenceService {
	
	private static PersistenceService singleton;
	private PersonDAO personDAO;
	
	private PersistenceService() throws Exception{
		personDAO = new PersonDAO();
	}
	
	public synchronized static PersistenceService getInstance() throws Exception{
		return singleton == null?singleton=new PersistenceService():singleton;
	}
	
	public Set<Person> getPersons() throws Exception{
		return personDAO.getPersons();
	}
	
	public boolean addPerson(Person p)throws Exception{
		return personDAO.addPerson(p);
	}
	
	public boolean updatePerson(Person p)throws Exception{
		return personDAO.updatePerson(p);
	}
	
	public boolean deletePerson(int id)throws Exception{
		return personDAO.deletePerson(id);
	}
}
