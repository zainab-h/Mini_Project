// Zainab Habib
// 05/11/2021
// VERSION  55
// pet program

import java.util.Scanner;
import java.util.Random; 
import java.util.Arrays;

//pet type 
class pet
{
  String name;
  String species; 
  int hunger;
  int happiness;
}


public class pet_program {
    public static void main(String[] args) {
        int petCreated = 0;
        pet pet = createPet();
        setName(pet, "Empty");

        //create empty array of 0 length
        String[] names = new String[0];
        String[] types = new String[0];
        
        welcomeInfo();
        int option = mainMenu(pet);
        
        //ensure the option they chose is valid
        while ((option ==3)||(option ==2) && (petCreated ==0))
        {
            print("You need to create a pet first");
            option = mainMenu(pet);
        }

        //allow the user to create a pet and store the data within an array of all pet names and types
        if (option == 1)
        {

            pet = petSetUp(pet);
            petCreated = petCreated + 1;
            //add data to array
            names = appendToArray(names, getName(pet));
            types = appendToArray(types, getType(pet));
            //proceeds to run the game
            helloMessage(pet);
            print("Proceeding to run the game.....");
            instructions();
            level1(pet);
            option = mainMenu(pet);

        }
        if (option ==2)
        {
            seePets(names, types, petCreated);
            //select show pets
        }
        //allow the user to play the game once they select a pet name
        if (option ==3)
        {
            int choice = selectPet(names, types, petCreated);
            if (choice == -1)
            {
                print("You entered the wrong pet name ");
                choice = selectPet(names, types, petCreated);
            }
            else
            {
                String tempName = names[choice];
                String tempType = types[choice];
                setName(pet, tempName);
                setType(pet, tempType);
            }
            level1(pet);
        }
        System.exit(0);
    }// END main
    
    //returns the chosen pet picked by the user
    public static int selectPet(String[] names, String[] types, int Count)
    {
        int temp = 0;
        seePets(names, types, Count);
        String choice = inputString("Enter which pet you want to play with");

        for (int i = 0; i<Count; i++)
        {
            if (names[i].equals(choice))
            {
                return temp = i;
            }
        }
        return temp = -1;
    }

    //allows string to be appended to end of array
    public static String[] appendToArray (String[] oldArray, String newString)
    {
        String[] newArray = Arrays.copyOf(oldArray, oldArray.length+1);
        newArray[oldArray.length] = newString;
        return newArray;
    }

    //displays all current pets and their types
    public static void seePets(String[] names, String[] types, int Count)
    {
        
        for (int i = 0; i<Count; i++)
        {
            
            System.out.println("Pet Name: " + names[i] + ", Species: " + types[i]);
        }
        return;
    }

    //create pet record
    public static pet createPet()
    {
        pet pet1 = new pet();
        return pet1;
    }

    //basic welcome to the game
    public static void welcomeInfo()
    {
        print("**********************************************");
        print("Hello!");
        print("Welcome to my pet program!");
        print("This is a simulation of a pet that you need to look after!");
        print("You will have to make sure your pet is happy and not hungry so it can survive!");
        print("If your pet dies you lose the game");
        print("**********************************************");
        print("\n");
        
        return;
    }//END welcome info

    //alow the user to pick what they want to do
    public static int mainMenu(pet pet1)
    {
        print("Main Menu: ");
        int option = Integer.parseInt(inputString("Options 1: create pet " +
                                             "\n" + "Options 2: see pets "+
                                             "\n" + "Options 3: Play game"));       

        while ((option != 1) && (option != 2) && (option != 3) )
        {
            option = Integer.parseInt(inputString("Options 1: create pet " +
                                                    "\n" + "Options 2: see pets " +
                                                    "\n" + "Options 3: Play game"));  
        }

        return option;
    }

    //creates an instance of a pet
    public static pet petSetUp(pet pet1){
        print("What type of pet do you want to create?");
        String type = inputString("Please select from the following options: Dragon, Mermaid, Fairy (case sensitive)");
        if (type.equals("Dragon") ||type.equals("Mermaid") ||type.equals("Fairy"))
        {
            String name = inputString("What do you want to name your pet? ");
            setName(pet1, name);
            setType(pet1, type);
        }
        else
        {
            print("Please enter correctly");
            print("\n");
            petSetUp(pet1);
        }
        return pet1;
    }

    //takes pet record as an argument and in this method calls it as petUpdateName
    public static void setName(pet petUpdateName, String name)
    {
        petUpdateName.name = name;
        return;
    }// end procedure
    
