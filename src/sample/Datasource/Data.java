package sample.Datasource;

import javax.swing.*;
import java.util.*;
import java.sql.*;

public class Data {
    //
    private static Data instance = new Data();
    private Data() {    }
    public static Data getInstance() {
        return instance;}
    //
    public static final String DB_NAME = "contact.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Veljko\\IdeaProjects\\Contact\\" + DB_NAME;
    //
    public static final String TABLE_CONTACT = "contacts";
    public static final String COLUMN_CONTACT_FIRST_NAME = "firstname";
    public static final String COLUMN_CONTACT_SECOND_NAME = "secondname";
    public static final String COLUMN_CONTACT_PHONENUMBER = "phonenumber";
    public static final int INDEX_FIRST_NAME = 1;
    public static final int INDEX_SECOND_NAME = 2;
    public static final int INDEX_PHONENUMBER = 3;
    //
    private Connection conn = null;
    private PreparedStatement insertintocontacts;
    private PreparedStatement deletecontact;
    //
    public static final String INSERT_CONTACTS = "INSERT INTO " + TABLE_CONTACT +
            '(' + COLUMN_CONTACT_FIRST_NAME + ", " + COLUMN_CONTACT_SECOND_NAME + ", " + COLUMN_CONTACT_PHONENUMBER +
            ") VALUES(?, ?, ?)";
    public static final String DELETE_CONTACT = "DELETE FROM " + TABLE_CONTACT + " WHERE " + TABLE_CONTACT + "." +
                                COLUMN_CONTACT_FIRST_NAME + "=? ";
    //
    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            insertintocontacts = conn.prepareStatement(INSERT_CONTACTS);
            deletecontact = conn.prepareStatement(DELETE_CONTACT);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }
    public void close() {
        try {
            if(deletecontact != null){
                deletecontact.close();
            }
            if(insertintocontacts != null) {
                insertintocontacts.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }
    public void insertToTable(String fname,String sname,String phone) {
        try {
                insertintocontacts.setString(1,  fname);
                insertintocontacts.setString(2,  sname);
                insertintocontacts.setString(3,  phone);
                int affectedRows = insertintocontacts.executeUpdate();
                if (affectedRows == 1) {
                    conn.commit();
                } else {
                    throw new SQLException("The song insert failed");
                }
            } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }
    public void deleteContact(String fname) {
        try {
            deletecontact = conn.prepareStatement(DELETE_CONTACT);
            deletecontact.setString(1,fname);
            deletecontact.execute();
        } catch (SQLException s) {
            JOptionPane.showMessageDialog(null, s.getMessage());
        }
    }
            public List<Contact> listArray () {
                try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
                     Statement statement = conn.createStatement()) {
                    ResultSet resultSet = statement.executeQuery("Select * FROM contacts  ");
                    List<Contact> contacts = new ArrayList<>();
                    while (resultSet.next()) {
                        try{
                            Thread.sleep(50);
                        }catch (InterruptedException i){
                            JOptionPane.showMessageDialog(null,i.getMessage());
                        }
                        Contact contact = new Contact();
                        contact.setFirstName(resultSet.getString(INDEX_FIRST_NAME));
                        contact.setSecondName(resultSet.getString(INDEX_SECOND_NAME));
                        contact.setPhoneNumber(resultSet.getString(INDEX_PHONENUMBER));
                        contacts.add(contact);
                    }
                    return contacts;
                } catch (SQLException s) {
                    System.out.println(s.getMessage());
                    return null;
                }
            }
        }
