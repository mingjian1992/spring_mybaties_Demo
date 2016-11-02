package com.andaily.zhishifenzi.domain.dto.file;

import com.andaily.zhishifenzi.domain.commons.GeckoFile;
import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import com.andaily.zhishifenzi.infrastructure.file.ImageUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author Shengzhao Li
 */
public class GeckoFileDto extends AbstractDto {

    protected String name;
    protected byte[] data;
    protected long size;

    protected boolean photo;

    public GeckoFileDto() {
    }

    public GeckoFileDto(GeckoFile file) {
        this(file, false);
    }

    public GeckoFileDto(GeckoFile file, boolean includeData) {
        super(file.guid());
        this.name = file.name();
        this.size = file.size();

        this.photo = file.isPhoto();
        if (includeData) {
            this.data = file.data();
        }
    }

    public String downloadFileName() throws UnsupportedEncodingException {
        String fileName = StringUtils.trimAllWhitespace(this.name);
        return new String(fileName.getBytes(), "ISO8859-1");
    }

    public String getContextTypeExtension() {
        final String extension = FilenameUtils.getExtension(this.name).toLowerCase();
        if ("png".equals(extension) || "gif".equals(extension)) {
            return extension;
        }
        return ImageUtils.DEFAULT_IMAGE_TYPE;
    }

    public boolean isPhoto() {
        return photo;
    }

    public void setPhoto(boolean photo) {
        this.photo = photo;
    }


    public long getSize() {
        return size;
    }

    public long getSizeAsKB() {
        return (size / 1024);
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}