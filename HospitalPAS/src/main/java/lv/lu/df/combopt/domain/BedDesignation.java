package lv.lu.df.combopt.domain;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import ai.timefold.solver.core.api.domain.variable.PlanningVariable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@PlanningEntity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonIdentityInfo(scope = BedDesignation.class, property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
public class BedDesignation {
    @PlanningId
    private int id;

    @JsonIdentityReference
    private PatientAdmission patientAdmission;

    @PlanningVariable
    @JsonIdentityReference
    private Bed bed;

    @JsonIgnore
    public Specialization getDepartmentSpecialization()
    {
        if (bed == null) return null;

        return bed.getRoom().getDepartment().getSpecialization();
    }

    @JsonIgnore
    public Room getRoom()
    {
        if (bed == null) return null;

        return bed.getRoom();
    }

    @JsonIgnore
    public Room.RoomGender getAssignedRoomGender()
    {
        if (bed == null) return null;

        return bed.getRoom().getRoomGender();
    }

    @Override
    public String toString() {
        return String.format("%s assigned %s", patientAdmission, bed);
    }
}
