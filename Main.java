import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Untitled Game");
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());
        Engine engine = new Engine();
        GameView gameView = new GameView(engine);
        Controls controls =new Controls(engine);
        frame.add(gameView);
        frame.add(controls,BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}