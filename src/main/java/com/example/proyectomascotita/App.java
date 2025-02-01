package com.example.proyectomascotita;

//WELCOME TO TAMAGOTCHI/PET PROJECT BY PANCHITOMÁGICO (CAMILO BONILLA MILLÁN) (DISCORD @camilochys).
//I HOPE YOU ENJOY THIS PROJECT, I WILL TRY TO MAKE IT AS FUN AS POSSIBLE.
//THANK YOU, XIMO, FOR PROPOSING THIS PROJECT, I HOPE YOU LIKE IT.
//(I have added some examples commented out, so you can see how to use JavaFX and other stuff I did).
//(It is probably a little bit messy and wrong because I just started learning everything, any suggestions are greatly appreciated! <3)

import javafx.animation.FadeTransition;//Import JavaFX FadeTransition class, it is to create a fade animation transition
import javafx.application.Application;//Import JavaFX Application class, it is the main class for JavaFX applications
import javafx.scene.Scene;//Import JavaFX Scene class, it is the container for all content in a scene graph
import javafx.scene.control.Button;//Import JavaFX Button class, it is a control that triggers an event when clicked
import javafx.scene.control.Label;//Import JavaFX Label class, it is a non-editable text control
import javafx.scene.control.TextField;//Import JavaFX TextField class, it is a control that allows the user to enter a single line of unformatted text
import javafx.scene.image.Image;//Import JavaFX Image class, it is to import saved images
import javafx.scene.image.ImageView;//Import JavaFX ImageView class, it is to show and view saved images used by Image class
import javafx.scene.layout.GridPane;//Import JavaFX GridPane class, it is a layout container for managing nodes in a grid
import javafx.scene.layout.HBox;//Import JavaFX HBox class, it is a layout container for managing boxes in a single horizontal *row*
import javafx.scene.layout.VBox;//Import JavaFX VBox class, it is a layout container for managing boxes in a single vertical *column*
import javafx.stage.Stage;//Import JavaFX Stage class, it is the top level JavaFX container (equivalent to a frame)
import javafx.geometry.Pos;//Import JavaFX Pos class, necessary for alignment of elements in the scene
import javafx.scene.control.Alert;//Import JavaFX Alert class, it is a dialog that shows information to the user
import javafx.scene.control.Alert.AlertType;//Import JavaFX AlertType class, it is an enumeration of different types of alerts
import javafx.util.Duration;//Import JavaFX Duration class, it is to control the duration of effects
import java.util.Arrays;//Import Java Arrays class, it is a utility class for manipulating arrays

//App class definition, extends from Application class, necessary for JavaFX applications to be recognized as starting point of the application
public class App extends Application {

    private Stage primaryStage;//Save the primary stage reference

    private Pet pet;//Attribute to store the pet object (instance of Pet class)

    private String petName = null;//Store the pet name
    private String selectedRace = null;//Store the selected pet race

    //Reusable method to show pet status label, I did this just in case I need to change the petStatus label or something, so I can only change it here *METHODS <3*
    private void updatePetStatus(Label petStatus) {
        petStatus.setText(getPetStatus());//Update pet status label
    }

    //Method to change the color of buttons when selected
    private void changeButtonColor(Button button, String color) {
        button.setStyle("-fx-background:color " + color + "; -fx-font-size: 14px; -fx-text-fill: black; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-border-color: gray;");
    }

    @Override//Override start() method of Application class

