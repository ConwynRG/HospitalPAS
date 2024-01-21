package lv.lu.df.combopt.domain;

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
    private Boolean isSpecializationRequired;

    public int getSharedNightCount(PatientAdmission admission) {
        int firstOverlappingNight = Math.max(getArrivalNight().getNumber(), admission.getArrivalNight().getNumber());
        int lastOverlappingNight = Math.min(getDepartureNight().getNumber(), admission.getDepartureNight().getNumber());

        return Math.max(0, lastOverlappingNight - firstOverlappingNight + 1);
    }

    public int getNightsSpent() {
        return getDepartureNight().getNumber() - getArrivalNight().getNumber() + 1;
    }

    @Override
    public String toString() {
        return patient.toString() + " admission from " + getArrivalNight() + " to " + getDepartureNight();
    }
}
