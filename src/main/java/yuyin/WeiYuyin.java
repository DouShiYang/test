package yuyin;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class WeiYuyin {


    public static void main(String[] args) {

        heCheng("当你走了");
    }

    /**
     *
     * @param content
     */
   static void heCheng(String content){
       final JSONObject jsonObject = new JSONObject();
       jsonObject.put("tex",content);
       jsonObject.put("spd","11.2");
       jsonObject.put("pit","8");
       jsonObject.put("rate","24000");
       jsonObject.put("vol","14");
       jsonObject.put("per","zh-CN-XiaochenNeural");
       //读者
       jsonObject.put("readerf","7");
       jsonObject.put("subtitle","MacBookPro17,1〓MacBookPro17,1〓macOS 12.5.1");
       jsonObject.put("filename","");
       jsonObject.put("source","30");
       jsonObject.put("peiyinsource","30");
       jsonObject.put("fadetime","0");
       jsonObject.put("fronttime","0");
       jsonObject.put("latertime","0");
       jsonObject.put("audioname","1662274104000wpy9744.wav");
       jsonObject.put("openid","oykAL4yO6xzUezTMNq3p5zVoKCBA");
       jsonObject.put("secret","YWxzaWtlMjAzOTE2NjIyNjMwMjQwMDB3cHkyMjU1");

       //创建文档添加静态类型

       String url = "https://www.10cha.cn/dub";
       HttpRequest httpRequest = HttpUtil.createPost(url);
       HttpResponse httpResponse = httpRequest.contentType("application/json").body(jsonObject.toJSONString()).execute();;
       JSONObject result = JSON.parseObject(httpResponse.body());
       System.out.printf(result.toJSONString());
    }
}
