package dungeon_crawler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GraphicsContext {
    private int ScreenWidth;
    private int ScreenHeight;
    private JFrame frame;
    private JPanel panel;
    private BufferedImage g2dimage;
    private Graphics2D g2d;
    private int size;

    public Graphics2D getG2d() {
        return g2d;
    }

    public JFrame getFrame() {
        return frame;
    }

    public int getSize() {
        return size;
    }

    public GraphicsContext() {
        ScreenWidth = 800;
        ScreenHeight = 800;
        frame = new JFrame();
        panel = new JPanel(true) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                draw(g);
            }
        };
        frame.setFocusable(true);
        frame.add(panel);
        frame.setTitle("Dungeon brawler");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(ScreenWidth, ScreenHeight);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void render() {
        panel.repaint();
    }

    private void draw(Graphics g) {
        Graphics2D graph2d = (Graphics2D) g;
        Toolkit.getDefaultToolkit().sync();
        graph2d.drawImage(g2dimage, 0, 0, null);
        graph2d.dispose();
        if (g2d != null)
            g2d.clearRect(0, 0, frame.getWidth(), frame.getHeight());
    }

    public void setGameDimensions(int GameCellsX, int GameCellsY) {
        size = Math.min(ScreenWidth / GameCellsX, ScreenHeight / GameCellsY);
        frame.setLocation(50, 50);
        frame.setSize(ScreenWidth, ScreenHeight);
        g2dimage = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
        g2d = g2dimage.createGraphics();
        g2d.setBackground(new Color(255, 255, 255));
        g2d.clearRect(0, 0, frame.getWidth(), frame.getHeight());
    }
}
