package com.SAEJavaMeteo;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


/** 
 * 
 * @author Firas
 * @param args
 * Creation de la class de Main Window
 * l'implementation de l'interface ActionListener pour l'interaction avec les buttons 
 * @note L'interface ActionListener comporte des methodes abstraites (Abstract) doivent �tre implementer 
 * 
 */

public class MainWindow implements ActionListener, FocusListener {
	private JFrame MainView;
	private JLabel warningText;
	private JLabel imageLabel,inputLabel,clockLabel,tempText,humidityText,cielText,pollutionAverageText,tempLabel,humidityLabel,cielLabel,pollutionLabel;
	private Image meteoImage,ClockImage,temperatureImage,humidityImage,cielImage,pollutionImage;
	private ImageIcon meteoImageIcon,clockImageIcon,temperatureImageIcon,humidityImageIcon,cielImageIcon,pollutionImageIcon;
	private JTextField inputText;
	private JPanel currentM;
	private JButton b1,b2,b3;
	private URL imageUrl;
	
	////////////////COLORS Palette //////////////////////////
	public static final Color BLUE_CLAIR = new Color(31, 190, 254);
	public static final Color BLUE_CLAIR1 = new Color(31, 170, 254);
	public static final Color BLUE_PEU_SOMBRE = new Color(31, 150, 254);

	
	
	
	public MainWindow() {
		/**
		 * Creation d'un Constructeur  qui va etre Notre <b>OnCreate</b> Methodes Lors D'instanciation De Class  <bold>MainWindow</bold>
		 * Instanciation de Frame <b>MainView</b> 
		 * EN JFrame Pour modifier le comportement par défaut Lors de la fermeture de fenetre , vous appelez la méthode <b>setDefaultCloseOperation</b>
		 * @see {@link #https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html}
		 * @note lLe Dossier assets est sous le dossier de Project !!!
		 */
		
		MainView = new JFrame("Météo");
		MainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainView.setBounds(450, 190, 1014, 597);
		MainView.setResizable(false);
		MainView.getContentPane();
		MainView.setLayout(null);
		MainView.getContentPane().setBackground( BLUE_CLAIR );
		// d�finir Image Icon 
		MainView.setIconImage(new ImageIcon("assets/ImageIcon.png").getImage());
		// Manipulation d'image
		meteoImage = new ImageIcon("assets/imageMeteo.png").getImage();
		meteoImage = meteoImage.getScaledInstance(300, 230, java.awt.Image.SCALE_SMOOTH);
		meteoImageIcon = new ImageIcon(meteoImage);
		imageLabel = new JLabel(meteoImageIcon);
		imageLabel.setBounds(320, -23, 300, 230);
		MainView.add(imageLabel);
		//////////////Input Field (pour La ville )
		inputLabel = new JLabel("Ville");
		inputLabel.setForeground(Color.WHITE);
		
		inputLabel.setFont(new Font("Times New Roman", Font.PLAIN, 28));
		inputLabel.setBounds(455, 125, 173, 93);
		MainView.add(inputLabel);
		inputText = new RoundJTextField(45);
		inputText.setBorder(null);
		inputText.setFont(new Font("Tahoma", Font.PLAIN, 17));
		inputText.setBounds(400, 195, 181, 26);
		inputText.setForeground(Color.WHITE);
		inputText.setBackground(BLUE_CLAIR1);
		inputText.addFocusListener(this);
		MainView.add(inputText);
		//////////////////////////////
		warningText = new JLabel("");
		warningText.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		warningText.setForeground(Color.RED);
		warningText.setBounds(412, 217, 181, 26);
		MainView.add(warningText);
		
		///////// Meteo Courante Button//////////////////////::::
		b1 = new JButton("Météo Courante ");
		b1.setFocusPainted(false);
		b1.setForeground(Color.WHITE);
		b1.setBorder(null);
		b1.setFont(new Font("Arial", Font.PLAIN, 25));
		b1.setBackground(BLUE_CLAIR1);
		b1.setBounds(145, 250, 200, 30);
		b1.addActionListener(this);
		b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b1.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				b1.setBackground(BLUE_PEU_SOMBRE);
			}

			public void mouseExited(MouseEvent evt) {
				b1.setBackground(BLUE_CLAIR1);
			}

		});
		MainView.add(b1);

		
		/////////////////////////////// setting JPanel Data/////////
//////////////////////temperature Image///////////:::
		 currentM = new JPanel();
		currentM.setLayout(null);
		currentM.setBackground(BLUE_PEU_SOMBRE);
		currentM.setBounds(50, 300, 900, 250);
		tempText = new JLabel();
		tempText.setBounds(170, 40, 150, 90);
		tempText.setFont(new Font("Times New Roman", Font.BOLD, 55));
		tempText.setForeground(Color.WHITE);
		currentM.add(tempText);
		//////////////////////temperature Image///////////:::
		temperatureImage = new ImageIcon("assets/temperature.png").getImage();
		temperatureImage = temperatureImage.getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH);
		temperatureImageIcon = new ImageIcon(temperatureImage);
		tempLabel = new JLabel(temperatureImageIcon);
		tempLabel.setBounds(20, 20, 200, 150);
		
		currentM.add(tempLabel);
		
		////////////set Humidity/////////////

		humidityText = new JLabel();
		humidityText.setBounds(430, 40, 150, 90);
		humidityText.setFont(new Font("Times New Roman", Font.BOLD, 40));
		humidityText.setForeground(Color.WHITE);
		currentM.add(humidityText);
