package br.com.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ClienteTableModel extends AbstractTableModel{

	private List<Cliente> linhasClientes;
	private String[] colunas;
	
	public ClienteTableModel(List<Cliente> clients) {
		
		this.linhasClientes = new ArrayList<Cliente>(clients);
	}
	
	public int getRowCount() {
		 //Quantidade de linhas da tabela, aqui as linhas v�o ser do tamanho da lista(List).
        return linhasClientes.size();
    }
	
    public int getColumnCount() {
    	//Quantidade de Colunas da tabela, no caso aqui s�o 4.
        return 4;
    }
    
    public String getColumnName(int columnIndex) {
    	 //Nome das colunas da JTable
        String colunas[] = {"Codigo", "N. Fantasia", "Fone", "Data do Cadastro"};
        return colunas[columnIndex];
    }
    
    public Object getValueAt(int row, int column) {
    	//Retornar o valor da coluna column e da linha row da JTable.
        Cliente client = linhasClientes.get(row);
        if (column == 0) return client.getId();
        else if (column == 1) return client.getFantasia();
        else if (column == 2) return client.getTelefone();
        else return client.getData_cadastro();
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // Pega o s�cio referente a linha especificada.
    	Cliente client = linhasClientes.get(rowIndex);
     
    	if (columnIndex == 0) client.setId((Long)aValue);
        else if (columnIndex == 1) client.setFantasia((String)aValue);
        else if (columnIndex == 2) client.setTelefone((String)aValue);
        else if (columnIndex == 2) client.setData_cadastro((String)aValue);
        else throw new IndexOutOfBoundsException("columnIndex out of bounds");
        	
        fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualiza��o da c�lula
    }

 // Retorna o s�cio referente a linha especificada
    public Cliente getcliente(int indiceLinha) {
        return linhasClientes.get(indiceLinha);
    }
     
    // Adiciona o s�cio especificado ao modelo
    public void addCliente(Cliente cliente) {
        // Adiciona o registro.
    	linhasClientes.add(cliente);
     
        // Pega a quantidade de registros e subtrai 1 para
        // achar o �ltimo �ndice. A subtra��o � necess�ria
        // porque os �ndices come�am em zero.
        int ultimoIndice = getRowCount() - 1;
     
        // Notifica a mudan�a.
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
     
    // Remove o s�cio da linha especificada.
    public void removeCliente(int indiceLinha) {
        // Remove o registro.
    	linhasClientes.remove(indiceLinha);
     
        // Notifica a mudan�a.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
     
    // Adiciona uma lista de s�cios no final da lista.
    public void addListaDeClientes(List<Cliente> clientes) {
        // Pega o tamanho antigo da tabela, que servir�
        // como �ndice para o primeiro dos novos registros
        int indice = getRowCount();
     
        // Adiciona os registros.
        linhasClientes.addAll(clientes);
     
        // Notifica a mudan�a.
        fireTableRowsInserted(indice, indice + clientes.size());
    }
     
    // Remove todos os registros.
    public void limpar() {
        // Remove todos os elementos da lista de s�cios.
    	linhasClientes.clear();
     
        // Notifica a mudan�a.
        fireTableDataChanged();
    }
	
}
