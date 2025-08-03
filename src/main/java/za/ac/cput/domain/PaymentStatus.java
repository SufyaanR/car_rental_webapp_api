package za.ac.cput.domain;

/**
 * Enum representing payment states
 * Author: Nompumezo Mcatshulwa (222614153)
 */
public enum PaymentStatus {
    PENDING,
    SUCCESSFUL,
    FAILED;

    @Override
    public String toString() {
        return name();
    }
}