//////////////////////Humidity Image///////////:::
		humidityImage = new ImageIcon("assets/humidite.png").getImage();
		humidityImage = humidityImage.getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH);
		humidityImageIcon = new ImageIcon(humidityImage);
		humidityLabel = new JLabel(humidityImageIcon);
		humidityLabel.setBounds(250, 20, 200, 150);

		currentM.add(humidityLabel);
///////////set Humidity/////////////

		cielText = new JLabel();
		cielText.setBounds(300, 150, 250, 90);
		cielText.setFont(new Font("Times New Roman", Font.BOLD, 40));
		cielText.setForeground(Color.WHITE);
		currentM.add(cielText);
//////////////////////Humidity Image///////////:::
		
		// inisialisation de label de l image 
		cielLabel = new JLabel();
		cielLabel.setBounds(200, 140, 150, 100);

		
///////////set pollution/////////////

		pollutionAverageText = new JLabel();
		pollutionAverageText.setBounds(735, 40, 180, 90);
		pollutionAverageText.setFont(new Font("Times New Roman", Font.BOLD, 40));
		pollutionAverageText.setForeground(Color.WHITE);
		currentM.add(pollutionAverageText);
//////////////////////Humidity Image///////////:::
		pollutionImage = new ImageIcon("assets/pollution.png").getImage();
		pollutionImage = pollutionImage.getScaledInstance(150, 100, java.awt.Image.SCALE_SMOOTH);
		pollutionImageIcon = new ImageIcon(pollutionImage);
		pollutionLabel = new JLabel(pollutionImageIcon);
		pollutionLabel.setBounds(565, 15, 200, 150);

		currentM.add(pollutionLabel);
			
		
		
		
		
		
		MainView.add(currentM);
		
		currentM.setVisible(false);
///////////////////////////////////////////////////////////////////////:::::
		///////// Meteo Précédente Button//////////////////////::::
		b2 = new JButton("Météo Précédente ");
		b2.setFocusPainted(false);
		b2.setForeground(Color.WHITE);
		b2.setBorder(null);
		b2.setFont(new Font("Arial", Font.PLAIN, 25));
		b2.setBackground(BLUE_CLAIR1);
		b2.setBounds(365, 250, 240, 30);
		b2.addActionListener(this);
		b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b2.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				b2.setBackground(BLUE_PEU_SOMBRE);
			}

			public void mouseExited(MouseEvent evt) {
				b2.setBackground(BLUE_CLAIR1);
			}

		});
		MainView.add(b2);

		
		///////// Meteo Dans une Heure Button//
	
		b3 = new JButton("Météo dans une Heure ");
		b3.setFocusPainted(false);
		b3.setForeground(Color.WHITE);
		b3.setBorder(null);
		b3.setFont(new Font("Arial", Font.PLAIN, 25));
		b3.setBackground(BLUE_CLAIR1);
		b3.setBounds(625, 250, 270, 30);
		b3.addActionListener(this);
		b3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b3.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				b3.setBackground(BLUE_PEU_SOMBRE);
			}

			public void mouseExited(MouseEvent evt) {
				b3.setBackground(BLUE_CLAIR1);
			}

		});
		MainView.add(b3);
		//////////////////////////////////////// add Time and Date Now////////////////
		Clock clock = new Clock();
		MainView.add(clock.getDate());
		MainView.add(clock.getHorloge());
		ClockImage = new ImageIcon("assets/clock.png").getImage();
		ClockImage = ClockImage.getScaledInstance(220, 200, java.awt.Image.SCALE_SMOOTH);
		clockImageIcon = new ImageIcon(ClockImage);
		clockLabel = new JLabel(clockImageIcon);
		clockLabel.setBounds(690, 0, 230, 200);
		MainView.add(clockLabel);
		
	
		
		////////////////////////////////////////
		/////////////////////////////////
		
		MainView.setVisible(true);


		
	}
	   

