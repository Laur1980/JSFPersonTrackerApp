package org.java.server.faces.person.app.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.java.server.faces.person.app.model.Person;
import org.java.server.faces.person.app.service.PersistenceService;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class PersonController {
	
	private Set<Person> persons;
	private Person person;
	private Logger logger;
	
	public PersonController(){
		persons = new HashSet<>();
		logger = Logger.getLogger(getClass().getName());
	}
	
	public void loadPersons(){
		logger.info("Loading students");
		persons.clear();
		try{
			persons = getPersons();
		}catch(Exception e){
			logger.log(Level.SEVERE,"Error loading persons",e);
			addErrorMessage(e);
		}
		
	}
	
	private void addErrorMessage(Exception e){
		FacesMessage fm = new FacesMessage("Error "+e.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}
	
	public Set<Person> getPersons() throws Exception{

		return persons = PersistenceService.getInstance().getPersons();
	
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
