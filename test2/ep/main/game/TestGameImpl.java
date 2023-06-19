package ep.main.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ep.main.cardClasses.Cards;
import ep.main.elem.Color;

public class TestGameImpl {
	  @Test
	  void shouldCreateGameWithPlayers() {
	    // Given
	    Player player1 = new Player("Peter", Color.RED, null);
	    Player player2 = new Player("Miles", Color.BLUE, null);

	    // When
	    Game game = new GameImpl(player1, player2);

	    // Then
	    assertEquals(player1, game.getRedPlayer());
	    assertEquals(player2, game.getBluePlayer());
	  }

	  @Test
	  void shouldCreateGameWithPlayersAndCard() {
	    // Given
	    Player player1 = new Player("Mary", Color.RED, null);
	    Player player2 = new Player("Gwen", Color.BLUE, null);

	    // When
	    GameImpl game = new GameImpl(player1, player2, Cards.getCards());

	    // Then
	    assertEquals(player1, game.getRedPlayer());
	    assertEquals(player2, game.getBluePlayer());
	    assertEquals(5, game.getGameCards().length);
	  }
	}