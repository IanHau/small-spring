package cn.ian.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDAO {

    private static Map<String,String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001","ian");
        hashMap.put("10002","hau");
        hashMap.put("10003","jay");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}
