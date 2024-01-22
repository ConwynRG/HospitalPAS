package lv.lu.df.combopt;

import ai.timefold.solver.core.api.score.ScoreExplanation;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.solver.SolutionManager;
import ai.timefold.solver.core.api.solver.Solver;
import ai.timefold.solver.core.api.solver.SolverFactory;
import ai.timefold.solver.core.config.solver.EnvironmentMode;
import ai.timefold.solver.core.config.solver.SolverConfig;
import ai.timefold.solver.core.config.solver.termination.TerminationConfig;
import lv.lu.df.combopt.domain.BedDesignation;
import lv.lu.df.combopt.domain.PatientAdmissionSchedule;
import lv.lu.df.combopt.solver.PatientAdmissionScheduleConstraintProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Test Logger");

        PatientAdmissionSchedule problem = PatientAdmissionSchedule.generateData(50);

        SolverFactory<PatientAdmissionSchedule> solverFactory = SolverFactory.create(
                new SolverConfig()
                        .withSolutionClass(PatientAdmissionSchedule.class)
                        .withEntityClasses(BedDesignation.class)
                        .withConstraintProviderClass(PatientAdmissionScheduleConstraintProvider.class)
                        .withTerminationConfig(new TerminationConfig()
                                .withSecondsSpentLimit(10L))
                        .withEnvironmentMode(EnvironmentMode.FULL_ASSERT)
        );

        Solver<PatientAdmissionSchedule> solver = solverFactory.buildSolver();
        PatientAdmissionSchedule solution = solver.solve(problem);

        SolutionManager<PatientAdmissionSchedule, HardSoftScore> solutionManager = SolutionManager.create(solverFactory);
        ScoreExplanation<PatientAdmissionSchedule, HardSoftScore> scoreExplanation = solutionManager.explain(solution);

        LOGGER.info(scoreExplanation.getSummary());

        solution.print();
    }
}