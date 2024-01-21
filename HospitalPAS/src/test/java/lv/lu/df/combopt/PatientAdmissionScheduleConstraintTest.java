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

    Specialization specialization1 = new Specialization("General");
    Specialization specialization2 = new Specialization("Intense Care");

    Department department1 = new Department("General Ward Dep.", specialization1, new ArrayList<>());
    Department department2 = new Department("Intense Care Dep.", specialization2, new ArrayList<>());

    Room room1 = new Room("Room 1", department1, 2, Room.RoomGender.FEMALE, new ArrayList<>(), new ArrayList<>());
    Room room2 = new Room("Room 2", department2, 2, Room.RoomGender.MALE, new ArrayList<>(), new ArrayList<>());

    Bed bed1 = new Bed(room1, 1);
    Bed bed2 = new Bed(room1, 2);
    Bed bed3 = new Bed(room2, 1);
    Bed bed4 = new Bed(room2, 2);

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
        PatientAdmission patientAdmissionA = new PatientAdmission(patientA, night1, night2, specialization2, true);
        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, null);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::assignEveryPatientToBed)
                .given(bedDesignationA)
                .penalizesBy(1);
    }

    @Test
    public void sameBedAssignmentTest1() {
        PatientAdmission patientAdmissionA = new PatientAdmission(patientA, night1, night2, specialization2, true);
        PatientAdmission patientAdmissionB = new PatientAdmission(patientB, night1, night2, specialization2, false);

        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed1);
        BedDesignation bedDesignationB = new BedDesignation(2, patientAdmissionB, bed1);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::spentNightInSameBed)
                .given(bedDesignationA, bedDesignationB)
                .penalizesBy(2);
    }

    @Test
    public void sameBedAssignmentTest2() {
        PatientAdmission patientAdmissionA = new PatientAdmission(patientA, night2, night3, specialization2, true);
        PatientAdmission patientAdmissionB = new PatientAdmission(patientB, night1, night2, specialization2, false);

        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed1);
        BedDesignation bedDesignationB = new BedDesignation(2, patientAdmissionB, bed1);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::spentNightInSameBed)
                .given(bedDesignationA, bedDesignationB)
                .penalizesBy(1);
    }

    @Test
    public void sameBedAssignmentTest3() {
        PatientAdmission patientAdmissionA = new PatientAdmission(patientA, night3, night3, specialization2, true);
        PatientAdmission patientAdmissionB = new PatientAdmission(patientB, night1, night2, specialization2, false);

        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed1);
        BedDesignation bedDesignationB = new BedDesignation(2, patientAdmissionB, bed1);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::spentNightInSameBed)
                .given(bedDesignationA, bedDesignationB)
                .penalizesBy(0);
    }

    @Test
    public void patientSpecializationMetTest1()
    {
        PatientAdmission patientAdmissionA = new PatientAdmission(patientA, night3, night3, specialization2, true);
        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed1);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::patientRequiredSpecializationMet)
                .given(bedDesignationA)
                .penalizesBy(1);
    }

    @Test
    public void patientSpecializationMetTest2()
    {
        PatientAdmission patientAdmissionA = new PatientAdmission(patientA, night3, night3, specialization1, true);
        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed1);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::patientRequiredSpecializationMet)
                .given(bedDesignationA)
                .penalizesBy(0);
    }

    @Test
    public void patientSpecializationMetTest3()
    {
        PatientAdmission patientAdmissionA = new PatientAdmission(patientA, night3, night3, specialization2, false);
        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed1);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::patientRequiredSpecializationMet)
                .given(bedDesignationA)
                .penalizesBy(0);
    }

    @Test
    public void patientPreferredSpecializationMetTest1()
    {
        PatientAdmission patientAdmissionA = new PatientAdmission(patientA, night3, night3, specialization2, false);
        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed1);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::patientPreferredSpecializationMet)
                .given(bedDesignationA)
                .penalizesBy(1);
    }

    @Test
    public void patientPreferredSpecializationMetTest2()
    {
        PatientAdmission patientAdmissionA = new PatientAdmission(patientA, night3, night3, specialization2, false);
        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed3);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::patientPreferredSpecializationMet)
                .given(bedDesignationA)
                .penalizesBy(0);
    }

    @Test
    public void patientPreferredSpecializationMetTest3()
    {
        PatientAdmission patientAdmissionA = new PatientAdmission(patientA, night3, night3, specialization1, true);
        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed3);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::patientPreferredSpecializationMet)
                .given(bedDesignationA)
                .penalizesBy(0);
    }

    @Test
    public void malePatientsInFemaleRoomTest1()
    {
        PatientAdmission patientAdmissionB = new PatientAdmission(patientB, night3, night3, specialization1, true);
        BedDesignation bedDesignationB = new BedDesignation(1, patientAdmissionB, bed1);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::malePatientsInFemaleRoom)
                .given(bedDesignationB)
                .penalizesBy(1);
    }

    @Test
    public void malePatientsInFemaleRoomTest2()
    {
        PatientAdmission patientAdmissionB = new PatientAdmission(patientB, night3, night3, specialization1, true);
        BedDesignation bedDesignationB = new BedDesignation(1, patientAdmissionB, bed3);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::malePatientsInFemaleRoom)
                .given(bedDesignationB)
                .penalizesBy(0);
    }

    @Test
    public void femalePatientsInMaleRoomTest1()
    {
        PatientAdmission patientAdmissionA = new PatientAdmission(patientA, night3, night3, specialization1, true);
        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed3);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::femalePatientsInMaleRoom)
                .given(bedDesignationA)
                .penalizesBy(1);
    }

    @Test
    public void femalePatientsInMaleRoomTest2()
    {
        PatientAdmission patientAdmissionA = new PatientAdmission(patientA, night3, night3, specialization1, true);
        BedDesignation bedDesignationA = new BedDesignation(1, patientAdmissionA, bed1);

        constraintVerifier.verifyThat(PatientAdmissionScheduleConstraintProvider::femalePatientsInMaleRoom)
                .given(bedDesignationA)
                .penalizesBy(0);
    }


}
