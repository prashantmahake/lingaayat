package in.lingayat.we.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import in.lingayat.we.models.CurrentUser;
import in.lingayat.we.models.User;
import in.lingayat.we.models.UserImages;
import in.lingayat.we.models.UserPrincipal;
import in.lingayat.we.payload.ApiResponse;
import in.lingayat.we.payload.UserBasicEditRequest;
import in.lingayat.we.repositories.UserImageRepository;
import in.lingayat.we.repositories.UserRepository;
import in.lingayat.we.services.ClouditonaryImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ClouditonaryImageController {

    @Autowired
    private ClouditonaryImageService clouditonaryImageService;

    @Autowired
    private UserImageRepository userImageRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/save/profileImage")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> uploadImage(@CurrentUser UserPrincipal currentUser, @RequestParam("file") MultipartFile file)  {

        Cloudinary cloudinary = clouditonaryImageService.getCloudinary();
        Map uploadResult = null;
        String responseMessage = "";

        User user = userRepository.findByEmail(currentUser.getEmail());

        try {

            uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        }
        catch (IOException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/upload/image")
                .buildAndExpand("image").toUri();

        System.out.println(uploadResult);
        if(uploadResult.get("url")!=null)
        {
//            responseMessage = "Image Uploaded at:";
            responseMessage += uploadResult.get("url").toString();
            UserImages userImages = new UserImages(uploadResult.get("url").toString(), "Profile", user);

            UserImages checkIfExist = userImageRepository.findByUser(user);

            if(checkIfExist!=null){
                userImages.setId(checkIfExist.getId());

            }

            userImageRepository.save(userImages);



        }

        return ResponseEntity.created(location).body(new ApiResponse(true, responseMessage));
    }

    @GetMapping("/get/profileImage")
    @PreAuthorize("hasRole('USER')")
    public UserImages getProfileImage(@CurrentUser UserPrincipal currentUser){
        User user = userRepository.findByEmail(currentUser.getEmail());


        return userImageRepository.findByUser(user);
    }


}
