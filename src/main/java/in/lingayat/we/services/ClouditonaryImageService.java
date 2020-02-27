package in.lingayat.we.services;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cloudinary.*;

@Service
public class ClouditonaryImageService {

    @Value("#{systemEnvironment['cloud_name']}")
    private String cloudName;

    @Value("#{systemEnvironment['cloud_api_key']}")
    private String cloudApiKey;

    @Value("#{systemEnvironment['cloud_api_secret']}")
    private String cloudApiSecret;

    private Cloudinary cloudinary = null;

    public Cloudinary getCloudinary(){
        if(this.cloudinary!=null)
        {
            return cloudinary;
        }else
        {
            cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", this.cloudName,
                    "api_key", this.cloudApiKey,
                    "api_secret", this.cloudApiSecret));

            return cloudinary;
        }
    }

}
