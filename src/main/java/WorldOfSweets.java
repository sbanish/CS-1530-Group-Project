import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.InputEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;
import java.util.ArrayList;
import javax.swing.filechooser.FileSystemView;
import java.util.Random;

public class WorldOfSweets implements Serializable{


	static Cards cards;
	static Gameboard board;
	static Login log;
	static Timer timer;
	static int playerNum = 1; //Counter to indicate which players turn it is, 1 - 4, incremented in card actionlistener
	static String player1n, player2n, player3n, player4n;
	static boolean isClassicMode;

	static int[] boomerangs;
	static JButton save;

	static int computerSelection = 1; //no computer as default
	static AbstractButton boomerangButton;
	static AbstractButton cardButtonClassic;
	static AbstractButton cardButton;
	static String computerCheck = "Computer";

	String currentPlayer;
	final int millis = 8000;
	int dadMode = 0;


    //Songs for the background game music
    static String songs = "Tobu_EDMSongs.wav";
    static Clip clip;
    static AudioInputStream audio;


	public WorldOfSweets(boolean isClassicMode, String player1n, String player2n, String player3n, String player4n, int computerSelection) throws Exception{

	        JFrame _frame = new JFrame();
	        _frame.setSize(500, 500);
			_frame.setVisible(true);
			_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        Image[] tokens = new Image[4];
	        ActionListener cardListener = new ButtonListener();
	        ActionListener boomerangListener = new BoomerangListener();
			ActionListener saveListener = new saveListener();
	        cards = new Cards(cardListener);
	        board = new Gameboard();
	        log = new Login(player1n, player2n, player3n, player4n);
			timer = new Timer();
			save = new JButton("Save");
			save.addActionListener(saveListener);

			JPanel southPanel = new JPanel();
			southPanel.add(log, BorderLayout.WEST);
			southPanel.add(timer, BorderLayout.CENTER);
			southPanel.add(save, BorderLayout.EAST);

			//Strategic mode
			//Give each player 3 boomerangs
			//create boomerang button

			dadMode = checkForDadMode();
			if(!isClassicMode){	

				boomerangs = new int[4];
				for(int i = 0; i < 4; i++){
					boomerangs[i] = 3;
				}

				JPanel eastPanel = new JPanel();
				
				boomerangButton = new JButton();
				boomerangButton.setLayout(new FlowLayout());
				boomerangButton.setPreferredSize(new Dimension(250,250));
				boomerangButton.addActionListener(boomerangListener);
				boomerangButton.setEnabled(true);
				cardButton = new JButton();
				cardButton.setLayout(new FlowLayout());
				cardButton.setPreferredSize(new Dimension(5,5));
				cardButton.setEnabled(true);

				eastPanel.setLayout(new BorderLayout());
				eastPanel.add(cards, BorderLayout.NORTH);
				eastPanel.add(cardButton, BorderLayout.CENTER);
				eastPanel.add(boomerangButton, BorderLayout.SOUTH );
				_frame.add(eastPanel, BorderLayout.WEST);
				setBoomerangs();
			}
			else{
				JPanel eastPanel = new JPanel();

				cardButtonClassic = new JButton();
				cardButtonClassic.setLayout(new FlowLayout());
				cardButtonClassic.setPreferredSize(new Dimension(5,5));
				cardButtonClassic.setEnabled(true);

				eastPanel.setLayout(new BorderLayout());
				eastPanel.add(cards, BorderLayout.NORTH);
				eastPanel.add(cardButtonClassic, BorderLayout.CENTER);
				_frame.add(eastPanel, BorderLayout.WEST);
			}

			_frame.add(board, BorderLayout.EAST);
	        _frame.add(southPanel, BorderLayout.SOUTH);
	        _frame.pack();
	        _frame.setVisible(true);

	        String currentPlayer = player1n;
	        int firstTurn1 = 0;
	        int firstTurn2 = 0;
	        int firstTurn3 = 0;

	        Random random = new Random();

	     if(isClassicMode == false){
		        while (computerSelection == 0){
						if (player2n.toLowerCase().contains(computerCheck.toLowerCase())){
							int randVariable = random.nextInt(2) + 1;
							if (randVariable == 1 && firstTurn1 != 0) {
								clickButton(boomerangButton, millis);
								currentPlayer = player3n;
								System.out.println("player1 "+randVariable +"\n");
							}else{
								clickCardButton(cardButton, millis);
								currentPlayer = player3n;
								System.out.println("player1 "+randVariable +"\n");
								firstTurn1 = 1;
							}
						}
						if (player3n.toLowerCase().contains(computerCheck.toLowerCase())){
							int randVariable1 = random.nextInt(2) + 1;
							if (randVariable1 == 1 && firstTurn2 != 0){
								clickButton(boomerangButton, millis);
								currentPlayer = player4n;
								System.out.println("player3 "+randVariable1 + "\n");
							}
							else{
								clickCardButton(cardButton, millis);
								currentPlayer = player4n;
								System.out.println("player3 "+randVariable1 + "\n");
								firstTurn2 = 1;
							}
						}
						if (player4n.toLowerCase().contains(computerCheck.toLowerCase())){
							int randVariable2 = random.nextInt(2) + 1;
							if (randVariable2 == 1 && firstTurn3 != 0){
								clickButton(boomerangButton, millis);
								currentPlayer = player1n;
								System.out.println("player4 "+randVariable2 + "\n");
							}
							else{
								clickCardButton(cardButton, millis);
								currentPlayer = player1n;
								System.out.println("player4 "+randVariable2 + "\n");
								firstTurn3 = 1;
							}
						}
					}
					//non computer option skipping to next token
					String nullCheck = "none";
				 	if(player2n.toLowerCase().contains(nullCheck.toLowerCase()))
				 		currentPlayer = player3n;
				 	if(player3n.toLowerCase().contains(nullCheck.toLowerCase()))
				 		currentPlayer = player4n;
				 	if(player4n.toLowerCase().contains(nullCheck.toLowerCase()))
				 		currentPlayer = player1n;
			} else {
					while (computerSelection == 0){
						if (player2n.toLowerCase().contains(computerCheck.toLowerCase())){
							clickCardButton(cardButton, millis);
							currentPlayer = player3n;
						}
						if (player2n.toLowerCase().contains(computerCheck.toLowerCase())){
							clickCardButton(cardButton, millis);
							currentPlayer = player3n;
						}
						if (player2n.toLowerCase().contains(computerCheck.toLowerCase())){
							clickCardButton(cardButton, millis);
							currentPlayer = player3n;
						}
					}

			} 
		}	
  
