import javax.swing.*;

public class BresenhamAlgorithm extends JFrame {

    private final int wndWidth = 800, wndHeight = 600;

    public BresenhamAlgorithm() {
        super("Window");
        setSize(wndWidth, wndHeight);
        setResizable(false);
        //setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new DrawingComp());

    }

    public static void main(String[] args) {
        new BresenhamAlgorithm().setVisible(true);
    }
}
