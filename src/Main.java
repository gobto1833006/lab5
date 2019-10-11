import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cube World definitive edition");
        primaryStage.setFullScreen(true);
        Contact contact = new Contact();


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
        homme.setSelected(true);
        Label age = new Label("Âge");
        Spinner boiteA = new Spinner(18, 150, 18);
        CheckBox conditionUtilisation = new CheckBox("J'accepte les conditions d'utilisations");
        Button inscription = new Button("S'inscrire");
        Button effacer = new Button("Effacer");
        Button retour = new Button("Retour");


        HBox hBox1 = new HBox(seConnecter, sInscrire);
        hBox1.setSpacing(5);
        VBox vBox1 = new VBox(nomUtilisateur, boiteNU, motDePasse, boiteMDP, hBox1);
        vBox1.setSpacing(10);

        HBox hBox2 = new HBox(homme, femme, autre);
        hBox2.setSpacing(5);
        HBox hBox3 = new HBox(inscription, effacer, retour);
        hBox3.setSpacing(5);
        VBox vBox2 = new VBox(prenom, boiteP, nomDeFamille, boiteNDF, username, boiteU, password, boitePW, cPassword, boiteCPW, genre, hBox2, age, boiteA, conditionUtilisation, hBox3);
        vBox2.setSpacing(10);

        Group group1 = new Group(vBox1);
        group1.setTranslateX(800);
        group1.setTranslateY(400);
        Group group2 = new Group(vBox2);
        group2.setTranslateX(800);
        group2.setTranslateY(200);
        Scene scene = new Scene(group1);


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
            gestionMotDePasse(boitePW, contact);
        }));
        boiteCPW.textProperty().addListener(((observable, oldValue, newValue) -> {
            gestionMotDePasse(boiteCPW, contact);
        }));

        toggleGroup1.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {

            if (toggleGroup1.getSelectedToggle()!=null){

                contact.setDonne("Genre",toggleGroup1.getSelectedToggle().getUserData().toString());
                System.out.println(contact.getDonne("Genre"));
            }
        });
        boiteA.valueProperty().addListener((observable, oldValue, newValue) -> {
            contact.setDonne("Âge",Integer.toString((int)boiteA.getValue()));
            System.out.println(contact.getDonne("Âge"));
        });




        retour.setOnAction(event -> {
            scene.setRoot(group1);
        });


        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public void gestionBoite(TextField textField, Contact contact) {

        contact.setDonne(textField.getPromptText(), textField.getText());
        System.out.println(textField.getPromptText() + " " + contact.getDonne(textField.getPromptText()));
    }

    public void gestionMotDePasse(PasswordField passwordField, Contact contact) {

        contact.setDonne(passwordField.getPromptText(), passwordField.getText());
    }
}
