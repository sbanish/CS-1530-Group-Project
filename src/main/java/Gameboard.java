import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.*;
import java.io.*;

public class Gameboard extends JPanel implements Serializable{

	private int spaceSize = 75;
	private Space redLoc;
	private Space blueLoc;
	private Space greenLoc;
	private Space yellowLoc;

	private int redLocIx;
	private int blueLocIx;
	private int greenLocIx;
	private int yellowLocIx;

	//private Space middle;
	private Space start;
	private Space end;
	private JLabel redImg;
	private JLabel grnImg;
	private JLabel blueImg;
	private JLabel yellowImg;
	private JLabel allTokens;

	private Space candybarSquare;
	private Space cookiesSquare;
	private Space lollipopSquare;
	private Space iceCreamSquare;
	private Space sodaSquare;

	private BufferedImage image;

    class Space implements Serializable{
        JPanel space;
        Space next;
        Space previous;
        String spaceColor;
        //Boolean middleSpace = false;
    }


    //int i = 0; test to make sure spaces are layed out in proper order

    LinkedList<Space> path = new LinkedList<>();


    public Gameboard() throws Exception{
        char[][] board = readPath();

        //TODO:  make the spaces in rows 3 and 7 in reverse order, but add them in order
        Space[] tmp = new Space[10];
        setLayout(new GridLayout(10, 10, 1, 1));

        for(int i = 0; i < 10; i++){
        	if(i == 2 | i == 6){
        		for (int j = 9; j >= 0; j--) {
	                if(board[i][j] == 'X'){
	                	//add(createNullSpace().space);
	                	Space e = createNullSpace();
	                	path.add(e);
	                	tmp[j] = e;
	                }
	                else{
	                	Space e = createPathSpace(board[i][j]);
	                	path.add(e);
	                	tmp[j] = e;
	                }
	            }
        		for(int k = 0; k < 10; k++ ){
        			add(tmp[k].space);
        		}
        	}
        	else{
        		for (int j = 0; j < 10; j++) {
	                if(board[i][j] == 'X'){add(createNullSpace().space);}
	                else{
	                	Space e = createPathSpace(board[i][j]);
	                	add(e.space);
	                	path.add(e);
	                }
	            }
        	}
        }

        for(int i = 0 ; i < path.size() - 1; i++){
        	path.get(i).next = path.get(i+1);
        	path.get(i+1).previous = path.get(i);
        }

//        redLoc = start;
//        blueLoc = start;
//        greenLoc = start;
//        yellowLoc = start;

        redLoc = path.get(0);
        blueLoc = path.get(0);
        greenLoc = path.get(0);
        yellowLoc = path.get(0);


//        candybarSquare = path.get(10);
//        //candybarSquare.spaceColor = "white";
//        candybarSquare.space.setLayout(new BorderLayout());
//
//        cookiesSquare = path.get(22);
//        cookiesSquare.spaceColor = "white";
//        cookiesSquare.space.setLayout(new BorderLayout());
//
//        iceCreamSquare = path.get(29);
//        iceCreamSquare.spaceColor = "white";
//        iceCreamSquare.space.setLayout(new BorderLayout());
//
//        lollipopSquare = path.get(36);
//        lollipopSquare.spaceColor = "white";
//        lollipopSquare.space.setLayout(new BorderLayout());
//
//        sodaSquare = path.get(42);
//        sodaSquare.spaceColor = "white";
//        sodaSquare.space.setLayout(new BorderLayout());

        end = path.getLast();
        end.space.setLayout(new BorderLayout());


        BufferedImage red = ImageIO.read(new File("redToken.png"));
        BufferedImage grn = ImageIO.read(new File("greenToken.png"));
        BufferedImage blue = ImageIO.read(new File("blueToken.png"));
        BufferedImage yellow = ImageIO.read(new File("yellowToken.png"));
        BufferedImage all = ImageIO.read(new File("tokens.png"));
        //BufferedImage house = ImageIO.read(new File("grandmasHouse.png"));
        //JLabel gmasHouse = new JLabel(new ImageIcon(house));
        redImg = new JLabel(new ImageIcon(red));
        grnImg = new JLabel(new ImageIcon(grn));
        blueImg = new JLabel(new ImageIcon(blue));
        yellowImg = new JLabel(new ImageIcon(yellow));
        allTokens = new JLabel(new ImageIcon(all));

        JPanel start = path.getFirst().space;
        start.setLayout(new BorderLayout());
        start.add(redImg, BorderLayout.NORTH);
        start.add(grnImg, BorderLayout.WEST);
        start.add(blueImg, BorderLayout.EAST);
        start.add(yellowImg, BorderLayout.SOUTH);

        //end.space.add(gmasHouse);

    }


