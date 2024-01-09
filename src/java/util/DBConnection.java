
package util;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletException;

public class DBConnection 
{
    //method connection
    public static Connection createConnection()
    {
        Connection con = null;
        
        try 
        {
            //declare driver and connection string
            String driver = "org.apache.derby.jdbc.ClientDriver";
            String connectionString = "jdbc:derby://localhost:1527/KingLandHotel;create=true;user=app;password=app";
            
            //load driver
            Class.forName(driver);
            //Return connection to database
            return DriverManager.getConnection(connectionString);
            
        } 
        catch (Exception ex) {
            ex.getMessage();
        }
        return null;
    }
}
