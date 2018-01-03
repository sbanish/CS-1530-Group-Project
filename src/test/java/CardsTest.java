import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.junit.Test;


public class CardsTest {

	@Test
	  public void cardTestPass(){
		ActionListener e = new ButtonListener();
		
	    Cards pass;
	    try{
	  		pass = new Cards(e);
	  		}
	  		catch(Exception x){
	  			pass = null;
	  		}
	    
	    String color = pass.getColor();
	    assertNotSame(color, "purple");
	  }

	  @Test
	  public void numSpacesTest(){
		  ActionListener e = new ButtonListener();
	    Cards pass;
	    try{
	  		pass = new Cards(e);
	  		}
	  		catch(Exception x){
	  			pass = null;
	  		}
	    assertNotSame(pass.getSpaces(), 3);
	  }
}



class ButtonListener implements ActionListener{
	// Every time the card is clicked it will change
	public void actionPerformed(ActionEvent e) {
		
	}
}