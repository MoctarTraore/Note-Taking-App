package ci.pigier.controllers.ui;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import ci.pigier.controllers.BaseController;
import ci.pigier.model.Note;
import ci.pigier.ui.FXMLPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditNoteController extends BaseController implements Initializable {

    @FXML
    private TextArea descriptionTxtArea;

    @FXML
    private Button updateBtn;

    @FXML
    private TextField titleTxtFld;

    private Note currentNote;

    @FXML
    void doBack(ActionEvent event) throws IOException {
        navigate(event, FXMLPage.LIST.getPage());
    }

    @FXML
    void doClear(ActionEvent event) {
        titleTxtFld.clear();
        descriptionTxtArea.clear();
    }

    @FXML
    void doUpdate(ActionEvent event) throws IOException {
        if (titleTxtFld.getText().trim().isEmpty() || descriptionTxtArea.getText().trim().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Invalid data to update!");
            alert.setContentText("Note title or description cannot be empty!");
            alert.showAndWait();
            return;
        }

        currentNote.setTitle(titleTxtFld.getText());
        currentNote.setDescription(descriptionTxtArea.getText());
        navigate(event, FXMLPage.LIST.getPage());
    }

    public void setNoteToEdit(Note note)
    {
        this.currentNote = note;
        if (currentNote != null) {
            titleTxtFld.setText(currentNote.getTitle());
            descriptionTxtArea.setText(currentNote.getDescription());
            updateBtn.setText("Update");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code, if needed
    }
}
