package controllers;

import com.example.TennisGameApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TennisGameApplication.class)
@AutoConfigureMockMvc
class TennisControllerIntegrationTest {

    public static final String API_TENNIS_PLAY = "/api/tennis/play";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    void testSequence_A_B_A_B_A_A_returnsCorrectScores() throws Exception {
        String sequence = "ABABAA";
        mockMvc.perform(post(API_TENNIS_PLAY)
                .contentType(MediaType.TEXT_PLAIN)
                .content(sequence))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0]").value(containsString("15")))
            .andExpect(jsonPath("$[1]").value(containsString("15")))
            .andExpect(jsonPath("$[2]").value(containsString("30")))
            .andExpect(jsonPath("$[3]").value(containsString("30")))
            .andExpect(jsonPath("$[4]").value(containsString("40")))
            .andExpect(jsonPath("$[5]").value(containsString("wins")));
    }
    
    @Test
    void testDeuceAdvantageDeuceWin() throws Exception {
        String sequence = "ABABABABAA";
        mockMvc.perform(post("/api/tennis/play")
                .contentType(MediaType.TEXT_PLAIN)
                .content(sequence))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[5]").value("Deuce"))
            .andExpect(jsonPath("$[6]").value("Advantage Player A"))
            .andExpect(jsonPath("$[7]").value("Deuce"))
            .andExpect(jsonPath("$[8]").value("Advantage Player A"))
            .andExpect(jsonPath("$[9]").value("Player A wins the game"));
    }
}