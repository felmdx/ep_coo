package ep.main.game;

import static org.junit.Assert.assertThrows;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ep.main.cardClasses.Card;
import ep.main.cardClasses.Cards;
import ep.main.elem.Color;
import ep.main.exceptions.*;

public class TestPlayer {
	  private static final Card[] cards = Cards.getCards();

	  @Test
	  void shouldCreatePlayerWithTwoCards() {
	    // Given
	    String playerName = "Peter";

	    // When
	    Player player = new Player(playerName, Color.BLUE, cards[0], cards[1]);

	    // Then
	    assertEquals(playerName, player.getName());
	    assertEquals(Color.BLUE, player.getPieceColor());
	    assertEquals(cards[0], player.getCards()[0]);
	  }

	  @Test
	  void shouldCreatePlayerWithCardList() {
	    // Given
	    String playerName = "Mary";

	    // When
	    Player player = new Player(playerName, Color.BLUE, cards);

	    // Then
	    assertEquals(playerName, player.getName());
	    assertEquals(Color.BLUE, player.getPieceColor());
	    assertEquals(cards, player.getCards());
	  }

	  @Test
	  void shouldSwapCardsSuccessfully() {
	    // Given
	    Card oldCard = cards[0];
	    Card newCard = cards[2];
	    Player player = new Player("Gwen", Color.BLUE, oldCard, cards[1]);

	    // When
	    player.swapCard(oldCard, newCard);

	    // Then
	    assertEquals(newCard, player.getCards()[0]);
	  }

	  @Test
	  void shouldSwapCardsWithInvalidCardException() {
	    // Given
	    Card nonexistentCard = cards[4];
	    Card newCard = cards[2];
	    Player player = new Player("Miles", Color.BLUE, cards[0], cards[1]);

	    // When - Then
	    assertThrows(InvalidCardException.class, () -> player.swapCard(nonexistentCard, newCard));
	  }
	}
