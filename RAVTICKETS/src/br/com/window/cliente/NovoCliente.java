package br.com.window.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.com.connection.Conexao;
import br.com.model.Cliente;
import br.com.model.ClienteTableModel;
import br.com.window.agente.Novo_agente;
import br.com.window.categoria.NovaItemCategoria;

public class NovoCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txt_razao;
	private JTextField txt_nfantasia;
	private JTextField txt_telefone;
	private JTextField txtEmail;
	private JTextField txt_dt_cadastro;
	private JTable tableCliente;
	private JTextField txtCpfCnpj;
	private static int auxline;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovoCliente frame = new NovoCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NovoCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1071, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
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
		
		ImageIcon iconB = new ImageIcon(NovoCliente.class.getResource("/br/com/img/back.png"));
		Image imaB = iconB.getImage();
		Image imagemB = imaB.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		Icon icoB = new ImageIcon(imagemB);
		
		JLabel lblX = new JLabel(icoB);
		lblX.setBounds(999, 6, 46, 39);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				NovoCliente.this.dispose();
			}
		});
		lblX.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
		
		JLabel lblCadastroDeCliente = new JLabel("Cadastro de Cliente");
		lblCadastroDeCliente.setBounds(10, 11, 436, 57);
		lblCadastroDeCliente.setFont(new Font("SansSerif", Font.BOLD, 45));
		
		JLabel lblNewLabel = new JLabel("ID.:");
		lblNewLabel.setBounds(10, 79, 46, 24);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(10, 114, 105, 27);
		txtId.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtId.setColumns(10);
		
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
		contentPane.setLayout(null);
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
		getContentPane().add(txtId);
		
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
					
					if(cliente.getCpf_cnpj().equals("") || cliente.getCpf_cnpj().equals(null) || cliente.getRazao().equals("") || cliente.getRazao().equals(null) || cliente.getFantasia().equals("") || 
							cliente.getFantasia().equals(null) || cliente.getTelefone().equals("") || cliente.getTelefone().equals(null) || cliente.getEmail().equals("") || cliente.getEmail().equals(null) ||
							cliente.getData_cadastro().equals("") || cliente.getData_cadastro().equals("")) {
						
						JOptionPane.showMessageDialog(NovoCliente.this,"Para poder gravar um Cliente preencha todos os campos ativos!");
						
					}else {
					
					try {
					
					Conexao.guardar(cliente);
					
					}catch(NullPointerException f) {
						JOptionPane.showMessageDialog(NovoCliente.this,"Ops.. Deve ter faltado preencher algo ai moral: \n" +f);
					}
					catch(Exception npe){
						JOptionPane.showMessageDialog(NovoCliente.this, "Ops.. Erro ao gravar cliente: \n" +npe);
					}
					
					txtId.setText("");
					txtCpfCnpj.setText("");
					txt_razao.setText("");
					txt_nfantasia.setText("");
					txt_telefone.setText("");
					txtEmail.setText("");
					txt_dt_cadastro.setText("");
					
					modelo.addCliente(cliente);
					tableCliente.getModel();
					
					JOptionPane.showMessageDialog(NovoCliente.this, "Cliente Salvo com Sucesso!");
					
					}
			}
		});
		btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnSalvar.setBounds(10, 462, 89, 33);
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
		scrollPane.setBounds(472, 102, 573, 393);
		getContentPane().add(scrollPane);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtId.setText("");
				txtCpfCnpj.setText("");
				txt_razao.setText("");
				txt_nfantasia.setText("");
				txt_telefone.setText("");
				txtEmail.setText("");
				txt_dt_cadastro.setText("");
				
			}
		});
		btnLimpar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnLimpar.setBounds(111, 462, 105, 33);
		getContentPane().add(btnLimpar);
		
		ImageIcon iconEdit = new ImageIcon(NovoCliente.class.getResource("/br/com/img/edit.png"));
		Image imaEdit = iconEdit.getImage();
		Image imagemEdit = imaEdit.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		Icon icoEdit = new ImageIcon(imagemEdit);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NovoCliente.this.dispose();
			}
		});
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnVoltar.setBounds(228, 462, 89, 33);
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
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cliente clienteremovetable = new Cliente();
				Cliente cliente = new Cliente();
				
				String id = txtId.getText();
				
				if(id.equals("") || id.equals(null)) {
					
					JOptionPane.showMessageDialog(NovoCliente.this, "Selecione uma linha, depois clique no pequeno Lápis acima da tabela para poder editar!");
					
				}else {
				
					Long idaux = Long.valueOf(id);
					
					try {
					
						clienteremovetable = Conexao.selecionaCliente(idaux);
					
					}catch(NullPointerException f) {
						JOptionPane.showMessageDialog(NovoCliente.this,"Ops.. Deve ter faltado preencher algo ai moral: \n" +f);
					}
					catch(Exception npe){
						JOptionPane.showMessageDialog(NovoCliente.this, "Ops.. Erro ao selecionar Cliente para remover da tabela.. \n" +npe);
					}
					
					cliente.setId(idaux);
					cliente.setFantasia(txt_nfantasia.getText());
					cliente.setRazao(txt_razao.getText());
					cliente.setTelefone(txt_telefone.getText());
					cliente.setEmail(txtEmail.getText());
					cliente.setCpf_cnpj(txtCpfCnpj.getText());
					cliente.setData_cadastro(txt_dt_cadastro.getText());
					
					modelo.removeCliente(auxline);
					
					try {
					
						Conexao.alterar(cliente);
					
					}catch(NullPointerException f) {
						JOptionPane.showMessageDialog(NovoCliente.this,"Ops.. Deve ter faltado preencher algo ai moral: \n" +f);
					}
					catch(Exception npe){
						JOptionPane.showMessageDialog(NovoCliente.this, "Ops.. Erro ao alterar Cliente.. \n" +npe);
					}
					
					txtId.setText("");
					txt_nfantasia.setText("");
					txt_razao.setText("");
					txt_telefone.setText("");
					txtEmail.setText("");
					txtCpfCnpj.setText("");
					txt_dt_cadastro.setText("");
					
					modelo.addCliente(cliente);
					tableCliente.getModel();
					
					JOptionPane.showMessageDialog(NovoCliente.this, "Cliente atualizado com sucesso!");
				
				}
			}
		});
		btnAtualizar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnAtualizar.setBounds(329, 462, 117, 33);
		btnAtualizar.setVisible(false);
		getContentPane().add(btnAtualizar);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				btnAtualizar.setVisible(false);
				btnSalvar.setVisible(true);
				
				txtId.setText("");
				txtCpfCnpj.setText("");
				txt_razao.setText("");
				txt_nfantasia.setText("");
				txt_telefone.setText("");
				txtEmail.setText("");
				txt_dt_cadastro.setText("");
				
				btnNovo.setVisible(false);
			}
		});
		btnNovo.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnNovo.setBounds(472, 57, 89, 33);
		btnNovo.setVisible(false);
		contentPane.add(btnNovo);
		
		JButton btnEdit = new JButton(icoEdit);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cliente cliente = new Cliente();
				
				auxline = tableCliente.getSelectedRow();
				String id = tableCliente.getValueAt(auxline, 0).toString();
				
				if(id.equals("") || id.equals(null)) {
					
					JOptionPane.showMessageDialog(NovoCliente.this, "Selecione uma linha para poder editar!");
					
				}else {
					
					btnSalvar.setVisible(false);
					btnAtualizar.setVisible(true);
					btnNovo.setVisible(true);
				
					Long idaux = Long.valueOf(id);
					
					try {
					
						cliente = Conexao.selecionaCliente(idaux);
					
					}
					catch(Exception npe){
						JOptionPane.showMessageDialog(NovoCliente.this, "Erro ao selecionar Cliente: \n" +npe);
					}
					
					txtId.setText(String.valueOf(cliente.getId()));
					txt_nfantasia.setText(cliente.getFantasia());
					txt_razao.setText(cliente.getRazao());
					txt_telefone.setText(cliente.getTelefone());
					txtEmail.setText(cliente.getEmail());
					txtCpfCnpj.setText(cliente.getCpf_cnpj());
					txt_dt_cadastro.setText(cliente.getData_cadastro());
				}
			}
		});
		btnEdit.setBounds(999, 57, 46, 33);
		getContentPane().add(btnEdit);
		
		ImageIcon iconRe = new ImageIcon(NovoCliente.class.getResource("/br/com/img/delete.png"));
		Image imaRe = iconRe.getImage();
		Image imagemRe = imaRe.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		Icon icoRe = new ImageIcon(imagemRe);
		
		JButton btnRemove = new JButton(icoRe);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cliente cliente = new Cliente();
				
				auxline = tableCliente.getSelectedRow();
				String id = tableCliente.getValueAt(auxline, 0).toString();
				
				if(id.equals("") || id.equals(null)) {
					
					JOptionPane.showMessageDialog(NovoCliente.this, "Selecione uma linha para poder excluir!");
					
				}else {
				
					Long idaux = Long.valueOf(id);
					
					cliente = Conexao.selecionaCliente(idaux);
					
					if(JOptionPane.showConfirmDialog(NovoCliente.this, "Deseja mesmo excluir o cliente selecionado?") == 0) {
					
						try {
						
							Conexao.removeCliente(cliente);
							
						}
						catch(Exception npe){
							JOptionPane.showMessageDialog(NovoCliente.this, "Erro ao remover Cliente: \n" +npe);
						}
							
						modelo.removeCliente(auxline);
						
						txtId.setText("");
						txtCpfCnpj.setText("");
						txt_razao.setText("");
						txt_nfantasia.setText("");
						txt_telefone.setText("");
						txtEmail.setText("");
						txt_dt_cadastro.setText("");
						
						JOptionPane.showMessageDialog(NovoCliente.this, "Cliente excluído com sucesso!");
					}
				}	
			}
		});
		btnRemove.setBounds(941, 57, 46, 33);
		getContentPane().add(btnRemove);
		
	}
}
