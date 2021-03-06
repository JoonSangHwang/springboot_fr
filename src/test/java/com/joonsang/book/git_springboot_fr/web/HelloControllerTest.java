package com.joonsang.book.git_springboot_fr.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @RunWith
 * -> 테스트를 진행할 때 JUnit 에 내장된 SrpingRunner 라는 스프링 실행자를 사용
 * -> 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할
 *
 * @WebMvcTest
 * -> Web(Srping MVC)에 집중할 수 있는 어노테이션으로 @Controller 와 @ControlerAdvice 등 사용 가능
 * -> @Service / @Component / @Repository 사용 안됨
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloControllerTest.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;       // HTTP GET, POST 등에 대한 API 테스트 가능

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }



}
