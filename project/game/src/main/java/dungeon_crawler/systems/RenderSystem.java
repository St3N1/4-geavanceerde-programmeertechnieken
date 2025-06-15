package dungeon_crawler.systems;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

import dungeon_crawler.GraphicsContext;
import dungeon_crawler.components.HealthComponent;
import dungeon_crawler.components.MovementComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.components.RenderComponent;
import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.enemies.EnemyEntity;
import dungeon_crawler.entities.objects.map.Chest;
import dungeon_crawler.entities.players.PlayerEntity;

public class RenderSystem {
    private GraphicsContext graphicsContext;
    private Graphics2D graphics2d;
    private final int radius = 150;

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

    private void showHasKey(boolean hasKey) {
        graphics2d.setColor(Color.ORANGE);
        graphics2d.drawString("KEY: " + hasKey, 310, 20);
    }

    private void drawDarknessOverlay(PlayerEntity player) {
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

    private void renderEntity(Entity entity) {
        if (entity.hasComponent(RenderComponent.class) && entity.hasComponent(PositionComponent.class)) {
            RenderComponent renderComponent = entity.getComponent(RenderComponent.class).get();
            PositionComponent positionComponent = entity.getComponent(PositionComponent.class).get();

            BufferedImage spriteImage = renderComponent.getSprite();
            int x = (int) positionComponent.getX();
            int y = (int) positionComponent.getY();

            int dx = 0;
            if (entity.hasComponent(MovementComponent.class)) {
                MovementComponent mov = entity.getComponent(MovementComponent.class).get();
                dx = mov.getX();
            }

            if (dx == -1) {
                AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
                tx.translate(-spriteImage.getWidth(null), 0);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                spriteImage = op.filter(spriteImage, null);
            }

            graphics2d.drawImage(spriteImage, x, y, null);

            // Color red on hit
            if (entity instanceof EnemyEntity || entity instanceof PlayerEntity) {
                if (entity.isHit()) {
                    for (int i = 0; i < spriteImage.getWidth(); i++) {
                        for (int j = 0; j < spriteImage.getHeight(); j++) {
                            int alpha = (spriteImage.getRGB(i, j) >> 24) & 0xff;
                            if (alpha != 0) {
                                graphics2d.setColor(new Color(255, 0, 0, 100));
                                graphics2d.fillRect(x + i, y + j, 1, 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public void render(List<Entity> entities, boolean showFps, double deltaTime) {
        graphics2d = graphicsContext.getG2d();
        graphics2d.setColor(Color.BLACK);
        graphics2d.fillRect(0, 0, graphicsContext.getFrame().getWidth(), graphicsContext.getFrame().getHeight());

        PlayerEntity player = null;
        Chest chest = null;
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            if (entity instanceof PlayerEntity) {
                player = (PlayerEntity) entity;
                continue;
            } else if (entity instanceof Chest) {
                chest = (Chest) entity;
                continue;
            } else
                renderEntity(entity);
        }

        if (chest != null)
            renderEntity(chest);

        if (player != null) {
            renderEntity(player);
            drawDarknessOverlay(player);
            showHealth(player.getComponent(HealthComponent.class).get().getHealth());
            showScore(player.getScore());
            showHasKey(player.getKey());
        }

        if (showFps)
            showFps(deltaTime);

        graphicsContext.render();
    }
}
