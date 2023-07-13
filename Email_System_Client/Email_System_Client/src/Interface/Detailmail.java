package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.SwingConstants;
import java.awt.Color;

public class Detailmail extends JFrame {

	private JPanel contentPane;
	private JTextPane textPane;
	private JLabel imageLabel;

	public static void main(String[] args) {
		Detailmail a = new Detailmail("khoa300504@gmail.com", "Hello", "friend with love <3", null);
		a.setVisible(true);
	}

	public Detailmail(String sender, String title, String body, String path) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 570, 700);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 555, 699);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel TenNguoiGui = new JLabel(sender);
		TenNguoiGui.setForeground(Color.BLACK);
		TenNguoiGui.setIcon(null);
		TenNguoiGui.setBackground(new Color(255, 0, 0));
		TenNguoiGui.setHorizontalAlignment(SwingConstants.CENTER);
		TenNguoiGui.setFont(new Font("Tahoma", Font.PLAIN, 20));
		TenNguoiGui.setBounds(42, 30, 464, 70);
		panel.add(TenNguoiGui);
		
		JLabel lblNewLabel_4 = new JLabel(title);
		lblNewLabel_4.setBounds(160, 60, 464, 147);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setForeground(Color.BLUE);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_1 = new JLabel(body);
		lblNewLabel_1.setBackground(Color.white);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBounds(42, 170, 464, 140);
		lblNewLabel_1.setForeground(Color.black);
		panel.add(lblNewLabel_1);
		if(path != null)
		{
		if(getFileExtensionUsingStandardMethods(path).equals("txt"))
		{
			textPane = new JTextPane();
	        textPane.setEditable(false);
	        JScrollPane scrollPane = new JScrollPane(textPane);
	        scrollPane.setBounds(42, 330, 464, 250);
	        panel.add(scrollPane);
	        this.displayTextFile(path);
		}
		else if(getFileExtensionUsingStandardMethods(path).equals("jpg") || getFileExtensionUsingStandardMethods(path).equals("png"))
		{
			imageLabel = new JLabel();
			imageLabel.setBounds(42, 330, 464, 250);
	        panel.add(imageLabel);
	        this.displayImage(path);
		}
		}
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\img\\image\\b12.jpg"));
		lblNewLabel.setBounds(0, 0, 700, 700);
		panel.add(lblNewLabel);
	}
	
	private String getFileExtensionUsingStandardMethods(String filePath) {
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1) {
            return "";
        }
        return fileName.substring(dotIndex + 1);
    }
	
	public void displayImage(String imagePath) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            ImageIcon imageIcon = new ImageIcon(image);
            imageLabel.setIcon(scaleImage(imageIcon));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private ImageIcon scaleImage(ImageIcon imageIcon) {
        int maxWidth = imageLabel.getWidth();
        int maxHeight = imageLabel.getHeight();

        int originalWidth = imageIcon.getIconWidth();
        int originalHeight = imageIcon.getIconHeight();

        double scaleFactor = Math.min((double) maxWidth / originalWidth, (double) maxHeight / originalHeight);

        int scaledWidth = (int) (originalWidth * scaleFactor);
        int scaledHeight = (int) (originalHeight * scaleFactor);

        Image scaledImage = imageIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
	
	public void displayTextFile(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            textPane.setText(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
