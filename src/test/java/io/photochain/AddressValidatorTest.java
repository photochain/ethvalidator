package io.photochain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class AddressValidatorTest {
    AddressValidator validator;

    @Before
    public void setUp() {
        validator = new AddressValidator();
    }

    @Test
    public void testCorrectPairWithoutHexPrefix() {
        Entry entry = new Entry();
        entry.setAddress("0xb87F175f978Cd5Ff6470Ecca16b7a8C01b41F23c");
        entry.setPrivKey("d18d71a865b22f33ef95203ad28098daf286a645bbd8d81ac715d3186d0b78b9");

        try {
            validator.validate(entry);
        } catch (InvalidAddressException e) {
            fail("Should not throw invalid address exception");
        }
    }

    @Test
    public void testCorrectPairWithHexPrefix() {
        Entry entry = new Entry();
        entry.setAddress("0xb87F175f978Cd5Ff6470Ecca16b7a8C01b41F23c");
        entry.setPrivKey("0xd18d71a865b22f33ef95203ad28098daf286a645bbd8d81ac715d3186d0b78b9");

        try {
            validator.validate(entry);
        } catch (InvalidAddressException e) {
            fail("Should not throw invalid address exception");
        }
    }

    @Test
    public void testCorrectPairWithoutChecksum() {
        Entry entry = new Entry();
        entry.setAddress("0xb87f175f978cd5ff6470ecca16b7a8c01b41f23c");
        entry.setPrivKey("0xd18d71a865b22f33ef95203ad28098daf286a645bbd8d81ac715d3186d0b78b9");

        try {
            validator.validate(entry);
        } catch (InvalidAddressException e) {
            fail("Should not throw invalid address exception");
        }
    }

    public void testEmptyPrivateKey() {
        Entry entry = new Entry();
        entry.setAddress("0xa818175f978cd5ff6470ecca16b7a8c01b41f23c");
        entry.setPrivKey("0xd18d71a865b22f33ef95203ad28098daf286a645bbd8d81ac715d3186d0b78b9");

        try {
            validator.validate(entry);
            fail("Should have thrown invalid address exception");
        } catch (InvalidAddressException e) {
            assertThat(e.getActualAddress(), is(entry.getAddress()));
            assertThat(e.getExpectedAddress(), is("0xb87f175f978cd5ff6470ecca16b7a8c01b41f23c"));
        }
    }

    @Test(expected = NumberFormatException.class)
    public void testIncorrectPrivateKey() throws InvalidAddressException {
        Entry entry = new Entry();
        entry.setAddress("0xa818175f978cd5ff6470ecca16b7a8c01b41f23c");
        entry.setPrivKey("What am I doing right now?");
        validator.validate(entry);
    }
}