public static void main(String args[]) {
		
		MainWindow Main = new MainWindow();
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// Pour le Button de Meteo Courant
		if (e.getSource() == b1) {
			if (inputText.getText().isEmpty()) {
				warningText.setText("veuillez entrer une ville !!");
		}
			else {
			
				ClientMeteoApi apiClient = new ClientMeteoApi();
				// Please offer the Ville text Field 
				JsonObject donnees =apiClient.getData(inputText.getText());
				String wether = donnees.get("wether").getAsString();
				String temperature = donnees.get("temperature").getAsString();
				String humidité = donnees.get("humidité").getAsString();
				String weatherIconUrl = donnees.get("weatherIconUrl").getAsString();
				String air_Q = donnees.get("qualitéAir").getAsString();
				
		/////////////////////////manipulation /////////
			        try {
			            // Get the image URL from your API 
			            imageUrl = new URL(weatherIconUrl);
			            // Read the image using ImageIO (make sure u use Image Icon )
			            meteoImage = ImageIO.read(imageUrl);
			            meteoImage = meteoImage.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
			            meteoImageIcon = new ImageIcon(meteoImage);
			          
			            
			            // Do something with the image (e.g. display it in a JLabel)
			            cielLabel.setIcon(meteoImageIcon);
			            
			            //Add label to the container
			            currentM.add(cielLabel);
			            // here repaint and revalidate to refresh the frame so that he can add the Image 
			            MainView.revalidate();
			            MainView.repaint();
			        } catch (IOException ex) {
			            ex.printStackTrace();
			        }
			        tempText.setText(temperature);
			        humidityText.setText(humidité);
			        cielText.setText(wether);
			        pollutionAverageText.setText(air_Q);
			        currentM.setVisible(true);
			     	String wether_jdbc = cielText.getText();
					String temperature_jdbc = tempText.getText();
					String humidité_jdbc = humidityText.getText();
					String weatherIconUrl_jdbc = imageUrl.toString();
					String air_Q_jdbc = pollutionAverageText.getText();
					String city = inputText.getText();
					MeteoJdbc meteojdbc = new  MeteoJdbc();
					meteojdbc.insertClient(humidité_jdbc, air_Q_jdbc, temperature_jdbc, weatherIconUrl_jdbc,wether_jdbc,city);
				
			}
		}
		else if(e.getSource()==b3) {
			if (inputText.getText().isEmpty()) {
				warningText.setText("veuillez entrer une ville !!");
		}
			else {
			
				ClientMeteoApi apiClient = new ClientMeteoApi();
				// Please offer the Ville text Field 
				JsonObject donnees =apiClient.getData(inputText.getText());
				String wether = donnees.get("wether_h").getAsString();
				String temperature = donnees.get("temperature_h").getAsString();
				String humidité = donnees.get("humidité_h").getAsString();
				String weatherIconUrl = donnees.get("weatherIconUrl_h").getAsString();
				String air_Q = donnees.get("qualitéAir_h").getAsString();
				
		/////////////////////////manipulation /////////
			        try {
			            // Get the image URL from your API 
			            URL imageUrl = new URL(weatherIconUrl);
			            // Read the image using ImageIO (make sure u use Image Icon )
			            meteoImage = ImageIO.read(imageUrl);
			            meteoImage = meteoImage.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
			            meteoImageIcon = new ImageIcon(meteoImage);
			           
			           
			          
			            
			            // Do something with the image (e.g. display it in a JLabel)
			            cielLabel.setIcon(meteoImageIcon);
			           
			            //Add label to the container
			            currentM.add(cielLabel);
			          
			        } catch (IOException ex) {
			            ex.printStackTrace();
			        }
			        tempText.setText(temperature);
			        humidityText.setText(humidité);
			        cielText.setText(wether);
			        pollutionAverageText.setText(air_Q);
					


			        currentM.setVisible(true);
			       
			   
		           
			}
			
		}
		else if (e.getSource() == b2) {
			
			if (inputText.getText().isEmpty()) {
				warningText.setText("veuillez entrer une ville !!");
		}
			else {
					
				
				MeteoJdbc meteojdbc = new  MeteoJdbc();
				
					List<String> arrayList = meteojdbc.selectClient();
					if (arrayList.size()  != 0) {
					String humidityTextP     = arrayList.get(0);
					String air_qualityP     = arrayList.get(1);
					String temperatureP     = arrayList.get(2);
					String imageIconP       = arrayList.get(3);
					String wetherP          = arrayList.get(4);
					String cityP            = arrayList.get(5);
                	tempText.setText(temperatureP);
                	humidityText.setText(humidityTextP);
                	cielText.setText(wetherP);
                	pollutionAverageText.setText(air_qualityP);
                	cielLabel.setIcon(new ImageIcon(imageIconP));
                	inputText.setText(cityP);
                	  URL imageUrl;
					try {
						imageUrl = new URL(imageIconP);
						meteoImage = ImageIO.read(imageUrl);
			            meteoImage = meteoImage.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
			            meteoImageIcon = new ImageIcon(meteoImage);
			            cielLabel.setIcon(meteoImageIcon);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			            // Read the image using ImageIO (make sure u use Image Icon )
			            
                	 currentM.add(cielLabel);
					 
					 
					}
					
					
	
			
					
				 }
		}
	}
	
	public void Round(int x, int y, JFrame label) {
		label.setShape(new RoundRectangle2D.Double(x, y, 100, 100, 50, 50));
	}


	@Override
	public void focusGained(FocusEvent e) {
		// pour le message de warning de Input
		
		warningText.setText(null);
	}


	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}



		
		


	
}

