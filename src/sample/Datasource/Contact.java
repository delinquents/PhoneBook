package sample.Datasource;

import javafx.beans.property.SimpleStringProperty;

public class Contact {

    SimpleStringProperty firstName;
    SimpleStringProperty secondName;
    SimpleStringProperty phoneNumber;

//    public Contact(String firstName, String secondName, String phoneNumber) {
//        this.firstName = new SimpleStringProperty();
//        this.secondName = new SimpleStringProperty();
//        this.phoneNumber = new SimpleStringProperty();
//    }

    public Contact() {
        this.firstName = new SimpleStringProperty();
        this.secondName = new SimpleStringProperty();
        this.phoneNumber = new SimpleStringProperty();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getSecondName() {
        return secondName.get();
    }

    public SimpleStringProperty secondNameProperty() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }
}

