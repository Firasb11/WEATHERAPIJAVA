package com.SAEJavaMeteo;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.util.Date;
public class Clock  {
	private static JLabel horloge,date;
	
    public static JLabel getDate() {
		return date;
	}
	public static void setDate(JLabel date) {
		Clock.date = date;
	}
	public Clock() {
    	  setHorloge(new JLabel());
          getHorloge().setBounds(730, 185, 170, 35);
          getHorloge().setForeground(Color.WHITE);
          getHorloge().setFont(
            UIManager.getFont("Label.font").deriveFont(Font.BOLD, 40f)
          );
          getHorloge().setText(
            DateFormat.getTimeInstance().format(new Date())
          );
          
          Timer t = new Timer(500, new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
               getHorloge().setText(
                  DateFormat.getTimeInstance().format(new Date())
               );
             }
          });
          t.setRepeats(true);
          t.setCoalesce(true);
          t.setInitialDelay(0);
          t.start();
      //////////////////////////date /////////////////
    	  setDate(new JLabel());
    	  getDate().setBounds(50, 125, 250, 50);
    	  getDate().setForeground(Color.WHITE);
    	  getDate().setFont(
            UIManager.getFont("Label.font").deriveFont(Font.BOLD, 40f)
          );
    	  getDate().setText(
            DateFormat.getDateInstance().format(new Date())
          );
          
          Timer t1 = new Timer(500, new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
               getDate().setText(
                  DateFormat.getDateInstance().format(new Date())
               );
             }
          });
          t1.setRepeats(true);
          t1.setCoalesce(true);
          t1.setInitialDelay(0);
          t1.start();
      
    }
	public static JLabel getHorloge() {
		return horloge;
	}
	public static void setHorloge(JLabel horloge) {
		Clock.horloge = horloge;
	}
   
    
       
       
       
       
    }


