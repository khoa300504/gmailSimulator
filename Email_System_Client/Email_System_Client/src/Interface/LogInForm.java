package Interface;

import java.awt.EventQueue;
import model.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LogInForm extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField tfUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LogInForm frame = new LogInForm();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public static void main(String[] args) {
		LogInForm a = new LogInForm();
		a.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public LogInForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 436);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_img = new JPanel();
		panel_img.setBackground(Color.WHITE);
		panel_img.setBounds(10, 0, 491, 399);
		contentPane.add(panel_img);
		panel_img.setLayout(null);
		
		JLabel lbImage = new JLabel("");
		lbImage.setBounds(28, 22, 418, 351);
		lbImage.setIcon(new ImageIcon(LogInForm.class.getResource("/Image_Icon/lll.png")));
		panel_img.add(lbImage);
		
		
		JPanel panel_infor = new JPanel();
		panel_infor.setBackground(SystemColor.textHighlight);
		panel_infor.setBounds(492, 0, 420, 410);
		contentPane.add(panel_infor);
		panel_infor.setLayout(null);
		
		JLabel lbLogIn = new JLabel("Login");
		lbLogIn.setBounds(10, 36, 402, 43);
		lbLogIn.setForeground(Color.WHITE);
		lbLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lbLogIn.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		panel_infor.add(lbLogIn);
		
		JLabel lbHeader = new JLabel("Hello! Let's get started!");
		lbHeader.setBounds(10, 89, 402, 23);
		lbHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lbHeader.setForeground(Color.WHITE);
		lbHeader.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_infor.add(lbHeader);
		
		JLabel lbUsername = new JLabel("Username");
		lbUsername.setBounds(46, 139, 81, 29);
		lbUsername.setForeground(new Color(255, 255, 255));
		lbUsername.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panel_infor.add(lbUsername);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(126, 139, 228, 36);
		tfUsername.setHorizontalAlignment(SwingConstants.LEFT);
		panel_infor.add(tfUsername);
		tfUsername.setColumns(10);
		
		JLabel lbPassword = new JLabel("Password");
		lbPassword.setBounds(45, 205, 94, 23);
		lbPassword.setForeground(new Color(255, 255, 255));
		lbPassword.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panel_infor.add(lbPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(126, 202, 228, 36);
		panel_infor.add(passwordField);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(this);
		btnLogin.setBounds(180, 271, 118, 36);
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnLogin.setBackground(new Color(255, 255, 255));
		btnLogin.setForeground(SystemColor.textHighlight);
		panel_infor.add(btnLogin);
		
		JLabel lblNewAccount = new JLabel("Don't have an account? ");
		lblNewAccount.setBounds(108, 346, 183, 23);
		lblNewAccount.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewAccount.setForeground(new Color(255, 255, 255));
		panel_infor.add(lblNewAccount);
		
		JLabel lbSignUp = new JLabel("Sign Up");
		lbSignUp.setBounds(276, 346, 78, 21);
		lbSignUp.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lbSignUp.setForeground(new Color(255, 255, 255));
		panel_infor.add(lbSignUp);
	}
	
	public void sendObjectToServer()  throws IOException{
		int loginrs;
		Socket socket = new Socket("localhost", 7777);
		OutputStream outputStream = socket.getOutputStream();
		InputStream inputStream = socket.getInputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		ta_kda_user user_login = new ta_kda_user();
		user_login.setT_EmailAddress(tfUsername.getText());
		user_login.setT_PassWord(passwordField.getText());
		objectOutputStream.writeObject(user_login);
		loginrs = inputStream.read();
		if(loginrs == 0)
		{
			JOptionPane.showMessageDialog(contentPane, "Wrong! Check and type again");
		}
		else
		{
			JOptionPane.showMessageDialog(contentPane, "Login Success <3");
			this.dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equals("LOGIN"))
		{
			try {
				sendObjectToServer();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
