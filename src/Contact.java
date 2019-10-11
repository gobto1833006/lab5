import java.util.HashMap;

public class Contact {
    private String prenom="Tommy";
    private  String nomDeFamille="Gobeil";
    private String username="CarTon";
    private String motDePasse="123";
    private String cMotDePasse="123";
    private String genre="Homme";
    private String age="18";
    private String condition="true";
    HashMap<String,String> hashMap=new HashMap<>();

    public Contact(/**String prenom, String nomDeFamille, String username, String motDePasse, int genre, int age*/) {
        hashMap.put("Prénom",prenom);
        hashMap.put("Nom de famille",nomDeFamille);
        hashMap.put("Nom d'utilisateur",username);
        hashMap.put("Mot de passe",motDePasse);
        hashMap.put("Mot de passe ",cMotDePasse);
        hashMap.put("Genre",genre);
        hashMap.put("Âge",age);
        hashMap.put("Condition",condition);
    }


    public String getDonne(String key) {
        return hashMap.get(key);
    }

    public void setDonne(String key,String donne){
        hashMap.replace(key,donne);
    }
}
