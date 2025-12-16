package per.app.traveler;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "travelers")
public class Traveler {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String firstName;
    private String lastName;
    private String nationality;

    private LocalDate dateOfBirth;

    @Column(unique = true)
    private String passportNumber;

    public Long getId() { return id; }
    
    public void setId(Long id) { this.id = id; }

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
