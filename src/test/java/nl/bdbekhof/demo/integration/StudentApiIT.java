package nl.bdbekhof.demo.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class StudentApiIT {
    @Autowired
    MockMvc mvc;

    @BeforeEach
    void seed() throws Exception {
        mvc.perform(post("/students")
                .contentType("application/json")
                .content("{\"firstName\":\"Barry\",\"lastName\":\"Bekhof\",\"email\":\"barry@example.com\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void getAll_paged_sorted_ok() throws Exception {
        mvc.perform(get("/students?page=0&size=5&sort=lastName,asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].email").value("barry@example.com"))
                .andExpect(jsonPath("$.size").value(5))
                .andExpect(jsonPath("$.number").value(0));
    }

    @Test
    void create_conflict_on_duplicate_email() throws Exception {
        mvc.perform(post("/students")
                .contentType("application/json")
                .content("{\"firstName\":\"Dup\",\"lastName\":\"Email\",\"email\":\"barry@example.com\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    void getById_notFound() throws Exception {
        mvc.perform(get("/students/99999999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void patch_updates_only_provided_fields() throws Exception {
        mvc.perform(patch("/students/1")
                        .contentType("application/json")
                        .content("\"firstName\":\"BarryPatched\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("BarryPatched"))
                .andExpect(jsonPath("$.lastName").value("Bekhof"));
    }

    @Test
    void search_by_firstName_paged_ok() throws Exception {
        mvc.perform(post("/students")
                .contentType("application/json")
                .content("{\"firstName\":\"Barry\",\"lastName\":\"Andere\",\"email\":\"barry2@example.com\""))
                .andExpect(status().isCreated());

        mvc.perform(get("/students/search?firstName=bar&page=0&size=1-&sort=lastName,asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].firstName").value("Barry"));
    }

    @Test
    void put_updates_full_entity() throws Exception {
        mvc.perform(put("/students/1")
                .contentType("application/json")
                .content("{\"id\":1,\"firstName\":\"BarryNew\",\"lastName\":\"Bekhof\",\"email\":\"barry@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("BarryNew"));
    }

    @Test
    void delete_noContent() throws Exception {
        mvc.perform(delete("/students/1"))
                .andExpect(status().isNoContent());

        mvc.perform(get("/students/1"))
                .andExpect(status().isNotFound());
    }
}
