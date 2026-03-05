
import javax.swing.*;
import java.awt.*;

public class Controls extends JPanel {
    public Controls(Engine engine) {
        setLayout(new FlowLayout());
        JButton btnSpawn = new JButton("SPAWN");
        btnSpawn.addActionListener(e -> {
            engine.spawnObject(new GameObject(0,10,engine.getScreenHeight(),50,3));
        });
        add(btnSpawn);
    }
}
