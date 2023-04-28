package cpt202.project.pizzaorderingsys.services;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.Json;

import java.io.IOException;

@Service
public class ImageService {

    public static String imageUpload(MultipartFile path) {
        System.out.println("enter upload image method");
        System.out.println(path);
        System.out.println(path.getOriginalFilename());
        String url;
        // 构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        // ...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // ...生成上传凭证，然后准备上传
        String accessKey = "w298VgyEaU-oVWHv8tl6q5Vj3ivX6uCkPz_Di8yj";
        String secretKey = "i3f99HOYG-8IZBRY6XN78rkKb667av9-hnM0iV4E";
        String bucket = "cpt202-pizzaordersys";
        // 如果是Windows情况下，格式是 D:\\qiniu\\test.png
        //String localFilePath = path.getOriginalFilename();
        // String localFilePath = path;
        // 默认不指定key的情况下，以文件内容的hash值作为文件名
//        int a = localFilePath.lastIndexOf("\\", 0);
//        System.out.println(localFilePath.substring(a));
        String key = "pizzaimage/"+path.getOriginalFilename();
        System.out.println(key);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            com.qiniu.http.Response response = uploadManager.put(path.getBytes(), key, upToken);
            // 解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            com.qiniu.http.Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                // ignore
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "http://rt9kvjh2p.hd-bkt.clouddn.com/" + key;
    }
}
