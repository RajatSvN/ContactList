package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.datamodel.Contact;

public class DeleteController {

    private static ObservableList<Contact> myList;

    @FXML
    private TextField checkNumber;

    @FXML
    private Label status;

    @FXML
    private Button deleter;

    @FXML
    public void delete(){
        boolean flag = false;

        String number = checkNumber.getText();

        Contact contact = new Contact();

        ObservableList<Contact> myList =Controller.getContactList();

        for(int i=0; i<myList.size();i++){

             contact = myList.get(i);

            if(contact.getPhoneNumber().equals(number)){

                flag = true;

                break;
            }

        }

        if(!flag){

            status.setText("The given contact does not exsist.");

        }else{

            actualDelete(contact);

        }

    }

    public void actualDelete(Contact c){

        myList = Controller.getContactList();

        myList.remove(c);

        // closing the current window when Add button is clicked
        Stage stage = (Stage)deleter.getScene().getWindow();

        stage.close();

    }

    public static ObservableList<Contact> getMyList() {

        return myList;

    }

}
