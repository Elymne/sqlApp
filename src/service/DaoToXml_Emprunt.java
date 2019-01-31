package service;

import java.sql.SQLException;
import modele.dao.Emprunt_ExportDao;
import modele.entities.*;

public class DaoToXml_Emprunt {

	public static String ObjectEmpruntToXml() throws SQLException {
		String result = null;
		if (!Emprunt_ExportDao.selectEmpruntNotExported().isEmpty()) {
			result = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" + 
					"<tracking location=\"Nantes\">";
			for(Emprunt_Export unEmprunt : Emprunt_ExportDao.selectEmpruntNotExported()) {
				result += "<entry customer=\"";
				result += unEmprunt.getNom() + " " + unEmprunt.getPrenom();
				result += "\" isbn=\"";
				result += unEmprunt.getIsbn();
				result += "\"/>";
			}
			result += "</tracking>";
		}
		return result;
	}

}
