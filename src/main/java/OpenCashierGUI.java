import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;
import java.util.Vector;

public class OpenCashierGUI extends JDialog{
    private JTextField itemNumberTextField;
    private JTextField quantityTextField;
    private JTextField totalTextField;
    private JButton checkoutButton;
    private JButton cancelButton;
    private JButton addButton;
    private JPanel mainPanel;

    private ItemDatabase db;

    // Variables to keep track of cashier info
    private TreeMap<Integer, Integer> receipt; //Keys: id of item, Value: quantity left after purchase
    private float totalPrice; // running total of the sale price

    protected OpenCashierGUI(ItemDatabase db){
        this.db = db;
        this.receipt = new TreeMap();
        this.totalPrice = (float)0.0;
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

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Query the database for the item number and item info
                    int id = Integer.parseInt(itemNumberTextField.getText());
                    int quantity_desired = Integer.parseInt(quantityTextField.getText());
                    Vector v = db.getItemById(id); // id, description, quantity, price
                    String desc = (String)v.get(1);
                    int quantity = (int)v.get(2);
                    float price = (float)v.get(3);

                    // Make sure there are enough of the items in inventory
                    if (quantity < quantity_desired){//if inventory quantity is less than the desired quantity...
                        // show error message
                        // clear field so user can try again
                        JOptionPane.showMessageDialog(OpenCashierGUI.this, "There are only "
                                + quantity + " " + desc + "s in inventory, try again.");
                        quantityTextField.setText("");
                    }
                    else { // else, there are enough so ...
                        // Calculate total price and update the totalPrice variable
                        totalPrice += quantity_desired * price;
                        // Add information to the "receipt" data structure
                        receipt.put(id, quantity-quantity_desired);
                        // Update the text field(s)
                        totalTextField.setText("$"+totalPrice);
                    }

                } catch (Exception ex){
                    // Do something...
                }
            }
        });
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the new quantity of the items in the inventory
                for (int k : receipt.keySet()){
                    db.updateItemQuantity(k, receipt.get(k));
                }
                // reset variables
                receipt.clear();
                totalPrice = (float)0.0;
                // hide frame
                setVisible(false);
                // TODO display some sort of message
            }
        });

    }
}
