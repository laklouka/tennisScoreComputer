package services;

import com.example.tennisGame.services.GameState;
import com.example.tennisGame.services.TennisScoreService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TennisScoreServiceTest {
    
    TennisScoreService service = new TennisScoreService();
    
    @Test
    void simpleStraightWinForA() {
        TennisScoreService.TennisScore s = service.process("AAAA");
        assertThat(s.state).isEqualTo(GameState.WIN_A);
    }
    
    @Test
    void simpleStraightWinForB() {
        TennisScoreService.TennisScore s = service.process("BBBB");
        assertThat(s.state).isEqualTo(GameState.WIN_B);
    }
    
    @Test
    void deuceScenario() {
        TennisScoreService.TennisScore s = service.process("ABABAB");
        assertThat(s.state).isEqualTo(GameState.DEUCE);
    }
    
    @Test
    void advantageForA() {
        TennisScoreService.TennisScore s = service.process("ABABABA");
        assertThat(s.state).isEqualTo(GameState.ADV_A);
    }
    
    @Test
    void deuceBackFromAdvantage() {
        TennisScoreService.TennisScore s = service.process("ABABABAB");
        assertThat(s.state).isEqualTo(GameState.DEUCE);
    }
    
    @Test
    void winAfterAdvantage() {
        TennisScoreService.TennisScore s = service.process("ABABABAA");
        assertThat(s.state).isEqualTo(GameState.WIN_A);
    }
    
    @Test
    void scoreDescriptions() {
        var score = new TennisScoreService.TennisScore();
        score.pointsA = 1;
        score.pointsB = 2;
        score.state = GameState.ONGOING;
        assertThat(service.getScoreString(score)).isEqualTo("Player A : 15 / Player B : 30");
        
        score.state = GameState.WIN_A;
        assertThat(service.getScoreString(score)).contains("wins");
    }
}