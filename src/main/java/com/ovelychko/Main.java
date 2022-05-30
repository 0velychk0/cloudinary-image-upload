package com.ovelychko;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static String cloudinary_Name = "xxx";
    private static String cloudinary_API_KEY = "xxxx";

    private static String cloudinary_API_SECRET = "xxxx";
    private static final Map cloudinary_config = ObjectUtils.asMap(
            "cloud_name", cloudinary_Name,
            "api_key", cloudinary_API_KEY,
            "api_secret", cloudinary_API_SECRET);

    private static String IMAGE_PATH = "/Users/xxxx/Downloads/card_";

    public static void main(String[] args) throws IOException {
        System.out.println("Upload started!");

        Cloudinary cloudinary = new Cloudinary(cloudinary_config);

        Map<String, String> mapOfFiles = new HashMap<>();
        for (int counter = 0; counter < 78; counter++) {
            mapOfFiles.put(IMAGE_PATH + counter + ".jpg", "card_" + counter);
        }

        mapOfFiles.entrySet().stream().parallel().forEach(obj -> {
            File fileP = new File(obj.getKey());
            try {
                Map<String, String> params = new HashMap<>();
                params.put("public_id", obj.getValue());
                Map uploadResultP = cloudinary.uploader().upload(fileP, params);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Upload done");
    }
}
