package lv.lu.df.combopt.domain;

import ai.timefold.solver.jackson.impl.domain.solution.JacksonSolutionFileIO;

public class PatientAdmissionScheduleJsonIO extends JacksonSolutionFileIO<PatientAdmissionSchedule> {
    public PatientAdmissionScheduleJsonIO() {
        super(PatientAdmissionSchedule.class);
    }
}