  	// this constructor pulls info from saved file
	public WorldOfSweets(String filename) throws Exception{
		Login new_log = null;
		Timer new_timer = null;
		int new_playerNum = -1;
		String new_player1n = null;
		String new_player2n = null;
		String new_player3n = null;
		String new_player4n = null;
		Boolean new_isClassicMode = null;
		JButton new_boomerangButton = null;
		int[] new_boomerangs = null;
		ArrayList<Object> new_tokenLocs = null;

		try{
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("last_saved_game.wos"));
			new_log = (Login) objectInputStream.readObject();
			new_timer = (Timer) objectInputStream.readObject();
			new_playerNum = (int) objectInputStream.readObject();
			new_player1n = (String) objectInputStream.readObject();
			new_player2n = (String) objectInputStream.readObject();
			new_player3n = (String) objectInputStream.readObject();
			new_player4n = (String) objectInputStream.readObject();
			new_isClassicMode = (Boolean) objectInputStream.readObject();
			new_boomerangButton = (JButton) objectInputStream.readObject();
			new_boomerangs = (int[]) objectInputStream.readObject();
			new_tokenLocs = (ArrayList<Object>) objectInputStream.readObject();
			objectInputStream.close();
		}
		catch(Exception ex){
			System.err.println("Unable to open last saved game.");
			System.exit(1);
		}
        JFrame _frame = new JFrame();
        _frame.setSize(500, 500);
		_frame.setVisible(true);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Image[] tokens = new Image[4];
        ActionListener cardListener = new ButtonListener();
        ActionListener boomerangListener = new BoomerangListener();
		ActionListener saveListener = new saveListener();
        cards = new Cards(cardListener);
        board = new Gameboard();
        log = new_log;
		timer = new Timer();
		save = new JButton("Save");
		save.addActionListener(saveListener);

