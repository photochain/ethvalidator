package io.photochain;

public class InvalidAddressException extends Exception {
    private String expectedAddress;
    private String actualAddress;

    public InvalidAddressException(String expectedAddress, String actualAddress) {
        super("Expected address " + expectedAddress + " instead got " + actualAddress);

        this.expectedAddress = expectedAddress;
        this.actualAddress = actualAddress;
    }

    public String getExpectedAddress() {
        return expectedAddress;
    }

    public String getActualAddress() {
        return actualAddress;
    }
}
