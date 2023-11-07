package org.applicants;

import java.util.ArrayList;
import java.util.List;

public class Applicant {
    private String name;
    private List<String> previousCompanies;
    private String currentCity;
    private String preferredLocation;
    double expectedSalary;
    String status;

    public Applicant(String name, String currentCity, String preferredLocation, double expectedSalary, String status) {
        this.name = name;
        this.currentCity = currentCity;
        this.preferredLocation = preferredLocation;
        this.expectedSalary = expectedSalary;
        this.status = status;
        previousCompanies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPreviousCompanies() {
        return previousCompanies;
    }

    public void addPreviousCompany(String company){
        previousCompanies.add(company);
    }

    public void setPreviousCompanies(List<String> previousCompanies) {
        this.previousCompanies = previousCompanies;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public double getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(double expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isRelocationPreferred(){
        return (!currentCity.equals(preferredLocation));
    }

    @Override
    public String toString(){
        return "name: " + name + ", current city: " + currentCity
                + ", preferred location: " + preferredLocation
                + ", expected salary: " + expectedSalary
                + ", status: " + status + ", previous companies: "
                + String.join(", ", previousCompanies)
                + "\n";
    }
}
