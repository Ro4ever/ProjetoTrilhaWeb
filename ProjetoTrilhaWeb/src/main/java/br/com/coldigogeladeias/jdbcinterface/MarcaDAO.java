package br.com.coldigogeladeias.jdbcinterface;

import java.util.List;
import br.com.coldigogeladeiras.modelo.Marca;

public interface MarcaDAO {
	
	public List<Marca> buscar();
}
