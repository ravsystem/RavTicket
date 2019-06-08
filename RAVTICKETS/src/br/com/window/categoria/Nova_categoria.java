package br.com.window.categoria;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import br.com.connection.Conexao;
import br.com.model.Agente;
import br.com.model.AgenteTableModel;
import br.com.model.Categoria;
import br.com.model.CategoriaTableModel;
import br.com.window.agente.Novo_agente;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Nova_categoria extends JInternalFrame {
	private JTextField txtNome;
	private JTable tableCateg;
	private static int auxline;
	private JTextField txtId;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Nova_categoria frame = new Nova_categoria();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Nova_categoria() {
		setBounds(100, 100, 950, 460);
		
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
		scrollPane.setBounds(494, 80, 430, 339);
		getContentPane().setLayout(null);
		getContentPane().add(lblCadastroDeCategoria);
		getContentPane().add(lblNome);
		getContentPane().add(txtNome);
		getContentPane().add(scrollPane);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Categoria categoria = new Categoria();
				
				categoria.setDescr(txtNome.getText());
				
				Conexao.guardar(categoria);
				
				txtNome.setText("");
				
				modelo.addCateg(categoria);
				tableCateg.getModel();
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnNewButton.setBounds(10, 386, 89, 33);
		getContentPane().add(btnNewButton);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtNome.setText("");
			}
		});
		btnLimpar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnLimpar.setBounds(109, 386, 105, 33);
		getContentPane().add(btnLimpar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Categoria categoriaremovetable = new Categoria();
				Categoria categoria = new Categoria();
				
				String id = txtId.getText();
				Long idaux = Long.valueOf(id);
				
				categoriaremovetable = Conexao.selecionaCategoria(idaux);
				
				categoria.setId(idaux);
				categoria.setDescr(txtNome.getText());
				
				modelo.removeCateg(auxline);
				
				Conexao.alterar(categoria);
				
				txtNome.setText("");
				txtId.setText("");
				
				modelo.addCateg(categoria);
				tableCateg.getModel();
			}
		});
		btnAtualizar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnAtualizar.setBounds(224, 386, 112, 33);
		getContentPane().add(btnAtualizar);
		
		JButton btnVoltar = new JButton("voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Nova_categoria.this.dispose();
			}
		});
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnVoltar.setBounds(346, 386, 89, 33);
		getContentPane().add(btnVoltar);
		
		ImageIcon iconEdit = new ImageIcon(Novo_agente.class.getResource("/br/com/img/edit.png"));
		Image imaEdit = iconEdit.getImage();
		Image imagemEdit = imaEdit.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		Icon icoEdit = new ImageIcon(imagemEdit);
		
		JButton btnEdit = new JButton(icoEdit);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Categoria categoria = new Categoria();
				
				auxline = tableCateg.getSelectedRow();
				String id = tableCateg.getValueAt(auxline, 0).toString();
				
				Long idaux = Long.valueOf(id);
				
				categoria = Conexao.selecionaCategoria(idaux);
				
				txtNome.setText(String.valueOf(categoria.getDescr()));
				txtId.setText(String.valueOf(categoria.getId()));
				
				
			}
		});
		btnEdit.setBounds(882, 43, 42, 33);
		getContentPane().add(btnEdit);
		
		ImageIcon iconRe = new ImageIcon(Novo_agente.class.getResource("/br/com/img/delete.png"));
		Image imaRe = iconRe.getImage();
		Image imagemRe = imaRe.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		Icon icoRe = new ImageIcon(imagemRe);
		
		JButton btnRemov = new JButton(icoRe);
		btnRemov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Categoria categoria = new Categoria();
				
				auxline = tableCateg.getSelectedRow();
				String id = tableCateg.getValueAt(auxline, 0).toString();
				
				Long idaux = Long.valueOf(id);
				
				categoria = Conexao.selecionaCategoria(idaux);
				
				Conexao.removeCategoria(categoria);
				
				modelo.removeCateg(auxline);
			}
		});
		btnRemov.setBounds(828, 43, 42, 33);
		getContentPane().add(btnRemov);
		
		ImageIcon iconB = new ImageIcon(Novo_agente.class.getResource("/br/com/img/back.png"));
		Image imaB = iconB.getImage();
		Image imagemB = imaB.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		Icon icoB = new ImageIcon(imagemB);
		
		JLabel label = new JLabel(icoB);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				Nova_categoria.this.dispose();
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
				
				
			}
		});
		btnSubcategoria.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnSubcategoria.setBounds(84, 189, 146, 33);
		getContentPane().add(btnSubcategoria);
		
		JButton btnItemcategoria = new JButton("ItemCategoria");
		btnItemcategoria.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnItemcategoria.setBounds(242, 189, 164, 33);
		getContentPane().add(btnItemcategoria);
		
		//getContentPane().add(tableCateg);
		
		

	}
}
