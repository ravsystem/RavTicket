package br.com.window.cliente;

import java.awt.EventQueue;
<<<<<<< HEAD

import javax.swing.JInternalFrame;
import javax.swing.JList;
import java.awt.BorderLayout;

public class Listar_clientes extends JInternalFrame {
=======
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JList;

import br.com.model.Agente;
import br.com.model.Cliente;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Listar_clientes extends JInternalFrame {
	private JTable table;
	private JTextField textField;
>>>>>>> master

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Listar_clientes frame = new Listar_clientes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
<<<<<<< HEAD

=======
>>>>>>> master
	/**
	 * Create the frame.
	 */
	public Listar_clientes() {
		setBounds(100, 100, 450, 300);
<<<<<<< HEAD
		
		JList list = new JList();
		getContentPane().add(list, BorderLayout.NORTH);

	}

=======
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 414, 160);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"ID", "Nome Fantasia", "Raz\u00E3o social", "CPF/CNPJ"
			}
		));
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(335, 215, 89, 23);
		getContentPane().add(btnRemover);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(236, 215, 89, 23);
		getContentPane().add(btnEditar);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 159, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(176, 10, 89, 23);
		getContentPane().add(btnPesquisar);
		table.getColumnModel().getColumn(3).setMinWidth(14);
		
		/*
		JList <Cliente> clientes = new JList();
		ArrayList dados = new ArrayList();
		
		getContentPane().add(clientes, BorderLayout.CENTER);
		
		Cliente client = new Cliente();

		client.setId(clientes.get(i).getId());
		client.setFantasia(client.get(i).getFantasia());
		dados.add(client);
		*/
		}
>>>>>>> master
}