		JPanel southPanel = new JPanel();
		southPanel.add(log, BorderLayout.WEST);
		southPanel.add(timer, BorderLayout.CENTER);
		southPanel.add(save, BorderLayout.EAST);

		player1n = new_player1n;
		player2n = new_player2n;
		player3n = new_player3n;
		player4n = new_player4n;
		isClassicMode = new_isClassicMode;

		//Strategic mode
		//Give each player 3 boomerangs
		//create boomerang button
		if(!isClassicMode){

			// boomerangs = new int[4];
			// for(int i = 0; i < 4; i++){
			// 	boomerangs[i] = 3;
			// }

			boomerangs = new_boomerangs;

			JPanel eastPanel = new JPanel();
			boomerangButton = new JButton();
			boomerangButton.setLayout(new FlowLayout());
			boomerangButton.setPreferredSize(new Dimension(250,250));
			boomerangButton.addActionListener(boomerangListener);
			boomerangButton.setEnabled(true);
			eastPanel.setLayout(new BorderLayout());
			eastPanel.add(cards, BorderLayout.NORTH);
			eastPanel.add(boomerangButton, BorderLayout.SOUTH );
			_frame.add(eastPanel, BorderLayout.WEST);
			setBoomerangs();
		}
		else{
			_frame.add(cards);
		}

		_frame.add(board, BorderLayout.EAST);
        _frame.add(southPanel, BorderLayout.SOUTH);
        _frame.pack();
        _frame.setVisible(true);

