import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditInventoryGUI extends JDialog {
    private JButton addNewItemButton;
    private JButton updateItemButton;
    private JButton deleteItemButton;
    private JPanel mainPanel;
    private JButton cancelButton;

    private ItemDatabase db;

    protected EditInventoryGUI(ItemDatabase db){
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
        addNewItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewItemGUI anigui = new AddNewItemGUI(db);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(EditInventoryGUI.this,
                        "Exit this screen?", "Exit",
                        JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
                    setVisible(false);
                }
            }
        });
    }
}
