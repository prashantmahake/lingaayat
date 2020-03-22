package india.lingayat.we.controllers;

import india.lingayat.we.models.CurrentUser;
import india.lingayat.we.models.User;
import india.lingayat.we.models.UserPrincipal;
import india.lingayat.we.services.BulkUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BulkUploadController {

    @Autowired
    BulkUploadService bulkUploadService;


    @PostMapping("/upload/bulk")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> bulkUploadUsers(@CurrentUser UserPrincipal currentUser, @RequestParam("file") MultipartFile file){

        List<User> usersUploaded = new ArrayList<>();

        try {
            usersUploaded = bulkUploadService.bulkUploadUsers(file);
        }
        catch
        (Exception e){
            e.printStackTrace();
        }
        return usersUploaded;

    }
}
