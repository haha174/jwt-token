package com.wen.token.service;

import com.wen.token.auth.JavaWebToken;
import com.wen.token.repository.RedisHandle;
import com.wen.token.util.DataResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RedisHandle redis;
    public boolean checkToken(String token){
        String toeknRe=(String)redis.get(token);
        if(StringUtils.isNotBlank(toeknRe)&&token.equals(toeknRe)){
            return true;
        }else{
            return false;
        }
    }
    public DataResponse<String> login(String username,String password){
        DataResponse<String> response =new  DataResponse<String>();

        String pass=(String)redis.get(username);
        if(StringUtils.isNotBlank(pass)&&pass.equals(password)){
            logger.info("login success"+username);
            Map map=new HashMap<>();
            map.put("id",1);
            String token=JavaWebToken.createJavaWebToken(map);
            redis.set(token,token);
            response.setValue(token);
        }else{
            logger.info("login error");
        }
        return response;
    }

}
