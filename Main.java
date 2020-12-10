import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

class Main {
  public static void main(String[] args) throws IOException{
    //Creates a scanner object that allows the users to input their values.
    Scanner input = new Scanner(System.in);
    //Declares myFile as a file object
    File myFile = new File("Order Information");

    //item represents the selection number of items the customer orders
    int item = -1;
    //quantity represents the quantity of items the customer orders
    int quantity = 0;
    //numOfSelections represents number of selections the customer made
    int numOfSelections = 0;
    //Creates a 2D array to store the quantity, price and subtotal cost
    int[][] info = {{0,5,0},{0,20,0},{0,12,0},{0,8,0},{0,50,0}};
    //name represents the uner's name in a string array
    String[] name;
    //subtotal means the total amount of money the user spends before tax
    double subtotal = 0;     

    //Create new file if it doesn't already exist
    myFile.createNewFile();

    System.out.println("Please enter your full name");

    //Stores the user's full name
    name = input.nextLine().split(" ");

    //Check if the user's name is appropriate
    while(name.length < 2){
      System.out.println("Please enter your full name");
      name = input.nextLine().split(" ");
    }

    //When the selection number is smaller than 5, user can purchase items from the shop
    while(numOfSelections < 5){
      
      //Menu
      System.out.printf("\nHello %s %s\n\nWhat would you like to order?(%d of selections have made)\nPlease enter the selection number\n\n1 - Apple  $5\n2 - Meat   $20\n3 - Sushi  $12\n4 - Hotdog $8\n5 - Pizza  $50\n\n0 - Finished Ordering\n\n",name[0],name[name.length-1],numOfSelections);

      while(item < 0 || item > 5){
        try{
          item = Integer.parseInt(input.nextLine());
          if(item > 0 && item < 6){
            //If the user alreay purchased this kind of items
            if(info[item - 1][0] > 0){
              System.out.println("You can not order the same item twice.");
              //Enters the selection number of items again
              item = -1;
            }
          }else if(item < 0 || item > 5){
              System.out.println("Please enter a valid number.");
          }else if(numOfSelections == 0){
              System.out.println("You have to choose at least one kind of items.");
          }
        }
        catch (Exception e){
          System.out.println("Please enter a valid number.");
        }
      }
      
      //If the user selects 0 with at least one kind of items purchased, the selection part exits
      if(item == 0){
        System.out.println("Order finished.");
        break;
      }

      //Amount of items the user purchases
      System.out.printf("Please enter the amount of %s's you'd like to order(1-999).\n",item);
      while(quantity < 1 || quantity > 999){
        try{
          quantity = Integer.parseInt(input.nextLine());
          info[item - 1][0] = quantity;
          if(quantity > 999 || quantity < 1){
            System.out.println("Please enter a valid number.");
          }
        }
        catch (Exception e){
          System.out.println("Please enter a valid number.");
        }
      }

      //The amount of money the user spends on one kind of item before tax.
      info[item - 1][2] = quantity * info[item - 1][1];
      
      //Sets the values each loop
      item = -1;
      clearScreen();
      quantity = 0;
      subtotal += info[numOfSelections][2];
      numOfSelections ++;
    }
    
    clearScreen();

    //Prints out the output
    System.out.printf("Order finished\nFirst Name: %s\nLast Name: %s\n\n         quantity  price($)  subtotal($)",name[0],name[name.length-1]);
    output(numOfSelections,info);

    System.out.printf("\n\nThe subtotal is $%.2f\nThe total is $%.2f",subtotal,tax(subtotal));
  }

  public static double tax(double subtotal){
    return subtotal + subtotal * 0.13;
  }
  
  public static void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
  }

  public static void output(int numOfSelections, int[][] info) throws IOException{
    //File writer;
    FileWriter myWriter = new FileWriter("Order Information");
    //Identifies the products the user purchased
    for(int i = 0; i < numOfSelections; i++){
      System.out.println();
      myWriter.write("\n");
      if(info[i][2] != 0){
        switch(info[i][1]){
          case 5:
            System.out.print("Apple  ");
            break;
          case 20:
            System.out.print("Meat   ");
            break;
          case 12:
            System.out.print("Sushi  ");
            break;
          case 8:
            System.out.print("Hotdog ");
            break;
          case 50:
            System.out.print("Pizza  ");
            break;
        }
        for(int j = 0; j < 3; j++){
          //Aligns the output and stores the information into the file
          myWriter.write(info[i][j] + " ");
          if(info[i][j] < 10){ 
            System.out.print("         " + info[i][j]);
          }else if(info[i][j] < 100){
            System.out.print("        " + info[i][j]);
          }else if(info[i][j] < 1000){
            System.out.print("       " + info[i][j]);
          }else if(info[i][j] < 10000){
            System.out.print("      " + info[i][j]);
          }else if(info[i][j] < 100000){
            System.out.print("     " + info[i][j]);
          }
        }
      }
    }
    //Closes the file
    myWriter.close();
  }
}