package Interface;

import java.awt.EventQueue;
import model.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JButton;

public class SuIf extends JFrame implements ActionListener{

	private JPanel contentPane;
	private static JTextField tfPhoneNumber;
	private static JTextField tfBirthday;
	private static JPasswordField passwordField;
	private static JTextField tfEmailAddress;
	private static JTextField tfFullName;
	private ta_kda_user user_info;
	private int id;
	SuIf user_form;
	lgIf a;
	
	public SuIf() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 650);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.text);
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbImage = new JLabel("");
		lbImage.setHorizontalAlignment(SwingConstants.CENTER);
		lbImage.setIcon(new ImageIcon(SuIf.class.getResource("/Image_Icon/ll.png")));
		lbImage.setBounds(46, 21, 85, 101);
		contentPane.add(lbImage);
		
		JLabel lbHeader = new JLabel("Register");
		lbHeader.setForeground(SystemColor.text);
		lbHeader.setFont(new Font("Segoe UI", Font.BOLD, 72));
		lbHeader.setBounds(172, 21, 299, 101);
		contentPane.add(lbHeader);
		
		JLabel lbFullName = new JLabel("FullName");
		lbFullName.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lbFullName.setForeground(SystemColor.text);
		lbFullName.setBounds(59, 145, 85, 31);
		contentPane.add(lbFullName);
		
		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setBounds(216, 410, 242, 37);
		contentPane.add(tfPhoneNumber);
		tfPhoneNumber.setColumns(10);
		
		JLabel lbEmailAddress = new JLabel("Email Address");
		lbEmailAddress.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lbEmailAddress.setForeground(SystemColor.text);
		lbEmailAddress.setBounds(59, 210, 103, 25);
		contentPane.add(lbEmailAddress);
		
		tfBirthday = new JTextField();
		tfBirthday.setBounds(216, 333, 242, 37);
		contentPane.add(tfBirthday);
		tfBirthday.setColumns(10);
		
		JLabel lbPassword = new JLabel("Password");
		lbPassword.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lbPassword.setForeground(SystemColor.text);
		lbPassword.setBounds(59, 268, 85, 25);
		contentPane.add(lbPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(216, 265, 242, 37);
		contentPane.add(passwordField);
		
		JLabel lbBirthday = new JLabel("BirthDay");
		lbBirthday.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lbBirthday.setForeground(SystemColor.text);
		lbBirthday.setBounds(59, 333, 85, 25);
		contentPane.add(lbBirthday);
		
		tfEmailAddress = new JTextField();
		tfEmailAddress.setBounds(216, 207, 242, 37);
		contentPane.add(tfEmailAddress);
		tfEmailAddress.setColumns(10);
		
		JLabel lbPhoneNumber = new JLabel("PhoneNumber");
		lbPhoneNumber.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lbPhoneNumber.setForeground(SystemColor.text);
		lbPhoneNumber.setBounds(59, 410, 122, 25);
		contentPane.add(lbPhoneNumber);
		
		tfFullName = new JTextField();
		tfFullName.setBounds(216, 145, 242, 37);
		contentPane.add(tfFullName);
		tfFullName.setColumns(10);
		
		JButton btnSignUp = new JButton("SIGN UP");
		btnSignUp.addActionListener(this);
		btnSignUp.setBackground(SystemColor.text);
		btnSignUp.setForeground(SystemColor.textHighlight);
		btnSignUp.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnSignUp.setBounds(85, 533, 122, 37);
		contentPane.add(btnSignUp);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setBackground(SystemColor.text);
		btnCancel.setForeground(SystemColor.textHighlight);
		btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnCancel.setBounds(275, 533, 122, 37);
		contentPane.add(btnCancel);
	}
	
	
	
	public static JTextField getTfPhoneNumber() {
		return tfPhoneNumber;
	}



	public void setTfPhoneNumber(JTextField tfPhoneNumber) {
		this.tfPhoneNumber = tfPhoneNumber;
	}



	public static JTextField getTfBirthday() {
		return tfBirthday;
	}



	public void setTfBirthday(JTextField tfBirthday) {
		this.tfBirthday = tfBirthday;
	}



	public static JPasswordField getPasswordField() {
		return passwordField;
	}



	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}



	public static JTextField getTfEmailAddress() {
		return tfEmailAddress;
	}



	public void setTfEmailAddress(JTextField tfEmailAddress) {
		this.tfEmailAddress = tfEmailAddress;
	}



	public static JTextField getTfFullName() {
		return tfFullName;
	}



	public void setTfFullName(JTextField tfFullName) {
		this.tfFullName = tfFullName;
	}



	public void sendObjectToServer() throws IOException
	{
		Socket socket = new Socket("localhost", 7777);
		System.out.println("Connected!");
//		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
//		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
//		OutputStream outputStream = socket.getOutputStream();
//		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        String request = "register";
        output.println(request);
        output.println(tfEmailAddress.getText());
        output.println(passwordField.getText());
        output.println(tfFullName.getText());
        output.println(tfBirthday.getText());
        output.println(tfPhoneNumber.getText());
        String response = input.readLine();
        JOptionPane.showMessageDialog(contentPane, response);
		lgIf a = new lgIf();
		a.setVisible(true);
        this.dispose();
//		List<ta_kda_user> user_dk = new ArrayList<>();
//		ta_kda_user abc = new ta_kda_user();
//		abc.setT_EmailAddress(tfEmailAddress.getText());
//		abc.setT_PassWord(passwordField.getText());
//		abc.setT_FullName(tfFullName.getText());
//		abc.setT_BirthDay(tfBirthday.getText());
//		abc.setT_PhoneNumber(tfPhoneNumber.getText());
//		user_dk.add(abc);
//		System.out.println("Sending info.....");
//		objectOutputStream.writeObject(abc);
//		InputStream inputStream = socket.getInputStream();
//		int check = inputStream.read();
//		if(check == 3)
//		{
//			JOptionPane.showMessageDialog(contentPane, "Tạo tài khoản thành công!, đăng nhập để sử dụng!");
//			this.dispose();
//			a = new LogInForm();
//			a.setVisible(true);
//		}
//		System.out.println("Closing program.....");
		socket.close();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equals("SIGN UP"))
		{
			try {
				sendObjectToServer();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		SuIf user = new SuIf();
		user.setVisible(true);
	}
}
