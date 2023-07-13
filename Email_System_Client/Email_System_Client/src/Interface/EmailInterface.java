package Interface;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import model.*;
import javax.print.attribute.AttributeSet;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Color;
public class EmailInterface extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JLabel NenSoanThu;
	private JTextField textField;
	private JTable table;
	private JTextField TiemKiemCs;
	private JTable table_1;
	private JTextField NguoiNhanTf;
	private JTextField TieuDeTf;
	private JTextArea NoiDungTf;
	private JLabel userLabel;
	private String idUs;
	private JButton slFile;
	private String fileExtension;
	private String path;
	private String fileName;
	private String attach = "No";
	private JTable allLetter;
	private DefaultTableModel tableModel;
	private JLabel fileInfo;
	private int selected;
	private int idUser;
	private int deleteRow;
	private JTable table3;
	private JTable table2;
	private DefaultTableModel tableModel2;
	private ArrayList<EmailInformation> emailInfo;
	
	
	public static void main(String[] args) throws IOException{
			new EmailInterface("Nguyen Tuan Khoa", "2");
	}
	
	public EmailInterface(String HiUser, String id) throws IOException{
			Socket socket = new Socket("localhost", 7777);
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
	        String request = "getdata";
	        output.println(request);
	        output.println(id);
	        idUser = Integer.parseInt(id);
	        emailInfo = new ArrayList<>();
	        int rowCount = Integer.parseInt(input.readLine());

	        for(int i = 0; i < rowCount; i++)
	        {
	        		EmailInformation email = new EmailInformation();
	        		email.setId(Integer.parseInt(input.readLine()));
	        		email.setEmail(input.readLine());
	        		email.setTitle(input.readLine());
	        		email.setBody(input.readLine());
	        		email.setPath(input.readLine());
	        		email.setAttach(input.readLine());
	        		emailInfo.add(email);
	        }
	        
	        
	        
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 941, 502);
			this.setVisible(true);
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
		
			JPanel pnHopThu = new JPanel();
			pnRight.add(pnHopThu);
			pnHopThu.setLayout(null);
			
			JPanel pnCoSao = new JPanel();
			pnRight.setBounds(0, -14, 926, 497);
			pnRight.add(pnCoSao);
			
			JPanel pnDaXoa = new JPanel();
			pnRight.setBounds(0, -14, 926, 497);
			pnRight.add(pnDaXoa);
			pnDaXoa.setLayout(null);
	
			JPanel pnDaGui = new JPanel();
			pnRight.setBounds(0, -14, 926, 497);
			pnRight.add(pnDaGui);
			pnDaGui.setLayout(null);
			
			JPanel pnSoanThu = new JPanel();
			pnRight.setBounds(0, -14, 926, 497);
			pnRight.add(pnSoanThu);
			pnSoanThu.setLayout(null);
			
			
			

			//HOPTHU1
			JLabel M = new JLabel("");
			M.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\logo.png"));
			M.setBounds(0, 28, 116, 45);
			pnHopThu.add(M);
			
			JLabel SoanThuHt = new JLabel("Soạn Thư");
			SoanThuHt.setHorizontalAlignment(SwingConstants.CENTER);
			SoanThuHt.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Iconsmind-Outline-Pen.32.png"));
			SoanThuHt.setFont(new Font("Tahoma", Font.BOLD, 19));
			SoanThuHt.setBounds(0, 76, 165, 45);
			pnHopThu.add(SoanThuHt);
			
			SoanThuHt.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnSoanThu);
		            pnRight.updateUI();
		        }
		        }
		        );
			
			JLabel HopThuHt = new JLabel("Hộp Thư Đến");
			HopThuHt.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Iconsmind-Outline-Photos.24.png"));
			HopThuHt.setHorizontalAlignment(SwingConstants.CENTER);
			HopThuHt.setFont(new Font("Tahoma", Font.BOLD, 15));
			HopThuHt.setBounds(0, 132, 144, 34);
			pnHopThu.add(HopThuHt);
			
			HopThuHt.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnHopThu);
		            pnRight.updateUI();
		        }
		        }
		        );
			
			JLabel CoSaoHt = new JLabel("Có Gắn Sao");
			CoSaoHt.setHorizontalAlignment(SwingConstants.CENTER);
			CoSaoHt.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Pictogrammers-Material-Light-Star.32.png"));
			CoSaoHt.setFont(new Font("Tahoma", Font.BOLD, 15));
			CoSaoHt.setBounds(0, 177, 144, 34);
			pnHopThu.add(CoSaoHt);
			
			CoSaoHt.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnCoSao);
		            pnRight.updateUI();
		        }
		        }
		        );
			
			JLabel DaGuiHt = new JLabel("Đã Gửi");
			DaGuiHt.setHorizontalAlignment(SwingConstants.CENTER);
			DaGuiHt.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Icons8-Windows-8-Arrows-Left-Round.24.png"));
			DaGuiHt.setFont(new Font("Tahoma", Font.BOLD, 15));
			DaGuiHt.setBounds(0, 267, 144, 34);
			pnHopThu.add(DaGuiHt);
			
			DaGuiHt.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnDaGui);
		            pnRight.updateUI();
		        }
		        }
		        );
			
			JLabel DaXoaHt = new JLabel("Đã Xóa");
			DaXoaHt.setHorizontalAlignment(SwingConstants.CENTER);
			DaXoaHt.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Pictogrammers-Material-Light-Clock.24.png"));
			DaXoaHt.setFont(new Font("Tahoma", Font.BOLD, 15));
			DaXoaHt.setBounds(0, 222, 144, 34);
			pnHopThu.add(DaXoaHt);
			
			DaXoaHt.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnDaXoa);
		            pnRight.updateUI();
		        }
		        }
		        );
			JLabel Hi1 = new JLabel("Hi: ");
			Hi1.setFont(new Font("Arial", Font.BOLD, 24));
			Hi1.setBounds(569, 15, 80, 50);
			JLabel userLabel1 = new JLabel(HiUser);
			userLabel1.setBounds(639, 0, 250, 80);
			userLabel1.setFont(new Font("Arial", Font.BOLD, 24));
			userLabel1.setForeground(new Color(241, 95, 104));
			
			textField = new JTextField();
			textField.setAlignmentX(Component.LEFT_ALIGNMENT);
			textField.setBounds(269, 68, 599, 34);
			pnHopThu.add(textField);
			pnHopThu.add(Hi1);
			pnHopThu.add(userLabel1);
			textField.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Gakuseisean-Ivista-2-Start-Menu-Search.24.png"));
			lblNewLabel.setBounds(240, 68, 31, 34);
			pnHopThu.add(lblNewLabel);
			
			JLabel ChinhHt = new JLabel("Chính");
			ChinhHt.setFont(new Font("Tahoma", Font.BOLD, 25));
			ChinhHt.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Iconsmind-Outline-Photos.32.png"));
			ChinhHt.setBounds(269, 125, 150, 45);
			pnHopThu.add(ChinhHt);
			
			//Table
			
			
			table = new JTable();
			tableModel = new DefaultTableModel();
			table.setAlignmentX(Component.LEFT_ALIGNMENT);
			String[] column1 = {"ID","Người gửi","Tiêu đề","Đính kèm"};
			JScrollPane scrl = new JScrollPane(table);
			table.setModel(tableModel);
			tableModel.setColumnIdentifiers(column1);
			table.setRowHeight(30);
			scrl.setBounds(269, 175, 600, 220);
			pnHopThu.add(scrl);
			table.getColumnModel().getColumn(0).setCellRenderer(new BoldTableCellRenderer());
			table.getColumnModel().getColumn(1).setCellRenderer(new RedTableCellRenderer());
			for(int i = 0; i < emailInfo.size(); i++)
			{
				EmailInformation email = emailInfo.get(i);
				Object[] rowData = {email.getId()+"", email.getEmail(), email.getTitle(), email.getAttach()};
	            tableModel.addRow(rowData);
			}
			JButton deleteBtn = new JButton("Delete");
			deleteBtn.setBounds(270, 400, 150, 30);
			deleteBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Socket socket = new Socket("localhost", 7777);
						BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
						String request = "deletedata";
						output.println(request);
						output.println(idUser);
						deleteRow = table.getSelectedRow();
						output.println(deleteRow);
						System.out.println(deleteRow);
						if(deleteRow != -1)
						{
						output.println(deleteRow);
						System.out.println("start send value");
						output.println(tableModel.getValueAt(deleteRow, 0) + "");
						System.out.println(tableModel.getValueAt(deleteRow, 0) + " is null??");
						}
						System.out.println("end send value");
						socket.close();
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
			JButton openBtn = new JButton("Open");
			openBtn.addActionListener(this);
			JButton refreshBtn = new JButton("Reload");
			refreshBtn.addActionListener(this);
			refreshBtn.setBounds(630, 400, 150, 30);
			openBtn.setBounds(450, 400, 150, 30);
			pnHopThu.add(openBtn);
			pnHopThu.add(deleteBtn);
			pnHopThu.add(refreshBtn);
			
			NenSoanThu = new JLabel("");
			NenSoanThu.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\5.jpg"));
			NenSoanThu.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenSoanThu.setBounds(0, 76, 165, 45);
			pnHopThu.add(NenSoanThu);
			
			JLabel NenHopThu = new JLabel("");
			NenHopThu.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\2.jpg"));
			NenHopThu.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenHopThu.setBounds(0, 132, 144, 34);
			pnHopThu.add(NenHopThu);

			JLabel NenCoSao = new JLabel("");
			NenCoSao.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\3.jpg"));
			NenCoSao.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenCoSao.setBounds(0, 177, 144, 34);
			pnHopThu.add(NenCoSao);
			
			JLabel NenDaGui = new JLabel("");
			NenDaGui.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\3.jpg"));
			NenDaGui.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenDaGui.setBounds(0, 267, 144, 34);
			pnHopThu.add(NenDaGui);
			
			JLabel NenDaXoa = new JLabel("");
			NenDaXoa.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\3.jpg"));
			NenDaXoa.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenDaXoa.setBounds(0, 222, 144, 34);
			pnHopThu.add(NenDaXoa);
			

			
			
			
			
			
			// CÓ SAO
			
			
			
			JLabel MCs = new JLabel("");
			MCs.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\logo.png"));
			MCs.setBounds(0, 28, 116, 45);
			pnCoSao.add(MCs);
			
			JLabel SoanThuCs = new JLabel("Soạn Thư");
			SoanThuCs.setHorizontalAlignment(SwingConstants.CENTER);
			SoanThuCs.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Iconsmind-Outline-Pen.32.png"));
			SoanThuCs.setFont(new Font("Tahoma", Font.BOLD, 19));
			SoanThuCs.setBounds(0, 76, 165, 45);
			pnCoSao.add(SoanThuCs);
			
			SoanThuCs.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnSoanThu);
		            pnRight.updateUI();
		        }
		        }
		        );

			
			JLabel HopThuCs = new JLabel("Hộp Thư Đến");
			HopThuCs.setHorizontalAlignment(SwingConstants.CENTER);
			HopThuCs.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Iconsmind-Outline-Photos.24.png"));
			HopThuCs.setFont(new Font("Tahoma", Font.BOLD, 15));
			HopThuCs.setBounds(0, 132, 144, 34);
			pnCoSao.add(HopThuCs);
			
			HopThuCs.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnHopThu);
		            pnRight.updateUI();
		        }
		        }
		        );
			
			JLabel CoSaoCs = new JLabel("Có Gắn Sao");
			CoSaoCs.setHorizontalAlignment(SwingConstants.CENTER);
			CoSaoCs.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Pictogrammers-Material-Light-Star.32.png"));
			CoSaoCs.setFont(new Font("Tahoma", Font.BOLD, 15));
			CoSaoCs.setBounds(0, 177, 144, 34);
			pnCoSao.add(CoSaoCs);
			
			CoSaoCs.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnCoSao);
		            pnRight.updateUI();
		        }
		        }
		        );
			
			JLabel DaGuiCs = new JLabel("Đã Gửi");
			DaGuiCs.setHorizontalAlignment(SwingConstants.CENTER);
			DaGuiCs.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Icons8-Windows-8-Arrows-Left-Round.24.png"));
			DaGuiCs.setFont(new Font("Tahoma", Font.BOLD, 15));
			DaGuiCs.setBounds(0, 267, 144, 34);
			pnCoSao.add(DaGuiCs);
			
			DaGuiCs.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnDaGui);
		            pnRight.updateUI();
		        }
		        }
		        );
						
			JLabel DaXoaCs = new JLabel("Đã Xóa");
			DaXoaCs.setHorizontalAlignment(SwingConstants.CENTER);
			DaXoaCs.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Pictogrammers-Material-Light-Clock.24.png"));
			DaXoaCs.setFont(new Font("Tahoma", Font.BOLD, 15));
			DaXoaCs.setBounds(0, 222, 144, 34);
			pnCoSao.add(DaXoaCs);
			
			DaXoaCs.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnDaXoa);
		            pnRight.updateUI();
		        }
		        }
		        );
			

			
			TiemKiemCs = new JTextField();
			TiemKiemCs.setColumns(10);
			TiemKiemCs.setAlignmentX(0.0f);
			TiemKiemCs.setBounds(269, 68, 599, 34);
			pnCoSao.add(TiemKiemCs);
			
			JLabel KinhCs = new JLabel("");
			KinhCs.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Gakuseisean-Ivista-2-Start-Menu-Search.24.png"));
			KinhCs.setBounds(240, 68, 31, 34);
			pnCoSao.add(KinhCs);
			
			JLabel ChinhCs = new JLabel("Chính");
			ChinhCs.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Iconsmind-Outline-Photos.32.png"));
			ChinhCs.setFont(new Font("Tahoma", Font.BOLD, 25));
			ChinhCs.setBounds(269, 127, 150, 45);
			pnCoSao.add(ChinhCs);
			
			table_1 = new JTable();
			table_1.setAlignmentX(0.0f);
			table_1.setBounds(269, 170, 600, 180);
			pnCoSao.add(table_1);
			
			JLabel NenSoanThuCs = new JLabel("");
			NenSoanThuCs.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\5.jpg"));
			NenSoanThuCs.setHorizontalAlignment(SwingConstants.CENTER);
			NenSoanThuCs.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenSoanThuCs.setBounds(0, 76, 165, 45);
			pnCoSao.add(NenSoanThuCs);
			
			JLabel NenHopThuCs = new JLabel("");
			NenHopThuCs.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\3.jpg"));
			NenHopThuCs.setHorizontalAlignment(SwingConstants.CENTER);
			NenHopThuCs.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenHopThuCs.setBounds(0, 132, 144, 34);
			pnCoSao.add(NenHopThuCs);
			
			JLabel NenCoSaoCs = new JLabel("");
			NenCoSaoCs.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\2.jpg"));
			NenCoSaoCs.setHorizontalAlignment(SwingConstants.CENTER);
			NenCoSaoCs.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenCoSaoCs.setBounds(0, 177, 144, 34);
			pnCoSao.add(NenCoSaoCs);
			
			JLabel NenDaGuiCs = new JLabel("");
			NenDaGuiCs.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\3.jpg"));
			NenDaGuiCs.setHorizontalAlignment(SwingConstants.CENTER);
			NenDaGuiCs.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenDaGuiCs.setBounds(0, 267, 144, 34);
			pnCoSao.add(NenDaGuiCs);
			
			JLabel NenDaXoaCs = new JLabel("");
			NenDaXoaCs.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\3.jpg"));
			NenDaXoaCs.setHorizontalAlignment(SwingConstants.CENTER);
			NenDaXoaCs.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenDaXoaCs.setBounds(0, 222, 144, 34);
			pnCoSao.add(NenDaXoaCs);
			JLabel Hi2 = new JLabel("Hi: ");
			Hi2.setFont(new Font("Arial", Font.BOLD, 24));
			Hi2.setBounds(569, 15, 80, 50);
			JLabel userLabel2 = new JLabel(HiUser);
			userLabel2.setBounds(639, 0, 250, 80);
			userLabel2.setFont(new Font("Arial", Font.BOLD, 24));
			userLabel2.setForeground(new Color(241, 95, 104));
			pnCoSao.add(Hi2);
			pnCoSao.add(userLabel2);

			
			//Da xoa
			
			
			
			JLabel MDa = new JLabel("");
			MDa.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\logo.png"));
			MDa.setBounds(0, 28, 116, 45);
			pnDaXoa.add(MDa);
			
			JLabel SoanThuDa = new JLabel("Soạn Thư");
			SoanThuDa.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Iconsmind-Outline-Pen.32.png"));
			SoanThuDa.setHorizontalAlignment(SwingConstants.CENTER);
			SoanThuDa.setFont(new Font("Tahoma", Font.BOLD, 19));
			SoanThuDa.setBounds(0, 76, 165, 45);
			pnDaXoa.add(SoanThuDa);
			
			SoanThuDa.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnSoanThu);
		            pnRight.updateUI();
		        }
		        }
		        );
			
			JLabel HopThuDa = new JLabel("Hộp Thư Đến");
			HopThuDa.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Iconsmind-Outline-Photos.24.png"));
			HopThuDa.setHorizontalAlignment(SwingConstants.CENTER);
			HopThuDa.setFont(new Font("Tahoma", Font.BOLD, 15));
			HopThuDa.setBounds(0, 132, 144, 34);
			pnDaXoa.add(HopThuDa);
			
			HopThuDa.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnHopThu);
		            pnRight.updateUI();

		        }
		        }
		        );
			
			JLabel CoSaoDa = new JLabel("Có Gắn Sao");
			CoSaoDa.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Pictogrammers-Material-Light-Star.32.png"));
			CoSaoDa.setHorizontalAlignment(SwingConstants.CENTER);
			CoSaoDa.setFont(new Font("Tahoma", Font.BOLD, 15));
			CoSaoDa.setBounds(0, 177, 144, 34);
			pnDaXoa.add(CoSaoDa);
			
			CoSaoDa.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnCoSao);
		            pnRight.updateUI();
		        }
		        }
		        );
			
			JLabel DaGuiDa = new JLabel("Đã Gửi");
			DaGuiDa.setHorizontalAlignment(SwingConstants.CENTER);
			DaGuiDa.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Icons8-Windows-8-Arrows-Left-Round.24.png"));
			DaGuiDa.setFont(new Font("Tahoma", Font.BOLD, 15));
			DaGuiDa.setBounds(0, 267, 144, 34);
			pnDaXoa.add(DaGuiDa);
			
			DaGuiDa.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnDaGui);
		            pnRight.updateUI();
		        }
		        }
		        );
			
			JLabel DaXoaDa = new JLabel("Đã Xóa");
			DaXoaDa.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Pictogrammers-Material-Light-Clock.24.png"));
			DaXoaDa.setHorizontalAlignment(SwingConstants.CENTER);
			DaXoaDa.setFont(new Font("Tahoma", Font.BOLD, 15));
			DaXoaDa.setBounds(0, 222, 144, 34);
			pnDaXoa.add(DaXoaDa);
			
			DaXoaDa.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnDaXoa);
		            pnRight.updateUI();
		        }
		        }
		        );
			

			
			textField = new JTextField();
			textField.setAlignmentX(Component.LEFT_ALIGNMENT);
			textField.setBounds(269, 68, 599, 34);
			pnDaXoa.add(textField);
			textField.setColumns(10);
			
			JLabel KinhDa = new JLabel("");
			KinhDa.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Gakuseisean-Ivista-2-Start-Menu-Search.24.png"));
			KinhDa.setBounds(240, 68, 31, 34);
			pnDaXoa.add(KinhDa);
			
			JLabel ChinhDa = new JLabel("Chính");
			ChinhDa.setFont(new Font("Tahoma", Font.BOLD, 25));
			ChinhDa.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Iconsmind-Outline-Photos.32.png"));
			ChinhDa.setBounds(269, 127, 150, 45);
			pnDaXoa.add(ChinhDa);
			
			table2 = new JTable();
			table2.setAlignmentX(Component.LEFT_ALIGNMENT);
			tableModel2 = new DefaultTableModel();
			table2.setModel(tableModel2);
			String[] columnList2 = new String[]{"Id", "Người Gửi", "Tiêu đề", "Đính kèm"};
			tableModel2.setColumnIdentifiers(columnList2);
			JScrollPane scrl2 = new JScrollPane(table2);
			scrl2.setBounds(269, 170, 600, 180);
			pnDaXoa.add(scrl2);
			
			JLabel NenSoanThuDa = new JLabel("");
			NenSoanThuDa.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\5.jpg"));
			NenSoanThuDa.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenSoanThuDa.setBounds(0, 76, 165, 45);
			pnDaXoa.add(NenSoanThuDa);
			
			JLabel NenHopThuDa = new JLabel("");
			NenHopThuDa.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\3.jpg"));
			NenHopThuDa.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenHopThuDa.setBounds(0, 132, 144, 34);
			pnDaXoa.add(NenHopThuDa);

			JLabel NenCoSaoDa = new JLabel("");
			NenCoSaoDa.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\3.jpg"));
			NenCoSaoDa.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenCoSaoDa.setBounds(0, 177, 144, 34);
			pnDaXoa.add(NenCoSaoDa);
			
			JLabel NenDaGuiDa = new JLabel("");
			NenDaGuiDa.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\3.jpg"));
			NenDaGuiDa.setHorizontalAlignment(SwingConstants.CENTER);
			NenDaGuiDa.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenDaGuiDa.setBounds(0, 267, 144, 34);
			pnDaXoa.add(NenDaGuiDa);
			
			JLabel NenDaXoADa = new JLabel("");
			NenDaXoADa.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\2.jpg"));
			NenDaXoADa.setHorizontalAlignment(SwingConstants.CENTER);
			NenDaXoADa.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenDaXoADa.setBounds(0, 222, 144, 34);
			pnDaXoa.add(NenDaXoADa);
			JLabel Hi3 = new JLabel("Hi: ");
			Hi3.setFont(new Font("Arial", Font.BOLD, 24));
			Hi3.setBounds(569, 15, 80, 50);
			JLabel userLabel3 = new JLabel(HiUser);
			userLabel3.setBounds(639, 0, 250, 80);
			userLabel3.setFont(new Font("Arial", Font.BOLD, 24));
			userLabel3.setForeground(new Color(241, 95, 104));
			pnDaXoa.add(Hi3);
			pnDaXoa.add(userLabel3);
			
		    //DA GUI
			
			
			
			JLabel MDg = new JLabel("");
			MDg.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\logo.png"));
			MDg.setBounds(0, 28, 116, 45);
			pnDaGui.add(MDg);
			
			JLabel SoanThuDg = new JLabel("Soạn Thư");
			SoanThuDg.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Iconsmind-Outline-Pen.32.png"));
			SoanThuDg.setHorizontalAlignment(SwingConstants.CENTER);
			SoanThuDg.setFont(new Font("Tahoma", Font.BOLD, 19));
			SoanThuDg.setBounds(0, 76, 165, 45);
			pnDaGui.add(SoanThuDg);
			
			SoanThuDg.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnSoanThu);
		            pnRight.updateUI();
		        }
		        }
		        );
			
			
			JLabel HopThuDg = new JLabel("Hộp Thư Đến");
			HopThuDg.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Iconsmind-Outline-Photos.24.png"));
			HopThuDg.setHorizontalAlignment(SwingConstants.CENTER);
			HopThuDg.setFont(new Font("Tahoma", Font.BOLD, 15));
			HopThuDg.setBounds(0, 132, 144, 34);
			pnDaGui.add(HopThuDg);
			
			HopThuDg.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnHopThu);
		            pnRight.updateUI();
		        }
		        }
		        );
			
			JLabel CoSaoDg = new JLabel("Có Gắn Sao");
			CoSaoDg.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Pictogrammers-Material-Light-Star.32.png"));
			CoSaoDg.setHorizontalAlignment(SwingConstants.CENTER);
			CoSaoDg.setFont(new Font("Tahoma", Font.BOLD, 15));
			CoSaoDg.setBounds(0, 177, 144, 34);
			pnDaGui.add(CoSaoDg);
			
			CoSaoDg.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnCoSao);
		            pnRight.updateUI();
		        }
		        }
		        );
			
			JLabel DaGuiDg = new JLabel("Đã Gửi");
			DaGuiDg.setHorizontalAlignment(SwingConstants.CENTER);
			DaGuiDg.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Icons8-Windows-8-Arrows-Left-Round.24.png"));
			DaGuiDg.setFont(new Font("Tahoma", Font.BOLD, 15));
			DaGuiDg.setBounds(0, 267, 144, 34);
			pnDaGui.add(DaGuiDg);
			
			DaGuiDg.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnDaGui);
		            pnRight.updateUI();
		        }
		        }
		        );
			
			JLabel DaXoaDg = new JLabel("Đã Xóa");
			DaXoaDg.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Pictogrammers-Material-Light-Clock.24.png"));
			DaXoaDg.setHorizontalAlignment(SwingConstants.CENTER);
			DaXoaDg.setFont(new Font("Tahoma", Font.BOLD, 15));
			DaXoaDg.setBounds(0, 222, 144, 34);
			pnDaGui.add(DaXoaDg);
			
			DaXoaDg.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnDaXoa);
		            pnRight.updateUI();
		        }
		        }
		        );

			
			textField = new JTextField();
			textField.setAlignmentX(Component.LEFT_ALIGNMENT);
			textField.setBounds(269, 68, 599, 34);
			pnDaGui.add(textField);
			textField.setColumns(10);
			
			JLabel KinhDg = new JLabel("");
			KinhDg.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Gakuseisean-Ivista-2-Start-Menu-Search.24.png"));
			KinhDg.setBounds(240, 68, 31, 34);
			pnDaGui.add(KinhDg);
			
			JLabel ChinhDg = new JLabel("Chính");
			ChinhDg.setFont(new Font("Tahoma", Font.BOLD, 25));
			ChinhDg.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Iconsmind-Outline-Photos.32.png"));
			ChinhDg.setBounds(269, 127, 150, 45);
			pnDaGui.add(ChinhDg);
			
			table3 = new JTable();
			table3.setAlignmentX(Component.LEFT_ALIGNMENT);
			table3.setBounds(269, 170, 600, 180);
			pnDaGui.add(table3);
			
			JLabel NenSoanThuDg = new JLabel("");
			NenSoanThuDg.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\5.jpg"));
			NenSoanThuDg.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenSoanThuDg.setBounds(0, 76, 165, 45);
			pnDaGui.add(NenSoanThuDg);
			
			JLabel NenHopThuDg = new JLabel("");
			NenHopThuDg.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\3.jpg"));
			NenHopThuDg.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenHopThuDg.setBounds(0, 132, 144, 34);
			pnDaGui.add(NenHopThuDg);

			JLabel NenCoSaoDg = new JLabel("");
			NenCoSaoDg.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\3.jpg"));
			NenCoSaoDg.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenCoSaoDg.setBounds(0, 177, 144, 34);
			pnDaGui.add(NenCoSaoDg);
			
			JLabel NenDaGuiDg = new JLabel("");
			NenDaGuiDg.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\2.jpg"));
			NenDaGuiDg.setHorizontalAlignment(SwingConstants.CENTER);
			NenDaGuiDg.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenDaGuiDg.setBounds(0, 267, 144, 34);
			pnDaGui.add(NenDaGuiDg);
			
			JLabel NenDaXoaDg = new JLabel("");
			NenDaXoaDg.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\3.jpg"));
			NenDaXoaDg.setHorizontalAlignment(SwingConstants.CENTER);
			NenDaXoaDg.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenDaXoaDg.setBounds(0, 222, 144, 34);
			pnDaGui.add(NenDaXoaDg);
			JLabel Hi4 = new JLabel("Hi: ");
			Hi4.setFont(new Font("Arial", Font.BOLD, 24));
			Hi4.setBounds(569, 15, 80, 50);
			JLabel userLabel4 = new JLabel(HiUser);
			userLabel4.setBounds(639, 0, 250, 80);
			userLabel4.setFont(new Font("Arial", Font.BOLD, 24));
			userLabel4.setForeground(new Color(241, 95, 104));
			pnDaGui.add(Hi4);
			pnDaGui.add(userLabel4);

			
			
			
			
			//SOAN THƯ
			
			
			
			

			JLabel MST = new JLabel("");
			MST.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\logo.png"));
			MST.setBounds(0, 28, 116, 45);
			pnSoanThu.add(MST);
			
			JLabel SoanThuSt = new JLabel("Soạn Thư");
			SoanThuSt.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Iconsmind-Outline-Pen.32.png"));
			SoanThuSt.setHorizontalAlignment(SwingConstants.CENTER);
			SoanThuSt.setFont(new Font("Tahoma", Font.BOLD, 19));
			SoanThuSt.setBounds(0, 76, 165, 45);
			pnSoanThu.add(SoanThuSt);
			
			SoanThuSt.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnSoanThu);
		            pnRight.updateUI();
		        }
		        }
		        );
			
			JLabel HopThuSt = new JLabel("Hộp Thư Đến");
			HopThuSt.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Iconsmind-Outline-Photos.24.png"));
			HopThuSt.setHorizontalAlignment(SwingConstants.CENTER);
			HopThuSt.setFont(new Font("Tahoma", Font.BOLD, 15));
			HopThuSt.setBounds(0, 132, 144, 34);
			pnSoanThu.add(HopThuSt);
			
			HopThuSt.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnHopThu);
		            pnRight.updateUI();
		        }
		        }
		        );
			
			JLabel CoSaoSt = new JLabel("Có Gắn Sao");
			CoSaoSt.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Pictogrammers-Material-Light-Star.32.png"));
			CoSaoSt.setHorizontalAlignment(SwingConstants.CENTER);
			CoSaoSt.setFont(new Font("Tahoma", Font.BOLD, 15));
			CoSaoSt.setBounds(0, 177, 144, 34);
			pnSoanThu.add(CoSaoSt);
			
			CoSaoSt.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnCoSao);
		            pnRight.updateUI();
		        }
		        }
		        );
			
			JLabel DaGuiSt = new JLabel("Đã Gửi");
			DaGuiSt.setHorizontalAlignment(SwingConstants.CENTER);
			DaGuiSt.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Icons8-Windows-8-Arrows-Left-Round.24.png"));
			DaGuiSt.setFont(new Font("Tahoma", Font.BOLD, 15));
			DaGuiSt.setBounds(0, 267, 144, 34);
			pnSoanThu.add(DaGuiSt);
			
			DaGuiSt.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnDaGui);
		            pnRight.updateUI();
		        }
		        }
		        );
			
			JLabel DaXoaSt = new JLabel("  Đã Xóa");
			DaXoaSt.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\Pictogrammers-Material-Light-Clock.24.png"));
			DaXoaSt.setHorizontalAlignment(SwingConstants.CENTER);
			DaXoaSt.setFont(new Font("Tahoma", Font.BOLD, 15));
			DaXoaSt.setBounds(0, 222, 144, 34);
			pnSoanThu.add(DaXoaSt);
			
			DaXoaSt.addMouseListener((MouseListener) new MouseAdapter() {
				@Override
		        public void mouseClicked(MouseEvent e) {
					pnRight.removeAll();
		            pnRight.add(pnDaXoa);
		            pnRight.updateUI();
		        }
		        }
		        );
			

			NguoiNhanTf = new JTextField();
			NguoiNhanTf.setColumns(10);
			NguoiNhanTf.setAlignmentX(0.0f);
			NguoiNhanTf.setBounds(338, 52, 300, 34);
			NguoiNhanTf.setFont(new Font("Arial", Font.BOLD, 24));
			NguoiNhanTf.setForeground(Color.red);
			pnSoanThu.add(NguoiNhanTf);
			
			TieuDeTf = new JTextField();
			TieuDeTf.setColumns(10);
			TieuDeTf.setAlignmentX(0.0f);
			TieuDeTf.setBounds(338, 111, 533, 34);
			TieuDeTf.setFont(new Font("Arial", Font.BOLD, 20));
			TieuDeTf.setForeground(Color.BLUE);
			pnSoanThu.add(TieuDeTf);
			
			NoiDungTf = new JTextArea();
