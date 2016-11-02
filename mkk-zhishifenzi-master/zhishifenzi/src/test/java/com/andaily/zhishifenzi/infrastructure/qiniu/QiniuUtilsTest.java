package com.andaily.zhishifenzi.infrastructure.qiniu;

import com.andaily.zhishifenzi.domain.shared.GuidGenerator;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.GetPolicy;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.URLUtils;
import org.testng.annotations.Test;

/**
 * 14-3-22 下午3:10
 *
 * @author Shengzhao Li
 */
public class QiniuUtilsTest {


    @Test(enabled = false)
    public void downloadToken() throws Exception {
        String key = "testIMG_023_副本.jpg";

        Mac mac = mac();
        String baseUrl = URLUtils.makeBaseUrl("zsfz.qiniudn.com", key);
        GetPolicy getPolicy = new GetPolicy();
        String downloadUrl = getPolicy.makeRequest(baseUrl, mac);
        System.out.println(downloadUrl);
    }

    private Mac mac() {
        Config.ACCESS_KEY = "iLMZC9OE88iWGXc1lOmvvXBReolN8bSdW58v4n5M";
        Config.SECRET_KEY = "DwnXYoJS3JDer4BRHAT_fSm5eNw4vb0fA7UayJzy";
        return new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
    }


    @Test(enabled = false)
    public void uploadFile() throws Exception {
        String bucket = "zsfz";
        String file = "F:\\wdcy-test\\zip_test\\index.jpg";

        final Mac mac = mac();
        PutPolicy putPolicy = new PutPolicy(bucket);
        String uptoken = putPolicy.token(mac);
        PutExtra extra = new PutExtra();
        String key = GuidGenerator.generate() + "_index.jpg";

        final PutRet putRet = IoApi.putFile(uptoken, key, file, extra);
        System.out.println(putRet.getKey());
    }
}
