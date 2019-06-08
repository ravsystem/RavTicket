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
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
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
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnNewButton.setBounds(10, 386, 89, 33);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Limpar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				cbCateg.setSelectedIndex(0);
			}
		});
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnNewButton_1.setBounds(109, 386, 106, 33);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Atualizar");
		btnNewButton_2.addActionListener(new ActionListener() {
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
		btnNewButton_2.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnNewButton_2.setBounds(225, 386, 115, 33);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Voltar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Nova_SubCategoria.this.dispose();
			}
		});
		btnNewButton_3.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnNewButton_3.setBounds(350, 386, 89, 33);
		getContentPane().add(btnNewButton_3);
		
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
		
		ImageIcon iconEdit = new ImageIcon(Novo_agente.class.getResource("/br/com/img/edit.png"));
		Image imaEdit = iconEdit.getImage();
		Image imagemEdit = imaEdit.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		Icon icoEdit = new ImageIcon(imagemEdit);
		
		JButton button = new JButton(icoEdit);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SubCategoria subcategoria = new SubCategoria();
				
				auxline = table.getSelectedRow();
				String id = table.getValueAt(auxline, 0).toString();
				
				Long idaux = Long.valueOf(id);
				
				subcategoria = Conexao.selecionaSubCategoria(idaux);
				
				txtNome.setText(String.valueOf(subcategoria.getDescr()));
				txtId.setText(String.valueOf(subcategoria.getIn()));
				cbCateg.setSelectedItem(subcategoria.getCategoria());
			}
		});
		button.setBounds(782, 46, 42, 33);
		getContentPane().add(button);
		
		ImageIcon iconRe = new ImageIcon(Novo_agente.class.getResource("/br/com/img/delete.png"));
		Image imaRe = iconRe.getImage();
		Image imagemRe = imaRe.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		Icon icoRe = new ImageIcon(imagemRe);
		
		JButton button_1 = new JButton(icoRe);
		button_1.addActionListener(new ActionListener() {
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
		button_1.setBounds(728, 46, 42, 33);
		getContentPane().add(button_1);
		
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
