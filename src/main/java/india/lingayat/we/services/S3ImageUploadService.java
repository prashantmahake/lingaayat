package india.lingayat.we.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import india.lingayat.we.controllers.UserController;
import india.lingayat.we.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class S3ImageUploadService {

    private static final Logger logger = LoggerFactory.getLogger(S3ImageUploadService.class);

    @Value("${bucketName}")
    private String bucketName;


    public Map<String, String> uploadImage(MultipartFile multipartFile, User user)
    {
        Regions clientRegion = Regions.AP_SOUTH_1;
        Map<String, String> uploadResult = new HashMap<>();
        uploadResult.put("url","");
        String bucketKey = user.getId()+"/"+user.getLastName()+user.getMiddleName()+".jpeg";
        File file = null;

        try{
            file = File.createTempFile(bucketKey, "jpeg");
            file.deleteOnExit();
            multipartFile.transferTo(file);
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(clientRegion)
                    .build();

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, bucketKey, file).withCannedAcl(CannedAccessControlList.PublicRead);
            s3Client.putObject(putObjectRequest);
            uploadResult.put("url",s3Client.getUrl(bucketName, bucketKey).toString());

        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }finally {
            file.delete();
        }

        return uploadResult;
    }
}
