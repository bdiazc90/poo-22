package semana16;

import java.util.*;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.*;

public class EquipoDAO {
	
	static DB db = new DB();
	static Scanner sc = new Scanner(System.in);
	
	private static String FIND_ALL = "SELECT * FROM equipo";
	private static String INSERT = "INSERT INTO equipo(pais,puntaje,grupo) VALUES (?,?,?)";
	
	public static void agregarEquipo() {
		System.out.println("--> Agregar Equipo:");
		EquipoVO equipo = new EquipoVO();
		System.out.println(" - pais: ");
		equipo.setPais(sc.nextLine());
		System.out.println(" - puntaje: ");
		equipo.setPuntaje(Integer.parseInt(sc.nextLine()));
		System.out.println(" - grupo: ");
		equipo.setGrupo(sc.nextLine());
		int rows = insert(equipo);
		if (rows > 0) {
			System.out.println("--> Equipo Agregado!");
		}
	}
	
	public static void mostrarEquipos() {
		List<EquipoVO> equipos = findAll();
		System.out.println("--> Mostrando equipos:");
		for (EquipoVO e : equipos) {
			System.out.println(
					e.getId() + ": " +
					e.getPais() + " / " + 
					e.getPuntaje() + " / " + 
					e.getGrupo()
			);
		}
	}
	
	public static void mostrarReporte() {
		String inputFile = "/Users/brunodiazcabanillas/workspace/eclipse/semana16/ReporteEquipos.jrxml";
		List<EquipoVO> equipos = findAll();
		// Convertir la lista de equipos a un objeto tipo JRBeanCollectionDataSource:
		JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(equipos);
		// Crear un Map para definir los parámetros del JasperReport:
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("CollectionBeanParam", itemsJRBean);
		try {
			// Leer el jrxml:
			InputStream input = new FileInputStream(new File(inputFile));
			
			// Cargar el jrxml:
			JasperDesign jasperDesign = JRXmlLoader.load(input);
			
			// Compilar el jrxml con la ayuda de la class JasperReport:
	        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
	        
	        // Using jasperReport object to generate PDF:
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
	        
	        // call jasper engine to display report in jasperviewer window:
	        JasperViewer.viewReport(jasperPrint);
	        
	        System.out.println("Reporte generado!.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int insert(EquipoVO equipo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = db.getConnection("poo_mundial");
			stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, equipo.getPais());
			stmt.setInt(2, equipo.getPuntaje());
			stmt.setString(3, equipo.getGrupo());
			int result = stmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(stmt);
			DB.close(conn);
		}
		return 0;
	}
	
	private static List<EquipoVO> findAll() {
		Connection con = null;
		Statement stmt = null;
		List<EquipoVO> equipos = new ArrayList<EquipoVO>();
		try {
			con = db.getConnection("poo_mundial");
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(FIND_ALL);
			while (rs.next()) {
				EquipoVO e = new EquipoVO();
				e.setId(rs.getInt("id"));
				e.setPais(rs.getString("pais"));
				e.setPuntaje(rs.getInt("puntaje"));
				e.setGrupo(rs.getString("grupo"));
				equipos.add(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(con);
			DB.close(stmt);
		}
		return equipos;
	}
	
	

}
