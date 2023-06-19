package ep.main.elem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestPosition {
	  @Test
	  void shouldCreatePosition() {
	    // Given
	    int row = 0, col = 1;

	    // When
	    Position position = new Position(row, col);

	    // Then
	    assertEquals(row, position.getRow());
	    assertEquals(col, position.getCol());
	  }
	}