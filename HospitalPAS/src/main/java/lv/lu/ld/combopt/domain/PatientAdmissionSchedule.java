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

import static lv.lu.ld.combopt.Main.LOGGER;

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

    public void print() {
      //  List<BedDesignation> designations = this.getBedDesignations().stream().sorted(Comparator.comparingInt(dest -> dest.getPatientAdmission().getArrivalNight().getNumber())).toList();

        this.getBedDesignations().forEach(designation -> {
            LOGGER.info(designation.getPatientAdmission().getPatient().getName()
                    + ", arrival: " + designation.getPatientAdmission().getArrivalNight()
                    + ", departure: " + designation.getPatientAdmission().getDepartureNight());


            LOGGER.info("    " + (designation.getBed() != null ? designation.getBed().getRoom().getDepartment() : "") + " - " + designation.getBed());
        });
    }

    public static PatientAdmissionSchedule generateTestData()
    {
        PatientAdmissionSchedule problem = new PatientAdmissionSchedule();

        problem.setSolutionId("P1");

        Night night1 = new Night(1);
        Night night2 = new Night(2);
        Night night3 = new Night(3);
        Night night4 = new Night(4);
        Night night5 = new Night(5);
        Night night6 = new Night(6);
        Night night7 = new Night(7);

        Specialization specialization1 = new Specialization("General");
        Specialization specialization2 = new Specialization("Intense Care");

        Department department1 = new Department("General Ward Dep.", specialization1, new ArrayList<>());
        Department department2 = new Department("Intense Care Dep.", specialization2, new ArrayList<>());

        Room room1 = new Room("Room 1", department1, 2, Room.RoomGender.NONE, new ArrayList<>(), new ArrayList<>());
        Room room2 = new Room("Room 2", department1, 2, Room.RoomGender.NONE, new ArrayList<>(), new ArrayList<>());

        department1.getRooms().addAll(List.of(room1));
        department2.getRooms().addAll(List.of(room2));

        Bed bed1 = new Bed(room1, 1);
        Bed bed2 = new Bed(room1, 2);
        Bed bed3 = new Bed(room2, 1);
        Bed bed4 = new Bed(room2, 2);

        room1.getBeds().addAll(List.of(bed1, bed2));
        room2.getBeds().addAll(List.of(bed3, bed4));

        Patient patientA = new Patient("A", Patient.PatientGender.FEMALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient patientB = new Patient("B", Patient.PatientGender.MALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient patientC = new Patient("C", Patient.PatientGender.MALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient patientD = new Patient("D", Patient.PatientGender.MALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient patientE = new Patient("E", Patient.PatientGender.FEMALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient patientF = new Patient("F", Patient.PatientGender.MALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient patientG = new Patient("G", Patient.PatientGender.MALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient patientH = new Patient("H", Patient.PatientGender.MALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient patientI = new Patient("I", Patient.PatientGender.FEMALE, 1, new ArrayList<>(), new ArrayList<>());

        PatientAdmission patientAdmissionA = new PatientAdmission(patientA, night1, night6, specialization2, true);
        PatientAdmission patientAdmissionB = new PatientAdmission(patientB, night1, night5, specialization2, false);
        PatientAdmission patientAdmissionC = new PatientAdmission(patientC, night4, night7, specialization1, false);
        PatientAdmission patientAdmissionD = new PatientAdmission(patientD, night1, night3, specialization1, false);
        PatientAdmission patientAdmissionE = new PatientAdmission(patientE, night2, night4, specialization1, true);
        PatientAdmission patientAdmissionF = new PatientAdmission(patientF, night3, night5, specialization1, false);
        PatientAdmission patientAdmissionG = new PatientAdmission(patientG, night5, night7, specialization1, false);
        PatientAdmission patientAdmissionH = new PatientAdmission(patientH, night1, night2, specialization1, false);
        PatientAdmission patientAdmissionI = new PatientAdmission(patientI, night6, night7, specialization1, true);

        BedDesignation bedDesignationA = new BedDesignation(patientAdmissionA, null);
        BedDesignation bedDesignationB = new BedDesignation(patientAdmissionB, null);
        BedDesignation bedDesignationC = new BedDesignation(patientAdmissionC, null);
        BedDesignation bedDesignationD = new BedDesignation(patientAdmissionD, null);
        BedDesignation bedDesignationE = new BedDesignation(patientAdmissionE, null);
        BedDesignation bedDesignationF = new BedDesignation(patientAdmissionF, null);
        BedDesignation bedDesignationG = new BedDesignation(patientAdmissionG, null);
        BedDesignation bedDesignationH = new BedDesignation(patientAdmissionH, null);
        BedDesignation bedDesignationI = new BedDesignation(patientAdmissionI, null);

        problem.getNights().addAll(List.of(night1, night2, night3, night4, night5, night6, night7));
        problem.getSpecializations().addAll(List.of(specialization1, specialization2));
        problem.getDepartments().addAll(List.of(department1, department2));
        problem.getRooms().addAll(List.of(room1, room2));
        problem.getBeds().addAll(List.of(bed1, bed2, bed3, bed4));
        problem.getPatients().addAll(List.of(patientA, patientB, patientC, patientD, patientE, patientF, patientG, patientH, patientI));
        problem.getPatientAdmissions().addAll(List.of(patientAdmissionA, patientAdmissionB, patientAdmissionC, patientAdmissionD, patientAdmissionE,
                patientAdmissionF, patientAdmissionG, patientAdmissionH, patientAdmissionI));
        problem.getBedDesignations().addAll(List.of(bedDesignationA, bedDesignationB, bedDesignationC, bedDesignationD, bedDesignationE, bedDesignationF, bedDesignationG,
                bedDesignationH, bedDesignationI));

        return problem;
    }
}
