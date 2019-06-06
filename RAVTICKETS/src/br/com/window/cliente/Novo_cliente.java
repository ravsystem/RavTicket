package br.com.window.cliente;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Novo_cliente extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Novo_cliente frame = new Novo_cliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Novo_cliente() {
		getContentPane().setFont(new Font("SansSerif", Font.PLAIN, 16));
		setBounds(100, 100, 1071, 540);
		
		JLabel lblX = new JLabel(" X");
		lblX.setBounds(1016, 0, 39, 39);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				Novo_cliente.this.dispose();
			}
		});
		lblX.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
		
		JLabel lblCadastroDeCliente = new JLabel("Cadastro de Cliente");
		lblCadastroDeCliente.setBounds(10, 11, 436, 57);
		lblCadastroDeCliente.setFont(new Font("SansSerif", Font.BOLD, 45));
		
		JLabel lblNewLabel = new JLabel("ID.:");
		lblNewLabel.setBounds(10, 79, 46, 24);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		textField = new JTextField();
		textField.setBounds(10, 114, 105, 27);
		textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		textField.setColumns(10);
		
		JLabel lblRazaoSocial = new JLabel("Razao Social.:");
		lblRazaoSocial.setBounds(10, 152, 123, 24);
		lblRazaoSocial.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 187, 436, 27);
		textField_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		textField_1.setColumns(10);
		
		JLabel lblNomeFantasia = new JLabel("Nome Fantasia.:");
		lblNomeFantasia.setBounds(10, 225, 142, 24);
		lblNomeFantasia.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 260, 436, 27);
		textField_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		textField_2.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone.:");
		lblTelefone.setBounds(10, 298, 89, 24);
		lblTelefone.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		textField_3 = new JTextField();
		textField_3.setBounds(10, 333, 228, 27);
		textField_3.setFont(new Font("SansSerif", Font.PLAIN, 16));
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("E-mail.:");
		lblNewLabel_1.setBounds(10, 371, 67, 24);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		textField_4 = new JTextField();
		textField_4.setBounds(10, 406, 436, 27);
		textField_4.setFont(new Font("SansSerif", Font.PLAIN, 16));
		textField_4.setColumns(10);
		
		JLabel lblDataDeCadastro = new JLabel("Data de Cadastro.:");
		lblDataDeCadastro.setBounds(248, 298, 161, 24);
		lblDataDeCadastro.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		textField_5 = new JTextField();
		textField_5.setBounds(248, 333, 198, 27);
		textField_5.setFont(new Font("SansSerif", Font.PLAIN, 16));
		textField_5.setColumns(10);
		getContentPane().setLayout(null);
		getContentPane().add(lblCadastroDeCliente);
		getContentPane().add(lblX);
		getContentPane().add(lblNewLabel);
		getContentPane().add(lblRazaoSocial);
		getContentPane().add(textField_1);
		getContentPane().add(lblNomeFantasia);
		getContentPane().add(textField_2);
		getContentPane().add(lblTelefone);
		getContentPane().add(textField_3);
		getContentPane().add(lblNewLabel_1);
		getContentPane().add(textField_4);
		getContentPane().add(lblDataDeCadastro);
		getContentPane().add(textField_5);
		getContentPane().add(textField);
		
		

	}

}
