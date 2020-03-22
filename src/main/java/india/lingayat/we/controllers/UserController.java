package india.lingayat.we.controllers;

import india.lingayat.we.models.*;
import india.lingayat.we.payload.ApiResponse;
import india.lingayat.we.payload.UserBasicEditRequest;
import india.lingayat.we.payload.UserSummary;
import india.lingayat.we.repositories.*;
import india.lingayat.we.models.*;
import india.lingayat.we.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserPersonalDetailsRepository userPersonalDetailsRepository;

    @Autowired
    private UserEducationalRepository userEducationalRepository;

    @Autowired
    private UserProfessionalDetailsRepository userProfessionalDetailsRepository;

    @Autowired
    private UserMedicalRepository userMedicalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFamilyRepository userFamilyRepository;

    @Autowired
    private UserAdditionalRepository userAdditionalRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getFirstName(), currentUser.getLastName(), currentUser.getMiddleName(), currentUser.getContact(), currentUser.getEmail());
        return userSummary;
    }

    @GetMapping("/version")
    public String getCurrentVersion(@CurrentUser UserPrincipal currentUser) {
        return "v1";
    }

    @PostMapping("/user/save/basicDetails")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> savePersonalDetails(@CurrentUser UserPrincipal currentUser, @RequestBody UserBasicEditRequest userFromRequest) {

        User user = userRepository.findByEmail(currentUser.getEmail());

        if (user != null) {
            user.setFirstName(userFromRequest.getFirstName());
            user.setMiddleName(userFromRequest.getMiddleName());
            user.setLastName(userFromRequest.getLastName());
            user.setContact(userFromRequest.getContact());
            user.setEmail(userFromRequest.getEmail());

            userRepository.save(user);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/basicUserDetails")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Basic details updated against user "));

        } else {
            return new ResponseEntity<>(new ApiResponse(false, "No user found"), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    @PostMapping("/user/save/personalDetails")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> savePersonalDetails(@CurrentUser UserPrincipal currentUser, @RequestBody UserPersonalDetails userPersonalDetails) {

        User user = userRepository.findByEmail(currentUser.getEmail());

        if (user != null) {
            user.setUserPersonalDetails(userPersonalDetails);
            userPersonalDetails.setUser(user);
            userRepository.save(user);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/personalDetails")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Personal details updated against user "));
        } else {
            return new ResponseEntity<>(new ApiResponse(false, "No user found"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/get/personalDetails")
    @PreAuthorize("hasRole('USER')")
    public UserPersonalDetails getUserPersonalDetails(@CurrentUser UserPrincipal currentUser) {

        User user = userRepository.findByEmail(currentUser.getEmail());


        return user.getUserPersonalDetails();
    }

    @PostMapping("/user/save/educationalDetails")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> saveEducationalDetails(@CurrentUser UserPrincipal currentUser, @RequestBody UserEducationalDetails userEducationalDetails) {

        User user = userRepository.findByEmail(currentUser.getEmail());

        if (user != null) {
            user.setUserEducationalDetails(userEducationalDetails);
            userEducationalDetails.setUser(user);
            userRepository.save(user);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/educationalDetails")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Educational details updated against user "));
        } else {
            return new ResponseEntity<>(new ApiResponse(false, "No user found"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/user/get/educationalDetails")
    @PreAuthorize("hasRole('USER')")
    public UserEducationalDetails getUserEducationalDetails(@CurrentUser UserPrincipal currentUser) {

        User user = userRepository.findByEmail(currentUser.getEmail());
        return user.getUserEducationalDetails();
    }

    @PostMapping("/user/save/professionalDetails")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> saveProfessionalDetails(@CurrentUser UserPrincipal currentUser, @RequestBody UserProfessionalDetails userProfessionalDetails) {

        User user = userRepository.findByEmail(currentUser.getEmail());

        if (user != null) {
            user.setUserProfessionalDetails(userProfessionalDetails);
            userProfessionalDetails.setUser(user);
            userRepository.save(user);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/professionalDetails")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Professional details updated against user "));
        } else {
            return new ResponseEntity<>(new ApiResponse(false, "No user found"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/user/get/professionalDetails")
    @PreAuthorize("hasRole('USER')")
    public UserProfessionalDetails getUserProfessionalDetails(@CurrentUser UserPrincipal currentUser) {

        User user = userRepository.findByEmail(currentUser.getEmail());
        return user.getUserProfessionalDetails();
    }

    @PostMapping("/user/save/medicalDetails")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> saveMedicalDetails(@CurrentUser UserPrincipal currentUser, @RequestBody UserMedicalDetails userMedicalDetails) {

        User user = userRepository.findByEmail(currentUser.getEmail());

        if (user != null) {
            user.setUserMedicalDetails(userMedicalDetails);
            userMedicalDetails.setUser(user);
            userRepository.save(user);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/medicalDetails")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Medical details updated against user "));
        } else {
            return new ResponseEntity<>(new ApiResponse(false, "No user found"), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/user/get/medicalDetails")
    @PreAuthorize("hasRole('USER')")
    public UserMedicalDetails getUserMedicalDetails(@CurrentUser UserPrincipal currentUser) {

        User user = userRepository.findByEmail(currentUser.getEmail());
        return user.getUserMedicalDetails();
    }

    @PostMapping("/user/save/userFamilyDetails")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> saveFamilyDetails(@CurrentUser UserPrincipal currentUser, @RequestBody UserFamilyDetails userFamilyDetails) {

        User user = userRepository.findByEmail(currentUser.getEmail());

        if (user != null) {
            user.setUserFamilyDetails(userFamilyDetails);
            userFamilyDetails.setUser(user);
            userRepository.save(user);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/familyDetals")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Family details updated against user "));
        } else {
            return new ResponseEntity<>(new ApiResponse(false, "No user found"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/user/delete/userFamilyDetails")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteFamilyDetails(@CurrentUser UserPrincipal currentUser, @RequestBody UserFamilyDetails userFamilyDetails) {
        User user = userRepository.findByEmail(currentUser.getEmail());

        if (user != null) {
//            To-do
//            Delete family details
            userFamilyDetails.setUser(user);
            userFamilyRepository.delete(userFamilyDetails);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/educationalDetails")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Personal details updated against user "));
        } else {
            return new ResponseEntity<>(new ApiResponse(false, "No user found"), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/user/get/userFamilyDetails")
    @PreAuthorize("hasRole('USER')")
    public Set<UserFamilyDetails> getUserFamilyDetails(@CurrentUser UserPrincipal currentUser) {

        User user = userRepository.findByEmail(currentUser.getEmail());
        return user.getUserFamilyDetails();
    }

    @PostMapping("/user/save/additionalDetails")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> saveAdditionalDetails(@CurrentUser UserPrincipal currentUser, @RequestBody UserAdditionalDetails userAdditionalDetails) {

        User user = userRepository.findByEmail(currentUser.getEmail());

        if (user != null) {
            user.setUserAdditionalDetails(userAdditionalDetails);
            userAdditionalDetails.setUser(user);
            userRepository.save(user);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/additionalDetails")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Additional details updated against user "));
        } else {
            return new ResponseEntity<>(new ApiResponse(false, "No user found"), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/user/get/additionalDetails")
    @PreAuthorize("hasRole('USER')")
    public UserAdditionalDetails getUserAdditionalDetails(@CurrentUser UserPrincipal currentUser) {

        User user = userRepository.findByEmail(currentUser.getEmail());
        return user.getUserAdditionalDetails();
    }



}
