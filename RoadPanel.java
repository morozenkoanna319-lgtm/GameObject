import javax.swing.*;
import java.awt.*;

public class RoadPanel extends JPanel {

    public RoadPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        // Земля начинается с 400-го пикселя
        int groundY = 400;
        int grassTopH = 30;
        int roadH = 140;
        int grassBottomH = h - groundY - grassTopH - roadH;

        // 1. Верхняя трава (более естественная)
        g2d.setColor(new Color(76, 175, 80));
        g2d.fillRect(0, groundY, w, grassTopH);

        // Зубчики травы разной высоты
        g2d.setColor(new Color(56, 142, 60));
        for (int i = 0; i < w; i += 15) {
            int height = 8 + (i % 3) * 4; // Разная высота
            int[] x = {i, i + 7, i + 15};
            int[] y = {groundY + grassTopH, groundY + grassTopH - height, groundY + grassTopH};
            g2d.fillPolygon(x, y, 3);
        }

        // 2. Дорога (грунт/песок)
        g2d.setColor(new Color(188, 143, 94));
        g2d.fillRect(0, groundY + grassTopH, w, roadH);

        // Камни и неровности
        g2d.setColor(new Color(160, 110, 70));
        g2d.fillOval(150, groundY + grassTopH + 40, 30, 12);
        g2d.fillOval(450, groundY + grassTopH + 90, 45, 15);
        g2d.fillOval(700, groundY + grassTopH + 50, 25, 10);
        g2d.fillOval(300, groundY + grassTopH + 70, 20, 8);

        // 3. Нижняя трава (более густая)
        g2d.setColor(new Color(67, 160, 71));
        g2d.fillRect(0, groundY + grassTopH + roadH, w, grassBottomH);

        // Тёмная кромка
        g2d.setColor(new Color(46, 125, 50));
        g2d.fillRect(0, groundY + grassTopH + roadH, w, 12);

        // Трава на переднем плане
        g2d.setColor(new Color(50, 140, 50));
        for (int i = 0; i < w; i += 12) {
            int height = 6 + (i % 2) * 4;
            int[] x = {i, i + 5, i + 10};
            int[] y = {groundY + grassTopH + roadH + 12,
                    groundY + grassTopH + roadH + 12 - height,
                    groundY + grassTopH + roadH + 12};
            g2d.fillPolygon(x, y, 3);
        }

        g2d.dispose();
    }
}