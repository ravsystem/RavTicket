package br.com.window.cliente;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JList;
import java.awt.BorderLayout;

public class Listar_clientes extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Listar_clientes frame = new Listar_clientes();
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
	public Listar_clientes() {
		setBounds(100, 100, 450, 300);
		
		JList list = new JList();
		getContentPane().add(list, BorderLayout.NORTH);

	}

}
