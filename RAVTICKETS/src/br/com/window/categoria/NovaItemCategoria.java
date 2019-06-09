package br.com.window.categoria;

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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import br.com.model.ItemCategoria;
import br.com.model.ItemCategoriaTableModel;
import br.com.model.SubCategoria;
import br.com.window.agente.Novo_agente;

public class NovaItemCategoria extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTable tableItemCateg;
	private static int auxline;
	private JTextField txtId;
	private String[] urgencia = {"BAIXA","MEDIA","ALTA","URGENTE"};

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovaItemCategoria frame = new NovaItemCategoria();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NovaItemCategoria() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		List<SubCategoria> sbcategs = Conexao.listarSubCateg();
		String[] tp_test = new String[sbcategs.size()];
		for(int i = 0; i < sbcategs.size(); i++) {
			tp_test[i] = sbcategs.get(i).getDescr();
		}
		
		List<ItemCategoria> Itemcategs = Conexao.listarItemCateg();
		
		ArrayList dados = new ArrayList();
		
		for(int i = 0; i < Itemcategs.size(); i++) {
			
			ItemCategoria Itemcateg = new ItemCategoria();
			Itemcateg.setId(Itemcategs.get(i).getId());
			Itemcateg.setNome(Itemcategs.get(i).getNome());
			Itemcateg.setCategoria(Itemcategs.get(i).getCategoria());
			dados.add(Itemcateg);
		}
		
		ItemCategoriaTableModel modelo = new ItemCategoriaTableModel(dados);
		
		JLabel lblCadastroDeItemcategoria = new JLabel("Cadastro de ItemCategoria");
		lblCadastroDeItemcategoria.setBounds(6, 6, 609, 67);
		lblCadastroDeItemcategoria.setFont(new Font("SansSerif", Font.BOLD, 45));
		
		JLabel lblNome = new JLabel("Nome.:");
		lblNome.setBounds(6, 89, 130, 24);
		lblNome.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JLabel lblSubcategoria = new JLabel("SubCategoria.:");
		lblSubcategoria.setBounds(6, 133, 130, 24);
		lblSubcategoria.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		txtNome = new JTextField();
		txtNome.setBounds(148, 85, 294, 33);
		txtNome.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtNome.setColumns(10);
		
		JComboBox cbSubCateg = new JComboBox(tp_test);
		cbSubCateg.setBounds(148, 130, 294, 31);
		cbSubCateg.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		tableItemCateg = new JTable();
		
		tableItemCateg = new JTable();

		tableItemCateg.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableItemCateg.setShowVerticalLines(true);
		tableItemCateg.setShowHorizontalLines(true);
		tableItemCateg.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tableItemCateg.setToolTipText("");
		tableItemCateg.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		tableItemCateg.setModel(modelo);
		tableItemCateg.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableItemCateg.getColumnModel().getColumn(0).setResizable(false);
		tableItemCateg.getColumnModel().getColumn(1).setPreferredWidth(240);
		tableItemCateg.getColumnModel().getColumn(1).setResizable(false);
		tableItemCateg.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableItemCateg.getColumnModel().getColumn(2).setResizable(false);
		
		tableItemCateg.setBounds(508, 95, 156, 328);
		
		JScrollPane scrollPane = new JScrollPane(tableItemCateg);
		scrollPane.setBounds(454, 89, 470, 321);
		contentPane.setLayout(null);
		
		JComboBox cbUrgencia = new JComboBox(urgencia);
		cbUrgencia.setFont(new Font("SansSerif", Font.PLAIN, 16));
		cbUrgencia.setBounds(148, 173, 294, 31);
		getContentPane().add(cbUrgencia);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ItemCategoria itemcategoria = new ItemCategoria();
				
				itemcategoria.setNome(txtNome.getText());
				itemcategoria.setCategoria(cbSubCateg.getSelectedItem().toString());
				itemcategoria.setUrgencia(cbUrgencia.getSelectedItem().toString());
				
				if(itemcategoria.getNome().equals("") || itemcategoria.getNome().equals(null) || itemcategoria.getCategoria().equals("") || itemcategoria.getCategoria().equals(null) || itemcategoria.getUrgencia().equals("") || itemcategoria.getUrgencia().equals(null)) {
					
					JOptionPane.showMessageDialog(NovaItemCategoria.this,"Para poder gravar uma SubCategoria escreva um nome pelomenos, selecione uma SubCategoria e a urgencia da operação!");
					
				}else {	
					
					try {
				
					Conexao.guardar(itemcategoria);
					
					}catch(NullPointerException f) {
						JOptionPane.showMessageDialog(NovaItemCategoria.this,"Ops.. Deve ter faltado preencher algo ai moral: \n" +f);
					}
					catch(Exception npe){
						JOptionPane.showMessageDialog(NovaItemCategoria.this, "Ops.. Erro ao gravar ItemCategoria: \n" +npe);
					}
					
					txtNome.setText("");
					cbSubCateg.setSelectedIndex(0);
					cbUrgencia.setSelectedIndex(0);
					txtId.setText("");
					
					modelo.addItemCategoria(itemcategoria);
					tableItemCateg.getModel();
					
					JOptionPane.showMessageDialog(NovaItemCategoria.this, "ItemCategoria cadastrado com sucesso!");
				}
			}
		});
		btnSalvar.setBounds(6, 377, 90, 33);
		btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(108, 377, 99, 33);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtNome.setText("");
				cbSubCateg.setSelectedIndex(0);
				cbUrgencia.setSelectedIndex(0);
				txtId.setText("");
			}
		});
		btnLimpar.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(322, 377, 120, 33);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ItemCategoria itemcategoriaremovetable = new ItemCategoria();
				ItemCategoria itemcategoria = new ItemCategoria();
				
				String id = txtId.getText();
				
				if(id.equals("") || id.equals(null)) {
					
					JOptionPane.showMessageDialog(NovaItemCategoria.this, "Selecione uma linha, depois clique no pequeno Lápis acima da tabela para poder editar!");
					
				}else {
				
					Long idaux = Long.valueOf(id);
					
					try {
					
					itemcategoriaremovetable = Conexao.selecionaItemCategoria(idaux);
					
					}catch(NullPointerException f) {
						JOptionPane.showMessageDialog(NovaItemCategoria.this,"Ops.. Deve ter faltado preencher algo ai moral: \n" +f);
					}
					catch(Exception npe){
						JOptionPane.showMessageDialog(NovaItemCategoria.this, "Ops.. Erro ao selecionar ItemCategoria para remover da tabela.. \n" +npe);
					}
					
					itemcategoria.setId(idaux);
					itemcategoria.setNome(txtNome.getText());
					itemcategoria.setCategoria(cbSubCateg.getSelectedItem().toString());
					itemcategoria.setUrgencia(cbUrgencia.getSelectedItem().toString());
					
					modelo.removeItemCategoria(auxline);
					
					try {
					
					Conexao.alterar(itemcategoria);
					
					}catch(NullPointerException f) {
						JOptionPane.showMessageDialog(NovaItemCategoria.this,"Ops.. Deve ter faltado preencher algo ai moral: \n" +f);
					}
					catch(Exception npe){
						JOptionPane.showMessageDialog(NovaItemCategoria.this, "Ops.. Erro ao alterar ItemCategoria.. \n" +npe);
					}
					
					txtNome.setText("");
					txtId.setText("");
					cbSubCateg.setSelectedIndex(0);
					cbUrgencia.setSelectedIndex(0);
					
					modelo.addItemCategoria(itemcategoria);
					tableItemCateg.getModel();
					
					JOptionPane.showMessageDialog(NovaItemCategoria.this, "ItemCategoria atualizada com sucesso!");
				}
			}
		});
		btnAtualizar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnAtualizar.setVisible(false);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(217, 377, 95, 33);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NovaItemCategoria.this.dispose();
			}
		});
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				btnAtualizar.setVisible(false);
				btnSalvar.setVisible(true);
				
				txtNome.setText("");
				cbSubCateg.setSelectedIndex(0);
				cbUrgencia.setSelectedIndex(0);
				txtId.setText("");
				
				btnNovo.setVisible(false);
			}
		});
		btnNovo.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnNovo.setBounds(6, 240, 90, 33);
		btnNovo.setVisible(false);
		contentPane.add(btnNovo);
		
		ImageIcon iconEdit = new ImageIcon(Novo_agente.class.getResource("/br/com/img/edit.png"));
		Image imaEdit = iconEdit.getImage();
		Image imagemEdit = imaEdit.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		Icon icoEdit = new ImageIcon(imagemEdit);
		
		JButton btnEdit = new JButton(icoEdit);
		btnEdit.setBounds(882, 45, 42, 33);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ItemCategoria itemcategoria = new ItemCategoria();
				
				auxline = tableItemCateg.getSelectedRow();
				String id = tableItemCateg.getValueAt(auxline, 0).toString();
				
				if(id.equals("") || id.equals(null)) {
					
					JOptionPane.showMessageDialog(NovaItemCategoria.this, "Selecione uma linha para poder editar!");
					
				}else {
					
					btnSalvar.setVisible(false);
					btnAtualizar.setVisible(true);
					btnNovo.setVisible(true);
				
					Long idaux = Long.valueOf(id);
					
					try {
					
					itemcategoria = Conexao.selecionaItemCategoria(idaux);
					
					}
					catch(Exception npe){
						JOptionPane.showMessageDialog(NovaItemCategoria.this, "Erro ao selecionar ItemCategoria: \n" +npe);
					}
					
					System.out.println(tp_test[0] +"    " +itemcategoria.getCategoria());
					
					txtNome.setText(String.valueOf(itemcategoria.getNome()));
					txtId.setText(String.valueOf(itemcategoria.getId()));
					cbSubCateg.setSelectedItem(itemcategoria.getCategoria());
					cbUrgencia.setSelectedItem(itemcategoria.getUrgencia());
				}
			}
		});
		
		ImageIcon iconRe = new ImageIcon(Novo_agente.class.getResource("/br/com/img/delete.png"));
		Image imaRe = iconRe.getImage();
		Image imagemRe = imaRe.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		Icon icoRe = new ImageIcon(imagemRe);
		
		JButton btnRemov = new JButton(icoRe);
		btnRemov.setBounds(828, 45, 42, 33);
		btnRemov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ItemCategoria subcategoria = new ItemCategoria();
				
				auxline = tableItemCateg.getSelectedRow();
				String id = tableItemCateg.getValueAt(auxline, 0).toString();
				
				if(id.equals("") || id.equals(null)) {
					
					JOptionPane.showMessageDialog(NovaItemCategoria.this, "Selecione uma linha para poder excluir!");
					
				}else {
				
					Long idaux = Long.valueOf(id);
					
					subcategoria = Conexao.selecionaItemCategoria(idaux);
					
					if(JOptionPane.showConfirmDialog(NovaItemCategoria.this, "Deseja mesmo excluir a ItemCategoria selecionada?") == 0) {
					
						try {
						
							Conexao.removeItemCategoria(subcategoria);
						
						}
						catch(Exception npe){
							JOptionPane.showMessageDialog(NovaItemCategoria.this, "Erro ao remover ItemCategoria: \n" +npe);
						}
						
						modelo.removeItemCategoria(auxline);
						
						txtNome.setText("");
						cbSubCateg.setSelectedIndex(0);
						cbUrgencia.setSelectedIndex(0);
						txtId.setText("");
						
						JOptionPane.showMessageDialog(NovaItemCategoria.this, "ItemCategoria removida com sucesso!");
					
					}
				}
			}
		});
		
		JLabel label = new JLabel((Icon) null);
		label.setBounds(790, 6, 42, 25);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				NovaItemCategoria.this.dispose();
			}
		});
		
		txtId = new JTextField();
		txtId.setBounds(767, 46, 49, 28);
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setColumns(10);
		
		ImageIcon iconB = new ImageIcon(Novo_agente.class.getResource("/br/com/img/back.png"));
		Image imaB = iconB.getImage();
		Image imagemB = imaB.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		Icon icoB = new ImageIcon(imagemB);
		
		JLabel label_1 = new JLabel(icoB);
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				NovaItemCategoria.this.dispose();
			}
		});
		label_1.setBounds(882, 7, 42, 25);
		
		JLabel lblUrgencia = new JLabel("Urg\u00EAncia.:");
		lblUrgencia.setBounds(6, 176, 130, 24);
		lblUrgencia.setFont(new Font("SansSerif", Font.BOLD, 18));
		getContentPane().setLayout(null);
		getContentPane().add(lblCadastroDeItemcategoria);
		getContentPane().add(txtId);
		getContentPane().add(btnRemov);
		getContentPane().add(label_1);
		getContentPane().add(label);
		getContentPane().add(btnEdit);
		getContentPane().add(lblNome);
		getContentPane().add(txtNome);
		getContentPane().add(lblSubcategoria);
		getContentPane().add(cbSubCateg);
		getContentPane().add(btnSalvar);
		getContentPane().add(btnLimpar);
		getContentPane().add(btnAtualizar);
		getContentPane().add(btnVoltar);
		getContentPane().add(lblUrgencia);
		getContentPane().add(scrollPane);

	}

}
