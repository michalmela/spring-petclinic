package org.springframework.samples.petclinic.model;

/**
 * @@@SOLID@@@ what could be said about the interface segregation in this interface?
 */
public interface Human {
    String getFirstName();

    String getLastName();

    String getTelephone();
}
