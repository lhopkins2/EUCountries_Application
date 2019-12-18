import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import java.util.ArrayList;


public class EUDriverTest {
        EUDriver euDriver;
        PartyList partyListPopulated, partyListEmpty;
        Party labourParty, fineGaelParty, fiannafailParty, sinnFeinParty, greenParty;
        Mep mepOne, mepTwo, mepThree, mepFour, mepFive, ukmepOne, ukmepTwo;
        Country ireland, uk;

        @Before
        public void setup() {
           euDriver  = new EUDriver(1);

            labourParty = new Party("Labour", "Brendan Howlin", "left", 23);
            fineGaelParty = new Party("Fine Gael", "Leo Varadker", "right", 60);
            fiannafailParty = new Party("Fianna Fail", "Michael Martin", "centre", 45);
            sinnFeinParty = new Party("Sinn Fein", "Mary-Lou MacDonald", "left", 45);
            greenParty = new Party("Green Party", "Eamon Ryan", "left", 20);

            partyListPopulated = new PartyList();
            partyListPopulated.addParty(labourParty);
            partyListPopulated.addParty(fineGaelParty);
            partyListPopulated.addParty(fiannafailParty);
            partyListPopulated.addParty(sinnFeinParty);


            //MEP Test Data
            mepOne = new Mep("Ciaran Cuffe", "ciaran.cuffe@ep.europa.eu", "232523", greenParty, partyListPopulated);
            mepTwo = new Mep("Frances Fitzgerald", "frances.fitzgerald@ep.europa.eu", "4323453", fineGaelParty,partyListPopulated);
            mepThree = new Mep("Matt Carthy", "matt.carthy@ep.europa.eu", "2325654", labourParty, partyListPopulated);
            mepFour = new Mep("Billy Kelleher", "billy.kelleher@ep.europa.eu", "874422", fiannafailParty, partyListPopulated);
            mepFive = new Mep("Sean Kelly", "sean.kelly@ep.europa.eu", "874322", fineGaelParty, partyListPopulated);
            //Setting up a Populated List

            euDriver.setPartyList(partyListPopulated);

            //Setting up an Empty List
            partyListEmpty = new PartyList();
        }
        @Test
        public void listMEPsbyPartyName() {

            ukmepOne = new Mep("Rory Palmer", "office@rorypalmer.org", "922 9717", labourParty, partyListPopulated);
            ukmepTwo = new Mep("Jackie Jones", "office@jackiejones.org", "922 9718", labourParty, partyListPopulated);
            //Party Test Data

            ireland = new Country("Ireland", 11);

            ireland.addMEP(mepOne);
            ireland.addMEP(mepTwo);
            ireland.addMEP(mepThree);
            ireland.addMEP(mepFour);
            ireland.addMEP(mepFive);
            uk = new Country("United Kingdom", 71);
            uk.addMEP(ukmepOne);
            uk.addMEP(ukmepTwo);

            ArrayList<Country> euCountries;
            euCountries = new ArrayList<Country>();

            euCountries.add(ireland);
            euCountries.add(uk);
            euDriver.setEuCountries(euCountries);
            System.out.println(euCountries);
            //listing an empty list
            System.out.println(euDriver.getPartyList());
            //////edit
         //System.out.println(euDriver.listMEPsbyPartyName("Labour", partyListPopulated));
           assertNotNull(Utilities.validParty("Labour", partyListPopulated));
           //assertTrue((euDriver.listMEPsbyPartyName("anything", partyListPopulated)).contains("There are no"));
          //assertEquals(3, euDriver.noMEPSBySpecificParty("Labour"));

        }

}
