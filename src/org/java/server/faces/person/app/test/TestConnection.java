package org.java.server.faces.person.app.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.java.server.faces.person.app.model.Person;
import org.junit.*;

public class TestConnection {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	@Before
	public void setUp() throws SQLException{
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chad?useSSL=false","demo","demo");
		 ps = con.prepareStatement("SELECT * FROM person");
		rs = ps.executeQuery();
	}
	
	
	@Test
	public void testConnection() throws SQLException{
		Set<Person> persoane = new HashSet<>();	
		while(rs.next()){
			Person temp = new Person();
			temp.setId(rs.getInt("person_id"));
			temp.setFirstName(rs.getString("first_name"));
			temp.setLastName(rs.getString("last_name"));
			temp.setEmail(rs.getString("email"));
		}
		assertTrue(!persoane.isEmpty());
	}
	
	@After
	public void tearDown() throws SQLException{
		con.close();
		ps.close();
		rs.close();
	}

	
}