		board.putBackTokens(new_tokenLocs);
	}


    public static void main(String [] args) throws Exception{


		int loadGameOption;
		String[] loadOptions = {"Load Last Game","Start New Game"};
		loadGameOption = JOptionPane.showOptionDialog(null,
			"Would you like to load the last game or start a new game?",
			"World of Sweets",
			JOptionPane.YES_NO_CANCEL_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			loadOptions,
			loadOptions[1]);

		if(loadGameOption == 0){
			new WorldOfSweets("");
		}

		else if(loadGameOption == 1){
          Object[] numberOfReal = {"Four Players", "Three Players", "Two Players", "One Player"};
    	int numberOfRealPlayers = JOptionPane.showOptionDialog(null, "Please select the number of players in your group","Number of Players", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, numberOfReal, numberOfReal[0]);
     	int numOfComputerPlayers = 0;
     	//current values one players=3, two players=2, three players=1, four players=0
    	//normalizeing numberOfRealPlayers to represent the users selection
    	switch(numberOfRealPlayers) {
    	case 0: numberOfRealPlayers = 4;
    			System.out.println("You selected " + numberOfRealPlayers + " Players");
    			break;
    	case 1: numberOfRealPlayers = 3;
    			System.out.println("You selected " + numberOfRealPlayers + " Players");
    			numOfComputerPlayers = 1;
    			break;
    	case 2: numberOfRealPlayers = 2;
    			System.out.println("You selected " + numberOfRealPlayers + " Players");
    			numOfComputerPlayers = 2;
    			break;
    	case 3: numberOfRealPlayers = 1;
    			System.out.println("You selected " + numberOfRealPlayers + " Players");
    			numOfComputerPlayers = 3;
    			break;
    	default: numberOfRealPlayers = 0;
    			System.out.println("You selected " + numberOfRealPlayers + " Players..Exiting Program");
    			System.exit(1);
    	 		break;
    	 }

    	if (numberOfRealPlayers != 4){
	    	String[] computers = {"Yes","No"};
	        computerSelection = JOptionPane.showOptionDialog(null, "Would you like to Play with Computers?" + "\n\nPlease select Yes or No if you want the " + numOfComputerPlayers +
	        " remaining players to be controlled\n" + "by AI or simply play with your group" + "\n\n(There is a 4 second delay before a computer player makes a action)" + 
	        "\n\nMake a move within 10 seconds of it becoming your turn when\nplaying with the computer ", "Playing with Computers",
			JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, computers, computers[0]);
    	}
      
      
	        //LOGIN 
	        MultiInput playerNames = new MultiInput();
	        JTextField player1Value = new JTextField(10);
	        JTextField player2Value = new JTextField(10);
	        JTextField player3Value = new JTextField(10);
	        JTextField player4Value = new JTextField(10);
          //Prepopulating the textfields
        //No computer condition
    	if (computerSelection == 1) {
	    	switch(numberOfRealPlayers) {
	    	case 1:	player1Value.setText("Player1");
	    			player2Value.setText("none");
	 				player3Value.setText("none");
	 				player4Value.setText("none");
	 				player2Value.setEditable( false );
	 				player3Value.setEditable( false );
	 				player4Value.setEditable( false );
	    			break;
	    	case 2: player1Value.setText("Player1");
	 				player2Value.setText("Player2");
	 				player3Value.setText("none");
	 				player4Value.setText("none");
	 				player3Value.setEditable( false );
	 				player4Value.setEditable( false );
	    			break;
	    	case 3: player1Value.setText("Player1");
	 				player2Value.setText("Player2");
	 				player3Value.setText("Player3");
	 				player4Value.setText("none");
	 				player4Value.setEditable( false );
	    			break;
	    	case 4: player1Value.setText("Player1");
	 				player2Value.setText("Player2");
	 				player3Value.setText("Player3");
	 				player4Value.setText("Player4");
	    			break;
	    	}
    	} 
    	//Yes computer condition
    	if (computerSelection == 0){
    		switch(numberOfRealPlayers) {
	    	case 1:	player1Value.setText("Player1");
	 				player2Value.setText("Computer1");
	 				player3Value.setText("Computer2");
	 				player4Value.setText("Comptuer3");
	 				player2Value.setEditable( false );
	 				player2Value.setEnabled(false);
	 				player3Value.setEditable( false );
	 				player3Value.setEnabled(false);
	 				player4Value.setEditable( false );
	 				player4Value.setEnabled(false);
	 				numOfComputerPlayers = 3;
	    			break;
	    	case 2: player1Value.setText("Player1");
	 				player2Value.setText("Player2");
	 				player3Value.setText("Computer1");
	 				player4Value.setText("Computer2");
	 				player3Value.setEditable( false );
	 				player3Value.setEnabled(false);
	 				player4Value.setEditable( false );
	 				player4Value.setEnabled(false);
	 				numOfComputerPlayers = 2;
	    			break;
	    	case 3: player1Value.setText("Player1");
	 				player2Value.setText("Player2");
	 				player3Value.setText("Player3");
	 				player4Value.setText("Computer1");
	 				player4Value.setEditable( false );
	 				player4Value.setEnabled(false);
	 				numOfComputerPlayers = 1;
	    			break;
	    	}
    	} 

      
      
      
      
      
      
	        JPanel myPanel = new JPanel();

	        myPanel.add(new JLabel("Player 1:"));
	        myPanel.add(player1Value);
	        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	        myPanel.add(new JLabel("Player 2:"));
	        myPanel.add(player2Value);
	        myPanel.add(new JLabel("Player 3:"));
	        myPanel.add(player3Value);
	        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	        myPanel.add(new JLabel("Player 4:"));
	        myPanel.add(player4Value);

	        int userInput = JOptionPane.showConfirmDialog(null, myPanel,
	                   "Welcome to World of Sweets - Please Enter Player Names", JOptionPane.OK_CANCEL_OPTION);

	        //CANCEL OPTION
	        if (userInput == JOptionPane.CANCEL_OPTION){
	             System.exit(1);
	        }
	        //OK_OPTION
	        if (userInput == JOptionPane.OK_OPTION) {
	             player1n = player1Value.getText();
	             player2n = player2Value.getText();
	             player3n = player3Value.getText();
	             player4n = player4Value.getText();

	             // System.out.println("Player 1's name is: " + player1n);
	             // System.out.println("Player 2's name is: " + player2n);
	             // System.out.println("Player 3's name is: " + player3n);
	             // System.out.println("Player 4's name is: " + player4n);
	        }

	        playerNames.setPlayer1Name(player1n);
	        playerNames.setPlayer2Name(player2n);
	        playerNames.setPlayer3Name(player3n);
	        playerNames.setPlayer4Name(player4n);
	    	//END OF LOGIN

	    	//DIRECTIONS (2nd popup to user)
	        int directions;
	        String[] buttonOptions = {"Yes","No"};

	        directions = JOptionPane.showOptionDialog(null, "World of Sweets Game for CS 1530 \n(For 2 to 4 Players / Age 3 and up) \n\nDeveloped by:\n   Shawn Banish\n   Chris Lagunilla\n   Nick Sallinger\n   Spencer Young" +
	            "\n\nGameplay: Player 1 goes first then Player 2 and so on.\nEach players has their token originally placed on the start space." +
	            "\n\nOn your turn: Press the card button to draw a card from the deck that will have one color block.\nThe game will automatically move your token for you, based on the color on the card." +
	            "\n\nHow to win the game: If you are the first person to reach Grandma's House," +
	            "\nthen you have won the game. Congratulations!!!"+
	            "\n\n\n Would you like to enable background music while you play the game?",
	            "Instructions for World of Sweets",
	            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttonOptions, buttonOptions[1]);

	        if (directions == JOptionPane.NO_OPTION){
	            // System.out.println("No music option selected....Continuing to Gameplay");
	        } else {
	            // System.out.println("Music option selected....Continuing to Gameplay");
	             playMusic(songs);
	            // songs, directions
	        }
	        String[] classicOptions = {"Classic Mode", "Strategic Mode"};
	        int classicModeOption = JOptionPane.showOptionDialog(null, "You have the option to play in Classic Mode or Strategic Mode!\n" +
	        "In strategic mode, each player starts with 3 boomerangs. At the beginning of their turn, they can chose to pull a card or use a boomerang.\n" +
	        		"If a player chooses to use a boomerang, they then choose a player to send backwards to the closest space of the next card.\n"+
	        "But beware, if the next card is a special card, the boomeranged player will be sent there!", "Mode", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, classicOptions, classicOptions[0]);

	        if(classicModeOption == 0){
	        	isClassicMode = true;

	        }
	        else{
	        	isClassicMode = false;
	        }

	        //******** TEST *********//
	        //isClassicMode = false;
	    	//END OF DIRECTIONS

			WorldOfSweets curr_wos = new WorldOfSweets(isClassicMode, player1n, player2n, player3n, player4n, computerSelection);
		}

    }

	private static void saveGame() throws IOException{
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("last_saved_game.wos"));
		objectOutputStream.writeObject(log);
		objectOutputStream.writeObject(timer);
		objectOutputStream.writeObject(playerNum);
		objectOutputStream.writeObject(player1n);
		objectOutputStream.writeObject(player2n);
		objectOutputStream.writeObject(player3n);
		objectOutputStream.writeObject(player4n);
		objectOutputStream.writeObject(isClassicMode);
		objectOutputStream.writeObject(boomerangButton);
		objectOutputStream.writeObject(boomerangs);
		objectOutputStream.writeObject(board.getLocData());
		objectOutputStream.close();
		System.out.println("RBGY IX: " + board.getLocData());
	}

    //playing music throughout the game
    public static void playMusic(String songs){
        try{
            audio = AudioSystem.getAudioInputStream(new File(songs).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

	private void setBoomerangs(){


		boomerangButton.removeAll();


		BufferedImage boomerangBI = null;
		try{
			boomerangBI = ImageIO.read(new File("boomerang.png"));
		}
		catch(Exception ex){

		}
		JLabel boomerangImg1 = new JLabel(new ImageIcon(boomerangBI));
		JLabel boomerangImg2 = new JLabel(new ImageIcon(boomerangBI));
		JLabel boomerangImg3 = new JLabel(new ImageIcon(boomerangBI));

		if(boomerangs[playerNum-1] == 0){
			boomerangButton.setEnabled(false);
			boomerangButton.add(new JLabel("No More Boomerangs"));
		}
		else if(boomerangs[playerNum-1] == 3){
			boomerangButton.setEnabled(true);
			boomerangButton.add(boomerangImg1);
			boomerangButton.add(boomerangImg2);
			boomerangButton.add(boomerangImg3);
		}
		else if(boomerangs[playerNum-1] == 2){
			boomerangButton.setEnabled(true);
			boomerangButton.add(boomerangImg1);
			boomerangButton.add(boomerangImg2);
		}
		else if(boomerangs[playerNum-1] == 1){
			boomerangButton.setEnabled(true);
			boomerangButton.add(boomerangImg1);
		}
		//boomerangButton.add(new JLabel(playerNum + ":" + boomerangs[playerNum-1]));

		boomerangButton.revalidate();
		boomerangButton.repaint();
	}


	class saveListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			try{
				saveGame();
				System.out.println("GAME SAVED");
			}
			catch(IOException err){System.out.println("lol");}
		}
	}

		// Checking if the game is in "dad mode" or not
		public int checkForDadMode(){
			String checkPlayOne;
			String checkPlayTwo;
			String checkPlayThree;
			String checkPlayFour;
			dadMode = 0;

			checkPlayOne = player1n;
			if (checkPlayOne.equals("Dad") || checkPlayOne.equals("dad")){
				dadMode = 1;
			}

			checkPlayTwo = player2n;
			if (checkPlayTwo.equals("Dad") || checkPlayTwo.equals("dad")){
				dadMode = 2;
			}

			checkPlayThree = player3n;
			if (checkPlayThree.equals("Dad") || checkPlayThree.equals("dad")){
				dadMode = 3;
			}

			checkPlayFour = player4n;
			if (checkPlayFour.equals("Dad") || checkPlayFour.equals("dad")){
				dadMode = 4;
			}

			return dadMode;
		}



    class ButtonListener implements ActionListener{
		// Every time the card is clicked it will change
		public void actionPerformed(ActionEvent e) {


			if(!isClassicMode){
				boomerangButton.setEnabled(true);
			}
			JButton source = (JButton) e.getSource();
			
			
			if(playerNum == dadMode){
				System.out.println("In Dad Mode, currentColor = " + board.getPlayerColor(dadMode));
				String currentColor = board.getPlayerColor(dadMode);

				if(currentColor != null){
					if(currentColor.equals("red")){
						while(!cards.getColor().equals("yellow") | cards.getSkip() == true){
							cards.newCard(source);
						}
					}
					else if(currentColor.equals("yellow")){
						while(!cards.getColor().equals("blue") | cards.getSkip() == true){
							cards.newCard(source);
						}
					}
					else if(currentColor.equals("blue")){
						while(!cards.getColor().equals("green") | cards.getSkip() == true){
							cards.newCard(source);
						}
					}
					else if(currentColor.equals("green")){
						while(!cards.getColor().equals("orange") | cards.getSkip() == true){
							cards.newCard(source);
						}
					}
					else if(currentColor.equals("orange")){
						while(!cards.getColor().equals("red") | cards.getSkip() == true){
							cards.newCard(source);
						}
					}
				}
			}
			else{
				cards.newCard(source);
			}

			
			if(cards.getCandybar()){
				board.gotoCandybar(playerNum);
			}
			else if(cards.getCookies()){
				board.gotoCookies(playerNum);
			}
			else if(cards.getIceCream()){
				board.gotoIceCream(playerNum);
			}
			else if(cards.getLollipop()){
				board.gotoLollipop(playerNum);
			}
			else if(cards.getSoda()){
				board.gotoSoda(playerNum);
			}
			else if(!cards.getSkip()){
				int ret_val = board.moveToken(cards.getSpaces(), cards.getColor(), playerNum);
				if(ret_val != -1){
					String winner = "";
					if(ret_val == 1){winner = player1n;}
					else if(ret_val == 2){winner = player2n;}
					else if(ret_val == 3){winner = player3n;}
					else if(ret_val == 4){winner = player4n;}

					timer.stopTimer();
					JFrame win_frame = new JFrame();
					JOptionPane.showMessageDialog(win_frame, winner+" is the winner!");
				}
			}

			if(playerNum == 4){ playerNum = 1;}
			else{ playerNum++;}

			currentPlayer = log.changePlayer(playerNum);
			System.out.println("Current Player is "+ currentPlayer);
			if(!isClassicMode){
				setBoomerangs();
			}
		}
    }

    class BoomerangListener implements ActionListener{
		// Every time the card is clicked it will change
		public void actionPerformed(ActionEvent e) {
			
			JButton source = (JButton) e.getSource();
			BufferedImage redBI = null;

			//intialize random number value to zero
    		int randomNum = 0;
    		//value from the users selection from joption pane
    		int n = 0;
    		int result = 0;

    		AbstractButton play1Button = new JButton(player1n); //red
    		AbstractButton play2Button = new JButton(player2n); //yellow
    		AbstractButton play3Button = new JButton(player3n); //blue
    		AbstractButton play4Button = new JButton(player4n); //green
    		
			Object[] options = {player1n,player2n,player3n,player4n};

			 
			//if the user decided to not play with computers
    		if (computerSelection == 1){
				n = JOptionPane.showOptionDialog(source,
				    "Who would you like to boomerang?",
				    null, JOptionPane.YES_NO_CANCEL_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,
				    options,
				    options[0]);
			}

			//if the user decided to play with computers
			//Condition for the computer to randomly select a player to boomerang other than themselves
			//COMPUTER PLAYER 2 CONDITIONAL
			if (computerSelection == 0){
				if(playerNum == 1){
					n = JOptionPane.showOptionDialog(source,
						    "Who would you like to boomerang?",
						    null, JOptionPane.YES_NO_CANCEL_OPTION,
						    JOptionPane.QUESTION_MESSAGE,
						    null,
						    options,
						    options[0]);
				}
				else if(playerNum == 2){
					if(player2n.toLowerCase().contains(computerCheck.toLowerCase())){
						ArrayList<Integer> oneValue = new ArrayList<Integer>();
						oneValue.add(2);
						randomNum = generateRandom(0, 3, oneValue);
						System.out.println("n = " + n);
						System.out.println("options[n] = " + options[n]);
						n = randomNum;
						if (randomNum == 1){
							boomerangPopup(options[n].toString());
						} else if (randomNum == 3){
							boomerangPopup(options[n].toString());
						} else {
							boomerangPopup(options[n].toString());
						}
					}
					else{
						n = JOptionPane.showOptionDialog(source,
							    "Who would you like to boomerang?",
							    null, JOptionPane.YES_NO_CANCEL_OPTION,
							    JOptionPane.QUESTION_MESSAGE,
							    null,
							    options,
							    options[0]);
					}
				}
				else if(playerNum == 3){
					if (player3n.toLowerCase().contains(computerCheck.toLowerCase())){
						ArrayList<Integer> oneValue2 = new ArrayList<Integer>();
						oneValue2.add(3);
						randomNum = generateRandom(0, 3, oneValue2);
						n = randomNum;
						System.out.println("n = " + n);
						System.out.println("options[n] = " + options[n]);
						if (randomNum == 1){
							boomerangPopup(options[n].toString());
						} else if (randomNum == 2){
							boomerangPopup(options[n].toString());
						} else {
							boomerangPopup(options[n].toString());
						}
					}
					else{
						n = JOptionPane.showOptionDialog(source,
							    "Who would you like to boomerang?",
							    null, JOptionPane.YES_NO_CANCEL_OPTION,
							    JOptionPane.QUESTION_MESSAGE,
							    null,
							    options,
							    options[0]);
					}
				}
				else if(playerNum == 4){
					if (player4n.toLowerCase().contains(computerCheck.toLowerCase())){
						ArrayList<Integer> oneValue3 = new ArrayList<Integer>();
						oneValue3.add(4);
						randomNum = generateRandom(0, 3, oneValue3);
						n = randomNum;
						System.out.println("n = " + n);
						System.out.println("options[n] = " + options[n]);
						if (randomNum == 1){
							boomerangPopup(options[n].toString());
						} else if (randomNum == 2){
							boomerangPopup(options[n].toString());
							boomerangPopup(options[n].toString());
						}
					}
					else{
						n = JOptionPane.showOptionDialog(source,
							    "Who would you like to boomerang?",
							    null, JOptionPane.YES_NO_CANCEL_OPTION,
							    JOptionPane.QUESTION_MESSAGE,
							    null,
							    options,
							    options[0]);
					}
				}

//				//if (player2n.toLowerCase().contains(computerCheck.toLowerCase())){
//				if(player2nCheck.equals("computer")){
//					ArrayList<Integer> oneValue = new ArrayList<Integer>();
//					oneValue.add(2);
//					randomNum = generateRandom(1, 4, oneValue);
//					n = randomNum;
//					if (randomNum == 1){
//						boomerangPopup(n);
//					} else if (randomNum == 3){
//						boomerangPopup(n);
//					} else {
//						boomerangPopup(n);
//					}
//				}//COMPUTER PLAYER 3 CONDITIONAL
//				else if (player3n.toLowerCase().contains(computerCheck.toLowerCase())){
//					ArrayList<Integer> oneValue2 = new ArrayList<Integer>();
//					oneValue2.add(3);
//					randomNum = generateRandom(1, 4, oneValue2);
//					n = randomNum;
//					if (randomNum == 1){
//						boomerangPopup(n);
//					} else if (randomNum == 2){
//						boomerangPopup(n);
//					} else {
//						boomerangPopup(n);
//					}
//				}//COMPUTER PLAYER 4 CONDITIONAL 
//				else if (player4n.toLowerCase().contains(computerCheck.toLowerCase())){
//					ArrayList<Integer> oneValue3 = new ArrayList<Integer>();
//					oneValue3.add(4);
//					randomNum = generateRandom(1, 4, oneValue3);
//					n = randomNum;
//					if (randomNum == 1){
//						boomerangPopup(n);
//					} else if (randomNum == 2){
//						boomerangPopup(n);
//					} else {
//						boomerangPopup(n);
//					}
//				//REAL PLAYER CONDITIONAL WITHIN COMPUTER OPTION
//				} 
//				else{
//					n = JOptionPane.showOptionDialog(source,
//					    "Who would you like to boomerang?",
//					    null, JOptionPane.YES_NO_CANCEL_OPTION,
//					    JOptionPane.QUESTION_MESSAGE,
//					    null,
//					    options,
//					    options[0]);
//				}
//			}
		
			boomerangs[playerNum-1]--;

			System.out.println("boomeranging " + options[n]);

			n=n+1;

			cards.newCard(cards._button);

			if(cards.getCandybar()){
				board.gotoCandybar(n);
			}
			else if(cards.getCookies()){
				board.gotoCookies(n);
			}
			else if(cards.getIceCream()){
				board.gotoIceCream(n);
			}
			else if(cards.getLollipop()){
				board.gotoLollipop(n);
			}
			else if(cards.getSoda()){
				board.gotoSoda(n);
			}
			else if(!cards.getSkip()){
				board.reverseMoveToken(cards.getSpaces(), cards.getColor(), n);
			}

			
			
			if(playerNum == 4){ playerNum = 1;}
			else{ playerNum++;}
		
			currentPlayer = log.changePlayer(playerNum);
			System.out.println("Current Player is "+ currentPlayer);
//			cards.newCard(cards._button);
			
			setBoomerangs();
			
			}
		}
    }


    //for generating a random player for the current player to boomerang back being able to exclude number
    public int generateRandom(int start, int end, ArrayList<Integer> excludeRows) {
	   
	    Random rand = new Random();
	    int range = end - start + 1;

	    int random = rand.nextInt(range);// + 1;
	    while(excludeRows.contains(random)) {
	        random = rand.nextInt(range);// + 1;
	    }

	return random;
	}

	public void clickButton(AbstractButton button, int millis) throws AWTException
	{
	    Point p = button.getLocationOnScreen();
	    java.awt.Robot r = new java.awt.Robot();
	    r.mouseMove(p.x + button.getWidth() / 2, p.y + button.getHeight() / 2);
	    r.mousePress(InputEvent.BUTTON1_MASK);
	    int millis2 = 30000;
	    //try { Thread.sleep(millis2); } catch (Exception e) {}
	    r.delay(millis);
	    r.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	public void clickCardButton(AbstractButton button, int millis) throws AWTException
	{
	    Point p = button.getLocationOnScreen();
	    java.awt.Robot r = new java.awt.Robot();
	    r.mouseMove(100,100);
	    r.mousePress(InputEvent.BUTTON1_MASK);
	    int millis2 = 30000;
	    //try { Thread.sleep(millis2); } catch (Exception e) {}
	    r.delay(millis);
	    r.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	public void boomerangPopup(String n){
		JOptionPane.showMessageDialog(null, n+  " selected for boomeranging", "Boomerang", JOptionPane.DEFAULT_OPTION);
	}

}//end of the WorldofSweets.java file
