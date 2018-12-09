package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.datamodel.Contact;

public class AddController {

    private static Contact contact;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField phoneNum;

    @FXML
    private TextField notes;

    @FXML
    private Button add;

    @FXML
    private Label status;

    public static Contact getContact() {
        return contact;
    }

    public static void setContact(Contact contact) {
        AddController.contact = contact;
    }

    @FXML
    public void setContactDetails(){

        String fName = firstName.getText();

        String lName = lastName.getText();

        String Num = phoneNum.getText();

        String Notes = notes.getText();

        boolean flag = false;

        if(fName!=null && lName!=null && phoneNum!=null && Notes!=null) {

            Controller controller = new Controller();

            ObservableList<Contact> myList = controller.getContactList();

            for(int i=0; i<myList.size();i++){

                Contact contact = myList.get(i);

                if(contact.getPhoneNumber().equals(Num)){

                    flag = true;

                    break;
                }

            }

            if(flag){

                status.setText("The given contact already exsists!");

            }else{

                contact = new Contact(fName,lName,Num,Notes);

                // closing the current window when Add button is clicked
                Stage stage = (Stage)add.getScene().getWindow();

                stage.close();
            }

        }

    }

}
