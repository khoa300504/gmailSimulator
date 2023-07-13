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
import java.awt.Taskbar.State;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Result;

import database.JDBCUtil;

	
public class svinft extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTextField textField_1;
	private int check;
	private static JLabel tf1;
	private static ta_kda_user user;
	private static EmailInformation emailInfo;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		svinft a = new svinft();
		a.setVisible(true);
	}
	
	 private static class ClientHandler implements Runnable {
	        private Socket clientSocket;
	        private BufferedReader input;
	        private PrintWriter output;

	        public ClientHandler(Socket socket) throws IOException {
	            this.clientSocket = socket;
	            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	            output = new PrintWriter(clientSocket.getOutputStream(), true);
	        }

	        @Override
	        public void run() {
	            try {
	                String request = input.readLine();
	                System.out.println(request);
	                if (request != null) {
	                    if (request.equals("register")) {
	                        handleRegister();
	                        System.out.println("End register process");
	                    } else if (request.equals("login")) {
	                        handleLogin();
	                        System.out.println("End login process");
	                    }
	                    else if(request.equals("sendletter")) {
	                    	handleSendLetter();
	                    	System.out.println("End letter process!");
	                    }
	                    else if(request.equals("getdata"))
	                    {
	                    	handleGetData();
	                    	System.out.println("End data process");
	                    }
	                    else if(request.equals("getdata2"))
	                    {
	                    	handleGetData2();
	                    	System.out.println("End data process");
	                    }
	                    else if(request.equals("deletedata"))
	                    {
	                    	handleDelete();
	                    	System.out.println("End delete process");
	                    }
	                }
	                clientSocket.close();
	                System.out.println("Client disconnected: " + clientSocket);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }

			private void handleGetData2() throws IOException {
				int rowCount;
	        	int user = Integer.parseInt(input.readLine());
	        	System.out.println(user);
	        	try {
					Connection con = JDBCUtil.getConnection();
					Statement st = con.createStatement();
					String sql = "SELECT COUNT(I_ID) FROM ta_kda_trash WHERE I_IdReceive = "+user;
					ResultSet rs1 = st.executeQuery(sql);
					if(rs1.next())
					{
						rowCount = rs1.getInt(1);
						output.println(rowCount);
					}
					else 
						output.println(0);
					sql = "SELECT e.I_ID, u.T_EmailAddress, e.T_Subject, e.T_Body, e.T_FilePath, e.T_Attach FROM ta_kda_trash e JOIN ta_kda_user u ON e.I_IdSend = u.I_ID WHERE e.I_IdReceive = "+user;
					ResultSet rs = st.executeQuery(sql);
					while(rs.next())
					{
						output.println(rs.getInt("I_ID"));
						output.println(rs.getString("T_EmailAddress"));
						output.println(rs.getString("T_Subject"));
						output.println(rs.getString("T_Body"));
						output.println(rs.getString("T_FilePath"));
						output.println(rs.getString("T_Attach"));
					}
					JDBCUtil.closeConnection(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			private void handleDelete() throws IOException {
				int idUser = Integer.parseInt(input.readLine());
				System.out.println(idUser + " is step 1");
				int deleteRow = Integer.parseInt(input.readLine());
				System.out.println("End step 2");
				if(deleteRow != -1)
				{
					int letterId = Integer.parseInt(input.readLine()) + 1;
					System.out.println(letterId + " is letter id");
					try {
						Connection conn = JDBCUtil.getConnection();
						Statement st = conn.createStatement();
						System.out.println("start sql");
							String sql = "INSERT INTO ta_kda_trash SELECT I_ID, T_Subject, T_Body, I_IdSend, I_IdReceive, T_FilePath, T_Attach FROM ta_kda_email WHERE I_ID = "+letterId;
							st.executeUpdate(sql);
							sql = "DELETE FROM ta_kda_email WHERE I_ID = "+letterId +"";
							st.executeUpdate(sql);
						JDBCUtil.closeConnection(conn);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	        }

			private void handleRegister() throws IOException {
	            String email = input.readLine();
	            String password = input.readLine();
	            String fullname = input.readLine();
	            String birthday = input.readLine();
	            String phonenumber = input.readLine();
	            System.out.println("Received registration request for username: " + email);
	            try {
					Connection con = JDBCUtil.getConnection();
					Statement st = con.createStatement();
					String sql = "INSERT INTO TA_KDA_USER VALUES(NULL,'"+email+"','"+password+"','"+fullname+"','"+birthday+"','"+phonenumber+"');";
					int check = st.executeUpdate(sql);
					System.out.println(check + "row was change!");
					JDBCUtil.closeConnection(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            output.println("Registration successful");
	        }

	        private void handleLogin() throws IOException {
	            String username = input.readLine();
	            String password = input.readLine();
	            String result = "0";
	            String id = null;
	            System.out.println("Received login request for username: " + username);
	            try {
					Connection con = JDBCUtil.getConnection();
					Statement st = con.createStatement();
					String sql = "SELECT T_FullName, I_ID FROM ta_kda_user WHERE T_EmailAddress = '" + username +"' AND T_PassWord = '" + password+"';";
					ResultSet rs = st.executeQuery(sql);
					if(rs != null)
						while(rs.next()) {
						result = rs.getString("T_FullName");
						id = rs.getString("I_ID");
						}
					rs.close();
					JDBCUtil.closeConnection(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            output.println(result);
	            output.println(id);
	        }
	        
	        private void handleSendLetter()  throws IOException{
	        	int id1 = Integer.parseInt(input.readLine());
	        	String receiver = input.readLine();
	        	int id2 = 0;
	        	String title = input.readLine();
	        	String content = input.readLine();
	        	String path = input.readLine();
	        	String attach = input.readLine();
	        	try {
					Connection con = JDBCUtil.getConnection();
					Statement st = con.createStatement();
					String sql = "SELECT I_ID from ta_kda_user WHERE T_EmailAddress = '"+receiver+"';";
					ResultSet rs = st.executeQuery(sql);
					if(rs.next())
					{
						id2 = rs.getInt("I_ID");
					}
					sql = "INSERT INTO ta_kda_email values (NULL, ?, ?, ?, ?, ?, ?)";
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, title);
					statement.setString(2, content);
					statement.setInt(3, id1);
					statement.setInt(4, id2);
					statement.setString(5, path);
					statement.setString(6, attach);
					int check = statement.executeUpdate();
					System.out.println(check + " letter was send!");
					rs.close();
					JDBCUtil.closeConnection(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        }
	        
	        private void handleGetData() throws IOException
	        {
	        	int rowCount;
	        	int user = Integer.parseInt(input.readLine());
	        	System.out.println(user);
	        	try {
					Connection con = JDBCUtil.getConnection();
					Statement st = con.createStatement();
					String sql = "SELECT COUNT(I_ID) FROM ta_kda_email WHERE I_IdReceive = "+user;
					ResultSet rs1 = st.executeQuery(sql);
					if(rs1.next())
					{
						rowCount = rs1.getInt(1);
						output.println(rowCount);
					}
					else 
						output.println(0);
					sql = "SELECT e.I_ID, u.T_EmailAddress, e.T_Subject, e.T_Body, e.T_FilePath, e.T_Attach FROM ta_kda_email e JOIN ta_kda_user u ON e.I_IdSend = u.I_ID WHERE e.I_IdReceive = "+user;
					ResultSet rs = st.executeQuery(sql);
					while(rs.next())
					{
						output.println(rs.getInt("I_ID"));
						output.println(rs.getString("T_EmailAddress"));
						output.println(rs.getString("T_Subject"));
						output.println(rs.getString("T_Body"));
						output.println(rs.getString("T_FilePath"));
						output.println(rs.getString("T_Attach"));
					}
					JDBCUtil.closeConnection(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
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
	public svinft() {
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



	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equals("Start"))
		{
			try {
	            ServerSocket serverSocket = new ServerSocket(7777);
	            System.out.println("Server started and listening on port " + 7777);

	            while (true) {
	                Socket clientSocket = serverSocket.accept();
	                System.out.println("Client connected: " + clientSocket);

	                // Create a new thread to handle client requests
	                Thread thread = new Thread(new ClientHandler(clientSocket));
	                thread.start();
	            }
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
		}
	}
	
}
