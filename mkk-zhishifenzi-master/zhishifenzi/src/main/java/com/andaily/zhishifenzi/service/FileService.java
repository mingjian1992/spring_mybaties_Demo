package com.andaily.zhishifenzi.service;

import com.andaily.zhishifenzi.domain.dto.file.CKEditorFileDto;

/**
 * @author Shengzhao Li
 */

public interface FileService {

    String uploadCKEditorFile(CKEditorFileDto fileDto);
}