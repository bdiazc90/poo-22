package semana16;

import java.sql.*;
// Clase que instancia el conector al DB.

public class DB {
	
	private final String db_path = "localhost";
	private final String db_port = "8889"; // normalmente: 3306
	private final String db_user = "root"; // normalmente: root
	private final String db_pass = "root"; // normalmente: "" (vacio)
	
	private boolean loadedDriver = false;

	public DB() {
		System.out.println("Intentando cargar driver...");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			loadedDriver = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(String db_name) {
		Connection con = null;
		if (loadedDriver) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + db_path + ":" + db_port + "/" + db_name, db_user, db_pass);
				System.out.println("Conexción con: " + db_name + " exitosa!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Falta cargar el driver");
		}
		return con;
	}
	
	protected static void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
	
	protected static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

}
