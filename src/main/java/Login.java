//package java;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class Login extends JPanel{

	JPanel login = new JPanel();
	String player1, player2 ,player3, player4;
	JLabel player1L, player2L, player3L, player4L;


	public Login(String player1n, String player2n, String player3n, String player4n){

		player1 = player1n;
		player2 = player2n;
		player3 = player3n;
		player4 = player4n;

		player1L = new JLabel(player1n);
		player2L = new JLabel(player2n);
		player3L = new JLabel(player3n);
		player4L = new JLabel(player4n);

		add(new JLabel("Current Player: " + player1));
		setVisible(true);
	}



	 public String getPlayer1Name(){
	 	return player1;
	 }

	 public String getPlayer2Name(){
	 	return player2;
	 }

	 public String getPlayer3Name(){
	 	return player3;
	 }

	 public String getPlayer4Name(){
	 	return player4;
	 }

	 protected String changePlayer(int playerNum){

		 if(playerNum == 1){
			 removeAll();
			 add(new JLabel(" Current Player : " + player1));
			 return player1;
		 }
		 if(playerNum == 2){
			 removeAll();
			 add(new JLabel(" Current Player : " + player2));
			 return player2;
		 }
		 if(playerNum == 3){
			 removeAll();
			 add(new JLabel(" Current Player : " + player3));
			 return player3;
		 }
		 if(playerNum == 4){
			 removeAll();
			 add(new JLabel(" Current Player : " + player4));
			 return player4;
		 }
		 return null;
	 }


}
