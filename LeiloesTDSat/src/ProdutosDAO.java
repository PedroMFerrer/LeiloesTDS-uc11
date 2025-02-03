/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        conn = new conectaDAO().connectDB();

        String sql = "INSERT INTO produtos(nome, valor, status) VALUES "
                + "(?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());
            stmt.execute();

        } catch (Exception e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        try {
            conn = conectaDAO.connectDB();
            String sql = "SELECT id, nome, valor, status FROM produtos";
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        } finally {
            try {
                if (resultset != null) {
                    resultset.close();
                }
                if (prep != null) {
                    prep.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexões: " + ex.getMessage());
            }
        }

        return listagem;
    }

    public void venderProduto(int idProduto) {
        conn = conectaDAO.connectDB();
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Produto vendido com sucesso.");
            } else {
                System.out.println("Erro ao vender o produto.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        public ArrayList<ProdutosDTO> listarProdutosVendidos() {

        try {
            conn = conectaDAO.connectDB();
            String sql = "SELECT * FROM produtos WHERE status = 'vendido'";
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        } finally {
            try {
                if (resultset != null) {
                    resultset.close();
                }
                if (prep != null) {
                    prep.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexões: " + ex.getMessage());
            }
        }

        return listagem;
    }

}
