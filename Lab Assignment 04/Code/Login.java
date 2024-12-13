package com.example.hblpsl;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Login extends Application {

    public static void main(String[] args) {
        launch();
    }

    private List<UserDetails> users = new ArrayList<>();
    private static final String USER_FILE = "Users.txt";

    @Override
    public void start(Stage stage) throws IOException {

        users.add(new UserDetails("abc","a"));
        users.add(new UserDetails("xyz","x"));
        users.add(new UserDetails("lmn","l"));

        saveUsers();


        StackPane stackPane = new StackPane();

        Image image1 = new Image(getClass().getResource("/Images/page4.jpg").toExternalForm());
        ImageView imageView = new ImageView(image1);
        imageView.setPreserveRatio(false);
        imageView.fitHeightProperty().bind(stage.heightProperty());
        imageView.fitWidthProperty().bind(stage.widthProperty());


        stackPane.getChildren().add(imageView);
        VBox formContainer = new VBox(10);
        formContainer.getChildren().add(showLoginForm());
        formContainer.setAlignment(Pos.CENTER);
        formContainer.setPrefWidth(300);
        formContainer.setPrefHeight(400);
        stackPane.getChildren().add(formContainer);


        Scene scene = new Scene(stackPane);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setWidth(950);
        stage.setHeight(750);

        Image image = new Image(getClass().getResource("/Images/image3.png").toExternalForm());
        stage.getIcons().add(image);
        stage.setTitle("HBL PSL Login Page.!!!");
        stage.show();

    }



    private VBox showLoginForm() {

        VBox loginForm = new VBox(10);
        loginForm.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-padding: 20px;");
        loginForm.setMaxWidth(300);
        loginForm.setMaxHeight(400);
        loginForm.setMinHeight(350);
        loginForm.setSpacing(15);
        loginForm.setAlignment(Pos.CENTER);

        Label label = new Label("Login Form");
        label.setId("Login");
        label.setStyle("-fx-text-fill: green");

        TextField userNameField = new TextField();
        userNameField.setId("userNameField");
        userNameField.setPromptText("User Name");

        PasswordField passwordField = new PasswordField();
        passwordField.setId("passwordField");
        passwordField.setPromptText("Password");

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(label,userNameField,passwordField);
        vBox.setAlignment(Pos.CENTER);

        Label label1 = new Label();
        label1.setStyle("-fx-text-fill: red;");
        label1.setVisible(false);

        Button loginButton = new Button("Login");
        loginButton.setId("loginButton");

        loginButton.setOnAction(e -> {
            String name = userNameField.getText();
            String pss = passwordField.getText();

            if(validUser(name,pss)) {
                try {
                    Menu menu = new Menu();

                    Stage currentStage = (Stage) loginButton.getScene().getWindow();

                    Scene menuScene = new Scene(new StackPane(), 1000, 800);
                    currentStage.setTitle("Menu Page of PSL...!!!");
                    currentStage.setScene(menu.start(currentStage, menuScene));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }


            } else {
                label1.setText("Invalid Username or Password...!!!");
                label1.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
            }
            label1.setVisible(true);
        });

        Label label2 = new Label("or");
        label2.setStyle("-fx-text-fill: black; -fx-font-size: 12px;");

        Label signupLabel = new Label("Sign up");
        signupLabel.setUnderline(true);
        signupLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: black;");


        signupLabel.setOnMouseEntered(event -> signupLabel.setCursor(Cursor.HAND));
        signupLabel.setOnMouseClicked(e ->{
            VBox formContainer = (VBox) signupLabel.getParent().getParent(); // Get the form container
            formContainer.getChildren().setAll(showSignupForm());

        });


        VBox vBox1 = new VBox(2);
       // vBox1.setSpacing(3);
        vBox1.getChildren().addAll(loginButton,label2,signupLabel);
        vBox1.setAlignment(Pos.CENTER);

        VBox.setMargin(loginButton, new Insets(5, 0, 1, 0));
        VBox.setMargin(label2, new Insets(2, 0, 1, 0));
        VBox.setMargin(signupLabel, new Insets(1, 0, 0, 0));
        loginForm.getChildren().addAll(vBox,vBox1,label1);
        return loginForm;
    }




    private VBox showSignupForm(){
        VBox signupForm = new VBox(10);
        signupForm.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-padding: 20px;");
        signupForm.setMaxWidth(300);
        signupForm.setMaxHeight(400);
        signupForm.setSpacing(15);
        signupForm.setAlignment(Pos.CENTER);

        Label label1 = new Label();
        label1.setVisible(false);

        Label signup = new Label("Signup Form");
        signup.setId("signup");

        TextField userNameField = new TextField();
        userNameField.setPromptText("User Name");
        userNameField.setId("enterName");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");
        passwordField.setId("enterPassword");

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");
        confirmPasswordField.setId("confirmPassword");

        Button createAccountButton = new Button("Create Account");
        createAccountButton.setId("creatAccount");
        createAccountButton.setOnAction(e -> {
            String userName = userNameField.getText();
            String password = passwordField.getText();
            String cPassword = confirmPasswordField.getText();

            if(password.equals(cPassword)) {
                users.add(new UserDetails(userName,password));
                saveUpdatedUsers();
                ((VBox) signupForm.getParent()).getChildren().setAll(showLoginForm());
            } else
                {
                    label1.setText("Passwords do not match!");
                    label1.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                }
            label1.setVisible(true);
        });

        Button backButton = new Button("Back");
        backButton.setId("back");
        backButton.setOnAction(e -> {
            ((VBox) signupForm.getParent()).getChildren().setAll(showLoginForm());
        });

        signupForm.getChildren().addAll(signup,userNameField, passwordField, confirmPasswordField,createAccountButton, backButton, label1);
        return  signupForm;
    }




    private void saveUsers() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE));
            for(UserDetails user : users) {
                writer.write(user.getUserName() + ";" + user.getPassword());
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private void saveUpdatedUsers() {
        try{
            BufferedWriter writer1 = new BufferedWriter(new FileWriter(USER_FILE));
            for(UserDetails user1 : users) {
                writer1.write(user1.getUserName() + ";" + user1.getPassword());
                writer1.newLine();
            }
            writer1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private boolean validUser(String name, String password) {

        try(BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(";");
                if (userDetails[0].equals(name) && userDetails[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
