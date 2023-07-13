package Interface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import model.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Window;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Result;

import database.JDBCUtil;


public class ServerInterface extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTextField textField_1;
	private int check;
	private static JLabel tf1;
	private static ta_kda_user user;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ServerInterface a = new ServerInterface();
		a.setVisible(true);
	}

	
	
	public JLabel getTf1() {
		return tf1;
	}



	public void setTf1(JLabel tf1) {
		this.tf1 = tf1;
	}



	/**
	 * Create the frame.
	 */
//	public ServerInterface() {
//		addControls();
//	}
//	
	public ServerInterface() {
	    DefaultTableModel dtm;
				JTable Local;
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 941, 502);
				this.setLocationRelativeTo(null);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				this.setResizable(false);
				
				JPanel pnRight = new JPanel();
				pnRight.setBounds(0, -29, 926, 512);
				contentPane.add(pnRight);
				pnRight.setLayout(new CardLayout(0, 0));
			
				JPanel pnLocalHost = new JPanel();
				pnRight.add(pnLocalHost);
				pnLocalHost.setLayout(null);
				
				JPanel pnPort = new JPanel();
				pnPort.setBounds(0, -14, 926, 497);
				pnRight.add(pnPort);
				
				JLabel lblNewLabel = new JLabel("Server");
				lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 60));
				lblNewLabel.setBounds(402, 28, 160, 70);
				pnLocalHost.add(lblNewLabel, BorderLayout.NORTH);
				
				JLabel lblNewLabel_1 = new JLabel("Status :");
				lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 36));
				lblNewLabel_1.setBounds(40, 99, 122, 47);
				pnLocalHost.add(lblNewLabel_1);
				
				JLabel lblNewLabel_2 = new JLabel("Port:");
				lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 36));
				lblNewLabel_2.setBounds(471, 99, 88, 47);
				pnLocalHost.add(lblNewLabel_2);
				
				JSplitPane sp=new JSplitPane();
				dtm=new DefaultTableModel();
				dtm.addColumn("STT");
				dtm.addColumn("EMAIL");
				dtm.addColumn("STATUS");
				
				 Local = new JTable(dtm);
				 JScrollPane scTable = new JScrollPane(Local);
				 scTable.setBounds(37, 190, 614, 250);		
				 pnLocalHost.setLayout(new BorderLayout());           
				 scTable.setVisible(true);
				 pnLocalHost.add(scTable);

				
				JButton btnNewButton = new JButton("Start");
				btnNewButton.addActionListener(this);
				btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnNewButton.setBounds(717, 206, 144, 39);
				pnLocalHost.add(btnNewButton);
				
				tf1 = new JLabel("OFF");
				tf1.setBounds(160, 102, 255, 39);
				tf1.setFont(new Font("Tahoma", Font.PLAIN, 34));
				tf1.setForeground(Color.red);
				pnLocalHost.add(tf1);
//				label1.setColumns(10);
				
				textField_1 = new JTextField();
				textField_1.setColumns(10);
				textField_1.setBounds(579, 107, 255, 39);
				pnLocalHost.add(textField_1);
				
				JButton btnNewButton_1 = new JButton("Stop");
				btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btnNewButton_1.setBounds(717, 316, 144, 39);
				pnLocalHost.add(btnNewButton_1);
				
				
				JLabel nenchinh3 = new JLabel("");
				nenchinh3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
				nenchinh3.setBounds(0, 0, 926, 515);
				pnLocalHost.add(nenchinh3);;
				nenchinh3.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\4.jpg"));
				
			
				JLabel nenchinh4 = new JLabel("New label");
				nenchinh4.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\4.jpg"));
				nenchinh4.setBounds(0, 0, 926, 497);
				pnPort.add(nenchinh4);
				tf1.setText("OFF");
	}
	
	public void getObjectFromClient() throws IOException, ClassNotFoundException, InterruptedException
	{
		boolean dk = true;
		int loginrs = 1;
		check = 0;
		while(true)
		{
			if(dk)
			{
				ServerSocket serverSocket = new ServerSocket(7777);
				Socket socket = serverSocket.accept();
//				System.out.println("Connection from "+socket +"!");
				InputStream inputStream = socket.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
				ta_kda_user abc = (ta_kda_user) objectInputStream.readObject();
				int id = 0;
				abc.setI_Id(id);
				try {
					Connection con = JDBCUtil.getConnection();
					Statement st = con.createStatement();
					String sql = "INSERT INTO TA_KDA_USER VALUES(NULL,'"+abc.getT_EmailAddress()+"','"+abc.getT_PassWord()+"','"+abc.getT_FullName()+"','"+abc.getT_BirthDay()+"','"+abc.getT_PhoneNumber()+"');";
					check = st.executeUpdate(sql);
					System.out.println(check + "row was change!");
					JDBCUtil.closeConnection(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				OutputStream outputStream = socket.getOutputStream();
				outputStream.write(check);
				if(objectInputStream.available() == 0)
				{
					serverSocket.close();
					socket.close();
				}
			}
			else {
			ServerSocket serverSocket2 = new ServerSocket(7777);
			Socket socket2 = serverSocket2.accept();
			InputStream inputStream2 = socket2.getInputStream();
			ObjectInputStream objectInputStream2 = new ObjectInputStream(inputStream2);
			ta_kda_user def = (ta_kda_user) objectInputStream2.readObject();
			try {
				Connection con = JDBCUtil.getConnection();
				Statement st = con.createStatement();
				String sql = "SELECT I_Id FROM ta_kda_user WHERE T_EmailAddress = '" + def.getT_EmailAddress()+"' AND T_PassWord = '" + def.getT_PassWord()+"';";
				ResultSet rs = st.executeQuery(sql);
				if(rs == null)
					loginrs = 2;
				else
				{
					loginrs = 3;
				}
				JDBCUtil.closeConnection(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			OutputStream outputStream2 = socket2.getOutputStream();
			outputStream2.write(loginrs);
			if(objectInputStream2.available() == 0)
			{
			serverSocket2.close();
			socket2.close();
			}
		}
			dk = !dk;
	}
}	

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equals("Start")) {
			tf1.setText("RUNNING");
			tf1.updateUI();
			try {
				System.out.println(getTf1().getText());
				getTf1().setText("RUNNING");
				System.out.println(getTf1().getText());
				getObjectFromClient();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
