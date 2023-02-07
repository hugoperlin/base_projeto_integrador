package ifpr.pgua.eic.setgo.controllers;

import ifpr.pgua.eic.setgo.models.results.Result;
import ifpr.pgua.eic.setgo.models.results.SuccessResult;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BaseController {
    public static void showMessage(Result msg){
        AlertType tipo = msg instanceof SuccessResult? AlertType.INFORMATION:AlertType.ERROR;
        
        Alert alert = new Alert(tipo, msg.getMsg());
        alert.showAndWait();
    }
}
