package org.java.server.faces.person.app.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class JDBCUtils {
	
	private JDBCUtils(){}
	
	public static void close(Connection con) throws SQLException{
		if(con !=null)con.close();
	}
	
	
	public static void close(Connection con, PreparedStatement ps) throws SQLException{
		if(con !=null)con.close();
		if(ps !=null)ps.close();
	}
	
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) throws SQLException{
		if(con !=null)con.close();
		if(ps !=null)ps.close();
		if(rs !=null)rs.close();
	}
}
