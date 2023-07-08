package com.imcalledtech.mcwd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import org.apache.commons.io.FileUtils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class MainController {

    @FXML
    private Label title;

    @FXML
    private Button selectDirButton;

    @FXML
    private TableView<DirectoryInfo> tableView;

    @FXML
    private TableColumn<DirectoryInfo, String> nameColumn;

    @FXML
    private TableColumn<DirectoryInfo, String> pathColumn;

    @FXML
    private TableColumn<DirectoryInfo, Long> sizeColumn;

    private ArrayList<File> speedruns = new ArrayList<>();

    @FXML
    protected Label getTitle() {
        //gets the title of the gui so that the main method can change it
        return title;
    }

    @FXML
    private void github() throws URISyntaxException, IOException {
        openLink("https://github.com/ImCalledTech/MCWD");
    }

    @FXML
    private void discord() throws URISyntaxException, IOException {
        openLink("https://discord.com/invite/BThrE9QFHw");
    }

    private void openLink(String link) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI(link));
    }

    @FXML
    private void openOptions() throws IOException {
        OptionsController controller = App.optionsLoader.getController();
        controller.setToJsonValues();
        App.optionsStage.show();
    }

    @FXML
    private void selectDir() throws IOException {

        // choose directory method for button
        DirectoryChooser chooser = new DirectoryChooser();
        File minecraftFolder = new File((String) App.options.getOptions("default_minecraft_folder"));
        if (!minecraftFolder.exists() || !minecraftFolder.isDirectory()) {
            App.options.createDefaultOptionsFile();
            minecraftFolder = new File(App.getOsMinecraftFolder());
        }
        chooser.setInitialDirectory(minecraftFolder);
        chooser.setTitle("Select .minecraft folder");
        Stage stage = (Stage) selectDirButton.getScene().getWindow();
        File selectedDir = chooser.showDialog(stage);
        if (selectedDir != null) {
            initialize(selectedDir.getAbsolutePath()+File.separator+"saves");
        }

    }

    @FXML
    private void deleteAll() throws IOException {

        // deletes all files that the search found and then clears the tableview
        if (!tableView.getItems().isEmpty()) {
            for (File dir : speedruns) {
                FileUtils.deleteDirectory(dir);
            }
            tableView.setItems(Constants.EMPTY_LIST);
        }
    }

    protected void initialize(String path) throws IOException {

        // sets up the tableview with columns???
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        pathColumn.setCellValueFactory(new PropertyValueFactory<>("path"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));

        scanDirectories(path);

    }

    private void scanDirectories(String pathArg) throws IOException {

        // scans the given directory for subdirectories and adds their info to the tableview and a separate array

        ObservableList<DirectoryInfo> directoryList = FXCollections.observableArrayList();

        File rootDirectory = new File(pathArg);
        if (rootDirectory.exists() && rootDirectory.isDirectory()) {
            String prefix = App.options.getOptions("search_prefix");
            File[] directories = rootDirectory.listFiles(file -> file.isDirectory() && file.getName().startsWith(prefix));
            if (directories != null) {
                for (File dir : directories) {
                    directoryList.add(new DirectoryInfo(dir.getName(), dir.getAbsolutePath(), fileSize(dir)));
                    speedruns.add(dir);
                }
            }
        }

        tableView.setItems(directoryList);
    }

    private String fileSize(File file) {
        String[] measurements = {"Bytes", "KiB", "MiB", "GiB", "TiB", "PiB", "EiB", "ZiB", "YiB"};
        double size = FileUtils.sizeOfDirectory(file);
        int i = 0;
        while (size >= 1024 && i < measurements.length-1) {
            size /= 1024;
            i++;
        }
        if (size == 1) {
            return "1 Byte";
        } else if (size % 1 == 0) {
            return String.format("%.0f", size)+" "+measurements[i];
        } else {
            return String.format("%.1f", size)+" "+measurements[i];
        }
    }

    public static class DirectoryInfo {

        // data type for observablelist???
        private String name;
        private String path;
        private String size;

        public DirectoryInfo(String name, String path, String size) {
            this.name = name;
            this.path = path;
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public String getPath() {
            return path;
        }

        public String getSize() {
            return size;
        }

    }

}