	public ArrayList<Object> getLocData(){
		ArrayList<Object> arr = new ArrayList<Object>();
		arr.add(path.indexOf(redLoc));
		arr.add(path.indexOf(blueLoc));
		arr.add(path.indexOf(greenLoc));
		arr.add(path.indexOf(yellowLoc));
		return arr;
	}

	public void putBackTokens(ArrayList<Object> arr){
		Space curSpace = redLoc.next;
		String curColor = curSpace.spaceColor;
		for(int i = 0; i < (int)arr.get(0)-1; i++){
			curSpace = curSpace.next;
			curColor = curSpace.spaceColor;
		}
		redLoc.space.remove(redImg);
		redLoc.space.revalidate();
		redLoc.space.repaint();
		redLoc = curSpace;
		redLoc.space.add(redImg, BorderLayout.NORTH);
		redLoc.space.revalidate();
		redLoc.space.repaint();

		curSpace = blueLoc.next;
		curColor = curSpace.spaceColor;
		for(int i = 0; i < (int)arr.get(1)-1; i++){
			curSpace = curSpace.next;
			curColor = curSpace.spaceColor;
		}
		blueLoc.space.remove(blueImg);
		blueLoc.space.revalidate();
		blueLoc.space.repaint();
		blueLoc = curSpace;
		blueLoc.space.add(blueImg, BorderLayout.NORTH);
		blueLoc.space.revalidate();
		blueLoc.space.repaint();

		curSpace = greenLoc.next;
		curColor = curSpace.spaceColor;
		for(int i = 0; i < (int)arr.get(2)-1; i++){
			curSpace = curSpace.next;
			curColor = curSpace.spaceColor;
		}
		greenLoc.space.remove(grnImg);
		greenLoc.space.revalidate();
		greenLoc.space.repaint();
		greenLoc = curSpace;
		greenLoc.space.add(grnImg, BorderLayout.NORTH);
		greenLoc.space.revalidate();
		greenLoc.space.repaint();

		curSpace = yellowLoc.next;
		curColor = curSpace.spaceColor;
		for(int i = 0; i < (int)arr.get(3)-1; i++){
			curSpace = curSpace.next;
			curColor = curSpace.spaceColor;
		}
		yellowLoc.space.remove(yellowImg);
		yellowLoc.space.revalidate();
		yellowLoc.space.repaint();
		yellowLoc = curSpace;
		yellowLoc.space.add(yellowImg, BorderLayout.NORTH);
		yellowLoc.space.revalidate();
		yellowLoc.space.repaint();
	}

