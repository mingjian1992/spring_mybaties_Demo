package com.andaily.zhishifenzi.service.impl;

import com.andaily.zhishifenzi.domain.commons.GeckoFile;
import com.andaily.zhishifenzi.domain.dto.file.CKEditorFileDto;
import com.andaily.zhishifenzi.service.FileService;
import org.springframework.stereotype.Service;

/**
 * @author Shengzhao Li
 */
@Service("fileService")
public class FileServiceImpl implements FileService {

    @Override
    public String uploadCKEditorFile(CKEditorFileDto fileDto) {
        final GeckoFile geckoFile = fileDto.toFile();
        geckoFile.saveOrUpdate();
        return geckoFile.guid();
    }
}