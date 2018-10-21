import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewItemGUI extends JDialog{
    private JTextField quantityTextField;
    private JTextField priceTextField;
    private JTextField descriptionTextField;
    private JButton addButton;
    private JButton cancelButton;
    private JPanel mainPanel;

    private ItemDatabase db;

    protected AddNewItemGUI(ItemDatabase db){
        this.db = db;
        // Regular setup stuff
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setVisible(true);
        setTitle("Edit Inventory");
        setPreferredSize(new Dimension(600, 400));

        pack();

        addListeners();
    }

    private void addListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String desc = descriptionTextField.getText();
                    int quantity = Integer.parseInt(quantityTextField.getText());
                    float price = Float.parseFloat(priceTextField.getText());

                    int result = db.addItem(desc, quantity, price);

                    if (result == -1) {
                        JOptionPane.showMessageDialog(AddNewItemGUI.this, "Error adding this item to the database.");
                    } else {
                        descriptionTextField.setText("");
                        quantityTextField.setText("");
                        priceTextField.setText("");
                        JOptionPane.showMessageDialog(AddNewItemGUI.this, "Item successfully added to the database.");
                    }
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(AddNewItemGUI.this, "There was an error, try again.");
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(AddNewItemGUI.this,
                        "Exit this screen?", "Exit",
                        JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
                    setVisible(false);
                }
            }
        });
    }
}
