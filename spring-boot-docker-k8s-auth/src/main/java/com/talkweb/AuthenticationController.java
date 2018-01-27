package com.talkweb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author songyougang
 */
@RestController
public class AuthenticationController {

    @RequestMapping(name = "/auth",
            method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String authenticate(HttpServletRequest request) throws IOException {

        String jsonString = getBodyString(request.getReader());
        JSONObject jsonObject = JSON.parseObject(jsonString);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        if ("admin".equals(username) && "docker@k8s".equals(password)) {
            return "{\"result\":\"success\"}";
        } else {
            return "{\"result\":\"failure\"}";
        }
    }

    private String getBodyString(BufferedReader br) {
        String line;
        StringBuilder str = new StringBuilder();
        try {
            while ((line = br.readLine()) != null) {
                str.append(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }

}
