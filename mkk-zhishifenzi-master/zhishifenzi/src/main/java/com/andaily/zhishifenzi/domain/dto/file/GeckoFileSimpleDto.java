package com.andaily.zhishifenzi.domain.dto.file;


import com.andaily.zhishifenzi.domain.commons.GeckoFile;

/**
 * Exclude <i>data</i>  field value
 *
 * @author Shengzhao Li
 */
public class GeckoFileSimpleDto extends GeckoFileDto {

    private boolean temp;
    private String suffix;

    public GeckoFileSimpleDto() {
        super();
    }

    public GeckoFileSimpleDto(GeckoFile file) {
        this.guid = file.guid();
        this.name = file.name();
        this.size = file.size();

        this.temp = file.temp();
        this.suffix = file.suffix();

        this.photo = file.isPhoto();
    }

    public boolean isTemp() {
        return temp;
    }

    public void setTemp(boolean temp) {
        this.temp = temp;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}