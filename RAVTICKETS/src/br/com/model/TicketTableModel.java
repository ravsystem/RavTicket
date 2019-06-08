package br.com.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TicketTableModel extends AbstractTableModel{

	private List<Ticket> linhasTickets;
	private String[] colunas;
	
	public TicketTableModel(List<Ticket> linhasTickets) {
		
		this.linhasTickets = new ArrayList<Ticket>(linhasTickets);
	}
	
	public int getRowCount() {
		 //Quantidade de linhas da tabela, aqui as linhas v�o ser do tamanho da lista(List).
        return linhasTickets.size();
    }
	
    public int getColumnCount() {
    	//Quantidade de Colunas da tabela, no caso aqui s�o 4.
        return 3;
    }
    
    public String getColumnName(int columnIndex) {
    	 //Nome das colunas da JTable
        String colunas[] = {"Codigo", "Nome", "Tipo"};
        return colunas[columnIndex];
    }
    
    public Object getValueAt(int row, int column) {
    	//Retornar o valor da coluna column e da linha row da JTable.
    	Ticket ticket = linhasTickets.get(row);
        if (column == 0) return ticket.getId();
        else if (column == 1) return ticket.getTitulo();
        else if (column == 2) return ticket.getCliente();
        else return ticket.getAgente();
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // Pega o registro referente a linha especificada.
    	Ticket ticket = linhasTickets.get(rowIndex);
     
    	if (columnIndex == 0) ticket.setId((Long)aValue);
        else if (columnIndex == 1) ticket.setTitulo((String)aValue);
        else if (columnIndex == 2) ticket.setCliente((String)aValue);
        else if (columnIndex == 3) ticket.setAgente((String)aValue);
        else throw new IndexOutOfBoundsException("columnIndex out of bounds");
        	
        fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualiza��o da c�lula
    }

 // Retorna o s�cio referente a linha especificada
    public Ticket getTicket(int indiceLinha) {
        return linhasTickets.get(indiceLinha);
    }
     
    // Adiciona o s�cio especificado ao modelo
    public void addTicket(Ticket ticket) {
        // Adiciona o registro.
    	linhasTickets.add(ticket);
     
        // Pega a quantidade de registros e subtrai 1 para
        // achar o �ltimo �ndice. A subtra��o � necess�ria
        // porque os �ndices come�am em zero.
        int ultimoIndice = getRowCount() - 1;
     
        // Notifica a mudan�a.
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
     
    // Remove o s�cio da linha especificada.
    public void removeTicket(int indiceLinha) {
        // Remove o registro.
    	linhasTickets.remove(indiceLinha);
     
        // Notifica a mudan�a.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
     
    // Adiciona uma lista de s�cios no final da lista.
    public void addListaDeTicket(List<Ticket> tickets) {
        // Pega o tamanho antigo da tabela, que servir�
        // como �ndice para o primeiro dos novos registros
        int indice = getRowCount();
     
        // Adiciona os registros.
        linhasTickets.addAll(tickets);
     
        // Notifica a mudan�a.
        fireTableRowsInserted(indice, indice + tickets.size());
    }
     
    // Remove todos os registros.
    public void limpar() {
        // Remove todos os elementos da lista de s�cios.
    	linhasTickets.clear();
     
        // Notifica a mudan�a.
        fireTableDataChanged();
    }
}
