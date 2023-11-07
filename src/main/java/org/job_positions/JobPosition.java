package org.job_positions;

import org.applicants.Applicant;

import java.util.ArrayList;
import java.util.List;

public class JobPosition {
    private String title;
    private String description;
    private double offeredSalaryRangeStart;
    private double offeredSalaryRangeEnd;
    private List<String> requiredSkills;
    private String location;
    private String industry;
    private String role;

    private List<Applicant> relevantApplicants;

    public JobPosition(String title, double offeredSalaryRangeStart, double offeredSalaryRangeEnd, String location, String industry, String role) {
        this.title = title;
        this.offeredSalaryRangeStart = offeredSalaryRangeStart;
        this.offeredSalaryRangeEnd = offeredSalaryRangeEnd;
        this.location = location;
        this.industry = industry;
        this.role = role;
        requiredSkills = new ArrayList<>();
        relevantApplicants = new ArrayList<>();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getOfferedSalaryRangeStart() {
        return offeredSalaryRangeStart;
    }

    public void setOfferedSalaryRangeStart(double offeredSalaryRangeStart) {
        this.offeredSalaryRangeStart = offeredSalaryRangeStart;
    }

    public double getOfferedSalaryRangeEnd() {
        return offeredSalaryRangeEnd;
    }

    public void setOfferedSalaryRangeEnd(double offeredSalaryRangeEnd) {
        this.offeredSalaryRangeEnd = offeredSalaryRangeEnd;
    }

    public List<Applicant> getRelevantApplicants() {
        return relevantApplicants;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void addRequiredSkill(String skill){
        if(!requiredSkills.contains(skill) && !skill.isEmpty()){
            requiredSkills.add(skill);
        }else System.out.println("Skill already presents");
    }

    public void addRelevantApplicant(Applicant applicant){
        if(applicant!=null){
            if(!relevantApplicants.contains(applicant)){
                if (isWithinBudget(applicant) && getLocation().equals(applicant.getPreferredLocation())) {
                    relevantApplicants.add(applicant);
                }
            }else {
                System.out.println("Job position already contains this applicant");
            }
        }else {
            System.out.println("Invalid applicant");
        }
    }

    public boolean isWithinBudget(Applicant applicant){
        return (offeredSalaryRangeStart<=applicant.getExpectedSalary() && applicant.getExpectedSalary()<=offeredSalaryRangeEnd);
    }
}
