package com.pizza.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpSession
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static com.jayway.jsonassert.JsonAssert.*

import static org.hamcrest.Matchers.*;

/**
 * Tests to check the functionality of the Toppings rest controller
 */
@WebAppConfiguration
@ContextConfiguration(locations = "/testContext.xml")
@Transactional
class ToppingsRestControllerTest extends Specification {

    @Autowired
    WebApplicationContext wac
    @Autowired
    MockHttpSession session

    MockMvc mockMvc

    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    def "GetAvailableToppings should return a list of toppings in json format"() {
        expect:
        mockMvc.perform(get("/topping")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        // At least it has to include Tomato and Mozzarella, without that there is no pizza
                .andExpect(jsonPath("\$", is(collectionWithSize(greaterThanOrEqualTo(2)))))
                .andExpect(jsonPath("[?(@.name == Tomato)]").exists())
                .andExpect(jsonPath("[?(@.name == Mozzarella)]").exists())
    }
}
