import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitTokensTest {

	@Test
	  public void nameTestPass(){
	    Tokens nameTokenPass = new Tokens();
	    nameTokenPass.setTokenName("Player1", 1);
	    assertEquals("Player1", nameTokenPass.getTokenName(1));
	  }

	@Test
	  public void nameTestFail(){
	    Tokens nameTokenFail = new Tokens();
	    nameTokenFail.setTokenName("Player2", 2);
	    assertNotSame("Player1", nameTokenFail.getTokenName(2));
	  }

	@Test
	  public void colorTestPass(){
	    Tokens colorTokenPass = new Tokens();
	    colorTokenPass.setTokenColor("blue", 3);
	    assertEquals("blue", colorTokenPass.getTokenColor(3));
	  }

	@Test
	  public void colorTestFail(){
	    Tokens colorTokenFail = new Tokens();
	    colorTokenFail.setTokenName("green", 4);
	    assertNotSame("green", colorTokenFail.getTokenColor(4));
	  }

}
