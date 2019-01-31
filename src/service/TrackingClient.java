package service;
import java.io.*;
import java.net.*;
import java.sql.SQLException;

import modele.dao.Emprunt_ExportDao;
import modele.entities.Emprunt_Export;
import modele.jdbc.Jdbc;
import test.EmpruntDaoTest;

public class TrackingClient {

	private String host;
	private int port;

	public TrackingClient( String host, int port ) {
		this.host = host;
		this.port = port;
	}


	public boolean sendData( String data ) {
		try {
			Socket s = new Socket(this.host, this.port);

			/* 1. send data */
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			out.write(data);
			out.newLine();
			out.flush();
			s.shutdownOutput();

			/* 2. read response */
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String r = in.readLine();
			System.out.println("first line received: " + r);

			s.close();

			return "OK.".equals(r);

		} catch ( IOException e ) {
			e.printStackTrace();
			return false;
		}
	}

	public void run() throws SQLException {
		Jdbc.creer("i10a07a", "i10a07a");
		Jdbc.getInstance().connection();
		
		String data = this.makeData();
		boolean success = this.sendData(data);
		if ( success ) {
			this.validateData();
		} else {
			this.invalidateData();
		}
		
		Jdbc.getInstance().deconnection();
	}

	static public void main( String[] args ) throws IOException, SQLException {
		String host = "localhost";
		int port = 9876;
		if ( args.length >= 1 ) {
			host = args[1];
			if ( args.length >= 2 ) {
				port = Integer.valueOf(args[1]);
			}
		}
		TrackingClient tc = new TrackingClient(host, port);
		tc.run();
	}

	public void validateData() throws SQLException {
		System.out.println("submission ok.");
		for(Emprunt_Export unEmprunt : Emprunt_ExportDao.selectEmpruntBeeingExported()) {
			Emprunt_ExportDao.updateEmpruntExported(unEmprunt.getNumAdh(), unEmprunt.getNumInv());
		}
	}
	public void invalidateData() throws SQLException {
		System.out.println("submission ko.");
		for(Emprunt_Export unEmprunt : Emprunt_ExportDao.selectEmpruntBeeingExported()) {
			Emprunt_ExportDao.updateEmpruntExportFailed(unEmprunt.getNumAdh(), unEmprunt.getNumInv());
		}
	}

	public String makeData() throws SQLException {
		String data = "";
		data = DaoToXml_Emprunt.ObjectEmpruntToXml();
		for(Emprunt_Export unEmprunt : Emprunt_ExportDao.selectEmpruntNotExported()) {
			Emprunt_ExportDao.updateEmpruntExporting(unEmprunt.getNumAdh(), unEmprunt.getNumInv());
		}
		return data;
	}


}
