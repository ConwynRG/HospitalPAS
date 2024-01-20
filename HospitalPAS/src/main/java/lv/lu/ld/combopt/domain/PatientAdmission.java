package lv.lu.ld.combopt.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PatientAdmission {
    private Patient patient;
    private Night arrivalNight;
    private Night departureNight;
    private Specialization specialization;
    private boolean isSpecializationRequired;

    @Override
    public String toString() {
        return patient.toString() + " admission from " + getArrivalNight() + " to " + getDepartureNight();
    }
}
