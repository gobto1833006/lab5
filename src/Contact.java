import java.util.ArrayList;
import java.util.HashMap;

public class Contact {
    private String prenom = "";
    private String nomDeFamille = "";
    private String username = "";
    private String motDePasse = "";
    private String cMotDePasse = "";
    private String genre = "";
    private String age = "18";
    private String condition = "false";
    HashMap<String, String> hashMap = new HashMap<>();


    public Contact(/**String prenom, String nomDeFamille, String username, String motDePasse, int genre, int age*/) {
        hashMap.put("Prénom", prenom);
        hashMap.put("Nom de famille", nomDeFamille);
        hashMap.put("Nom d'utilisateur", username);
        hashMap.put("Mot de passe", motDePasse);
        hashMap.put("Mot de passe ", cMotDePasse);
        hashMap.put("Genre", genre);
        hashMap.put("Âge", age);
        hashMap.put("Condition", condition);

    }


    public String getDonne(String key) {
        return hashMap.get(key);
    }

    public void setDonne(String key, String donne) {
        hashMap.replace(key, donne);
    }

    public String verification(ArrayList<String[]> tableauUsername) {
        if (!(verificationToutRentré().equals(""))) {
            return "Veuillez rentrer une information à la case: " + verificationToutRentré();
        } else if (!(hashMap.get("Mot de passe").equals(hashMap.get("Mot de passe ")))) {
            return "La confirmation du mot de passe n'est pas identique au mot de passe";
        } else if (hashMap.get("Condition").equals("false")) {
            return "Veuillez accepter les conditions d'utilisation";
        } else if (usernamePris(hashMap.get("Nom d'utilisateur"), tableauUsername)) {
            return "Ce nom d'utilisateur existe déjà";
        } else return "";

    }

    private String verificationToutRentré() {
        if (hashMap.get("Prénom").equals("")) {
            return "Prénom";
        } else if (hashMap.get("Nom de famille").equals("")) {
            return "Nom de famille";
        } else if (hashMap.get("Nom d'utilisateur").equals("")) {
            return "Nom d'utilisateur";
        } else if (hashMap.get("Mot de passe").equals("")) {
            return "Mot de passe";
        } else if (hashMap.get("Mot de passe ").equals("")) {
            return "Confirmer le mot de passe";
        } else if (hashMap.get("Genre").equals("")) {
            return "Genre";
        } else return "";
    }

    public void effacer() {
        setDonne("Prénom", "");
        setDonne("Nom de famille", "");
        setDonne("Nom d'utilisateur", "");
        setDonne("Mot de passe", "");
        setDonne("Mot de passe ", "");
        setDonne("Genre", "");
        setDonne("Âge", "18");
        setDonne("Condition", "false");
    }

    public String outputDonne() {
        String output = getDonne("Prénom") + "," + getDonne("Nom de famille") + "," + getDonne("Nom d'utilisateur") + "," + getDonne("Mot de passe") + "," + getDonne("Mot de passe ") + "," + getDonne("Genre") + "," + getDonne("Âge") + "," + getDonne("Condition") + "\n";
        return output;
    }

    public boolean usernamePris(String username, ArrayList<String[]> tableauContact) {
        boolean bool = false;
        for (int i = 0; i < tableauContact.size(); i++) {
            if (username.equals(tableauContact.get(i)[0])) {
                bool = true;
            }
        }
        return bool;
    }
}