    public int moveToken(int numSpaces, String color, int playerNum){
    	//MOVE RED TOKEN
    	if(playerNum == 1){
    		if(redLoc == end){
    			return playerNum;
    		}
    		try{
	    		Space curSpace = redLoc.next;
		    	String curColor = curSpace.spaceColor;
		    	while(!curColor.equals(color)){
		    		curSpace = curSpace.next;
		    		curColor = curSpace.spaceColor;
		    	}
		    	if(numSpaces == 2){
		    		curSpace = redLoc.next;
			    	curColor = curSpace.spaceColor;
			    	while(!curColor.equals(color)){
			    		curSpace = curSpace.next;
			    		curColor = curSpace.spaceColor;
			    	}
		    	}
		    	redLoc.space.remove(redImg);
		    	redLoc.space.revalidate();
		    	redLoc.space.repaint();
		    	redLoc = curSpace;
		    	redLoc.space.add(redImg, BorderLayout.NORTH);
		    	redLoc.space.revalidate();
		    	redLoc.space.repaint();
				return -1;
    		}
    		catch(Exception e){
    			try{
    			redLoc.space.remove(redImg);
		    	redLoc.space.revalidate();
		    	redLoc.space.repaint();
    			// System.out.println("you win!");
    			redLoc = end;
    			end.space.add(redImg, BorderLayout.NORTH);
    			end.space.revalidate();
		    	end.space.repaint();
				return playerNum;
    			}
    			catch(Exception f){
    				
    			}
    		}

    	}

    	//MOVE BLUE TOKEN
    	if(playerNum == 3){
    		if(blueLoc == end){
    			return playerNum;
    		}
    		try{
	    		Space curSpace = blueLoc.next;
		    	String curColor = curSpace.spaceColor;
		    	while(!curColor.equals(color)){
		    		curSpace = curSpace.next;
		    		curColor = curSpace.spaceColor;
		    	}
		    	if(numSpaces == 2){
		    		curSpace = blueLoc.next;
			    	curColor = curSpace.spaceColor;
			    	while(!curColor.equals(color)){
			    		curSpace = curSpace.next;
			    		curColor = curSpace.spaceColor;
			    	}
		    	}
		    	blueLoc.space.remove(blueImg);
		    	blueLoc.space.revalidate();
		    	blueLoc.space.repaint();
		    	blueLoc = curSpace;
		    	blueLoc.space.add(blueImg, BorderLayout.EAST);
		    	blueLoc.space.revalidate();
		    	blueLoc.space.repaint();
				return -1;
    		}
    		catch(Exception e){
    			try{
    			blueLoc.space.remove(blueImg);
		    	blueLoc.space.revalidate();
		    	blueLoc.space.repaint();
    			// System.out.println("you win!");
    			end.space.add(blueImg, BorderLayout.EAST);
    			end.space.revalidate();
		    	end.space.repaint();
		    	blueLoc = end;
				return playerNum;
    			}
    			catch(Exception f){
    				
    			}
    		}
    	}

    	//MOVE GREEN TOKEN
	    	if(playerNum == 4){
	    		if(greenLoc == end){
	    			return playerNum;
	    		}
	    		try{
		    		Space curSpace = greenLoc.next;
			    	String curColor = curSpace.spaceColor;
			    	while(!curColor.equals(color)){
			    		curSpace = curSpace.next;
			    		curColor = curSpace.spaceColor;
			    	}
			    	if(numSpaces == 2){
			    		curSpace = greenLoc.next;
				    	curColor = curSpace.spaceColor;
				    	while(!curColor.equals(color)){
				    		curSpace = curSpace.next;
				    		curColor = curSpace.spaceColor;
				    	}
			    	}
			    	greenLoc.space.remove(grnImg);
			    	greenLoc.space.revalidate();
			    	greenLoc.space.repaint();
			    	greenLoc = curSpace;
			    	greenLoc.space.add(grnImg, BorderLayout.WEST);
			    	greenLoc.space.revalidate();
			    	greenLoc.space.repaint();
					return -1;
	    		}
	    		catch(Exception e){
	    			try{
	    			greenLoc.space.remove(grnImg);
			    	greenLoc.space.revalidate();
			    	greenLoc.space.repaint();
	    			// System.out.println("you win!");
	    			end.space.add(grnImg, BorderLayout.WEST);
	    			end.space.revalidate();
			    	end.space.repaint();
			    	greenLoc = end;
					return playerNum;
	    			}
	    			catch(Exception f){
	    				
	    			}
	    		}

    	}

    	//MOVE YELLOW TOKEN
	    	if(playerNum == 2){
	    		if(yellowLoc == end){
	    			return playerNum;
	    		}
	    		try{
		    		Space curSpace = yellowLoc.next;
			    	String curColor = curSpace.spaceColor;
			    	while(!curColor.equals(color)){
			    		curSpace = curSpace.next;
			    		curColor = curSpace.spaceColor;
			    	}
			    	if(numSpaces == 2){
			    		curSpace = yellowLoc.next;
				    	curColor = curSpace.spaceColor;
				    	while(!curColor.equals(color)){
				    		curSpace = curSpace.next;
				    		curColor = curSpace.spaceColor;
				    	}
			    	}
			    	yellowLoc.space.remove(yellowImg);
			    	yellowLoc.space.revalidate();
			    	yellowLoc.space.repaint();
			    	yellowLoc = curSpace;
			    	yellowLoc.space.add(yellowImg, BorderLayout.SOUTH);
			    	yellowLoc.space.revalidate();
			    	yellowLoc.space.repaint();
					return -1;
	    		}
	    		catch(Exception e){
	    			try{
	    			yellowLoc.space.remove(yellowImg);
			    	yellowLoc.space.revalidate();
			    	yellowLoc.space.repaint();
	    			// System.out.println("you win!");
	    			end.space.add(yellowImg, BorderLayout.SOUTH);
	    			end.space.revalidate();
			    	end.space.repaint();
			    	yellowLoc = end;
					return playerNum;
	    			}
	    			catch(Exception f){
	    				
	    			}
	    		}


    	}
		return -1;
    }

