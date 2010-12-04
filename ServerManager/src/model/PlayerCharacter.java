package model;

public class PlayerCharacter {

    private int charGuid;
    private int charAccount;
    private String charName;
    private int charGmLevel;
    private int charRace;
    private int charClass;
    private int charGender;
    private int charLevel;
    private int charMoney;
    private int charOnline;

    public PlayerCharacter(int charguid, int characcount, String charname, int chargmlevel, int charrace, int charclass, int chargender, int charlevel, int charmoney, int charonline)
    {
        this.charGuid = charguid;
        this.charAccount = characcount;
        this.charName = charname;
        this.charGmLevel = chargmlevel;
        this.charRace = charrace;
        this.charClass = charclass;
        this.charGender = chargender;
        this.charLevel = charlevel;
        this.charMoney = charmoney;
        this.charOnline = charonline;
    }

    public String getAllValues()
    {
        String allvalues =
                charGuid+":"+
                charAccount+":"+
                charName+":"+
                charGmLevel+":"+
                charRace+":"+
                charClass+":"+
                charGender+":"+
                charLevel+":"+
                charMoney+":"+
                charOnline;
        return allvalues;
    }

    public String getAllVariables()
    {
        String allvariables =
                "Guid:Account:Name:GmLevel:Race:Class:Gender:Level:Money:Online";
        return allvariables;
    }

    /**
     * @return the charguid
     */
    public int getCharguid() {
        return charGuid;
    }

    /**
     * @return the characcount
     */
    public int getCharaccount() {
        return charAccount;
    }

    /**
     * @return the charname
     */
    public String getCharname() {
        return charName;
    }

    /**
     * @return the chargmlevel
     */
    public int getChargmlevel() {
        return charGmLevel;
    }

    /**
     * @return the charrace
     */
    public int getCharrace() {
        return charRace;
    }

    /**
     * @return the charclass
     */
    public int getCharclass() {
        return charClass;
    }

    /**
     * @return the chargender
     */
    public int getChargender() {
        return charGender;
    }

    /**
     * @return the charlevel
     */
    public int getCharlevel() {
        return charLevel;
    }

    /**
     * @return the charmoney
     */
    public int getCharmoney() {
        return charMoney;
    }

    /**
     * @return the charonline
     */
    public int getCharonline() {
        return charOnline;
    }
}
