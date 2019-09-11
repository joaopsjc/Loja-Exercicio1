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
import model.Produto;

/**
 *
 * @author ice
 */
public class ProdutoDAO {
    
        private static ProdutoDAO instance = new ProdutoDAO();
        public static ProdutoDAO getInstance()
        {
            return instance;
        }
        public void updateValor(float valor, String nome) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.executeQuery("UPDATE `produto` SET `valor`= " + valor + " WHERE produto.nome = '" + nome + "'");
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
        public void updateEstado(String estado, String nome) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.execute("UPDATE `produto` SET `estado`= '" + estado + "' WHERE produto.nome = '" + nome + "'");

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
            
            List<Cliente> observadores = new ArrayList<>();
            List<String> idObservadores = new ArrayList<>();
                    
             while(rsClienteProduto.next())
            {
                idObservadores.add(rsClienteProduto.getInt("id_cliente")+"");
            }           
            for(String idObservador:idObservadores) 
            {
                String clienteId = idObservador;
                
                String queryCliente = "SELECT nome FROM `cliente` WHERE cliente.id = '" + clienteId + "'";
                ResultSet rsCliente = st.executeQuery(queryCliente);
                rsCliente.first();
                
                Cliente novoObservador = new Cliente(rsCliente.getString("nome"));
                observadores.add(novoObservador);
            }

            rsClienteProduto.close();
            rsProduto.close();
            return observadores;
        } catch (SQLException e) {
            throw e;
        } finally {

            closeResources(conn, st);
        }
    }

    public List<Produto> readAll() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            
            String query = "SELECT nome FROM `produto`";
            ResultSet rsProdutos = st.executeQuery(query);
            
            List<Produto> produtos = new ArrayList<>();
            while(rsProdutos.next())
            {
                String nomeProduto = rsProdutos.getString("nome");
                Produto novoProduto = new Produto(nomeProduto);
                produtos.add(novoProduto);
            }
            
            return produtos;
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
