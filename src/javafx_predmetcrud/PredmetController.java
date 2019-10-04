package javafx_predmetcrud;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class PredmetController implements Initializable {

    @FXML
    private TextField textFieldSearch;
    @FXML
    private Label label;
    @FXML
    private Button buttonSearch;
    @FXML
    private TableView<Predmet> tableView;
    @FXML
    private TableColumn<Predmet, String> predmet_sifra;
    @FXML
    private TableColumn<Predmet, String> predmet_naziv;

    @FXML
    private void handleSearchAction() {
        WebTarget clientTarget;
        ObservableList<Predmet> data = tableView.getItems();
        data.clear();
        Client client = ClientBuilder.newClient();
        client.register(PredmetMessageBodyReader.class);
        if (textFieldSearch.getText().length() > 0) {
            clientTarget = client.target("http://localhost:42685/Projekat_ki401/webresources/ki401.entiteti.predmet/search/{beginBy}");
            clientTarget = clientTarget.resolveTemplate("beginBy", textFieldSearch.getText());
        } else {
            clientTarget = client.target("http://localhost:42685/Projekat_ki401/webresources/ki401.entiteti.predmet");
        }
        GenericType<List<Predmet>> listc = new GenericType<List<Predmet>>() {
        };
        List<Predmet> predmeti = clientTarget.request("application/json").get(listc);

        for (Predmet c : predmeti) {
            data.add(c);
            System.out.println(c.toString());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handleSearchAction();
    }

    @FXML
    private void handleDodajAction() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPredmet.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Dodaj predmet");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
