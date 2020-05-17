package com.example.demo.service;

import java.io.File;

public interface UploadPathService {
    File getFilePath(String modifiedFileName, String path);
}
