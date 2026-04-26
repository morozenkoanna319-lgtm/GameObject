import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameView extends JPanel implements MouseListener {
    private long lastFrameTime;
    private Background background = new Background();
    private RoadPanel roadPanel = new RoadPanel();
    private Engine engine;
    private CurrencyManager currency;

    public GameView(Engine engine) {
        this.engine = engine;
        this.currency = CurrencyManager.getInstance();
        lastFrameTime = System.currentTimeMillis();

        new Timer(16, e -> {
            long now = System.currentTimeMillis();
            float deltaTime = (now - lastFrameTime) / 1000.0f;
            lastFrameTime = now;
            if (deltaTime > 0.05f) deltaTime = 0.05f;
            engine.update(deltaTime);
            repaint();
        }).start();
        addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        background.draw(g2d, getWidth(), getHeight());
        roadPanel.setBounds(0, 0, getWidth(), getHeight());
        roadPanel.paintComponent(g2d);
        drawEnvironment(g2d, getWidth(), getHeight());
        engine.draw(g2d);
        drawCurrencyPanel(g2d);
        g2d.dispose();
    }

    // ✅ ДЕРЕВЬЯ ВРАЗБРОС — разные X и Y, никакой линии
    private void drawEnvironment(Graphics2D g, int w, int h) {
        // Нижняя трава начинается с Y=570, рисуем деревья с разбросом по Y (585-635)

        drawTree(g, 45, 615, false);   // Лиственное слева (низко)
        drawTree(g, 140, 595, true);   // Сосна (выше)
        drawBush(g, 95, 625);          // Куст между ними

        drawTree(g, 280, 630, false);  // Лиственное (низко, в центре-лево)
        drawTree(g, 410, 600, true);   // Сосна (выше, центр)
        drawBush(g, 350, 640);         // Куст

        drawTree(g, 550, 620, false);  // Лиственное (центр-право)
        drawTree(g, 670, 590, true);   // Сосна (высоко)
        drawBush(g, 610, 635);         // Куст

        drawTree(g, 760, 610, false);  // Лиственное край справа
        drawBush(g, 200, 650);         // Куст внизу слева
        drawBush(g, 720, 645);         // Куст внизу справа
    }

    private void drawTree(Graphics2D g, int x, int y, boolean isPine) {
        g.setColor(new Color(0, 0, 0, 60));
        g.fillOval(x - 15, y + 35, 50, 12); // Тень

        if (isPine) {
            g.setColor(new Color(101, 67, 33));
            g.fillRect(x + 15, y + 20, 12, 25); // Ствол
            g.setColor(new Color(34, 139, 34));
            g.fillPolygon(new int[]{x - 5, x + 45, x + 20}, new int[]{y + 30, y + 30, y}, 3);
            g.setColor(new Color(50, 179, 60));
            g.fillPolygon(new int[]{x, x + 40, x + 20}, new int[]{y + 15, y + 15, y - 15}, 3);
            g.setColor(new Color(80, 200, 80));
            g.fillPolygon(new int[]{x + 5, x + 35, x + 20}, new int[]{y + 0, y + 0, y - 30}, 3);
        } else {
            g.setColor(new Color(101, 67, 33));
            g.fillRect(x + 15, y + 20, 12, 25);
            g.setColor(new Color(34, 139, 34));
            g.fillOval(x, y, 45, 40);
            g.setColor(new Color(50, 179, 60));
            g.fillOval(x + 5, y - 10, 35, 35);
            g.setColor(new Color(80, 200, 80));
            g.fillOval(x + 10, y - 20, 25, 25);
        }
    }

    private void drawBush(Graphics2D g, int x, int y) {
        g.setColor(new Color(0, 0, 0, 50));
        g.fillOval(x - 5, y + 20, 30, 8);
        g.setColor(new Color(40, 130, 40));
        g.fillOval(x, y, 25, 22);
        g.setColor(new Color(60, 160, 60));
        g.fillOval(x + 4, y - 5, 18, 16);
    }

    private void drawCurrencyPanel(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = 15, y = 15;
        g2d.setColor(new Color(0, 0, 0, 180));
        g2d.fillRoundRect(x, y, 120, 45, 12, 12);
        g2d.setColor(new Color(255, 215, 0));
        g2d.setStroke(new BasicStroke(1.5f));
        g2d.drawRoundRect(x, y, 120, 45, 12, 12);
        g2d.setColor(new Color(255, 215, 0));
        g2d.fillOval(x + 8, y + 7, 30, 30);
        g2d.setColor(new Color(255, 180, 0));
        g2d.fillOval(x + 10, y + 9, 26, 26);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        g2d.drawString("", x + 15, y + 30);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString(currency.getCurrency() + " у.к.", x + 48, y + 30);
        g2d.dispose();
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    public void draw() { repaint(); }
}