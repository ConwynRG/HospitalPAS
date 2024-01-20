package lv.lu.ld.combopt.domain;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.variable.PlanningVariable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@PlanningEntity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BedDesignation {
    private PatientAdmission patientAdmission;
    @PlanningVariable
    private Bed bed;

    @Override
    public String toString() {
        return String.format("%s assigned %s", patientAdmission, bed);
    }
}
