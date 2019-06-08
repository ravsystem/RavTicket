package br.com.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AgenteTableModel extends AbstractTableModel{
	
	private List<Agente> linhasAgentes;
	private String[] colunas;
	
	public AgenteTableModel(List<Agente> agents) {
		
		this.linhasAgentes = new ArrayList<Agente>(agents);
	}
	
	public int getRowCount() {
		 //Quantidade de linhas da tabela, aqui as linhas vão ser do tamanho da lista(List).
        return linhasAgentes.size();
    }
	
    public int getColumnCount() {
    	//Quantidade de Colunas da tabela, no caso aqui são 4.
        return 3;
    }
    
    public String getColumnName(int columnIndex) {
    	 //Nome das colunas da JTable
        String colunas[] = {"Codigo", "Nome", "Tipo"};
        return colunas[columnIndex];
    }
    
    public Object getValueAt(int row, int column) {
    	//Retornar o valor da coluna column e da linha row da JTable.
        Agente agent = linhasAgentes.get(row);
        if (column == 0) return agent.getId();
        else if (column == 1) return agent.getNome();
        else return agent.getTipo();
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // Pega o sócio referente a linha especificada.
    	Agente agent = linhasAgentes.get(rowIndex);
     
    	if (columnIndex == 0) agent.setId((Long)aValue);
        else if (columnIndex == 1) agent.setNome((String)aValue);
        else if (columnIndex == 2) agent.setTipo((String)aValue);
        else throw new IndexOutOfBoundsException("columnIndex out of bounds");
        	
        fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
    }

 // Retorna o sócio referente a linha especificada
    public Agente getAgente(int indiceLinha) {
        return linhasAgentes.get(indiceLinha);
    }
     
    // Adiciona o sócio especificado ao modelo
    public void addProduto(Agente agente) {
        // Adiciona o registro.
    	linhasAgentes.add(agente);
     
        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;
     
        // Notifica a mudança.
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
     
    // Remove o sócio da linha especificada.
    public void removeSocio(int indiceLinha) {
        // Remove o registro.
    	linhasAgentes.remove(indiceLinha);
     
        // Notifica a mudança.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
     
    // Adiciona uma lista de sócios no final da lista.
    public void addListaDeSocios(List<Agente> produtos) {
        // Pega o tamanho antigo da tabela, que servirá
        // como índice para o primeiro dos novos registros
        int indice = getRowCount();
     
        // Adiciona os registros.
        linhasAgentes.addAll(produtos);
     
        // Notifica a mudança.
        fireTableRowsInserted(indice, indice + produtos.size());
    }
     
    // Remove todos os registros.
    public void limpar() {
        // Remove todos os elementos da lista de sócios.
    	linhasAgentes.clear();
     
        // Notifica a mudança.
        fireTableDataChanged();
    }

}
