package ci.pigier.controllers.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ci.pigier.controllers.BaseController;
import ci.pigier.model.Note;
import ci.pigier.ui.FXMLPage;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ListNotesUIController extends BaseController implements Initializable {

    @FXML
    private TableColumn<Note, String> descriptionTc;

    @FXML
    private Label notesCount;

    @FXML
    private TableView<Note> notesListTable;

    @FXML
    private TextField searchNotes;

    @FXML
    private TableColumn<Note, String> titleTc;

    @FXML
    void doDelete(ActionEvent event) throws IOException {
        Note selectedNote = notesListTable.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLPage.DELETE.getPage().toExternalForm()));
            Parent root = loader.load();

            DeleteNoteController controller = loader.getController();
            controller.setNoteToDelete(selectedNote);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    void doEdit(ActionEvent event) throws IOException {
        Note selectedNote = notesListTable.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLPage.EDIT.getPage().toExternalForm()));
            Parent root = loader.load();

            EditNoteController controller = loader.getController();
            controller.setNoteToEdit(selectedNote);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    void newNote(ActionEvent event) throws IOException {
        editNote = null;
        navigate(event, FXMLPage.ADD.getPage());
    }

    @FXML
    void clearNoteAction(ActionEvent event) throws IOException {
        Note selectedNote = notesListTable.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLPage.CLEAR.getPage().toExternalForm()));
            Parent root = loader.load();

            ClearNoteController controller = loader.getController();
            controller.setNoteToClear(selectedNote);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FilteredList<Note> filteredData = new FilteredList<>(data, n -> true);
        notesListTable.setItems(filteredData);
        titleTc.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionTc.setCellValueFactory(new PropertyValueFactory<>("description"));
        searchNotes.setOnKeyReleased(e -> {
            filteredData.setPredicate(n -> {
                if (searchNotes.getText() == null || searchNotes.getText().isEmpty())
                    return true;
                return n.getTitle().contains(searchNotes.getText()) || n.getDescription().contains(searchNotes.getText());
            });
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FilteredList<Note> filteredData = new FilteredList<>(data, n -> true);
        notesListTable.setItems(filteredData);

        titleTc.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionTc.setCellValueFactory(new PropertyValueFactory<>("description"));

        searchNotes.setOnKeyReleased(e -> {
            filteredData.setPredicate(n -> {
                if (searchNotes.getText() == null || searchNotes.getText().isEmpty()) {
                    return true;
                }
                return n.getTitle().contains(searchNotes.getText()) || n.getDescription().contains(searchNotes.getText());
            });
            updateNotesCount(filteredData);
        });

        updateNotesCount(filteredData);
    }

    private void updateNotesCount(FilteredList<Note> filteredData) {
        notesCount.setText(filteredData.size() + " Notes");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FilteredList<Note> filteredData = new FilteredList<>(data, n -> true);
        notesListTable.setItems(filteredData);

        titleTc.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionTc.setCellValueFactory(new PropertyValueFactory<>("description"));

        searchNotes.setOnKeyReleased(e -> {
            filteredData.setPredicate(n -> {
                if (searchNotes.getText() == null || searchNotes.getText().isEmpty()) {
                    return true;
                }
                return n.getTitle().contains(searchNotes.getText()) || n.getDescription().contains(searchNotes.getText());
            });
        });
    }

    @FXML
    void clearNoteAction(ActionEvent event) throws IOException {
        Note selectedNote = notesListTable.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLPage.CLEAR.getPage().toExternalForm()));
            Parent root = loader.load();

            ClearNoteController controller = loader.getController();
            controller.setNoteToClear(selectedNote);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

}
