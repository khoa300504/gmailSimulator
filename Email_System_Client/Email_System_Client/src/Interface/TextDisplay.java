package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TextDisplay extends JFrame {
    private JLabel imageLabel;

    public TextDisplay() {
        setTitle("Image Display");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        imageLabel = new JLabel();
        add(imageLabel);

        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void displayImage(String imagePath) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            ImageIcon imageIcon = new ImageIcon(image);
            imageLabel.setIcon(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TextDisplay imageDisplay = new TextDisplay();
            imageDisplay.displayImage("C:\\Users\\TGDD\\OneDrive\\Desktop\\java\\javaFile\\Client\\itachi.jpg");
        });
    }
}