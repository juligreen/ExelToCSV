import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

//TODO: make Textfield editable and make them change the  actual  Filepath
public class Controller {

    @FXML
    private Button fileChooserButton;
    @FXML
    private TextField fileOriginTextField;
    @FXML
    private Button pathChooserButton;
    @FXML
    private TextField destinationTextField;
    @FXML
    private Button executeButton;

    private MainApp mainApp;

    private File xlsxFile;
    private File destinationPath;

    public Controller() {

    }

    public void tranferDataFromXLSXToCSV() {
        XLSXReader XLSXReader = new XLSXReader();
        try {
            XLSXReader.readXLSXFile(xlsxFile);
        } catch (IOException e) {
            new BetterErrorAlert("IOException", "IOException while reading .xlsx", "");
            e.printStackTrace();
        }
    }

    public void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose xlsx file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Exel Spreadsheet", "*.xlsx"));
        File xlsxFile = fileChooser.showOpenDialog(null);
        this.setXlsxFile(xlsxFile);
    }

    public void chooseDestination() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select destination folder");
        File destinationPath = chooser.showDialog(null);
        this.setDestinationPath(destinationPath);
    }

    public TextField getDestinationTextField() {
        return destinationTextField;
    }

    public void setDestinationTextField(TextField destinationTextField) {
        this.destinationTextField = destinationTextField;
    }

    public Button getFileChooserButton() {
        return fileChooserButton;
    }

    public void setFileChooserButton(Button fileChooserButton) {
        this.fileChooserButton = fileChooserButton;
    }

    public File getXlsxFile() {
        return xlsxFile;
    }

    public void setXlsxFile(File xlsxFile) {
        this.xlsxFile = xlsxFile;
        this.fileOriginTextField.setText(xlsxFile.getAbsolutePath());

    }

    public File getDestinationPath() {
        return destinationPath;
    }

    public void setDestinationPath(File destinationPath) {
        this.destinationPath = destinationPath;
        this.destinationTextField.setText(destinationPath.getAbsolutePath());
    }

    public MainApp getMainApp() {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
