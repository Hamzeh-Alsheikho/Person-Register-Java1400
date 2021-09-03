package sample;

import exceptions.Avvik;
import exceptions.ConvertingAge;
import exceptions.ConvertingDato;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    PersonRegister register = new PersonRegister();

    @FXML
    private TableView<PersonMolde> tableview;

    @FXML
    private TableColumn<PersonMolde, Dato> birthdayColumen;

    @FXML
    private TableColumn<PersonMolde, Integer> ageColumen;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    birthdayColumen.setCellFactory(TextFieldTableCell.forTableColumn(new ConvertingDato()));
    ageColumen.setCellFactory(TextFieldTableCell.forTableColumn(new ConvertingAge()));
        register.attachTableView(tableview);

    }

    @FXML
    private TextField txtNavn;

    @FXML
    private TextField txtDag;

    @FXML
    private TextField txtMåned;

    @FXML
    private TextField txtÅr;

    @FXML
    private TextField txtE;

    @FXML
    private TextField txtNr;

    @FXML
    private Button lagere;

    @FXML
    private Button listen;

    @FXML
    private Label txtUt;

    @FXML
    private Label txtresult;

    @FXML
    private TextField search;

    @FXML
    private ChoiceBox<String> drop;

    @FXML
    void seach(ActionEvent event) {
        if (search.getText().equals("")){
            txtUt.setText("Type something to search");
        }else {


         drop.getValue();
        if (drop.getValue().equals("Name")){
            tableview.setItems(register.filtreringName(search.getText()));
        }
        if (drop.getValue().equals("Age")){
            tableview.setItems(register.filtreringAge(search.getText()));
        }
        if (drop.getValue().equals("E-mail")){
            tableview.setItems(register.filtreringEmail(search.getText()));
        }
        if (drop.getValue().equals("Phone")){
            tableview.setItems(register.filtreringPhone(search.getText()));
        }

    }}

    @FXML
    void close1(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void close(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void reset(ActionEvent event) {
        search.setText("");
        txtUt.setText("");
        register.attachTableView(tableview);
        tableview.refresh();

    }

    @FXML
     void saveFile(ActionEvent event) {
        if (txtNavn.getText().equals("")){
            txtUt.setText("You have to fill the information!");
        }else {


        try{
           FileChooser ny = new FileChooser();
           ny.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All file", "*.*"));
           ny.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT file", "*.txt"));
           ny.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Binary file", "*.jobj"));
           File selctedFile = ny.showSaveDialog(null);
           FileWriter myWriter = new FileWriter(selctedFile.getAbsolutePath());
           myWriter.write(txtNavn.getText()+";"+txtDag.getText()+"."+txtMåned.getText()+"."+txtÅr.getText()+"."+";"+
                   txtE.getText()+";"+txtNr.getText()+"\n");
           myWriter.close();

            txtNavn.setText("");
            txtDag.setText("");
            txtMåned.setText("");
            txtÅr.setText("");
            txtE.setText("");
            txtNr.setText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info!");
            alert.setHeaderText("Success");
            alert.setContentText("Saved sccessfully");
            alert.showAndWait();

        }
       catch (IOException e){
            txtUt.setText("An error occured");
            e.getStackTrace();

       }
        }

    }

    @FXML
    void lagere(ActionEvent event) {
        txtUt.setText(register.lagerePerson(txtNavn.getText(),txtDag.getText(),txtMåned.getText(),txtÅr.getText(),
                txtE.getText(),txtNr.getText()));
        if (txtUt.getText().equals("")){
            resetTxtFields();
            txtresult.setText("Registerd successfully!");

        }
    }

    @FXML

    void showData(ActionEvent event) throws NumberFormatException{
            String a = ReadFile.reading();
                String[] strings = a.split("\n");
                for (String pers : strings) {
                    String[] person = pers.split(";");
                    String[] dd = person[1].split("\\.");
                    register.lagerePerson(person[0], dd[0], dd[1], dd[2], person[2], person[3]);

                }
    }


    @FXML
    void vis(ActionEvent event) {
        txtUt.setText(register.visListen());

    }

    @FXML
    private void resetTxtFields() {
        txtNavn.setText("");
        txtDag.setText("");
        txtMåned.setText("");
        txtÅr.setText("");
        txtE.setText("");
        txtNr.setText("");
    }


   @FXML
    public void nameedit(TableColumn.CellEditEvent<PersonMolde, String> event) {
        try {

            event.getRowValue().setName(Avvik.sjekkName(event.getNewValue()));
        }
        catch (IllegalArgumentException iae) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Feil!");
            alert.setHeaderText("Invalid data!");
            alert.setContentText("Type correct name!");
            alert.showAndWait();
            tableview.refresh();

        }
    }

   @FXML
   public void birthdayedit(TableColumn.CellEditEvent <PersonMolde, Dato>birdayedit) {
        birdayedit.getRowValue().setDate(birdayedit.getNewValue());
        txtresult.setText("You changed birthdate");
   }


    @FXML
    public void editAge(TableColumn.CellEditEvent <PersonMolde, Integer>Event) {
        Event.getRowValue().setAge(Event.getNewValue());
        txtresult.setText("You changed age");
    }

    @FXML
    public void postedit(TableColumn.CellEditEvent<PersonMolde,String> cellEditEvent) {
        try{
            cellEditEvent.getRowValue().setEmail(Avvik.sjekkEmail(cellEditEvent.getNewValue()));
        }
        catch (IllegalArgumentException iae) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Feil!");
            alert.setHeaderText("Invalid data!");
            alert.setContentText("Type correct E-mail!");
            alert.showAndWait();
            tableview.refresh();

        }

    }
    @FXML
    public void phonedit(TableColumn.CellEditEvent <PersonMolde,String>cellEditEvent) {
        try {
            cellEditEvent.getRowValue().setPhone(Avvik.sjekkTlfNummer(cellEditEvent.getNewValue()));
        }
        catch (IllegalArgumentException iae) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Feil!");
            alert.setHeaderText("Invalid data!");
            alert.setContentText("Type correct phone number!");
            alert.showAndWait();
            tableview.refresh();

        }

    }
    @FXML
    void sbinay(ActionEvent event) throws IOException {
        ArrayList<PersonMolde>list = new ArrayList<>();
        if (txtNavn.getText().equals("")){
            txtUt.setText("You have to fill the information!");
        }else {

            try {
                FileOutputStream filos = new FileOutputStream("data.jobj");
                ObjectOutputStream s = new ObjectOutputStream(filos);
         File  file = new File("data.jobj");
         if(file!=null){
             s.writeObject(list);
         }
         s.close();
         filos.flush();
         s.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            txtNavn.setText("");
            txtDag.setText("");
            txtMåned.setText("");
            txtÅr.setText("");
            txtE.setText("");
            txtNr.setText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info!");
            alert.setHeaderText("Success");
            alert.setContentText("Saved sccessfully to binary");
            alert.showAndWait();
        }

    }

    @FXML
    private Button btnUser;

    @FXML
    void rbinary(ActionEvent event) {
       /* try{
            ObjectInputStream files = new ObjectInputStream(new FileInputStream("data.txt"));
            ObjectInputStream is = new ObjectInputStream(files);
            String name = is.readUTF();
            int d = is.readInt();
            int m = is.readInt();
            int y = is.readInt();
            String email = is.readUTF();
            String phone = is.readUTF();
            is.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


        */

        ArrayList<PersonMolde>list = new ArrayList<>();
            try {
               // FileInputStream filos = new FileInputStream("data.jobj");
                File  file = new File("data.jobj");
                InputStream br = new FileInputStream(file);
                //OutputStream outputStream = new ObjectOutputStream(txtresult.setText());
                //FileInputStream s = new FileInputStream(("data.jobj"));

                Integer line;
                while ((line = br.read())!= -1) {
                    txtresult.setText(line.toString());
                }
                br.close();

                //s.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }




    }

    public void btnUser(ActionEvent actionEvent) {
    }
}



