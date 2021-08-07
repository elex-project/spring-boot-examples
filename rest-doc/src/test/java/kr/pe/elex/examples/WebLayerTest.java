package kr.pe.elex.examples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MyController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class WebLayerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void defaultPage() throws Exception {
		this.mockMvc.perform(
				get("/name/{name}", "Charlie"))
				.andDo(print())
				.andExpect(status().isOk())
				.andDo(document("home",
						responseFields(fieldWithPath("name").description("Name..."),
								fieldWithPath("age").description("Age..."))
						));
	}
}
