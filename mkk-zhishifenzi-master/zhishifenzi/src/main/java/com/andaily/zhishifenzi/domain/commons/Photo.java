package com.andaily.zhishifenzi.domain.commons;

import com.andaily.zhishifenzi.domain.album.Album;
import com.andaily.zhishifenzi.infrastructure.qiniu.QiniuUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * 14-3-8 下午8:16
 *
 * @author Shengzhao Li
 */
public class Photo extends GeckoFile {

    private static final Logger LOGGER = LoggerFactory.getLogger(Photo.class);


    //图片完整的URL,如: http://sss.sdd/sdo.jpg
    private String url;

    private String description;
    //是否为 首页显示的图片
    private boolean headPhoto;

    private Album album;

    public Photo() {
    }

    public Photo(String name, byte[] data) {
        super(name, data);
    }

    public void saveOrUpdate() {
        if (this.data != null) {
            this.path = fileWarehouse.write(this.data);
        }
        if (isNewly()) {
            commonsRepository.savePhoto(this);
        } else {
            commonsRepository.updatePhoto(this);
        }
    }

    public String description() {
        return description;
    }

    public Photo description(String description) {
        this.description = description;
        return this;
    }

    public boolean headPhoto() {
        return headPhoto;
    }

    public Photo headPhoto(boolean headPhoto) {
        this.headPhoto = headPhoto;
        return this;
    }

    public String url() {
        return url;
    }

    public Photo url(String url) {
        this.url = url;
        return this;
    }

    public boolean isPhoto() {
        return true;
    }

    public Album album() {
        return album;
    }

    public Photo album(Album album) {
        this.album = album;
        return this;
    }

    public String remove() {
        //check url firstly, if have url then call delete cloud file
        if (StringUtils.isNotEmpty(this.url)) {
            String key = this.url.substring(this.url.lastIndexOf("/") + 1, this.url.length());
            QiniuUtils.deleteFile(key);
            LOGGER.debug("Delete Cloud Photo(id={}) by key[{}]", id, key);
        }
        this.delete();
        LOGGER.debug("Remove Photo(id={})", id);
        return album.guid();
    }

    /**
     * 同步照片到云存储
     *
     * @param deleteLocalPhoto true 将删除本地的照片
     * @return true is success,otherwise false
     */
    public boolean transferToCloud(boolean deleteLocalPhoto) {
        final File file = fileWarehouse.file(this.path);
        final String key = QiniuUtils.uploadFile(file, this.suffix);
        this.url = QiniuUtils.visitUrl(key);

        LOGGER.debug("Transfer Photo(id={}, path={}) to Cloud Server, the url is ({})", this.id(), this.path, this.url);

        if (deleteLocalPhoto) {
            fileWarehouse.delete(this.path);
            LOGGER.debug("Delete local Photo(id={}, file-path={}), please visit the replaced url ({}) when you want", this.id(), file.getAbsolutePath(), this.url);
        }
        this.saveOrUpdate();
        return true;
    }
}
