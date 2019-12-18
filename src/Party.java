public class Party {
    private String partyName;
    private String partyLeader;
    private  String partyGenre;
    private int numLocalReps;


    /**
     *
     * @param partyName is the name of the party
     * @param partyLeader is the name of the partys leader
     * @param partyGenre is the genre of the party
     * @param numLocalReps the amount of local reps the party has
     */
    public Party(String partyName, String partyLeader, String partyGenre, int numLocalReps) {
        this.partyName = partyName;
        this.partyLeader = partyLeader;
        this.partyGenre = partyGenre;
        this.numLocalReps = numLocalReps;
    }

    public  String getPartyName() {
        return partyName;
    }

    public String getPartyGenre() {
        return partyGenre;
    }

    public int getNumLocalReps() {
        return numLocalReps;
    }

    public void setPartyName(String partyName) {
        this.partyName = Utilities.max30Chars(partyName);
    }


    public void setPartyGenre(String partyGenre) {
            this.partyGenre = Utilities.validGenre(partyGenre);
    }

    public void setNumLocalReps(int numLocalReps) {
        if(Utilities.validIntNonNegative(numLocalReps))
            this.numLocalReps = numLocalReps;
    }

    public String getPartyLeader() {
        return partyLeader;
    }

    @Override
    public  String toString() {
        return "Party{" +
                "partyName='" + partyName + '\'' +
                ", partyLeader='" + partyLeader + '\'' +
                ", partyGenre='" + partyGenre + '\'' +
                ", numLocalReps=" + numLocalReps +
                '}';
    }
}

