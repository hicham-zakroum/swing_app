import java.sql.*;
import javax.swing.JOptionPane;

public class ConnectionBD {
	public static Connection Connection_Mysql() {
		
			try{
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing?characterEncoding=utf8","root","");
			System.out.println("connection ...");
			return  con ;
			}catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, e);
				return null;
			}
			
			
		
		
	}
	
}
