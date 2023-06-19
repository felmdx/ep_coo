package ep.main.game;

import ep.main.elem.Color;
import ep.main.elem.Piece;
import ep.main.elem.Position;
import ep.main.exceptions.IllegalMovementException;

/**
* Classe contendo ações e informações sobre cada espaço (quadrado) no tabuleiro
*/
public class Spot {
   private Piece piece;
   private Position pos;
   private Color color;
   private boolean isValid;

   private static final String filledSpace=
           "Espaço ocupado por uma peça da mesma cor";
   /**
    * Construtor para espaços com peça e com cor
    * @param piece Peça que inicia nesse espaço do tabuleiro
    * @param pos Posição do espaço no tabuleiro
    * @param color Cor do espaço no tabuleiro (Templo)
    */
   public Spot(Piece piece, Position pos, Color color) {
       this.piece = piece;
       this.pos = pos;
       this.color = color;
   }

   /**
    * Construtor para espaços com peça e sem cor
    * @param piece Peça que inicia nesse espaço do tabuleiro
    * @param pos Posição do espaço no tabuleiro
    */
   public Spot(Piece piece, Position pos) {
       this.piece = piece;
       this.pos = pos;
   }

   /**
    * Construtor para espaços sem peça e sem cor
    * @param pos Posição do espaço no tabuleiro
    */
   public Spot(Position pos) {
       this.pos = pos;
   }

   /**
    * Método que devolve a posição (coordenadas) do espaço
    * @return Objeto Position contendo a posição (coordenadas) do espaço
    */
   public Position getPosition() {

       return this.pos;
   }

   /**
    * Método que devolve a peça contida neste espaço
    * @return Objeto Piece caso tenha uma peça ou null caso o espaço esteja vazio
    */
   public Piece getPiece() {
       return this.piece;
   }

   /**
    * Método que devolve a cor do espaço
    * @return Enum Color com a cor do espaço. Caso o espaço não tenha cor, o valor do enum será NONE
    */
   Color getColor() {
       return this.color;
   }

   /**
    * Método que verifica se a posição é válida no tabuleiro
    * @return Um booleano que indica se o objeto representa uma posição válida no tabuleiro
    */
   public boolean isValid() {
       return this.isValid;
   }

   /**
    * Método que verifica se uma peça de mesma cor vai colidir com outra
    * @param piece As duas peças
    * @return Se uma cor é igual a outra
    */
   private boolean sameColor(Piece p1, Piece p2) {
       if (p1 == null || p1.getColor() == null) {
           return false;
       }

       if (p2 == null || p2.getColor() == null) {
           return false;
       }

       return p1.getColor().equals(p2.getColor());
   }
   /**
    * Método que ocupa o espaço atual com a peça passada
    * @param piece A peça para ocupar este espaço
    * @exception IllegalMovementException Caso o espaço já esteja ocupado por uma peça da mesma cor
    */
   protected void occupySpot(Piece piece) throws IllegalMovementException {
       if (this.sameColor(this.piece, piece)) {
           throw new IllegalMovementException(filledSpace);
       }
       this.piece = piece;
   }

   /**
    * Método que "libera" o espaço atual, ou seja, deixa-o vazio
    */
   protected void releaseSpot() {
       this.piece = null;
   }
}