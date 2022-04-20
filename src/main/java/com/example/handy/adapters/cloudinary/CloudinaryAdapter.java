package com.example.handy.adapters.cloudinary;

import java.io.File;
import java.util.Map;

public interface CloudinaryAdapter {

    Map uploadImageToCloudinary(File file) throws Exception;

}
