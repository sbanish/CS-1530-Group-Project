import static org.junit.Assert.*;

import org.junit.Test;

public class MultiInputTest {

//***************Player 1*************************************
@Test
public void testsetPlayer1(){
	System.out.println("setPlayer1_Test1\n");
	String player = "Spencer";
	MultiInput instance = new MultiInput();
	instance.setPlayer1Name(player);
	assertEquals(instance.getPlayer1Name(), player);
}	

@Test
public void testGetPlayer1(){
	System.out.println("getPlayer1_Test1\n");
	MultiInput instance = new MultiInput();
	String player = "Spencer";
	instance.setPlayer1Name("Spencer");
	String result = instance.getPlayer1Name();
	assertEquals(player, result);
}

//**************PLAYER 2*************************************
@Test
public void testsetPlayer2(){
	System.out.println("setPlayer2_Test1\n");
	String player = "Shawn";
	MultiInput instance = new MultiInput();
	instance.setPlayer2Name(player);
	assertEquals(instance.getPlayer2Name(), player);
}	

@Test
public void testGetPlayer2(){
	System.out.println("getPlayer2_Test1\n");
	MultiInput instance = new MultiInput();
	String player = "Spencer";
	instance.setPlayer2Name("Spencer");
	String result = instance.getPlayer2Name();
	assertEquals(player, result);
}

//***************Player 3*************************************
@Test
public void testsetPlayer3(){
	System.out.println("setPlayer3_Test1\n");
	String player = "Spencer";
	MultiInput instance = new MultiInput();
	instance.setPlayer3Name(player);
	assertEquals(instance.getPlayer3Name(), player);
}	

@Test
public void testGetPlayer3(){
	System.out.println("getPlayer3_Test1\n");
	MultiInput instance = new MultiInput();
	String player = "Spencer";
	instance.setPlayer3Name("Spencer");
	String result = instance.getPlayer3Name();
	assertEquals(player, result);
}

//***************Player 4*************************************
@Test
public void testsetPlayer4(){
	System.out.println("setPlayer4_Test1\n");
	String player = "Spencer";
	MultiInput instance = new MultiInput();
	instance.setPlayer4Name(player);
	assertEquals(instance.getPlayer4Name(), player);
}	

@Test
public void testGetPlayer4(){
	System.out.println("getPlayer4_Test1\n");
	MultiInput instance = new MultiInput();
	String player = "Spencer";
	instance.setPlayer4Name("Spencer");
	String result = instance.getPlayer4Name();
	assertEquals(player, result);
}

}//end of the test file for getter and setter methods

