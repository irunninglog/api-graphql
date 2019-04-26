package com.irunninglog.graphql;

import com.irunninglog.graphql.security.SecurityConfig;
import com.oembedler.moon.graphql.boot.GraphQLWebAutoConfiguration;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLHttpServlet;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Ignore
@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
@WebMvcTest(SimpleGraphQLHttpServlet.class)
public class RootQueryTest {

//    @Autowired
//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//    private GraphQLTestTemplate testTemplate;

    @Autowired
    private MockMvc mvc;

//    @LocalServerPort
//    private int localPort;

//    @Test
//    public void athlete() throws IOException {
//        testTemplate.postForResource("foo");
//    }

//    @Autowired
//    private MockMvc mvc;

//    @Bean
//    public ServletRegistrationBean exampleServletBean() {
//        ServletRegistrationBean bean = new ServletRegistrationBean(
//                SimpleGraphQLHttpServlet.newBuilder() .newBuilder(new GraphQLSchema()).build(), "/graphql");
//        bean.setLoadOnStartup(1);
//        return bean;
//    }

    @Test
    public void athlete() throws Exception {
        mvc.perform(post("/graphql")
                .header("Authorization", "Bearer foo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists());
    }
//
//    @Test
//    public void shoes() throws Exception {
//        mvc.perform(post("/graphql")
//                .header("Authorization", "Bearer foo")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists());
//    }

}
