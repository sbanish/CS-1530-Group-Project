//package java;

public class Tokens {
  // Holder String for the player's name
  public static String playerN1 = "p1";
  public static  String playerN2 = "p2";
  public static  String playerN3 = "p3";
  public static  String playerN4 = "p4";

// Holder strings for the player's color
  public static  String playerC1 = "none";
  public static  String playerC2 = "none";
  public static String playerC3 = "none";
  public static String playerC4 = "none";

// Setting the token name based on user input of name of choice + number of choice
  public static void setTokenName(String name, int playerNum)
  {
    if (playerNum == 1)
    {
      playerN1 = name;
    }
    else if (playerNum == 2)
    {
      playerN2 = name;
    }
    if (playerNum == 3)
    {
      playerN3 = name;
    }
    if (playerNum == 4)
    {
      playerN4 = name;
    }

  }

// returning the name of the token based on player number
  public static String getTokenName(int playerNum)
  {
    if(playerNum == 1)
    {
      return playerN1;
    }
    else if (playerNum == 2)
    {
      return playerN2;
    }
    else if(playerNum == 3)
    {
      return playerN3;
    }
    else
    {
      return playerN4;
    }
  }

// setting the color of the token based on the player's number
  public static void setTokenColor(String color, int playerNum)
  {
    if (playerNum == 1)
    {
      playerC1 = color;
    }
    else if (playerNum == 2)
    {
      playerC2 = color;
    }
    if (playerNum == 3)
    {
      playerC3 = color;
    }
    if (playerNum == 4)
    {
      playerC4 = color;
    }
  }

// returning the color of the player's token based on number
  public static String getTokenColor(int playerNum)
  {
    if(playerNum == 1)
    {
      return playerC1;
    }
    else if (playerNum == 2)
    {
      return playerC2;
    }
    else if(playerNum == 3)
    {
      return playerC3;
    }
    else
    {
      return playerC4;
    }
  }
}
