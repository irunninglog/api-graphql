package com.irunninglog.graphql.query;

import com.irunninglog.graphql.object.Athlete;
import com.irunninglog.graphql.object.ShoeSummary;
import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class AthleteQueryTest {

    @Autowired
    private AthleteQuery query;
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void query() {
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        Optional<HttpServletRequest> optional = Optional.of(httpServletRequest);

        GraphQLContext context = Mockito.mock(GraphQLContext.class);
        Mockito.when(context.getHttpServletRequest()).thenReturn(optional);

        DataFetchingEnvironment environment = Mockito.mock(DataFetchingEnvironment.class);
        Mockito.when(environment.getContext()).thenReturn(context);

        ShoeSummary summary = new ShoeSummary("g1", "Name", Boolean.TRUE);
        Athlete athlete = new Athlete("1", "Allan", "Lewis", "Hoboken", "New Jersey", "USA", Athlete.Sex.Male, "small", "large", Collections.singletonList(summary));

        ResponseEntity<Athlete> responseEntity = new ResponseEntity<>(athlete, HttpStatus.OK);
        //noinspection unchecked
        Mockito.when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenReturn(responseEntity);

        Athlete response = query.athlete(environment);
        assertEquals("1", response.getId());
        assertEquals("Allan", response.getFirstName());
        assertEquals("Lewis", response.getLastName());
        assertEquals("Hoboken", response.getCity());
        assertEquals("New Jersey", response.getState());
        assertEquals("USA", response.getCountry());
        assertEquals(Athlete.Sex.Male, response.getSex());
        assertEquals("small", response.getProfileSmall());
        assertEquals("large", response.getProfileLarge());
        assertEquals(1, response.getShoeSummaries().size());

        ShoeSummary summary1 = response.getShoeSummaries().get(0);
        assertEquals("g1", summary1.getId());
        assertEquals("Name", summary1.getName());
        assertEquals(Boolean.TRUE, summary1.isPrimary());
    }

}
