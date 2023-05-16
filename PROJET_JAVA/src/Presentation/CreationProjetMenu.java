package Presentation;
import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import Metier.Etape;
import Metier.Projet;
import Metier.Gestionnaires.Gestionnaire_Etape;
import Metier.Gestionnaires.Gestionnaire_Projet;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

public class CreationProjetMenu{

	private Scene scene;
	
	public CreationProjetMenu() {

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));

        Label CreateProjectLabel = new Label();
        CreateProjectLabel.setText("Creation Du Projet");
        CreateProjectLabel.setStyle("-fx-text-fill: #2c3e50; -fx-font-size: 35pt");
        
        // Création de la partie de droite

        GridPane droite = new GridPane();
        droite.setVgap(10);
        droite.setHgap(5);
        droite.setAlignment(Pos.CENTER);

        Label idLabel = new Label("Id du projet:");
        idLabel.setTextFill(Color.web("#0076a3"));
        idLabel.setFont(Font.font("Arial", 14));
        TextField idTextField = new TextField();
        idTextField.setStyle("-fx-border-color: #2c3e50;");

        Label titreLabel = new Label("Titre du projet:");
        titreLabel.setTextFill(Color.web("#0076a3"));
        titreLabel.setFont(Font.font("Arial", 14));
        TextField titreTextField = new TextField();
        titreTextField.setStyle("-fx-border-color: #2c3e50;");

        Label cneLabel = new Label("CNE d'étudiant:");
        cneLabel.setTextFill(Color.web("#0076a3"));
        cneLabel.setFont(Font.font("Arial", 14));
        TextField cneTextField = new TextField();
        cneTextField.setStyle("-fx-border-color: #2c3e50;");

        Label typeLabel = new Label("Type de projet:");
        typeLabel.setTextFill(Color.web("#0076a3"));
        typeLabel.setFont(Font.font("Arial", 14));
        
        ComboBox<String> typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll("PFA", "PFE", "Doctorat");
        typeComboBox.setStyle("-fx-background-color: white; -fx-border-color: #2c3e50; -fx-border-radius: 2px; -fx-font-size: 10pt; -fx-pref-width: 150px; -fx-pref-height: 20px;");
        typeComboBox.setValue("PFA");

        // Création de la partie de gauche

        VBox topBox = new VBox();
        topBox.setAlignment(Pos.CENTER);
        HBox gauche = new HBox();
        topBox.setSpacing(10);
        gauche.setSpacing(10);
        gauche.setAlignment(Pos.CENTER);

        Button fileButton = new Button();
        Label topButtonLabel = new Label("  Livarables : ");
        topButtonLabel.setTextFill(Color.web("#0076a3"));
        topButtonLabel.setFont(Font.font("Arial", 14));
       
        Label selectedFilesLabel = new Label();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        
        fileButton.setOnAction(event -> {
			List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
            if (selectedFiles != null && !selectedFiles.isEmpty()) {
       
                    StringBuilder sb = new StringBuilder();
                    for (File file : selectedFiles) {
                        sb.append(file.getAbsolutePath()).append("\n");
                    }
                    selectedFilesLabel.setText(sb.toString());
                
            } else {
                selectedFilesLabel.setText("No files selected.");
            }
        });
        Image image = new Image("/images/ajouterLivrable.png");
        ImageView imageView = new ImageView(image);
        
        ScrollPane scrollPane = new ScrollPane(selectedFilesLabel);
        scrollPane.setStyle("-fx-border-color: #2c3e50; -fx-max-width: 650; -fx-max-height: 200; -fx-min-width: 650; -fx-min-height: 200; -fx-background-color: white;");
        selectedFilesLabel.setStyle("-fx-border-color: #2c3e50; -fx-max-width: 650; -fx-max-height: 200; -fx-min-width: 650; -fx-min-height: 200; -fx-background-color: white;");
        fileButton.setGraphic(imageView);
        fileButton.setStyle("-fx-min-width: 100px; -fx-min-height: 100px; -fx-background-radius: 2em; -fx-max-width: 100px; -fx-max-height: 100px; -fx-background-color: #2c3e50;");
        
        // Création de la partie du bas
        
        GridPane bas = new GridPane();
        bas.setVgap(10);
        bas.setHgap(10);
        bas.setAlignment(Pos.CENTER);

		TextField idEtapeTextField = new TextField();
		idEtapeTextField.setPromptText("ID de l'etape");
        idEtapeTextField.setStyle("-fx-border-color: #2c3e50;");

		DatePicker startDatePicker = new DatePicker();
        startDatePicker.setPrefWidth(150);
		startDatePicker.setPromptText("Date debut");
        startDatePicker.setStyle("-fx-font-size: 9pt;-fx-border-color: #2c3e50; -fx-background-color: white; -fx-prompt-text-fill: #8f8f8f; -fx-text-fill: #2c3e50;");
        

        //Label delaiLabel = new Label("Dernier délai:");
        //delaiLabel.setTextFill(Color.web("#0076a3"));
        //delaiLabel.setFont(Font.font("Arial", 14));

        DatePicker delaiDatePicker = new DatePicker();
        delaiDatePicker.setPrefWidth(150);
		delaiDatePicker.setPromptText("Dernier délai");
        delaiDatePicker.setStyle("-fx-font-size: 9pt;-fx-border-color: #2c3e50; -fx-background-color: white; -fx-prompt-text-fill: #8f8f8f; -fx-text-fill: #2c3e50;");
        
        //Label etapeLabel = new Label("Étape:");
        //etapeLabel.setTextFill(Color.web("#0076a3"));
        //etapeLabel.setFont(Font.font("Arial", 14));
        TextField etapeTextField = new TextField();
		etapeTextField.setPromptText("Nom de l'etape");
        etapeTextField.setStyle("-fx-border-color: #2c3e50;");
        
        //Label texteLabel = new Label("Texte:");
        //texteLabel.setTextFill(Color.web("#0076a3"));
        //texteLabel.setFont(Font.font("Arial", 14));
        TextArea texteTextArea = new TextArea();
		texteTextArea.setPromptText("Text...");
        texteTextArea.setStyle("-fx-border-color: #2c3e50;");
        texteTextArea.setPrefWidth(400);
        texteTextArea.setPrefHeight(150);
       
        Button ajouterEtapeButton = new Button("Ajouter étape");
        ajouterEtapeButton.setStyle("-fx-background-color: #2c3e50; -fx-background-radius: 2em; -fx-text-fill: white; -fx-font-size: 10pt; -fx-pref-width: 100px; -fx-pref-height: 20px;");
       
        Button enregistrerButton = new Button("Enregistrer");
        enregistrerButton.setStyle("-fx-background-color: white;-fx-border-color: #2c3e50;-fx-border-radius: 2em ; -fx-background-radius: 2em; -fx-text-fill: #2c3e50; -fx-font-size: 10pt; -fx-pref-width: 100px; -fx-pref-height: 20px;");
       
        Button refreshButton = new Button("Actualiser");
        refreshButton.setStyle("-fx-background-color: #2c3e50; -fx-background-radius: 2em; -fx-text-fill: white; -fx-font-size: 10pt; -fx-pref-width: 150px; -fx-pref-height: 20px;");

        
        Button annulerButton = new Button("Annuler");
        annulerButton.setStyle("-fx-background-color: #2c3e50; -fx-background-radius: 2em; -fx-text-fill: white; -fx-font-size: 10pt; -fx-pref-width: 100px; -fx-pref-height: 20px;");
       
        Button goLeft = new Button();
        goLeft.setStyle("-fx-background-color: #2c3e50; -fx-background-radius: 2em; -fx-text-fill: white; -fx-font-size: 10pt; -fx-pref-width: 50; -fx-pref-height: 20px;");
      
        Button goRight = new Button();
        goRight.setStyle("-fx-background-color: #2c3e50; -fx-background-radius: 2em; -fx-text-fill: white; -fx-font-size: 10pt; -fx-pref-width: 50; -fx-pref-height: 20px;");

        Image iconLeft = new Image("/images/left.png");
        ImageView imageViewLeft = new ImageView(iconLeft);
        Image iconRight = new Image("/images/right.png");
        ImageView imageViewRight = new ImageView(iconRight);
        goLeft.setGraphic(imageViewLeft);
        goRight.setGraphic(imageViewRight);
        
       // Ajout des parties au BorderPane
       
       HBox hBoxtop = new HBox();
       hBoxtop.setAlignment(Pos.CENTER);
       hBoxtop.getChildren().add(CreateProjectLabel);

       topBox.getChildren().addAll(topButtonLabel, fileButton);
       gauche.getChildren().add(topBox);
       gauche.getChildren().add(scrollPane);  

       droite.add(idLabel, 0, 0);
       droite.add(idTextField, 1, 0);
       droite.add(titreLabel, 0, 1);
       droite.add(titreTextField, 1, 1);
       droite.add(cneLabel, 0, 2);
       droite.add(cneTextField, 1, 2);
       droite.add(typeLabel, 0, 3);
       droite.add(typeComboBox, 1, 3);

       GridPane.setMargin(idEtapeTextField, new Insets(5,5,5,20));
       GridPane.setMargin(delaiDatePicker, new Insets(5,5,5,20));
       GridPane.setMargin(startDatePicker, new Insets(5,5,5,20));
       GridPane.setMargin(etapeTextField, new Insets(5,5,5,20));
       GridPane.setMargin(texteTextArea, new Insets(5,5,5,100));
       GridPane.setMargin(ajouterEtapeButton, new Insets(10, 5, 5, 5));
       GridPane.setMargin(enregistrerButton, new Insets(10, 5, 5, 5));
       GridPane.setMargin(annulerButton, new Insets(10, 5, 5, 20));
       GridPane.setMargin(refreshButton, new Insets(10, 5, 5, 5));

       bas.add(idEtapeTextField, 0, 0);
       bas.add(etapeTextField, 1, 0);
       bas.add(startDatePicker, 2, 0);
       bas.add(delaiDatePicker, 3, 0);
       bas.add(texteTextArea, 0, 1, 4, 1);
       bas.add(ajouterEtapeButton, 0, 2);
       bas.add(enregistrerButton, 1, 2);
       bas.add(goLeft, 2, 2);
       bas.add(goRight, 3, 2);
       bas.add(annulerButton, 5, 2);
       bas.add(refreshButton, 4,2);

       root.setRight(droite);
       root.setLeft(gauche);
       root.setBottom(bas);
       root.setTop(hBoxtop);

       scene = new Scene(root, 800, 600);
