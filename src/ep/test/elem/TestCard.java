package ep.test.elem;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import ep.main.*;

public class TestCard {
    @Test
    void shouldCreateCard() {
        // Given
        String cardName = "tiger";
        Position[] positionList = new Position[1];

        // When
        Card card = new Card(cardName, Color.BLUE, new Position[1]);

        // Then
        assertEquals(positionList.length, card.getPositions().length);
        assertEquals(cardName, card.getName());
        assertEquals(Color.BLUE, card.getColor());
    }

    @Test
    void shouldCreateAllCardsInTheGame() {
        // When
        Card[] cards = Card.createCards();

        // Then
        assertEquals(5, cards.length);
    }
}
