package br.com.window.agente;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

import br.com.connection.Conexao;
import br.com.model.Agente;
import br.com.model.AgenteTableModel;
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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Novo_agente frame = new Novo_agente();
					frame.setVisible(true);
					 frame.getDesktopIcon().removeMouseMotionListener(frame.getDesktopIcon().getMouseMotionListeners()[0]);

					 // bloquear o frame
					 for(Component c : frame.getComponents()){
					        if (c instanceof BasicInternalFrameTitlePane){
					        		for (MouseMotionListener m : c.getMouseMotionListeners()){
					        			  c.removeMouseMotionListener(m);
					        		}
					        		break;
					        }
					 }
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
		btnSalvar.setBounds(10, 376, 98, 33);
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
		btnLimpar.setBounds(120, 376, 110, 33);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txt_usuario.setText("");
				txt_senha.setText("");
				cb_tipo_usuario.setSelectedIndex(0);
			}
		});
		btnLimpar.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(242, 376, 107, 33);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Novo_agente.this.dispose();
			}
			
		});
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JLabel label = new JLabel("<-");
		label.setBounds(865, 0, 59, 34);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Novo_agente.this.dispose();
			}
		});
		label.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
		
		
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
		getContentPane().add(label);
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
		
		//getContentPane().add(table);

	}
}
