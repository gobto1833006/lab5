import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Cube World definitive edition");
        primaryStage.setMinHeight(920);
        primaryStage.setMinWidth(920);
        primaryStage.setFullScreen(false);
        Contact contact = new Contact();

        File sauvegarde = new File("Sauvegarde.csv");

        Label nomUtilisateur = new Label("Nom d'utilisateur");
        TextField boiteNU = new TextField();
        boiteNU.setPromptText("Nom d'utilisateur");
        Label motDePasse = new Label("Mot de passe");
        PasswordField boiteMDP = new PasswordField();
        boiteMDP.setPromptText("Mot de passe");
        Button seConnecter = new Button("Se connecter");
        Button sInscrire = new Button("S'inscrire");


        Label prenom = new Label("Prénom");
        TextField boiteP = new TextField();
        boiteP.setPromptText("Prénom");
        Label nomDeFamille = new Label("Nom de famille");
        TextField boiteNDF = new TextField();
        boiteNDF.setPromptText("Nom de famille");
        Label username = new Label("Nom d'utilisateur");
        TextField boiteU = new TextField();
        boiteU.setPromptText("Nom d'utilisateur");
        Label password = new Label("Mot de passe");
        PasswordField boitePW = new PasswordField();
        boitePW.setPromptText("Mot de passe");
        Label cPassword = new Label("Confirmer le mot de passe");
        PasswordField boiteCPW = new PasswordField();
        boiteCPW.setPromptText("Mot de passe ");
        Label genre = new Label("Genre");
        ToggleGroup toggleGroup1 = new ToggleGroup();
        RadioButton homme = new RadioButton("Homme");
        RadioButton femme = new RadioButton("Femme");
        RadioButton autre = new RadioButton("Autre");
        homme.setUserData("Homme");
        femme.setUserData("Femme");
        autre.setUserData("Autre");
        homme.setToggleGroup(toggleGroup1);
        femme.setToggleGroup(toggleGroup1);
        autre.setToggleGroup(toggleGroup1);
        Label age = new Label("Âge");
        Spinner boiteA = new Spinner(18, 150, 18);
        CheckBox conditionUtilisation = new CheckBox("J'accepte les conditions d'utilisations");
        Button inscription = new Button("S'inscrire");
        Button effacer = new Button("Effacer");
        Button retour = new Button("Retour");
        Label messageErreur1 =new Label("");
        Label messageErreur2 = new Label("");

        ProgressIndicator progressIndicator=new ProgressIndicator();
        Label chargement=new Label("Chargement du contenu");
        chargement.setTranslateY(100);


        HBox hBox1 = new HBox(seConnecter, sInscrire);
        hBox1.setSpacing(5);
        VBox vBox1 = new VBox(nomUtilisateur, boiteNU, motDePasse, boiteMDP, hBox1);
        vBox1.setSpacing(10);

        HBox hBox2 = new HBox(homme, femme, autre);
        hBox2.setSpacing(5);
        HBox hBox3 = new HBox(inscription, effacer, retour);
        hBox3.setSpacing(5);
        VBox vBox2 = new VBox(prenom, boiteP, nomDeFamille, boiteNDF, username, boiteU, password, boitePW, cPassword, boiteCPW, genre, hBox2, age, boiteA, conditionUtilisation, hBox3, messageErreur2);
        vBox2.setSpacing(10);

        Group group1 = new Group(vBox1);
        group1.setTranslateX(400);
        group1.setTranslateY(400);
        Group group2 = new Group(vBox2);
        group2.setTranslateX(400);
        group2.setTranslateY(200);
        Scene scene = new Scene(group1);
        Group group3=new Group(progressIndicator,chargement);
        group3.setTranslateX(400);
        group3.setTranslateY(400);

        seConnecter.setOnAction(event -> {
            ArrayList<String[]> listeUsername = outUsername();
            for (int i = 0; i < listeUsername.size(); i++) {
                if (listeUsername.get(i)[0].equals(boiteNU.getText())) {
                    MessageDigest digest = null;
                    try {
                        digest = MessageDigest.getInstance("SHA-256");
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    byte[] encodedhash = digest.digest(
                            boiteMDP.getText().getBytes(StandardCharsets.UTF_8));
                    if (listeUsername.get(i)[1].equals(bytesToHex(encodedhash))) {
                        scene.setRoot(group3);
                    }

                }else{
                    messageErreur1.setText("La connexion a échouée");
                }
            }
        });


        sInscrire.setOnAction(event -> {
            scene.setRoot(group2);

        });


        boiteP.textProperty().addListener(((observable, oldValue, newValue) -> {
            gestionBoite(boiteP, contact);
        }));
        boiteNDF.textProperty().addListener(((observable, oldValue, newValue) -> {
            gestionBoite(boiteNDF, contact);
        }));
        boiteU.textProperty().addListener(((observable, oldValue, newValue) -> {
            gestionBoite(boiteU, contact);
        }));
        boitePW.textProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                gestionMotDePasse(boitePW, contact);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }));
        boiteCPW.textProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                gestionMotDePasse(boiteCPW, contact);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }));

        toggleGroup1.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {

            if (toggleGroup1.getSelectedToggle() != null) {

                contact.setDonne("Genre", toggleGroup1.getSelectedToggle().getUserData().toString());

            }
        });
        boiteA.valueProperty().addListener((observable, oldValue, newValue) -> {
            contact.setDonne("Âge", Integer.toString((int) boiteA.getValue()));

        });

        conditionUtilisation.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                contact.setDonne("Condition", "true");
            } else {
                contact.setDonne("Condition", "false");

            }
        });


        retour.setOnAction(event -> {
            scene.setRoot(group1);
        });

        inscription.setOnAction(event -> {

            ArrayList<String[]> listeUsername = outUsername();
            messageErreur2.setText(contact.verification(listeUsername));
            if (contact.verification(listeUsername).equals("")) {
                try {
                    enregistrer(contact, sauvegarde);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                boiteP.setText("");
                boiteNDF.setText("");
                boiteU.setText("");
                boitePW.setText("");
                boiteCPW.setText("");
                if (toggleGroup1.getSelectedToggle() != null) {
                    toggleGroup1.getSelectedToggle().setSelected(false);
                }
                boiteA.decrement(Integer.parseInt(boiteA.getValue().toString()) - 18);
                conditionUtilisation.setSelected(false);
                messageErreur2.setText("");
                contact.effacer();

                scene.setRoot(group1);
            }
        });

        effacer.setOnAction(event -> {
            boiteP.setText("");
            boiteNDF.setText("");
            boiteU.setText("");
            boitePW.setText("");
            boiteCPW.setText("");
            if (toggleGroup1.getSelectedToggle() != null) {
                toggleGroup1.getSelectedToggle().setSelected(false);
            }
            boiteA.decrement(Integer.parseInt(boiteA.getValue().toString()) - 18);
            conditionUtilisation.setSelected(false);
            messageErreur2.setText("");
            contact.effacer();
        });


        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public void gestionBoite(TextField textField, Contact contact) {

        contact.setDonne(textField.getPromptText(), textField.getText());

    }

    public void gestionMotDePasse(PasswordField passwordField, Contact contact) throws NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(
                passwordField.getText().getBytes(StandardCharsets.UTF_8));
        contact.setDonne(passwordField.getPromptText(), bytesToHex(encodedhash));

    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void enregistrer(Contact contact, File sauvegarde) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(sauvegarde, true));
            writer.write(contact.outputDonne());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public static ArrayList<String[]> outUsername() {
        String line;

        ArrayList<String> tableauContact = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("Sauvegarde.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                tableauContact.add(line);
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String[]> listeUsername = new ArrayList<>();
        for (int i = 0; i < tableauContact.size(); i++) {
            String[] strings = new String[2];
            strings[0] = tableauContact.get(i).split(",")[2];
            strings[1] = tableauContact.get(i).split(",")[3];
            listeUsername.add(strings);
        }
        return listeUsername;
    }
}


