package br.com.window.principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.connection.Conexao;
import br.com.window.agente.NovoAgente;
import br.com.window.agente.Novo_agente;
import br.com.window.categoria.NovaCategoria;
import br.com.window.categoria.NovaItemCategoria;
import br.com.window.categoria.NovaSubCategoria;
import br.com.window.categoria.Nova_SubCategoria;
import br.com.window.categoria.Nova_categoria;
import br.com.window.categoria.Novo_itemCategoria;
import br.com.window.cliente.Novo_cliente;
import br.com.window.login.Login;
import br.com.window.tickts.Novo_ticket;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.Color;

public class Main_principal extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	public Dimension tk = Toolkit.getDefaultToolkit().getScreenSize();
	
	 private void centralizaForm(JInternalFrame frame) {
	        Dimension desktopSize = desktopPane.getSize();
	        Dimension jInternalFrameSize = frame.getSize();
	        frame.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
	                (desktopSize.height - jInternalFrameSize.height) / 2);
	    }

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_principal frame = new Main_principal();
					frame.setUndecorated(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main_principal() {
		
		Conexao.iniciarFabrica();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Integer.valueOf((int) tk.getWidth()), Integer.valueOf((int) tk.getHeight()));
		
		//desktopPane = new JDesktopPane();
		//desktopPane.setBounds(0, 0, Integer.valueOf((int) tk.getWidth()), Integer.valueOf((int) tk.getHeight()));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.desktop);
		menuBar.setForeground(SystemColor.desktop);
		menuBar.setMargin(new Insets(0, 10, 0, 0));
		setJMenuBar(menuBar);
		
		JMenu mnTickets = new JMenu("TICKET");
		mnTickets.setForeground(SystemColor.textHighlightText);
		mnTickets.setBackground(SystemColor.desktop);
		mnTickets.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
		menuBar.add(mnTickets);
		
		JMenuItem mntmNovo = new JMenuItem("Novo");
		mntmNovo.setFont(new Font("SansSerif", Font.PLAIN, 18));
		mntmNovo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				Novo_ticket novo_ticket = new Novo_ticket();
				desktopPane.add(novo_ticket);
				contentPane.add(desktopPane);
				novo_ticket.setResizable(false);
				centralizaForm(novo_ticket);
				novo_ticket.show();
			}
		});
		mnTickets.add(mntmNovo);
		
		JMenuItem mntmListar = new JMenuItem("Listar");
		mntmListar.setFont(new Font("SansSerif", Font.PLAIN, 18));
		mnTickets.add(mntmListar);
		
		JMenuItem mntmBuscar = new JMenuItem("Buscar");
		mntmBuscar.setFont(new Font("SansSerif", Font.PLAIN, 18));
		mnTickets.add(mntmBuscar);
		
		menuBar.add(Box.createGlue());
		
		JMenu mnClientes = new JMenu("CLIENTE");
		mnClientes.setForeground(SystemColor.textHighlightText);
		mnClientes.setBackground(SystemColor.desktop);
		mnClientes.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
		menuBar.add(mnClientes);
		
		JMenuItem mntmNovo_1 = new JMenuItem("Novo");
		mntmNovo_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		mntmNovo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Novo_cliente novo_cliente = new Novo_cliente();
				desktopPane.add(novo_cliente);
				contentPane.add(desktopPane);
				novo_cliente.setResizable(false);
				centralizaForm(novo_cliente);
				novo_cliente.show();
			}
		});
		mnClientes.add(mntmNovo_1);
		
		Component glue = Box.createGlue();
		glue.setBackground(SystemColor.desktop);
		menuBar.add(glue);
		
		JMenu mnCategoria = new JMenu("CATEGORIA");
		mnCategoria.setForeground(Color.WHITE);
		mnCategoria.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
		menuBar.add(mnCategoria);
		
		JMenuItem mntmNovo_3 = new JMenuItem("Nova");
		mntmNovo_3.setFont(new Font("SansSerif", Font.PLAIN, 18));
		mntmNovo_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NovaCategoria nova_categoria = new NovaCategoria();
				nova_categoria.setResizable(false);
				nova_categoria.setUndecorated(true);
				nova_categoria.setLocationRelativeTo(null);
				nova_categoria.setAlwaysOnTop(true);
				nova_categoria.setVisible(true);

			}
		});
		mnCategoria.add(mntmNovo_3);
		menuBar.add(Box.createGlue());
		
		JMenu mnSubcategoria = new JMenu("SUBCATEGORIA");
		mnSubcategoria.setForeground(Color.WHITE);
		mnSubcategoria.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
		menuBar.add(mnSubcategoria);
		
		JMenuItem mntmNova = new JMenuItem("Nova");
		mntmNova.setFont(new Font("SansSerif", Font.PLAIN, 18));
		mntmNova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NovaSubCategoria nova_subcategoria = new NovaSubCategoria();
				nova_subcategoria.setResizable(false);
				nova_subcategoria.setUndecorated(true);
				nova_subcategoria.setLocationRelativeTo(null);
				nova_subcategoria.setAlwaysOnTop(true);
				nova_subcategoria.setVisible(true);

			}
		});
		mnSubcategoria.add(mntmNova);
		menuBar.add(Box.createGlue());
		
		JMenu mnItemCategoria = new JMenu("ITEM CATEGORIA");
		mnItemCategoria.setForeground(Color.WHITE);
		mnItemCategoria.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
		menuBar.add(mnItemCategoria);
		
		JMenuItem mntmNova_1 = new JMenuItem("Novo");
		mntmNova_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		mntmNova_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NovaItemCategoria nova_itemcategoria = new NovaItemCategoria();
				nova_itemcategoria.setResizable(false);
				nova_itemcategoria.setUndecorated(true);
				nova_itemcategoria.setLocationRelativeTo(null);
				nova_itemcategoria.setAlwaysOnTop(true);
				nova_itemcategoria.setVisible(true);
				
			}
		});
		mnItemCategoria.add(mntmNova_1);
		menuBar.add(Box.createGlue());
		menuBar.add(Box.createGlue());
		menuBar.add(Box.createGlue());
		menuBar.add(Box.createGlue());
		menuBar.add(Box.createGlue());
		menuBar.add(Box.createGlue());
		menuBar.add(Box.createGlue());
		menuBar.add(Box.createGlue());
		menuBar.add(Box.createGlue());
		menuBar.add(Box.createGlue());
		menuBar.add(Box.createGlue());
		menuBar.add(Box.createGlue());
		menuBar.add(Box.createGlue());
		menuBar.add(Box.createGlue());
		
		JMenu mnAgentes = new JMenu("AGENTE");
		mnAgentes.setForeground(SystemColor.textHighlightText);
		mnAgentes.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		menuBar.add(mnAgentes);
		
		JMenuItem mntmNovo_2 = new JMenuItem("Novo");
		mntmNovo_2.setFont(new Font("SansSerif", Font.PLAIN, 18));
		mntmNovo_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				NovoAgente novo_agente = new NovoAgente();
				novo_agente.setResizable(false);
				novo_agente.setUndecorated(true);
				novo_agente.setLocationRelativeTo(null);
				novo_agente.setAlwaysOnTop(true);
				novo_agente.setVisible(true);
				
			}
		});
		mnAgentes.add(mntmNovo_2);
		
		menuBar.add(Box.createGlue());
		
		JMenu menu = new JMenu("-");
		menu.setForeground(SystemColor.textHighlightText);
		menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				setExtendedState(ICONIFIED);
			}
		});
		menu.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
		menuBar.add(menu);
		
		//menuBar.add(Box.createGlue());
		
		JMenu mnX = new JMenu("X");
		mnX.setForeground(SystemColor.textHighlightText);
		mnX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				Main_principal.this.dispose();
				Login novoLogin = new Login();
				novoLogin.setUndecorated(true);
				novoLogin.setLocationRelativeTo(null);
				novoLogin.setVisible(true);
			}
		});
		mnX.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
		menuBar.add(mnX);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	}
}
