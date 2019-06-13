package br.com.window.tickts;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.connection.Conexao;
import br.com.model.Agente;
import br.com.model.Categoria;
import br.com.model.Cliente;
import br.com.model.ItemCategoria;
import br.com.model.SubCategoria;
import br.com.model.Ticket;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class NovoTicket extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitulo;
	private String[] status = {"RESOLVIDO","PAUSADO","PENDENTE"};
	private String[] padraoSub = {""};
	private String[] padraoitem = {""};

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovoTicket frame = new NovoTicket();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NovoTicket() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1075, 714);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		List<Cliente> clients = Conexao.listarClient();
		String[] tp_test_cli = new String[clients.size()];
		for(int i = 0; i < clients.size(); i++) {
			tp_test_cli[i] = clients.get(i).getFantasia();
		}
		
		List<Categoria> categs = Conexao.listarCateg();
		String[] tp_test_categs = new String[categs.size()];
		for(int i = 0; i < categs.size(); i++) {
			tp_test_categs[i] = categs.get(i).getDescr();
		}
		
		List<ItemCategoria> itemcategsUgencia = Conexao.listarItemCateg();
		String[] tp_test_itemCateg_urgencia = new String[itemcategsUgencia.size()];
		for(int i = 0; i < itemcategsUgencia.size(); i++) {
			tp_test_itemCateg_urgencia[i] = itemcategsUgencia.get(i).getUrgencia();
		}
		
		List<Agente> nivelAgents = Conexao.listarAgent();
		String[] tp_test_nivel_agentes = new String[nivelAgents.size()];
		for(int i = 0; i < nivelAgents.size(); i++) {
			tp_test_nivel_agentes[i] = nivelAgents.get(i).getTipo();
		}
		
		List<Agente> agents = Conexao.listarAgent();
		String[] tp_test_agentes = new String[agents.size()];
		for(int i = 0; i < agents.size(); i++) {
			tp_test_agentes[i] = agents.get(i).getNome();
		}
		
		JLabel lblAdicionarNovoTicket = new JLabel("Adicionar novo Ticket");
		lblAdicionarNovoTicket.setBounds(10, 11, 493, 64);
		lblAdicionarNovoTicket.setFont(new Font("SansSerif", Font.BOLD, 45));
		
		JLabel lblCliente = new JLabel("Cliente.:");
		lblCliente.setBounds(10, 100, 82, 24);
		lblCliente.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JComboBox cbCliente = new JComboBox(tp_test_cli);
		cbCliente.setBounds(10, 136, 308, 31);
		cbCliente.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JLabel lblNewLabel = new JLabel("Categoria.:");
		lblNewLabel.setBounds(10, 179, 308, 24);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
				
		JLabel lblSubcategoria = new JLabel("SubCategoria.:");
		lblSubcategoria.setBounds(10, 256, 130, 24);
		lblSubcategoria.setFont(new Font("SansSerif", Font.BOLD, 18));
			
		JComboBox cbItemCategoria = new JComboBox(padraoitem);
		cbItemCategoria.setBounds(10, 368, 308, 31);
		cbItemCategoria.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JComboBox cbSubCategoria = new JComboBox(padraoSub);
		cbSubCategoria.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				
				if(cbItemCategoria.getSelectedItem() == null){
					cbItemCategoria.setModel(new DefaultComboBoxModel<>());

	            }else{
				
					List<ItemCategoria> itemcategs = Conexao.subCategoriaInItemcategoria(cbSubCategoria.getSelectedItem().toString());
					
					String[] tp_test_itemCategs = new String[itemcategs.size()];
					
					for(int i = 0; i < itemcategs.size(); i++) {
						tp_test_itemCategs[i] = itemcategs.get(i).getNome();
					}
					
					cbItemCategoria.removeAllItems();
					cbItemCategoria.setModel(new DefaultComboBoxModel<String>(tp_test_itemCategs));
	            }
			}
		});
		cbSubCategoria.setBounds(10, 291, 308, 31);
		cbSubCategoria.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JComboBox cbCategoria = new JComboBox(tp_test_categs);
		cbCategoria.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent arg0) {
				
				if(cbSubCategoria.getSelectedItem() == null){
					
					List<SubCategoria> sbcategs = Conexao.categoriaInSubcategoria(cbCategoria.getSelectedItem().toString());
					
					String[] tp_test_subCategs = new String[sbcategs.size()];
					
					for(int i = 0; i < sbcategs.size(); i++) {
						tp_test_subCategs[i] = sbcategs.get(i).getDescr();
					}
					
					cbSubCategoria.setModel(new DefaultComboBoxModel<String>(tp_test_subCategs));

	            }else{
				
					List<SubCategoria> sbcategs = Conexao.categoriaInSubcategoria(cbCategoria.getSelectedItem().toString());
					
					String[] tp_test_subCategs = new String[sbcategs.size()];
					
					for(int i = 0; i < sbcategs.size(); i++) {
						tp_test_subCategs[i] = sbcategs.get(i).getDescr();
					}
					
					cbSubCategoria.removeAllItems();
					cbSubCategoria.setModel(new DefaultComboBoxModel<String>(tp_test_subCategs));
	            }
			}
		});
		cbCategoria.setBounds(10, 214, 308, 31);
		cbCategoria.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JLabel lblItemDeCategoria = new JLabel("Item de Categoria.:");
		lblItemDeCategoria.setBounds(10, 333, 167, 24);
		lblItemDeCategoria.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JLabel lblUrgncia = new JLabel("Urg\u00EAncia.:");
		lblUrgncia.setBounds(10, 410, 90, 24);
		lblUrgncia.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JComboBox cbUrgencia = new JComboBox(tp_test_itemCateg_urgencia);
		cbUrgencia.setBounds(10, 445, 308, 31);
		cbUrgencia.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JLabel lblStatus = new JLabel("Status.:");
		lblStatus.setBounds(10, 487, 67, 24);
		lblStatus.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JLabel lblAgente = new JLabel("Agente.:");
		lblAgente.setBounds(10, 566, 75, 24);
		lblAgente.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo");
		lblTtulo.setBounds(371, 100, 60, 24);
		lblTtulo.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(371, 135, 630, 33);
		txtTitulo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtTitulo.setColumns(10);
		getContentPane().setLayout(null);
		getContentPane().add(lblAdicionarNovoTicket);
		getContentPane().add(lblNewLabel);
		getContentPane().add(lblSubcategoria);
		getContentPane().add(lblItemDeCategoria);
		getContentPane().add(lblUrgncia);
		getContentPane().add(lblStatus);
		getContentPane().add(lblAgente);
		getContentPane().add(lblCliente);
		getContentPane().add(cbCliente);
		getContentPane().add(cbUrgencia);
		getContentPane().add(cbItemCategoria);
		getContentPane().add(cbSubCategoria);
		getContentPane().add(cbCategoria);
		getContentPane().add(txtTitulo);
		getContentPane().add(lblTtulo);
		
		JTextArea descricao = new JTextArea();
		descricao.setBounds(371, 214, 630, 108);
		descricao.setLineWrap(true);
		getContentPane().add(descricao);
		
		JLabel lblDescricaoDoTicket = new JLabel("Descricao do Ticket");
		lblDescricaoDoTicket.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblDescricaoDoTicket.setBounds(371, 185, 185, 16);
		getContentPane().add(lblDescricaoDoTicket);
		
		JButton btnNewButton = new JButton("Limpar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				descricao.setText("");
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnNewButton.setBounds(898, 177, 103, 28);
		getContentPane().add(btnNewButton);
		
		JComboBox cbStatus = new JComboBox(status);
		cbStatus.setFont(new Font("SansSerif", Font.PLAIN, 16));
		cbStatus.setBounds(10, 523, 308, 31);
		getContentPane().add(cbStatus);
		
		JComboBox cbAgent = new JComboBox(tp_test_agentes);
		cbAgent.setFont(new Font("SansSerif", Font.PLAIN, 16));
		cbAgent.setBounds(112, 599, 206, 31);
		getContentPane().add(cbAgent);
		
		JComboBox cbNivel = new JComboBox(tp_test_nivel_agentes);
		cbNivel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		cbNivel.setBounds(10, 599, 90, 31);
		getContentPane().add(cbNivel);
		
		JTextArea txtResolucao = new JTextArea();
		txtResolucao.setLineWrap(true);
		txtResolucao.setBounds(371, 522, 630, 108);
		getContentPane().add(txtResolucao);
		
		JTextArea txtO_que_feito = new JTextArea();
		txtO_que_feito.setLineWrap(true);
		txtO_que_feito.setBounds(371, 368, 630, 108);
		getContentPane().add(txtO_que_feito);
		
		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Ticket ticket = new Ticket();
				
				ticket.setCliente(cbCliente.getSelectedItem().toString());
				ticket.setCategoria(cbCategoria.getSelectedItem().toString());
				ticket.setSubCategoria(cbSubCategoria.getSelectedItem().toString());
				ticket.setItemCateg(cbItemCategoria.getSelectedItem().toString());
				ticket.setUrgencia(cbUrgencia.getSelectedItem().toString());
				ticket.setAgente(cbAgent.getSelectedItem().toString());
				ticket.setStatus(cbStatus.getSelectedItem().toString());
				ticket.setTitulo(txtTitulo.getText());
				ticket.setNivel(cbNivel.getSelectedItem().toString());
				ticket.setDescr_problema(descricao.getText());
				ticket.setResolucao(txtResolucao.getText());
				ticket.setO_que_foi_feito(txtO_que_feito.getText());
				
				Conexao.guardar(ticket);
				
				cbAgent.setSelectedIndex(0);
				cbCategoria.setSelectedIndex(0);
				cbCliente.setSelectedIndex(0);
				cbItemCategoria.setSelectedIndex(0);
				cbNivel.setSelectedIndex(0);
				cbStatus.setSelectedIndex(0);
				cbSubCategoria.setSelectedIndex(0);
				cbUrgencia.setSelectedIndex(0);
				txtTitulo.setText("");
				txtO_que_feito.setText("");
				txtResolucao.setText("");
				descricao.setText("");
			}
		});
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnNewButton_1.setBounds(763, 649, 90, 28);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblOQueFoi = new JLabel("O que foi feito?");
		lblOQueFoi.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblOQueFoi.setBounds(371, 339, 185, 16);
		getContentPane().add(lblOQueFoi);
		
		JLabel lblResoluo = new JLabel("Resolu\u00E7\u00E3o");
		lblResoluo.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblResoluo.setBounds(371, 493, 185, 16);
		getContentPane().add(lblResoluo);
		
		JButton button = new JButton("Limpar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtO_que_feito.setText("");
			}
		});
		button.setFont(new Font("SansSerif", Font.BOLD, 18));
		button.setBounds(898, 333, 103, 28);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("Limpar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtResolucao.setText("");
			}
		});
		button_1.setFont(new Font("SansSerif", Font.BOLD, 18));
		button_1.setBounds(898, 487, 103, 28);
		getContentPane().add(button_1);
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				NovoTicket.this.dispose();
			}
		});
		lblX.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
		lblX.setBounds(1029, 0, 34, 39);
		getContentPane().add(lblX);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cbAgent.setSelectedIndex(0);
				cbCategoria.setSelectedIndex(0);
				cbCliente.setSelectedIndex(0);
				cbItemCategoria.setSelectedIndex(0);
				cbNivel.setSelectedIndex(0);
				cbStatus.setSelectedIndex(0);
				cbSubCategoria.setSelectedIndex(0);
				cbUrgencia.setSelectedIndex(0);
				txtTitulo.setText("");
				txtO_que_feito.setText("");
				txtResolucao.setText("");
				descricao.setText("");
			}
		});
		btnLimpar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnLimpar.setBounds(865, 649, 90, 28);
		getContentPane().add(btnLimpar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NovoTicket.this.dispose();
			}
		});
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnVoltar.setBounds(967, 649, 90, 28);
		getContentPane().add(btnVoltar);
	}

}
