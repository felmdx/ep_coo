package ep.main.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import ep.main.elem.*;
import ep.main.exceptions.IllegalMovementException;

public class TestSpot {
	  @Test
	  void shouldCreateSpotWithPosition() {
	    // Given
	    Position position = new Position(0, 0);

	    // When
	    Spot spot = new Spot(position);

	    // Then
	    assertEquals(position, spot.getPosition());
	  }

	  @Test
	  void shouldCreateSpotWithPieceAndPosition() {
	    // Given
	    Position position = new Position(0, 0);
	    Piece piece = new Piece(Color.BLUE, Boolean.TRUE);

	    // When
	    Spot spot = new Spot(piece, position);

	    // Then
	    assertEquals(position, spot.getPosition());
	    assertEquals(piece, spot.getPiece());
	  }

	  @Test
	  void shouldCreateSpotWithPieceAndPositionAndColor() {
	    // Given
	    Position position = new Position(0, 0);
	    Piece piece = new Piece(Color.RED, Boolean.TRUE);

	    // When
	    Spot spot = new Spot(piece, position, Color.RED);

	    // Then
	    assertEquals(position, spot.getPosition());
	    assertEquals(piece, spot.getPiece());
	    assertEquals(Color.RED, spot.getColor());
	  }

	  @Test
	  void shouldReleaseSpot() {
	    // Given
	    Position position = new Position(0, 0);
	    Spot spot = new Spot(position);

	    // When
	    spot.releaseSpot();

	    // Then
	    assertNull(spot.getPiece());
	  }

	  @Test
	  void shouldOccupySpotSuccessfully() {
	    // Given
	    Position position = new Position(0, 0);
	    Piece oldPiece = new Piece(Color.RED, Boolean.TRUE);
	    Piece newPiece = new Piece(Color.BLUE, Boolean.TRUE);
	    Spot spot = new Spot(oldPiece, position);

	    // When
	    spot.occupySpot(newPiece);

	    // Then
	    assertEquals(newPiece, spot.getPiece());
	  }

	  @Test
	  void shouldOccupySpotThrowIllegalMovementException() {
	    // Given
	    Position position = new Position(0, 0);
	    Piece oldPiece = new Piece(Color.BLUE, Boolean.TRUE);
	    Piece newPiece = new Piece(Color.BLUE, Boolean.FALSE);
	    Spot spot = new Spot(oldPiece, position);

	    // When - Then
	    assertThrows(IllegalMovementException.class, () -> spot.occupySpot(newPiece));
	  }
	}
