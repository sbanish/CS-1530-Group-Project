import static org.junit.Assert.*;

import org.junit.Test;

public class playerInputTest {

  	@Test
  	public void testLoginP1() {
  		String shawn = "shawn";
  		String nick = "nick";
  		String spencer = "spencer";
  		String chris = "chris";
  		Login test1 = new Login(shawn, nick, spencer, chris);
  		assertEquals("shawn", test1.getPlayer1Name());
  	}

  	@Test
  	public void testLoginP2() {
  		String shawn = "shawn";
  		String nick = "nick";
  		String spencer = "spencer";
  		String chris = "chris";
  		Login test2 = new Login(shawn, nick, spencer, chris);
  		assertEquals("nick", test2.getPlayer2Name());
  	}

  	@Test
  	public void testLoginP3() {
  		String shawn = "shawn";
  		String nick = "nick";
  		String spencer = "spencer";
  		String chris = "chris";
  		Login test3 = new Login(shawn, nick, spencer, chris);
  		assertEquals("spencer", test3.getPlayer3Name());
  	}

  	@Test
  	public void testLoginP4() {
  		String shawn = "shawn";
  		String nick = "nick";
  		String spencer = "spencer";
  		String chris = "chris";
  		Login test4 = new Login(shawn, nick, spencer, chris);
  		assertEquals("chris", test4.getPlayer4Name());
  	}
  }
