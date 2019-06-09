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
import br.com.model.Categoria;
import br.com.model.CategoriaTableModel;
import br.com.window.agente.NovoAgente;

public class NovaCategoria extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTable tableCateg;
	private static int auxline;
	private JTextField txtId;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovaCategoria frame = new NovaCategoria();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NovaCategoria() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		List<Categoria> categs = Conexao.listarCateg();
		
		ArrayList dados = new ArrayList();
		
		for(int i = 0; i < categs.size(); i++) {
			
			Categoria categ = new Categoria();
			categ.setId(categs.get(i).getId());
			categ.setDescr(categs.get(i).getDescr());
			dados.add(categ);
		}
		
		CategoriaTableModel modelo = new CategoriaTableModel(dados);
		
		JLabel lblCadastroDeCategoria = new JLabel("Cadastro de Categoria");
		lblCadastroDeCategoria.setBounds(10, 11, 511, 68);
		lblCadastroDeCategoria.setFont(new Font("SansSerif", Font.BOLD, 45));
		
		JLabel lblNome = new JLabel("Nome.:");
		lblNome.setBounds(10, 98, 64, 24);
		lblNome.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		txtNome = new JTextField();
		txtNome.setBounds(84, 97, 322, 27);
		txtNome.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtNome.setColumns(10);
		
		tableCateg = new JTable();
		
		tableCateg.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCateg.setShowVerticalLines(true);
		tableCateg.setShowHorizontalLines(true);
		tableCateg.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tableCateg.setToolTipText("");
		tableCateg.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		tableCateg.setModel(modelo);
		tableCateg.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableCateg.getColumnModel().getColumn(0).setResizable(false);
		tableCateg.getColumnModel().getColumn(1).setPreferredWidth(270);
		tableCateg.getColumnModel().getColumn(1).setResizable(false);

		tableCateg.setBounds(524, 80, 198, 339);
		
		JScrollPane scrollPane = new JScrollPane(tableCateg);
		scrollPane.setBounds(494, 80, 430, 335);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		getContentPane().add(lblCadastroDeCategoria);
		getContentPane().add(lblNome);
		getContentPane().add(txtNome);
		getContentPane().add(scrollPane);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Categoria categoria = new Categoria();
				
				categoria.setDescr(txtNome.getText());
				
				if(categoria.getDescr().equals("") || categoria.getDescr().equals(null)) {
					
					JOptionPane.showMessageDialog(NovaCategoria.this,"Para poder gravar uma Categoria escreva um nome pelomenos!");
					
				}else {
				
					try {
					
					Conexao.guardar(categoria);
					
					}catch(NullPointerException f) {
						JOptionPane.showMessageDialog(NovaCategoria.this,"Ops.. Deve ter faltado preencher algo ai moral: \n" +f);
					}
					catch(Exception npe){
						JOptionPane.showMessageDialog(NovaCategoria.this, "Ops.. Erro ao gravar Categoria: \n" +npe);
					}
					
					txtNome.setText("");
					txtId.setText("");
					
					modelo.addCateg(categoria);
					tableCateg.getModel();
					
					JOptionPane.showMessageDialog(NovaCategoria.this,"Categoria gravada com sucesso!");
				}
			}
		});
		btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnSalvar.setBounds(10, 382, 89, 33);
		getContentPane().add(btnSalvar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtNome.setText("");
				txtId.setText("");
			}
		});
		btnLimpar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnLimpar.setBounds(109, 382, 105, 33);
		getContentPane().add(btnLimpar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Categoria categoriaremovetable = new Categoria();
				Categoria categoria = new Categoria();
				
				String id = txtId.getText();
				
				if(id.equals("") || id.equals(null)) {
					
					JOptionPane.showMessageDialog(NovaCategoria.this, "Selecione uma linha, depois clique no pequeno Lápis acima da tabela para poder editar!");
					
				}else {
					
					Long idaux = Long.valueOf(id);
					
					try {
						
					categoriaremovetable = Conexao.selecionaCategoria(idaux);
					
					}catch(NullPointerException f) {
						JOptionPane.showMessageDialog(NovaCategoria.this,"Ops.. Deve ter faltado preencher algo ai moral: \n" +f);
					}
					catch(Exception npe){
						JOptionPane.showMessageDialog(NovaCategoria.this, "Ops.. Erro ao selecionar Categoria para remover da tabela.. \n" +npe);
					}
					
					categoria.setId(idaux);
					categoria.setDescr(txtNome.getText());
					
					modelo.removeCateg(auxline);
					
					try {
					
					Conexao.alterar(categoria);
					
					}catch(NullPointerException f) {
						JOptionPane.showMessageDialog(NovaCategoria.this,"Ops.. Deve ter faltado preencher algo ai moral: \n" +f);
					}
					catch(Exception npe){
						JOptionPane.showMessageDialog(NovaCategoria.this, "Ops.. Erro ao Alterar Categoria: \n" +npe);
					}
					
					txtNome.setText("");
					txtId.setText("");
					
					modelo.addCateg(categoria);
					tableCateg.getModel();
					
					JOptionPane.showMessageDialog(NovaCategoria.this, "Categoria alterada com sucesso!");
				
				}
			}
		});
		btnAtualizar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnAtualizar.setBounds(323, 382, 112, 33);
		btnAtualizar.setVisible(false);
		getContentPane().add(btnAtualizar);
		
		JButton btnNova = new JButton("Nova");
		btnNova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnSalvar.setVisible(true);
				btnAtualizar.setVisible(false);
				
				txtNome.setText("");
				txtId.setText("");
				
				btnNova.setVisible(false);
			}
		});
		btnNova.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnNova.setBounds(10, 154, 89, 33);
		btnNova.setVisible(false);
		contentPane.add(btnNova);
		
		JButton btnVoltar = new JButton("voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NovaCategoria.this.dispose();
			}
		});
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnVoltar.setBounds(224, 382, 89, 33);
		getContentPane().add(btnVoltar);
		
		ImageIcon iconEdit = new ImageIcon(NovaCategoria.class.getResource("/br/com/img/edit.png"));
		Image imaEdit = iconEdit.getImage();
		Image imagemEdit = imaEdit.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		Icon icoEdit = new ImageIcon(imagemEdit);
		
		JButton btnEdit = new JButton(icoEdit);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Categoria categoria = new Categoria();
				
				auxline = tableCateg.getSelectedRow();
				String id = tableCateg.getValueAt(auxline, 0).toString();
				
				if(id.equals("") || id.equals(null)) {
					
					JOptionPane.showMessageDialog(NovaCategoria.this, "Selecione uma linha para poder editar!");
					
				}else {
					
					btnAtualizar.setVisible(true);
					btnNova.setVisible(true);
					btnSalvar.setVisible(false);
				
					Long idaux = Long.valueOf(id);
					
					try {
					
						categoria = Conexao.selecionaCategoria(idaux);
					
					}
					catch(Exception npe){
						JOptionPane.showMessageDialog(NovaCategoria.this, "Erro ao selecionar Categorias: \n" +npe);
					}
					
					txtNome.setText(String.valueOf(categoria.getDescr()));
					txtId.setText(String.valueOf(categoria.getId()));
				}
				
			}
		});
		btnEdit.setBounds(882, 43, 42, 33);
		getContentPane().add(btnEdit);
		
		ImageIcon iconRe = new ImageIcon(NovaCategoria.class.getResource("/br/com/img/delete.png"));
		Image imaRe = iconRe.getImage();
		Image imagemRe = imaRe.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		Icon icoRe = new ImageIcon(imagemRe);
		
		JButton btnRemov = new JButton(icoRe);
		btnRemov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Categoria categoria = new Categoria();
				
				auxline = tableCateg.getSelectedRow();
				String id = tableCateg.getValueAt(auxline, 0).toString();
				
				if(id.equals("") || id.equals(null)) {
					
					JOptionPane.showMessageDialog(NovaCategoria.this, "Selecione uma linha para poder excluir!");
					
				}else {
				
					Long idaux = Long.valueOf(id);
					
					categoria = Conexao.selecionaCategoria(idaux);
					
					if(JOptionPane.showConfirmDialog(NovaCategoria.this, "Deseja mesmo excluir a Categoria selecionada?") == 0) {
					
						try {
							
							Conexao.removeCategoria(categoria);
					
						}
						catch(Exception npe){
							JOptionPane.showMessageDialog(NovaCategoria.this, "Erro ao remover Categoria: \n" +npe);
						}
							
						modelo.removeCateg(auxline);
						
						txtNome.setText("");
						txtId.setText("");
						
						JOptionPane.showMessageDialog(NovaCategoria.this, "Categoria removida com sucesso!");
					}
				
				}
			}
		});
		btnRemov.setBounds(828, 43, 42, 33);
		getContentPane().add(btnRemov);
		
		ImageIcon iconB = new ImageIcon(NovaCategoria.class.getResource("/br/com/img/back.png"));
		Image imaB = iconB.getImage();
		Image imagemB = imaB.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		Icon icoB = new ImageIcon(imagemB);
		
		JLabel label = new JLabel(icoB);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				NovaCategoria.this.dispose();
			}
		});
		label.setBounds(882, 6, 42, 25);
		getContentPane().add(label);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setEnabled(false);
		txtId.setBounds(752, 43, 64, 28);
		txtId.setVisible(false);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		JButton btnSubcategoria = new JButton("SubCategoria");
		btnSubcategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NovaSubCategoria nsb = new NovaSubCategoria();
				nsb.setResizable(false);
				nsb.setUndecorated(true);
				nsb.setLocationRelativeTo(null);
				nsb.setAlwaysOnTop(true);
				nsb.setVisible(true);
				
			}
		});
		btnSubcategoria.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnSubcategoria.setBounds(71, 247, 159, 33);
		getContentPane().add(btnSubcategoria);
		
		JButton btnItemcategoria = new JButton("ItemCategoria");
		btnItemcategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NovaItemCategoria nic = new NovaItemCategoria();
				nic.setResizable(false);
				nic.setUndecorated(true);
				nic.setLocationRelativeTo(null);
				nic.setAlwaysOnTop(true);
				nic.setVisible(true);
				
			}
		});
		btnItemcategoria.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnItemcategoria.setBounds(242, 247, 175, 33);
		getContentPane().add(btnItemcategoria);
		
		//getContentPane().add(tableCateg);
	
	}
}