//			NoiDungTf.setColumns(10);
//			NoiDungTf.setAlignmentX(0.0f);
			NoiDungTf.setBounds(338, 173, 533, 100);
			NoiDungTf.setFont(new Font("Arial", Font.BOLD, 16));
			NoiDungTf.addKeyListener(new KeyAdapter() {
			    public void keyTyped(KeyEvent e) { 
			        if (NoiDungTf.getText().length() >= 255 )
			            e.consume(); 
			    }  
			});
			pnSoanThu.add(NoiDungTf);
			
			JButton btnNewButton = new JButton("Gửi Thư");
			btnNewButton.addActionListener(this);
			btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
			btnNewButton.setBounds(541, 422, 119, 34);
			btnNewButton.setForeground(Color.white);
			btnNewButton.setBackground(Color.black);
			pnSoanThu.add(btnNewButton);
			
			fileInfo = new JLabel("None");
			fileInfo.setBounds(465, 365, 260, 35);
			fileInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
			fileInfo.setForeground(Color.black);
			fileInfo.setBackground(Color.white);
			fileInfo.setOpaque(true);
			slFile = new JButton("Select File");
			slFile.addActionListener(this);
			slFile.setBounds(541, 322, 119, 34);
			pnSoanThu.add(slFile);
			pnSoanThu.add(fileInfo);
			btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
			
			
			JLabel NguoiNhanLb = new JLabel("Người Nhận");
			NguoiNhanLb.setFont(new Font("Tahoma", Font.BOLD, 20));
			NguoiNhanLb.setBounds(209, 53, 119, 35);
			pnSoanThu.add(NguoiNhanLb);
			
			JLabel TieuDeLb = new JLabel("Tiêu Đề");
			TieuDeLb.setFont(new Font("Tahoma", Font.BOLD, 20));
			TieuDeLb.setBounds(209, 111, 87, 30);
			pnSoanThu.add(TieuDeLb);
			
			JLabel NoiDungLb = new JLabel("Nội Dung");
			NoiDungLb.setFont(new Font("Tahoma", Font.BOLD, 20));
			NoiDungLb.setBounds(209, 220, 100, 25);
			pnSoanThu.add(NoiDungLb);
			

			
			JLabel NenSoanThuST = new JLabel("");
			NenSoanThuST.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\5.jpg"));
			NenSoanThuST.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenSoanThuST.setBounds(0, 76, 165, 45);
			pnSoanThu.add(NenSoanThuST);
			
			JLabel NenHopThuSt = new JLabel("");
			NenHopThuSt.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\3.jpg"));
			NenHopThuSt.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenHopThuSt.setBounds(0, 132, 144, 34);
			pnSoanThu.add(NenHopThuSt);

			JLabel NenCoSaoSt = new JLabel("");
			NenCoSaoSt.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\3.jpg"));
			NenCoSaoSt.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenCoSaoSt.setBounds(0, 177, 144, 34);
			pnSoanThu.add(NenCoSaoSt);
			
			JLabel NenDaGuiSt = new JLabel("");
			NenDaGuiSt.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\3.jpg"));
			NenDaGuiSt.setHorizontalAlignment(SwingConstants.CENTER);
			NenDaGuiSt.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenDaGuiSt.setBounds(0, 267, 144, 34);
			pnSoanThu.add(NenDaGuiSt);
			
			JLabel NenDaXoaSt = new JLabel("");
			NenDaXoaSt.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\3.jpg"));
			NenDaXoaSt.setHorizontalAlignment(SwingConstants.CENTER);
			NenDaXoaSt.setFont(new Font("Tahoma", Font.BOLD, 22));
			NenDaXoaSt.setBounds(0, 222, 144, 34);
			pnSoanThu.add(NenDaXoaSt);
			

			
			//TRANG TRI
			
			JLabel T1 = new JLabel("");
			T1.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\tt1.png"));
			T1.setBounds(21, 357, 203, 109);
			pnHopThu.add(T1);
			
			JLabel T2 = new JLabel("");
			T2.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\tt2.png"));
			T2.setBounds(10, 346, 183, 140);
			pnCoSao.add(T2);
			
			JLabel T3 = new JLabel("");
			T3.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\tt3.png"));
			T3.setBounds(27, 334, 165, 163);
			pnDaXoa.add(T3);
		
			JLabel T4 = new JLabel("");
			T4.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\tt4.png"));
			T4.setBounds(10, 357, 132, 117);
			pnDaGui.add(T4);
			
			JLabel T5 = new JLabel("");
			T5.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\tt5.png"));
			T5.setBounds(28, 357, 116, 110);
			pnSoanThu.add(T5);
			
			
			
			//nền
			
	
			JLabel nenchinh = new JLabel("New label");
			nenchinh.setBounds(0, 11, 926, 486);
			pnHopThu.add(nenchinh);
			nenchinh.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\4.jpg"));
			pnCoSao.setLayout(null);
			
			JLabel nenchinh1 = new JLabel("New label");
			nenchinh1.setBounds(0, 0, 926, 497);
			pnCoSao.add(nenchinh1);
			nenchinh1.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\4.jpg"));
			
			JLabel nenchinh2 = new JLabel("New label");
			nenchinh2.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\4.jpg"));
			nenchinh2.setBounds(0, 0, 926, 497);
			pnDaXoa.add(nenchinh2);
			
			JLabel nenchinh3 = new JLabel("New label");
			nenchinh3.setBounds(0, 0, 926, 497);
			pnDaGui.add(nenchinh3);;
			nenchinh3.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\4.jpg"));

			JLabel nenchinh4 = new JLabel("New label");
			nenchinh4.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\4.jpg"));
			nenchinh4.setBounds(0, 0, 926, 497);
			pnSoanThu.add(nenchinh4);
			idUs = id;
	        socket.close();
	        Socket socket2 = new Socket("localhost", 7777);
			BufferedReader input2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
	        PrintWriter output2 = new PrintWriter(socket2.getOutputStream(), true);
	        String request2 = "getdata2";
	        output2.println(request2);
	        String id2 = id;
	        output2.println(id2);
	        idUser = Integer.parseInt(id);
	        int socot = Integer.parseInt(input2.readLine());
			ArrayList<EmailInformation> emailInfo2 = new ArrayList<>();
			if(socot != 0)
			{
				for(int i = 0; i < socot; i++)
		        {
		        		EmailInformation email = new EmailInformation();
		        		email.setId(Integer.parseInt(input2.readLine()));
		        		email.setEmail(input2.readLine());
		        		email.setTitle(input2.readLine());
		        		email.setBody(input2.readLine());
		        		email.setPath(input2.readLine());
		        		email.setAttach(input2.readLine());
		        		emailInfo2.add(email);
		        }
			}
			for(int i = 0; i < emailInfo2.size(); i++)
			{
				EmailInformation email = emailInfo2.get(i);
				Object[] rowData = {email.getId()+"", email.getEmail(), email.getTitle(), email.getAttach()};
	            tableModel2.addRow(rowData);
			}
			socket2.close();
			}

	public void sendLetter() throws IOException{
		Socket socket = new Socket("localhost", 7777);
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        String request = "sendletter";
        output.println(request);
        output.println(idUs);
        output.println(NguoiNhanTf.getText());
        output.println(TieuDeTf.getText());
        output.println(NoiDungTf.getText());
        output.println(path);
        output.println(attach);
        socket.close();
	}
	
	static class BoldTableCellRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (column == 0) {
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));
            }

            return cellComponent;
        }
    }
	
	static class RedTableCellRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (column == 1) {
                cellComponent.setForeground(Color.RED);
            }

            return cellComponent;
        }
    }
	
	public void getPath(String path)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\javaFile\\Client"));
		int response = fileChooser.showOpenDialog(null);
		if(response == JFileChooser.APPROVE_OPTION)
		{
			File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			path = file+"";
			System.out.println(path);
		}
	}
	
	private static String getFileExtension(Path path) {
        String fileName = path.getFileName().toString();
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1) {
            return "";
        }
        return fileName.substring(dotIndex + 1);
    }
	
	private static String getFileExtensionUsingStandardMethods(String filePath) {
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1) {
            return "";
        }
        return fileName.substring(dotIndex + 1);
    }
	
	private static String getFileNameUsingStandardMethods(String filePath) {
        return filePath.substring(filePath.lastIndexOf("/") + 1);
    }
	
	public void deleteLetter() throws IOException
	{
		Socket socket = new Socket("localhost", 7777);
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        String request = "deletedata";
        output.println(request);
        output.println(idUser);
        int deleteRow = table.getSelectedRow();
        System.out.println(deleteRow);
        if(deleteRow == 1)
        {
        output.println(deleteRow);
        System.out.println("start send value");
        output.println(tableModel.getValueAt(deleteRow-1, 0) + "");
        System.out.println(tableModel.getValueAt(deleteRow-1, 0) + " is null??");
        }
        System.out.println("end send value");
        socket.close();
        lastedLetter();
	}
	
	public void openLetter()
	{
		int id = table.getSelectedRow();
		String sender,title,body,path;
		for(int i = 0; i < emailInfo.size(); i++)
		{
			EmailInformation email = emailInfo.get(i);
			System.out.println(Integer.parseInt((String) table.getValueAt(id, 0)));
			if(email.getId() == Integer.parseInt((String) table.getValueAt(id, 0)))
			{
				Detailmail a = new Detailmail(email.getEmail(), email.getTitle(), email.getBody(), email.getPath());
				a.setVisible(true);
				break;
			}
		}
	}
	
	public void lastedLetter() throws IOException
	{
		tableModel.getDataVector().removeAllElements();
		tableModel.fireTableDataChanged();
		Socket socket = new Socket("localhost", 7777);
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        String request = "getdata";
        output.println(request);
        output.println(idUser);
        
        ArrayList<EmailInformation> emailInfo = new ArrayList<>();
        int rowCount = Integer.parseInt(input.readLine());

        for(int i = 0; i < rowCount; i++)
        {
        		EmailInformation email = new EmailInformation();
        		email.setId(Integer.parseInt(input.readLine()));
        		email.setEmail(input.readLine());
        		email.setTitle(input.readLine());
        		email.setBody(input.readLine());
        		email.setPath(input.readLine());
        		email.setAttach(input.readLine());
        		emailInfo.add(email);
        }
        for(int i = 0; i < emailInfo.size(); i++)
		{
			EmailInformation email = emailInfo.get(i);
			Object[] rowData = {email.getId(), email.getEmail(), email.getTitle(), email.getAttach()};
            tableModel.addRow(rowData);
		}
		tableModel.fireTableDataChanged();
		socket.close();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equals("Gửi Thư"))
		{
			try {
				sendLetter();
				attach = "No";
				TieuDeTf.setText("");
				NoiDungTf.setText("");
				path = "";
				fileInfo.setText("Null");
				JOptionPane.showMessageDialog(contentPane,"Đã gửi!");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==slFile)
		{
			path = null;
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\javaFile\\Client"));
			int response = fileChooser.showOpenDialog(null);
			if(response == JFileChooser.APPROVE_OPTION)
			{
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				path = file + "";
				System.out.println(path);
				attach = "Yes";
				fileInfo.setText(file.getName());
			}
		}
//		else if(str.equals("Delete"))
//		{
//			try {
//				deleteLetter();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
		else if(str.equals("Open"))
		{
			try {
				openLetter();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(str.equals("Reload"))
		{
			try {
				lastedLetter();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
