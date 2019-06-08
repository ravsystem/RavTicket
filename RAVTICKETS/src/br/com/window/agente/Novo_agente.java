package br.com.window.agente;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

import br.com.connection.Conexao;
import br.com.model.Agente;
import br.com.model.AgenteTableModel;
import br.com.model.Cliente;
import br.com.model.Nivel1;
import br.com.model.Nivel2;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Novo_agente extends JInternalFrame {
	private JTextField txt_usuario;
	private JPasswordField txt_senha;
	private String[] tipos_agente = {"ADM","N1","N2"};
	private JTable table;
	private JTextField txtId;
	private static int auxline;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Novo_agente frame = new Novo_agente();
					frame.setVisible(true);
					frame.getDesktopIcon().removeMouseMotionListener(frame.getDesktopIcon().getMouseMotionListeners()[0]);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Novo_agente() {
		getContentPane().setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		setBounds(100, 100, 950, 460);
		
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
					
					}catch(Exception io) {
						JOptionPane.showMessageDialog(null, "Erro ao Gravar.:");
					}
					
					modelo.addAgente(agente);
					table.getModel();
					
				}else if(tipo.equals("N2")) {
					Agente n2 = new Nivel2();
					n2.setNome(usuario);
					n2.setSenha(senha);
					n2.setTipo(tipo);
					
					try {
					
						Conexao.guardar(n2);
					
					}catch(Exception io) {
						JOptionPane.showMessageDialog(null, "Erro ao Gravar.:");
					}
					
					modelo.addAgente(n2);
					table.getModel();
				}else {
					
					Agente n1 = new Nivel1();
					n1.setNome(usuario);
					n1.setSenha(senha);
					n1.setTipo(tipo);
					
					try {
						Conexao.guardar(n1);
					
					}catch(Exception io) {
						JOptionPane.showMessageDialog(null, "Erro ao Gravar.:");
					}
					
					modelo.addAgente(n1);
					table.getModel();
				}
				
				
				
				txt_usuario.setText("");
				txt_senha.setText("");
				cb_tipo_usuario.setSelectedIndex(0);
				
				//JOptionPane.showMessageDialog(null, "Usu�rio Salvo com Sucesso!");
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
			}
		});
		btnLimpar.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(353, 376, 100, 33);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Novo_agente.this.dispose();
			}
			
		});
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		ImageIcon iconB = new ImageIcon(Novo_agente.class.getResource("/br/com/img/back.png"));
		Image imaB = iconB.getImage();
		Image imagemB = imaB.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		Icon icoB = new ImageIcon(imagemB);
		
		JLabel lblX = new JLabel(icoB);
		lblX.setBounds(875, 4, 49, 27);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Novo_agente.this.dispose();
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
		
		ImageIcon iconEdit = new ImageIcon(Novo_agente.class.getResource("/br/com/img/edit.png"));
		Image imaEdit = iconEdit.getImage();
		Image imagemEdit = imaEdit.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		Icon icoEdit = new ImageIcon(imagemEdit);
		
		JButton btnNewButton = new JButton(icoEdit);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Agente agente = new Agente();
				
				auxline = table.getSelectedRow();
				String id = table.getValueAt(auxline, 0).toString();
				
				Long idaux = Long.valueOf(id);
				
				System.out.println("mostrou a porra do id>.: " +idaux);
				
				agente = Conexao.selecionaAgente(idaux);
				
				txtId.setText(String.valueOf(agente.getId()));
				txt_usuario.setText(agente.getNome());
				txt_senha.setText(agente.getSenha());
				cb_tipo_usuario.setSelectedItem(agente.getTipo());
			}
		});
		btnNewButton.setBounds(875, 43, 49, 33);
		getContentPane().add(btnNewButton);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Agente agenteremovetable = new Agente();
				Agente agente = new Agente();
				
				String id = txtId.getText();
				Long idaux = Long.valueOf(id);
				
				agenteremovetable = Conexao.selecionaAgente(idaux);
				
				agente.setId(idaux);
				agente.setNome(txt_usuario.getText());
				agente.setSenha(txt_senha.getText());
				agente.setTipo(cb_tipo_usuario.getSelectedItem().toString());
				
				modelo.removeAgente(auxline);
				
				Conexao.alterar(agente);
				
				txt_usuario.setText("");
				txt_senha.setText("");
				cb_tipo_usuario.setSelectedIndex(0);
				
				modelo.addAgente(agente);
				table.getModel();
				
			}
		});
		btnAtualizar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnAtualizar.setBounds(229, 376, 112, 33);
		getContentPane().add(btnAtualizar);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setVisible(false);
		txtId.setBounds(743, 43, 59, 34);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		ImageIcon iconRe = new ImageIcon(Novo_agente.class.getResource("/br/com/img/delete.png"));
		Image imaRe = iconRe.getImage();
		Image imagemRe = imaRe.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		Icon icoRe = new ImageIcon(imagemRe);
		
		JButton btnRemove = new JButton(icoRe);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Agente agente = new Agente();
				
				auxline = table.getSelectedRow();
				String id = table.getValueAt(auxline, 0).toString();
				
				Long idaux = Long.valueOf(id);
				
				agente = Conexao.selecionaAgente(idaux);
				
				Conexao.removeAgente(agente);
				
				modelo.removeAgente(auxline);
			}
		});
		btnRemove.setBounds(814, 44, 49, 33);
		getContentPane().add(btnRemove);

	}
}
