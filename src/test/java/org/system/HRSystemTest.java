package org.system;

import org.applicants.Applicant;
import org.job_positions.JobPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.recruiters.Recruiter;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class HRSystemTest {
    HRSystem hrSystem;
    Recruiter recruiter;
    JobPosition jobPosition;
    JobPosition jobPosition2;
    Applicant applicant;
    Applicant applicant2;

    @BeforeEach
    void init() {
        hrSystem = new HRSystem();
        jobPosition = new JobPosition("QA Automation Engineer", 55000.0, 65000.0, "Berlin", "Tech", "role1");
        jobPosition2 = new JobPosition("CTO", 120000.0, 180000.0, "Berlin", "Tech", "role2");
        applicant = new Applicant("Natallia", "Berlin", "Berlin", 65000.0, "Open");
        recruiter = new Recruiter("Tom");
        applicant2 = new Applicant("Jon", "Berlin", "Berlin", 165000.0, "Reviewed");
        jobPosition.addRelevantApplicant(applicant);
        jobPosition2.addRelevantApplicant(applicant2);
    }
    @Test
    void givenNullJobPosition_addJobPosition() {
        hrSystem.addJobPosition(null);
        assertTrue(hrSystem.getJobPositions().isEmpty());
    }

    @Test
    void givenValidJobPosition_addJobPosition() {
        hrSystem.addJobPosition(jobPosition);
        assertEquals(1, hrSystem.getJobPositions().size());
        assertEquals(jobPosition, hrSystem.getJobPositions().get(0));
    }

    @Test
    void givenAlreadyAddedJobPosition_addJobPosition() {
        hrSystem.addJobPosition(jobPosition);
        hrSystem.addJobPosition(jobPosition);
        assertEquals(1, hrSystem.getJobPositions().size());
        assertEquals(jobPosition, hrSystem.getJobPositions().get(0));
    }

    @Test
    void givenNullRecruiter_addRecruiter() {
        hrSystem.addRecruiter(null);
        assertTrue(hrSystem.getRecruiters().isEmpty());
    }

    @Test
    void givenValidRecruiter_addRecruiter() {
        hrSystem.addRecruiter(recruiter);
        assertEquals(1, hrSystem.getRecruiters().size());
        assertEquals(recruiter, hrSystem.getRecruiters().get(0));
    }

    @Test
    void givenAlreadyAddedRecruiter_addRecruiter() {
        hrSystem.addRecruiter(recruiter);
        hrSystem.addRecruiter(recruiter);
        assertEquals(1, hrSystem.getRecruiters().size());
        assertEquals(recruiter, hrSystem.getRecruiters().get(0));
    }

    @Test
    void givenNullApplicant_addApplicant() {
        hrSystem.addApplicant(null);
        assertTrue(hrSystem.getApplicants().isEmpty());
    }

    @Test
    void givenValidApplicant_addApplicant() {
        hrSystem.addApplicant(applicant);
        assertEquals(1, hrSystem.getApplicants().size());
        assertEquals(applicant, hrSystem.getApplicants().get(0));
    }

    @Test
    void givenAlreadyAddedApplicant_addApplicant() {
        hrSystem.addApplicant(applicant);
        hrSystem.addApplicant(applicant);
        assertEquals(1, hrSystem.getApplicants().size());
        assertEquals(applicant, hrSystem.getApplicants().get(0));
    }

    @Test
    void getApplicantsCountPerStatus() {
        hrSystem.addApplicant(applicant);
        hrSystem.addApplicant(applicant2);
        Map<String, Long> expectedResult = new HashMap<>();
        expectedResult.put(applicant.getStatus(), 1L);
        expectedResult.put(applicant2.getStatus(), 1L);
        assertEquals(expectedResult, hrSystem.getApplicantsCountPerStatus());
    }

    @Test
    void getApplicantsByJobPosition() {
        hrSystem.addApplicant(applicant);
        hrSystem.addApplicant(applicant2);
        hrSystem.addJobPosition(jobPosition);
        hrSystem.addJobPosition(jobPosition2);
        Map<String, List<String>> expectedResult = new HashMap<>();
        expectedResult.put(jobPosition.getTitle(), Collections.singletonList(jobPosition.getRelevantApplicants().get(0).getName()));
        expectedResult.put(jobPosition2.getTitle(), Collections.singletonList(jobPosition2.getRelevantApplicants().get(0).getName()));
        assertEquals(expectedResult, hrSystem.getApplicantsByJobPosition());

    }

    @Test
    void generateReportTest(){
        Recruiter recruiter2 = new Recruiter("Nina");

        hrSystem.addApplicant(applicant);
        hrSystem.addApplicant(applicant2);
        hrSystem.addRecruiter(recruiter);
        hrSystem.addRecruiter(recruiter2);
        hrSystem.addJobPosition(jobPosition);
        hrSystem.addJobPosition(jobPosition2);
        hrSystem.generateReports();
        StringBuilder expectedReport = new StringBuilder();
        expectedReport.append("REPORT").append(System.lineSeparator())
                .append("Number of applicants in the system: ")
                .append(2)
                .append(System.lineSeparator()).append("Number of job positions in the system: ")
                .append(2)
                .append(System.lineSeparator())
                .append("Number of applicants per job position: ")
                .append(System.lineSeparator())
                .append(jobPosition.getTitle())
                .append(" - ")
                .append(applicant.getName())
                .append(System.lineSeparator())
                .append(jobPosition2.getTitle())
                .append(" - ")
                .append(applicant2.getName())
                .append(System.lineSeparator())
                .append("Number of applicants per status: ")
                .append(System.lineSeparator())
                .append(applicant2.getStatus())
                .append(" - ")
                .append(1)
                .append(System.lineSeparator())
                .append(applicant.getStatus())
                .append(" - ")
                .append(1)
                .append(System.lineSeparator());
        try(BufferedReader br = new BufferedReader(new FileReader(HRSystem.REPORT_FILE))) {
            StringBuilder loadedReport = new StringBuilder();
            String line = br.readLine();
            while (line!=null){
                loadedReport.append(line).append(System.lineSeparator());
                line=br.readLine();
            }
            assertEquals(expectedReport.toString(), loadedReport.toString());
            File reportFile = new File(HRSystem.REPORT_FILE);
            if(reportFile.exists()){
                reportFile.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}