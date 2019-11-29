package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {
	Connection connection;
	public static void main(String[] args) {
		DatabaseController databaseController= new DatabaseController();
	}
	
	public DatabaseController() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/databasetd", "root", "4223");
			// here sonoo is database name, root is username and password
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from events");
			while (rs.next())
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void insertImage() {
        Connection connection = null;
        PreparedStatement statement = null;
        FileInputStream inputStream = null;
 
        try {
            File image = new File("C:/honda.jpg");
            inputStream = new FileInputStream(image);
 
            
            statement = connection.prepareStatement("insert into "
            		+ "trn_imgs(img_title, img_data) " + "values(?,?)");
            statement.setString(1, "Honda Car");
            statement.setBinaryStream(2, (InputStream) inputStream, (int)(image.length()));
 
            statement.executeUpdate();
 
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: - " + e);
        } catch (SQLException e) {
            System.out.println("SQLException: - " + e);
        } finally {
 
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                System.out.println("SQLException Finally: - " + e);
            }
        }
    }

}
