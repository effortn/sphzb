package com.en.sphzb.dto;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class CommonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);
    /**
     * 获取不带“-”的uuid
     * getUUID:(这里用一句话描述这个方法的作用)
     * @Title: getUUID
     * @return
     * String
     * 时间:2017年6月23日下午2:19:41
     */
    public static String getUUID(){
        int random = (int)(Math.random()*9+1);
        String valueOf =String.valueOf(random);
        int code = UUID.randomUUID().toString().hashCode();
        if (code<0){
            code=-code;
        }
        String codeID = String.valueOf(code);
        return codeID;
    }


}
