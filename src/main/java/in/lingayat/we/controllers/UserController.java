package in.lingayat.we.controllers;

import in.lingayat.we.models.CurrentUser;
import in.lingayat.we.models.User;
import in.lingayat.we.models.UserPersonalDetails;
import in.lingayat.we.models.UserPrincipal;
import in.lingayat.we.payload.ApiResponse;
import in.lingayat.we.payload.UserSummary;
import in.lingayat.we.repositories.UserPersonalDetailsRepository;
import in.lingayat.we.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserPersonalDetailsRepository userPersonalDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

    @PostMapping("/user/save/personalDetails")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> savePersonalDetails(@CurrentUser UserPrincipal currentUser, @RequestBody UserPersonalDetails userPersonalDetails){

        User user = userRepository.findByEmail(currentUser.getEmail());

        if(user != null){
            userPersonalDetails.setUser(user);
            userPersonalDetailsRepository.save(userPersonalDetails);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/save/personalDetails")
                    .buildAndExpand(currentUser.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "Personal details added against user "));

        }
        else{
            return new ResponseEntity<>(new ApiResponse(false, "No user found"), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}
