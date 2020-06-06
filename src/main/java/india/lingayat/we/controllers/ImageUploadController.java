package india.lingayat.we.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import india.lingayat.we.models.CurrentUser;
import india.lingayat.we.models.User;
import india.lingayat.we.models.UserImages;
import india.lingayat.we.models.UserPrincipal;
import india.lingayat.we.payload.ApiResponse;
import india.lingayat.we.repositories.UserImageRepository;
import india.lingayat.we.repositories.UserRepository;
import india.lingayat.we.services.ClouditonaryImageService;
import india.lingayat.we.services.S3ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ImageUploadController {

    @Autowired
    private ClouditonaryImageService clouditonaryImageService;

    @Autowired
    private S3ImageUploadService s3ImageUploadService;

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

        //            uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        uploadResult = s3ImageUploadService.uploadImage(file, user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/upload/image")
                .buildAndExpand("image").toUri();

        System.out.println(uploadResult);
        if(uploadResult.get("url")!=null)
        {

            responseMessage += uploadResult.get("url").toString();
            UserImages userImages = new UserImages(uploadResult.get("url").toString(), "Profile", user);

            userImages.setUser(user);
            user.setUserImages(userImages);
            userRepository.save(user);



        }

        return ResponseEntity.created(location).body(new ApiResponse(true, responseMessage));
    }

    @GetMapping("/get/profileImage")
    @PreAuthorize("hasRole('USER')")
    public UserImages getProfileImage(@CurrentUser UserPrincipal currentUser){
        User user = userRepository.findByEmail(currentUser.getEmail());


        return user.getUserImages();
    }


}
