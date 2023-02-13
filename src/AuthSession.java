import java.util.prefs.Preferences;

/**
 *
 * @author anan
 */
public class AuthSession {
    final static String idPref = "id";
    final static String emailPref = "email";
    final static String namePref = "name";
    final static String currentOrbitBoxID = "oid";
    static Preferences prefs = Preferences.userNodeForPackage(Main.class);
    
    public static void writeAuth (String userID, String username, String email) {
        prefs.put(idPref, userID);
        prefs.put(emailPref, email);
        prefs.put(namePref, username);
    }

    public static String readID() {
        return prefs.get(idPref, "@ERROR");
    }
    
    public static String readEmail() {
        return prefs.get(emailPref, "@ERROR");
    }
    
    public static String readName() {
        return prefs.get(namePref, "@ERROR");
    }
    
    public static void writeCurrentOID (String id) {
        prefs.put(currentOrbitBoxID, id);
    }
    
    public static String readCurrentOID () {
        return prefs.get(currentOrbitBoxID, "@ERROR");
    }
    
    public static void logout() {
        prefs.put(idPref, "@ERROR");
        prefs.put(emailPref, "@ERROR");
        prefs.put(namePref, "@ERROR");
    }
}
