package cpt202.project.pizzaorderingsys.services;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import java.util.*;
import com.aliyuncs.dysmsapi.model.v20170525.*;
import org.springframework.stereotype.Service;

@Service
public class SendSms {
    public static boolean sendVerificationCode(String phone) {

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI5t9CTozLXesv2otPKUF1", "HLKVBcm7UjzWwvXDGzjEmSaRHhytO4");
        /** use STS Token
         DefaultProfile profile = DefaultProfile.getProfile(
         "<your-region-id>",           // The region ID
         "<your-access-key-id>",       // The AccessKey ID of the RAM account
         "<your-access-key-secret>",   // The AccessKey Secret of the RAM account
         "<your-sts-token>");          // STS Token
         **/

        IAcsClient client = new DefaultAcsClient(profile);


        SendSmsRequest request = new SendSmsRequest();
        request.setSignName("G24Tech");
        request.setTemplateCode("SMS_276255265");
        request.setPhoneNumbers(phone);
        request.setTemplateParam("{\"code\":\"6587\"}");
        String result = "";
        String resultStr = "";
        try {
            SendSmsResponse response = client.getAcsResponse(request);
            result = response.getCode();
            resultStr = response.getMessage();
            System.out.println(resultStr);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
        if (result.equals("OK")) {
            return true;
        } else if(result.equals("isv.SMS_TEST_NUMBER_LIMIT") || result.equals("isv.MOBILE_NUMBER_ILLEGAL")){
            return true;
        }
        else return false;

    }


}
