import java.util.ArrayList;
import java.util.Scanner;

public class Loops {

    ArrayList<String> strings;
    Scanner input = new Scanner(System.in);

    public static void main(String args[]){
        Loops loops = new Loops();
        loops.simpleWhile();
        loops.exercise_1();
        loops.exercise_2();
        loops.exercise_3();
        loops.exercise_4();
    }

    private void simpleWhile() {

        System.out.println("This method will print 1 to 10 to the console:");

        int i = 1;
        while (i <= 10){
            System.out.println(i);
            i++;
        }
    }

    private void exercise_1(){
        strings = new ArrayList<>();

        int i = 0;
        while (i < 6){
            System.out.print("Please enter a String: ");
            strings.add(input.nextLine());
            i++;
        }

        System.out.println("You entered:");
        for (int j = 0; j < 6; j++){
            System.out.println(strings.get(j));
        }
    }


    private void exercise_2(){
        strings = new ArrayList<>();

        for (int i = 0;i < 6; i ++){
            System.out.print("Please enter a String: ");
            strings.add(input.nextLine());
        }

        System.out.println("You entered:");
        for (String string : strings){
            System.out.println(string);
        }
    }


    private void exercise_3(){
        strings = new ArrayList<>();

        System.out.print("Please enter a String: ");
        String enteredData = input.nextLine();

        while (!enteredData.equals("-end")){
            System.out.print("Please enter a String: ");
            strings.add(enteredData);
            enteredData = input.nextLine();
        }

        System.out.println("You entered:");
        for (String string : strings){
            System.out.println(string);
        }
    }


    private void exercise_4(){
        strings = new ArrayList<>();
        boolean stopInputting = false;
        String enteredData = "";

        while (!stopInputting){
            System.out.print("Please enter a String: ");
            enteredData = input.nextLine();
            if (enteredData.startsWith("end")){
                stopInputting = true;
            }
            else{
                strings.add(enteredData);
            }
        }

        System.out.println("You entered:");
        for (String string : strings){
            System.out.println(string);
        }
    }

}