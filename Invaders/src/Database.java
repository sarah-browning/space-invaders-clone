import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	//Attributes
	private static final long serialVersionUID = -8138325704828394630L;
	private static Connection conn = null;
	private static Statement stmt = null;
	private static PreparedStatement pstmt = null;
	private static String sql;
	private static ResultSet rs;
	
	public void connect() {
	
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("Database driver loaded.");
			
			String dbURL = "jdbc:sqlite:scores.db";
			conn = DriverManager.getConnection(dbURL);
			
			if (conn != null) {
				System.out.println("Connected to database.");
				//Turns off auto commit. Forces a two step process of submitting and then committing.
				conn.setAutoCommit(false);
				
				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				System.out.println("Driver name: " + dm.getDriverName());
				System.out.println("Driver version: " + dm.getDriverVersion());
				System.out.println("Product name: " + dm.getDatabaseProductName());
				System.out.println("Product version: " + dm.getDatabaseProductVersion());
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void createTable() {
		
		try {
			stmt = conn.createStatement();
			
			sql = "CREATE TABLE IF NOT EXISTS SCORE " + 
						 "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
						 " NAME CHAR(50) NOT NULL, " +
						 " SCORE INT NOT NULL)";				
			stmt.executeUpdate(sql);
			conn.commit();
		
			System.out.println("Score table created successfully.");
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void recordScore(String name, int score) {
		sql = "INSERT INTO SCORE (NAME, SCORE) VALUES (?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, score);
			pstmt.executeUpdate();
			conn.commit();
			
			System.out.println("Record inserted.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getDBTable() {
		try {
			if (rs != null) {
				sql = "SELECT rowid, * FROM SCORE ORDER BY (SCORE, NAME) DESC LIMIT 10";
				
				connect();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery(sql);
				
				while (rs.next()) {
					
					System.out.println(rs.getInt("RowId") + rs.getString("name") + rs.getInt("score"));
				}
				rs.close();
				closeConnection();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			conn.close();
			System.out.println("Database connection closed.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Getters
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Connection getConn() {
		return conn;
	}

	public static Statement getStmt() {
		return stmt;
	}

	public static PreparedStatement getPstmt() {
		return pstmt;
	}

	public static String getSql() {
		return sql;
	}

	public static ResultSet getRs() {
		return rs;
	}

	//Setters
	public static void setConn(Connection conn) {
		Database.conn = conn;
	}

	public static void setStmt(Statement stmt) {
		Database.stmt = stmt;
	}

	public static void setPstmt(PreparedStatement pstmt) {
		Database.pstmt = pstmt;
	}

	public static void setSql(String sql) {
		Database.sql = sql;
	}

	public static void setRs(ResultSet rs) {
		Database.rs = rs;
	}

}