//       primaryStage.setTitle("Créer un projet");
//       primaryStage.setScene(scene);
//       primaryStage.setResizable(false);
//       primaryStage.show();
     
       //ACTIONS
       enregistrerButton.setOnAction(event -> {
           String idProjet = idTextField.getText();
           String titrProjet = titreTextField.getText();
           String cneEtudiant = cneTextField.getText();
           String typeProjet = typeComboBox.getValue();
           String filespath = selectedFilesLabel.getText();
			String idEtape = idEtapeTextField.getText();
           String etapeName = etapeTextField.getText();
			Date start = Date.valueOf(startDatePicker.getValue());
			Date delai = Date.valueOf(delaiDatePicker.getValue());
           String paragraph = texteTextArea.getText();
           String lieuProjet ;
           long daysBetween = ChronoUnit.DAYS.between(start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
        		   delai.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
           
           String duree_etape = String.valueOf(daysBetween);
           float duree_projet = 0 ;
           String idProfesseur= null;
           String idLaboratoire= null;
           String idEntreprise= null;
           
        //   Date dateStart = new Date(start.getYear(),start.getMonthValue(), 1);
           
           if(typeProjet == "PFA") {
           	lieuProjet = "";
           }
           if(typeProjet == "PFE") {
           	lieuProjet = "";
           }
           else {
           	lieuProjet = "";
           }


           if (idProjet.isEmpty() || titrProjet.isEmpty() || cneEtudiant.isEmpty() 
               || typeProjet.isEmpty() || filespath.isEmpty() 
               || delai == null || etapeName.isEmpty() || paragraph.isEmpty()
				|| idEtape.isEmpty() || start == null) {

               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Error");
               alert.setContentText("Please fill all field");
               alert.showAndWait();
           }
           else{
        	   Gestionnaire_Projet gestProjet = new Gestionnaire_Projet();
        	   Gestionnaire_Etape gestEtape = new Gestionnaire_Etape();
        	   
        	   List<Etape> listEtapesDuProjet = gestEtape.getEtapesByProjet(idProjet);
        	   for(Etape etape : listEtapesDuProjet)
        	   {
        		   duree_projet = duree_projet + Float.valueOf(etape.getDuree_etape());
        	   }
        	   
        	   if(gestProjet.getProjectById(idProjet) != null) {
        		    Alert alert = new Alert(Alert.AlertType.ERROR);
	                alert.setHeaderText("Error");
	                alert.setContentText("Project already exist");
	                alert.showAndWait();
        	   }
        	   else
			   {
        		   
					gestProjet.ajouterProject(new Projet(idProjet, typeProjet, lieuProjet,
							titrProjet, start, duree_projet, cneEtudiant, null, null,null));
					
			   }
				
        	  
        	   if(gestEtape.findEtapeById(idEtape) != null && gestEtape.getEtapesByProjet(idProjet) != null) {
        		   Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText("Error");
					alert.setContentText("Etape already exist");
					alert.showAndWait();
        	   }
        	   else
			   {
        		   gestEtape.ajouterEtape(new Etape( idEtape,  duree_etape, null,  null,  paragraph));
			   }	
           }
           
           
       });


        /*   annulerButton.setOnAction(event2 -> {

               primaryStage.close();

               

           });    */
           	   refreshButton.setOnAction(event3 -> {
               idTextField.clear();
               titreTextField.clear();
               cneTextField.clear();
               texteTextArea.clear();
               idEtapeTextField.clear();
               etapeTextField.clear();
               selectedFilesLabel.setText("");
               startDatePicker.setValue(null);
               delaiDatePicker.setValue(null);
               
           });

           ajouterEtapeButton.setOnAction(event4 ->{

               //CLEAR DATA ALREADY EXIST IN INPUT FIELDS
               texteTextArea.clear();
               idEtapeTextField.clear();
               etapeTextField.clear();
               startDatePicker.setValue(null);
               delaiDatePicker.setValue(null);
            
            String idProjet = idTextField.getText();
           	String idEtape = idEtapeTextField.getText();
            String etapeName = etapeTextField.getText();
 			LocalDate start = startDatePicker.getValue();
 			LocalDate delai = delaiDatePicker.getValue();
            String paragraph = texteTextArea.getText();
            String lieuProjet ;
            long daysBetween = ChronoUnit.DAYS.between(start, delai);
            
            String duree_etape = String.valueOf(daysBetween);
               
               Gestionnaire_Etape gestEtape = new Gestionnaire_Etape();
				if(gestEtape.findEtapeById(idEtape) != null && gestEtape.getEtapesByProjet(idProjet) != null) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
	                alert.setHeaderText("Error");
	                alert.setContentText("Etape already exist");
	                alert.showAndWait();
				}
				else
				{
					gestEtape.ajouterEtape(new Etape( idEtape,  duree_etape, null,  null,  paragraph));
				}
				
               

               
           });

    
	}
 
	public Scene getScene() {
		return scene;
	}
}