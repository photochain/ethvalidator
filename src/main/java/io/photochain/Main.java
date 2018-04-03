package io.photochain;

import com.fasterxml.jackson.core.JsonParseException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Specify a single parameter with path to JSON entries file");
            return;
        }

        try {
            ValidateFile(args[0]);
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist. Specify correct path as the first argument.");
        } catch (JsonParseException e) {
            System.err.println("Failed to parse JSON file: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Failed to read file: " + e.getMessage());
        }
    }

    private static void ValidateFile(String arg) throws IOException {
        EntryParser parser = new EntryParser();
        AddressValidator validator = new AddressValidator();

        FileInputStream stream = new FileInputStream(arg);
        List<Entry> entries = parser.parse(stream);

        for (Entry entry : entries) {
            try {
                validator.validate(entry);
            } catch (InvalidAddressException e) {
                System.err.println("Invalid address, expected " + e.getExpectedAddress() + ", instead got " + e.getActualAddress());
            }
        }

        System.out.println("Processed " + entries.size() + " entries");
    }
}

