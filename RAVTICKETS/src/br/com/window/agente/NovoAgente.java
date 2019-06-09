package br.com.window.agente;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;

import br.com.connection.Conexao;
import br.com.model.Agente;
import br.com.model.AgenteTableModel;

public class NovoAgente extends JFrame {

	private JPanel contentPane;
	private JTextField txt_usuario;
	private JPasswordField txt_senha;
	private String[] tipos_agente = {"ADM","N1","N2"};
	private JTable table;
	private JTextField txtId;
	private static Integer auxline;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovoAgente frame = new NovoAgente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NovoAgente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		List<Agente> produts = Conexao.listarAgent();
		
		ArrayList dados = new ArrayList();
		
		for(int i = 0; i < produts.size(); i++) {
			
			Agente agent = new Agente();
			agent.setId(produts.get(i).getId());
			agent.setNome(produts.get(i).getNome());
			agent.setTipo(produts.get(i).getTipo());
			dados.add(agent);
		}
		
		AgenteTableModel modelo = new AgenteTableModel(dados);
		
		JLabel lblCadastrarNovoAgente = new JLabel("Cadastrar novo Agente");
		lblCadastrarNovoAgente.setBounds(10, 11, 510, 59);
		lblCadastrarNovoAgente.setFont(new Font("SansSerif", Font.BOLD, 45));
		
		JLabel lblNome = new JLabel("Nome.:");
		lblNome.setBounds(10, 90, 67, 24);
		lblNome.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JLabel lblSenha = new JLabel("Senha.:");
		lblSenha.setBounds(10, 128, 67, 24);
		lblSenha.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JLabel lblNewLabel = new JLabel("Tipo.:");
		lblNewLabel.setBounds(10, 166, 67, 24);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		txt_usuario = new JTextField();
		txt_usuario.setBounds(81, 89, 136, 27);
		txt_usuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txt_usuario.setColumns(10);
		
