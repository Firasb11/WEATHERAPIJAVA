package com.SAEJavaMeteo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MeteoJdbc {
		private static String id, password, url;
		private static Connection cn;
		private static Statement st;
		private static ResultSet rs;
		private static PreparedStatement pst;
		
		public  MeteoJdbc () {
		// l inisialisation des attribues de configuration pour ouvrire une nouvel session 
			MeteoJdbc.url = "jdbc:mysql://localhost/meteo";
			MeteoJdbc.id = "root";
			MeteoJdbc.password = "";
			cn = null;
			st = null;
			rs = null;
		}
		
	 /**
	  * cela est la methode main 
	  * @param args
	  */

	    /**
	     * cette Methode est pour l ajout des donnees dans la base 
	     * @param idM
	     * @param observation_time
	     * @param humidity
	     * @param air_quality
	     * @param temperature
	     * @param imageIcon
	     * connection a la base de donnees par DriverManager.getConnection
	     */
		// il faut Installez package de jdbc (jdbc driver )
	 
		// sauvgarder les donnes dans le tableau meteo SQL
		public void  insertClient(String humidity , String air_quality ,String temperature,String imageIcon,String wether, String city) {
			// Notice :Class name est genere automatiquement en les nouveau version de mysql-connector 
	/*try {
			Class.forName("com.mysql.jdbc.Driver");
			*/
			try {
				cn = DriverManager.getConnection(url, id, password);
		
			st = cn.createStatement();
			// requete pour sauvgarde les donnes de meteo
			
			String query = "INSERT  INTO meteo (humidity,air_quality,temperature,ImageIcon,wether,city) " + "VALUES (?,?,?,?,?,?)";
			pst = cn.prepareStatement(query);
			pst.setString(1,humidity);
			pst.setString(2,air_quality);
			pst.setString(3,temperature);
			pst.setString(4,imageIcon);
			pst.setString(5,wether);
			pst.setString(6,city);
			
			
			
			pst.executeUpdate();
			
			
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public List<String> selectClient() {
			List<String> donnees = new ArrayList<String>();
			//ouvrire une connexion
				// Notice :Class name est genere automatiquement en les nouveau version de mysql-connector 
	/*		try {
				Class.forName("com.mysql.jdbc.Driver");*/
					try {
						cn = DriverManager.getConnection(url, id, password);
				
					st = cn.createStatement();
					// query de contenu du table meteo
					String query = "SELECT * FROM meteo ORDER BY id DESC LIMIT 1,1 ";
					pst = cn.prepareStatement(query);
					rs = pst.executeQuery();
					  while (rs.next()) {	
				            // Retrieve par le nom de colonne 
						  	String humidity = rs.getString("humidity");
						  	donnees.add(humidity);
				            String air_quality = rs.getString("air_quality");
				            donnees.add(air_quality);
				           String temperature =  rs.getString("temperature");
				           donnees.add(temperature);
				            String imageIcon = rs.getString("imageIcon");
				            donnees.add(imageIcon);
				            String wether = rs.getString("wether");
				            donnees.add(wether);
				            String city = rs.getString("city");
				            donnees.add(city);
				            
				         }
			
	          } catch (Exception e) {
	         	e.printStackTrace();
	         }
					return donnees;
		}
}

