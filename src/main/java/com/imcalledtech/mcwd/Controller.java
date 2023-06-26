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
        return title;
    }

    @FXML
    private void selectDir() {

        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setInitialDirectory(new File(System.getenv("APPDATA")+"/.minecraft"));
        chooser.setTitle("Select .minecraft folder");
        Stage stage = (Stage) selectDirButton.getScene().getWindow();
        File selectedDir = chooser.showDialog(stage);
        if (selectedDir != null) {
            initialize(selectedDir.getAbsolutePath()+"/saves");
        }

    }

    @FXML
    private void deleteAll() throws IOException {
        if (!tableView.getItems().isEmpty()) {
            for (File dir : speedruns) {
                FileUtils.deleteDirectory(dir);
            }
            tableView.setItems(Constants.EMPTY_LIST);
        }
    }

    public void initialize(String path) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        pathColumn.setCellValueFactory(new PropertyValueFactory<>("path"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));

        scanDirectories(path);

    }

    private void scanDirectories(String pathArg) {

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