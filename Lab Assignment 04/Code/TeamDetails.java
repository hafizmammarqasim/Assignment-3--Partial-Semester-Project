package com.example.hblpsl;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeamDetails {

    private static final String TEAM_FILE = "Teams.txt";
    private static final String PLAYER_FILE = "PlayerDraft.txt";
    private static final String PLAYER_IN_TEAM_FILE = "PlayersInTeams.txt";


    public Scene createTeamDetailsScene(Stage stage, Scene scene, String text) {

        VBox vBox = new VBox(10);
        Label teamLabel = new Label("Welcome to Team Details!");
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

        Button teamOverview = new Button("Team Overview");
        Button teamPlayers =new Button("Players");
        Button teamMatches = new Button("Team Matches");
        Button teamStats = new Button("Team Stats");

        teamOverview.setOnAction(e -> {
            try {
                Stage currentStage = (Stage) teamOverview.getScene().getWindow();
                Scene scene1 = new Scene(new StackPane(), 1000, 800);
                currentStage.setTitle("Team Overview...!!!");
                currentStage.setScene(teamOverviewScene(currentStage,scene1,text));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });


        teamPlayers.setOnAction(e -> {
            try {
                Stage currentStage = (Stage) teamPlayers.getScene().getWindow();
                Scene scene1 = new Scene(new StackPane(), 1000, 800);
                currentStage.setTitle("Team Players...!!!");
                currentStage.setScene(teamPlayersScene(currentStage, scene1, text));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });




        vBox.getChildren().addAll(teamLabel,backButton,teamOverview,teamPlayers,teamMatches,teamStats);
        return new Scene(vBox,1000,800);
    }

    public Scene teamOverviewScene(Stage stage, Scene scene, String text) {
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(TEAM_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] overviewList = line.split(",");
                if (overviewList[0].equals(text)) {
                    String[] firstSevenParts = new String[Math.min(7,overviewList.length)];
                    System.arraycopy(overviewList, 0, firstSevenParts, 0, firstSevenParts.length);
                    Label overViewTeam = new Label("!!!--- Team OverView ---!!!");
                    Label nameLabel = new Label("Team Name: "); Label nameValue = new Label(overviewList[0]);
                    Label captainLabel = new Label("Team Captain: "); Label captainLabelValue = new Label(overviewList[1]);
                    Label headCoachLabel = new Label("Head Coach: ");  Label headCoachValue = new Label(overviewList[2]);
                    Label battingCoachLabel = new Label("Batting Coach");   Label battingCoachValue = new Label(overviewList[3]);
                    Label bowlingCoachLabel = new Label("Bowling Coach: ");   Label bowlingCoachValue = new Label(overviewList[4]);
                    Label ownerLabel = new Label("Team Owner: "); Label ownerValue = new Label(overviewList[5]);
                    Label teamCity = new Label("Home City: ");  Label teamCityValue = new Label(overviewList[6]);

                    HBox nameBox = new HBox();      nameBox.getChildren().addAll(nameLabel,nameValue);
                    HBox captainBox = new HBox();     captainBox.getChildren().addAll(captainLabel,captainLabelValue);
                    HBox headCoachBox = new HBox();    headCoachBox.getChildren().addAll(headCoachLabel,headCoachValue);
                    HBox battingCoachBox = new HBox();     battingCoachBox.getChildren().addAll(battingCoachLabel,battingCoachValue);
                    HBox bowlingCoachBox = new HBox();     bowlingCoachBox.getChildren().addAll(bowlingCoachLabel,bowlingCoachValue);
                    HBox ownerBox = new HBox();      ownerBox.getChildren().addAll(ownerLabel,ownerValue);
                    HBox teamCityBox = new HBox();     teamCityBox.getChildren().addAll(teamCity,teamCityValue);

                    vBox.getChildren().addAll(overViewTeam,nameBox,captainBox,headCoachBox,battingCoachBox,bowlingCoachBox,ownerBox,teamCityBox);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Scene(vBox,1000,800);
    }

    public Scene teamPlayersScene(Stage stage, Scene scene, String text) {
        Button addPlayer = new Button("Add Players");
        Button removePlayer = new Button("Delete Player");
        Button addPlayer1 = new Button("Add Player Manually");
        Button refresh = new Button("Refresh");

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(addPlayer,addPlayer1,removePlayer,refresh);

        addPlayer.setOnAction(e -> {
            try {
                Stage currentStage = (Stage) addPlayer.getScene().getWindow();
                Scene scene1 = new Scene(new StackPane(), 1000, 800);
                currentStage.setTitle("Add Players in Team...!!!");
                currentStage.setScene(addPlayers(currentStage, scene1, text));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        return new Scene(hBox, 1000,800);
    }


    public Scene addPlayers(Stage stage, Scene scene, String text) {
        VBox vBox = new VBox(10);
        try{
            BufferedReader reader = new BufferedReader(new FileReader(PLAYER_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] player = line.split(",");
                CheckBox checkBox = new CheckBox(player[0]);
                vBox.getChildren().addAll(checkBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Scene(vBox,1000,800);
    }

}
