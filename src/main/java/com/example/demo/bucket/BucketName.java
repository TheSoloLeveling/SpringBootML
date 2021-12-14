package com.example.demo.bucket;

public enum BucketName {

    CLUB_IMAGE("uir-clubs-ressources");
    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
