package io.photochain;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EntryParser {
    List<Entry> parse(InputStream stream) throws IOException {
        ArrayList<Entry> entries = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                Entry entry = mapper.readValue(line, Entry.class);
                entries.add(entry);
            }
        } finally {
            reader.close();
        }

        return entries;
    }
}
