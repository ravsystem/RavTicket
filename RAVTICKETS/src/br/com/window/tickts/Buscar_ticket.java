package br.com.window.tickts;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class Buscar_ticket extends JInternalFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar_ticket frame = new Buscar_ticket();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Buscar_ticket() {
		setBounds(100, 100, 450, 300);

	}

}
