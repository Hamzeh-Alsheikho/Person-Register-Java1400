package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PersonMolde implements Serializable {
    private transient SimpleStringProperty name;
    private transient SimpleObjectProperty<Dato> date;
    private transient SimpleIntegerProperty age;
    private transient SimpleStringProperty email;
    private transient SimpleStringProperty phone;

    public PersonMolde(String name, Dato date, int age, String eMail, String phone) {
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleObjectProperty<Dato>(date);
        this.age = new SimpleIntegerProperty(age);
        this.email = new SimpleStringProperty(eMail);
        this.phone = new SimpleStringProperty(phone);
    }

    public String getName() {
        return name.getValue();
    }


    public void setName(String name) {
        this.name.set(name);
    }

    public Dato getDate() {
        return date.getValue();
    }



    public void setDate(Dato date) {
        this.date.set(date);
    }

    public int getAge() {
        return age.getValue();
    }


    public void setAge(int age) {
        this.age.set(age);
    }

    public String getEmail() {
        return email.getValue();
    }


    public void setEmail(String eMail) {
        this.email.set(eMail);
    }

    public String getPhone() {
        return phone.getValue();
    }


    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.writeUTF(name.getValue());
        s.writeObject(date.getValue());
        s.writeInt(age.getValue());
        s.writeUTF(email.getValue());
        s.writeUTF(phone.getValue());
    }
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        String name = s.readUTF();
        Dato date = (Dato) s.readObject();
        int age = s.readInt();
        String email = s.readUTF();
        String phone = s.readUTF();

        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.date = new SimpleObjectProperty<Dato>(date);

    }


}
