import java.sql.*;
import java.util.Vector;

public class ItemDatabase {
    // Connection string
    private static final String CONNECTION_URL = "jdbc:sqlite:C:/Users/ce691/IdeaProjects/retail_app/src/main/java\\items";
    // Column names
    private static final String ID_COLUMN = "id";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String QUANTITY_COLUMN = "quantity";
    private static final String PRICE_COLUMN = "price";
    // SQL Statements - SELECT
    private static final String SELECT_BY_ID = "SELECT * FROM items WHERE " + ID_COLUMN + " IS ";
    // SQL Statements - INSERT
    private static final String INSERT_PREP_STATEMENT = "INSERT INTO items (description, quantity, price) VALUES (?, ?, ?)";
    // SQL Statements - UPDATE
    private static final String UPDATE_ITEM_BY_ID = "UPDATE items SET quantity=%d WHERE id=%d";
    // Constructor
    ItemDatabase(){}

    // Insert item
    public int addItem(String desc, int quantity, float price){
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL);
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_PREP_STATEMENT)){
            // Build the prepared statement and then execute it
            preparedStatement.setString(1, desc);
            preparedStatement.setInt(2, quantity);
            preparedStatement.setFloat(3, price);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            // return 1 to signify success
            return 1;
        } catch (SQLException sqle){
            return -1; // not successful
        }
    }
    // Update item quantity
    public int updateItemQuantity(int id, int new_quantity){
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL);
             Statement statement = conn.createStatement()) {
            // Build the SQL statement and execute it
            String SQL_UPDATE = String.format(UPDATE_ITEM_BY_ID, new_quantity, id);
            statement.executeUpdate(SQL_UPDATE);
            statement.close();

            return 1;
        }catch (SQLException sqle){
            return -1;
        }
    }
    // Get item by id
    public Vector getItemById(int id){
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL);
             Statement statement = conn.createStatement()){
            ResultSet rs = statement.executeQuery(SELECT_BY_ID + id);

            Vector v = new Vector();
            String desc;
            int quantity;
            float price;

            desc = rs.getString(DESCRIPTION_COLUMN);
            quantity = rs.getInt(QUANTITY_COLUMN);
            price = rs.getFloat(PRICE_COLUMN);

            v.add(id); v.add(desc); v.add(quantity); v.add(price);


            rs.close();

            return v;
        } catch (SQLException sqle){
            return null;
        }
    }
}
