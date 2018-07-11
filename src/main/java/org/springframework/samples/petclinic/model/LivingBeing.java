package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

public abstract class LivingBeing {

    private LocalDate birthDate;

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
