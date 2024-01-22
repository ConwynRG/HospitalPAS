package lv.lu.df.combopt.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonIdentityInfo(scope = PatientAdmission.class, property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
public class PatientAdmission {
    private int id;

    @JsonIdentityReference
    private Patient patient;

    @JsonIdentityReference
    private Night arrivalNight;

    @JsonIdentityReference
    private Night departureNight;

    @JsonIdentityReference
    private Specialization specialization;

    private Boolean isSpecializationRequired;

    @JsonIgnore
    public int getSharedNightCount(PatientAdmission admission) {
        int firstOverlappingNight = Math.max(getArrivalNight().getNumber(), admission.getArrivalNight().getNumber());
        int lastOverlappingNight = Math.min(getDepartureNight().getNumber(), admission.getDepartureNight().getNumber());

        return Math.max(0, lastOverlappingNight - firstOverlappingNight + 1);
    }

    @JsonIgnore
    public int getNightsSpent() {
        return getDepartureNight().getNumber() - getArrivalNight().getNumber() + 1;
    }

    @Override
    public String toString() {
        return patient.toString() + " admission from " + getArrivalNight() + " to " + getDepartureNight();
    }
}
