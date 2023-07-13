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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JButton;

public class SignUpForm extends JFrame implements ActionListener{

	private JPanel contentPane;
	private static JTextField tfPhoneNumber;
	private static JTextField tfBirthday;
	private static JPasswordField passwordField;
	private static JTextField tfEmailAddress;
	private static JTextField tfFullName;
	private ta_kda_user user_info;
	private int id;
	SignUpForm user_form;
	lgIf a;
	
	public SignUpForm() {
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
		lbImage.setIcon(new ImageIcon(SignUpForm.class.getResource("/Image_Icon/ll.png")));
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
	
	public class checkEmail {
	    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

	    public static boolean isValidEmail(String email) {
	        Matcher matcher = EMAIL_PATTERN.matcher(email);
	        return matcher.matches();
	    }
	}

	private boolean checkFields() {
	    String emailAddress = tfEmailAddress.getText();
	    String fullName = tfFullName.getText();
	    String birthday = tfBirthday.getText();
	    String phoneNumber = tfPhoneNumber.getText();
	    char[] password = passwordField.getPassword();
	    
	 
	    // Full Name
	    if (fullName.isEmpty()) {
	        JOptionPane.showMessageDialog(null, " Vui lòng nhập Full Name.", "Lỗi !!!", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    // Email Address
	    if (emailAddress.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Vui lòng nhập Email Address", "Lỗi !!!", JOptionPane.ERROR_MESSAGE);
	        return false;
	    } else if (!checkEmail.isValidEmail(emailAddress)) {
	        JOptionPane.showMessageDialog(null, "Định dạng email không chính xác! Vui lòng nhập lại!", "Lỗi !!!", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    
	    // Password
	    if (password.length == 0) {
	        JOptionPane.showMessageDialog(null, "Vui lòng nhập Password", "Lỗi !!!", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    
	    //Birthday
	    if (birthday.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Vui lòng nhập Birthday", "Validation Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }else {
	        String birthdayPattern = "\\d{2}/\\d{2}/\\d{4}";
	        if (!birthday.matches(birthdayPattern)) {
	            JOptionPane.showMessageDialog(null, "Định dạng ngày sinh không chính xác. Vui lòng nhập đúng định dạng sau: dd/MM/yyyy ", "Lỗi !!!", JOptionPane.ERROR_MESSAGE);
	            return false;
	        }
	        
	    }
	    
	    // Phone Number
	    if (phoneNumber.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Vui lòng nhập Phone Number", "Lỗi !!!", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }else {
	        String phonePattern = "\\d{10,}";
	        if (!phoneNumber.matches(phonePattern)) {
	            JOptionPane.showMessageDialog(null, "Định dạng số điện thoại không chính xác. Vui lòng nhập số điện thoại có 10 chữ số trở lên", "Lỗi !!!", JOptionPane.ERROR_MESSAGE);
	            return false;
	        }
	    }
	   
	    return true;
	}
	
	



	public void sendObjectToServer() throws IOException
	{
	        if (!checkFields()) {
	        return; 
	    }  
	    
		Socket socket = new Socket("localhost", 7777);
		System.out.println("Connected!");
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
		SignUpForm user = new SignUpForm();
		user.setVisible(true);
	}
}
