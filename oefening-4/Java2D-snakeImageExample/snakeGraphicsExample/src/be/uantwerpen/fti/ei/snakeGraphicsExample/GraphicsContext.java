package be.uantwerpen.fti.ei.snakeGraphicsExample;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicsContext {
    private int ScreenWidth;
    private int ScreenHeight;
    private JFrame frame;
    private JPanel panel;
    private BufferedImage g2dimage;     // used for drawing
    private Graphics2D g2d;             // always draw in this one
    public BufferedImage backgroundImg;
    public BufferedImage snakeSprite;
    private int size;                   // cel size

    public Graphics2D getG2d() {
        return g2d;
    }
    public JFrame getFrame() {
        return frame;
    }
    public int getSize() {
        return size;
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight){
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    private void loadImages() {
        backgroundImg = null;
        try {
            backgroundImg = ImageIO.read(new File("src/resource/snakeskin_background.png"));
            snakeSprite = ImageIO.read(new File("src/resource/snake-graphics.png"));
        } catch (IOException e) {
            System.out.println("Unable to load snake.png or snake-graphics.png!");
        }
    }

    public GraphicsContext() {
        ScreenWidth = 600;
        ScreenHeight = 500;
        frame = new JFrame();
        panel = new JPanel(true) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                doDrawing(g);
            }
        };
        frame.setFocusable(true);
        frame.add(panel);
        frame.setTitle("Graphics example");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(ScreenWidth, ScreenHeight);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void render() {
        panel.repaint();
    }

    private void doDrawing(Graphics g) {
        Graphics2D graph2d = (Graphics2D) g;
        Toolkit.getDefaultToolkit().sync();
        graph2d.drawImage(g2dimage, 0, 0, null);   // copy buffered image
        graph2d.dispose();
        if (g2d != null)
            g2d.drawImage(backgroundImg,0, 0, null);
    }

    public void setGameDimensions(int GameCellsX, int GameCellsY) {
        size = Math.min(ScreenWidth/GameCellsX, ScreenHeight/GameCellsY);
        System.out.println("size: "+size);
        frame.setLocation(50,50);
        frame.setSize(ScreenWidth, ScreenHeight);
        loadImages();
        try {
            backgroundImg = resizeImage(backgroundImg, frame.getWidth(), frame.getHeight());
            snakeSprite = resizeImage(snakeSprite, size*5, size*4);
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
        g2dimage = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
        g2d = g2dimage.createGraphics();
        g2d.drawImage(backgroundImg,0, 0, null);
    }
}
