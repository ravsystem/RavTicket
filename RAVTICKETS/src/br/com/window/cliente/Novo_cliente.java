package br.com.window.cliente;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import br.com.connection.Conexao;
import br.com.model.Agente;
import br.com.model.AgenteTableModel;
import br.com.model.Cliente;
import br.com.model.ClienteTableModel;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Novo_cliente extends JInternalFrame {
	private JTextField textField;
	private JTextField txt_razao;
	private JTextField txt_nfantasia;
	private JTextField txt_telefone;
	private JTextField txtEmail;
	private JTextField txt_dt_cadastro;
	private JTable tableCliente;
	private JTextField txtCpfCnpj;

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
		
		List<Cliente> clients = Conexao.listarClient();
		
		ArrayList dados = new ArrayList();
		
		for(int i = 0; i < clients.size(); i++) {
			
			Cliente client = new Cliente();
			client.setId(clients.get(i).getId());
			client.setFantasia(clients.get(i).getFantasia());
			client.setTelefone(clients.get(i).getTelefone());
			client.setData_cadastro(clients.get(i).getData_cadastro());
			dados.add(client);
		}
		
		ClienteTableModel modelo = new ClienteTableModel(dados);
		
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
		textField.setEditable(false);
		textField.setBounds(10, 114, 105, 27);
		textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		textField.setColumns(10);
		
		JLabel lblRazaoSocial = new JLabel("Razao Social.:");
		lblRazaoSocial.setBounds(10, 152, 123, 24);
		lblRazaoSocial.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		txt_razao = new JTextField();
		txt_razao.setBounds(10, 187, 436, 27);
		txt_razao.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txt_razao.setColumns(10);
		
		JLabel lblNomeFantasia = new JLabel("Nome Fantasia.:");
		lblNomeFantasia.setBounds(10, 225, 142, 24);
		lblNomeFantasia.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		txt_nfantasia = new JTextField();
		txt_nfantasia.setBounds(10, 260, 436, 27);
		txt_nfantasia.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txt_nfantasia.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone.:");
		lblTelefone.setBounds(10, 298, 89, 24);
		lblTelefone.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		txt_telefone = new JTextField();
		txt_telefone.setBounds(10, 333, 228, 27);
		txt_telefone.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txt_telefone.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("E-mail.:");
		lblNewLabel_1.setBounds(10, 371, 67, 24);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		txtEmail = new JTextField();
		txtEmail.setBounds(10, 406, 436, 27);
		txtEmail.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtEmail.setColumns(10);
		
		JLabel lblDataDeCadastro = new JLabel("Data de Cadastro.:");
		lblDataDeCadastro.setBounds(248, 298, 161, 24);
		lblDataDeCadastro.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		txt_dt_cadastro = new JTextField();
		txt_dt_cadastro.setEditable(false);
		txt_dt_cadastro.setBounds(248, 333, 198, 27);
		txt_dt_cadastro.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txt_dt_cadastro.setColumns(10);
		getContentPane().setLayout(null);
		getContentPane().add(lblCadastroDeCliente);
		getContentPane().add(lblX);
		getContentPane().add(lblNewLabel);
		getContentPane().add(lblRazaoSocial);
		getContentPane().add(txt_razao);
		getContentPane().add(lblNomeFantasia);
		getContentPane().add(txt_nfantasia);
		getContentPane().add(lblTelefone);
		getContentPane().add(txt_telefone);
		getContentPane().add(lblNewLabel_1);
		getContentPane().add(txtEmail);
		getContentPane().add(lblDataDeCadastro);
		getContentPane().add(txt_dt_cadastro);
		getContentPane().add(textField);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
					Cliente cliente = new Cliente();
					
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
					LocalDateTime atual = LocalDateTime.now();
					
					cliente.setCpf_cnpj(txtCpfCnpj.getText());
					cliente.setRazao(txt_razao.getText());
					cliente.setFantasia(txt_nfantasia.getText());
					cliente.setTelefone(txt_telefone.getText());
					cliente.setEmail(txtEmail.getText());
					cliente.setData_cadastro(String.valueOf(dtf.format(atual)));
					
					
					try {
					
					Conexao.guardar(cliente);
					
					}catch(NullPointerException f) {
						JOptionPane.showMessageDialog(null,"Ops.. Deve ter faltado preencher algo ai moral: \n" +f);
					}
					catch(Exception npe){
						JOptionPane.showMessageDialog(null, "Ops.. Erro ao gravar cliente: \n" +npe);
					}
					
					JOptionPane.showMessageDialog(null, "Cliente Salvo com Sucesso!");
					
					modelo.addCliente(cliente);
					tableCliente.getModel();
			}
		});
		btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnSalvar.setBounds(10, 466, 105, 33);
		getContentPane().add(btnSalvar);
		
		tableCliente = new JTable();
		
		tableCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCliente.setShowVerticalLines(true);
		tableCliente.setShowHorizontalLines(true);
		tableCliente.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tableCliente.setToolTipText("");
		tableCliente.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		tableCliente.setModel(modelo);
		tableCliente.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableCliente.getColumnModel().getColumn(0).setResizable(false);
		tableCliente.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableCliente.getColumnModel().getColumn(1).setResizable(false);
		tableCliente.getColumnModel().getColumn(2).setPreferredWidth(70);
		tableCliente.getColumnModel().getColumn(2).setResizable(false);
		tableCliente.getColumnModel().getColumn(3).setPreferredWidth(60);
		tableCliente.getColumnModel().getColumn(3).setResizable(false);
		
		tableCliente.setBounds(486, 59, 259, 414);
		
		JScrollPane scrollPane = new JScrollPane(tableCliente);
		scrollPane.setBounds(472, 59, 573, 407);
		getContentPane().add(scrollPane);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtCpfCnpj.setText("");
				txt_razao.setText("");
				txt_nfantasia.setText("");
				txt_telefone.setText("");
				txtEmail.setText("");
				txt_dt_cadastro.setText("");
				
			}
		});
		btnLimpar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnLimpar.setBounds(125, 466, 104, 33);
		getContentPane().add(btnLimpar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Novo_cliente.this.dispose();
			}
		});
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnVoltar.setBounds(237, 466, 105, 33);
		getContentPane().add(btnVoltar);
		
		JLabel lblCpfcnpj = new JLabel("CPF/CNPJ.:");
		lblCpfcnpj.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblCpfcnpj.setBounds(125, 79, 110, 24);
		getContentPane().add(lblCpfcnpj);
		
		txtCpfCnpj = new JTextField();
		txtCpfCnpj.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtCpfCnpj.setColumns(10);
		txtCpfCnpj.setBounds(125, 114, 321, 27);
		getContentPane().add(txtCpfCnpj);

	}
}
