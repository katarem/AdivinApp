package dad.adivinapp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class App extends Application
{
    private TextField introducirText;
    private Label infoLabel;
    private Button comprobarButton;
    private VBox rootPanel;
    private Scene scene;
    private int intentos = 1;
    private int numeroAdivinar = (int)(Math.random()*100+1);
    @Override
    public void start(Stage primaryStage) throws Exception {
       // creamos cuadro de texto
       introducirText = new TextField();
       introducirText.setPromptText("Introduce número");
       //creamos etiqueta
       infoLabel = new Label("Introduce un número del 1 al 100:");
       //creamos boton
       comprobarButton = new Button("Comprobar");
       comprobarButton.setDefaultButton(true);
       comprobarButton.setOnAction(e -> comprobarNumero()); //Con lambda
       //creamos el panel con disposicion vertical
       rootPanel = new VBox();
       rootPanel.setFillWidth(false);
       rootPanel.setSpacing(15);
       rootPanel.getChildren().addAll(infoLabel,introducirText,comprobarButton);
       rootPanel.setAlignment(Pos.CENTER);
       //Escena
       scene = new Scene(rootPanel,320,200);

       primaryStage.setTitle("AdivinApp");
       primaryStage.setScene(scene);
       primaryStage.show();
        
    }

    public void comprobarNumero() {

        String var = introducirText.getText().replaceAll("[^0-9]","");
        Alert alerta;

        if(var.isEmpty()){
            alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setContentText("Debes introducir un número, nada de texto ni dejarlo vacío.\nTambién debe ser positivo");
            alerta.showAndWait();
        }
        else{
            int num = Integer.parseInt(var);
        if (num==numeroAdivinar) {
            alerta = new Alert(AlertType.INFORMATION);
            alerta.setTitle("GANASTE!");
            alerta.setContentText(String.format("Adivinaste el número en %d intentos", intentos));
            alerta.showAndWait();
        } 
        if (num!=numeroAdivinar) {
            String dif;
            alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("NO ACERTASTE");
            if(num>numeroAdivinar)
                dif = "mayor que";
            else
                dif = "menor que";
            alerta.setContentText(String.format("No era ese número :(, es %s el número a adivinar.\nLlevas %d intentos",dif,intentos));
            intentos++;
            alerta.showAndWait();
        }
    }
    }





    public static void main( String[] args )
    {
        launch(args);
    }

}
