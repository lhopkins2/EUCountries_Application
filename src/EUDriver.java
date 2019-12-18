import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * This is the driver class and is the main entry point of the project
 * @author Liam Hopkins
 * @date 28/10/2019
 */

public class EUDriver {
    private Scanner input = new Scanner(System.in);
    private ArrayList<Country> euCountries;
    private PartyList partyList;
    public static void main(String[] args){
        new EUDriver();
    }
    public EUDriver(){
        euCountries = new ArrayList<Country>();
        partyList = new PartyList();
        runMenu();
    }
    public EUDriver(int x) {
        euCountries = new ArrayList<Country>();
        runMenu();
    }

    public void setEuCountries(ArrayList<Country> a){
        this.euCountries = a;
    }

    public PartyList getPartyList(){
        return partyList;
    }

    /**
     * runs the menu list that the user interacts with
     * @return the choice on the menu
     */

    private int mainMenu() {
        System.out.println("EU Menu");
        System.out.println("---------");
        System.out.println("  1) Add a country to EU");
        System.out.println("  2) Remove a country from the EU");
        System.out.println("  3) Update an EU country's information");
        System.out.println("  4) List all the EU Countries");
        System.out.println("  --------------------");
        System.out.println("Country Menu");
        System.out.println("  5) Add an MEP");
        System.out.println("  6) Remove an MEP");
        System.out.println("  7) Update the information on an MEP");
        System.out.println("  8) List all MEP's in this country");
        System.out.println("  --------------------");
        System.out.println("Party Menu");
        System.out.println("  9)  Add a New Party");
        System.out.println("  10) Remove a Party");
        System.out.println("  11) Update the Party Information");
        System.out.println("  12) List all parties");
        System.out.println("  --------------------");
        System.out.println("Report Menu");
        System.out.println("  13) Calculate and print the party with the most local Representatives");
        System.out.println("  14) Calculate and print the party with the most MEP's");
        System.out.println("  15) List all parties of a given Genre");
        System.out.println("  16) List all MEPs of a given party");
        System.out.println("  -------");
        System.out.println("  20) Save to XML");
        System.out.println("  21) Load from XML");
        System.out.println("  -------");
        System.out.println("  0) Exit");
        System.out.print(" ==>>");
        return input.nextInt();
    }

