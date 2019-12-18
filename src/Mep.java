public class Mep {
    private String MEPName;
    private String MEPEmail;
    private String MEPPhone;
    private Party mepParty;
    private PartyList partyListPopulated;

    /**
     *
     * @param MEPName is the name of the mep
     * @param MEPEmail is the mep's email
     * @param MEPPhone is the phone number of the mep
     * @param mepParty is the party the mep is associated with
     * @param partyListPoulated is the party list the mep is contained on
     */

    public Mep(String MEPName, String MEPEmail, String MEPPhone, Party mepParty, PartyList partyListPoulated) {
        this.MEPName = MEPName;
        this.MEPEmail = MEPEmail;
        this.MEPPhone = MEPPhone;
        this.mepParty = mepParty;
    }

    public String getMEPName() {
        return MEPName;
    }

    public void setMEPName(String MEPName) {
        this.MEPName = Utilities.max30Chars(MEPName);
    }

    public String getMEPEmail() {
        return MEPEmail;
    }

    public void setMEPEmail(String MEPEmail) {
        if(Utilities.validEmail(MEPEmail))
            this.MEPEmail = MEPEmail;
        else
            this.MEPEmail = "unknown";
    }

    public String getMEPPhone() {
        return MEPPhone;
    }

    public void setMEPPhone(String MEPPhone) {
        if(Utilities.onlyContainsNumbers(MEPPhone))
            this.MEPPhone = MEPPhone;
        else
            this.MEPPhone = "unknown";
    }

    public Party getMepParty() {
        return mepParty;
    }

    public void setMepParty(Party mepParty, PartyList partyListPopulated) {
        this.partyListPopulated = partyListPopulated;
        this.mepParty = mepParty;
    }

    @Override
    public String toString() {
        return "Mep{" +
                "MEPName='" + MEPName + '\'' +
                ", MEPEmail='" + MEPEmail + '\'' +
                ", MEPPhone='" + MEPPhone + '\'' +
                ", mepParty=" + mepParty +
                '}';
    }
}
