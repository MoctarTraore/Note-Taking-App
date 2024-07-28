package ci.pigier.controllers.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ci.pigier.controllers.BaseController;
import ci.pigier.model.Note;
import ci.pigier.ui.FXMLPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DeleteNoteController extends BaseController implements Initializable {

    private Note noteToDelete;

    @FXML
    void doCancel(ActionEvent event) throws IOException {
        navigate(event, FXMLPage.LIST.getPage());
    }

    @FXML
    void doDelete(ActionEvent event) throws IOException {
        if (noteToDelete != null) {
            data.remove(noteToDelete);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Note has been deleted!");
            alert.showAndWait();
        }
        navigate(event, FXMLPage.LIST.getPage());
    }

    public void setNoteToDelete(Note note) {
        this.noteToDelete = note;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code, if needed
    }
}
