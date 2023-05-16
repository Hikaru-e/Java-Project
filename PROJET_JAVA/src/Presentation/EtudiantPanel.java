package Presentation;

import java.io.File;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EtudiantPanel {

	private Scene scene;

	public Scene getScene() {
		return scene;
	}

	public EtudiantPanel() {
		// Titre de la fenêtre

		// Création des éléments de l'interface
		Label titreProjetLabel = new Label("TITRE :");
		titreProjetLabel.setStyle("-fx-font-weight: bold;");
		Label nomEncadrantLabel = new Label("ENCADRANT :");
		nomEncadrantLabel.setStyle("-fx-font-weight: bold;");

		TextField titreProjetTextField = new TextField();
		TextField nomEncadrantTextField = new TextField();

		ObservableList<String> items = FXCollections.observableArrayList("Etape 1", "Etape 2", "Etape 3");
		
		// Create ListView and set the data
		ListView<String> listView = new ListView<>(items);
		// Set click event handler for the ListView
		listView.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2) { // Double-click event
				String selectedApp = listView.getSelectionModel().getSelectedItem();
				openNewApplication(selectedApp);
			}
		});

		VBox root1 = new VBox(listView);
		root1.setPrefWidth(600);

		// Création des conteneurs pour les éléments
		VBox titreProjetVBox = new VBox(5, titreProjetLabel, titreProjetTextField);
		titreProjetVBox.setAlignment(Pos.CENTER);

		VBox nomEncadrantVBox = new VBox(5, nomEncadrantLabel, nomEncadrantTextField);
		nomEncadrantVBox.setAlignment(Pos.CENTER);

		GridPane etapesGridPane = new GridPane();
		etapesGridPane.setHgap(10);
		etapesGridPane.setVgap(10);
		etapesGridPane.setAlignment(Pos.CENTER);
		etapesGridPane.add(root1, 0, 0);

		VBox mainVBox = new VBox(20, titreProjetVBox, nomEncadrantVBox, etapesGridPane);
		mainVBox.setPadding(new Insets(20));
		mainVBox.setAlignment(Pos.CENTER);
		// Ajout du conteneur principal à la scène
		scene = new Scene(new BorderPane(mainVBox), 600, 400);

		// Affichage de la scène
	}
	
	private void openNewApplication(String appName) {
		// Launch a new JavaFX application here
//        System.out.println("Opening " + appName + " application...");
		Stage newStage = new Stage();
		newStage.setTitle(appName);

		// Création des labels, des champs de texte et des boutons
		Label projetLabel = new Label("Projet :");
		projetLabel.setStyle("-fx-font-weight: bold");
		projetLabel.setPrefWidth(80);

		TextField projetTextField = new TextField();
		projetTextField.setPrefWidth(300);
		projetTextField.setStyle("-fx-border-color: #2c3e50;");

		Button ajouterFichierButton = new Button("Ajouter un fichier");
		ajouterFichierButton.setStyle(
				"-fx-background-color: #2c3e50;-fx-border-color: white;-fx-border-radius: 2em ; -fx-background-radius: 2em; -fx-text-fill: white; -fx-font-size: 10pt; -fx-pref-width: 140px; -fx-pref-height: 20px;");
		ajouterFichierButton.setPrefWidth(150);

		Button supprimerFichierButton = new Button("Supprimer ce fichier");
		supprimerFichierButton.setStyle(
				"-fx-background-color: white;-fx-border-color: #2c3e50;-fx-border-radius: 2em ; -fx-background-radius: 2em; -fx-text-fill: #2c3e50; -fx-font-size: 10pt; -fx-pref-width: 140px; -fx-pref-height: 20px;");
		supprimerFichierButton.setPrefWidth(150);

		Label selectedFilesLabel = new Label();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select File");
		ajouterFichierButton.setOnAction(event -> {
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

		supprimerFichierButton.setOnAction(event -> {
			String filesList = selectedFilesLabel.getText().trim();
			if (filesList.isEmpty()) {
				return;
			}
			String[] files = filesList.split("\n");
			if (files.length > 0) {
				selectedFilesLabel.setText("");
				for (int i = 0; i < files.length - 1; i++) {
					selectedFilesLabel.setText(selectedFilesLabel.getText() + files[i] + "\n");
				}
			}
		});

		ScrollPane scrollPane = new ScrollPane(selectedFilesLabel);
		scrollPane.setStyle(
				"-fx-border-color: #2c3e50; -fx-max-width: 400; -fx-max-height: 100; -fx-min-width: 400; -fx-min-height: 100; -fx-background-color: white;");
		selectedFilesLabel.setStyle(
				"-fx-border-color: #2c3e50; -fx-max-width: 400; -fx-max-height: 100; -fx-min-width: 400; -fx-min-height: 100; -fx-background-color: white;");

		Label dateLimiteLabel = new Label("Date limite :");
		dateLimiteLabel.setStyle("-fx-font-weight: bold");
		dateLimiteLabel.setPrefWidth(80);

		DatePicker dateLimitePicker = new DatePicker();
		dateLimitePicker.setStyle("-fx-border-color: #2c3e50;");

		Label encadrantLabel = new Label("Encadrant :");
		encadrantLabel.setStyle("-fx-font-weight: bold");
		encadrantLabel.setPrefWidth(80);

		TextField encadrantTextField = new TextField();
		encadrantTextField.setPrefWidth(300);
		encadrantTextField.setStyle("-fx-border-color: #2c3e50;");

		Label descriptionLabel = new Label("Description :");
		descriptionLabel.setStyle("-fx-font-weight: bold");
		descriptionLabel.setPrefWidth(80);

		TextArea descriptionTextArea = new TextArea();
		descriptionTextArea.setPrefWidth(300);
		descriptionTextArea.setPrefHeight(100);
		descriptionTextArea.setStyle("-fx-border-color: #2c3e50;");

		Button annulerButton = new Button("Annuler");
		annulerButton.setStyle(
				"-fx-background-color: white;-fx-border-color: #2c3e50;-fx-border-radius: 2em ; -fx-background-radius: 2em; -fx-text-fill: #2c3e50; -fx-font-size: 10pt; -fx-pref-width: 100px; -fx-pref-height: 20px;");

		// Action d'annulation
		annulerButton.setOnAction(event -> {
			// Fermeture de la fenêtre
			newStage.close();
		});

		HBox annulerbox = new HBox();
		annulerbox.getChildren().addAll(annulerButton);
		annulerbox.setSpacing(20);
		annulerbox.setAlignment(Pos.CENTER_RIGHT);
		annulerbox.setPadding(new Insets(20));

		Button envoyerButton = new Button("Envoyer");
		envoyerButton.setStyle(
				"-fx-background-color: #2c3e50;-fx-border-color: white;-fx-border-radius: 2em ; -fx-background-radius: 2em; -fx-text-fill: white; -fx-font-size: 10pt; -fx-pref-width: 100px; -fx-pref-height: 20px;");

		// Action d'envoi
		envoyerButton.setOnAction(event -> {
			String filesList = selectedFilesLabel.getText().trim();
			if (!filesList.isEmpty()) {
				// Envoyer les fichiers sur le serveur ici
				System.out.println("Fichiers envoyés sur le serveur !");
			}
			// Fermeture de la fenêtre
			newStage.close();
		});

		HBox envoyerBox = new HBox();
		envoyerBox.getChildren().addAll(envoyerButton);
		envoyerBox.setSpacing(20);
		envoyerBox.setAlignment(Pos.CENTER_RIGHT);
		envoyerBox.setPadding(new Insets(20));

		// Création des boîtes pour contenir les éléments
		HBox projetHBox = new HBox(10, projetLabel, projetTextField);
		projetHBox.setAlignment(Pos.TOP_LEFT);

		HBox encadrantHBox = new HBox(10, encadrantLabel, encadrantTextField);
		encadrantHBox.setAlignment(Pos.TOP_LEFT);

		VBox topVBox = new VBox(10, ajouterFichierButton, supprimerFichierButton);
		topVBox.setAlignment(Pos.TOP_RIGHT);

		HBox scrollHBox = new HBox(10, scrollPane, topVBox);
		scrollHBox.setAlignment(Pos.TOP_LEFT);

		HBox dateLimiteHBox = new HBox(10, dateLimiteLabel, dateLimitePicker);
		dateLimiteHBox.setAlignment(Pos.TOP_LEFT);
		HBox envann = new HBox(250, envoyerBox, annulerbox);
		VBox vBox = new VBox(10, scrollHBox, projetHBox, encadrantHBox, dateLimiteHBox, descriptionLabel,
				descriptionTextArea, envann);
		vBox.setPadding(new Insets(20));
		vBox.setAlignment(Pos.CENTER_LEFT);

		// Création de la scène et affichage de la fenêtre
		Scene scene = new Scene(vBox, 600, 400);
		newStage.setScene(scene);
		newStage.show();
		// Add your code to launch the new application
	}

}