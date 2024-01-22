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
import java.util.Random;

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
                    + ", gender: " + designation.getPatientAdmission().getPatient().getGender().name()
                    + (!designation.getPatientAdmission().getPatient().getRequiredEquipments().isEmpty()
                        ? ", Required eq.: " + String.join(", ", designation.getPatientAdmission().getPatient().getRequiredEquipments().stream().map(RequiredEquipment::toString).toList())
                        : "")
                    + (!designation.getPatientAdmission().getPatient().getPreferredEquipments().isEmpty()
                        ? ", Preferred eq.: " +String.join(", ", designation.getPatientAdmission().getPatient().getPreferredEquipments().stream().map(PreferredEquipment::toString).toList())
                        : "")
                    + ", preferred capacity: " + designation.getPatientAdmission().getPatient().getPreferredMaxRoomSize()
            );


            Main.LOGGER.info("    " + (designation.getBed() != null
                        ? designation.getBed().getRoom().getDepartment()
                            + " (" +  designation.getBed().getRoom().getDepartment().getSpecialization() + ")"
                            + " - " + designation.getBed() + " (" + designation.getBed().getRoom().getRoomGender().name() + ")"
                            + (!designation.getBed().getRoom().getRoomEquipments().isEmpty()
                                ? ", Available eq.: " +String.join(", ", designation.getBed().getRoom().getRoomEquipments().stream().map(RoomEquipment::toString).toList())
                                : "")
                            + ", room capacity: " + designation.getBed().getRoom().getCapacity()
                        : "The bed isn't assigned"));
        });
    }

    private static int problemId = 0;
    private static Integer getProblemId() { problemId++; return problemId;}

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

        Room room1 = new Room("Room 11", department1, 2, Room.RoomGender.SAME_GENDER, new ArrayList<>(), new ArrayList<>());
        Room room2 = new Room("Room 21", department2, 2, Room.RoomGender.MALE, new ArrayList<>(), new ArrayList<>());
        Room room3 = new Room("Room 12", department1, 1, Room.RoomGender.MALE, new ArrayList<>(), new ArrayList<>());
        Room room4 = new Room("Room 22", department2, 1, Room.RoomGender.FEMALE, new ArrayList<>(), new ArrayList<>());

        department1.getRooms().addAll(List.of(room1, room3));
        department2.getRooms().addAll(List.of(room2, room4));

        Bed bed1 = new Bed(1, room1, 1);
        Bed bed2 = new Bed(2, room1, 2);
        Bed bed3 = new Bed(3, room2, 1);
        Bed bed4 = new Bed(4, room2, 2);
        Bed bed5 = new Bed(5, room3, 1);
        Bed bed6 = new Bed(6, room4, 1);

        room1.getBeds().addAll(List.of(bed1, bed2));
        room2.getBeds().addAll(List.of(bed3, bed4));
        room3.getBeds().add(bed5);
        room4.getBeds().add(bed6);

        Patient patientA = new Patient("A", Patient.PatientGender.FEMALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient patientB = new Patient("B", Patient.PatientGender.MALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient patientC = new Patient("C", Patient.PatientGender.MALE, 2, new ArrayList<>(), new ArrayList<>());
        Patient patientD = new Patient("D", Patient.PatientGender.MALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient patientE = new Patient("E", Patient.PatientGender.FEMALE, 2, new ArrayList<>(), new ArrayList<>());
        Patient patientF = new Patient("F", Patient.PatientGender.MALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient patientG = new Patient("G", Patient.PatientGender.MALE, 2, new ArrayList<>(), new ArrayList<>());
        Patient patientH = new Patient("H", Patient.PatientGender.MALE, 2, new ArrayList<>(), new ArrayList<>());
        Patient patientI = new Patient("I", Patient.PatientGender.FEMALE, 1, new ArrayList<>(), new ArrayList<>());

        PatientAdmission patientAdmissionA = new PatientAdmission(1, patientA, night1, night6, specialization2, true);
        PatientAdmission patientAdmissionB = new PatientAdmission(2, patientB, night1, night5, specialization2, false);
        PatientAdmission patientAdmissionC = new PatientAdmission(3, patientC, night5, night7, specialization1, false);
        PatientAdmission patientAdmissionD = new PatientAdmission(4, patientD, night1, night3, specialization1, false);
        PatientAdmission patientAdmissionE = new PatientAdmission(5, patientE, night3, night4, specialization1, true);
        PatientAdmission patientAdmissionF = new PatientAdmission(6, patientF, night4, night5, specialization1, true);
        PatientAdmission patientAdmissionG = new PatientAdmission(7, patientG, night6, night7, specialization1, false);
        PatientAdmission patientAdmissionH = new PatientAdmission(8, patientH, night1, night2, specialization1, false);
        PatientAdmission patientAdmissionI = new PatientAdmission(9, patientI, night7, night7, specialization2, true);

        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, null);
        BedDesignation bedDesignationB = new BedDesignation(2, patientAdmissionB, null);
        BedDesignation bedDesignationC = new BedDesignation(3, patientAdmissionC, null);
        BedDesignation bedDesignationD = new BedDesignation(4, patientAdmissionD, null);
        BedDesignation bedDesignationE = new BedDesignation(5, patientAdmissionE, null);
        BedDesignation bedDesignationF = new BedDesignation(6, patientAdmissionF, null);
        BedDesignation bedDesignationG = new BedDesignation(7, patientAdmissionG, null);
        BedDesignation bedDesignationH = new BedDesignation(8, patientAdmissionH, null);
        BedDesignation bedDesignationI = new BedDesignation(9,patientAdmissionI, null);

        Equipment equipment1 = new Equipment("telemetry");
        Equipment equipment2 = new Equipment("oxygen");

        RoomEquipment roomEquipment1 = new RoomEquipment(1, room3, equipment1);
        RoomEquipment roomEquipment2 = new RoomEquipment(2, room4, equipment2);

        room3.getRoomEquipments().add(roomEquipment1);
        room4.getRoomEquipments().add(roomEquipment2);

        RequiredEquipment requiredEquipment1 = new RequiredEquipment(1, patientA, equipment2);
        RequiredEquipment requiredEquipment2 = new RequiredEquipment(2, patientH, equipment1);

        patientA.getRequiredEquipments().add(requiredEquipment1);
        patientH.getRequiredEquipments().add(requiredEquipment2);

        PreferredEquipment preferredEquipment1 = new PreferredEquipment(1, patientI, equipment2);
        PreferredEquipment preferredEquipment2 = new PreferredEquipment(2, patientE, equipment1);

        patientI.getPreferredEquipments().add(preferredEquipment1);
        patientE.getPreferredEquipments().add(preferredEquipment2);

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
        problem.getEquipments().addAll(List.of(equipment1, equipment2));
        problem.getRoomEquipments().addAll(List.of(roomEquipment1, roomEquipment2));
        problem.getRequiredEquipments().addAll(List.of(requiredEquipment1, requiredEquipment2));
        problem.getPreferredEquipments().addAll(List.of(preferredEquipment1, preferredEquipment2));

        return problem;
    }

    // scale: number of patients
    public static PatientAdmissionSchedule generateData(int scale) {
        PatientAdmissionSchedule problem = new PatientAdmissionSchedule();

        problem.setSolutionId(PatientAdmissionSchedule.getProblemId().toString());

        Random random = new Random();

        List<Night> nightList = new ArrayList<>();
        List<Equipment> equipmentList = new ArrayList<>();
        List<Patient> patientList = new ArrayList<>();
        List<Specialization> specializationList = new ArrayList<>();
        List<Department> departmentList = new ArrayList<>();
        List<Room> roomList = new ArrayList<>();
        List<Bed> bedList = new ArrayList<>();
        List<RoomEquipment> roomEquipmentList = new ArrayList<>();
        List<RequiredEquipment> requiredEquipmentList = new ArrayList<>();
        List<PreferredEquipment> preferredEquipmentList = new ArrayList<>();
        List<PatientAdmission> patientAdmissionList = new ArrayList<>();
        List<BedDesignation> bedDesignationList = new ArrayList<>();

        int maxRoomCapacity = (int) Math.floor(Math.log(scale)) + 1;

        //Night: scale
        for (int i = 1; i <= scale; i++)
        {
            nightList.add(new Night(i));
        }

        //Equipment: log(scale)
        int equipmentCount = (int) Math.floor(Math.log(scale));

        for (int i = 1; i <= equipmentCount; i++)
        {
            equipmentList.add(new Equipment("Equipment " + i));
        }

        //Specialization: log(scale)
        //Department: log(scale)
        int specializationCount = (int) Math.floor(Math.log(scale));
        int bedId = 1;
        int roomEquipmentId = 1;

        for (int i = 1; i <= specializationCount; i++)
        {
            Specialization specialization = new Specialization("Specialization " + i);
            Department department = new Department("Department " + i, specialization, new ArrayList<>());


            // Rooms: log(scale)
            int roomCount = (int) Math.floor(Math.log(scale));
            for (int j = 1; j <= roomCount; j++)
            {
                int roomCapacity = random.nextInt(1, maxRoomCapacity);
                Room.RoomGender roomType = Room.RoomGender.randomRoomType();

                Room room = new Room("Room " + i + "-" + j, department, roomCapacity, roomType, new ArrayList<>(), new ArrayList<>());

                for (int k = 1; k <= roomCapacity; k++)
                {
                    Bed bed = new Bed(bedId, room, k);

                    bedId++;
                    room.getBeds().add(bed);
                    bedList.add(bed);
                }

                int roomEquipmentCount = Math.max(random.nextInt(10)-4, 0);
                List<Equipment> equipmentCopy = new ArrayList<>(equipmentList);

                for (int k = 1; k <= roomEquipmentCount && !equipmentCopy.isEmpty(); k++)
                {
                    RoomEquipment roomEquipment = new RoomEquipment(roomEquipmentId, room, equipmentCopy.remove(random.nextInt(equipmentCopy.size())));

                    roomEquipmentId++;

                    room.getRoomEquipments().add(roomEquipment);
                    roomEquipmentList.add(roomEquipment);
                }

                department.getRooms().add(room);
                roomList.add(room);
            }

            specializationList.add(specialization);
            departmentList.add(department);
        }

        int patientAdmissionId = 1;
        int preferredEquipmentId = 1;
        int requiredEquipmentId = 1;
        // Patients: scale
        for (int i = 1; i <= scale; i++) {
            Patient.PatientGender gender = random.nextBoolean() ? Patient.PatientGender.FEMALE : Patient.PatientGender.MALE;
            int preferredRoomCapacity = Math.max(maxRoomCapacity-1, random.nextInt(1, maxRoomCapacity) + random.nextInt(1, maxRoomCapacity));

            Patient patient = new Patient("Patient_" + i, gender, preferredRoomCapacity, new ArrayList<>(), new ArrayList<>());

            int requiredEquipmentCount = Math.max(random.nextInt(10)-8, 0);
            int preferredEquipmentCount = Math.max(random.nextInt(7)-4, 0);

            List<Equipment> equipmentCopy = new ArrayList<>(equipmentList);

            for (int j = 1; j <= requiredEquipmentCount && !equipmentCopy.isEmpty(); j++)
            {
                RequiredEquipment requiredEquipment = new RequiredEquipment(requiredEquipmentId, patient, equipmentCopy.remove(random.nextInt(equipmentCopy.size())));

                requiredEquipmentId++;

                requiredEquipmentList.add(requiredEquipment);
                patient.getRequiredEquipments().add(requiredEquipment);
            }

            equipmentCopy = new ArrayList<>(equipmentList);

            for (int j = 1; j <= preferredEquipmentCount && !equipmentCopy.isEmpty(); j++)
            {
                PreferredEquipment preferredEquipment = new PreferredEquipment(preferredEquipmentId, patient, equipmentCopy.remove(random.nextInt(equipmentCopy.size())));

                preferredEquipmentId++;

                preferredEquipmentList.add(preferredEquipment);
                patient.getPreferredEquipments().add(preferredEquipment);
            }

            int arrivalNight = random.nextInt(1, nightList.size());
            int nightSpent = random.nextInt(1, (int)Math.round(Math.log(scale)));

            int departureNight = Math.min(arrivalNight + random.nextInt(nightSpent)+random.nextInt(3), nightList.size());
            Specialization specialization = specializationList.get(random.nextInt(specializationList.size()));
            boolean isSpecializationRequired = random.nextInt(4) == 0;

            PatientAdmission patientAdmission = new PatientAdmission(patientAdmissionId, patient, nightList.get(arrivalNight-1), nightList.get(departureNight-1),specialization, isSpecializationRequired);
            BedDesignation bedDesignation = new BedDesignation(i, patientAdmission, null);

            patientAdmissionId++;

            patientList.add(patient);
            patientAdmissionList.add(patientAdmission);
            bedDesignationList.add(bedDesignation);
        }

        problem.getBeds().addAll(bedList);
        problem.getBedDesignations().addAll(bedDesignationList);
        problem.getDepartments().addAll(departmentList);
        problem.getEquipments().addAll(equipmentList);
        problem.getNights().addAll(nightList);
        problem.getPatients().addAll(patientList);
        problem.getPatientAdmissions().addAll(patientAdmissionList);
        problem.getPreferredEquipments().addAll(preferredEquipmentList);
        problem.getRequiredEquipments().addAll(requiredEquipmentList);
        problem.getRooms().addAll(roomList);
        problem.getRoomEquipments().addAll(roomEquipmentList);
        problem.getSpecializations().addAll(specializationList);

        return problem;
    }
}
