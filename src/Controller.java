import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

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
    @FXML
    private CheckBox lastLoginCheckBox;
    @FXML
    private CheckBox passwordLastSetCheckBox;
    @FXML
    private TextField passwordCategoryTextField;


    private MainApp mainApp;

    private File xlsxFile;
    private File destinationPath;

    private TwoDimensionalArrayList xlsxList;


    public Controller() {

    }

    public void extractFromXLSXToCSV() {
        try {
            createCSV(tranferDataFromXLSXToArrayList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void createCSV(TwoDimensionalArrayList<String> xlsxList) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File(destinationPath + "\\" + xlsxFile.getName().substring(0, xlsxFile.getName().length() - 4) + ".csv"));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name");
        stringBuilder.append(',');
        stringBuilder.append("Username");
        stringBuilder.append(',');
        stringBuilder.append("Password");
        stringBuilder.append(',');
        stringBuilder.append("Password Category");
        stringBuilder.append(',');
        stringBuilder.append("Notes");
        stringBuilder.append('\n');

        for (int rowCount = 1; rowCount < xlsxList.size(); rowCount++) {
            for (int columnCount = 0; columnCount < 5; columnCount++) {
                switch (columnCount) {
                    case 0:
                        stringBuilder.append(xlsxList.getFromInnerArray(rowCount, columnCount) + ',');
                        break;
                    case 1:
                        stringBuilder.append(xlsxList.getFromInnerArray(rowCount, columnCount) + ',');
                        break;
                    case 2:
                        stringBuilder.append("" + ',');
                        break;
                    case 3:
                        stringBuilder.append(passwordCategoryTextField.getText() + ',');
                        break;
                    case 4:
                        if (lastLoginCheckBox.isSelected()) {
                            stringBuilder.append("Last login: " + xlsxList.getFromInnerArray(rowCount, 5) + " ");
                        }
                        if (passwordLastSetCheckBox.isSelected()) {
                            stringBuilder.append("Password last set: " + xlsxList.getFromInnerArray(rowCount, 3));
                        }
                        stringBuilder.append('\n');
                }
            }
        }
        printWriter.write(stringBuilder.toString());
        printWriter.close();

    }


    public TwoDimensionalArrayList<String> tranferDataFromXLSXToArrayList() {
        XLSXReader XLSXReader = new XLSXReader();
        TwoDimensionalArrayList<String> xlsxList = new TwoDimensionalArrayList<>();
        try {
            xlsxList = (XLSXReader.readXLSXFile(xlsxFile));
        } catch (IOException e) {
            new BetterErrorAlert("IOException", "IOException while reading .xlsx", "");
            e.printStackTrace();
        }
        return xlsxList;
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

    public TwoDimensionalArrayList getXlsxList() {
        return xlsxList;
    }

    public void setXlsxList(TwoDimensionalArrayList xlsxList) {
        this.xlsxList = xlsxList;
    }
}
