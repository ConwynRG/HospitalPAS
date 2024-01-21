package lv.lu.df.combopt.solver;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.Constraint;
import ai.timefold.solver.core.api.score.stream.ConstraintFactory;
import ai.timefold.solver.core.api.score.stream.ConstraintProvider;
import lv.lu.df.combopt.domain.BedDesignation;

import java.util.Objects;

import static ai.timefold.solver.core.api.score.stream.Joiners.equal;

public class PatientAdmissionScheduleConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                assignEveryPatientToBed(constraintFactory),
                spentNightInSameBed(constraintFactory),
                patientRequiredSpecializationMet(constraintFactory),
                patientPreferredSpecializationMet(constraintFactory)
        };
    }

    // Hard constraints
    public Constraint assignEveryPatientToBed(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEachIncludingNullVars(BedDesignation.class)
                .filter(badDesignation -> badDesignation.getBed() == null)
                .penalize(HardSoftScore.ONE_HARD, badDesignation -> 1)
                .asConstraint("assignEveryPatientToBed");
    }

    public Constraint spentNightInSameBed(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachUniquePair(BedDesignation.class, equal(BedDesignation::getBed))
                .filter((designation1, designation2) -> designation1.getPatientAdmission().getSharedNightCount(designation2.getPatientAdmission()) > 0)
                .penalize(HardSoftScore.ofHard(1000),
                        (designation1, designation2) -> designation1.getPatientAdmission().getSharedNightCount(designation2.getPatientAdmission()))
                .asConstraint("spentNightInSameBed");
    }

    public Constraint patientRequiredSpecializationMet(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(BedDesignation.class)
                .filter(designation -> designation.getPatientAdmission().getIsSpecializationRequired())
                .filter(designation -> !Objects.equals(designation.getDepartmentSpecialization().getName(), designation.getPatientAdmission().getSpecialization().getName()))
                .penalize(HardSoftScore.ofHard(10), designation -> designation.getPatientAdmission().getNightsSpent())
                .asConstraint("patientRequiredSpecializationMet");
    }

    //Soft constraints
    public Constraint patientPreferredSpecializationMet(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(BedDesignation.class)
                .filter(designation -> !designation.getPatientAdmission().getIsSpecializationRequired())
                .filter(designation -> !Objects.equals(designation.getDepartmentSpecialization().getName(), designation.getPatientAdmission().getSpecialization().getName()))
                .penalize(HardSoftScore.ofSoft(10), designation -> designation.getPatientAdmission().getNightsSpent())
                .asConstraint("patientPreferredSpecializationMet");
    }
}
