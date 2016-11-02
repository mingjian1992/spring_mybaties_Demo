package com.andaily.zhishifenzi.infrastructure.qiniu;

import com.andaily.zhishifenzi.infrastructure.DateUtils;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.net.CallRet;
import com.qiniu.api.rs.Entry;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author Shengzhao Li
 */
public class QiniuUtils implements InitializingBean {


    //Qiniu upload url, fixed value
    public static final String UPLOAD_URL = "http://up.qiniu.com/";

    //More details see: http://developer.qiniu.com/docs/v6/api/overview/up/response/vars.html#xvar
    private static final String RETURN_BODY = "bucket=$(bucket)&key=$(key)&name=$(fname)&hash=$(etag)&guid=$(x:guid)&size=$(fsize)";


    private static String accessKey;
    private static String secretKey;

    private static String bucket;
    private static String host;

    private static String applicationHost;

    //Generate from bucket and host
    private static String bucketHost;

    //Get the resource visit url of Qiniu
    public static String visitUrl(String key) {
        return bucketHost + key;
    }

    /*
     *  Upload a local file to Qiniu server bucket.
     */
    public static String uploadFile(File file, String suffix) {
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        PutPolicy putPolicy = new PutPolicy(bucket);
        try {
            String uptoken = putPolicy.token(mac);
            PutExtra extra = new PutExtra();
            String key = DateUtils.toDateText(DateUtils.now()) + "_" + file.getName() + "." + suffix;
            final PutRet putRet = IoApi.putFile(uptoken, key, file.getAbsolutePath(), extra);

            return putRet.getKey();
        } catch (Exception e) {
            throw new IllegalStateException("Upload file failed", e);
        }
    }

    /*
     *  Generate HTML upload token
     */
    public static String generateUploadToken(String returnUrl) {
        return generateUploadToken(returnUrl, RETURN_BODY);
    }

    /*
    *  Generate HTML upload token ,
    *  allow custom define returnBody
    */
    public static String generateUploadToken(String returnUrl, String returnBody) {
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);

        PutPolicy putPolicy = new PutPolicy(bucket);
        putPolicy.returnUrl = returnUrl;
        putPolicy.returnBody = returnBody;

        try {
            return putPolicy.token(mac);
        } catch (Exception e) {
            throw new IllegalStateException("Generate upload token exception", e);
        }
    }

    /**
     * A demo of localUrl:  public/cloud_file/response
     *
     * @param localUrl localUrl
     * @return Response full url
     */
    public static String responseUrl(String localUrl) {
        return applicationHost + localUrl;
    }

    /**
     * Delete file by bucketName and key.
     * If the bucketName is null then use the default bucket
     *
     * @param key key
     * @return True is delete successful, otherwise false
     */
    public static boolean deleteFile(String key) {
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        RSClient client = new RSClient(mac);
        final CallRet callRet;
        try {
            callRet = client.delete(bucket, new String(key.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
        return callRet.ok();
    }

    /**
     * Retrieve a resource details
     *
     * @param key key
     * @return True is delete successful, otherwise false
     */
    public static QiniuResourceDetails details(String key) {
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        RSClient client = new RSClient(mac);
        try {
            final Entry entry = client.stat(bucket, new String(key.getBytes("UTF-8")));
            final boolean ok = entry.ok();
            QiniuResourceDetails details = new QiniuResourceDetails(key, bucket).ok(ok);
            if (ok) {
                details.size(entry.getFsize())
                        .hash(entry.getHash())
                        .mimeType(entry.getMimeType())
                        .putTime(new Date(entry.getPutTime()));
            }
            return details;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Retrieve a resource as byte array.
     *
     * @param key key
     * @return Data as bytes, if it is failed, return null
     */
    public static byte[] dataAsBytes(String key) {
        final QiniuResourceDetails details = details(key);
        if (details.isOk()) {
            final String url = visitUrl(key);
            HttpClient client = new DefaultHttpClient();
            HttpUriRequest request = new HttpGet(url);
            try {
                final HttpResponse response = client.execute(request);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                response.getEntity().writeTo(baos);
                return baos.toByteArray();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        return null;
    }


    public void setApplicationHost(String applicationHost) {
        Assert.notNull(applicationHost);
        if (!applicationHost.endsWith("/")) {
            applicationHost = applicationHost + "/";
        }
        QiniuUtils.applicationHost = applicationHost;
    }


    public void setAccessKey(String accessKey) {
        QiniuUtils.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        QiniuUtils.secretKey = secretKey;
    }

    public void setBucket(String bucket) {
        QiniuUtils.bucket = bucket;
    }

    public void setHost(String host) {
        QiniuUtils.host = host;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(accessKey);
        Assert.notNull(secretKey);
        Assert.notNull(bucket);

        Assert.notNull(host);
        Assert.notNull(applicationHost);

        //initial
        Config.ACCESS_KEY = accessKey;
        Config.SECRET_KEY = secretKey;
        bucketHost = "http://" + bucket + "." + host + "/";
    }
}