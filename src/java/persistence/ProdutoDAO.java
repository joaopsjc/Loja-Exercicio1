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
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/**
 *
 * @author ice
 */
public class ProdutoDAO {

        public void updateValor(float valor, String nome) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.executeQuery("UPDATE `produto` SET `valor`= " + valor + " WHERE empresa.nome = '" + nome + "'");
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
        public void updateEstado(String estado) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
        
    public List<Cliente> readObservers(String nome) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            
            String queryProduto = "SELECT id FROM `produto` WHERE produto.nome = '" + nome + "'";
            ResultSet rsProduto = st.executeQuery(queryProduto);
            rsProduto.first();
            int idProduto = rsProduto.getInt("id");
            
            String queryClienteProduto = "SELECT id_cliente FROM `cliente_produto` WHERE cliente_produto.id_produto = '" + idProduto + "'";
            ResultSet rsClienteProduto = st.executeQuery(queryClienteProduto);
            
            List<Cliente> observadores = new ArrayList<Cliente>();
            
            while(rsClienteProduto.next())
            {
                int clienteId = rsClienteProduto.getInt("id_cliente");
                String queryCliente = "SELECT nome FROM `cliente` WHERE cliente.id = '" + clienteId + "'";
                ResultSet rsCliente = st.executeQuery(queryCliente);
                Cliente novoObservador = new Cliente(rsCliente.getString("nome"));
                observadores.add(novoObservador);
            }

            
            return observadores;
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    private void closeResources(Connection conn, Statement st) {
        try {
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }

        } catch (SQLException e) {

        }
    }
}
