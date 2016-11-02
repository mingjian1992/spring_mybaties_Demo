package com.andaily.zhishifenzi.infrastructure.qiniu;

import java.util.Date;

/**
 * @author Shengzhao Li
 */
public class QiniuResourceDetails {

    private boolean ok;
    private String key;
    private String bucket;

    private long size;
    private String hash;
    private String mimeType;
    private Date putTime;

    public QiniuResourceDetails() {
    }

    public QiniuResourceDetails(String key, String bucket) {
        this.key = key;
        this.bucket = bucket;
    }

    public String getKey() {
        return key;
    }

    public QiniuResourceDetails key(String key) {
        this.key = key;
        return this;
    }

    public String getBucket() {
        return bucket;
    }

    public QiniuResourceDetails bucket(String bucket) {
        this.bucket = bucket;
        return this;
    }

    public long getSize() {
        return size;
    }

    public QiniuResourceDetails size(long size) {
        this.size = size;
        return this;
    }

    public String getHash() {
        return hash;
    }

    public QiniuResourceDetails hash(String hash) {
        this.hash = hash;
        return this;
    }

    public String getMimeType() {
        return mimeType;
    }

    public QiniuResourceDetails mimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public Date getPutTime() {
        return putTime;
    }

    public QiniuResourceDetails putTime(Date putTime) {
        this.putTime = putTime;
        return this;
    }

    public boolean isOk() {
        return ok;
    }

    public QiniuResourceDetails ok(boolean ok) {
        this.ok = ok;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{ok=").append(ok);
        sb.append(", key='").append(key).append('\'');
        sb.append(", bucket='").append(bucket).append('\'');
        sb.append(", size=").append(size);
        sb.append(", hash='").append(hash).append('\'');
        sb.append(", mimeType='").append(mimeType).append('\'');
        sb.append(", putTime=").append(putTime);
        sb.append('}');
        return sb.toString();
    }
}