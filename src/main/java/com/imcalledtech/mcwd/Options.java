package com.imcalledtech.mcwd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Options {

    private File optionsFile;
    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .enableComplexMapKeySerialization()
            .create();

    Options(File optionsFile) throws IOException {
        // constructor that creates a new options file if it doesn't exist and adds the default options
        this.optionsFile = optionsFile;
        if (optionsFile.exists() && optionsFile.isFile()) {
            for (int i=0; i<Constants.OPTIONS_KEYS.length; i++) {
                if (getOptions(Constants.OPTIONS_KEYS[i]) == null) {
                    createOptionsFile(optionsFile);
                }
            }
        } else if (optionsFile.isDirectory()) {
            FileUtils.deleteDirectory(optionsFile);
            optionsFile.createNewFile();
            createOptionsFile(optionsFile);
        } else {
            optionsFile.getParentFile().mkdirs();
            optionsFile.createNewFile();
            createOptionsFile(optionsFile);
        }

    }

    private void createOptionsFile(File optionsFile) throws IOException {
        // creates a new options file with default options
        Map<String, Object> data = new HashMap<>();
        for (int i=0; i<Constants.OPTIONS_KEYS.length; i++) {
            data.put(Constants.OPTIONS_KEYS[i], Constants.OPTIONS_DEFAULTS[i]);
        }
        Writer writer = new FileWriter(optionsFile);
        gson.toJson(data, writer);
        writer.close();
    }

    protected void createDefaultOptionsFile() throws IOException {
        // creates a new options file with default options in the default options path
        createOptionsFile(optionsFile);
    }

    protected <T> void setOptions(String optionKey, T optionValue) throws IOException {
        // options setter
        Map<String, T> data = getOptionsObject();
        if (data.containsKey(optionKey)) {
            data.put(optionKey, optionValue);
            Writer writer = new FileWriter(optionsFile);
            gson.toJson(data, writer);
            writer.close();
        }
    }

    public <T> T getOptions(String optionKey) throws IOException {
        // method that returns the value of the option specified
        Map<String, T> readData = getOptionsObject();
        ArrayList<String> readDataKeys = new ArrayList<>(readData.keySet());
        ArrayList<T> readDataValues = new ArrayList<>(readData.values());
        if (readDataKeys.contains(optionKey)) {
            int i = 0;
            while (!readDataKeys.get(i).equals(optionKey)) {
                i++;
            }
            if (readDataValues.get(i).getClass() == Constants.OPTIONS_TYPES[i]) {
                return readDataValues.get(i);
            } else {
                createOptionsFile(optionsFile);
                return getOptions(optionKey);
            }
        } else {
            return null;
        }

    }

    private <T> Map<String, T> getOptionsObject() throws IOException {
        // reads the options json file and returns it as an object
        Map<String, T> readData = null;
        try {
            Reader reader = new FileReader(optionsFile);
            readData = gson.fromJson(reader, HashMap.class);
            reader.close();
        } catch (JsonSyntaxException | NullPointerException e) {
            createOptionsFile(optionsFile);
            Reader reader = new FileReader(optionsFile);
            readData = gson.fromJson(reader, HashMap.class);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readData;
    }

    public File getOptionsFile() {
        // get options file
        return optionsFile;
    }

    public void setOptionsFile(File optionsFile) {
        // set options file
        this.optionsFile = optionsFile;
    }

}
