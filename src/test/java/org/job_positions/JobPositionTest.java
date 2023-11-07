package org.job_positions;

import org.applicants.Applicant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JobPositionTest {
    JobPosition jobPosition;
    Applicant applicant;

    @BeforeEach
    void init() {
        jobPosition = new JobPosition("QA Automation Engineer", 55000.0, 65000.0, "Berlin", "Tech", "role1");
        applicant = new Applicant("Natallia", "Berlin", "Berlin", 65000.0, "Open");
    }

    @Test
    void givenExpectedSalaryEqualsEndRange_isWithinBudget() {
        assertTrue(jobPosition.isWithinBudget(applicant));
    }

    @Test
    void givenExpectedSalaryEqualsStartRange_isWithinBudget() {
        applicant.setExpectedSalary(55000.0);
        assertTrue(jobPosition.isWithinBudget(applicant));
    }

    @Test
    void givenExpectedSalaryBelowStartRange_isWithinBudget() {
        applicant.setExpectedSalary(50000.0);
        assertFalse(jobPosition.isWithinBudget(applicant));
    }

    @Test
    void givenExpectedSalaryAboveEndRange_isWithinBudget() {
        applicant.setExpectedSalary(65000.5);
        assertFalse(jobPosition.isWithinBudget(applicant));
    }

    @Test
    void givenExpectedSalaryInRange_isWithinBudget() {
        applicant.setExpectedSalary(60000.0);
        assertTrue(jobPosition.isWithinBudget(applicant));
    }

    @Test
    void givenNullApplicant_addRelevantApplicant() {
        int applicantsBeforeAction = jobPosition.getRelevantApplicants().size();
        jobPosition.addRelevantApplicant(null);
        assertEquals(applicantsBeforeAction, jobPosition.getRelevantApplicants().size());
    }

    @Test
    void givenValidApplicant_addRelevantApplicant() {
        int expectedApplicants = jobPosition.getRelevantApplicants().size() + 1;
        jobPosition.addRelevantApplicant(applicant);
        assertEquals(applicant,jobPosition.getRelevantApplicants().get(0));
        assertEquals(expectedApplicants, jobPosition.getRelevantApplicants().size());
    }

    @Test
    void givenInvalidApplicant_addRelevantApplicant() {
        Applicant applicant2 = new Applicant("Jon", "Berlin", "Berlin", 165000.0, "Reviewed");
        int expectedApplicants = jobPosition.getRelevantApplicants().size();
        jobPosition.addRelevantApplicant(applicant2);
        assertFalse(jobPosition.getRelevantApplicants().contains(applicant2));
        assertEquals(expectedApplicants, jobPosition.getRelevantApplicants().size());
    }

    @Test
    void givenAlreadyAddedApplicant_addRelevantApplicant() {
        int expectedApplicants = jobPosition.getRelevantApplicants().size() + 1;
        jobPosition.addRelevantApplicant(applicant);
        jobPosition.addRelevantApplicant(applicant);
        assertEquals(expectedApplicants, jobPosition.getRelevantApplicants().size());
        assertEquals(applicant,jobPosition.getRelevantApplicants().get(0));
    }
}