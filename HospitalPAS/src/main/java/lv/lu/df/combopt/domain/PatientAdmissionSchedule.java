package lv.lu.df.combopt.domain;

import ai.timefold.solver.core.api.domain.solution.PlanningEntityCollectionProperty;
import ai.timefold.solver.core.api.domain.solution.PlanningScore;
import ai.timefold.solver.core.api.domain.solution.PlanningSolution;
import ai.timefold.solver.core.api.domain.solution.ProblemFactCollectionProperty;
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeProvider;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.lu.df.combopt.Main;

import java.util.ArrayList;
import java.util.Comparator;
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

    public void print() {
        List<BedDesignation> designations = this.getBedDesignations().stream().sorted(Comparator.comparingInt(dest -> dest.getPatientAdmission().getArrivalNight().getNumber())).toList();

        designations.forEach(designation -> {
            Main.LOGGER.info(designation.getPatientAdmission().getPatient().getName()
                    + ", arrival: " + designation.getPatientAdmission().getArrivalNight()
                    + ", departure: " + designation.getPatientAdmission().getDepartureNight()
                    + ", specialization: " + designation.getPatientAdmission().getSpecialization()
                    + (designation.getPatientAdmission().getIsSpecializationRequired() ? "(required)" : "")
                    + ", gender: " + designation.getPatientAdmission().getPatient().getGender().name());


            Main.LOGGER.info("    " + (designation.getBed() != null
                        ? designation.getBed().getRoom().getDepartment()
                            + " (" +  designation.getBed().getRoom().getDepartment().getSpecialization() + ")"
                            + " - " + designation.getBed() + " (" + designation.getBed().getRoom().getRoomGender().name() + ")"
                        : "The bed isn't assigned"));
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

        Room room1 = new Room("Room 11", department1, 2, Room.RoomGender.FEMALE, new ArrayList<>(), new ArrayList<>());
        Room room2 = new Room("Room 21", department2, 2, Room.RoomGender.MALE, new ArrayList<>(), new ArrayList<>());
        Room room3 = new Room("Room 12", department1, 1, Room.RoomGender.MALE, new ArrayList<>(), new ArrayList<>());
        Room room4 = new Room("Room 22", department2, 1, Room.RoomGender.FEMALE, new ArrayList<>(), new ArrayList<>());

        department1.getRooms().addAll(List.of(room1, room3));
        department2.getRooms().addAll(List.of(room2, room4));

        Bed bed1 = new Bed(room1, 1);
        Bed bed2 = new Bed(room1, 2);
        Bed bed3 = new Bed(room2, 1);
        Bed bed4 = new Bed(room2, 2);
        Bed bed5 = new Bed(room3, 1);
        Bed bed6 = new Bed(room4, 1);

        room1.getBeds().addAll(List.of(bed1, bed2));
        room2.getBeds().addAll(List.of(bed3, bed4));
        room3.getBeds().add(bed5);
        room4.getBeds().add(bed6);

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
        PatientAdmission patientAdmissionC = new PatientAdmission(patientC, night5, night7, specialization1, false);
        PatientAdmission patientAdmissionD = new PatientAdmission(patientD, night1, night3, specialization1, false);
        PatientAdmission patientAdmissionE = new PatientAdmission(patientE, night3, night4, specialization1, true);
        PatientAdmission patientAdmissionF = new PatientAdmission(patientF, night4, night5, specialization1, true);
        PatientAdmission patientAdmissionG = new PatientAdmission(patientG, night6, night7, specialization1, false);
        PatientAdmission patientAdmissionH = new PatientAdmission(patientH, night1, night2, specialization1, false);
        PatientAdmission patientAdmissionI = new PatientAdmission(patientI, night7, night7, specialization2, true);

        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, null);
        BedDesignation bedDesignationB = new BedDesignation(2, patientAdmissionB, null);
        BedDesignation bedDesignationC = new BedDesignation(3, patientAdmissionC, null);
        BedDesignation bedDesignationD = new BedDesignation(4, patientAdmissionD, null);
        BedDesignation bedDesignationE = new BedDesignation(5, patientAdmissionE, null);
        BedDesignation bedDesignationF = new BedDesignation(6, patientAdmissionF, null);
        BedDesignation bedDesignationG = new BedDesignation(7, patientAdmissionG, null);
        BedDesignation bedDesignationH = new BedDesignation(8, patientAdmissionH, null);
        BedDesignation bedDesignationI = new BedDesignation(9,patientAdmissionI, null);

        problem.getNights().addAll(List.of(night1, night2, night3, night4, night5, night6, night7));
        problem.getSpecializations().addAll(List.of(specialization1, specialization2));
        problem.getDepartments().addAll(List.of(department1, department2));
        problem.getRooms().addAll(List.of(room1, room2, room3, room4));
        problem.getBeds().addAll(List.of(bed1, bed2, bed3, bed4, bed5, bed6));
        problem.getPatients().addAll(List.of(patientA, patientB, patientC, patientD, patientE, patientF, patientG, patientH, patientI));
        problem.getPatientAdmissions().addAll(List.of(patientAdmissionA, patientAdmissionB, patientAdmissionC, patientAdmissionD, patientAdmissionE,
                patientAdmissionF, patientAdmissionG, patientAdmissionH, patientAdmissionI));
        problem.getBedDesignations().addAll(List.of(bedDesignationA, bedDesignationB, bedDesignationC, bedDesignationD, bedDesignationE, bedDesignationF, bedDesignationG,
                bedDesignationH, bedDesignationI));

        return problem;
    }
}
