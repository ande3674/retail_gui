import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteItemGUI extends JDialog {
    private JTextField itemNumberTextField;
    private JButton deleteButton;
    private JButton cancelButton;
    private JPanel mainPanel;

    private ItemDatabase db;

    protected DeleteItemGUI(ItemDatabase db){
        this.db = db;
        // Regular setup stuff
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setVisible(true);
        setTitle("Delete Item From Inventory");
        setPreferredSize(new Dimension(600, 400));

        pack();

        addListeners();
    }

    private void addListeners() {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(itemNumberTextField.getText());
                    int result = db.deleteItem(id);

                    if (result == -1) {
                        JOptionPane.showMessageDialog(DeleteItemGUI.this,
                                "Error deleting this item from the database.");
                    } else {
                        itemNumberTextField.setText("");
                        JOptionPane.showMessageDialog(DeleteItemGUI.this,
                                "Item successfully deleted from the database.");
                    }

                } catch (Exception ex){
                    JOptionPane.showMessageDialog(DeleteItemGUI.this,
                            "There was an error, try again.");
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(DeleteItemGUI.this,
                        "Exit this screen?", "Exit",
                        JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
                    setVisible(false);
                }
            }
        });
    }
}
