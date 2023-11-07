package org.recruiters;

import org.applicants.Applicant;
import org.job_positions.JobPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecruiterTest {
    JobPosition jobPosition;
    Recruiter recruiter;

    @BeforeEach
    void init() {
        jobPosition = new JobPosition("QA Automation Engineer", 55000.0, 65000.0, "Berlin", "Tech", "role1");
        recruiter = new Recruiter("Tom");
    }

    @Test
    void givenNullJobPosition_assignJobPosition() {
        int managedPositionBeforeAction = recruiter.getJobPositionsManaged().size();
        recruiter.assignJobPosition(null);
        assertEquals(managedPositionBeforeAction, recruiter.getJobPositionsManaged().size());
    }

    @Test
    void givenRecruiterContainsRoleAndIndustry_isSpecializedFor() {
        recruiter.addSpecializedRole("role1");
        recruiter.addSpecializedIndustry("Tech");
        assertTrue(recruiter.isSpecializedFor(jobPosition));
    }

    @Test
    void givenRecruiterNotContainsRole_isSpecializedFor2() {
        jobPosition.setRole("role2");
        recruiter.addSpecializedRole("role1");
        recruiter.addSpecializedIndustry("Tech");
        assertFalse(recruiter.isSpecializedFor(jobPosition));
    }

    @Test
    void givenRecruiterNotContainsIndustry_isSpecializedFor3() {
        jobPosition.setIndustry("Medicine");
        recruiter.addSpecializedRole("role1");
        recruiter.addSpecializedIndustry("Tech");
        assertFalse(recruiter.isSpecializedFor(jobPosition));
    }

    @Test
    void givenRecruiterNotContainsRoleAndIndustry_isSpecializedFor4() {
        jobPosition.setRole("role2");
        jobPosition.setIndustry("Medicine");
        recruiter.addSpecializedRole("role1");
        recruiter.addSpecializedIndustry("Tech");
        assertFalse(recruiter.isSpecializedFor(jobPosition));
    }
}