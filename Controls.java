import javax.swing.*;
import java.awt.*;

/**
 * Главный класс игры.
 * Исправлено: башня теперь корректно создаётся посередине экрана.
 *
 * By AmericanCoolBoyUSA777
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Engine engine = Engine.getInstance();

            // координаты башни
            int towerX = engine.getScreenWidth() / 2 + 200;
            int towerY = engine.getScreenHeight() - 180;

            // создание башни через динамическую загрузку класса
            try {
                Class<?> towerClass = Class.forName("Tower");
                GameObject tower = (GameObject) towerClass
                        .getConstructor(int.class, float.class, float.class, int.class, float.class)
                        .newInstance(1, (float) towerX, (float) towerY, 100, 0f);
                tower.setFraction(2);
                engine.spawnObject(tower);
                System.out.println("Башня создана на позиции: " + towerX + ", " + towerY);
            } catch (Exception e) {
                // заглушка на случай отсутствия класса Tower
                System.err.println("Класс Tower не найден, создаём заглушку");
                GameObject dummyTower = new GameObject(1, towerX, towerY, 100, 0f, Color.GRAY);
                dummyTower.setFraction(2);
                dummyTower.setHealth(200);
                engine.spawnObject(dummyTower);
            }

            // ========== ДОБАВЛЯЕМ СПАВНЕР ВРАГОВ ==========
            // Создаём спавнер
            EnemySpawner enemySpawner = new EnemySpawner();
            // Добавляем спавнер в engine (чтобы он жил в игровом мире)
            engine.spawnObject(enemySpawner);
            System.out.println("EnemySpawner добавлен в игру!");
            // ============================================

            GameView gameView = new GameView(engine);
            JFrame frame = new JFrame("Tower Battle - Archer Defense");
            frame.setSize(engine.getScreenWidth(), engine.getScreenHeight());
            frame.setLayout(new BorderLayout());
            frame.add(gameView, BorderLayout.CENTER);
            frame.add(new Controls(engine), BorderLayout.SOUTH);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
