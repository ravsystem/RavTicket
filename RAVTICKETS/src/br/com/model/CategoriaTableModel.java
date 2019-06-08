package br.com.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CategoriaTableModel extends AbstractTableModel{
	
	private List<Categoria> linhasCategoria;
	private String[] colunas;
	
	public CategoriaTableModel(List<Categoria> categs) {
		
		this.linhasCategoria = new ArrayList<Categoria>(categs);
	}
	
	public int getRowCount() {
		 //Quantidade de linhas da tabela, aqui as linhas vão ser do tamanho da lista(List).
        return linhasCategoria.size();
    }
	
    public int getColumnCount() {
    	//Quantidade de Colunas da tabela, no caso aqui são 4.
        return 2;
    }
    
    public String getColumnName(int columnIndex) {
    	 //Nome das colunas da JTable
        String colunas[] = {"Codigo", "Descrição"};
        return colunas[columnIndex];
    }
    
    public Object getValueAt(int row, int column) {
    	//Retornar o valor da coluna column e da linha row da JTable.
        Categoria categ = linhasCategoria.get(row);
        if (column == 0) return categ.getId();
        else return categ.getDescr();
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // Pega o registro referente a linha especificada.
    	Categoria categ = linhasCategoria.get(rowIndex);
     
    	if (columnIndex == 0) categ.setId((Long)aValue);
        else if (columnIndex == 1) categ.setDescr((String)aValue);
        else throw new IndexOutOfBoundsException("columnIndex out of bounds");
        	
        fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
    }

 // Retorna o sócio referente a linha especificada
    public Categoria getCateg(int indiceLinha) {
        return linhasCategoria.get(indiceLinha);
    }
     
    // Adiciona o sócio especificado ao modelo
    public void addCateg(Categoria categoria) {
        // Adiciona o registro.
    	linhasCategoria.add(categoria);
     
        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;
     
        // Notifica a mudança.
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
     
    // Remove o sócio da linha especificada.
    public void removeCateg(int indiceLinha) {
        // Remove o registro.
    	linhasCategoria.remove(indiceLinha);
     
        // Notifica a mudança.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
     
    // Adiciona uma lista de sócios no final da lista.
    public void addListaDeCategs(List<Categoria> categs) {
        // Pega o tamanho antigo da tabela, que servirá
        // como índice para o primeiro dos novos registros
        int indice = getRowCount();
     
        // Adiciona os registros.
        linhasCategoria.addAll(categs);
     
        // Notifica a mudança.
        fireTableRowsInserted(indice, indice + categs.size());
    }
     
    // Remove todos os registros.
    public void limpar() {
        // Remove todos os elementos da lista de sócios.
    	linhasCategoria.clear();
     
        // Notifica a mudança.
        fireTableDataChanged();
    }

}
