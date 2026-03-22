import java.awt.*;

public class UnitGunner extends GameObject {

    @Override
    protected void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int offsetX = (int) x;
        int offsetY = (int) y;

        // шлем
        g2.setColor(new Color(55, 120, 14));
        g2.fillOval(offsetX + 180, offsetY + 40, 120, 120);
        g2.setColor(Color.BLACK);
        g2.drawOval(offsetX + 180, offsetY + 40, 120, 120);

        // защита
        g2.setColor(new Color(30, 73, 5));
        g2.fillOval(offsetX + 265, offsetY + 75, 45, 70);
        g2.setColor(Color.BLACK);
        g2.drawOval(offsetX + 265, offsetY + 75, 45, 70);

        // лицо
        g2.setColor(new Color(255, 233, 214));
        g2.fillRoundRect(offsetX + 205, offsetY + 90, 65, 55, 15, 15);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(offsetX + 205, offsetY + 90, 65, 55, 15, 15);

        // глаза
        g2.fillOval(offsetX + 218, offsetY + 110, 8, 8);
        g2.fillOval(offsetX + 245, offsetY + 110, 8, 8);

        // рот
        g2.setStroke(new BasicStroke(3));
        g2.drawArc(offsetX + 225, offsetY + 125, 20, 10, 180, 180);

        // тело
        g2.setStroke(new BasicStroke(4));
        g2.setColor(new Color(55, 120, 14));
        g2.fillRoundRect(offsetX + 210, offsetY + 145, 85, 65, 20, 20);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(offsetX + 210, offsetY + 145, 85, 65, 20, 20);

        // АВТОМАТ
        // приклад
        g2.setColor(new Color(60, 60, 70));
        g2.fillRoundRect(offsetX + 255, offsetY + 150, 65, 45, 8, 8);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(offsetX + 255, offsetY + 150, 65, 45, 8, 8);

        // основа
        g2.setColor(new Color(85, 90, 100));
        g2.fillRoundRect(offsetX + 100, offsetY + 155, 160, 30, 8, 8);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(offsetX + 100, offsetY + 155, 160, 30, 8, 8);

        // ствол
        g2.setColor(new Color(70, 70, 80));
        g2.fillRect(offsetX + 55, offsetY + 163, 45, 14);
        g2.setColor(Color.BLACK);
        g2.drawRect(offsetX + 55, offsetY + 163, 45, 14);

        // магазин
        g2.setColor(new Color(75, 75, 85));
        g2.fillRect(offsetX + 225, offsetY + 185, 22, 32);
        g2.setColor(Color.BLACK);
        g2.drawRect(offsetX + 225, offsetY + 185, 22, 32);

        // рукоять
        g2.setColor(new Color(60, 60, 70));
        g2.fillRoundRect(offsetX + 165, offsetY + 185, 18, 28, 6, 6);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(offsetX + 165, offsetY + 185, 18, 28, 6, 6);
    }
}
