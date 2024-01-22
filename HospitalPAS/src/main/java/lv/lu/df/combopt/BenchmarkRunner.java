package lv.lu.df.combopt;

import ai.timefold.solver.benchmark.api.PlannerBenchmarkFactory;
import lv.lu.df.combopt.domain.PatientAdmissionSchedule;
import lv.lu.df.combopt.domain.PatientAdmissionScheduleJsonIO;

import java.io.File;

public class BenchmarkRunner {
    public static void main(String[] args) {
        PlannerBenchmarkFactory benchmarkFactoryFromXML = PlannerBenchmarkFactory
                .createFromXmlResource("benchmarkConfiguration.xml");

        PatientAdmissionScheduleJsonIO routingSolutionJsonIO = new PatientAdmissionScheduleJsonIO();
        routingSolutionJsonIO.write(PatientAdmissionSchedule.generateData(50),
                new File("data/testData3.json"));
/*
        PlannerBenchmark benchmark = benchmarkFactoryFromXML.buildPlannerBenchmark();

        benchmark.benchmarkAndShowReportInBrowser();*/
    }
}
