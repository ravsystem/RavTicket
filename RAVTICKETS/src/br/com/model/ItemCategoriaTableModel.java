package br.com.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ItemCategoriaTableModel extends AbstractTableModel{

	private List<ItemCategoria> linhasItemCategorias;
	private String[] colunas;
	
	public ItemCategoriaTableModel(List<ItemCategoria> linhasItemCategorias) {
		
		this.linhasItemCategorias = new ArrayList<ItemCategoria>(linhasItemCategorias);
	}
	
	public int getRowCount() {
		 //Quantidade de linhas da tabela, aqui as linhas v�o ser do tamanho da lista(List).
        return linhasItemCategorias.size();
    }
	
    public int getColumnCount() {
    	//Quantidade de Colunas da tabela, no caso aqui s�o 4.
        return 2;
    }
    
    public String getColumnName(int columnIndex) {
    	 //Nome das colunas da JTable
        String colunas[] = {"Codigo", "Nome"};
        return colunas[columnIndex];
    }
    
    public Object getValueAt(int row, int column) {
    	//Retornar o valor da coluna column e da linha row da JTable.
    	ItemCategoria itemCategoria = linhasItemCategorias.get(row);
        if (column == 0) return itemCategoria.getId();
        else return itemCategoria.getNome();
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // Pega o registro referente a linha especificada.
    	ItemCategoria itemCategoria = linhasItemCategorias.get(rowIndex);
     
    	if (columnIndex == 0) itemCategoria.setId((Long)aValue);
        else if (columnIndex == 1) itemCategoria.setNome((String)aValue);
        else throw new IndexOutOfBoundsException("columnIndex out of bounds");
        	
        fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualiza��o da c�lula
    }

 // Retorna o s�cio referente a linha especificada
    public ItemCategoria getItemCategoria(int indiceLinha) {
        return linhasItemCategorias.get(indiceLinha);
    }
     
    // Adiciona o s�cio especificado ao modelo
    public void addItemCategoria(ItemCategoria itemCategoria) {
        // Adiciona o registro.
    	linhasItemCategorias.add(itemCategoria);
     
        // Pega a quantidade de registros e subtrai 1 para
        // achar o �ltimo �ndice. A subtra��o � necess�ria
        // porque os �ndices come�am em zero.
        int ultimoIndice = getRowCount() - 1;
     
        // Notifica a mudan�a.
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
     
    // Remove o s�cio da linha especificada.
    public void removeItemCategoria(int indiceLinha) {
        // Remove o registro.
    	linhasItemCategorias.remove(indiceLinha);
     
        // Notifica a mudan�a.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
     
    // Adiciona uma lista de s�cios no final da lista.
    public void addListaDeItemCategoria(List<ItemCategoria> itemCategorias) {
        // Pega o tamanho antigo da tabela, que servir�
        // como �ndice para o primeiro dos novos registros
        int indice = getRowCount();
     
        // Adiciona os registros.
        linhasItemCategorias.addAll(itemCategorias);
     
        // Notifica a mudan�a.
        fireTableRowsInserted(indice, indice + itemCategorias.size());
    }
     
    // Remove todos os registros.
    public void limpar() {
        // Remove todos os elementos da lista de s�cios.
    	linhasItemCategorias.clear();
     
        // Notifica a mudan�a.
        fireTableDataChanged();
    }
}
