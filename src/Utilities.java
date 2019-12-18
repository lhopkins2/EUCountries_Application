import java.util.ArrayList;

/**
 * This is a class with methods used mostly for validation
 */
public class Utilities {
    public static boolean onlyContainsNumbers(String str){
        return str.matches("[0-9]+");
    }

    public static String max30Chars(String str){
        if(str.length() < 30)
            return str;
        else
            return str.substring(0,30);
    }
    public static boolean validEmail(String str){
        if(str.contains("@") && str.contains("."))
            return true;
        else return false;
    }
    public static boolean validIntRange(int a, int b,int  c){
        if(c > a && c < b)
            return true;
        else return false;
    }
    public static boolean validIntNonNegative(int a){
        if(a > 0) return true;
        else return false;
    }
    public static String validGenre(String str){
        if (str.equalsIgnoreCase("Right") || str.equalsIgnoreCase("Left") || str.equalsIgnoreCase("Extreme Right")
                || str.equalsIgnoreCase("Extreme Left") || str.equalsIgnoreCase("Centre") || str.equalsIgnoreCase("UNCATEGORISED"))
            return str;
        else return "unknown";
    }

    public static Party validParty(String str, PartyList partyListPopulated){

        Party party = null;
        for(int i = 0; i < partyListPopulated.numberOfParties(); i++){
            if (partyListPopulated.getParty(i).getPartyName().equals(str))
                party = partyListPopulated.getParty(i);
        }
        return party;
    }
    public static boolean validIndex(int a, ArrayList<Mep> meps){
        if(meps.size()  < a)
            return true;
        else return false;
    }

    public String toProperCase(String str){
        str.toLowerCase();
        str.substring(0,1).toUpperCase();
        return str;
    }
}
