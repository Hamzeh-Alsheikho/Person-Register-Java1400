package exceptions;

import javafx.scene.control.Alert;
import javafx.util.StringConverter;
import sample.Dato;

public class ConvertingDato  extends StringConverter<Dato> {

    @Override
    public String toString(Dato dato) {
        return dato.toString();
    }

    @Override
    public Dato fromString(String s) throws NumberFormatException{

        try {
            String[] d = s.split("\\.");
            return new Dato(Integer.parseInt(d[0]), Integer.parseInt(d[1]),Integer.parseInt(d[2]));
        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Feil!");
            alert.setHeaderText("Invalid data!");
            alert.setContentText("Type correct date!");
            alert.showAndWait();
            e.printStackTrace();
            throw new NumberFormatException();


        }


    }
}








