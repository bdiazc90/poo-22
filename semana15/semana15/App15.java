package semana15;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class App15 {

	public static void main(String[] args) {
		String inputFile = "/Users/brunodiazcabanillas/workspace/eclipse/semana15/ReporteCollection.jrxml"; 
		String outputFile = "/Users/brunodiazcabanillas/workspace/eclipse/semana15/Ejemplo1.pdf";
		
		List<AlumnoVO> alumnos = new ArrayList<AlumnoVO>();
		AlumnoVO a1 = new AlumnoVO(213, "bruno", "diaz");
		AlumnoVO a2 = new AlumnoVO(214, "jaime", "farfan");
		AlumnoVO a3 = new AlumnoVO(215, "laura", "melendez");
		AlumnoVO a4 = new AlumnoVO(216, "linder", "hassinger");
		AlumnoVO a5 = new AlumnoVO(217, "david", "balboa");
		AlumnoVO a6 = new AlumnoVO(218, "nicole", "arguedas");
		
		
		alumnos.add(a1);
		alumnos.add(a2);
		alumnos.add(a3);
		alumnos.add(a4);
		alumnos.add(a5);
		alumnos.add(a6);
		
		
		// Convertir la lista de alumnos a un objeto tipo JRBeanCollectionDataSource:
		JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(alumnos);
		
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
