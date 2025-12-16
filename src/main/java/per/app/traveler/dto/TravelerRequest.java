package per.app.traveler.dto;

import java.time.LocalDate;

public class TravelerRequest {

    private String firstName;
    private String lastName;
    private String nationality;
    private LocalDate dateOfBirth;
    private String passportNumber;

    public String getFirstName() { return firstName; }
    
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getNationality() { return nationality; }
    
    public void setNationality(String nationality) { this.nationality = nationality; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getPassportNumber() { return passportNumber; }
    
    public void setPassportNumber(String passportNumber) { this.passportNumber = passportNumber; }
}
