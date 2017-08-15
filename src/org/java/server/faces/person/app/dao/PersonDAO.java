package org.java.server.faces.person.app.dao;

import static org.java.server.faces.person.app.util.JDBCUtils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.java.server.faces.person.app.model.Person;

public class PersonDAO {
	
	private DataSource data;
	private String jndiName;
	
	public PersonDAO() throws Exception{
		jndiName="java:comp/env/jdbc/chad";
		data = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context= new InitialContext();
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		return theDataSource;
	}
	
	public boolean addPerson(Person p) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			con = data.getConnection();
			ps = con.prepareStatement("INSERT INTO person VALUES(NULL,?,?,?)");
			ps.setString(1, p.getFirstName());
			ps.setString(2, p.getLastName());
			ps.setString(3, p.getEmail());
			return ps.executeUpdate()==1? true: false;
		}finally{
			close(con,ps);
		}
	}
	
	private Person getThePerson(int id)throws SQLException{
			Connection con = data.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM person WHERE person_id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Person p = null;
			if(rs.next()){
				p = new Person();
				p.setId(id);
				p.setFirstName(rs.getString("first_name"));
				p.setLastName(rs.getString("last_name"));
				p.setEmail(rs.getString("email"));
			}
			return p;
	}
	
	public Person getPerson(int id)throws SQLException{
		return getThePerson(id);
	}
	
	public boolean updatePerson(Person p) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;	
		try{
			con = data.getConnection();
			ps = con.prepareStatement("UPDATE person "
										+ "SET first_name=?, last_name=?, email=? "
										+ "WHERE person_id=?");
			ps.setString(1, p.getFirstName());
			ps.setString(2, p.getLastName());
			ps.setString(3, p.getEmail());
			ps.setInt(4, p.getId());
			return ps.executeUpdate()==1?true:false;
		}finally{
			close(con,ps);
		}
	}
	
	public boolean deletePerson(int id)throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = ps.getConnection();
			ps= con.prepareStatement("DELETE FROM person WHERE person_id=?");
			ps.setInt(1, id);
			return ps.executeUpdate()==1?true:false;
		}finally{
			close(con,ps);
		}
	}
	
	public Set<Person> getPersons()throws Exception{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<Person> persons = new HashSet<>();
		try{
			con = data.getConnection();
			ps = con.prepareStatement("SELECT * FROM person ORDER BY last_name");
			rs = ps.executeQuery();
			while(rs.next()){
				Person p = new Person();
				p.setId(rs.getInt("person_id"));
				p.setFirstName(rs.getString("first_name"));
				p.setLastName(rs.getString("last_name"));
				p.setEmail(rs.getString("email"));
				persons.add(p);
			}
			return persons;
		}finally{
			close(con,ps,rs);
		}
	}
}
