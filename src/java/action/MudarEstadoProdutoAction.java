/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produto;

/**
 *
 * @author John
 */
public class MudarEstadoProdutoAction implements Action{
    public void execute (HttpServletRequest request, HttpServletResponse response)
            throws IOException, ClassNotFoundException
    {
        String nomeProduto = request.getParameter("textNome");
        String estadoProduto = request.getParameter("textEstado");
        
        if(nomeProduto.equals("") || estadoProduto.equals("")) {
        
                response.sendRedirect("OperacaoErro.jsp");
        
        } else {
            try{

                Produto produto = new Produto(nomeProduto,estadoProduto);
                produto.alterarEstado();
                response.sendRedirect("OperacaoSucesso.jsp");
            }
            catch(SQLException ex)
            {
                response.sendRedirect("OperacaoErro.jsp");
                ex.printStackTrace();
            }
        
        }
    }
}