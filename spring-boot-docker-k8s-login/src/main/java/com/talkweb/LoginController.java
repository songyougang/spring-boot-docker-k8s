package com.talkweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author songyougang
 */
@RestController
public class LoginController {

    private final RestTemplate restTemplate;

    @Autowired
    public LoginController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 认证服务名
        String authService = "auth-service";
        // k8s可以通过环境变量或DNS来发现服务
        String host = authService; // host默认为服务名
        if ("env".equals(System.getenv("GET_HOSTS_FROM"))) {
            host = System.getenv((authService.replaceAll("-", "_")
                    + "_SERVICE_HOST").toUpperCase());
        }
        // for test
        // host = "localhost";
        int port = 25001;

        String url = "http://" + host + ":" + port + "/auth";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String body = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String responseEntityBody = responseEntity.getBody();

        if (responseEntityBody.contains("failure")) {
            return "login failed";
        }
        return "login successfully";

    }
}
