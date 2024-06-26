package br.com.coldigogeladeiras.jdbcinterface;

import java.util.List;
import com.google.gson.JsonObject;
import br.com.coldigogeladeiras.modelo.Marca;

// DAO é um padrão de dados que onde tem a separação das regras de negócio e da separação do banco de dados

public interface MarcaDAO {
    
    // Método para buscar todas as marcas no banco de dados.
    // Retorna uma lista de objetos Marca.
    public List<Marca> buscar();
    
    // Método para inserir uma nova marca no banco de dados.
    // Recebe um objeto Marca como parâmetro e retorna um booleano indicando sucesso ou falha.
    public boolean inserir(Marca marca);
    
    // Método para deletar uma marca pelo seu ID.
    // Recebe um int id como parâmetro e retorna um booleano indicando sucesso ou falha.
    public boolean deletar (int id);
    
    // Método para buscar uma marca específica pelo seu ID.
    // Recebe um int id como parâmetro e retorna um objeto Marca.
    public Marca buscarPorId(int id);
    
    // Método para alterar os dados de uma marca existente.
    // Recebe um objeto Marca como parâmetro e retorna um booleano indicando sucesso ou falha.
    public boolean alterar(Marca marca);
    
    // Método para verificar se existem produtos associados a uma marca.
    // Recebe um int id da marca como parâmetro e retorna um booleano indicando se existem produtos vinculados.
    public boolean verificaProduto (int id);
    
    // Método para alterar o status de uma marca (ativo/inativo).
    // Recebe um int id da marca como parâmetro e retorna um int indicando o novo status.
    public int alterarStatus(int id);
    
    // Método para verificar se uma marca já está cadastrada pelo seu nome.
    // Recebe uma String nome como parâmetro e retorna um booleano indicando se a marca já está registrada.
    public boolean verificaMarcaCadastrada(String nome);
}
