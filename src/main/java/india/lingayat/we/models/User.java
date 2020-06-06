package india.lingayat.we.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rajeevkumarsingh on 01/08/17.
 */

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String firstName;

    @NotBlank
    @Size(max = 40)
    private String lastName;

    @NotBlank
    @Size(max = 40)
    private String middleName;

    @NotBlank
    @Size(max = 40)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    @NotBlank
    @Size(max = 15)
    private String contact;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany()
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(cascade = CascadeType.ALL)
    private UserAdditionalDetails userAdditionalDetails;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(cascade = CascadeType.ALL)
    private UserEducationalDetails userEducationalDetails;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(cascade = CascadeType.ALL)
    private Set<UserFamilyDetails> userFamilyDetails;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(cascade = CascadeType.ALL)
    private UserImages userImages;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(cascade = CascadeType.ALL)
    private UserMedicalDetails userMedicalDetails;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(cascade = CascadeType.ALL)
    private UserPersonalDetails userPersonalDetails;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(cascade = CascadeType.ALL)
    private UserProfessionalDetails userProfessionalDetails;

    public User() {

    }

    public User(@NotBlank @Size(max = 40) String firstName, @NotBlank @Size(max = 40) String lastName, @NotBlank @Size(max = 40) String middleName, @NotBlank @Size(max = 40) String username, @NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(max = 100) String password, @NotBlank @Size(max = 15) String contact) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.contact = contact;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public UserPersonalDetails getUserPersonalDetails() {
        return userPersonalDetails;
    }

    public void setUserPersonalDetails(UserPersonalDetails userPersonalDetails) {
        this.userPersonalDetails = userPersonalDetails;
    }

    public UserAdditionalDetails getUserAdditionalDetails() {
        return userAdditionalDetails;
    }

    public void setUserAdditionalDetails(UserAdditionalDetails userAdditionalDetails) {
        this.userAdditionalDetails = userAdditionalDetails;
    }

    public UserEducationalDetails getUserEducationalDetails() {
        return userEducationalDetails;
    }

    public void setUserEducationalDetails(UserEducationalDetails userEducationalDetails) {
        this.userEducationalDetails = userEducationalDetails;
    }

    public Set<UserFamilyDetails> getUserFamilyDetails() {
        return userFamilyDetails;
    }

    public void setUserFamilyDetails(UserFamilyDetails userFamilyDetails) {
        Set<UserFamilyDetails> familyDetails = getUserFamilyDetails();
        familyDetails.add(userFamilyDetails);
        this.userFamilyDetails = familyDetails;
    }

    public UserImages getUserImages() {
        return userImages;
    }

    public void setUserImages(UserImages userImages) {
        this.userImages = userImages;
    }

    public UserMedicalDetails getUserMedicalDetails() {
        return userMedicalDetails;
    }

    public void setUserMedicalDetails(UserMedicalDetails userMedicalDetails) {
        this.userMedicalDetails = userMedicalDetails;
    }

    public UserProfessionalDetails getUserProfessionalDetails() {
        return userProfessionalDetails;
    }

    public void setUserProfessionalDetails(UserProfessionalDetails userProfessionalDetails) {
        this.userProfessionalDetails = userProfessionalDetails;
    }
}