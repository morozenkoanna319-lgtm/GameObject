import java.awt.*;
import java.util.List;

public class UnitGunner extends BaseUnit {

    @Override
    public void attack(GameObject target, float currentTime) {
        // todo
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int x = (int) this.x;
        int y = (int) this.y;
        float k = this.size / 100.0f;
        if (k <= 0) k = 1.0f;

        g2.setColor(new Color(55, 120, 14));
        g2.fillOval(x + 45, y + 10, 30, 30);
        g2.setColor(Color.BLACK);
        g2.drawOval(x + 45, y + 10, 30, 30);

        g2.setColor(new Color(255, 233, 214));
        g2.fillRoundRect(x + 50, y + 20, 16, 14, 4, 4);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(x + 50, y + 20, 16, 14, 4, 4);

        g2.fillOval(x + 53, y + 25, 2, 2);
        g2.fillOval(x + 60, y + 25, 2, 2);

        g2.setColor(new Color(55, 120, 14));
        g2.fillRoundRect(x + 51, y + 36, 22, 18, 5, 5);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(x + 51, y + 36, 22, 18, 5, 5);

        g2.setColor(new Color(60, 60, 70));
        g2.fillRoundRect(x + 64, y + 37, 16, 11, 2, 2);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(x + 64, y + 37, 16, 11, 2, 2);

        g2.setColor(new Color(85, 90, 100));
        g2.fillRoundRect(x + 25, y + 39, 40, 7, 2, 2);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(x + 25, y + 39, 40, 7, 2, 2);

        g2.setColor(new Color(70, 70, 80));
        g2.fillRect(x + 14, y + 41, 11, 3);
        g2.setColor(Color.BLACK);
        g2.drawRect(x + 14, y + 41, 11, 3);

        g2.setColor(new Color(75, 75, 85));
        g2.fillRect(x + 56, y + 46, 5, 8);
        g2.setColor(Color.BLACK);
        g2.drawRect(x + 56, y + 46, 5, 8);

        g2.setColor(new Color(60, 60, 70));
        g2.fillRoundRect(x + 41, y + 46, 5, 7, 2, 2);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(x + 41, y + 46, 5, 7, 2, 2);

    }
}