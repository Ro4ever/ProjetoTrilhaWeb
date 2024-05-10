package br.com.coldigogeladeiras.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import br.com.coldigogeladeiras.jdbcinterface.ProdutoDAO;
import br.com.coldigogeladeiras.modelo.Produto;
import com.google.gson.JsonObject;

// DAO é um padrão de dados que onde tem a separação das regras de negócio e da separação do banco de dados
// JDBC é como se fosse uma "API", que contém classes e interfaces que faz acomunicação entre o Java e o banco de dados

public class JDBCProdutoDAO implements ProdutoDAO{
    
    private Connection conexao;
    
    public JDBCProdutoDAO(Connection conexao) {
        // Construtor que inicializa a conexão com o banco de dados
        this.conexao = conexao;
    }
    
    public boolean inserir(Produto produto) {
        
        // Comando SQL para inserção de um novo produto no banco de dados
        String comando = "INSERT INTO produtos"
                + "(id, categoria, modelo, capacidade, valor, marcas_id)"
                + "VALUE (?,?,?,?,?,?)";
        PreparedStatement p;
        
        try {
            // Preparando o comando SQL para execução
            p = this.conexao.prepareStatement(comando);
            
            // Substituindo os "?" por valores específicos do produto
            p.setInt(1, produto.getId());
            p.setString(2, produto.getCategoria());
            p.setString(3, produto.getModelo());
            p.setInt(4, produto.getCapacidade());
            p.setFloat(5, produto.getValor());
            p.setInt(6, produto.getMarcaId());
            
            // Executando o comando SQL no banco de dados
            p.execute();
            
        }catch (SQLException e) {
            // Tratamento de exceção e retorno de false em caso de erro
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public List<JsonObject> buscarPorNome(String nome){
        
        // Comando SQL para buscar produtos pelo nome
        String comando = "SELECT produtos.*, marcas.nome as marca FROM produtos "
                + "INNER JOIN marcas ON produtos.marcas_id = marcas.id ";
        // Verifica se o nome não está vazio
        if(!nome.equals("")) {
            // Adiciona condição WHERE ao comando para buscar por nome
            comando += "WHERE modelo LIKE '%" + nome + "%' ";
        }
        // Ordena os resultados da busca
        comando += "ORDER BY categoria ASC, marcas.nome ASC, modelo ASC";
        
        List<JsonObject> listaProdutos = new ArrayList<JsonObject>();
        JsonObject produto = null;
        
        try {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(comando);
            
            while(rs.next()) {
                
                int id = rs.getInt("id");
                String categoria = rs.getString("categoria");
                String modelo = rs.getString("modelo");
                int capacidade = rs.getInt("capacidade");
                float valor = rs.getFloat("valor");
                String marcaNome = rs.getString("marca");
                
                if(categoria.equals("1")) {
                    categoria = "Geladeira";
                }else if(categoria.equals("2")) {
                    categoria = "Freezer";
                }
                
                produto = new JsonObject();
                produto.addProperty("id", id);
                produto.addProperty("categoria", categoria);
                produto.addProperty("modelo", modelo);
                produto.addProperty("capacidade", capacidade);
                produto.addProperty("valor", valor);
                produto.addProperty("marcaNome", marcaNome);
                
                listaProdutos.add(produto);
                
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return listaProdutos;
    }
    
    public boolean deletar(int id) {
        // Comando SQL para deletar um produto pelo ID
        String comando = "DELETE FROM produtos WHERE id = ? ";
        PreparedStatement p;
        try {
            // Preparando o comando para execução
            p = this.conexao.prepareStatement(comando);
            p.setInt(1, id);
            p.execute();
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public Produto buscarPorId(int id) {
        // Comando SQL para buscar um produto pelo ID
        String comando = "SELECT * FROM produtos WHERE produtos.id = ?";
        Produto produto = new Produto();
        try {
            PreparedStatement p = this.conexao.prepareStatement(comando);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            while(rs.next()) {
                
                String categoria = rs.getString("categoria");
                String modelo = rs.getString("modelo");
                int capacidade = rs.getInt("capacidade");
                float valor = rs.getFloat("valor");
                int marcaId = rs.getInt("marcas_id");
                
                produto.setId(id);
                produto.setCategoria(categoria);
                produto.setMarcaId(marcaId);
                produto.setModelo(modelo);
                produto.setCapacidade(capacidade);
                produto.setValor(valor);
                
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return produto;
    }
    public boolean alterar(Produto produto) {
        
        // Comando SQL para atualizar os dados de um produto
        String comando = "UPDATE produtos "
                + "SET categoria=?, modelo=?, capacidade=?, valor=?, marcas_id=?"
                + " WHERE id=?";
        PreparedStatement p;
        
        try {
            // Preparando o comando com novos valores para o produto especificado pelo ID
            p = this.conexao.prepareStatement(comando);
            p.setString(1, produto.getCategoria());
            p.setString(2, produto.getModelo());
            p.setInt(3, produto.getCapacidade());
            p.setFloat(4, produto.getValor());
            p.setInt(5,  produto.getMarcaId());
            p.setInt(6,  produto.getId());
            p.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
