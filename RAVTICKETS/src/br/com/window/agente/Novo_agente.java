package br.com.window.agente;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseMotionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

import br.com.connection.Conexao;
import br.com.model.Agente;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Novo_agente extends JInternalFrame {
	private JTextField txt_usuario;
	private JPasswordField txt_senha;
	private String[] tipos_agente = {"ADM","N1","N2"};

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

	/**
	 * Create the frame.
	 */
	public Novo_agente() {
		getContentPane().setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		setBounds(100, 100, 950, 460);
		
		
		JLabel lblCadastrarNovoAgente = new JLabel("Cadastrar novo Agente");
		lblCadastrarNovoAgente.setFont(new Font("SansSerif", Font.BOLD, 45));
		
		JLabel lblNome = new JLabel("Nome.:");
		lblNome.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JLabel lblSenha = new JLabel("Senha.:");
		lblSenha.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JLabel lblNewLabel = new JLabel("Tipo.:");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		txt_usuario = new JTextField();
		txt_usuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txt_usuario.setColumns(10);
		
		txt_senha = new JPasswordField();
		txt_senha.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JComboBox cb_tipo_usuario = new JComboBox(tipos_agente);
		cb_tipo_usuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String usuario = txt_usuario.getText();
				String senha = txt_senha.getText();
				String tipo = cb_tipo_usuario.getSelectedItem().toString();
				
				try {
				
				if(tipo.equals("ADM")) {
					Agente agente = new Agente();
					agente.setNome(usuario);
					agente.setSenha(senha);
					agente.setTipo(tipo);
					Conexao.guardar(agente);
					
				}else if(tipo.equals("N2")) {
					Agente n2 = new Agente();
					n2.setNome(usuario);
					n2.setSenha(senha);
					n2.setTipo(tipo);
					Conexao.guardar(n2);
				}else {
					
					Agente n1 = new Agente();
					n1.setNome(usuario);
					n1.setSenha(senha);
					n1.setTipo(tipo);
					Conexao.guardar(n1);
				}
				
				}catch(Exception io) {
					JOptionPane.showMessageDialog(null, "Erro ao Gravar.:");
				}
				
				txt_usuario.setText("");
				txt_senha.setText("");
				cb_tipo_usuario.setSelectedIndex(0);
				
				JOptionPane.showMessageDialog(null, "Usuário Salvo com Sucesso!");
			}
		});
		btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txt_usuario.setText("");
				txt_senha.setText("");
				cb_tipo_usuario.setSelectedIndex(0);
			}
		});
		btnLimpar.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Novo_agente.this.dispose();
			}
			
		});
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JLabel label = new JLabel("<-");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Novo_agente.this.dispose();
			}
		});
		label.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCadastrarNovoAgente, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 345, Short.MAX_VALUE)
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblNome, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblSenha, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(cb_tipo_usuario, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(txt_senha)
										.addComponent(txt_usuario)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnSalvar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnLimpar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnVoltar)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCadastrarNovoAgente, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(64)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNome)
								.addComponent(txt_usuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSenha)
								.addComponent(txt_senha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(cb_tipo_usuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(139)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSalvar)
								.addComponent(btnLimpar)
								.addComponent(btnVoltar)))
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
