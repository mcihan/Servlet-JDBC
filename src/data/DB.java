/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.*;

public class DB 
{
    String cS= "jdbc:postgresql://localhost:5432/DENEME";
    Connection con;
    
    public DB() throws Exception
    {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection(cS,"postgres","123");
    }
    
    public ResultSet getResult(String sql) throws Exception 
    {
        return con.createStatement().executeQuery(sql);
    }
    
    public void query(String sql) throws Exception
    { 
    	 con.createStatement().execute(sql); 
    }
}
