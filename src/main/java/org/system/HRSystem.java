package org.system;

import org.applicants.Applicant;
import org.job_positions.JobPosition;
import org.recruiters.Recruiter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class HRSystem {
    public static final String REPORT_FILE = "src/main/resources/report.txt";
    List<Applicant> applicants;
    List<JobPosition> jobPositions;
    List<Recruiter> recruiters;

    public HRSystem() {
        applicants = new ArrayList<>();
        jobPositions = new ArrayList<>();
        recruiters = new ArrayList<>();
    }

    public List<Applicant> getApplicants() {
        return applicants;
    }

    public List<JobPosition> getJobPositions() {
        return jobPositions;
    }

    public List<Recruiter> getRecruiters() {
        return recruiters;
    }

    public void addJobPosition(JobPosition jobPosition) {
        if (jobPosition != null && (jobPosition.getOfferedSalaryRangeEnd() >= jobPosition.getOfferedSalaryRangeStart())) {
            if (!jobPositions.contains(jobPosition)) {
                jobPositions.add(jobPosition);
            } else {
                System.out.println("Job position already exists in the system");
            }
        } else {
            System.out.println("Invalid job position");
        }
    }

    public void addRecruiter(Recruiter recruiter) {
        if (recruiter != null) {
            if (!recruiters.contains(recruiter)) {
                recruiters.add(recruiter);
            } else {
                System.out.println("Recruiter already exists in the system");
            }
        } else {
            System.out.println("Invalid recruiter");
        }
    }

    public void addApplicant(Applicant applicant) {
        if (applicant != null) {
            if (!applicants.contains(applicant)) {
                applicants.add(applicant);
            } else {
                System.out.println("Applicant already exists in the system");
            }
        } else {
            System.out.println("Invalid applicant");
        }
    }

    public void generateReports() {
        StringBuilder report = new StringBuilder();
        report.append("REPORT").append(System.lineSeparator())
                .append("Number of applicants in the system: ")
                .append(applicants.size())
                .append(System.lineSeparator()).append("Number of job positions in the system: ")
                .append(jobPositions.size())
                .append(System.lineSeparator())
                .append("Number of applicants per job position: ")
                .append(System.lineSeparator());
        getApplicantsByJobPosition()
                .forEach((key, value) ->
                        report.append(key.getTitle())
                                .append(" - ")
                                .append(String.join(", ", value.stream().map(Applicant::getName).toList()))
                                .append(System.lineSeparator()));
        report.append("Number of applicants per status: ")
                .append(System.lineSeparator());
        getApplicantsCountPerStatus()
                .forEach((key, value) -> report
                        .append(key)
                        .append(" - ")
                        .append(value)
                        .append(System.lineSeparator()));
        System.out.println(report);
        try (FileWriter fileWriter = new FileWriter(REPORT_FILE);) {
            fileWriter.write(report.toString());
        } catch (IOException e) {
            System.out.println("Write report to file is failed.");
            e.printStackTrace();
        }
    }

    public Map<String, Long> getApplicantsCountPerStatus() {
        return applicants.stream().collect(Collectors.groupingBy(Applicant::getStatus, Collectors.counting()));
    }

    public Map<JobPosition, List<Applicant>> getApplicantsByJobPosition() {
        Map<JobPosition, List<Applicant>> applicantsByJobPosition = new HashMap<>();
        for (JobPosition job : jobPositions) {
            for (Applicant applicant : applicants) {
                if (job.isWithinBudget(applicant) && job.getLocation().equals(applicant.getPreferredLocation())) {
                    if (applicantsByJobPosition.containsKey(job)) {
                        applicantsByJobPosition.get(job).add(applicant);
                    } else {
                        applicantsByJobPosition.put(job, Collections.singletonList(applicant));
                    }
                }
            }
        }
        return applicantsByJobPosition;
    }
}