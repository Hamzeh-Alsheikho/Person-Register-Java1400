package exceptions;

import javafx.scene.control.Alert;
import javafx.util.converter.IntegerStringConverter;

public class ConvertingAge extends IntegerStringConverter {
    public static boolean conversionSuccessful = true;

    @Override
    public Integer fromString(String s) {
        try {
            Integer result = super.fromString(s);
            conversionSuccessful = true;
            return result;
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Feil!");
            alert.setHeaderText("Invalid data!");
            alert.setContentText("Type correct age!");
            alert.showAndWait();

            conversionSuccessful = false;
            return 0;
        }
    }
}








