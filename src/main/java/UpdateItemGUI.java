import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateItemGUI extends JDialog{
    private JTextField newQuantityTextField;
    private JTextField itemNumberTextField;
    private JButton addButton;
    private JButton cancelButton;
    private JPanel mainPanel;

    private ItemDatabase db;

    protected UpdateItemGUI(ItemDatabase db){
        this.db = db;
        // Regular setup stuff
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setVisible(true);
        setTitle("Update Inventory");
        setPreferredSize(new Dimension(600, 400));

        pack();

        addListeners();
    }

    private void addListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int quantity = Integer.parseInt(newQuantityTextField.getText());
                    int id = Integer.parseInt(itemNumberTextField.getText());

                    int result = db.updateItemQuantity(id, quantity);

                    if (result == -1) {
                        JOptionPane.showMessageDialog(UpdateItemGUI.this,
                                "Error updating this item in the database.");
                    } else {
                        itemNumberTextField.setText("");
                        newQuantityTextField.setText("");
                        JOptionPane.showMessageDialog(UpdateItemGUI.this,
                                "Item successfully updated in the database.");
                    }
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(UpdateItemGUI.this,
                            "There was an error, try again.");
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(UpdateItemGUI.this,
                        "Exit this screen?", "Exit",
                        JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
                    setVisible(false);
                }
            }
        });
    }
}
