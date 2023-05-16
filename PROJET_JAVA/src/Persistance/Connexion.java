package Persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Connexion {
	Connection cn = null;

	public static Connection ConnectDB() {
		try {
//			Class.forName("com.mysql.jdbc.Driver"); // pilote
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "");
			System.out.println("cnx khdaaama khay");
			return cn;
		} catch (SQLException e) {
			System.out.println("Connexion est Echouee");
			JOptionPane.showMessageDialog(null, "Connexion est echouee avec BD mySQL");
			return null;
		}
	}
}
