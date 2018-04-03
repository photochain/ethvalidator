package io.photochain;

import java.util.Objects;

public class Entry {
    private String address;
    private String privKey;

    public Entry() {}

    public Entry(String address, String privKey) {
        this.address = address;
        this.privKey = privKey;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrivKey() {
        return privKey;
    }

    public void setPrivKey(String privKey) {
        this.privKey = privKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(address, entry.address) &&
                Objects.equals(privKey, entry.privKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, privKey);
    }
}
