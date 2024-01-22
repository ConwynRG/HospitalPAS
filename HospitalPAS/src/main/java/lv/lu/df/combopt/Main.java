package lv.lu.df.combopt;

import ai.timefold.solver.core.api.score.ScoreExplanation;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.solver.SolutionManager;
import ai.timefold.solver.core.api.solver.Solver;
import ai.timefold.solver.core.api.solver.SolverFactory;
import lv.lu.df.combopt.domain.PatientAdmissionSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        PatientAdmissionSchedule problem = PatientAdmissionSchedule.generateData(25);

        SolverFactory<PatientAdmissionSchedule> solverFactoryFromXML = SolverFactory
                .createFromXmlResource("solverInitialConfiguration.xml");

        Solver<PatientAdmissionSchedule> solver = solverFactoryFromXML.buildSolver();
        PatientAdmissionSchedule solution = solver.solve(problem);

        SolutionManager<PatientAdmissionSchedule, HardSoftScore> solutionManager = SolutionManager.create(solverFactoryFromXML);
        ScoreExplanation<PatientAdmissionSchedule, HardSoftScore> scoreExplanation = solutionManager.explain(solution);

        LOGGER.info(scoreExplanation.getSummary());
        solution.print();
    }
}