		txt_senha = new JPasswordField();
		txt_senha.setBounds(81, 127, 136, 27);
		txt_senha.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JComboBox cb_tipo_usuario = new JComboBox(tipos_agente);
		cb_tipo_usuario.setBounds(81, 165, 136, 27);
		cb_tipo_usuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(10, 376, 95, 33);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				if(txt_usuario.getText().equals("") || txt_senha.getText().equals("")) {
					
					JOptionPane.showMessageDialog(NovoAgente.this, "Por favor Cidad�o, tenha vergonha na cara e preencha o usu�rio e senha para poder salvar!!");
					
				}else {
					
					String usuario = txt_usuario.getText();
					String senha = txt_senha.getText();
					String tipo = cb_tipo_usuario.getSelectedItem().toString();
					
					if(tipo.equals("ADM")) {
						Agente agente = new Agente();
						agente.setNome(usuario);
						agente.setSenha(senha);
						agente.setTipo(tipo);
						
						try {
						
						Conexao.guardar(agente);
						
						}catch(NullPointerException f) {
							JOptionPane.showMessageDialog(NovoAgente.this,"Ops.. Deve ter faltado preencher algo ai moral: \n" +f);
						}
						catch(Exception npe){
							JOptionPane.showMessageDialog(NovoAgente.this, "Ops.. Erro ao gravar Agente: \n" +npe);
						}
						
						modelo.addAgente(agente);
						table.getModel();
						
						txt_usuario.setText("");
						txt_senha.setText("");
						cb_tipo_usuario.setSelectedIndex(0);
						txtId.setText("");
						
						JOptionPane.showMessageDialog(NovoAgente.this, "Agente Salvo com Sucesso!");
						
					}else if(tipo.equals("N2")) {
						Agente n2 = new Agente();
						n2.setNome(usuario);
						n2.setSenha(senha);
						n2.setTipo(tipo);
						
						try {
						
							Conexao.guardar(n2);
						
						
						}catch(NullPointerException f) {
							JOptionPane.showMessageDialog(NovoAgente.this,"Ops.. Deve ter faltado preencher algo ai moral: \n" +f);
						}
						catch(Exception npe){
							JOptionPane.showMessageDialog(NovoAgente.this, "Ops.. Erro ao gravar Agente: \n" +npe);
						}
						
						modelo.addAgente(n2);
						table.getModel();
						
						txt_usuario.setText("");
						txt_senha.setText("");
						cb_tipo_usuario.setSelectedIndex(0);
						txtId.setText("");
						
						JOptionPane.showMessageDialog(NovoAgente.this, "Usu�rio Salvo com Sucesso!");
					}else {
						
						Agente n1 = new Agente();
						n1.setNome(usuario);
						n1.setSenha(senha);
						n1.setTipo(tipo);
						
						try {
							Conexao.guardar(n1);
						
						}catch(NullPointerException f) {
							JOptionPane.showMessageDialog(NovoAgente.this,"Ops.. Deve ter faltado preencher algo ai moral: \n" +f);
						}
						catch(Exception npe){
							JOptionPane.showMessageDialog(NovoAgente.this, "Ops.. Erro ao gravar Agente: \n" +npe);
						}
						
						modelo.addAgente(n1);
						table.getModel();
						
						txt_usuario.setText("");
						txt_senha.setText("");
						cb_tipo_usuario.setSelectedIndex(0);
						txtId.setText("");
						
						JOptionPane.showMessageDialog(NovoAgente.this, "Usu�rio Salvo com Sucesso!");
					}
					
					
					
					txt_usuario.setText("");
					txt_senha.setText("");
					cb_tipo_usuario.setSelectedIndex(0);
					txtId.setText("");
					
					}
				
			}
		});
		btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(117, 376, 100, 33);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txt_usuario.setText("");
				txt_senha.setText("");
				cb_tipo_usuario.setSelectedIndex(0);
				txtId.setText("");
			}
		});
		btnLimpar.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(229, 376, 100, 33);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NovoAgente.this.dispose();
			}
			
		});
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		ImageIcon iconB = new ImageIcon(NovoAgente.class.getResource("/br/com/img/back.png"));
		Image imaB = iconB.getImage();
		Image imagemB = imaB.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		Icon icoB = new ImageIcon(imagemB);
		
		JLabel lblX = new JLabel(icoB);
		lblX.setBounds(875, 4, 49, 27);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				NovoAgente.this.dispose();
			}
		});
		lblX.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
		
		table = new JTable();
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table.setToolTipText("");
		table.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.setBounds(513, 76, 187, 333);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(462, 89, 462, 320);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		getContentPane().add(lblCadastrarNovoAgente);
		getContentPane().add(lblX);
		getContentPane().add(lblNome);
		getContentPane().add(lblSenha);
		getContentPane().add(lblNewLabel);
		getContentPane().add(cb_tipo_usuario);
		getContentPane().add(txt_senha);
		getContentPane().add(txt_usuario);
		getContentPane().add(scrollPane);
		getContentPane().add(btnSalvar);
		getContentPane().add(btnLimpar);
		getContentPane().add(btnVoltar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Agente agenteremovetable = new Agente();
				Agente agente = new Agente();
				
				String id = txtId.getText();
				
				if(id.equals("") || id.equals(null)) {
					
					JOptionPane.showMessageDialog(NovoAgente.this, "Selecione uma linha, depois clique no pequeno L�pis acima da tabela para poder editar!");
					
				}else {
				
					Long idaux = Long.valueOf(id);
				
					try {
					agenteremovetable = Conexao.selecionaAgente(idaux);
					}catch(NullPointerException f) {
						JOptionPane.showMessageDialog(NovoAgente.this,"Erro ao Listar Agentes: \n" +f);
					}
					catch(Exception npe){
						JOptionPane.showMessageDialog(NovoAgente.this, "Erro ao Listar Agentes: \n" +npe);
					}
					agente.setId(idaux);
					agente.setNome(txt_usuario.getText());
					agente.setSenha(txt_senha.getText());
					agente.setTipo(cb_tipo_usuario.getSelectedItem().toString());
					
					
					modelo.removeAgente(auxline);
					
					try {
						
						Conexao.alterar(agente);
					
					}
					catch(Exception npe){
						JOptionPane.showMessageDialog(NovoAgente.this, "Erro ao alterar Agente: \n" +npe);
					}
					
					txt_usuario.setText("");
					txt_senha.setText("");
					cb_tipo_usuario.setSelectedIndex(0);
					txtId.setText("");
					
					modelo.addAgente(agente);
					table.getModel();
					
					JOptionPane.showMessageDialog(NovoAgente.this, "Agente Alterado com sucesso!!");
					
				}
			}
		});
		btnAtualizar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnAtualizar.setBounds(341, 376, 112, 33);
		btnAtualizar.setVisible(false);
		getContentPane().add(btnAtualizar);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSalvar.setVisible(true);
				btnAtualizar.setVisible(false);
				
				txt_usuario.setText("");
				txt_senha.setText("");
				cb_tipo_usuario.setSelectedIndex(0);
				txtId.setText("");
				
				btnNovo.setVisible(false);
			}
		});
		btnNovo.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnNovo.setBounds(10, 202, 95, 33);
		btnNovo.setVisible(false);
		getContentPane().add(btnNovo);
		
		ImageIcon iconEdit = new ImageIcon(NovoAgente.class.getResource("/br/com/img/edit.png"));
		Image imaEdit = iconEdit.getImage();
		Image imagemEdit = imaEdit.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		Icon icoEdit = new ImageIcon(imagemEdit);
		
		JButton btnNewButton = new JButton(icoEdit);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				
				Agente agente = new Agente();
				
				auxline = table.getSelectedRow();
				String id = table.getValueAt(auxline, 0).toString();
				
				if(id.equals("") || id.equals(null)) {
					
					JOptionPane.showMessageDialog(NovoAgente.this, "Selecione uma linha para poder editar!");
					
				}else {
					
					btnSalvar.setVisible(false);
					btnAtualizar.setVisible(true);
					btnNovo.setVisible(true);
				
					Long idaux = Long.valueOf(id);
					
					try {
					
					agente = Conexao.selecionaAgente(idaux);
					
					}
					catch(Exception npe){
						JOptionPane.showMessageDialog(NovoAgente.this, "Erro ao listar Agentes: \n" +npe);
					}
					
					txtId.setText(String.valueOf(agente.getId()));
					txt_usuario.setText(agente.getNome());
					txt_senha.setText(agente.getSenha());
					cb_tipo_usuario.setSelectedItem(agente.getTipo());
				}
			}
		});
		btnNewButton.setBounds(875, 43, 49, 33);
		getContentPane().add(btnNewButton);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setVisible(false);
		txtId.setBounds(743, 43, 59, 34);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		ImageIcon iconRe = new ImageIcon(NovoAgente.class.getResource("/br/com/img/delete.png"));
		Image imaRe = iconRe.getImage();
		Image imagemRe = imaRe.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		Icon icoRe = new ImageIcon(imagemRe);
		
		JButton btnRemove = new JButton(icoRe);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Agente agente = new Agente();
				
				auxline = table.getSelectedRow();
				String id = table.getValueAt(auxline, 0).toString();
				
				if(id.equals("") || id.equals(null)) {
					
					JOptionPane.showMessageDialog(NovoAgente.this, "Selecione uma linha para poder excluir!");
					
				}else {
				
					Long idaux = Long.valueOf(id);
					
					agente = Conexao.selecionaAgente(idaux);
					
					if(JOptionPane.showConfirmDialog(NovoAgente.this, "Deseja mesmo excluir o Agente selecionado?") == 0) {
					
						try {
							
							Conexao.removeAgente(agente);
						
						}
						catch(Exception npe){
							JOptionPane.showMessageDialog(NovoAgente.this, "Erro ao remover Agente: \n" +npe);
						}
						
						modelo.removeAgente(auxline);
						
						txt_usuario.setText("");
						txt_senha.setText("");
						cb_tipo_usuario.setSelectedIndex(0);
						txtId.setText("");
						
						JOptionPane.showMessageDialog(NovoAgente.this, "Agente Excluido com sucesso!!");
					
					}
				}
		}
		});
		btnRemove.setBounds(814, 43, 49, 33);
		getContentPane().add(btnRemove);
	}

}
