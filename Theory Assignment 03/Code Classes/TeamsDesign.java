package com.example.hblpsl;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TeamsDesign {

//    public static void main(String[] args) {
//        launch();
//    }

    private List<Team> teamList = new ArrayList<>();
    private static final String TEAM_FILE = "Teams.txt";
    VBox teamBox = new VBox(10);

    public Scene createTeamScene(Stage stage, Scene scene) {

        creatNewButton();



        Label teamLabel = new Label("Welcome to Teams Page!");
        teamLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: blue;");

        Button backButton = new Button("Back to Main");
        backButton.setOnAction(e -> {
            try {
                Menu menu = new Menu();
                Scene menuScene = new Scene(new StackPane(), 1000, 800);
                Stage currentStage = (Stage) backButton.getScene().getWindow();
                currentStage.setTitle("Menu Page of PSL...!!!");
                currentStage.setScene(menu.start(currentStage, menuScene));
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        });


        Button exit = new Button("Exit");
        exit.setMaxHeight(50);
        exit.setMaxWidth(50);


            Button removeTeam = new Button("Delete Team");
        removeTeam.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
        removeTeam.setMinWidth(170);
        removeTeam.setMinHeight(40);

        Button upDate = new Button("Update");
        upDate.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
        upDate.setMinWidth(170);
        upDate.setMinHeight(40);


            Button addNewTeam = new Button("Add New Team");
        addNewTeam.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
        addNewTeam.setMinWidth(170);
        addNewTeam.setMinHeight(40);

        addNewTeam.setOnAction(e -> {
            try{
                Stage currrentStage = (Stage) addNewTeam.getScene().getWindow();
                currrentStage.setTitle("Add New Team...!!!");
                Scene addNewTeamScene = new Scene(new StackPane(), 1000, 800);
                currrentStage.setScene(addNewTeam(currrentStage,addNewTeamScene));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

            HBox hBox = new HBox(10);
            hBox.setMaxWidth(200);
            hBox.setMaxHeight(100);
            hBox.getChildren().addAll(backButton,exit);
            hBox.setAlignment(Pos.BASELINE_CENTER);




        teamBox.setAlignment(Pos.CENTER);
        teamBox.getChildren().addAll(addNewTeam,removeTeam,upDate,hBox);



            Image teamImage = new Image(getClass().getResource("/Images/team0.jpg").toExternalForm());
            ImageView imageView5 = new ImageView(teamImage);
            imageView5.setPreserveRatio(false);
            imageView5.fitHeightProperty().bind(stage.heightProperty());
            imageView5.fitWidthProperty().bind(stage.widthProperty());

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(imageView5,teamBox);


        return new Scene(stackPane,1000,800);
    }

    public Scene addNewTeam(Stage stage, Scene scene) {

        Label label1 = new Label("Team Name: ");
        TextField textField = new TextField();
        label1.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        HBox hBox = new HBox(36);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(label1, textField);

        Label label2 = new Label("Head Coach: ");
        TextField textField1 = new TextField();
        label2.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        HBox hBox1 = new HBox(36);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(label2, textField1);


        Label label3 = new Label("Batting Coach: ");
        TextField textField2 = new TextField();
        label3.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        HBox hBox2 = new HBox(21);
        hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(label3, textField2);

        Label label4 = new Label("Bowling Coach: ");
        TextField textField3 = new TextField();
        label4.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        HBox hBox3 = new HBox(14);
        hBox3.setAlignment(Pos.CENTER);
        hBox3.getChildren().addAll(label4, textField3);

        Label label5 = new Label("Team Owner: ");
        TextField textField4 = new TextField();
        label5.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        HBox hBox4 = new HBox(30);
        hBox4.setAlignment(Pos.CENTER);
        hBox4.getChildren().addAll(label5, textField4);

        Label label6 = new Label("Home City: ");
        ComboBox<String> cityBox = new ComboBox<>();
        cityBox.getItems().addAll("Lahore", "Multan", "Karachi", "Peshawar", "Quetta", "Islamabad");
        cityBox.setPromptText("Select City");
        label6.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        HBox hBox5 = new HBox(47);
        hBox5.setAlignment(Pos.CENTER);
        hBox5.getChildren().addAll(label6,cityBox);



        Button saveButton = new Button("Save");

        Button backButton = new Button("Back");

        saveButton.setOnAction(e -> {
            String teamName = textField.getText();
            String captainName="Hello";
            String headCoach = textField1.getText();
            String battingCoach = textField2.getText();
            String bowlingCoach = textField3.getText();
            String teamOwner = textField4.getText();
            String teamCity = cityBox.getValue();
            Team tempTeam = new Team(teamName,captainName,headCoach,bowlingCoach,battingCoach,teamOwner,teamCity,0,0,0,0);
            teamList.add(tempTeam);

            File file = new File("Teams.txt");

            if(file.exists()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))){
                        writer.write(tempTeam.addToFile());
                        writer.newLine();
                } catch(IOException e1){
                    System.out.println(e1.getMessage());
                }
            }


        });

        backButton.setOnAction(e -> {
            Stage currrentStage = (Stage) backButton.getScene().getWindow();
            currrentStage.setTitle("Add New Team...!!!");
            Scene addNewTeamScene = new Scene(new StackPane(), 1000, 800);
            currrentStage.setScene(createTeamScene(currrentStage,addNewTeamScene));
        });


        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(hBox,hBox1,hBox2,hBox3,hBox4,hBox5,saveButton,backButton);


        return new Scene(vBox,1000,800);


    }


    private void creatNewButton() {
        teamBox.getChildren().clear();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(TEAM_FILE));
            String line;
            while((line = reader.readLine()) != null) {
                String[] team = line.split(",");
                String teamName = team[0];

                Button newTeamButton = new Button(teamName);
                newTeamButton.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
                newTeamButton.setMinWidth(170);
                newTeamButton.setMinHeight(40);
                newTeamButton.setStyle("-fx-background-color: Green; -fx-text-fill: white;");

                newTeamButton.setOnAction(e -> {
                    try{
                        TeamDetails teamDetails = new TeamDetails();
                        Stage currentStage = (Stage) newTeamButton.getScene().getWindow();
                        currentStage.setTitle("Team " + teamName + "...!!!");
                        Scene teamDetailScene = new Scene(new StackPane(), 1000, 800);
                        currentStage.setScene(teamDetails.createTeamDetailsScene(currentStage,teamDetailScene,teamName));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });



                teamBox.getChildren().add(newTeamButton);
            }
            reader.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


}
