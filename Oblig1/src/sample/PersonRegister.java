package sample;

import exceptions.Avvik;
import exceptions.InvalidDateException;
import exceptions.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonRegister {
   private transient ObservableList<PersonMolde> liste = FXCollections.observableArrayList();
    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeObject(new ArrayList<>(liste));
    }
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        List<Person> list = (List<Person>) s.readObject();
        list = FXCollections.observableArrayList();
        list.addAll(list);
    }
    public ObservableList<PersonMolde> filtreringName(String n){

        return liste.stream().filter((person) -> person.getName().contains(n)).collect(Collectors.toCollection(FXCollections::observableArrayList));
   }
    public ObservableList<PersonMolde> filtreringAge(String a){
        int con = Integer.parseInt(a);
        return liste.stream().filter((person) -> person.getAge()==con).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    public ObservableList<PersonMolde> filtreringEmail(String e){
        return liste.stream().filter((person) -> person.getEmail().contains(e)).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    public ObservableList<PersonMolde> filtreringPhone(String p){
        return liste.stream().filter((person) -> person.getPhone().contains(p)).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
     public void attachTableView(TableView<PersonMolde> tv){ tv.setItems(liste);}

    public String lagerePerson (String navn, String dag, String måned, String år, String epost, String telefonnr){
        try {
            if (!PersonValidator.NavnValidation.testRegx(navn)) {
                return "Enter correct name";
            }
            if (!PersonValidator.EmailValidator.testRegx(epost)) {
                return "Enter correct E-pot";
            }
            if (!PersonValidator.NumberValidation.testRegx(telefonnr)){
                return "Enter correct phone number";
            }
            else {
                int indag = Integer.parseInt(dag);
                int riktigDag = Avvik.sjekkDag(indag);
                int imMåned = Integer.parseInt(måned);
                int riktigMåned = Avvik.sjekkMåned(imMåned);
                int inÅr = Integer.parseInt(år);
                int riktigÅr = Avvik.sjekkÅr(inÅr);
                Dato fødslesdato = new Dato(indag, imMåned, inÅr);
                PersonMolde enPerson = new PersonMolde(navn, fødslesdato, 2020-inÅr,epost,telefonnr);
                liste.add(enPerson);
                return "";
            }
        }

        catch (Avvik.InvalidAgeException iae){
        return iae.getMessage();
        }
        catch (InvalidDateException ide){
            return ide.getMessage();
        }
        catch (NumberFormatException nfe){
            return "Fill the birthday ";
        }

    }

        public String visListen(){
        String personListe = "";
        for (PersonMolde i : liste){
            personListe +=i+"\n";
        }

        return personListe;
        }

}
