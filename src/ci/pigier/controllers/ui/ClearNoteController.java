package ci.pigier.controllers.ui;

import ci.pigier.model.Note;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClearNoteController {

    @FXML
    private TextField titleTxtFld;

    @FXML
    private TextArea descriptionTxtArea;

    private Note noteToClear;

    public void setNoteToClear(Note note)
    {
        this.noteToClear = note;
        titleTxtFld.setText(note.getTitle());
        descriptionTxtArea.setText(note.getDescription());


    }

    @FXML
    void clearNote() {
        titleTxtFld.clear();
        descriptionTxtArea.clear();
    }
}