    public void reverseMoveToken(int numSpaces, String color, int playerNum){

    	//MOVE RED TOKEN
    	if(playerNum == 1){
    		if(redLoc == end){
    			return;
    		}
    		try{
    			if(redLoc == path.getFirst()){
    				return;
    			}
	    		Space curSpace = redLoc.previous;
	    		if(curSpace == start){
	    			redLoc.space.remove(redImg);
			    	redLoc.space.revalidate();
			    	redLoc.space.repaint();
			    	redLoc = start;
			    	redLoc.space.add(redImg, BorderLayout.NORTH);
			    	redLoc.space.revalidate();
			    	redLoc.space.repaint();
					return;
	    		}
		    	String curColor = curSpace.spaceColor;
		    	while(!curColor.equals(color)){
		    		curSpace = curSpace.previous;
		    		curColor = curSpace.spaceColor;
		    		if(curSpace == start){
		    			redLoc.space.remove(redImg);
				    	redLoc.space.revalidate();
				    	redLoc.space.repaint();
				    	redLoc = start;
				    	redLoc.space.add(redImg, BorderLayout.NORTH);
				    	redLoc.space.revalidate();
				    	redLoc.space.repaint();
						return;
		    		}
		    	}
		    	redLoc.space.remove(redImg);
		    	redLoc.space.revalidate();
		    	redLoc.space.repaint();
		    	redLoc = curSpace;
		    	redLoc.space.add(redImg, BorderLayout.NORTH);
		    	redLoc.space.revalidate();
		    	redLoc.space.repaint();
				return;
    		}
    		catch(Exception e){
    			if(redLoc == path.getFirst()){
    				return;
    			}
    			else{
    				try{
	    			redLoc.space.remove(redImg);
			    	redLoc.space.revalidate();
			    	redLoc.space.repaint();
	    			// System.out.println("you win!");
	    			redLoc = path.getFirst();
	    			path.getFirst().space.add(redImg, BorderLayout.NORTH);
	    			path.getFirst().space.revalidate();
	    			path.getFirst().space.repaint();
					return;
    				}
    				catch(Exception f){
    	    			redLoc = path.getFirst();
    	    			path.getFirst().space.add(redImg, BorderLayout.NORTH);
    	    			path.getFirst().space.revalidate();
    	    			path.getFirst().space.repaint();
    					return;
    				}
    			}
    		}

    	}

    	//MOVE BLUE TOKEN
    	if(playerNum == 3){
    		if(blueLoc == end){
    			return;
    		}
    		try{
    			if(blueLoc == start){
    				//do nothing
    				return;
    			}
	    		Space curSpace = blueLoc.previous;
	    		if(curSpace == start){
	    			blueLoc.space.remove(blueImg);
			    	blueLoc.space.revalidate();
			    	blueLoc.space.repaint();
			    	blueLoc = start;
			    	blueLoc.space.add(blueImg, BorderLayout.EAST);
			    	blueLoc.space.revalidate();
			    	blueLoc.space.repaint();
					return;
	    		}
		    	String curColor = curSpace.spaceColor;
		    	while(!curColor.equals(color)){
		    		curSpace = curSpace.previous;
		    		curColor = curSpace.spaceColor;
		    		if(curSpace == start){
		    			blueLoc.space.remove(blueImg);
				    	blueLoc.space.revalidate();
				    	blueLoc.space.repaint();
				    	blueLoc = start;
				    	blueLoc.space.add(blueImg, BorderLayout.EAST);
				    	blueLoc.space.revalidate();
				    	blueLoc.space.repaint();
						return;
		    		}
		    	}

		    	blueLoc.space.remove(blueImg);
		    	blueLoc.space.revalidate();
		    	blueLoc.space.repaint();
		    	blueLoc = curSpace;
		    	blueLoc.space.add(blueImg, BorderLayout.EAST);
		    	blueLoc.space.revalidate();
		    	blueLoc.space.repaint();
				return;
    		}
    		catch(Exception e){
    			if(blueLoc == path.getFirst()){
    				return;
    			}
    			else{
    				try{
    				
	    			blueLoc.space.remove(blueImg);
			    	blueLoc.space.revalidate();
			    	redLoc.space.repaint();
	    			// System.out.println("you win!");
	    			blueLoc = path.getFirst();
	    			path.getFirst().space.add(blueImg, BorderLayout.EAST);
	    			path.getFirst().space.revalidate();
	    			path.getFirst().space.repaint();
					return;
    				}
    				catch(Exception f){
    	    			path.getFirst().space.add(blueImg, BorderLayout.EAST);
    	    			path.getFirst().space.revalidate();
    	    			path.getFirst().space.repaint();
    					return;
    				}
    			}
    		}
    	}


    	//MOVE GREEN TOKEN
	    	if(playerNum == 4){
	    		if(greenLoc == end){
	    			return;
	    		}
	    		try{
	    			if(greenLoc == start){
	    				//do nothing
	    				return;
	    			}
		    		Space curSpace = greenLoc.previous;
		    		if(curSpace == start){
		    			greenLoc.space.remove(grnImg);
				    	greenLoc.space.revalidate();
				    	greenLoc.space.repaint();
				    	greenLoc = start;
				    	greenLoc.space.add(grnImg, BorderLayout.WEST);
				    	greenLoc.space.revalidate();
				    	greenLoc.space.repaint();
						return;
		    		}
			    	String curColor = curSpace.spaceColor;
			    	while(!curColor.equals(color)){
			    		curSpace = curSpace.previous;
			    		curColor = curSpace.spaceColor;
			    	}

			    	greenLoc.space.remove(grnImg);
			    	greenLoc.space.revalidate();
			    	greenLoc.space.repaint();
			    	greenLoc = curSpace;
			    	greenLoc.space.add(grnImg, BorderLayout.WEST);
			    	greenLoc.space.revalidate();
			    	greenLoc.space.repaint();
					return;
	    		}
	    		catch(Exception e){
	    			if(greenLoc == path.getFirst()){
	    				return;
	    			}
	    			else{
	    				try{
		    			greenLoc.space.remove(grnImg);
				    	greenLoc.space.revalidate();
				    	greenLoc.space.repaint();
		    			// System.out.println("you win!");
		    			greenLoc = path.getFirst();
		    			path.getFirst().space.add(grnImg, BorderLayout.WEST);
		    			path.getFirst().space.revalidate();
		    			path.getFirst().space.repaint();
						return;
	    				}
	    				catch(Exception f){
			    			greenLoc = path.getFirst();
			    			path.getFirst().space.add(grnImg, BorderLayout.WEST);
			    			path.getFirst().space.revalidate();
			    			path.getFirst().space.repaint();
			    			return;
	    				}
	    			}
	    		}

    	}

    	//MOVE YELLOW TOKEN
	    	if(playerNum == 2){
	    		if(yellowLoc == end){
	    			return;
	    		}
	    		try{
	    			if(yellowLoc == start){
	    				//do nothing
	    				return;
	    			}
		    		Space curSpace = yellowLoc.previous;
		    		if(curSpace == start){
		    			yellowLoc.space.remove(yellowImg);
		    			yellowLoc.space.revalidate();
		    			yellowLoc.space.repaint();
		    			yellowLoc = start;
		    			yellowLoc.space.add(yellowImg, BorderLayout.SOUTH);
		    			yellowLoc.space.revalidate();
		    			yellowLoc.space.repaint();
						return;
		    		}
			    	String curColor = curSpace.spaceColor;
			    	while(!curColor.equals(color)){
			    		curSpace = curSpace.previous;
			    		curColor = curSpace.spaceColor;
			    	}

			    	yellowLoc.space.remove(yellowImg);
			    	yellowLoc.space.revalidate();
			    	yellowLoc.space.repaint();
			    	yellowLoc = curSpace;
			    	yellowLoc.space.add(yellowImg, BorderLayout.SOUTH);
			    	yellowLoc.space.revalidate();
			    	yellowLoc.space.repaint();
					return;
	    		}
	    		catch(Exception e){
	    			if(yellowLoc == path.getFirst()){
	    				return;
	    			}
	    			else{
	    				try{
		    				System.out.println("594");
			    			yellowLoc.space.remove(yellowImg);
					    	yellowLoc.space.revalidate();
					    	yellowLoc.space.repaint();
			    			// System.out.println("you win!");
			    			yellowLoc = path.getFirst();
			    			path.getFirst().space.add(yellowImg, BorderLayout.SOUTH);
			    			path.getFirst().space.revalidate();
			    			path.getFirst().space.repaint();
							return;
	    				}
	    				catch(Exception f){
	    					yellowLoc = path.getFirst();
			    			path.getFirst().space.add(yellowImg, BorderLayout.SOUTH);
			    			path.getFirst().space.revalidate();
			    			path.getFirst().space.repaint();
	    				}
	    			}
	    		}

    	}
		return;
    }

