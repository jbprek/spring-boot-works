package com.foo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * How to test the hypermedia-based {@link PersonController} with everything else mocked out.
 *
 * @author Greg Turnquist
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTests {

	@Autowired private MockMvc mvc;

	@MockBean private AppConfProperties repository;

	@Test
	public void getShouldFetchAHelloMessage() throws Exception {

		given(repository.getHelloMessage()).willReturn("Test Hello Message");

		mvc.perform(get("/hello")) //
				.andDo(print()) //
				.andExpect(status().isOk()) //
				.andExpect(content().string("Test Hello Message"))
				.andReturn();
	}
}
