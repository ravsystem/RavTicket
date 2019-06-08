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
		 //Quantidade de linhas da tabela, aqui as linhas v�o ser do tamanho da lista(List).
        return linhasCategoria.size();
    }
	
    public int getColumnCount() {
    	//Quantidade de Colunas da tabela, no caso aqui s�o 4.
        return 2;
    }
    
    public String getColumnName(int columnIndex) {
    	 //Nome das colunas da JTable
        String colunas[] = {"Codigo", "Descri��o"};
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
        	
        fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualiza��o da c�lula
    }

 // Retorna o s�cio referente a linha especificada
    public Categoria getCateg(int indiceLinha) {
        return linhasCategoria.get(indiceLinha);
    }
     
    // Adiciona o s�cio especificado ao modelo
    public void addCateg(Categoria categoria) {
        // Adiciona o registro.
    	linhasCategoria.add(categoria);
     
        // Pega a quantidade de registros e subtrai 1 para
        // achar o �ltimo �ndice. A subtra��o � necess�ria
        // porque os �ndices come�am em zero.
        int ultimoIndice = getRowCount() - 1;
     
        // Notifica a mudan�a.
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
     
    // Remove o s�cio da linha especificada.
    public void removeCateg(int indiceLinha) {
        // Remove o registro.
    	linhasCategoria.remove(indiceLinha);
     
        // Notifica a mudan�a.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
     
    // Adiciona uma lista de s�cios no final da lista.
    public void addListaDeCategs(List<Categoria> categs) {
        // Pega o tamanho antigo da tabela, que servir�
        // como �ndice para o primeiro dos novos registros
        int indice = getRowCount();
     
        // Adiciona os registros.
        linhasCategoria.addAll(categs);
     
        // Notifica a mudan�a.
        fireTableRowsInserted(indice, indice + categs.size());
    }
     
    // Remove todos os registros.
    public void limpar() {
        // Remove todos os elementos da lista de s�cios.
    	linhasCategoria.clear();
     
        // Notifica a mudan�a.
        fireTableDataChanged();
    }

}
