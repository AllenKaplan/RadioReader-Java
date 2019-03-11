import java.sql.*;

public class Driver {

	final static String URL = "jdbc:mysql://localhost:3306/mydb";
	final static String USER = "root";
	final static String PASSWORD = "password";
	
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRs = null;
	
	public Driver() throws ClassNotFoundException, SQLException {
		// 1. Get a connection to database
					// load and register JDBC driver for MySQL
					Class.forName("com.mysql.jdbc.Driver"); 
					myConn = DriverManager.getConnection(URL, USER, PASSWORD);
					System.out.println("connection made");
	}

	void addSong(String artist, String title){
			try {
			// 2. Create a statement
			myStmt = myConn.createStatement();

			// 3. Execute SQL query
			String sql = "insert into songs " + " (title, artist)"
					+ " values ('"+title+"', '" + artist + "')";

			myStmt.executeUpdate(sql);

			// 2. Create a statement
//			myStmt.close();
//			myStmt = myConn.createStatement();

			// 3. Execute SQL query
			myRs = myStmt.executeQuery("SELECT * FROM songs");

			// 4. Process the result set
			System.out.println("\n------CURRENT SONGS-----");
			while (myRs.next()) {
				System.out.println(myRs.getString("title") + ", " + myRs.getString("artist"));
			}
			} catch (Exception e) {
				e.printStackTrace();;
			}
	}
}
