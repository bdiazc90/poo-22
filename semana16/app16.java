package semana16;

import java.util.*;

public class app16 {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		/*
		 * db: poo_mundial
		 * tablas: equipo (id, pais, puntaje, grupo) 
		 * JAVA: Menú
		 * 		- 1. agregar equipo
		 * 		- 2. mostrar equipos
		 * 		- 3. Generar Reporte Equipos
		 * 		- 4. Generar Reporte Equipos ordenados
		 * 		- 5. Generar Reporte Equipos por param. Grupo
		 * 				Ingresa el grupo para generar:
		 * 
		 */
		menu();
	}
	
	private static void menu() {
		System.out.println("Bienvenido al mundial-reports:");
		boolean menu = true;
		while(menu) {
			System.out.println("-- MENU PRINCIPAL --");
			System.out.println("1. Agregar nuevo equipo.");
			System.out.println("2. Mostrar equipos.");
			System.out.println("3. Generar REPORTE equipos.");
			System.out.println("4. Generar REPORTE equipos ORDENADOS.");
			System.out.println("5. Generar REPORTE equipos por Grupo.");
			System.out.println("[X = Salir]");
			System.out.println(" --> Ingrese una opción:");
			String opcion = sc.nextLine();
			if (opcion.equals("x") || opcion.equals("X")) {
				System.out.println("Fin...");
				menu = false;
				break;
			}
			switch (opcion) {
			case "1":
				EquipoDAO.agregarEquipo();
				break;
			case "2":
				EquipoDAO.mostrarEquipos();
				break;
			case "3":
				EquipoDAO.mostrarReporte();
				break;
			default:
				System.out.println("Opción inválida.");
				break;
			}
		}
	}

}
