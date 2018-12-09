package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.datamodel.Contact;

public class EditController {

    private static boolean flag = false ;

    private static Contact required = new Contact();

    private static int requiredIndex ;

    @FXML
    private Button editor;

    @FXML
    private TextField checkNumber;

    @FXML
    private Label status;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField phoneNum;

    @FXML
    private TextField notes;

    @FXML
    public void loadDetails(){

        Controller controller = new Controller();

        ObservableList<Contact> myList = controller.getContactList();

        String num = checkNumber.getText();

        for(int i=0; i<myList.size();i++){

            Contact contact = myList.get(i);

            if(contact.getPhoneNumber().equals(num)){

                required=contact;

                flag = true;

                requiredIndex=i;

                break;
            }

        }

        if(flag){

            firstName.setText(required.getFirstName());

            lastName.setText(required.getLastName());

            phoneNum.setText(required.getPhoneNumber());

            notes.setText(required.getNotes());

            firstName.setEditable(true);

            lastName.setEditable(true);

            phoneNum.setEditable(true);

            notes.setEditable(true);

        }else{
            status.setText("The given contact is not present.");

            firstName.setEditable(false);

            lastName.setEditable(false);

            phoneNum.setEditable(false);

            notes.setEditable(false);

            }

    }

    private static ObservableList<Contact> result ;

    public static ObservableList<Contact> getResult() {
        return result;
    }

    @FXML
    public void setContactDetails(){
        if(flag){

            result = Controller.getContactList();

            result.add(requiredIndex,new Contact(firstName.getText(),lastName.getText(),phoneNum.getText(),notes.getText()));

            result.remove(requiredIndex+1);

            flag=false;

            required = new Contact();

            requiredIndex=0;

            // closing the current window when Add button is clicked
            Stage stage = (Stage)editor.getScene().getWindow();

            stage.close();

        }else{

            return;

        }
    }
}