    //start() method is the main entry point for all JavaFX applications
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;//Save the primary stage reference
        showWelcomeScreen();//Show the welcome screen
    }

    //Method to show the welcome screen
    private void showWelcomeScreen() {
        //Create label with welcome message
        Label welcomeLabel = new Label("🐾 ¡Bienvenido a Mascota Virtual/Tamagotchi v1.3! 🐾");
        welcomeLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: darkgreen; -fx-font-weight: 500; ");

        Label welcomeLabel2 = new Label("by PanchitoMágico");
        welcomeLabel2.setStyle("-fx-font-size: 14px; -fx-text-fill: darkgreen; ");

        //Create button to start playing
        Button startButton = new Button("Comenzar a jugar");
        startButton.setStyle("-fx-font-size: 16px; -fx-background-color: lightgreen; -fx-text-fill: black; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-border-color: green;");

        //Configure button action when clicked, in this case, show the pet creation screen
        startButton.setOnAction(e -> showPetCreationScreen());

        //Create VBox layout container for welcome screen elements
        VBox layout = new VBox(15);//Create a VBox layout with 10 pixels of spacing between elements
        layout.setStyle("-fx-background-color: lightgray;");

        //Add those buttons to layout (welcome message and start button)
        layout.getChildren().addAll(welcomeLabel, welcomeLabel2, startButton);

        //Create welcome scene with layout and size 500x300
        Scene welcomeScene = new Scene(layout, 500, 300);

        //Assign the welcome scene to the primary stage and show it and center elements in the scene
        primaryStage.setScene(welcomeScene);//Add scene to window
        primaryStage.setTitle("Proyecto Mascotita/Tamagotchi \uD83D\uDC3B - by PanchitoMágico");//Set window title
        primaryStage.show();//Show window
        layout.setAlignment(Pos.CENTER);//Center elements in the scene
    }

    //Method to show the pet creation screen
    private void showPetCreationScreen() {

        //Create label with instructions for pet creation screen
        Label instructionlabel = new Label("Elige la raza de tu mascota y escribe su nombre: ");
        instructionlabel.setStyle("-fx-font-size: 16px;");

        //Create text field for entering pet name and this also STORES IT, but I can not use it in other different areas
        TextField nameInput = new TextField();
        nameInput.setMaxWidth(200);
        nameInput.setMinHeight(25);
        nameInput.setStyle("-fx-font-size: 16px; -fx-background-color: white; -fx-text-fill: black; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-border-color: gray;");
        nameInput.setPromptText("Escribe el nombre aquí...");//Set placeholder text for text field

        //Set actions for race selection (MY FIRST ARRAY :D) ¿qué es un booleano? ¿qué es un array? ¿qué es un bucle? ¿qué es un if? ¿qué es un else? xdxd
        Button[] raceButtons = {
                new Button("Wanyamon"),
                new Button("Koromon"),
                new Button("Tokomon"),
                new Button("Tanemon"),
                new Button("Puttimon"),
                new Button("Tsunomon"),
                new Button("Dorimon"),
                new Button("Gigimon"),
                new Button("Panxomon")
        };

        //Create button to confirm selection
        Button confirmButton = new Button("Confirmar");

        //Set action for confirm button and save the pet name
        confirmButton.setOnAction(e -> {
            petName = nameInput.getText().trim();//Save the pet name

            //If pet name is empty, show an alert
            if (petName.isEmpty()) {
                showAlert(AlertType.ERROR,"❌ Error", "Por favor, escribe un nombre válido para tu mascota. ❌");//If pet name is empty, show an alert with error message and return from method (do not create pet)
            } else if (selectedRace == null) {
                showAlert(AlertType.ERROR,"❌ Error", "Por favor, selecciona una raza para tu mascota. ❌");//If pet race is not selected, show an alert with error message and return from method (do not create pet)
            } else {
                pet = new Pet(petName, selectedRace);//Create a new pet with selected name and race
                showAlert(AlertType.INFORMATION,"✨\uD83D\uDC3E ¡Mascota creada! \uD83D\uDC3E✨", "¡Tu mascota " + petName + " , de raza " + selectedRace + " ha sido creada con éxito!");//show alert with success message and create pet with selected name and race
                showGameScreen();//Show the game screen after creating the pet
            }
        });

        //I WILL ADD THE ARRAY BUTTONS, but fancier ;)
        //With a GridPane, yes yes
        GridPane raceGrid = new GridPane();
        raceGrid.setHgap(10);
        raceGrid.setVgap(10);
        raceGrid.setAlignment(Pos.CENTER);

        //INTs for columns and rows
        int col = 0;
        int row = 0;
        for (Button button : raceButtons) {//Iterate on every button of the array
            button.setStyle("-fx-font-size: 14px; -fx-background-color: #D3D3D3; -fx-text-fill: black; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-border-color: gray;");//ApplyButton style
            button.setPrefWidth(100);//Fixed width to 100px (perfect size)

            //Changing color for each button when selected
            button.setOnAction(e -> {
                selectedRace = button.getText();//Assign action for each button
                changeButtonColor(button, "#D3D3D3");
                for (Button otherButton : raceButtons) {
                    if (otherButton != button) {
                        changeButtonColor(otherButton, "#D3D3D3");
                    }
                }
            });

            raceGrid.add(button, col, row);//Add button to GridPane in actual position of col/row def
            //Hardest part for me, update position, col increment, when there are already 3 buttons in a column, its reset to 0 and its added +1 to start on next one
            col++;
            if (col == 3) {
                col = 0;
                row++;
            }
            //End of hard ++ lol
        }

        //Create VBox layout container for pet creation screen elements
        VBox layout = new VBox(10);//Create a VBox layout with 10 pixels of spacing between elements
        layout.getChildren().addAll(instructionlabel, nameInput);//Adds the instruction label and name input to the layout
        layout.getChildren().add(raceGrid);//I forgot to add it to the main VBox jajajaja, en fin
        layout.getChildren().add(confirmButton);//Adds the confirm button to the layout
        confirmButton.setStyle("-fx-font-size: 16px; -fx-background-color: lightgreen; -fx-text-fill: black; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-border-color: green;");//Confirm button style, so gud
        layout.setAlignment(Pos.CENTER);//Center elements in the scene (layout)
        layout.setStyle("-fx-background-color: lightgray;");

        //Create pet creation scene with layout and size 400x400
        Scene creationScene = new Scene(layout, 400, 400);
        primaryStage.setScene(creationScene);//Add scene to window
    }

    //Button Style
    private void setButtonStyle(Button button, int width, int height) {
        button.setPrefWidth(width);
        button.setPrefHeight(height);
        button.setStyle("-fx-font-size: 14px; -fx-background-color: lightgreen; -fx-text-fill: black; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-border-color: green;");
    }

    //Method to show the game screen
    private void showGameScreen() {
        if (pet == null) {
            showAlert(AlertType.ERROR, "\uD83D\uDED1 Error", "No se ha creado ninguna mascota.");//If pet is null, show an alert with error message
            return;//Return from method (do not show game screen) if pet is null (not created)
        }

        //IMAGES, i will use imgur on each
        Image petImage = new Image("file:src/main/resources/images/" + selectedRace + ".png");//Create image with selected path
        ImageView petImageView = new ImageView(petImage);//Create image view with image
        petImageView.setFitWidth(150);//Set width of image view to 100 pixels
        petImageView.setFitHeight(150);//Set height of image view to 100 pixels

        //Info labels for pet info and status
        Label petInfoLabel = new Label("¡Tu mascota " + pet.getName() + " de raza " + selectedRace + " está lista para jugar!");//Create label with pet info message
        petInfoLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
        Label statusLabel = new Label("Estado de tu mascota: ");//Create label with pet status message
        statusLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
        Label petStatus = new Label(getPetStatus());//Create label with pet status
        petStatus.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");

        //BUTTONS FOR FEED, PLAY AND REST
        Button feedButton = new Button("\uD83C\uDF56 - Alimentar");//Create button to feed pet (decrease hunger)
        Button playButton = new Button("\uD83C\uDFC0 - Jugar");//Create button to play with pet
        Button restButton = new Button("\uD83D\uDD0B - Descansar");//Create button to rest pet
        Button cleanButton = new Button("\uD83D\uDEC1 - Limpiar");//Create button to clean pet
        Button chatButton = new Button("\uD83D\uDCAC - Hablar");//Create button to chat with pet
        Button petButton = new Button("\uD83D\uDC95 - Acariciar");//Create button to pet pet xd

        //Size for all buttons
        int buttonWidth = 150;
        int buttonHeight = 50;

        //Apply style and size for each button using aux. method defined above showGameScreen
        setButtonStyle(feedButton, buttonWidth, buttonHeight);
        setButtonStyle(playButton, buttonWidth, buttonHeight);
        setButtonStyle(restButton, buttonWidth, buttonHeight);
        setButtonStyle(cleanButton, buttonWidth, buttonHeight);
        setButtonStyle(chatButton, buttonWidth, buttonHeight);
        setButtonStyle(petButton, buttonWidth, buttonHeight);

        //Assign actions for each button using methods of pet class
        feedButton.setOnAction(e -> {
            pet.feed();//Method to feed pet
            updatePetStatus(petStatus);//Update pet status label
        });

        playButton.setOnAction(e -> {
            pet.play();//Method to play with pet
            updatePetStatus(petStatus);//Update pet status label
        });

        restButton.setOnAction(e -> {
            pet.rest();//Method to rest pet
            updatePetStatus(petStatus);//Update pet status label
        });

        chatButton.setOnAction(e -> {
            pet.chat();//Method to chat with pet
            updatePetStatus(petStatus);//Update pet status label
        });

        cleanButton.setOnAction(e -> {
            pet.clean();//Method to clean pet
            updatePetStatus(petStatus);//Update pet status label
        });

        petButton.setOnAction(e -> {
            pet.pet();//Method to pet pet
            updatePetStatus(petStatus);//Update pet status label
        });

        //Main layout for game screen
        //Create VBox layout to group up the elements in the scene (buttons) and text
        VBox statusLayout = new VBox(15);//Create a VBox layout with 10 pixels of spacing between elements
        statusLayout.getChildren().addAll(petImageView, petInfoLabel, statusLabel, petStatus);//Add pet info label, status label and pet status to layout
        statusLayout.setAlignment(Pos.CENTER);//Align elements to the top left of the scene (layout)
        statusLayout.setPrefWidth(300);//Set preferred width of layout
        statusLayout.setMinWidth(300);//Set minimum width of layout
        //Add margin to layout
        statusLayout.setStyle("-fx-padding: 10px;");//Set padding of layout to 10 pixels
        statusLayout.setStyle("-fx-background-color: lightgray;");

        //Set image to 100x100 pixels and add margin to layout
        petImageView.setFitWidth(200);//Set width of image view to 100 pixels
        petImageView.setFitHeight(200);//Set height of image view to 100 pixels
        statusLayout.setStyle("-fx-padding: 10px;");//Set padding of layout to 10 pixels

        //Create GridPane for buttons (2x2x2) WOW AMAZING OMG :O :D :D INCREDIBLE :O :D :D
        //GridPane layout to group up the buttons in the scene
        GridPane buttonLayout = new GridPane();
        buttonLayout.setHgap(10);//Set horizontal gap between buttons
        buttonLayout.setVgap(10);//Set vertical gap between buttons
        buttonLayout.setAlignment(Pos.CENTER);//Center elements in the scene (layout)
        buttonLayout.addRow(3);//Set maximum rows of layout

        //Add buttons to the GridPane layout (buttons)
        //X---X---X
        //X---X---X
        //All buttons in 3x2 grid layout (3 columns, 2 rows)
        buttonLayout.add(feedButton, 0, 0);//Add feed button to layout at position
        buttonLayout.add(playButton, 1, 0);//Add play button to layout at position
        buttonLayout.add(restButton, 2, 0);//Add rest button to layout at position
        buttonLayout.add(cleanButton, 0, 1);//Add clean button to layout at position
        buttonLayout.add(chatButton, 1, 1);//Add chat button to layout at position
        buttonLayout.add(petButton, 2, 1);//Add pet button to layout at position

        //Force GridPane to expand so its visible
        buttonLayout.setMinWidth(200);//Set minimum width of layout
        buttonLayout.setMinHeight(150);//Set minimum height of layout

        //Group up everything in a VBox layout correctly
        VBox mainLayout = new VBox(50);//Create a HBox layout with 50 pixels of spacing between elements
        mainLayout.getChildren().addAll(statusLayout, buttonLayout);//Add status layout and center layout to main layout
        mainLayout.setAlignment(Pos.CENTER);//Center elements in the scene (layout)
        mainLayout.setPrefSize(700, 450);//Set preferred size of layout
        mainLayout.setStyle("-fx-background-color: lightgray;");

        //Create game scene with layout and size 700x450
        Scene gameScene = new Scene(mainLayout, 500, 700);
        primaryStage.setScene(gameScene);//Add scene to window

        //------------------------------------------------------------------------------------------------------------------
    }

    //
    private void showTemporaryChange(Label attributeLabel, int change) {
        Label tempLabel = new Label("(" + (change > 0 ? "+" : "") + change + ")");
        tempLabel.setStyle("-fx-text-fill: " + (change > 0 ? "green" : "red") + "; -fx-font-weight: bold;");

        attributeLabel.setGraphic(tempLabel);

        FadeTransition fade = new FadeTransition(Duration.seconds(5), tempLabel);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setOnFinished(event -> attributeLabel.setGraphic(null));
        fade.play();
    }

    //Method to get pet status as a string
    private String getPetStatus() {
        return "Nombre: " + pet.getName() +
                "\nRaza: " + pet.getRace() +
                "\nPersonalidad: " + pet.getTrait() +
                "\nEnergía: " + pet.getEnergy() + "/100" +
                "\nHambre: " + pet.getHunger() + "/100" +
                "\nLimpieza: " + pet.getCleanliness() + "/100" +
                "\nÁnimo: " + pet.getMood() + "/100" +
                "\nAmistad: " + pet.getFriendship() + "/100";
    }

    //Create and define showAlert method to show alerts with custom title and message
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);//Create alert with specified type (error, warning, information, etc.) (AlertType)
        alert.setTitle(title);//Set alert title
        alert.setHeaderText(null);//Set alert header text to null
        alert.setContentText(message);//Set alert content text
        alert.showAndWait();//Show alert and wait for user to close it
    }

    public static void main(String[] args) {
        launch(args);//Call start method of Application class
    }
}