import javax.swing.*;
import java.awt.*;

public class Controls extends JPanel {
    public Controls(Engine game) {
        setLayout(new FlowLayout());

        add(new Button("Spawn Base", new GameObject() /* under construction */, () -> {
            System.out.println("spawn Base");
            // under construction
        }));

        add(new Button("Spawn Archer", new UnitArcher() , () -> {
            // under construction
        }));

        add(new Button("Spawn Tank", new UnitDinoRider(), () -> {
            // under construction
        }));
    }
}
