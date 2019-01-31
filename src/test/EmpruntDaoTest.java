package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.dao.Emprunt_ExportDao;
import modele.entities.*;


import modele.jdbc.Jdbc;
import service.DaoToXml_Emprunt;

public class EmpruntDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		// Test de connexion
		EmpruntDaoTest.test0_Connexion();
		
		//Test Selection de la liste de tout les emprunts non exportés
		EmpruntDaoTest.test1_SelectAll();
		
		//Test phase de transition pendant l'exportation
		//EmpruntDaoTest.test2_UpdateExporting();
		
		//Test Génération d'un String au format xml
		//EmpruntDaoTest.test3_printObjectToXml();
		
		//Test Mettre tous les attributs flag à 2
		//test4_setAllToExported();
		
		
		
		Jdbc.getInstance().deconnection();

	}

	public static void test0_Connexion() throws ClassNotFoundException, SQLException {
		Jdbc.creer("i10a07a", "i10a07a");
		Jdbc.getInstance().connection();
		Connection cnx = Jdbc.getInstance().getConnection();
	}
	
	public static void test1_SelectAll() throws SQLException {
		List<Emprunt_Export> listeEmprunts = new ArrayList<>();
		listeEmprunts = Emprunt_ExportDao.selectEmpruntNotExported();
		for(Emprunt_Export unEmprunt : listeEmprunts) {
			System.out.println(unEmprunt.toString());
		}
	}
	
	public static void test2_UpdateExporting() throws SQLException {
		Emprunt_ExportDao.updateEmpruntExporting(1, 1);
	}
	
	public static void test3_printObjectToXml() throws SQLException {
		System.out.println(DaoToXml_Emprunt.ObjectEmpruntToXml());
		
	}
	
	public static void test4_setAllToExported() throws SQLException {
		for(Emprunt_Export unEmprunt : Emprunt_ExportDao.selectEmpruntNotExported()) {
			Emprunt_ExportDao.updateEmpruntExported(unEmprunt.getNumAdh(), unEmprunt.getNumInv());
		}
	}

}
