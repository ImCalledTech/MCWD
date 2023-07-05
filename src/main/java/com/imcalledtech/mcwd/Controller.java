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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

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

    protected void initialize(String path) {

        // sets up the tableview with columns???
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        pathColumn.setCellValueFactory(new PropertyValueFactory<>("path"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));

        scanDirectories(path);

    }

    private void scanDirectories(String pathArg) {

        // scans the given directory for subdirectories and adds their info to the tableview and a separate array

        ObservableList<DirectoryInfo> directoryList = FXCollections.observableArrayList();

        File rootDirectory = new File(pathArg);
        if (rootDirectory.exists() && rootDirectory.isDirectory()) {
            File[] directories = rootDirectory.listFiles(file -> file.isDirectory() && file.getName().startsWith("Random Speedrun #"));
            if (directories != null) {
                for (File dir : directories) {
                    directoryList.add(new DirectoryInfo(dir.getName(), dir.getAbsolutePath(), FileUtils.sizeOfDirectory(dir)));
                    speedruns.add(dir);
                }
            }
        }

        tableView.setItems(directoryList);
    }

    public static class DirectoryInfo {

        // data type for observablelist???
        private String name;
        private String path;
        private long size;

        public DirectoryInfo(String name, String path, long size) {
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

        public long getSize() {
            return size;
        }

    }

}