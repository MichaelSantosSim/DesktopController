package com.michael.desktopcontrol.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import com.michael.desktopcontrol.control.CommandManager;
import com.michael.desktopcontrol.control.SystemTrayManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

public class DebugWindow extends JFrame {

	private JPanel contentPane;
	
	CommandManager cm = CommandManager.getInstance();
	private static int serial = 0;
	private JTextField fieldMin;


	/**
	 * Create the frame.
	 */
	public DebugWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Debug");
		setBounds(100, 100, 465, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnChapolin = new JButton("Ver Big Bang Theory");
		btnChapolin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					cm.runCommand("chrome", "bigbang");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				
			}
		});
		btnChapolin.setBounds(12, 300, 170, 30);
		contentPane.add(btnChapolin);
		
		JButton btnShutdown = new JButton("Desligar em");
		btnShutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					int seconds = Integer.valueOf(fieldMin.getText()) * 60;
					
					cm.runCommand("shutdown", "-s -t " + String.valueOf(seconds));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnShutdown.setBounds(12, 33, 115, 25);
		contentPane.add(btnShutdown);
		
		fieldMin = new JTextField();
		fieldMin.setText("60");
		fieldMin.setColumns(6);
		fieldMin.setBounds(141, 34, 74, 22);
		contentPane.add(fieldMin);
		
		JLabel lblMinutos = new JLabel("Minutos");
		lblMinutos.setBounds(227, 37, 56, 16);
		contentPane.add(lblMinutos);
		
		JButton btnCancelShutdown = new JButton("Cancelar Desligamento");
		btnCancelShutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cm.runCommand("shutdown", "-a");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCancelShutdown.setBounds(12, 76, 170, 25);
		contentPane.add(btnCancelShutdown);
		
		JButton btnRenew = new JButton("Ipconfig -renew");
		btnRenew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cm.runCommand("ipconfig", "-renew");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRenew.setBounds(12, 114, 154, 25);
		contentPane.add(btnRenew);
		
		JButton btnFullscreen = new JButton("Trocar Tela Cheia");
		btnFullscreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cm.runCommand("toggleFullScreen", "");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnFullscreen.setBounds(12, 160, 142, 25);
		contentPane.add(btnFullscreen);
		
		JButton btnReload = new JButton("Recarregar");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cm.runCommand("reload", "dummy");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnReload.setBounds(179, 160, 97, 25);
		contentPane.add(btnReload);
		
		JLabel lblVolume = new JLabel("Volume");
		lblVolume.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVolume.setBounds(12, 208, 80, 16);
		contentPane.add(lblVolume);
		
		JButton volUp = new JButton("+");
		volUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cm.runCommand("volume", "+");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		volUp.setBounds(88, 206, 41, 25);
		contentPane.add(volUp);
		
		JButton volDown = new JButton("-");
		volDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cm.runCommand("volume", "-");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		volDown.setBounds(142, 206, 41, 25);
		contentPane.add(volDown);
		
		JButton btnChaves = new JButton("Ver Chaves");
		btnChaves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cm.runCommand("chrome", "chaves");
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					System.out.println(ex.getMessage());
				}
			}
		});
		btnChaves.setBounds(12, 344, 170, 25);
		contentPane.add(btnChaves);
		
		JButton btnVerChapolin = new JButton("Ver Chapolin");
		btnVerChapolin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cm.runCommand("chrome", "chapolin");
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					System.out.println(ex.getMessage());
				}
			}
		});
		btnVerChapolin.setBounds(12, 382, 170, 25);
		contentPane.add(btnVerChapolin);
		
		JLabel label2 = new JLabel("Brilho");
		label2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label2.setBounds(12, 252, 56, 16);
		contentPane.add(label2);
		
		JButton btnBrightUp = new JButton("+");
		btnBrightUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cm.runCommand("brightness", "+");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBrightUp.setBounds(88, 244, 41, 25);
		contentPane.add(btnBrightUp);
		
		JButton btnBrightDown = new JButton("-");
		btnBrightDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cm.runCommand("brightness", "-");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBrightDown.setBounds(141, 244, 41, 25);
		contentPane.add(btnBrightDown);
	}
}
