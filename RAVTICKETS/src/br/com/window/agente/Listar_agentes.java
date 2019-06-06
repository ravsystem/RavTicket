package br.com.window.agente;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class Listar_agentes extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Listar_agentes frame = new Listar_agentes();
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
	public Listar_agentes() {
		setBounds(100, 100, 450, 300);

	}

}
