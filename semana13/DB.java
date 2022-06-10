package semana13;
import java.sql.*;
// Clase que instancia el conector al DB.

public class DB {
	
	private final String db_path = "localhost";
	private final String db_port = ""; // normalmente: 3306
	private final String db_user = ""; // normalmente: root
	private final String db_pass = ""; // normalmente: "" (vacio)
	
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

}