    private char[][] readPath() throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/path.txt"));
        char[][] board = new char[10][10];
        String line;
        int currRow = 0;

        while((line = br.readLine()) != null){
            String[] nums = line.split(",");
            for(int i = 0; i < nums.length; i++){
                char val = nums[i].charAt(0);
                board[currRow][i] = val;
            }
            currRow += 1;
        }
        return board;
    }

    private Space createNullSpace(){
        Dimension d = new Dimension(spaceSize, spaceSize);
        Space s = new Space();
        s.space = new JPanel();
        s.space.setPreferredSize(d);
        s.space.setBackground(Color.GRAY);
        s.space.setOpaque(true);
        return s;
    }

    private Space createPathSpace(char type){
        Dimension d = new Dimension(spaceSize, spaceSize);

        if(type == 'S'){
            Space s = new Space();
            s.space = new JPanel();
            s.space.setPreferredSize(d);
            s.space.setBackground(Color.BLACK);
            return s;
        }

        else if(type == 'F'){
            Space s = new Space();
            s.space = new FinishSpace();
            s.space.setPreferredSize(d);
            s.space.setBackground(Color.BLACK);
            return s;
        }
        else if(type == 'C'){
        	//Candybar
        	Space s = new Space();
        	s.space = new CandybarSpace();
        	 s.space.setPreferredSize(d);
             s.space.setBackground(Color.BLACK);
             s.spaceColor = "white";
             candybarSquare = s;
             return s;
        }
        else if(type == 'K'){
        	//Cookies
        	Space s = new Space();
        	s.space = new CookieSpace();
        	s.space.setPreferredSize(d);
            s.space.setBackground(Color.BLACK);
            cookiesSquare = s;
            s.spaceColor = "white";
            return s;
        }
        else if(type == 'I'){
        	//Ice Cream
        	Space s = new Space();
        	s.space = new IceCreamSpace();
        	s.space.setPreferredSize(d);
            s.space.setBackground(Color.BLACK);
            iceCreamSquare = s;
            s.spaceColor = "white";
             return s;
        }
        else if(type == 'L'){
        	//Lollipop
        	Space s = new Space();
        	s.space = new LollipopSpace();
        	 s.space.setPreferredSize(d);
             s.space.setBackground(Color.BLACK);
             lollipopSquare = s;
             s.spaceColor = "white";
             return s;
        }
        else if(type == 'D'){
        	//Soda
        	Space s = new Space();
        	s.space = new SodaSpace();
        	s.space.setPreferredSize(d);
            s.space.setBackground(Color.BLACK);
            sodaSquare = s;
            s.spaceColor = "white";
             return s;
        }
        else {
        	Space s = new Space();
            Color color = null;
            if(type == 'R'){color = Color.RED; s.spaceColor = "red";}
            else if(type == 'Y'){color = Color.YELLOW; s.spaceColor = "yellow";}
            else if(type == 'B'){color = Color.BLUE; s.spaceColor = "blue";}
            else if(type == 'G'){color = Color.GREEN; s.spaceColor = "green";}
            else if(type == 'O'){color = Color.ORANGE; s.spaceColor = "orange";}


            s.space = new JPanel();
            s.space.setLayout(new BorderLayout());
            s.space.setPreferredSize(d);
            s.space.setBackground(color);
            s.space.setOpaque(true);
            return s;
        }

    }


