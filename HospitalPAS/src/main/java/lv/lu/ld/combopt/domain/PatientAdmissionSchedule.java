package lv.lu.ld.combopt.domain;

import ai.timefold.solver.core.api.domain.solution.PlanningEntityCollectionProperty;
import ai.timefold.solver.core.api.domain.solution.PlanningScore;
import ai.timefold.solver.core.api.domain.solution.PlanningSolution;
import ai.timefold.solver.core.api.domain.solution.ProblemFactCollectionProperty;
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeProvider;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@PlanningSolution
@Getter @Setter @NoArgsConstructor
public class PatientAdmissionSchedule {
    private String solutionId;

    @PlanningScore
    private HardSoftScore score;

    @PlanningEntityCollectionProperty
    private List<BedDesignation> bedDesignations = new ArrayList<>();

    @ValueRangeProvider
    @ProblemFactCollectionProperty
    private List<Bed> beds = new ArrayList<>();

    @ProblemFactCollectionProperty
    private List<Department> departments = new ArrayList<>();

    @ProblemFactCollectionProperty
    private List<Equipment> equipments = new ArrayList<>();

    @ProblemFactCollectionProperty
    private List<Night> nights = new ArrayList<>();

    @ProblemFactCollectionProperty
    private List<Patient> patients = new ArrayList<>();

    @ProblemFactCollectionProperty
    private List<PatientAdmission> patientAdmissions = new ArrayList<>();

    @ProblemFactCollectionProperty
    private List<PreferredEquipment> preferredEquipments = new ArrayList<>();

    @ProblemFactCollectionProperty
    private List<RequiredEquipment> requiredEquipments = new ArrayList<>();

    @ProblemFactCollectionProperty
    private List<Room> rooms = new ArrayList<>();

    @ProblemFactCollectionProperty
    private List<RoomEquipment> roomEquipments = new ArrayList<>();

    @ProblemFactCollectionProperty
    private List<Specialization> specializations = new ArrayList<>();

    public static PatientAdmissionSchedule generateTestData()
    {
        PatientAdmissionSchedule problem = new PatientAdmissionSchedule();

        problem.setSolutionId("P1");

        return problem;
    }
}
