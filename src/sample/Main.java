package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import javafx.stage.StageStyle;
import netscape.javascript.JSObject;

public class Main extends Application {

    Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage;
        VBox root = new VBox();

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        webEngine.load("http://demo1.muzima.org/");

        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                JSObject jsObject = (JSObject) webEngine.executeScript("window");
                jsObject.setMember("app",new JavaApp());
            }
        });
        root.getChildren().addAll(webView);

        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("P-Smart Browser - Version 0.0.1");
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root, 600, 540));
        primaryStage.show();
    }

    // JavaScript interface object
    public class JavaApp {

        public String readCard() {
            Stage dialog = new Stage();

            VBox vBox = new VBox();
            Label label = new Label("Reading card, please wait...");

            vBox.getChildren().addAll(label);

            //dialog.initStyle(StageStyle.UNDECORATED);
            dialog.initStyle(StageStyle.UTILITY);
            dialog.setScene(new Scene(vBox,200,200));
            dialog.initOwner(stage);
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.show();
            try {
                //psmart reading code here...
                Thread.sleep(3000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dialog.close();
            //return response in json shr...
            return SHRSample.sample;
        }

        public String writeCard() {
            Stage dialog = new Stage();

            VBox vBox = new VBox();
            Label label = new Label("Writing card, please wait...");

            vBox.getChildren().addAll(label);

            //dialog.initStyle(StageStyle.UNDECORATED);
            dialog.initStyle(StageStyle.UTILITY);
            dialog.setScene(new Scene(vBox,200,200));
            dialog.initOwner(stage);
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.show();
            try {
                //psmart reading code here...
                Thread.sleep(3000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dialog.close();
            //return response in json shr...
            return "U0hSIERhdGEgaGVyZS4uLg==";
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
