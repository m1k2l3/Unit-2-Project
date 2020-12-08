import java.util.Scanner;
class Main {
  public static void main(String[] args) {
    //Creates a scanner object that allows the users to input their values.
    Scanner input = new Scanner(System.in);

    //fullName represemts user's full name
    String fullName = "";
    //item represents the selection number of item the customer orders
    int item = 0;
    //quantity represents the quantity of item the customer orders
    int quantity;
    //price represents the price of an item
    int[] price = {5,20,12,8,50};
    //numOfSelections represents number of selections the customer made
    int numOfSelections = 0;

    //Creates a 2D array to store the quantity, price and subtotal cost
    int[][] info = new int[5][3];

    System.out.println("Please enter your full name");
    fullName = input.nextLine();

    while(numOfSelections > -1 && numOfSelections < 5){
      item = 0;
      System.out.printf("What would like to order?(Enter the selection number)\n1 - Apple  $5\n2 - Meat   $20\n3 - Sushi  $12\n4 - Hotdog $8\n5 - Pizza  $50\n");

      System.out.println("Please enter the selection number of the item you would like to order.");
      
      while(item != 1 && item != 2 && item != 3 && item  != 4 && item != 5){
        try{
          item = Integer.parseInt(input.nextLine());
        }
        catch (Exception e){
          System.out.println("Please enter the correct selection number of the item you woule like to order!");
        }
      }
      
      //If the user selects the same item twice, the program stops
      if(price[item - 1] == -1){
        System.out.println("Order finished");
        break;
      }

      info[numOfSelections][1] = price[item - 1];
      price[item - 1] = -1;

      System.out.printf("Please enter the amount of %s you'd like to order\n",item);
      quantity = Integer.parseInt(input.nextLine());
      info[numOfSelections][0] = quantity;

      info[numOfSelections][2] = quantity * info[numOfSelections][1];

      //System.out.println(info[numOfSelections - 1][0] + " " + info[numOfSelections - 1][1] + " " + info[numOfSelections - 1][2]);

      numOfSelections ++;
    }

    for(int i = 0; i < 5; i++){
      System.out.println();
      for(int j = 0; j < 3; j++){
        System.out.print(info[i][j] + " ");
      }
    }

  }
}