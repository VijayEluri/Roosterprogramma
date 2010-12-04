package connectivity;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PlayerCharacter;
import model.ShaEncrypt;

public class QueryManager {

    private final Dbmanager dbmanager;

    public QueryManager(Dbmanager dbmanager) {
        this.dbmanager = dbmanager;
    }

    public void makeAccount(String Name, String Pass, int Gmlevel, int Addon) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        dbmanager.openConnection("realmd");
        Pass = ShaEncrypt.SHA1(Name.toUpperCase()+":"+Pass.toUpperCase());
        String sql = "INSERT INTO account (username, sha_pass_hash, gmlevel, expansion)"
                + "VALUES('" + Name + "','" + Pass + "','" + Gmlevel + "','" + Addon + "');";
        System.out.println(sql);
        dbmanager.insertQuery(sql);
    }

    public List<PlayerCharacter> getCharacters()
    {
        dbmanager.openConnection("characters");
        List<PlayerCharacter> characters = new ArrayList<PlayerCharacter>();
        try {
            String sql = "SELECT * FROM characters ORDER BY name ASC";
            ResultSet result = dbmanager.doQuery(sql);
            while (result.next()) {
                if (result.getInt("guid") == 0 || result.getInt("account") == 0 || result.getString("name").equals(""))
                    continue;
                characters.add(
                        new PlayerCharacter(
                            result.getInt("guid"),
                            result.getInt("account"),
                            result.getString("name"),
                            result.getInt("gmlevel"),
                            result.getInt("race"),
                            result.getInt("class"),
                            result.getInt("gender"),
                            result.getInt("level"),
                            result.getInt("money"),
                            result.getInt("online")
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return characters;
    }
}
