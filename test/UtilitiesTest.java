import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class UtilitiesTest {
    EUDriver euDriver;
    PartyList partyListPopulated, partyListEmpty;
    Party labourParty, fineGaelParty, fiannafailParty, sinnFeinParty, greenParty;
    Mep mepOne, mepTwo, mepThree, mepFour, mepFive, ukmepOne, ukmepTwo;
    Country ireland, uk;

    @Before
    public void setUp() throws Exception {
        euDriver  = new EUDriver(1);

        labourParty = new Party("Labour", "Brendan Howlin", "left", 23);
        fineGaelParty = new Party("Fine Gael", "Leo Varadker", "right", 60);
        fiannafailParty = new Party("Fianna Fail", "Michael Martin", "centre", 45);
        sinnFeinParty = new Party("Sinn Fein", "Mary-Lou MacDonald", "left", 45);
        greenParty = new Party("Green Party", "Eamon Ryan", "left", 20);
        //MEP Test Data


        //Setting up a Populated List
        partyListPopulated = new PartyList();
        partyListPopulated.addParty(labourParty);
        partyListPopulated.addParty(fineGaelParty);
        partyListPopulated.addParty(fiannafailParty);
        partyListPopulated.addParty(sinnFeinParty);

        mepOne = new Mep("Ciaran Cuffe", "ciaran.cuffe@ep.europa.eu", "232523", greenParty, partyListPopulated);
        mepTwo = new Mep("Frances Fitzgerald", "frances.fitzgerald@ep.europa.eu", "4323453", fineGaelParty,partyListPopulated);
        mepThree = new Mep("Matt Carthy", "matt.carthy@ep.europa.eu", "2325654", sinnFeinParty, partyListPopulated);
        mepFour = new Mep("Billy Kelleher", "billy.kelleher@ep.europa.eu", "874422", fiannafailParty, partyListPopulated);
        mepFive = new Mep("Sean Kelly", "sean.kelly@ep.europa.eu", "874322", fineGaelParty, partyListPopulated);
        euDriver.setPartyList(partyListPopulated);

        //Setting up an Empty List
        partyListEmpty = new PartyList();
    }
    @After
    public void tearDown() throws Exception {
        partyListPopulated = partyListEmpty = null;
    }



    @Test
    public void onlyContainsNumbers() {
        assertTrue(Utilities.onlyContainsNumbers("1234"));
        assertFalse(Utilities.onlyContainsNumbers("1aa234"));
    }

    @Test
    public void max30Chars() {
        assertEquals("1234567890", Utilities.max30Chars("1234567890"));
        assertEquals("123456789012345678901234567890", Utilities.max30Chars("123456789012345678901234567890-123"));
        assertEquals("", Utilities.max30Chars(""));
    }

    @Test
    public void validEmail() {
        assertTrue(Utilities.validEmail("mmeagher@wit.ie"));
        assertFalse(Utilities.validEmail("mmeagherwit.ie"));
        assertFalse(Utilities.validEmail("mmeagher@witie"));
        assertFalse(Utilities.validEmail("mmeagherwitie"));
        assertFalse(Utilities.validEmail(""));
    }

    @Test
    public void validIntRange() {
        assertTrue(Utilities.validIntRange(1, 10, 5));
        assertTrue(Utilities.validIntRange(1, 10, 1));
        assertTrue(Utilities.validIntRange(1, 10, 10));
        assertFalse(Utilities.validIntRange(1, 10, 0));
        assertFalse(Utilities.validIntRange(1, 10, 11));

    }

    @Test
    public void validIntNonNegative() {
        assertTrue(Utilities.validIntNonNegative(0));
        assertTrue(Utilities.validIntNonNegative(1));
        assertTrue(Utilities.validIntNonNegative(1000));
        assertFalse(Utilities.validIntNonNegative(-1));


    }

    @Test
    public void validGenre() {
        assertEquals("LEFT", Utilities.validGenre("LEFT"));
        assertEquals("LEFT", Utilities.validGenre("LefT"));

        assertEquals("EXTREME LEFT", Utilities.validGenre("EXTREME LEFT"));
        assertEquals("EXTREME LEFT", Utilities.validGenre("EXTReme LEFT"));

        assertEquals("RIGHT", Utilities.validGenre("RIGHT"));
        assertEquals("RIGHT", Utilities.validGenre("RigHT"));

        assertEquals("EXTREME RIGHT", Utilities.validGenre("EXTREME RIGHT"));
        assertEquals("EXTREME RIGHT", Utilities.validGenre("EXTREME RigHT"));

        assertEquals("CENTRE", Utilities.validGenre("CENTRE"));
        assertEquals("CENTRE", Utilities.validGenre("CENtrE"));
        assertEquals("UNCATEGORISED", Utilities.validGenre("UNCATEGORISED"));
        assertEquals("UNCATEGORISED", Utilities.validGenre("uncategorISEd"));

        assertEquals("unknown", Utilities.validGenre("not political"));
        assertEquals("unknown", Utilities.validGenre("NOT"));

    }

    @Test
    public void validParty() {
        assertEquals(labourParty, Utilities.validParty("Labour", partyListPopulated));
        assertNull(Utilities.validParty("LaboIIr", partyListPopulated));
        assertEquals(fineGaelParty, Utilities.validParty("Fine Gael", partyListPopulated));


    }

    @Test
    public void validIndex() {
        ArrayList<Mep> meps = new ArrayList<Mep>();
        meps.add(mepOne);
        meps.add(mepTwo);
        meps.add(mepThree);
        meps.add(ukmepOne);
        assertTrue(Utilities.validIndex(2, meps));
        assertTrue(Utilities.validIndex(0, meps));
        assertFalse(Utilities.validIndex(-1, meps));
        assertTrue(Utilities.validIndex(3, meps));
        assertFalse(Utilities.validIndex(4, meps));
    }
}