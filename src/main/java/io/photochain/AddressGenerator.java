package io.photochain;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.Keys;

public class AddressGenerator {
    public String getAddress(String privateKey) {
        Credentials credentials = Credentials.create(privateKey);
        String address = credentials.getAddress();
        return Keys.toChecksumAddress(address);
    }
}