/*	public void gotoMiddle(int playerNum) {

		if(playerNum == 1){
			if(redLoc == end){
				//these are there to offer the possibility of finding 2nd and 3rd place
				return;
			}
			redLoc.space.remove(redImg);
			redLoc.space.revalidate();
	    	redLoc.space.repaint();
	    	redLoc = middle;
	    	redLoc.space.add(redImg, BorderLayout.EAST);
	    	redLoc.space.revalidate();
	    	redLoc.space.repaint();
		}
		if(playerNum == 2){
			if(yellowLoc == end){
				return;
			}
			yellowLoc.space.remove(yellowImg);
			yellowLoc.space.revalidate();
	    	yellowLoc.space.repaint();
	    	yellowLoc = middle;
	    	yellowLoc.space.add(yellowImg, BorderLayout.SOUTH);
	    	yellowLoc.space.revalidate();
	    	yellowLoc.space.repaint();
		}
		if(playerNum == 3){
			if(blueLoc == end){
				return;
			}
			blueLoc.space.remove(blueImg);
			blueLoc.space.revalidate();
	    	blueLoc.space.repaint();
	    	blueLoc = middle;
	    	blueLoc.space.add(blueImg, BorderLayout.WEST);
	    	blueLoc.space.revalidate();
	    	blueLoc.space.repaint();
		}
		if(playerNum == 4){
			if(greenLoc == end){
				return;
			}
			greenLoc.space.remove(grnImg);
			greenLoc.space.revalidate();
	    	greenLoc.space.repaint();
	    	greenLoc = middle;
	    	greenLoc.space.add(grnImg, BorderLayout.NORTH);
	    	greenLoc.space.revalidate();
	    	greenLoc.space.repaint();
		}
	}
*/


	public void gotoCandybar(int playerNum) {

		if(playerNum == 1){
			if(redLoc == end){
				return;
			}
			redLoc.space.remove(redImg);
			redLoc.space.revalidate();
	    	redLoc.space.repaint();
	    	redLoc = candybarSquare;
	    	redLoc.space.add(redImg, BorderLayout.NORTH);
	    	redLoc.space.revalidate();
	    	redLoc.space.repaint();
		}
		if(playerNum == 2){
			if(yellowLoc == end){
				return;
			}
			yellowLoc.space.remove(yellowImg);
			yellowLoc.space.revalidate();
	    	yellowLoc.space.repaint();
	    	yellowLoc = candybarSquare;
	    	yellowLoc.space.add(yellowImg, BorderLayout.SOUTH);
	    	yellowLoc.space.revalidate();
	    	yellowLoc.space.repaint();
		}
		if(playerNum == 3){
			if(blueLoc == end){
				return;
			}
			blueLoc.space.remove(blueImg);
			blueLoc.space.revalidate();
	    	blueLoc.space.repaint();
	    	blueLoc = candybarSquare;
	    	blueLoc.space.add(blueImg, BorderLayout.EAST);
	    	blueLoc.space.revalidate();
	    	blueLoc.space.repaint();
		}
		if(playerNum == 4){
			if(greenLoc == end){
				return;
			}
			greenLoc.space.remove(grnImg);
			greenLoc.space.revalidate();
	    	greenLoc.space.repaint();
	    	greenLoc = candybarSquare;
	    	greenLoc.space.add(grnImg, BorderLayout.WEST);
	    	greenLoc.space.revalidate();
	    	greenLoc.space.repaint();
		}
	}

	public void gotoCookies(int playerNum) {
		if(playerNum == 1){
			if(redLoc == end){
				return;
			}
			redLoc.space.remove(redImg);
			redLoc.space.revalidate();
	    	redLoc.space.repaint();
	    	redLoc = cookiesSquare;
	    	redLoc.space.add(redImg, BorderLayout.NORTH);
	    	redLoc.space.revalidate();
	    	redLoc.space.repaint();
		}
		if(playerNum == 2){
			if(yellowLoc == end){
				return;
			}
			yellowLoc.space.remove(yellowImg);
			yellowLoc.space.revalidate();
	    	yellowLoc.space.repaint();
	    	yellowLoc = cookiesSquare;
	    	yellowLoc.space.add(yellowImg, BorderLayout.SOUTH);
	    	yellowLoc.space.revalidate();
	    	yellowLoc.space.repaint();
		}
		if(playerNum == 3){
			if(blueLoc == end){
				return;
			}
			blueLoc.space.remove(blueImg);
			blueLoc.space.revalidate();
	    	blueLoc.space.repaint();
	    	blueLoc = cookiesSquare;
	    	blueLoc.space.add(blueImg, BorderLayout.EAST);
	    	blueLoc.space.revalidate();
	    	blueLoc.space.repaint();
		}
		if(playerNum == 4){
			if(greenLoc == end){
				return;
			}
			greenLoc.space.remove(grnImg);
			greenLoc.space.revalidate();
	    	greenLoc.space.repaint();
	    	greenLoc = cookiesSquare;
	    	greenLoc.space.add(grnImg, BorderLayout.WEST);
	    	greenLoc.space.revalidate();
	    	greenLoc.space.repaint();
		}

	}

	public void gotoIceCream(int playerNum) {
		if(playerNum == 1){
			if(redLoc == end){
				return;
			}
			redLoc.space.remove(redImg);
			redLoc.space.revalidate();
	    	redLoc.space.repaint();
	    	redLoc = iceCreamSquare;
	    	redLoc.space.add(redImg, BorderLayout.NORTH);
	    	redLoc.space.revalidate();
	    	redLoc.space.repaint();
		}
		if(playerNum == 2){
			if(yellowLoc == end){
				return;
			}
			yellowLoc.space.remove(yellowImg);
			yellowLoc.space.revalidate();
	    	yellowLoc.space.repaint();
	    	yellowLoc = iceCreamSquare;
	    	yellowLoc.space.add(yellowImg, BorderLayout.SOUTH);
	    	yellowLoc.space.revalidate();
	    	yellowLoc.space.repaint();
		}
		if(playerNum == 3){
			if(blueLoc == end){
				return;
			}
			blueLoc.space.remove(blueImg);
			blueLoc.space.revalidate();
	    	blueLoc.space.repaint();
	    	blueLoc = iceCreamSquare;
	    	blueLoc.space.add(blueImg, BorderLayout.EAST);
	    	blueLoc.space.revalidate();
	    	blueLoc.space.repaint();
		}
		if(playerNum == 4){
			if(greenLoc == end){
				return;
			}
			greenLoc.space.remove(grnImg);
			greenLoc.space.revalidate();
	    	greenLoc.space.repaint();
	    	greenLoc = iceCreamSquare;
	    	greenLoc.space.add(grnImg, BorderLayout.WEST);
	    	greenLoc.space.revalidate();
	    	greenLoc.space.repaint();
		}

	}

	public void gotoLollipop(int playerNum) {
		if(playerNum == 1){
			if(redLoc == end){
				return;
			}
			redLoc.space.remove(redImg);
			redLoc.space.revalidate();
	    	redLoc.space.repaint();
	    	redLoc = lollipopSquare;
	    	redLoc.space.add(redImg, BorderLayout.NORTH);
	    	redLoc.space.revalidate();
	    	redLoc.space.repaint();
		}
		if(playerNum == 2){
			if(yellowLoc == end){
				return;
			}
			yellowLoc.space.remove(yellowImg);
			yellowLoc.space.revalidate();
	    	yellowLoc.space.repaint();
	    	yellowLoc = lollipopSquare;
	    	yellowLoc.space.add(yellowImg, BorderLayout.SOUTH);
	    	yellowLoc.space.revalidate();
	    	yellowLoc.space.repaint();
		}
		if(playerNum == 3){
			if(blueLoc == end){
				return;
			}
			blueLoc.space.remove(blueImg);
			blueLoc.space.revalidate();
	    	blueLoc.space.repaint();
	    	blueLoc = lollipopSquare;
	    	blueLoc.space.add(blueImg, BorderLayout.EAST);
	    	blueLoc.space.revalidate();
	    	blueLoc.space.repaint();
		}
		if(playerNum == 4){
			if(greenLoc == end){
				return;
			}
			greenLoc.space.remove(grnImg);
			greenLoc.space.revalidate();
	    	greenLoc.space.repaint();
	    	greenLoc = lollipopSquare;
	    	greenLoc.space.add(grnImg, BorderLayout.WEST);
	    	greenLoc.space.revalidate();
	    	greenLoc.space.repaint();
		}

	}

	public void gotoSoda(int playerNum) {
		if(playerNum == 1){
			if(redLoc == end){
				return;
			}
			redLoc.space.remove(redImg);
			redLoc.space.revalidate();
	    	redLoc.space.repaint();
	    	redLoc = sodaSquare;
	    	redLoc.space.add(redImg, BorderLayout.NORTH);
	    	redLoc.space.revalidate();
	    	redLoc.space.repaint();
		}
		if(playerNum == 2){
			if(yellowLoc == end){
				return;
			}
			yellowLoc.space.remove(yellowImg);
			yellowLoc.space.revalidate();
	    	yellowLoc.space.repaint();
	    	yellowLoc = sodaSquare;
	    	yellowLoc.space.add(yellowImg, BorderLayout.SOUTH);
	    	yellowLoc.space.revalidate();
	    	yellowLoc.space.repaint();
		}
		if(playerNum == 3){
			if(blueLoc == end){
				return;
			}
			blueLoc.space.remove(blueImg);
			blueLoc.space.revalidate();
	    	blueLoc.space.repaint();
	    	blueLoc = sodaSquare;
	    	blueLoc.space.add(blueImg, BorderLayout.EAST);
	    	blueLoc.space.revalidate();
	    	blueLoc.space.repaint();
		}
		if(playerNum == 4){
			if(greenLoc == end){
				return;
			}
			greenLoc.space.remove(grnImg);
			greenLoc.space.revalidate();
	    	greenLoc.space.repaint();
	    	greenLoc = sodaSquare;
	    	greenLoc.space.add(grnImg, BorderLayout.WEST);
	    	greenLoc.space.revalidate();
	    	greenLoc.space.repaint();
		}

	}

	public class FinishSpace extends JPanel{
		@Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        try {
				image = ImageIO.read(new File("grandmasHouse.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        g.drawImage(image,0,0,this);
	    }
	}

	public class CandybarSpace extends JPanel{
		@Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        try {
				image = ImageIO.read(new File("candybarSquare.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        g.drawImage(image,0,0,this);
	    }
	}

	public class CookieSpace extends JPanel{
		@Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        try {
				image = ImageIO.read(new File("cookiesSquare.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        g.drawImage(image,0,0,this);
	    }
	}

	public class IceCreamSpace extends JPanel{
		@Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        try {
				image = ImageIO.read(new File("icecreamSquare.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        g.drawImage(image,0,0,this);
	    }
	}

	public class LollipopSpace extends JPanel{
		@Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        try {
				image = ImageIO.read(new File("lollipopSquare.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        g.drawImage(image,0,0,this);
	    }
	}

	public class SodaSpace extends JPanel{
		@Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        try {
				image = ImageIO.read(new File("sodaSquare.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        g.drawImage(image,0,0,this);
	    }
	}
	
	public String getPlayerColor(int playerNum){
		String returnColor = null;
		if(playerNum == 1){
			//red
			return redLoc.spaceColor;
		}
		if(playerNum == 2){
			//yellow
			return yellowLoc.spaceColor;
		}
		if(playerNum == 3){
			//blue
			return blueLoc.spaceColor;
		}
		if(playerNum == 4){
			//green
			return greenLoc.spaceColor;
		}
		return "red";
	}

}