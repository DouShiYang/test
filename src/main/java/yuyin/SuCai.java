package yuyin;

import cn.hutool.http.HttpUtil;

public class SuCai {

    static String url = "https://h.aaaapp.cn/posts";

    static String userId = "325A344799D6B609AD20F3CED9A25BB9";

    static String secretKey = "1f307b02a725de86dd2c6bdad9e5c61a";

    public void getSucai(){
        HttpUtil.createGet(url);

    }

    public void downLoad(){

    }

}
