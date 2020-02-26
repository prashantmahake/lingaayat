package in.lingayat.we.controllers;

import in.lingayat.we.models.*;
import in.lingayat.we.payload.ApiResponse;
import in.lingayat.we.payload.UserBasicEditRequest;
import in.lingayat.we.payload.UserSummary;
import in.lingayat.we.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

        if (user != null ) {
            user.setFirstName(userFromRequest.getFirstName());
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
        UserPersonalDetails checkIfExists = userPersonalDetailsRepository.findByUser(user);

        if (user != null && checkIfExists != null) {
            userPersonalDetails.setUser(user);
            userPersonalDetails.setId(checkIfExists.getId());
            userPersonalDetailsRepository.save(userPersonalDetails);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/personalDetails")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Personal details updated against user "));

        } else if (checkIfExists == null && user != null) {
            userPersonalDetails.setUser(user);
            userPersonalDetailsRepository.save(userPersonalDetails);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/personalDetails")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Personal details added against new user "));

        } else {
            return new ResponseEntity<>(new ApiResponse(false, "No user found"), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/user/get/personalDetails")
    @PreAuthorize("hasRole('USER')")
    public UserPersonalDetails getUserPersonalDetails(@CurrentUser UserPrincipal currentUser) {

        User user = userRepository.findByEmail(currentUser.getEmail());


        return userPersonalDetailsRepository.findByUser(user);
    }

    @PostMapping("/user/save/educationalDetails")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> saveEducationalDetails(@CurrentUser UserPrincipal currentUser, @RequestBody UserEducationalDetails userEducationalDetails) {

        User user = userRepository.findByEmail(currentUser.getEmail());
        UserEducationalDetails checkIfExists = userEducationalRepository.findByUser(user);

        if (user != null && checkIfExists != null) {
            userEducationalDetails.setUser(user);
            userEducationalDetails.setId(checkIfExists.getId());
            userEducationalRepository.save(userEducationalDetails);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/educationalDetails")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Educational details updated against user "));

        } else if (checkIfExists == null && user != null) {
            userEducationalDetails.setUser(user);
            userEducationalRepository.save(userEducationalDetails);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/educationalDetails")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Educational details added against new user "));

        } else {
            return new ResponseEntity<>(new ApiResponse(false, "No user found"), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/user/get/educationalDetails")
    @PreAuthorize("hasRole('USER')")
    public UserEducationalDetails getUserEducationalDetails(@CurrentUser UserPrincipal currentUser) {

        User user = userRepository.findByEmail(currentUser.getEmail());
        return userEducationalRepository.findByUser(user);
    }

    @PostMapping("/user/save/professionalDetails")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> saveProfessionalDetails(@CurrentUser UserPrincipal currentUser, @RequestBody UserProfessionalDetails userProfessionalDetails) {

        User user = userRepository.findByEmail(currentUser.getEmail());
        UserProfessionalDetails checkIfExists = userProfessionalDetailsRepository.findByUser(user);

        if (user != null && checkIfExists != null) {
            userProfessionalDetails.setUser(user);
            userProfessionalDetails.setId(checkIfExists.getId());
            userProfessionalDetailsRepository.save(userProfessionalDetails);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/professionalDetails")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Professional details updated against user "));

        } else if (checkIfExists == null && user != null) {
            userProfessionalDetails.setUser(user);
            userProfessionalDetailsRepository.save(userProfessionalDetails);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/professionalDetails")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Professional details added against new user "));

        } else {
            return new ResponseEntity<>(new ApiResponse(false, "No user found"), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/user/get/professionalDetails")
    @PreAuthorize("hasRole('USER')")
    public UserProfessionalDetails getUserProfessionalDetails(@CurrentUser UserPrincipal currentUser) {

        User user = userRepository.findByEmail(currentUser.getEmail());
        return userProfessionalDetailsRepository.findByUser(user);
    }

    @PostMapping("/user/save/medicalDetails")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> saveMedicalDetails(@CurrentUser UserPrincipal currentUser, @RequestBody UserMedicalDetails userMedicalDetails) {

        User user = userRepository.findByEmail(currentUser.getEmail());
        UserMedicalDetails checkIfExists = userMedicalRepository.findByUser(user);

        if (user != null && checkIfExists != null) {
            userMedicalDetails.setUser(user);
            userMedicalDetails.setId(checkIfExists.getId());
            userMedicalRepository.save(userMedicalDetails);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/medicalDetails")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Medical details updated against user "));

        } else if (checkIfExists == null && user != null) {
            userMedicalDetails.setUser(user);
            userMedicalRepository.save(userMedicalDetails);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/medicalDetails")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Medical details added against new user "));

        } else {
            return new ResponseEntity<>(new ApiResponse(false, "No user found"), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/user/get/medicalDetails")
    @PreAuthorize("hasRole('USER')")
    public UserMedicalDetails getUserMedicalDetails(@CurrentUser UserPrincipal currentUser) {

        User user = userRepository.findByEmail(currentUser.getEmail());
        return userMedicalRepository.findByUser(user);
    }

    @PostMapping("/user/save/userFamilyDetails")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> saveFamilyDetails(@CurrentUser UserPrincipal currentUser, @RequestBody UserFamilyDetails userFamilyDetails) {

        User user = userRepository.findByEmail(currentUser.getEmail());
        UserFamilyDetails checkIfExists = userFamilyRepository.
                findByUserAndFirstNameAndLastName(user, userFamilyDetails.getFirstName(), userFamilyDetails.getLastName());

        if (user != null && checkIfExists != null) {
            userFamilyDetails.setUser(user);
            userFamilyDetails.setId(checkIfExists.getId());
            userFamilyRepository.save(userFamilyDetails);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/userFamilyDetails")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Family details updated against user "));

        } else if (checkIfExists == null && user != null) {
            userFamilyDetails.setUser(user);
            userFamilyRepository.save(userFamilyDetails);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/userFamilyDetails")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Family details added against new user "));

        } else {
            return new ResponseEntity<>(new ApiResponse(false, "No user found"), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @PostMapping("/user/delete/userFamilyDetails")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteFamilyDetails(@CurrentUser UserPrincipal currentUser, @RequestBody UserFamilyDetails userFamilyDetails) {
        User user = userRepository.findByEmail(currentUser.getEmail());
        UserFamilyDetails checkIfExists = userFamilyRepository.
                findByUserAndFirstNameAndLastName(user, userFamilyDetails.getFirstName(), userFamilyDetails.getLastName());

        if (user != null && checkIfExists != null) {
            userFamilyDetails.setUser(user);
            userFamilyDetails.setId(checkIfExists.getId());
            userFamilyRepository.delete(userFamilyDetails);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/userFamilyDetails")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Family details Deleted against user "));

        }else {
            return new ResponseEntity<>(new ApiResponse(false, "No user found"), HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }

    @GetMapping("/user/get/userFamilyDetails")
    @PreAuthorize("hasRole('USER')")
    public List<UserFamilyDetails> getUserFamilyDetails(@CurrentUser UserPrincipal currentUser) {

        User user = userRepository.findByEmail(currentUser.getEmail());
        return userFamilyRepository.findByUser(user);
    }

    @PostMapping("/user/save/additionalDetails")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> saveAdditionalDetails(@CurrentUser UserPrincipal currentUser, @RequestBody UserAdditionalDetails userAdditionalDetails) {

        User user = userRepository.findByEmail(currentUser.getEmail());
        UserAdditionalDetails checkIfExists = userAdditionalRepository.findByUser(user);

        if (user != null && checkIfExists != null) {
            userAdditionalDetails.setUser(user);
            userAdditionalDetails.setId(checkIfExists.getId());
            userAdditionalRepository.save(userAdditionalDetails);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/additionalDetails")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Additional details updated against user "));

        } else if (checkIfExists == null && user != null) {
            userAdditionalDetails.setUser(user);
            userAdditionalRepository.save(userAdditionalDetails);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/additionalDetails")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Additional details added against new user "));

        } else {
            return new ResponseEntity<>(new ApiResponse(false, "No user found"), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/user/get/additionalDetails")
    @PreAuthorize("hasRole('USER')")
    public UserAdditionalDetails getUserAdditionalDetails(@CurrentUser UserPrincipal currentUser) {

        User user = userRepository.findByEmail(currentUser.getEmail());
        return userAdditionalRepository.findByUser(user);
    }

}
