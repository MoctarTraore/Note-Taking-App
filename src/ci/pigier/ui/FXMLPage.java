package ci.pigier.ui;

import java.net.URL;

public enum FXMLPage {
    LIST("/ci/pigier/ui/fxml/ListNotesUI.fxml"),
    ADD("/ci/pigier/ui/fxml/AddEditUI.fxml"),
    EDIT("/ci/pigier/ui/fxml/EditNoteUI.fxml"),
    DELETE("/ci/pigier/ui/fxml/DeleteNoteUI.fxml"),
    CLEAR("/ci/pigier/ui/fxml/ClearNoteUI.fxml");



    private final String location;

    FXMLPage(String location) {
        this.location = location;
    }

    public URL getPage() {
        return getClass().getResource(location);
    }
}
