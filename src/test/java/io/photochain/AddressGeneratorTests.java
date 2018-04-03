package io.photochain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class AddressGeneratorTests {
    AddressGenerator generator;

    @Before
    public void setUp() {
        generator = new AddressGenerator();
    }

    @Test
    public void testCorrectPrivateKeyWithoutHexPrefix() {
        String privateKey = "d18d71a865b22f33ef95203ad28098daf286a645bbd8d81ac715d3186d0b78b9";
        assertThat(generator.getAddress(privateKey), is("0xb87F175f978Cd5Ff6470Ecca16b7a8C01b41F23c"));
    }

    @Test
    public void testCorrectPrivateKeyWithHexPrefix() {
        String privateKey = "0xd18d71a865b22f33ef95203ad28098daf286a645bbd8d81ac715d3186d0b78b9";
        assertThat(generator.getAddress(privateKey), is("0xb87F175f978Cd5Ff6470Ecca16b7a8C01b41F23c"));
    }

    @Test(expected = NumberFormatException.class)
    public void testEmptyPrivateKey() {
        generator.getAddress("");
    }

    @Test(expected = NumberFormatException.class)
    public void testIncorrectPrivateKey() {
        generator.getAddress("What am I doing right now?");
    }
}

