package br.com.coldigogeladeiras.jdbcinterface;

import java.util.List;
import com.google.gson.JsonObject;
import br.com.coldigogeladeiras.modelo.Produto;

//DAO é um padrão de dados que onde tem a separação das regras de negócio e da separação do banco de dados

public interface ProdutoDAO{
    
    // Método para inserir um novo produto no banco de dados.
    // Recebe um objeto Produto e retorna um booleano indicando se a inserção foi bem-sucedida.
    public boolean inserir(Produto produto);
    
    // Método para buscar produtos por nome. 
    // Recebe uma string nome e retorna uma lista de JsonObject representando os produtos encontrados.
    public List<JsonObject> buscarPorNome(String nome);
    
    // Método para deletar um produto pelo seu ID.
    // Recebe um int id como parâmetro e retorna um booleano indicando se a deleção foi bem-sucedida.
    public boolean deletar(int id);
    
    // Método para buscar um produto específico pelo seu ID.
    // Recebe um int id como parâmetro e retorna um objeto Produto.
    public Produto buscarPorId(int id);
    
    // Método para alterar os dados de um produto existente.
    // Recebe um objeto Produto e retorna um booleano indicando se a alteração foi bem-sucedida.
    public boolean alterar(Produto produto);
    
}
