package br.com.window.categoria;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import br.com.connection.Conexao;
import br.com.model.Categoria;
import br.com.model.CategoriaTableModel;
import br.com.model.SubCategoria;
import br.com.model.SubCategoriaTableModel;
import br.com.window.agente.Novo_agente;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Nova_SubCategoria extends JInternalFrame {
	private JTextField txtNome;
	private JTable table;
	private static int auxline;
	private JTextField txtId;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Nova_SubCategoria frame = new Nova_SubCategoria();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Nova_SubCategoria() {
		setBounds(100, 100, 850, 460);
		
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
				
				Conexao.guardar(sb);
				
				txtNome.setText("");
				cbCateg.setSelectedIndex(0);
				
				modelo.addSubCategoria(sb);
				table.getModel();
			}
		});
		btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnSalvar.setBounds(10, 386, 89, 33);
		getContentPane().add(btnSalvar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				cbCateg.setSelectedIndex(0);
			}
		});
		btnLimpar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnLimpar.setBounds(109, 386, 106, 33);
		getContentPane().add(btnLimpar);
		
		JButton btnAtt = new JButton("Atualizar");
		btnAtt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SubCategoria subcategoriaremovetable = new SubCategoria();
				SubCategoria subcategoria = new SubCategoria();
				
				String id = txtId.getText();
				Long idaux = Long.valueOf(id);
				
				subcategoriaremovetable = Conexao.selecionaSubCategoria(idaux);
				
				subcategoria.setIn(idaux);
				subcategoria.setDescr(txtNome.getText());
				subcategoria.setCategoria(cbCateg.getSelectedItem().toString());
				
				modelo.removeSubCategoria(auxline);
				
				Conexao.alterar(subcategoria);
				
				txtNome.setText("");
				txtId.setText("");
				cbCateg.setSelectedIndex(0);
				
				modelo.addSubCategoria(subcategoria);
				table.getModel();
			}
		});
		btnAtt.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnAtt.setBounds(225, 386, 115, 33);
		btnAtt.setVisible(false);
		getContentPane().add(btnAtt);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Nova_SubCategoria.this.dispose();
			}
		});
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnVoltar.setBounds(350, 386, 89, 33);
		getContentPane().add(btnVoltar);
		
		table = new JTable();

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table.setToolTipText("");
		table.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(270);
		table.getColumnModel().getColumn(1).setResizable(false);
		
		table.setBounds(449, 91, 213, 328);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(451, 91, 373, 328);
		getContentPane().add(scrollPane);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSalvar.setVisible(true);
				btnAtt.setVisible(false);
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
				
				btnSalvar.setVisible(false);
				btnAtt.setVisible(true);
				btnNovo.setVisible(true);
				
				SubCategoria subcategoria = new SubCategoria();
				
				auxline = table.getSelectedRow();
				String id = table.getValueAt(auxline, 0).toString();
				
				Long idaux = Long.valueOf(id);
				
				subcategoria = Conexao.selecionaSubCategoria(idaux);
					
				/*for(int i = 0; i > categs.size(); i++) {
					
					if(tp_test[i].equals(categTest)) {
						index = i;
					}
				}
					
				System.out.println(tp_test[0].toString() +"      " +subcategoria.getCategoria() +"   " +subcategoria.getDescr() +"     " +index);
				*/
				
				txtNome.setText(String.valueOf(subcategoria.getDescr()));
				txtId.setText(String.valueOf(subcategoria.getIn()));
				cbCateg.setSelectedItem(subcategoria.getCategoria());
			}
		});
		btnEdit.setBounds(782, 46, 42, 33);
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
				
				Long idaux = Long.valueOf(id);
				
				subcategoria = Conexao.selecionaSubCategoria(idaux);
				
				Conexao.removeSubCategoria(subcategoria);
				
				modelo.removeSubCategoria(auxline);
				
			}
		});
		btnRemov.setBounds(728, 46, 42, 33);
		getContentPane().add(btnRemov);
		
		ImageIcon iconB = new ImageIcon(Novo_agente.class.getResource("/br/com/img/back.png"));
		Image imaB = iconB.getImage();
		Image imagemB = imaB.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		Icon icoB = new ImageIcon(imagemB);
		
		JLabel label = new JLabel(icoB);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Nova_SubCategoria.this.dispose();
			}
		});
		label.setBounds(782, 6, 42, 25);
		getContentPane().add(label);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setBounds(674, 51, 42, 28);
		txtId.setVisible(false);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		
		

		//getContentPane().add(table);

	}
}
