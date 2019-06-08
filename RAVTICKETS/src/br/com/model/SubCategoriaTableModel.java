package br.com.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class SubCategoriaTableModel extends AbstractTableModel{

	private List<SubCategoria> linhasSubCategorias;
	private String[] colunas;
	
	public SubCategoriaTableModel(List<SubCategoria> subCategorias) {
		
		this.linhasSubCategorias = new ArrayList<SubCategoria>(subCategorias);
	}
	
	public int getRowCount() {
		 //Quantidade de linhas da tabela, aqui as linhas vão ser do tamanho da lista(List).
        return linhasSubCategorias.size();
    }
	
    public int getColumnCount() {
    	//Quantidade de Colunas da tabela, no caso aqui são 4.
        return 2;
    }
    
    public String getColumnName(int columnIndex) {
    	 //Nome das colunas da JTable
        String colunas[] = {"Codigo", "Nome"};
        return colunas[columnIndex];
    }
    
    public Object getValueAt(int row, int column) {
    	//Retornar o valor da coluna column e da linha row da JTable.
    	SubCategoria subCategoria = linhasSubCategorias.get(row);
        if (column == 0) return subCategoria.getIn();
        else return subCategoria.getDescr();
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // Pega o registro referente a linha especificada.
    	SubCategoria subCategoria = linhasSubCategorias.get(rowIndex);
     
    	if (columnIndex == 0) subCategoria.setIn((Long)aValue);
        else if (columnIndex == 1) subCategoria.setDescr((String)aValue);
        else throw new IndexOutOfBoundsException("columnIndex out of bounds");
        	
        fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
    }

 // Retorna o sócio referente a linha especificada
    public SubCategoria getSubCategoria(int indiceLinha) {
        return linhasSubCategorias.get(indiceLinha);
    }
     
    // Adiciona o sócio especificado ao modelo
    public void addSubCategoria(SubCategoria subCategoria) {
        // Adiciona o registro.
    	linhasSubCategorias.add(subCategoria);
     
        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;
     
        // Notifica a mudança.
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
     
    // Remove o sócio da linha especificada.
    public void removeSubCategoria(int indiceLinha) {
        // Remove o registro.
    	linhasSubCategorias.remove(indiceLinha);
     
        // Notifica a mudança.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
     
    // Adiciona uma lista de sócios no final da lista.
    public void addListaDeSubCategorias(List<SubCategoria> subCategorias) {
        // Pega o tamanho antigo da tabela, que servirá
        // como índice para o primeiro dos novos registros
        int indice = getRowCount();
     
        // Adiciona os registros.
        linhasSubCategorias.addAll(subCategorias);
     
        // Notifica a mudança.
        fireTableRowsInserted(indice, indice + subCategorias.size());
    }
     
    // Remove todos os registros.
    public void limpar() {
        // Remove todos os elementos da lista de sócios.
    	linhasSubCategorias.clear();
     
        // Notifica a mudança.
        fireTableDataChanged();
    }
}
