package com.example.mymsapay.membership;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Server localServer = new Server().description("로컬 서버").url("http://localhost:8080");
        Server dockerServer = new Server().description("도커 서버").url("http://localhost:8081");
        Info info = new Info()
                .title("MSA 찍먹용 미니 페이 프로젝트의 membership service API")
                .version("1.0.0")
                .description("카카오 페이와 같은 페이 서비스를 MSA 구조를 경험하기 위해 미니 프로젝트로 진행")
                .contact(new Contact() // 연락처
                        .name("zoomin3022")
                        .email("jjwm0128@naver.com")
                        .url("https://github.com/zoomin3022"));

        return new OpenAPI()
                .addServersItem(localServer)
                .addServersItem(dockerServer)
                .info(info);
    }
}
