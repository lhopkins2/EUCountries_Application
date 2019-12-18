import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * this is a class that directly interatcs with the driver and also acceses the party class
 */
public class PartyList {
    private ArrayList<Party> parties;
    private Country country;
    public PartyList() {
        parties = new ArrayList<Party>();
    }

    public ArrayList<Party> getPartyList(){
        return parties;
    }
    public void addParty(Party party){
        parties.add(party);
    }

    public Party getParty(int a){
        return parties.get(a);
    }

    public boolean removeParty(int a){
        try{
            parties.remove(a);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public int numberOfParties(){
        return parties.size();
    }

    public String listOfParties(){
        String str = "";
        for(Party p : parties){
            str = str + p;
        }
        return str;
    }

    @Override
    public String toString() {
        return "PartyList{" +
                "parties=" + parties +
                '}';
    }
    public String listPartiesBySpecificGenre(String str){
        String result = "";
        for(int i = 0; i < parties.size(); i++){
            if(parties.get(i).getPartyName().equalsIgnoreCase(str))
                result = result + parties.get(i).getPartyName();
        }
        return result;
    }
    public Party largestParty(){
        int temp = parties.get(0).getNumLocalReps();
        Party party = parties.get(0);
        for(int i = 0; i < parties.size(); i++){
            if(parties.get(i).getNumLocalReps() > temp)
                party = parties.get(i);
                temp = parties.get(i).getNumLocalReps();

        }
        return party;
    }

    public String listMEPsbySpecificParty(Party party){
            return country.listOfMEPsByParty(party);
        }


    public Party mostMEPs(ArrayList<Country> euCountries){
        Party temp = euCountries.get(0).getMeps().get(0).getMepParty();
        for(int i = 0; i < euCountries.size(); i++){
            if(euCountries.get(0).noOfMEPsByParty(temp) < euCountries.get(0).noOfMEPsByParty(euCountries.get(i).getMeps().get(i).getMepParty()))
                temp = euCountries.get(i).getMeps().get(i).getMepParty();
        }
        return temp;
    }

    public void load() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("partylist.xml"));
        parties = (ArrayList<Party>) is.readObject();
        is.close();
    }

    public void save() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("partylist.xml"));
        out.writeObject(parties);
        out.close();
    }
}
