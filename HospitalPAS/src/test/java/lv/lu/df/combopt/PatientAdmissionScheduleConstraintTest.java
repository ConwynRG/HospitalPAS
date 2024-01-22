package lv.lu.df.combopt;

import ai.timefold.solver.test.api.score.stream.ConstraintVerifier;
import lv.lu.df.combopt.domain.*;
import lv.lu.df.combopt.solver.PatientAdmissionScheduleConstraintProvider;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PatientAdmissionScheduleConstraintTest {

    Night night1 = new Night(1);
    Night night2 = new Night(2);
    Night night3 = new Night(3);
    Night night4 = new Night(4);


    Specialization specialization1 = new Specialization("General");
    Specialization specialization2 = new Specialization("Intense Care");

    Department department1 = new Department("General Ward Dep.", specialization1, new ArrayList<>());
    Department department2 = new Department("Intense Care Dep.", specialization2, new ArrayList<>());

    Room room1 = new Room("Room 1", department1, 2, Room.RoomGender.FEMALE, new ArrayList<>(), new ArrayList<>());
    Room room2 = new Room("Room 2", department2, 2, Room.RoomGender.MALE, new ArrayList<>(), new ArrayList<>());

    Bed bed1 = new Bed(1, room1, 1);
    Bed bed2 = new Bed(2, room1, 2);
    Bed bed3 = new Bed(3, room2, 1);
    Bed bed4 = new Bed(4, room2, 2);

    Patient patientA = new Patient("A", Patient.PatientGender.FEMALE, 1, new ArrayList<>(), new ArrayList<>());
    Patient patientB = new Patient("B", Patient.PatientGender.MALE, 1, new ArrayList<>(), new ArrayList<>());

    ConstraintVerifier<PatientAdmissionScheduleConstraintProvider, PatientAdmissionSchedule> constraintVerifier = ConstraintVerifier.build(
            new PatientAdmissionScheduleConstraintProvider(), PatientAdmissionSchedule.class, BedDesignation.class);

    public PatientAdmissionScheduleConstraintTest() {
        department1.getRooms().add(room1);
        department2.getRooms().add(room2);

        room1.getBeds().addAll(List.of(bed1, bed2));
        room2.getBeds().addAll(List.of(bed3, bed4));
    }

    @Test
    public void bedAssignmentTest() {
        PatientAdmission patientAdmissionA = new PatientAdmission(1, patientA, night1, night2, specialization2, true);
        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, null);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::assignEveryPatientToBed)
                .given(bedDesignationA)
                .penalizesBy(1);
    }

    @Test
    public void sameBedAssignmentTest1() {
        PatientAdmission patientAdmissionA = new PatientAdmission(1, patientA, night1, night2, specialization2, true);
        PatientAdmission patientAdmissionB = new PatientAdmission(2, patientB, night1, night2, specialization2, false);

        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed1);
        BedDesignation bedDesignationB = new BedDesignation(2, patientAdmissionB, bed1);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::spentNightInSameBed)
                .given(bedDesignationA, bedDesignationB)
                .penalizesBy(2);
    }

    @Test
    public void sameBedAssignmentTest2() {
        PatientAdmission patientAdmissionA = new PatientAdmission(1, patientA, night2, night3, specialization2, true);
        PatientAdmission patientAdmissionB = new PatientAdmission(2, patientB, night1, night2, specialization2, false);

        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed1);
        BedDesignation bedDesignationB = new BedDesignation(2, patientAdmissionB, bed1);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::spentNightInSameBed)
                .given(bedDesignationA, bedDesignationB)
                .penalizesBy(1);
    }

    @Test
    public void sameBedAssignmentTest3() {
        PatientAdmission patientAdmissionA = new PatientAdmission(1, patientA, night3, night3, specialization2, true);
        PatientAdmission patientAdmissionB = new PatientAdmission(2, patientB, night1, night2, specialization2, false);

        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed1);
        BedDesignation bedDesignationB = new BedDesignation(2, patientAdmissionB, bed1);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::spentNightInSameBed)
                .given(bedDesignationA, bedDesignationB)
                .penalizesBy(0);
    }

    @Test
    public void patientSpecializationMetTest1()
    {
        PatientAdmission patientAdmissionA = new PatientAdmission(1, patientA, night3, night3, specialization2, true);
        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed1);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::patientRequiredSpecializationMet)
                .given(bedDesignationA)
                .penalizesBy(1);
    }

    @Test
    public void patientSpecializationMetTest2()
    {
        PatientAdmission patientAdmissionA = new PatientAdmission(1, patientA, night3, night3, specialization1, true);
        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed1);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::patientRequiredSpecializationMet)
                .given(bedDesignationA)
                .penalizesBy(0);
    }

    @Test
    public void patientSpecializationMetTest3()
    {
        PatientAdmission patientAdmissionA = new PatientAdmission(1, patientA, night3, night3, specialization2, false);
        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed1);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::patientRequiredSpecializationMet)
                .given(bedDesignationA)
                .penalizesBy(0);
    }

    @Test
    public void patientPreferredSpecializationMetTest1()
    {
        PatientAdmission patientAdmissionA = new PatientAdmission(1, patientA, night3, night3, specialization2, false);
        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed1);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::patientPreferredSpecializationMet)
                .given(bedDesignationA)
                .penalizesBy(1);
    }

    @Test
    public void patientPreferredSpecializationMetTest2()
    {
        PatientAdmission patientAdmissionA = new PatientAdmission(1, patientA, night3, night3, specialization2, false);
        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed3);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::patientPreferredSpecializationMet)
                .given(bedDesignationA)
                .penalizesBy(0);
    }

    @Test
    public void patientPreferredSpecializationMetTest3()
    {
        PatientAdmission patientAdmissionA = new PatientAdmission(1, patientA, night3, night3, specialization1, true);
        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed3);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::patientPreferredSpecializationMet)
                .given(bedDesignationA)
                .penalizesBy(0);
    }

    @Test
    public void malePatientsInFemaleRoomTest1()
    {
        PatientAdmission patientAdmissionB = new PatientAdmission(1, patientB, night3, night3, specialization1, true);
        BedDesignation bedDesignationB = new BedDesignation(1, patientAdmissionB, bed1);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::malePatientsInFemaleRoom)
                .given(bedDesignationB)
                .penalizesBy(1);
    }

    @Test
    public void malePatientsInFemaleRoomTest2()
    {
        PatientAdmission patientAdmissionB = new PatientAdmission(1, patientB, night3, night3, specialization1, true);
        BedDesignation bedDesignationB = new BedDesignation(1, patientAdmissionB, bed3);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::malePatientsInFemaleRoom)
                .given(bedDesignationB)
                .penalizesBy(0);
    }

    @Test
    public void femalePatientsInMaleRoomTest1()
    {
        PatientAdmission patientAdmissionA = new PatientAdmission(1, patientA, night3, night3, specialization1, true);
        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed3);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::femalePatientsInMaleRoom)
                .given(bedDesignationA)
                .penalizesBy(1);
    }

    @Test
    public void femalePatientsInMaleRoomTest2()
    {
        PatientAdmission patientAdmissionA = new PatientAdmission(1, patientA, night3, night3, specialization1, true);
        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed1);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::femalePatientsInMaleRoom)
                .given(bedDesignationA)
                .penalizesBy(0);
    }

    @Test
    public void singleGenderTypeInTheSameGenderRoomTest1()
    {
        Patient femaleA = new Patient("A", Patient.PatientGender.FEMALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient femaleB = new Patient("B", Patient.PatientGender.FEMALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient maleC = new Patient("C", Patient.PatientGender.MALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient maleD = new Patient("D", Patient.PatientGender.MALE, 1, new ArrayList<>(), new ArrayList<>());

        PatientAdmission patientAdmissionA = new PatientAdmission(1, femaleA, night1, night1, specialization1, true);
        PatientAdmission patientAdmissionB = new PatientAdmission(2, femaleB, night1, night1, specialization1, true);
        PatientAdmission patientAdmissionC = new PatientAdmission(3, maleC, night1, night1, specialization1, true);
        PatientAdmission patientAdmissionD = new PatientAdmission(4, maleD, night1, night1, specialization1, true);

        Room sameGenderRoom = new Room("Same Gender Room", department1, 4, Room.RoomGender.SAME_GENDER, new ArrayList<>(), new ArrayList<>());

        Bed bed1 = new Bed(1, sameGenderRoom, 1);
        Bed bed2 = new Bed(2, sameGenderRoom, 2);
        Bed bed3 = new Bed(3, sameGenderRoom, 3);
        Bed bed4 = new Bed(4, sameGenderRoom, 4);

        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed1);
        BedDesignation bedDesignationB = new BedDesignation(2, patientAdmissionB, bed2);
        BedDesignation bedDesignationC = new BedDesignation(3, patientAdmissionC, bed3);
        BedDesignation bedDesignationD = new BedDesignation(4, patientAdmissionD, bed4);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::singleGenderTypeInTheSameGenderRoom)
                .given(bedDesignationA, bedDesignationB, bedDesignationC, bedDesignationD)
                .penalizesBy(4);
    }

    @Test
    public void singleGenderTypeInTheSameGenderRoomTest2()
    {
        Patient femaleA = new Patient("A", Patient.PatientGender.FEMALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient femaleB = new Patient("B", Patient.PatientGender.FEMALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient maleC = new Patient("C", Patient.PatientGender.MALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient maleD = new Patient("D", Patient.PatientGender.MALE, 1, new ArrayList<>(), new ArrayList<>());

        PatientAdmission patientAdmissionA = new PatientAdmission(1, femaleA, night1, night2, specialization1, true);
        PatientAdmission patientAdmissionB = new PatientAdmission(2, femaleB, night1, night2, specialization1, true);
        PatientAdmission patientAdmissionC = new PatientAdmission(3, maleC, night2, night3, specialization1, true);
        PatientAdmission patientAdmissionD = new PatientAdmission(4, maleD, night2, night3, specialization1, true);

        Room sameGenderRoom = new Room("Same Gender Room", department1, 4, Room.RoomGender.SAME_GENDER, new ArrayList<>(), new ArrayList<>());

        Bed bed1 = new Bed(1, sameGenderRoom, 1);
        Bed bed2 = new Bed(2, sameGenderRoom, 2);
        Bed bed3 = new Bed(3, sameGenderRoom, 3);
        Bed bed4 = new Bed(4, sameGenderRoom, 4);

        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed1);
        BedDesignation bedDesignationB = new BedDesignation(2, patientAdmissionB, bed2);
        BedDesignation bedDesignationC = new BedDesignation(3, patientAdmissionC, bed3);
        BedDesignation bedDesignationD = new BedDesignation(4, patientAdmissionD, bed4);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::singleGenderTypeInTheSameGenderRoom)
                .given(bedDesignationA, bedDesignationB, bedDesignationC, bedDesignationD)
                .penalizesBy(4);
    }

    @Test
    public void singleGenderTypeInTheSameGenderRoomTest3()
    {
        Patient femaleA = new Patient("A", Patient.PatientGender.FEMALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient femaleB = new Patient("B", Patient.PatientGender.FEMALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient maleC = new Patient("C", Patient.PatientGender.MALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient maleD = new Patient("D", Patient.PatientGender.MALE, 1, new ArrayList<>(), new ArrayList<>());

        PatientAdmission patientAdmissionA = new PatientAdmission(1, femaleA, night1, night2, specialization1, true);
        PatientAdmission patientAdmissionB = new PatientAdmission(2, femaleB, night1, night2, specialization1, true);
        PatientAdmission patientAdmissionC = new PatientAdmission(3, maleC, night3, night3, specialization1, true);
        PatientAdmission patientAdmissionD = new PatientAdmission(4, maleD, night3, night3, specialization1, true);

        Room sameGenderRoom = new Room("Same Gender Room", department1, 4, Room.RoomGender.SAME_GENDER, new ArrayList<>(), new ArrayList<>());

        Bed bed1 = new Bed(1, sameGenderRoom, 1);
        Bed bed2 = new Bed(2, sameGenderRoom, 2);
        Bed bed3 = new Bed(3, sameGenderRoom, 3);
        Bed bed4 = new Bed(4, sameGenderRoom, 4);

        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed1);
        BedDesignation bedDesignationB = new BedDesignation(2, patientAdmissionB, bed2);
        BedDesignation bedDesignationC = new BedDesignation(3, patientAdmissionC, bed3);
        BedDesignation bedDesignationD = new BedDesignation(4, patientAdmissionD, bed4);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::singleGenderTypeInTheSameGenderRoom)
                .given(bedDesignationA, bedDesignationB, bedDesignationC, bedDesignationD)
                .penalizesBy(0);
    }

    @Test
    public void singleGenderTypeInTheSameGenderRoomTest4()
    {
        Patient femaleA = new Patient("A", Patient.PatientGender.FEMALE, 1, new ArrayList<>(), new ArrayList<>());
        Patient maleC = new Patient("C", Patient.PatientGender.MALE, 1, new ArrayList<>(), new ArrayList<>());

        PatientAdmission patientAdmissionA = new PatientAdmission(1, femaleA, night1, night3, specialization1, true);
        PatientAdmission patientAdmissionC = new PatientAdmission(2,maleC, night2, night4, specialization1, true);

        Room sameGenderRoom = new Room("Same Gender Room", department1, 2, Room.RoomGender.SAME_GENDER, new ArrayList<>(), new ArrayList<>());

        Bed bed1 = new Bed(1, sameGenderRoom, 1);
        Bed bed2 = new Bed(2, sameGenderRoom, 2);

        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed1);
        BedDesignation bedDesignationC = new BedDesignation(2, patientAdmissionC, bed2);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::singleGenderTypeInTheSameGenderRoom)
                .given(bedDesignationA, bedDesignationC)
                .penalizesBy(2);
    }

    @Test
    public void requiredEquipmentTest1()
    {
        Patient patient = new Patient("A", Patient.PatientGender.FEMALE, 1, new ArrayList<>(), new ArrayList<>());

        Equipment equipment1 = new Equipment("telemetry");
        Equipment equipment2 = new Equipment("oxygen");

        Room roomWithEquipment = new Room("Room with Equipment", department1, 1, Room.RoomGender.SAME_GENDER, new ArrayList<>(), new ArrayList<>());

        RoomEquipment roomEquipment1 = new RoomEquipment(1, roomWithEquipment, equipment1);
        RoomEquipment roomEquipment2 = new RoomEquipment(2, roomWithEquipment, equipment2);

        roomWithEquipment.getRoomEquipments().addAll(List.of(roomEquipment1, roomEquipment2));

        RequiredEquipment requiredEquipment1 = new RequiredEquipment(1, patient, equipment1);
        RequiredEquipment requiredEquipment2 = new RequiredEquipment(2, patient, equipment2);

        patient.getRequiredEquipments().addAll(List.of(requiredEquipment1, requiredEquipment2));

        Bed bed = new Bed(1, roomWithEquipment, 1);
        PatientAdmission patientAdmission = new PatientAdmission(1, patient, night1, night3, specialization1, true);
        BedDesignation bedDesignation = new BedDesignation(1, patientAdmission, bed);


        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::patientHasRequiredEquipment)
                .given(bedDesignation, requiredEquipment1, requiredEquipment2, roomEquipment1, roomEquipment2)
                .penalizesBy(0);
    }

    @Test
    public void requiredEquipmentTest2()
    {
        Patient patient = new Patient("A", Patient.PatientGender.FEMALE, 1, new ArrayList<>(), new ArrayList<>());

        Equipment equipment1 = new Equipment("telemetry");
        Equipment equipment2 = new Equipment("oxygen");

        Room roomWithEquipment = new Room("Room with Equipment", department1, 1, Room.RoomGender.SAME_GENDER, new ArrayList<>(), new ArrayList<>());

        RequiredEquipment requiredEquipment1 = new RequiredEquipment(1, patient, equipment1);
        RequiredEquipment requiredEquipment2 = new RequiredEquipment(2, patient, equipment2);

        patient.getRequiredEquipments().addAll(List.of(requiredEquipment1, requiredEquipment2));

        Bed bed = new Bed(1, roomWithEquipment, 1);
        PatientAdmission patientAdmission = new PatientAdmission(1, patient, night1, night1, specialization1, true);
        BedDesignation bedDesignation = new BedDesignation(1, patientAdmission, bed);


        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::patientHasRequiredEquipment)
                .given(bedDesignation, requiredEquipment1, requiredEquipment2)
                .penalizesBy(2);
    }

    @Test
    public void preferredEquipmentTest1()
    {
        Patient patient = new Patient("A", Patient.PatientGender.FEMALE, 1, new ArrayList<>(), new ArrayList<>());

        Equipment equipment1 = new Equipment("telemetry");
        Equipment equipment2 = new Equipment("oxygen");

        Room roomWithEquipment = new Room("Room with Equipment", department1, 1, Room.RoomGender.SAME_GENDER, new ArrayList<>(), new ArrayList<>());

        RoomEquipment roomEquipment1 = new RoomEquipment(1, roomWithEquipment, equipment1);
        RoomEquipment roomEquipment2 = new RoomEquipment(2, roomWithEquipment, equipment2);

        roomWithEquipment.getRoomEquipments().addAll(List.of(roomEquipment1, roomEquipment2));

        PreferredEquipment preferredEquipment1 = new PreferredEquipment(1, patient, equipment1);
        PreferredEquipment preferredEquipment2 = new PreferredEquipment(2, patient, equipment2);

        patient.getPreferredEquipments().addAll(List.of(preferredEquipment1, preferredEquipment2));

        Bed bed = new Bed(1, roomWithEquipment, 1);
        PatientAdmission patientAdmission = new PatientAdmission(1, patient, night1, night3, specialization1, true);
        BedDesignation bedDesignation = new BedDesignation(1, patientAdmission, bed);


        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::patientHasPreferredEquipment)
                .given(bedDesignation, preferredEquipment1, preferredEquipment2, roomEquipment1, roomEquipment2)
                .penalizesBy(0);
    }

    @Test
    public void preferredEquipmentTest2()
    {
        Patient patient = new Patient("A", Patient.PatientGender.FEMALE, 1, new ArrayList<>(), new ArrayList<>());

        Equipment equipment1 = new Equipment("telemetry");
        Equipment equipment2 = new Equipment("oxygen");

        Room roomWithEquipment = new Room("Room with Equipment", department1, 1, Room.RoomGender.SAME_GENDER, new ArrayList<>(), new ArrayList<>());

        PreferredEquipment preferredEquipment1 = new PreferredEquipment(1, patient, equipment1);
        PreferredEquipment preferredEquipment2 = new PreferredEquipment(2, patient, equipment2);

        patient.getPreferredEquipments().addAll(List.of(preferredEquipment1, preferredEquipment2));

        Bed bed = new Bed(1, roomWithEquipment, 1);
        PatientAdmission patientAdmission = new PatientAdmission(1, patient, night1, night1, specialization1, true);
        BedDesignation bedDesignation = new BedDesignation(1, patientAdmission, bed);


        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::patientHasPreferredEquipment)
                .given(bedDesignation, preferredEquipment1, preferredEquipment2)
                .penalizesBy(2);
    }

    @Test
    public void preferredRoomCapacityTest1()
    {
        Patient patient = new Patient("A", Patient.PatientGender.FEMALE, 2, new ArrayList<>(), new ArrayList<>());

        PatientAdmission patientAdmission = new PatientAdmission(1, patient, night1, night3, specialization1, true);

        Room room = new Room("Same Gender Room", department1, 2, Room.RoomGender.SAME_GENDER, new ArrayList<>(), new ArrayList<>());

        Bed bed1 = new Bed(1, room, 1);

        BedDesignation bedDesignation = new BedDesignation(1, patientAdmission, bed1);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::preferredRoomCapacity)
                .given(bedDesignation)
                .penalizesBy(0);
    }

    @Test
    public void preferredRoomCapacityTest2()
    {
        Patient patient = new Patient("A", Patient.PatientGender.FEMALE, 1, new ArrayList<>(), new ArrayList<>());

        PatientAdmission patientAdmission = new PatientAdmission(1, patient, night1, night1, specialization1, true);

        Room room = new Room("Same Gender Room", department1, 2, Room.RoomGender.SAME_GENDER, new ArrayList<>(), new ArrayList<>());

        Bed bed1 = new Bed(1, room, 1);

        BedDesignation bedDesignation = new BedDesignation(1, patientAdmission, bed1);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::preferredRoomCapacity)
                .given(bedDesignation)
                .penalizesBy(1);
    }
}
