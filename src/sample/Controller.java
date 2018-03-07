package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import sample.Datasource.Contact;
import sample.Datasource.Data;

import javax.swing.*;

public class Controller {
    @FXML
    private TableView<Contact> tableView;
    @FXML
    protected TextField firstField;
    @FXML
    protected TextField secondField;
    @FXML
    protected TextField phoneField;
    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    public void setOnTable(){
        String fname= firstField.getText();
        String sname= secondField.getText();
        String phone= phoneField.getText();
        Data.getInstance().insertToTable(fname,sname,phone);
        loadList();
        firstField.clear();
        secondField.clear();
        phoneField.clear();
        tableView.refresh();
    }
   public void loadList(){
        Task<ObservableList<Contact>> task = new GetAllFromData();
        tableView.itemsProperty().bind(task.valueProperty());
        progressIndicator.progressProperty().bind(task.progressProperty());
        progressIndicator.setVisible(true);
        task.setOnSucceeded(event -> progressIndicator.setVisible(false));
        task.setOnFailed(event -> progressIndicator.setVisible(false));
        new Thread(task).start();
   }
   @FXML
    public void deleteContact(){
        final Contact contact =  tableView.getSelectionModel().getSelectedItem();
        if(contact == null){
            JOptionPane.showMessageDialog(null,"No contact selected.");
        }
        Data.getInstance().deleteContact(contact.getFirstName());
        loadList();
        tableView.refresh();
    }
}
class GetAllFromData extends Task{
    @Override
    protected ObservableList<Contact> call()  {
        return FXCollections.observableArrayList(Data.getInstance().listArray());
    }
}


