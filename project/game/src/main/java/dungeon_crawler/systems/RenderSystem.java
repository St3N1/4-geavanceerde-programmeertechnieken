package dungeon_crawler.systems;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.List;

import dungeon_crawler.GraphicsContext;
import dungeon_crawler.components.HealthComponent;
import dungeon_crawler.components.MovementComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.components.RenderComponent;
import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.players.PlayerEntity;

public class RenderSystem {
    private GraphicsContext graphicsContext;
    private Graphics2D graphics2d;

    public RenderSystem(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    private void showFps(double deltaTime) {
        double fps = 1000.0 / deltaTime;
        graphics2d.setColor(Color.WHITE);
        graphics2d.drawString("FPS: " + (int) fps, 10, 20);
    }

    private void showScore(int score) {
        graphics2d.setColor(Color.YELLOW);
        graphics2d.drawString("SCORE: " + (int) score, 110, 20);
    }

    private void showHealth(int health) {
        graphics2d.setColor(Color.RED);
        graphics2d.drawString("HEALTH: " + (int) health, 210, 20);
    }

    private void drawDarknessOverlay(PlayerEntity player) {
        final int radius = 150;

        PositionComponent pos = player.getComponent(PositionComponent.class).orElse(null);
        if (pos == null)
            return;

        int width = graphicsContext.getFrame().getWidth();
        int height = graphicsContext.getFrame().getHeight();

        int centerX = (int) (pos.getX() + pos.getWidth() / 2);
        int centerY = (int) (pos.getY() + pos.getHeight() / 2);

        BufferedImage overlay = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2Overlay = overlay.createGraphics();

        float[] dist = { 0.0f, 0.7f, 1.0f };
        Color[] colors = {
                new Color(0, 0, 0, 0),
                new Color(0, 0, 0, 130),
                new Color(0, 0, 0, 220)
        };

        RadialGradientPaint paint = new java.awt.RadialGradientPaint(new Point(centerX, centerY), radius, dist, colors);
        g2Overlay.setPaint(paint);
        g2Overlay.fillRect(0, 0, width, height);

        g2Overlay.dispose();
        graphics2d.drawImage(overlay, 0, 0, null);
    }

    public void render(List<Entity> entities, boolean showFps, double deltaTime, int score) {
        graphics2d = graphicsContext.getG2d();
        graphics2d.setColor(Color.BLACK);
        graphics2d.fillRect(0, 0, graphicsContext.getFrame().getWidth(), graphicsContext.getFrame().getHeight());

        int playerHealth = 0;
        PlayerEntity player = null;

        for (Entity entity : entities) {
            if (entity.hasComponent(RenderComponent.class) && entity.hasComponent(PositionComponent.class)) {
                RenderComponent renderComponent = entity.getComponent(RenderComponent.class).get();
                PositionComponent positionComponent = entity.getComponent(PositionComponent.class).get();

                BufferedImage spriteImage = renderComponent.getSprite();
                int x = (int) positionComponent.getX();
                int y = (int) positionComponent.getY();

                boolean flip = entity.getComponent(MovementComponent.class)
                        .map(MovementComponent::isFacingLeft)
                        .orElse(false);

                if (entity instanceof PlayerEntity) {
                    player = (PlayerEntity) entity;
                    playerHealth = entity.getComponent(HealthComponent.class).get().getHealth();
                }

                if (flip) {
                    AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
                    AffineTransform old = graphics2d.getTransform();
                    graphics2d.translate(x + spriteImage.getWidth(), y);
                    graphics2d.drawImage(spriteImage, tx, null);
                    graphics2d.setTransform(old);
                } else {
                    graphics2d.drawImage(spriteImage, x, y, null);
                }
            }
        }
        if (player != null) {
            drawDarknessOverlay(player);
        }
        showHealth(playerHealth);
        showScore(score);
        if (showFps)
            showFps(deltaTime);

        graphicsContext.render();
    }
}
