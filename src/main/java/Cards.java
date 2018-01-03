import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Cards  extends JPanel{
//The initial deck has 10 singles and 2 doubles of each color of card (red, yellow, blue, green, orange).
//60 total card

	JLabel mid, skip, cookies, candybar, lollipop, soda, iceCream;
	Card[] c = new Card[70];
	int cardIndex = 0;
	JButton _button;

	private class Card{
		String color;
		int numSpaces;
		Boolean skip = false;
		Boolean isCandybar = false;
		Boolean isCookies = false;
		Boolean isIceCream = false;
		Boolean isLollipop = false;
		Boolean isSoda = false;

		//Boolean middle = false;
	}

	public Cards(ActionListener e) throws Exception{
		createCards();
		shuffle();

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(250, 250));
		_button = new JButton("Start");
		//ActionListener buttonListener = new ButtonListener();
		_button.setPreferredSize(new Dimension(200,200));
	    _button.addActionListener(e);
		_button.setFont(new Font("Arial", Font.BOLD, 32));
		_button.setOpaque(true);
		_button.setBorderPainted(false);
		add(_button);

        BufferedImage skipCard = ImageIO.read(new File("skipCard.png"));
        skip = new JLabel(new ImageIcon(skipCard));

        BufferedImage iceCreamCard = ImageIO.read(new File("icecream.png"));
        iceCream = new JLabel(new ImageIcon(iceCreamCard));

        BufferedImage lollipopCard = ImageIO.read(new File("lollipop.png"));
        lollipop = new JLabel(new ImageIcon(lollipopCard));

        BufferedImage candybarCard = ImageIO.read(new File("candybar.png"));
        candybar = new JLabel(new ImageIcon(candybarCard));

        BufferedImage sodaCard = ImageIO.read(new File("soda.png"));
        soda = new JLabel(new ImageIcon(sodaCard));

        BufferedImage cookieCard = ImageIO.read(new File("cookies.png"));
        cookies = new JLabel(new ImageIcon(cookieCard));

	}


	public void createCards(){
		for(int i = 0; i < c.length; i++){
			c[i] = new Card();
		}

		for(int i = 0; i < 10; i++){
			c[i].color = "red";
			c[i].numSpaces = 1;
			c[i+10].color = "yellow";
			c[i+10].numSpaces = 1;
			c[i+20].color = "blue";
			c[i+20].numSpaces = 1;
			c[i+30].color = "green";
			c[i+30].numSpaces = 1;
			c[i+40].color = "orange";
			c[i+40].numSpaces = 1;
		}
		for(int i = 0; i < 2; i++){
			c[i+50].color = "red";
			c[i+50].numSpaces = 2;
			c[i+52].color = "yellow";
			c[i+52].numSpaces = 2;
			c[i+54].color = "blue";
			c[i+54].numSpaces = 2;
			c[i+56].color = "green";
			c[i+56].numSpaces = 2;
			c[i+58].color = "orange";
			c[i+58].numSpaces = 2;
		}
		for(int i = 0; i < 5; i++){
			c[60+i].color = "black";
			c[60+i].numSpaces = 0;
			c[60+i].skip = true;

		}
		for(int i = 0; i < 5; i++){
			c[65+i].color = "black";
			c[65+i].numSpaces = 0;

			//c[65+i].middle = true;
		}
		c[65].color = "pink";
		c[65].numSpaces = 0;
		c[65].isCandybar = true;

		c[66].color = "pink";
		c[66].numSpaces = 0;
		c[66].isCookies = true;

		c[67].color = "pink";
		c[67].numSpaces = 0;
		c[67].isIceCream = true;

		c[68].color = "pink";
		c[68].numSpaces = 0;
		c[68].isLollipop = true;

		c[69].color = "pink";
		c[69].numSpaces = 0;
		c[69].isSoda = true;

	}
	private void shuffle(){
	    int index;
	    Card temp;
	    Random random = new Random();
	    for (int i = c.length - 1; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        temp = c[index];
	        c[index] = c[i];
	        c[i] = temp;
	    }
	}

	public Card getCard(){
		if(cardIndex == 67){
			Card tmp = c[cardIndex];
			shuffle();
			cardIndex = 0;
			return tmp;
		}
		else{
			cardIndex++;
			return c[cardIndex];
		}
	}
	


	 public void newCard(JButton source){
		 source.removeAll();
		 Card next = getCard();
		 	if(next.skip){
		 		source.setText("");
		 		source.add(skip);
		 	}
		 	if(next.isCandybar){
		 		source.setText("");
		 		source.add(candybar);
		 	}
		 	if(next.isCookies){
		 		source.setText("");
		 		source.add(cookies);
		 	}
		 	if(next.isIceCream){
		 		source.setText("");
		 		source.add(iceCream);
		 	}
		 	if(next.isLollipop){
		 		source.setText("");
		 		source.add(lollipop);
		 	}
		 	if(next.isSoda){
		 		source.setText("");
		 		source.add(soda);
		 	}
		 	else{
		 		source.setText(next.color.toUpperCase() + "\n" + next.numSpaces);
		 	}

			if(next.color == "red"){
				source.setBackground(Color.RED);
			}
			else if(next.color == "orange"){
				source.setBackground(Color.ORANGE);
			}
			else if(next.color == "yellow"){
				source.setBackground(Color.YELLOW);
			}
			else if(next.color == "green"){
				source.setBackground(Color.GREEN);
			}
			else if(next.color == "blue"){
				source.setBackground(Color.BLUE);
			}
			else{
				source.setBackground(Color.WHITE);
			}

		}

	 	 //Testing Purposes
	 public String getColor(){
	 	return c[cardIndex].color;
	 }

	 //Testing Purposes
	 public int getSpaces(){
	 	return c[cardIndex].numSpaces;

	 }
	 public Boolean getSkip(){
		 	return c[cardIndex].skip;

	 }
//	 public Boolean getMiddle(){
//		 	return c[cardIndex].middle;
//
//	 }
	 public Boolean getCandybar(){
		 return c[cardIndex].isCandybar;
	 }
	 public Boolean getCookies(){
		 return c[cardIndex].isCookies;
		 }
	 public Boolean getIceCream(){
		 return c[cardIndex].isIceCream;
	 }
	 public Boolean getLollipop(){
		 return c[cardIndex].isLollipop;
	 }
	 public Boolean getSoda(){
		return c[cardIndex].isSoda;
	 }
}
