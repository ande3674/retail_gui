import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenCashierGUI extends JDialog{
    private JTextField itemNumberTextField;
    private JTextField quantityTextField;
    private JTextField totalTextField;
    private JButton checkoutButton;
    private JButton cancelButton;
    private JButton addButton;
    private JPanel mainPanel;

    private ItemDatabase db;

    protected OpenCashierGUI(ItemDatabase db){
        this.db = db;
        // Regular setup stuff
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setVisible(true);
        setTitle("Cashier");
        setPreferredSize(new Dimension(600, 400));

        pack();

        addListeners();
    }

    private void addListeners() {

    }
}
