import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * This is a class that interacts directly with the driver and accesses the Mep class
 * @author  Liam Hopkins
 */
public class Country {
    private String name;
    private static ArrayList<Mep> meps;
    private int noMEPs;

    public Country(String name, int noMEPs) {
        this.name = name;
        this.noMEPs = noMEPs;
        meps = new ArrayList<Mep>();
    }

    public static ArrayList<Mep> getMeps(){
        return meps;
    }

    public void setMeps(ArrayList<Mep> meps) {
        Country.meps = meps;
    }

    public void addMEP(Mep mep){
        meps.add(mep);
    }

    public Mep getMEP(int a){
        return meps.get(a);
    }

    public static int numberOfMEPs(){
        return meps.size();
    }

    public boolean removeMEP(int a){
        try{
            meps.remove(a);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public static String listOfMEPs(){
        String str = "";
        for(Mep p : meps)
            str = str + p;
        return str;
    }
    public String listOfMEPsByParty(Party party){
        String str = "";
        for(Mep p : meps){
            if(getName().equalsIgnoreCase(party.getPartyName()))
                str = str + p;
        }
        return str;
    }
    public int noOfMEPsByParty(Party party){
        int temp = 0;
        for(Mep p : meps){
            if(getName().equalsIgnoreCase(party.getPartyName()))
                temp = meps.size();
        }
        return temp;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoMEPs() {
        return noMEPs;
    }

    public void setNoMEPs(int noMEPs) {
        this.noMEPs = noMEPs;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", noMEPs=" + noMEPs +
                '}';
    }

    @SuppressWarnings("unchecked")
    public static void load() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("country.xml"));
        meps = (ArrayList<Mep>) is.readObject();
        is.close();
    }

    public static void save() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("country.xml"));
        out.writeObject(meps);
        out.close();
    }
}
