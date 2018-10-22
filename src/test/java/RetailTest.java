import java.sql.*;
import input.InputUtils;
import static junit.framework.TestCase.*;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;

@PrepareForTest(InputUtils.class)
public class RetailTest {

    private String databaseURL = "jdbc:sqlite:C:/Users/ce691/IdeaProjects/retail_app/src/main/java\\items";

    @Test
    public void testTestDevelopmentDatabaseAndTableExists() throws Exception {
        testTableExists(databaseURL);
    }


    public void testTableExists(String dbURL) throws Exception {

        try (Connection conn = DriverManager.getConnection(dbURL);
             Statement statement = conn.createStatement() ) {

            String tableInfo = "PRAGMA table_info(items)";
            ResultSet rs = statement.executeQuery(tableInfo);

            rs.next();
            String itemIdCol = rs.getString(2);
            String itemIdType = rs.getString(3);

            rs.next();
            String descriptionCol = rs.getString(2);
            String descriptionType = rs.getString(3);

            rs.next();
            String quantityCol = rs.getString(2);
            String quantityType = rs.getString(3);

            rs.next();
            String priceCol = rs.getString(2);
            String priceType = rs.getString(3);

            assertFalse("The database should only contain four columns, name and quantity.", rs.next());   // No more columns.

            assertEquals("The first column's name should be 'id'", "id", itemIdCol);
            assertEquals("The first column's type should be 'integer'", "INTEGER", itemIdType);

            assertEquals("The second column's name should be 'description'", "description", descriptionCol);
            assertEquals("The second column's type should be 'text'", "TEXT", descriptionType);

            assertEquals("The third column's name should be 'quantity'", "quantity", quantityCol);
            assertEquals("The third column's type should be 'integer'", "INTEGER", quantityType);

            assertEquals("The fourth column's name should be 'price'", "price", priceCol);
            assertEquals("The fourth column's type should be 'real'", "REAL", priceType);


        } catch (SQLException e) {
            fail("Database not configured correctly, " + e.getMessage());
        }
    }
}
