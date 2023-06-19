package ep.test.elem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ep.main.elem.*;

public class TestPiece {
	  @Test
	  void shouldCreatePiece() {
	    // When
	    Piece piece = new Piece(Color.BLUE, Boolean.TRUE);

	    // Then
	    assertEquals(Boolean.TRUE, piece.isMaster());
	    assertEquals(Color.BLUE, piece.getColor());
	  }
}
