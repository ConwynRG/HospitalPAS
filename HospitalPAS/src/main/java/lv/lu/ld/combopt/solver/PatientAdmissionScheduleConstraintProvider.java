package lv.lu.ld.combopt.solver;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.Constraint;
import ai.timefold.solver.core.api.score.stream.ConstraintFactory;
import ai.timefold.solver.core.api.score.stream.ConstraintProvider;
import lv.lu.ld.combopt.domain.BedDesignation;

public class PatientAdmissionScheduleConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                assignEveryPatientToABed(constraintFactory)
        };
    }

    public Constraint assignEveryPatientToABed(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEachIncludingNullVars(BedDesignation.class)
                .filter(badDesignation -> badDesignation.getBed() == null)
                .penalize(HardSoftScore.ONE_HARD, badDesignation -> 1)
                .asConstraint("assignEveryPatientToABed");
    }
}
