package foo.customError;

import com.foo.controller.EmployeeController;
import com.foo.service.persistence.Employee;
import com.foo.service.persistence.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
// TODO Fix
//@RunWith(SpringRunner.class)
//@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTests {
//
//	@Autowired private MockMvc mvc;
//
//	@MockBean private EmployeeRepository repository;
//
//	@Test
//	public void getShouldFetchAJsonDocument() throws Exception {
//
//		given(repository.findAll()).willReturn( //
//				Arrays.asList( //
//						new Employee(1L, "Frodo", "Baggins", "ring bearer"), //
//						new Employee(2L, "Bilbo", "Baggins", "burglar")));
//
//		mvc.perform(get("/employees").accept(MediaType.APPLICATION_JSON)) //
//				.andDo(print()) //
//				.andExpect(status().isOk()) //
//				.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString()))
//				.andExpect(jsonPath("$.[0].id", is(1)))
//				.andExpect(jsonPath("$.[0].firstName", is("Frodo")))
//				.andExpect(jsonPath("$.[0].lastName", is("Baggins")))
//				.andExpect(jsonPath("$.[0].role", is("ring bearer")))
//				.andExpect(jsonPath("$.[0]._links.self.href", is("http://localhost/employees/1")))
//				.andExpect(jsonPath("$.[0]._links.employees.href", is("http://localhost/employees")))
//				.andExpect(jsonPath("$.[1].id", is(2)))
//				.andExpect(jsonPath("$.[1].firstName", is("Bilbo")))
//				.andExpect(jsonPath("$.[1].lastName", is("Baggins")))
//				.andExpect(jsonPath("$.[1].role", is("burglar")))
//				.andReturn();
//	}
}
