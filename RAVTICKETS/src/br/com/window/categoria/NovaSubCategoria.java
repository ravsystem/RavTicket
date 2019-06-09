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
import br.com.model.Categoria;
import br.com.model.SubCategoria;
import br.com.model.SubCategoriaTableModel;
import br.com.window.agente.Novo_agente;

public class NovaSubCategoria extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTable table;
	private static int auxline;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovaSubCategoria frame = new NovaSubCategoria();
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
	public NovaSubCategoria() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		List<Categoria> categs = Conexao.listarCateg();
		String[] tp_test = new String[categs.size()];
		for(int i = 0; i < categs.size(); i++) {
			tp_test[i] = categs.get(i).getDescr();
		}
		
		List<SubCategoria> Sbcategs = Conexao.listarSubCateg();
		
		ArrayList dados = new ArrayList();
		
		for(int i = 0; i < Sbcategs.size(); i++) {
			
			SubCategoria Sbcateg = new SubCategoria();
			Sbcateg.setIn(Sbcategs.get(i).getIn());
			Sbcateg.setDescr(Sbcategs.get(i).getDescr());
			Sbcateg.setCategoria(Sbcategs.get(i).getCategoria());
			dados.add(Sbcateg);
		}
		
		SubCategoriaTableModel modelo = new SubCategoriaTableModel(dados);
		
		JLabel lblCadastrarNovaSubcategoria = new JLabel("Cadastrar Nova SubCategoria");
		lblCadastrarNovaSubcategoria.setBounds(10, 11, 665, 61);
		lblCadastrarNovaSubcategoria.setFont(new Font("SansSerif", Font.BOLD, 45));
		
		JLabel lblNome = new JLabel("Nome.:");
		lblNome.setBounds(10, 91, 96, 24);
		lblNome.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JLabel lblNewLabel = new JLabel("Categoria.:");
		lblNewLabel.setBounds(10, 131, 96, 24);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		txtNome = new JTextField();
		txtNome.setBounds(116, 90, 283, 27);
		txtNome.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtNome.setColumns(10);
		
		JComboBox cbCateg = new JComboBox(tp_test);
		cbCateg.setBounds(116, 128, 283, 30);
		cbCateg.setFont(new Font("SansSerif", Font.PLAIN, 18));
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		getContentPane().add(lblCadastrarNovaSubcategoria);
		getContentPane().add(lblNome);
		getContentPane().add(lblNewLabel);
		getContentPane().add(txtNome);
		getContentPane().add(cbCateg);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SubCategoria sb = new SubCategoria();
				
				sb.setDescr(txtNome.getText());
				sb.setCategoria(cbCateg.getSelectedItem().toString());
				
				if(sb.getDescr().equals("") || sb.getDescr().equals(null)) {
					
					JOptionPane.showMessageDialog(NovaSubCategoria.this,"Para poder gravar uma SubCategoria escreva um nome pelomenos e selecione uma Categoria!");
					
				}else if(sb.getCategoria().equals("") || sb.getCategoria().equals(null)) {
					
					JOptionPane.showMessageDialog(NovaSubCategoria.this,"Caso ainda ainda não tenha cadastrado uma Categoria, cadastre uma antes para poder Cadastrar uma SubCategoria");
					
				}else {	
				
					try {	
						
						Conexao.guardar(sb);
					
					}catch(NullPointerException f) {
						JOptionPane.showMessageDialog(NovaSubCategoria.this,"Ops.. Deve ter faltado preencher algo ai moral: \n" +f);
					}
					catch(Exception npe){
						JOptionPane.showMessageDialog(NovaSubCategoria.this, "Ops.. Erro ao gravar SubCategoria: \n" +npe);
					}
					
					txtNome.setText("");
					cbCateg.setSelectedIndex(0);
					txtId.setText("");
					
					modelo.addSubCategoria(sb);
					table.getModel();
					
					JOptionPane.showMessageDialog(NovaSubCategoria.this, "SubCategoria gravada com sucesso!");
				
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
				cbCateg.setSelectedIndex(0);
				txtId.setText("");
			}
		});
		btnLimpar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnLimpar.setBounds(109, 382, 106, 33);
		getContentPane().add(btnLimpar);
		
		JButton btnAtt = new JButton("Atualizar");
		btnAtt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SubCategoria subcategoriaremovetable = new SubCategoria();
				SubCategoria subcategoria = new SubCategoria();
				
				String id = txtId.getText();
				
				if(id.equals("") || id.equals(null)) {
					
					JOptionPane.showMessageDialog(NovaSubCategoria.this, "Selecione uma linha, depois clique no pequeno Lápis acima da tabela para poder editar!");
					
				}else {
				
					Long idaux = Long.valueOf(id);
					
					try {
					
					subcategoriaremovetable = Conexao.selecionaSubCategoria(idaux);
					
					}catch(NullPointerException f) {
						JOptionPane.showMessageDialog(NovaSubCategoria.this,"Ops.. Deve ter faltado preencher algo ai moral: \n" +f);
					}
					catch(Exception npe){
						JOptionPane.showMessageDialog(NovaSubCategoria.this, "Ops.. Erro ao selecionar SubCategoria para remover da tabela.. \n" +npe);
					}
					
					subcategoria.setIn(idaux);
					subcategoria.setDescr(txtNome.getText());
					subcategoria.setCategoria(cbCateg.getSelectedItem().toString());
					
					modelo.removeSubCategoria(auxline);
					
					try {
					
					Conexao.alterar(subcategoria);
					
					}catch(NullPointerException f) {
						JOptionPane.showMessageDialog(NovaSubCategoria.this,"Ops.. Deve ter faltado preencher algo ai moral: \n" +f);
					}
					catch(Exception npe){
						JOptionPane.showMessageDialog(NovaSubCategoria.this, "Ops.. Erro ao alterar SubCategoria.. \n" +npe);
					}
					
					txtNome.setText("");
					txtId.setText("");
					cbCateg.setSelectedIndex(0);
					
					modelo.addSubCategoria(subcategoria);
					table.getModel();
					
					JOptionPane.showMessageDialog(NovaSubCategoria.this, "SubCategoria atualizada com sucesso!");
				}
			}
		});
		btnAtt.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnAtt.setBounds(324, 382, 115, 33);
		btnAtt.setVisible(false);
		btnAtt.setVisible(false);
		getContentPane().add(btnAtt);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NovaSubCategoria.this.dispose();
			}
		});
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnVoltar.setBounds(225, 382, 89, 33);
		getContentPane().add(btnVoltar);
		
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
		table.getColumnModel().getColumn(1).setPreferredWidth(240);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setResizable(false);
		
		table.setBounds(449, 91, 213, 328);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(451, 91, 473, 324);
		getContentPane().add(scrollPane);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSalvar.setVisible(true);
				btnAtt.setVisible(false);
				
				txtNome.setText("");
				txtId.setText("");
				cbCateg.setSelectedIndex(0);
				
				btnNovo.setVisible(false);
			}
		});
		btnNovo.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnNovo.setBounds(10, 187, 89, 33);
		btnNovo.setVisible(false);
		getContentPane().add(btnNovo);
		
		ImageIcon iconEdit = new ImageIcon(Novo_agente.class.getResource("/br/com/img/edit.png"));
		Image imaEdit = iconEdit.getImage();
		Image imagemEdit = imaEdit.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		Icon icoEdit = new ImageIcon(imagemEdit);
		
		JButton btnEdit = new JButton(icoEdit);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SubCategoria subcategoria = new SubCategoria();
				
				auxline = table.getSelectedRow();
				String id = table.getValueAt(auxline, 0).toString();
				
				if(id.equals("") || id.equals(null)) {
					
					JOptionPane.showMessageDialog(NovaSubCategoria.this, "Selecione uma linha para poder editar!");
					
				}else {
					
					btnSalvar.setVisible(false);
					btnAtt.setVisible(true);
					btnNovo.setVisible(true);
				
					Long idaux = Long.valueOf(id);
					
					try {
					
					subcategoria = Conexao.selecionaSubCategoria(idaux);
					
					}
					catch(Exception npe){
						JOptionPane.showMessageDialog(NovaSubCategoria.this, "Erro ao selecionar SubCategoria: \n" +npe);
					}
					
					//System.out.println(tp_test[0] +"    " +subcategoria.getCategoria());
					
					txtNome.setText(String.valueOf(subcategoria.getDescr()));
					txtId.setText(String.valueOf(subcategoria.getIn()));
					cbCateg.setSelectedItem(subcategoria.getCategoria());
				
				}
			}
		});
		btnEdit.setBounds(882, 51, 42, 33);
		getContentPane().add(btnEdit);
		
		ImageIcon iconRe = new ImageIcon(Novo_agente.class.getResource("/br/com/img/delete.png"));
		Image imaRe = iconRe.getImage();
		Image imagemRe = imaRe.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		Icon icoRe = new ImageIcon(imagemRe);
		
		JButton btnRemov = new JButton(icoRe);
		btnRemov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SubCategoria subcategoria = new SubCategoria();
				
				auxline = table.getSelectedRow();
				String id = table.getValueAt(auxline, 0).toString();
				
				if(id.equals("") || id.equals(null)) {
					
					JOptionPane.showMessageDialog(NovaSubCategoria.this, "Selecione uma linha para poder excluir!");
					
				}else {
				
					Long idaux = Long.valueOf(id);
					
					subcategoria = Conexao.selecionaSubCategoria(idaux);
					
					if(JOptionPane.showConfirmDialog(NovaSubCategoria.this, "Deseja mesmo excluir a SubCategoria selecionada?") == 0) {
					
						try {	
							
							Conexao.removeSubCategoria(subcategoria);
						
						}
						catch(Exception npe){
							JOptionPane.showMessageDialog(NovaSubCategoria.this, "Erro ao remover SubCategoria: \n" +npe);
						}
						
						modelo.removeSubCategoria(auxline);
						
						
						
						txtNome.setText("");
						cbCateg.setSelectedIndex(0);
						txtId.setText("");
						
						JOptionPane.showMessageDialog(NovaSubCategoria.this, "SubCategoria removida com sucesso!");
					
					}
				
				}
				
			}
		});
		btnRemov.setBounds(828, 51, 42, 33);
		getContentPane().add(btnRemov);
		
		ImageIcon iconB = new ImageIcon(Novo_agente.class.getResource("/br/com/img/back.png"));
		Image imaB = iconB.getImage();
		Image imagemB = imaB.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		Icon icoB = new ImageIcon(imagemB);
		
		JLabel label = new JLabel(icoB);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				NovaSubCategoria.this.dispose();
			}
		});
		label.setBounds(882, 11, 42, 25);
		getContentPane().add(label);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setBounds(774, 56, 42, 28);
		txtId.setVisible(false);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		
		

		//getContentPane().add(table);

	}

}
