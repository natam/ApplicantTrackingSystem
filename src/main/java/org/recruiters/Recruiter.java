package org.recruiters;

import org.applicants.Applicant;
import org.job_positions.JobPosition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Recruiter {
    private String name;
    private List<JobPosition> jobPositionsManaged;
    private Set<String> specializedIndustries;
    private Set<String> specializedRoles;

    public Recruiter(String name){
        this.name = name;
        jobPositionsManaged = new ArrayList<>();
        specializedRoles = new HashSet<>();
        specializedIndustries = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void addSpecializedRole(String role){
        if(!role.isEmpty()){
            specializedRoles.add(role);
        }
    }

    public void addSpecializedIndustry(String industry){
        if(!industry.isEmpty()){
            specializedIndustries.add(industry);
        }
    }

    public Set<String> getSpecializedIndustries() {
        return specializedIndustries;
    }

    public Set<String> getSpecializedRoles() {
        return specializedRoles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JobPosition> getJobPositionsManaged() {
        return jobPositionsManaged;
    }

    public void assignJobPosition(JobPosition jobPosition){
        if(jobPosition!=null) {
            if (!jobPositionsManaged.contains(jobPosition)) {
                jobPositionsManaged.add(jobPosition);
            } else {
                System.out.println("This job position already managed by this recruiter");
            }
        }
    }

    public void reviewApplicant(Applicant applicant){
        System.out.println(applicant.toString());
        applicant.setStatus("Reviewed");
    }

    public boolean isSpecializedFor(JobPosition jobPosition){
        return (specializedIndustries.contains(jobPosition.getIndustry()) && specializedRoles.contains(jobPosition.getRole()));
    }
}
