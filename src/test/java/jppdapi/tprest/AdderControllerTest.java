package jppdapi.tprest;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import jppdapi.tprest.controller.AdderController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import jppdapi.tprest.service.AdderService;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(AdderController.class)
class AdderControllerTest {
    @Autowired
    private WebApplicationContext context;

    @MockBean
    private AdderService adderService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.<DefaultMockMvcBuilder> webAppContextSetup(context).build();
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void shouldReturnCurrentNumber() throws Exception {
        when(adderService.currentBase())
                .thenReturn(7);

        RestAssuredMockMvc.given().auth().none()
                .when().get("/adder/current")
                .then().statusCode(200)
                .body(is(equalTo("7")));
    }

    @Test
    void shouldReturnAddedNumber() throws Exception {
        when(adderService.add(3))
                .thenReturn(3);

        RestAssuredMockMvc.given().auth().none()
                .contentType("application/json")
                .body(3)
                .when().
                post("/adder")
                .then().statusCode(200)
                .body(is(equalTo("3")));
    }

    @Test
    void shouldReturnAccumulatedNumber() throws Exception {
        when(adderService.accumulate(3))
                .thenReturn(3);

        RestAssuredMockMvc.given().auth().none()
                .contentType("application/json")
                .body(3)
                .when().
                post("/adder/acc")
                .then().statusCode(200)
                .body(is(equalTo("3")));

        when(adderService.accumulate(3))
                .thenReturn(6);

        RestAssuredMockMvc.given().auth().none()
                .contentType("application/json")
                .body(3)
                .when().
                post("/adder/acc")
                .then().statusCode(200)
                .body(is(equalTo("6")));
    }
}