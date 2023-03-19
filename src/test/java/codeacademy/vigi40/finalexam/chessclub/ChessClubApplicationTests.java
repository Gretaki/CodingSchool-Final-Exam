package codeacademy.vigi40.finalexam.chessclub;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ChessClubApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getMembers_successful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/chess-club")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getMembers_successfulWithDifferentPagination() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/chess-club?page=1&size=10")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getMembersById_successful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/chess-club/1")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
