import java.util.Scanner;
import java.util.Arrays;

class pet
{
    String name;
}

public class test {
    public static void main(String[] args)
    {
        pet pet1= new pet();
        pet1.name = "Lexi";
        String[] winOrLost = new String[0];
        String[] name = new String[0];
        String[] tempScore = new String[1];
    
        tempScore= runGame(true,2,pet1);
        System.out.println(Arrays.toString(tempScore));
        System.out.println(tempScore.length);
        name = updateName(name, tempScore);
        System.out.println(Arrays.toString(name));
    }

    public static String[] updatewinOrLost(String[] arrScore,String[] arrName, String score)
    {
        for (int i=0; i<arrScore.length; i++)
        {
            if (arrScore[i].equals(arrName[i]))
            {
                String oldVal = arrScore[i];
                int value = Integer.parseInt(oldVal);
                value +=1;
                oldVal = Integer.toString(value);
                arrScore[i] = oldVal;
            }
        }
        appendToArray(arrScore, score);
        return arrScore;
    }


    //allows string to be appended to end of array
    public static String[] appendToArray (String[] oldArray, String newString)
    {
        String[] newArray = Arrays.copyOf(oldArray, oldArray.length+1);
        newArray[oldArray.length] = newString;
        return newArray;
    }
        
    public static String[] updateName(String[] arr, String[] tempScore)
    {
        for (int i=0; i<=tempScore.length; i++)
        {
            if (tempScore[0].equals(arr[i]))
            {
                return arr;
            }
        }
        appendToArray(arr, tempScore[0]);
        return arr;
    }
    //first level of the game 
    public static String[] runGame(boolean haveLost, int count, pet pet1){
        String[] tempScore = new String[2];
        if ((haveLost==true) && (count ==2))
        {
            System.out.println("You lost this level as your pet was hungry/happy for 2 rounds or more.");
            System.out.println("better luck next time");
            tempScore[0] = getName(pet1);
            tempScore[1] = "loss";
        }
        else
        {
            System.out.println("You won this Level!");
            tempScore[0] = getName(pet1);
            tempScore[1] = "won";
        }
        return tempScore;
    }

    public static String[] replaceArrayValue(String[] winOrLost, String[] tempScore)
    {
        if (tempScore[1].equals("loss"))
        {
        winOrLost = appendToArray(winOrLost, "0");
        }
        else
        {
        winOrLost = appendToArray(winOrLost, "1");
        }
        return winOrLost;
    }
    
    public static String getName(pet pet)
    {
        return pet.name;
    }


}