    //returns pet name
    public static String getName(pet pet)
    {
        return pet.name;
    }// end procedure
    
    //returns pet type
    public static String getType(pet pet)
    {
        return pet.species;
    }// end procedure
    
    //sets the sepecifies of the pet
    public static void setType(pet petUpdatetype, String type)
    {
        petUpdatetype.species = type;
        return;
    }// end procedure

    //allows the pet to say hello to the user
    public static void helloMessage(pet petDetails)
    {   
        print("\n");
        print("***************************");
        print(petDetails.name + " is born!");
        print(petDetails.name + " is a " +petDetails.species + "!");
        print("***************************");
        print(petDetails.name + " says hello!");
        print("***************************");
        return;
    }// end hello message
 
    //prints instructions for the rounds
    public static void instructions(){
        print("\n");
        print("********Instructions**********");
        print("Your pet will get hungry and fluctate in mood");
        print("Its your job to make sure it is well fed and happy");
        print("To do this you have to spend points on your pet");
        print("Points are allocated at the start of the round and the round lasts for set amount of time");
        print("Different rounds have different points and durations allocated");
        print("If your pet is unhappy or hungry for 2 rounds straight you lose the game");
        print("There are 5 rounds");
        print("Goodluck");
        print("\n");
        return;
    }

    //first level of the game 
    public static void level1(pet pet1){
        boolean isUnhappy = false;
        boolean isHungry = false;
        boolean haveLost = false;

        int count = 0;
        final int ROUNDS = 2;
        Random dice = new Random();
        
        int points = dice.nextInt(15) + 1;

        print("Level 1");
        for (int i = 1; i<=ROUNDS; i++)
        {
            print("Round: " +i);
            setHunger(pet1);
            setHappiness(pet1);
            isUnhappy = getHunger(pet1);
            isHungry = getHappiness(pet1);
            print("You have " + points + " points.");
            
            points = feed(pet1, points);
            points = play(pet1, points);

            count = checkState(isUnhappy,isHungry,count, pet1);
            if (count==0)
            {
                haveLost = false;
            }
            else
            {
                haveLost = true;
            }
            //loop for next round
            print("\n");
            changeState(pet1);
        }

        print("The round has ended");
        print("You had " + points + " points left.");
        if ((haveLost==true) && (count ==2))
        {
            print("You lost this level as your pet was hungry/happy for 2 rounds or more.");
            print("better luck next time");
        }
        else
        {
            print("You won this round!");
        }
        
        return;
    }

    //checks to see if they have won or lost the round
    public static int checkState(boolean isUnhappy, boolean isHungry, int count, pet pet1)
    {
        print("\n");
        isUnhappy = getHunger(pet1);
        isHungry = getHappiness(pet1);
        if (isUnhappy == true || isHungry == true)
        {
            count +=1;
            print("You lost this round");
        }
        else
        {
            count = 0;
            print("You won this round");
        }
        return count;
    }

