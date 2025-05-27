package dungeon_crawler.components;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class RenderComponent extends Component {
    private static final Map<String, BufferedImage> spriteCache = new HashMap<>();
    private final String sprite;
    private int x;
    private int y;
    private int w;
    private int h;
    private int scale;

    public RenderComponent(String sprite, int x, int y, int w, int h, int scale) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.scale = scale;
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    private BufferedImage loadImage() {
        if (spriteCache.containsKey(sprite))
            return spriteCache.get(sprite);

        try {
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream(sprite));
            spriteCache.put(sprite, img);
            return img;
        } catch (IOException | NullPointerException e) {
            System.out.println("Unable to load " + sprite);
            return null;
        }
    }

    public BufferedImage getSprite() {
        BufferedImage image = loadImage();
        if (image == null) {
            System.err.println("Could not load image");
            return null;
        }
        BufferedImage subImage = image.getSubimage(x, y, w, h);
        return resizeImage(subImage, w * scale, h * scale);
    }

    public double getWidth() {
        return w;
    }

    public double getHeight() {
        return h;
    }
}
