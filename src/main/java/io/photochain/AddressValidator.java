package io.photochain;

public class AddressValidator {
    void validate(Entry entry) throws InvalidAddressException {
        AddressGenerator generator = new AddressGenerator();
        String expected = generator.getAddress(entry.getPrivKey());
        if (!entry.getAddress().equalsIgnoreCase(expected)) {
            throw new InvalidAddressException(expected, entry.getAddress());
        }
    }
}
