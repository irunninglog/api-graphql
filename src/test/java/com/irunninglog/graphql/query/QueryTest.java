package com.irunninglog.graphql.query;

import com.irunninglog.graphql.object.Athlete;
import com.irunninglog.graphql.object.ShoeDetail;
import com.irunninglog.graphql.object.ShoeSummary;
import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;
import org.junit.Before;
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
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class QueryTest {

    @Autowired
    private Query query;
    @Autowired
    private RestTemplate restTemplate;

    private DataFetchingEnvironment environment;

    @Before
    public void before() {
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        Mockito.when(httpServletRequest.getHeader("Authorization")).thenReturn("foo");
        Optional<HttpServletRequest> optional = Optional.of(httpServletRequest);

        GraphQLContext context = Mockito.mock(GraphQLContext.class);
        Mockito.when(context.getHttpServletRequest()).thenReturn(optional);

        environment = Mockito.mock(DataFetchingEnvironment.class);
        Mockito.when(environment.getContext()).thenReturn(context);
    }

    @Test
    public void athlete() {
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

    @Test
    public void shoes() {
        ShoeDetail shoeDetail = new ShoeDetail("g1", "Name", Boolean.TRUE, 500, "Mizuno", "Wave Inspire 14", "Description");

        ResponseEntity<ShoeDetail> responseEntity = new ResponseEntity<>(shoeDetail, HttpStatus.OK);
        //noinspection unchecked
        Mockito.when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenReturn(responseEntity);

        List<ShoeDetail> details = query.shoes(Collections.singletonList("id"), environment);
        assertEquals(1, details.size());

        ShoeDetail detail = details.get(0);
        assertEquals("g1", detail.getId());
        assertEquals("Name", detail.getName());
        assertEquals(Boolean.TRUE, detail.isPrimary());
        assertEquals(500, detail.getDistance(), 1E-9);
        assertEquals("Mizuno", detail.getBrandName());
        assertEquals("Wave Inspire 14", detail.getModelName());
        assertEquals("Description", detail.getDescription());
    }

}
