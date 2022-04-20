package com.example.handy.adapters.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService implements CloudinaryAdapter {

    private final Cloudinary cloudinary;

    public CloudinaryService() {
        this.cloudinary = new Cloudinary(com.cloudinary.utils.ObjectUtils.asMap(
                "cloud_name", "drtqtujnv",
                "api_key", "839946942299748",
                "api_secret", "H-qrSMRjjfs8THvZihgbFQjsjt8",
                "secure", true));
    }

    @Override
    public Map uploadImageToCloudinary(File file) throws Exception {
        try {
            return cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }

    }
}
