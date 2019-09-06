/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Cliente;

/**
 *
 * @author ice
 */
public class ClienteDAO {
    private static ClienteDAO instance = new ClienteDAO();
    
    public static ClienteDAO getInstance()
    {
        return instance;
    }
    
    public Cliente read() throws SQLException,ClassNotFoundException
    {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            
            String query = "";
            
            ResultSet rs = st.executeQuery(query);
            
            rs.first();
            
            return new Cliente("nome");
            
        } catch (SQLException e) {
            throw e;
        } 
        finally
        {
            closeResources(conn,st);
        }
    }
    private void closeResources(Connection conn, Statement st) {
        try {
                    if(st!=null) st.close();
                    if(conn!=null) conn.close();
                    
                } catch(SQLException e) {
                    
                }
    }
}
