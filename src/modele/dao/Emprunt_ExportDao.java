package modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.entities.Emprunt_Export;
import modele.jdbc.Jdbc;

public class Emprunt_ExportDao {

	public static ArrayList<Emprunt_Export> selectEmpruntNotExported() throws SQLException {
		ArrayList<Emprunt_Export> lesEmprunts = new ArrayList<Emprunt_Export>();
		Emprunt_Export Emprunt_Export = null;
		ResultSet rs;
		PreparedStatement pstmt;
		Jdbc jdbc = Jdbc.getInstance();

		String requete = "select l.isbn, a.nom, a.prenom, exempl_em.numadh ,exempl_em.numinv from adherent a\n"
				+ "    inner join exemplaireemprunt exempl_em on exempl_em.numadh = a.numadh\n"
				+ "    inner join exemplaire exempl on exempl.numinv = exempl_em.numinv\n"
				+ "    inner join livre l on l.isbn = exempl.isbn\n" + "where exempl_em.flag = 0";

		pstmt = jdbc.getConnection().prepareStatement(requete);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Emprunt_Export = Emprunt_ExportDao.emprunt_ExportFromResultSet(rs);
			lesEmprunts.add(Emprunt_Export);
		}
		return lesEmprunts;
	}
	
	public static ArrayList<Emprunt_Export> selectEmpruntBeeingExported() throws SQLException {
		ArrayList<Emprunt_Export> lesEmprunts = new ArrayList<Emprunt_Export>();
		Emprunt_Export Emprunt_Export = null;
		ResultSet rs;
		PreparedStatement pstmt;
		Jdbc jdbc = Jdbc.getInstance();

		String requete = "select l.isbn, a.nom, a.prenom, exempl_em.numadh ,exempl_em.numinv from adherent a\n"
				+ "    inner join exemplaireemprunt exempl_em on exempl_em.numadh = a.numadh\n"
				+ "    inner join exemplaire exempl on exempl.numinv = exempl_em.numinv\n"
				+ "    inner join livre l on l.isbn = exempl.isbn\n" + "where exempl_em.flag = 1";

		pstmt = jdbc.getConnection().prepareStatement(requete);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Emprunt_Export = Emprunt_ExportDao.emprunt_ExportFromResultSet(rs);
			lesEmprunts.add(Emprunt_Export);
		}
		return lesEmprunts;
	}

	public static void updateEmpruntExporting(int numAdh, int numInv) throws SQLException {
		PreparedStatement pstmt;
		Jdbc jdbc = Jdbc.getInstance();
		String requete = "UPDATE exemplaireemprunt SET flag = 1 WHERE numadh = ? AND numinv = ?";
		pstmt = jdbc.getConnection().prepareStatement(requete);
		pstmt.setInt(1, numAdh);
		pstmt.setInt(2, numInv);
		pstmt.executeUpdate();
	}

	public static void updateEmpruntExportFailed(int numAdh, int numInv) throws SQLException {
		PreparedStatement pstmt;
		Jdbc jdbc = Jdbc.getInstance();
		String requete = "UPDATE exemplaireemprunt SET flag = 0 WHERE numadh = ? AND numinv = ?";
		pstmt = jdbc.getConnection().prepareStatement(requete);
		pstmt.setInt(1, numAdh);
		pstmt.setInt(2, numInv);
		pstmt.executeUpdate();
	}

	public static void updateEmpruntExported(int numAdh, int numInv) throws SQLException {
		PreparedStatement pstmt;
		Jdbc jdbc = Jdbc.getInstance();
		String requete = "UPDATE exemplaireemprunt SET flag = 2 WHERE numadh = ? AND numinv = ?";
		pstmt = jdbc.getConnection().prepareStatement(requete);
		pstmt.setInt(1, numAdh);
		pstmt.setInt(2, numInv);
		pstmt.executeUpdate();
	}

	private static Emprunt_Export emprunt_ExportFromResultSet(ResultSet rs) throws SQLException {
		Emprunt_Export clt = null;
		String nom = rs.getString("NOM");
		String prenom = rs.getString("PRENOM");
		int numAdh = rs.getInt("NUMADH");
		int isbn = rs.getInt("ISBN");
		int numInv = rs.getInt("NUMINV");

		clt = new Emprunt_Export(nom, prenom, numAdh, isbn, numInv);
		return clt;
	}

}
