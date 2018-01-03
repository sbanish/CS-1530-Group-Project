import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.junit.Test;

public class specialCardTest {

  	@Test
  	public void skipCardTest() {
  		
  		Cards c;
  		
  		ActionListener e = new ButtonListener();
  		try{
  		c = new Cards(e);
  		}
  		catch(Exception x){
  			c = null;
  		}
  		Boolean output = false;
  		
  		for(int i = 0; i < 70; i++){
  			c.newCard(new JButton());
  			if(c.getSkip()){
  				output = true;
  			}
  		}
  		assertEquals(true, output);
  		
  		 
  	}
  	
 	@Test
  	public void lollipopTest() {
  		
  		Cards c;
  		
  		ActionListener e = new ButtonListener();
  		try{
  		c = new Cards(e);
  		}
  		catch(Exception x){
  			c = null;
  		}
  		Boolean output = false;
  		
  		for(int i = 0; i < 70; i++){
  			c.newCard(new JButton());
  			if(c.getLollipop()){
  				output = true;
  			}
  		}
  		assertEquals(true, output);
  		
  		 
  	}
 	
	@Test
  	public void cookieTest() {
  		
  		Cards c;
  		
  		ActionListener e = new ButtonListener();
  		try{
  		c = new Cards(e);
  		}
  		catch(Exception x){
  			c = null;
  		}
  		Boolean output = false;
  		
  		for(int i = 0; i < 70; i++){
  			c.newCard(new JButton());
  			if(c.getCookies()){
  				output = true;
  			}
  		}
  		assertEquals(true, output);
  		
  		 
  	}
	
	@Test
  	public void candybarTest() {
  		
  		Cards c;
  		
  		ActionListener e = new ButtonListener();
  		try{
  		c = new Cards(e);
  		}
  		catch(Exception x){
  			c = null;
  		}
  		Boolean output = false;
  		
  		for(int i = 0; i < 70; i++){
  			c.newCard(new JButton());
  			if(c.getCandybar()){
  				output = true;
  			}
  		}
  		assertEquals(true, output);
  		
  		 
  	}
	
	@Test
  	public void iceCreamTest() {
  		
  		Cards c;
  		
  		ActionListener e = new ButtonListener();
  		try{
  		c = new Cards(e);
  		}
  		catch(Exception x){
  			c = null;
  		}
  		Boolean output = false;
  		
  		for(int i = 0; i < 70; i++){
  			c.newCard(new JButton());
  			if(c.getIceCream()){
  				output = true;
  			}
  		}
  		assertEquals(true, output);
  		
  		 
  	}
	
	@Test
  	public void sodaTest() {
  		
  		Cards c;
  		
  		ActionListener e = new ButtonListener();
  		try{
  		c = new Cards(e);
  		}
  		catch(Exception x){
  			c = null;
  		}
  		Boolean output = false;
  		
  		for(int i = 0; i < 70; i++){
  			c.newCard(new JButton());
  			if(c.getSoda()){
  				output = true;
  			}
  		}
  		assertEquals(true, output);
  		
  		 
  	}

  	class ButtonListener implements ActionListener{
			// Every time the card is clicked it will change
			public void actionPerformed(ActionEvent e) {
				
			}
		}
  	

  }