    /**
     * chooses which method to run
     */
     private void runMenu() {
        int option = mainMenu();
        while (option != 0) {

            switch (option) {
                case 1:
                    addCountry();
                    break;
                case 2:
                    deleteCountry();
                    break;
                case 3:
                    upDateCountry();
                    break;
                case 4:
                    System.out.println(listOfCountries());
                    break;
                    //////within a country
                case 5:
                    addMEP();
                    break;
                case 6:
                    deleteMEP();
                    break;
                case 7:
                    updateMEP();
                    break;
                case 8:
                    listMEPSofCountry();
                    break;
                    /////party menu
                case 9:
                    addNewParty();
                    break;
                case 10:
                    removeParty();
                    break;
                case 11:
                    updateParty();
                    break;
                case 12:
                    System.out.println(listOfParties());
                    break;
                    //////report menu
                case 13:
                    System.out.println(largestParty());
                    break;
                case 14:
                    mostMEPs();
                    break;
                case 15:
                    System.out.println(listPartyByGenre());
                    break;
                case 16:
                    System.out.println(listMEPsbyPartyName());
                    break;
                case 20:
                    try{
                        saveCountries();
                        savePartyList();
                    }
                    catch(Exception e){
                        System.err.println("Error writing to file: " + e);
                    }
                    break;
                case 21:
                    try{
                        loadCountries();
                        loadPartyList();
                    }
                    catch(Exception e){
                        System.err.println("Error reading from file: " + e);
                    }
                    break;
                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();

            //display the main menu again
            option = mainMenu();
        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye");
        System.exit(0);
    }

    /**
     * adds a country to the list
     */
    private void addCountry(){
        System.out.print("Enter the name of the country: ");
        String country = input.next();
        System.out.println("");
        System.out.println("Enter the amount of MEP's: ");
        int meps = input.nextInt();
        input.nextLine();
        euCountries.add(new Country(country, meps));
    }
    private void deleteCountry(){
        for(int i = 0; i < euCountries.size(); i++){
            System.out.println(i +") " + euCountries.get(i).getName());
        }
        System.out.print("Enter the index of the country you'd like to remove: ");
        int choice = input.nextInt();
        input.nextLine();
        System.out.println("");
        try{
            euCountries.remove(choice);
        }
        catch(Exception e){
            System.out.println("There is no country for this index number");
        }
    }
    private void upDateCountry(){
        for(int i = 0; i < euCountries.size(); i++){
            System.out.println(i +") " + euCountries.get(i).getName());
        }
        System.out.print("Enter the index of the country you'd like to update: ");
        int choice = input.nextInt();
        input.nextLine();
        System.out.println("");
        try{
            System.out.print("Enter the new name for the country: ");
            String countryName = input.next();
            System.out.println("");
            System.out.print("Enter the new number of MEP's: ");
            int numMeps = input.nextInt();
            input.nextLine();
            var newCountry = new Country(countryName, numMeps);
            euCountries.set(choice, newCountry);
        }
        catch(Exception e){
            System.out.println("There is no country for this index number");
        }
    }
    private String listOfCountries(){
        String str = "";
        for(Country p : euCountries){
            str = str + p;
        }
        return str;
    }
////////////////Within a country
    private void addMEP(){
        for(int i = 0; i < euCountries.size(); i++){
            Country c = euCountries.get(i);
            System.out.println(i+") " + c.getName());
        }
        System.out.println("Enter the index of the country that you'd like to add the MEP to");
        var choice = input.nextInt();
        try{
            if(partyList.getPartyList().size() > 0) {
                System.out.print("Enter the MEP name: ");
                String name = input.next();
                System.out.println("");
                System.out.print("Enter the MEP phone number: ");
                String phoneNo = input.next();
                if (!Utilities.onlyContainsNumbers(phoneNo))
                    phoneNo = "unknown";
                System.out.println("");
                System.out.print("Enter the MEP email: ");
                String email = input.next();
                if (!Utilities.validEmail(email))
                    email = "unknown";
                System.out.println("");
                System.out.print("Enter the MEP party: ");
                String party = input.next();


                var newMep = new Mep(name, email, phoneNo, Utilities.validParty(party, partyList), partyList);
                euCountries.get(choice).addMEP(newMep);
            }
            else System.out.println("You must add a party before an MEP");
        }
        catch(Exception e){
            System.out.println("There is no MEP for this index number or the party is incorrect");
        }

    }
    private void deleteMEP(){
        for(int i = 0; i < euCountries.size(); i++){
            Country c = euCountries.get(i);
            System.out.println(i+") " + c.getName());
        }
        System.out.println("Enter the index of the country that you'd like to remove the MEP from");
        try{
            euCountries.remove(input.nextInt());
            input.nextLine();
        }
        catch(Exception e){
            System.out.println("There is no MEP for this index number");
        }
    }
    private void updateMEP(){
        Country c = null;
        if(Country.numberOfMEPs() != 0){

            for(int i = 0; i < euCountries.size(); i++){
                c = euCountries.get(i);
                System.out.println(i+") " + c.getName());
            }
            System.out.print("Enter the index of the country that you'd like to remove the MEP from");
            var choice = input.nextInt();
            input.nextLine();
            System.out.println("");
            try{
                System.out.print("Enter the MEP name: ");
                String name = input.next();
                System.out.println("");
                System.out.print("Enter the MEP phone number: ");
                String phoneNo = input.next();
                if(!Utilities.onlyContainsNumbers(input.next()))
                    phoneNo = "unknown";
                System.out.println("");
                System.out.print("Enter the MEP email: ");
                String email = input.next();
                if(!Utilities.validEmail(email))
                    email = "unknown";
                System.out.println("");
                System.out.print("Enter the MEP party: ");
                String party = input.next();

                var newMep = new Mep(name, phoneNo, email, Utilities.validParty(party, partyList), partyList);
                 c.getMeps().set(choice, newMep);


            }
            catch(Exception e){
                System.out.println("There is no MEP for this index number");
            }


        }
        else System.out.println("This country has no MEP's");
    }
    private void listMEPSofCountry(){
        System.out.print("Enter the Country to list MEP's: ");
        String str = input.next();
        System.out.println("");
        try{
                for(int i = 0; i < euCountries.size(); i++){
                    Country c = euCountries.get(i);
                    if(c.getName().equalsIgnoreCase(str))
                        System.out.println(c.listOfMEPs());
            }
        }
        catch(Exception e){
            System.out.println("This country does not have any MEP's");
        }
    }
    private void addNewParty(){
        System.out.print("Enter the name of a party: ");
        var name = input.next();
        System.out.println("");
        System.out.print("Enter the party Leader: ");
        var leader = input.next();
        System.out.println("");
        System.out.print("Enter the genre: ");
        var genre = Utilities.validGenre(input.next());
        System.out.println("");
        System.out.print("Enter the number of local representatives: ");
        var localReps = input.nextInt();
        input.nextLine();
        partyList.addParty(new Party(name, leader, genre, localReps ));
    }
    private void removeParty(){
        for(int i = 0; i < partyList.getPartyList().size(); i++){
            System.out.println(i+ ") "+ partyList.getPartyList().get(i).getPartyName());
        }
        System.out.print("Enter the index of the MEP you'd like to delete: ");
        var choice = input.nextInt();
        input.nextLine();
        System.out.println("");
        try{
            partyList.getPartyList().remove(choice);
        }
        catch(Exception e){
            System.out.println("The index of that MEP does not exist");
        }
    }
    public void updateParty(){
        var str = "";
        for(int i = 0; i < partyList.getPartyList().size(); i++){
            System.out.println(i+ ") "+ partyList.getPartyList().get(i).getPartyName());
        }
        System.out.print("Select the index of the Party you'd like to update");
        int choice = input.nextInt();
        input.nextLine();
        System.out.println("");
        try{
            System.out.print("Enter the name of a party: ");
            var name = input.next();
            System.out.println("");
            System.out.print("Enter the party Leader: ");
            var leader = input.next();
            System.out.println("");
            System.out.print("Enter the genre: ");
            var genre = Utilities.validGenre(input.next());
            System.out.println("");
            System.out.print("Enter the number of local representatives: ");
            var localReps = input.nextInt();
            input.nextLine();
            var party = new Party(name, leader, genre, localReps);

            partyList.getPartyList().set(choice, party);
        }
        catch(Exception e){
            System.out.println("That index does not exist");
        }
    }

    public String listOfParties(){
        return partyList.listOfParties();
    }

    private String largestParty(){
        //return partyList.getPartyList().get(partyList.getPartyList().indexOf(largestParty())).toString();
        return partyList.largestParty().toString();
    }
    //////////////////////Reporting Menu

    private String listPartyByGenre(){
        System.out.println("Enter a genre: ");
        return partyList.listPartiesBySpecificGenre(Utilities.validGenre(input.next()));
    }

    public String listMEPsbyPartyName(){
        Party temp = null;
        System.out.println("Enter the name of the party: ");
        var name = input.next();
        for(int i = 0; i < partyList.getPartyList().size(); i++){
            if(partyList.getPartyList().get(i).getPartyName().equalsIgnoreCase(name)){
                temp = partyList.getPartyList().get(i);
            }
        }
        return partyList.listMEPsbySpecificParty(temp);

    }

    private void mostMEPs(){
         System.out.println(partyList.mostMEPs(euCountries).toString());
    }


    //////load and save
    public void loadCountries() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("country.xml"));
        euCountries = (ArrayList<Country>) is.readObject();
        is.close();
    }

    public void saveCountries() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("country.xml"));
        out.writeObject(euCountries);
        out.close();
    }

    public void loadPartyList() throws Exception {
        partyList.load();
    }

    private void savePartyList() throws Exception{
        partyList.save();
    }

    public void setPartyList(PartyList partyListPopulated) {
        partyList = partyListPopulated;
    }
}
