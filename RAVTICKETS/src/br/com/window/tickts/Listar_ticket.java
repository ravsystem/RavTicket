package br.com.window.tickts;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class Listar_ticket extends JInternalFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Listar_ticket frame = new Listar_ticket();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Listar_ticket() {
		setBounds(100, 100, 450, 300);

	}

}
