package lv.lu.df.combopt.domain;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import ai.timefold.solver.core.api.domain.variable.PlanningVariable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@PlanningEntity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BedDesignation {
    @PlanningId
    private int id;

    private PatientAdmission patientAdmission;

    @PlanningVariable
    private Bed bed;

    public Specialization getDepartmentSpecialization()
    {
        if (bed == null) return null;

        return bed.getRoom().getDepartment().getSpecialization();
    }

    @Override
    public String toString() {
        return String.format("%s assigned %s", patientAdmission, bed);
    }
}
