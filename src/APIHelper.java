
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anan
 */
public class APIHelper {
    
    public static String urlResponse (String URLstring) {
        try {
            URL url = new URL(URLstring);
            BufferedReader in;
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            String check = in.readLine();
            return check;
        } catch (IOException ex) {
            return "@ERROR";
        }
        
    }
    
    public static boolean createExperts (String user, String pass, String email) {
        String url ="https://khandokeranan.com/projects/academyflix/createExperts.php?u="+user+"&p="+pass+"&e="+email;
        System.out.println(url);
        String res = urlResponse(url);
        if (!res.equals("@ERROR")) {
            JSONArray jsonarr = new JSONArray(res);
            JSONObject jso = (JSONObject) jsonarr.getJSONObject(0);
            String name = (String) jso.get("username");
            String id = (String) jso.get("id");
            String emaild = (String) jso.get("email");  
            AuthSession.writeAuth(id, name, emaild);
            return true;
        }else {
            return false;
        }
    }
    
    public static boolean signIn (String user, String pass) {
        String url ="https://khandokeranan.com/projects/academyflix/checkid.php?u="+user+"&p="+pass;
        String res = urlResponse(url);
        if (!res.equals("@ERROR")) {
            JSONArray jsonarr = new JSONArray(res);
            JSONObject jso = (JSONObject) jsonarr.getJSONObject(0);
            String name = (String) jso.get("username");
            String id = (String) jso.get("id");
            String email = (String) jso.get("email");  
            AuthSession.writeAuth(id, name, email);
        }
        return !res.equals("@ERROR");
        
    }
    
    public static boolean createOrbit (String title, String desc, String price, String priceUnit, String visibility, String b64Img) {
        if (!AuthSession.readID().equals("@ERROR")) {
            String urlString = "https://khandokeranan.com/projects/academyflix/createOrbit.php";
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("title", title);
            parameters.put("desc", desc);
            parameters.put("price", price);
            parameters.put("priceUnit", priceUnit);
            parameters.put("vis", visibility);
            parameters.put("img", b64Img);
            parameters.put("expert_id", AuthSession.readID());
            PostRequestTask task = new PostRequestTask(urlString, parameters);
            task.setOnSucceeded(event -> {
               String response = task.getValue();
               System.out.println("Response: " + response);
            });
            new Thread(task).start();

            return true;
        }else {
            return false;
        }  
    }
    
    public static List<Orbit> checkOrbitList (String id) {
        String url = "https://khandokeranan.com/projects/academyflix/orbitlist.php?id=" + id;
        String res = urlResponse(url);
        List<Orbit> ol = new ArrayList<>();
        if (!res.equals("@ERROR")) {
            JSONArray jsonarr = new JSONArray(res);
            
            for (int i =0; i<jsonarr.length(); i++) {
                JSONObject jso = (JSONObject) jsonarr.getJSONObject(i);
                Orbit orbit = new Orbit(jso.getString("id"),jso.getString("title"),jso.getString("description"),jso.getString("price"), jso.getString("price_unit"), jso.getString("created_date"));
                ol.add(orbit);
            }
        }
        return ol;
        
    }
    
    public static Orbit viewOrbit(String oid, String id) {
        String url = "https://khandokeranan.com/projects/academyflix/viewOrbit.php?oid=" + oid + "&id=" + id;
        String res = urlResponse(url);
        if (!res.equals("@ERROR")) {
            JSONArray jsonarr = new JSONArray(res);
            JSONObject jso = (JSONObject) jsonarr.getJSONObject(0);
            Orbit orbit = new Orbit(jso.getString("id"),jso.getString("title"),jso.getString("description"),jso.getString("price"), jso.getString("price_unit"), jso.getString("created_date"));
            orbit.coverpic = jso.getString("coverpic");
            orbit.visibility = jso.getString("visibility");
            orbit.last_date = jso.getString("published_date");
            
            
            return orbit;
        }
        return null;
    }
    
    public static String deleteOrbit (String id) {
        String url = "https://khandokeranan.com/projects/academyflix/deleteOrbit.php?id=" + id + "&uid=" + AuthSession.readID();
        return urlResponse(url);
    }
    
    
    public static boolean updateOrbit (String title, String desc, String price, String priceUnit, String visibility, String b64Img) {
        if (!AuthSession.readID().equals("@ERROR") && !AuthSession.readCurrentOID().equals("@ERROR")) {
            String urlString = "https://khandokeranan.com/projects/academyflix/updateOrbit.php";
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("title", title);
            parameters.put("desc", desc);
            parameters.put("price", price);
            parameters.put("priceUnit", priceUnit);
            parameters.put("vis", visibility);
            parameters.put("img", b64Img);
            parameters.put("expert_id", AuthSession.readID());
            parameters.put("id", AuthSession.readCurrentOID());
            PostRequestTask task = new PostRequestTask(urlString, parameters);
            task.setOnSucceeded(event -> {
               String response = task.getValue();
               System.out.println("Response: " + response);
            });
            new Thread(task).start();

            return true;
        }else {
            return false;
        }  
    }
    
    public static boolean createOrbitBox (String title, String desc, String ctype, String index, String link, String thumb, String vis, String acc) {
        if (!AuthSession.readCurrentOID().equals("@ERROR")) {
            String urlString = "https://khandokeranan.com/projects/academyflix/createOrbitBox.php";
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("oid", AuthSession.readCurrentOID());
            parameters.put("content_type", ctype);
            parameters.put("index_no", index);
            parameters.put("title", title);
            parameters.put("description", desc);
            parameters.put("link", link);
            parameters.put("thumbnail", thumb);
            parameters.put("visibility", vis);
            parameters.put("accessibility", acc);
            PostRequestTask task = new PostRequestTask(urlString, parameters);
            task.setOnSucceeded(event -> {
               String response = task.getValue();
               System.out.println("Response: " + response);
            });
            new Thread(task).start();

            return true;
        }else {
            return false;
        }  
    }
}
