import java.awt.*;

public class Background {

    public void draw(Graphics g, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 1. Небо
        GradientPaint sky = new GradientPaint(
                0, 0, new Color(135, 206, 235),
                0, 500, new Color(176, 224, 230)
        );
        g2d.setPaint(sky);
        g2d.fillRect(0, 0, width, height);

        // 2. Облака
        drawCloud(g2d, 120, 80, 70);
        drawCloud(g2d, 400, 140, 50);
        drawCloud(g2d, 650, 90, 60);
        drawCloud(g2d, 280, 220, 45);

        // 3. ВЕРХНЯЯ ТРАВА - без деревьев, только кусты
        drawBush(g2d, 100, 415);
        drawBush(g2d, 250, 420);
        drawBush(g2d, 450, 410);
        drawBush(g2d, 600, 425);
        drawBush(g2d, 750, 415);

        // 4. НИЖНЯЯ ТРАВА - деревья вразброс (начало Y=570)
        // Разные позиции X и Y для естественности
        drawTree(g2d, 60, 580, false, 1.1f);   // Лиственное слева
        drawTree(g2d, 150, 570, true, 1.3f);   // Крупная сосна
        drawTree(g2d, 230, 590, false, 0.9f);  // Маленькое лиственное
        drawBush(g2d, 200, 595);

        drawTree(g2d, 320, 575, true, 1.0f);   // Сосна
        drawTree(g2d, 420, 585, false, 1.2f);  // Лиственное в центре
        drawBush(g2d, 380, 590);

        drawTree(g2d, 520, 570, true, 1.1f);   // Сосна
        drawBush(g2d, 500, 585);

        drawTree(g2d, 620, 595, false, 1.0f);  // Лиственное
        drawTree(g2d, 700, 575, true, 1.2f);   // Крупная сосна справа
        drawBush(g2d, 680, 590);

        drawTree(g2d, 770, 585, false, 1.1f);  // Лиственное край справа
        drawBush(g2d, 750, 595);

        // Дополнительные кусты
        drawBush(g2d, 100, 600);
        drawBush(g2d, 300, 585);
        drawBush(g2d, 560, 600);
    }

    private void drawCloud(Graphics2D g, int x, int y, int size) {
        g.setColor(new Color(255, 255, 255, 200));
        g.fillOval(x, y, size, size * 2/3);
        g.fillOval(x + size/3, y - size/4, size * 2/3, size * 2/3);
        g.fillOval(x + size * 2/3, y, size * 3/4, size * 2/3);
    }

    private void drawTree(Graphics2D g, int x, int y, boolean isPine, float size) {
        // Тень (масштабируется)
        g.setColor(new Color(0, 0, 0, 50));
        g.fillOval(Math.round(x - 5 * size), Math.round(y + 40 * size),
                Math.round(40 * size), Math.round(8 * size));

        if (isPine) {
            // Сосна — ствол
            g.setColor(new Color(139, 90, 43));
            g.fillRect(Math.round(x + 15 * size), Math.round(y + 25 * size),
                    Math.round(10 * size), Math.round(20 * size));

            // Крона (3 уровня)
            g.setColor(new Color(34, 120, 34));
            int[] x1 = {Math.round(x), Math.round(x + 20 * size), Math.round(x + 10 * size)};
            int[] y1 = {Math.round(y + 30 * size), Math.round(y + 30 * size), Math.round(y)};
            g.fillPolygon(x1, y1, 3);

            g.setColor(new Color(50, 150, 50));
            int[] x2 = {Math.round(x + 2 * size), Math.round(x + 18 * size), Math.round(x + 10 * size)};
            int[] y2 = {Math.round(y + 20 * size), Math.round(y + 20 * size), Math.round(y - 10 * size)};
            g.fillPolygon(x2, y2, 3);

            g.setColor(new Color(80, 180, 80));
            int[] x3 = {Math.round(x + 4 * size), Math.round(x + 16 * size), Math.round(x + 10 * size)};
            int[] y3 = {Math.round(y + 10 * size), Math.round(y + 10 * size), Math.round(y - 20 * size)};
            g.fillPolygon(x3, y3, 3);
        } else {
            // Лиственное дерево — ствол
            g.setColor(new Color(139, 90, 43));
            g.fillRect(Math.round(x + 15 * size), Math.round(y + 30 * size),
                    Math.round(10 * size), Math.round(15 * size));

            // Крона
            g.setColor(new Color(34, 120, 34));
            g.fillOval(Math.round(x), Math.round(y),
                    Math.round(40 * size), Math.round(35 * size));

            g.setColor(new Color(50, 150, 50));
            g.fillOval(Math.round(x + 5 * size), Math.round(y - 8 * size),
                    Math.round(30 * size), Math.round(28 * size));

            g.setColor(new Color(80, 180, 80));
            g.fillOval(Math.round(x + 10 * size), Math.round(y - 15 * size),
                    Math.round(20 * size), Math.round(20 * size));
        }
    }

    private void drawTree(Graphics2D g, int x, int y, boolean isPine) {
        drawTree(g, x, y, isPine, 1.0f);
    }

    private void drawBush(Graphics2D g, int x, int y) {
        g.setColor(new Color(0, 0, 0, 40));
        g.fillOval(x - 5, y + 18, 25, 6);
        g.setColor(new Color(40, 130, 40));
        g.fillOval(x, y, 22, 18);
        g.setColor(new Color(60, 160, 60));
        g.fillOval(x + 4, y - 4, 16, 14);
    }
}