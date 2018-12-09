package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.datamodel.Contact;
import java.io.IOException;

public class Controller {

   // This class has full process to get started with TableView ;

   // TableView Compulsory STEP 1 : - declare an Observable List Like this

   private static ObservableList<Contact> contactList = FXCollections.observableArrayList(

           new Contact("Rajat","Sharma","94xxxxx603","Contact List Developer"),

           new Contact("Tim","Buchalka","122-333-444","My Java Teacher"),

           new Contact("Rob","Percival","44-773-447","My Web Dev teacher")
   );

   @FXML
   private TableView tableView;

   // Table Columns defined in FXML : Compulsory Step Number 2
   @FXML
   private TableColumn<Contact,String> firstName;

   @FXML
   private TableColumn<Contact,String> lastName;

   @FXML
   private TableColumn<Contact,String> phoneNumber;

   @FXML
   private TableColumn<Contact,String> notes;

   public void initialize(){

     // to set the columnSize to take up all the width....

     tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

     // Associate columns with Data : Compulsory Step Number 4

     firstName.setCellValueFactory(new PropertyValueFactory<Contact,String>("firstName"));

     lastName.setCellValueFactory(new PropertyValueFactory<Contact,String>("lastName"));

     phoneNumber.setCellValueFactory(new PropertyValueFactory<Contact,String>("phoneNumber"));

     notes.setCellValueFactory(new PropertyValueFactory<Contact,String>("notes"));

     // last and most simple step , Add data to the table

     tableView.setItems(contactList);

  }

  @FXML
  public void openNewContactWindow(){

     try{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Add.fxml"));

        Parent root = loader.load();

        Stage stage = new Stage();

        stage.setScene(new Scene(root,600,400));

        // to make the dialog modal , Application Modal is the key
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setTitle("Add new contact.");

        stage.showAndWait();

        addContact();

     }catch (IOException ex){

        System.out.println(ex);

        return;
     }

  }

  public void addContact(){

     Contact contact = AddController.getContact();

     AddController.setContact(null);

     if(contact != null) {

        contactList.add(contact);

     }
     if(!(contactList.isEmpty())) {

        tableView.setItems(contactList);

     }

  }

  @FXML
   public void openEditContactWindow(){

     try{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Edit.fxml"));

        Parent root = loader.load();

        Stage stage = new Stage();

        stage.setScene(new Scene(root,600,400));

        // to make the dialog modal , Application Modal is the key
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setTitle("Edit a contact.");

        stage.showAndWait();

        editContact();

     }catch (IOException ex){

        System.out.println(ex);

        return;

     }

  }

   public static ObservableList<Contact> getContactList() {

      return contactList;

   }

   public void editContact(){

     ObservableList<Contact> setList = EditController.getResult();

     if(setList!=null) {

         // Testing purpose : System.out.println(setList);

         contactList = setList;

         // Testing purpose : System.out.println(contactList);

         // for future rajat : you got stuck here because you were using tableView.getItem().clear() which eventually clears all your lists

         // by tableView.setItems(someList) the table is populated with new items and table is automaticaaly emptied unlike listView

         tableView.setItems(setList);

      }

  }

  @FXML
   public void openDeleteContactWindow(){

     try{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Delete.fxml"));

        Parent root = loader.load();

        Stage stage = new Stage();

        stage.setScene(new Scene(root,600,400));

        // to make the dialog modal , Application Modal is the key
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setTitle("Delete contact.");

        stage.showAndWait();

        deleteContact();

     }catch (IOException ex){

        System.out.println(ex);

        return;

     }

  }

  @FXML
   public  void deleteContact(){

     ObservableList<Contact> setList = DeleteController.getMyList();

     if(setList!=null) {

        contactList = setList;

        tableView.setItems(setList);

     }

  }

}
