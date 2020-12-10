import java.util.Scanner;
class Main {
  public static void main(String[] args) {
    //Creates a scanner object that allows the users to input their values.
    Scanner input = new Scanner(System.in);

    //item represents the selection number of item the customer orders
    int item = 0;
    //quantity represents the quantity of item the customer orders
    int quantity = 0;
    //numOfSelections represents number of selections the customer made
    int numOfSelections = 0;
    //Creates a 2D array to store the quantity, price and subtotal cost
    int[][] info = {{0,5,0},{0,20,0},{0,12,0},{0,8,0},{0,50,0}};
    //name represents the uner's name in a string array
    String[] name;
    //subtotal means the total amount of money the user spend before tax
    double subtotal = 0;

    System.out.println("Please enter your full name");

    //Store the user's full name
    name = input.nextLine().split(" ");
    while(name.length < 2){
      System.out.println("Please enter your full name");
      name = input.nextLine().split(" ");
    }

    while(numOfSelections > -1 && numOfSelections < 5){
      item = -1;

      System.out.printf("\nHello %s %s\n\nWhat would like to order?(Enter the selection number)\n1 - Apple  $5\n2 - Meat   $20\n3 - Sushi  $12\n4 - Hotdog $8\n5 - Pizza  $50\n\n0 - Finished Ordering\n\n",name[0],name[name.length-1]);

      System.out.printf("Please enter the selection number of the item you would like to order.If you enter the same section number in two different selections, the order finishes.You have to choose at least 1 item for ordering.(%d of selections made)\n\n",numOfSelections);

      while(item < 0 || item > 5||(item == 0 && numOfSelections == 0)){
        try{
          item = Integer.parseInt(input.nextLine());
        }
        catch (Exception e){
          System.out.println("Please enter a valid number");
        }
      }
      
      //If the user selects the same item twice or select all the item, the selection part exits
      if(item == 0){
        System.out.println("Order finished");
        break;
      }else if(info[item - 1][0] > 0){
        System.out.println("Order finished");
        break;
      }

      System.out.printf("Please enter the amount of %s's you'd like to order(1-999)\n",item);
      while(quantity == 0){
        try{
          quantity = Integer.parseInt(input.nextLine());
          info[item - 1][0] = quantity;
        }
        catch (Exception e){
          System.out.println("Please enter a valid number");
        }
        if(quantity > 999 || quantity < 1){
          System.out.println("Number excceds the limit, please enter a number from 1-999");
          quantity = 0;
        }
      }

      //The amount of money the user spend on one kind of items.
      info[item - 1][2] = quantity * info[item - 1][1];

      //System.out.println(info[numOfSelections - 1][0] + " " + info[numOfSelections - 1][1] + " " + info[numOfSelections - 1][2]);

      clearScreen();
      quantity = 0;
      subtotal += info[numOfSelections][2];
      numOfSelections ++;
    }
    
    clearScreen();

    //Print out the output
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

  public static void output(int numOfSelections, int[][] info){
    System.out.printf("Order finished\nFirst Name: %s\nLast Name: %s\n\n         quantity  price($)  subtotal($)",name[0],name[name.length-1]);
    for(int i = 0; i < numOfSelections; i++){
      System.out.println();
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
          //Align the output 
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
  }
}