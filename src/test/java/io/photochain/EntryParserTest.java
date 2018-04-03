package io.photochain;

import com.fasterxml.jackson.core.JsonParseException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class EntryParserTest {
    EntryParser parser;

    @Before
    public void setUp() {
        parser = new EntryParser();
    }

    @Test
    public void testParseEmpty() throws IOException {
        List<Entry> entries = parser.parse(new ByteArrayInputStream(new byte[0]));
        assertThat(entries.isEmpty(), is(true));
    }

    @Test
    public void testParseSingle() throws IOException {
        String input = "{\"address\":\"0xb87F175f978Cd5Ff6470Ecca16b7a8C01b41F23c\",\"privKey\":\"d18d71a865b22f33ef95203ad28098daf286a645bbd8d81ac715d3186d0b78b9\"}";
        List<Entry> expected = Arrays.asList(
                new Entry("0xb87F175f978Cd5Ff6470Ecca16b7a8C01b41F23c", "d18d71a865b22f33ef95203ad28098daf286a645bbd8d81ac715d3186d0b78b9")
        );

        List<Entry> entries = parser.parse(new ByteArrayInputStream(input.getBytes()));
        assertThat(entries, is(expected));
    }

    @Test
    public void testParseMultiple() throws IOException {
        String input =
                "{\"address\":\"0xb87F175f978Cd5Ff6470Ecca16b7a8C01b41F23c\", \"privKey\":\"d18d71a865b22f33ef95203ad28098daf286a645bbd8d81ac715d3186d0b78b9\"}\n" +
                "{\"address\":\"0xc775FC9feadE71B9E39fAEE43e15e64461d7c206\", \"privKey\":\"48f40f776fe63791e50d17e5e609681142da9de3aae6d5f1fb34f752ccbec83e\"}\n" +
                "{\"address\":\"0xa47eD12Da01bc0f3b5969C11380bAD78EEF128Ed\", \"privKey\":\"23ccf5c155b14cf47891771c88a9732dd069bb179740efad84035446ae719114\"}";

        List<Entry> expected = Arrays.asList(
                new Entry("0xb87F175f978Cd5Ff6470Ecca16b7a8C01b41F23c", "d18d71a865b22f33ef95203ad28098daf286a645bbd8d81ac715d3186d0b78b9"),
                new Entry("0xc775FC9feadE71B9E39fAEE43e15e64461d7c206", "48f40f776fe63791e50d17e5e609681142da9de3aae6d5f1fb34f752ccbec83e"),
                new Entry("0xa47eD12Da01bc0f3b5969C11380bAD78EEF128Ed", "23ccf5c155b14cf47891771c88a9732dd069bb179740efad84035446ae719114")
        );

        List<Entry> entries = parser.parse(new ByteArrayInputStream(input.getBytes()));
        assertThat(entries, is(expected));
    }

    @Test(expected = JsonParseException.class)
    public void testParseIncorrectJson() throws IOException {
        String input = "{\"address\":0xb87F175f978Cd5Ff6470Ecca16b7a8C01b41F23c\",\"privKey\":\"d18d71a865b22f33ef95203ad28098daf286a645bbd8d81ac715d3186d0b78b9\"}";

        parser.parse(new ByteArrayInputStream(input.getBytes()));
    }
}