    //checks to see if the user has enough points
    public static boolean enoughPoints(int points){
        if (points >= 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //changes the state of the pet for the next round
    public static void changeState (pet pet1)
    {
        Random dice = new Random();
        int oneOrTwo = dice.nextInt(2);
        if (oneOrTwo == 1)
        {
            setHunger(pet1);
            setHappiness(pet1);
        }
    }

    //feeds the pet and updates the points 
    public static int feed (pet pet1, int points)
    {
        boolean yesOrNo = false;
        yesOrNo = decision("Do you want to feed your pet? yes or no");
        if (yesOrNo && (enoughPoints(points)==true))
        {
            points = feedPet(pet1, points);
            print("You now have " + points + " points.");
        }
        else if (enoughPoints(points)==false)
        {
            print("You don't have enough points");
        }
        return points;
    }

    //plays with the pet and updates the points 
    public static int play(pet pet1, int points)
    {
        boolean yesOrNo = false;
        yesOrNo = decision("Do you want to play with your pet? yes or no");
        if (yesOrNo && (enoughPoints(points)==true))
        {
           points = playWithPet(pet1, points);
           print("You now have " + points + " points.");
        }
        else if (enoughPoints(points)==false)
        {
            print("You don't have enough points");
        }
        return points;
    }
   
    //allows the user to feed their pet
    public static int feedPet(pet pet, int pointsUpdate)
    {

        int points = Integer.parseInt(inputString("How many levels do you want to feed your pet? 1 - 5"));
        int newPoints = pet.hunger+points;
        int oldPoints = pointsUpdate;
        pointsUpdate = pointsUpdate - points;

        //input is within range
        //new points is positive
        if ((points>=1 && points<=5) && pointsUpdate>=0)
        {
            //pet hunger is 1-5
            if (newPoints<=5)
            {
                pet.hunger = newPoints;
                getHunger(pet);
                return pointsUpdate;
            }
            //new pet hunger is above 5
            else
            {
                pet.hunger = newPoints - pet.hunger;
                getHunger(pet);
                return pointsUpdate;
            }
        }
        else if (pointsUpdate<0)
        {
            print("You cant feed your pet this many levels as you dont have enough points");
            return oldPoints;
        }
        else
        {
            print("You entered the wrong number");
            return oldPoints;
        }

    }//END method

    //allows the user to play with their pet
    public static int playWithPet(pet pet, int pointsUpdate)
    {
        int points = Integer.parseInt(inputString("How much do you want to play with your pet? 1 - 4"));
        int newPoints =  pet.happiness+points;
        int oldPoints = pointsUpdate;
        pointsUpdate = pointsUpdate - points;

        //input is within range
        //new points is positive
        if ((points>=1 && points<=4) && pointsUpdate>=0)
        {
            //pet happiness is 1-4
            if (newPoints<=4)
            {
                pet.happiness = newPoints;
                getHappiness(pet);
                return pointsUpdate;
            }
            //new pet hunger is above 5
            else
            {
                pet.happiness= newPoints - pet.happiness;
                getHappiness(pet);
                return pointsUpdate;
            }
        }
        else if (pointsUpdate<0)
        {
            print("You cant play with your pet as you dont have enough points");
            return oldPoints;
        }
        else
        {
            print("You entered the wrong number");
            return oldPoints;
        }


    }//END method

    //procedure that randomly sets the pets hunger level
    public static void setHunger(pet petHunger)
    {
        Random dice = new Random();
        int hungerLevel = dice.nextInt(4) + 1;
        petHunger.hunger = hungerLevel;
        return;
    }//END method

    //procedure that randomly sets the pets happiness level
    public static void setHappiness(pet petHapppiness)
    {
        Random dice = new Random();
        int happinessLevel = dice.nextInt(5) + 1;
        petHapppiness.happiness = happinessLevel;
        return;
    }//END method
    
    //method that displays the pets hunger level
    public static boolean getHunger(pet pet)
    {
        boolean isHungry = false; 
        if (pet.hunger == 1)
        {
            print(pet.name + "'s hunger rate is extremely hungry");
            isHungry = true; 
        }
        else if (pet.hunger == 2)
        {
            print(pet.name + "'s hunger rate is very hungry");
            isHungry = true; 
        }
        else if (pet.hunger == 3)
        {
            print(pet.name + "'s hunger rate is satisfied");
            isHungry = false; 
        }
        else if (pet.hunger == 4)
        {
            print(pet.name + "'s hunger rate is full");
            isHungry = false; 
        }
        else
        {
            print(pet.name + "'s hunger rate is overfilled");
            isHungry = false; 
        }
        return isHungry;
    }//END method

    //method that displays the pets happiness level
    public static boolean getHappiness(pet pet)
    {
        boolean isUnhappy = false;
        if (pet.happiness == 1)
        {
            print(pet.name + "'s angry");
            isUnhappy = true;
        }
        else if (pet.happiness == 2)
        {
            print(pet.name + "'s sad");
            isUnhappy = true;
        }
        else if (pet.happiness == 3)
        {
            print(pet.name + "'s happy");
            isUnhappy = false;
        }
        else
        {
            print(pet.name + "'s excited");
            isUnhappy = false;
        }
        return isUnhappy;
    }//END method


    //allows the user to enter yes or no and returns a boolean equivalent
    public static boolean decision(String message){
        boolean yesOrNo = true;
        String response = inputString(message);
        if ((response.equals("yes") || response.equals("Yes") || response.equals("YES")))
        {
            yesOrNo = true;
        }
        else
        {
            yesOrNo = false;
        }
        return yesOrNo;
    }

    //simplify the print
    public static void print(String message)
    {
        System.out.println(message);
    }//END print

    //returns user input following output message
    public static String inputString(String message)
    {
        String response;
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);
        response = scanner.nextLine();
        return response;
    }// end input string 

}
