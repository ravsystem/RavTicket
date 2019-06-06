package br.com.window.login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import br.com.connection.Conexao;
import br.com.model.Agente;
import br.com.window.principal.Main_principal;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField txt_senha;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setUndecorated(true);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		
		Conexao.iniciarFabrica();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/br/com/img/login1.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 368, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				System.exit(0);
			}
		});
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(new Color(255, 255, 255));
		lblX.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblX.setBounds(324, 2, 37, 35);
		contentPane.add(lblX);
		
		List<Agente> agents = Conexao.listarAgent();
		String[] agentes = new String[agents.size()];
		for(int i = 0; i < agents.size(); i++) {
			agentes[i] = agents.get(i).getNome();
		}
		
		JComboBox cb_usuario = new JComboBox(agentes);
		cb_usuario.setFont(new Font("Lucida Console", Font.PLAIN, 20));
		cb_usuario.setBounds(85, 255, 197, 35);
		contentPane.add(cb_usuario);
		
		txt_senha = new JPasswordField();
		txt_senha.setToolTipText("");
		txt_senha.setFont(new Font("Lucida Console", Font.PLAIN, 20));
		txt_senha.setColumns(10);
		txt_senha.setBounds(85, 330, 197, 35);
		contentPane.add(txt_senha);
		
		JLabel lblUsurio = new JLabel("Usuario");
		lblUsurio.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		lblUsurio.setForeground(new Color(255, 255, 255));
		lblUsurio.setBounds(85, 227, 102, 16);
		contentPane.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		lblSenha.setBounds(85, 302, 102, 16);
		contentPane.add(lblSenha);
		
		JButton btnNewButton = new JButton("Limpar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cb_usuario.setSelectedIndex(0);;
				txt_senha.setText("");
			}
		});
		btnNewButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnNewButton.setBounds(192, 377, 90, 28);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Entrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String agente = cb_usuario.getSelectedItem().toString();
				String senha = txt_senha.getText();
				
				if (Conexao.validaAgente(agente, senha)) {
					
					if(Conexao.tipoAgente(agente)) {
					
						Main_principal frame = new Main_principal();
						JOptionPane.showMessageDialog(null, "Bem vindo " +agente);
						Login.this.dispose();
						frame.setUndecorated(true);
						frame.setVisible(true);
						frame.setResizable(false);
						frame.setLocationRelativeTo(null);
						
					}else {
						
						Main_principal frame = new Main_principal();
						JOptionPane.showMessageDialog(null, "Bem vindo " +agente);
						Login.this.dispose();
						frame.setUndecorated(true);
						frame.setVisible(true);
						frame.setResizable(false);
						frame.setLocationRelativeTo(null);
					}
					
				}else {
					
					JOptionPane.showMessageDialog(null, "Usu�rio e/ou senha incorretos!");
					txt_senha.setText("");
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnNewButton_1.setBounds(85, 377, 90, 28);
		contentPane.add(btnNewButton_1);
		
		JLabel lbl_img_principal = new JLabel("");
		lbl_img_principal.setIcon(new ImageIcon(Login.class.getResource("/br/com/img/login1.png")));
		lbl_img_principal.setBounds(0, 0, 368, 455);
		contentPane.add(lbl_img_principal);
		
		
	}
}
