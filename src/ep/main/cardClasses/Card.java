package ep.main.cardClasses;

import ep.main.elem.Color;
import java.util.Arrays;
import ep.main.elem.Position;

/** Classe que contém informações das cartas */
public class Card {
    private String name;
    private Color color;
    private Position[] positions;

    //private static final int totalCards = 5;

    /**
     * Construtor que define os principais atributos de uma carta
     *
     * @param name Nome da carta
     * @param color Cor da carta
     * @param positions Todas as posições relativas de movimento
     */
    public Card(String name, Color color, Position[] positions) {
        this.name = name;
        this.color = color;
        this.positions = positions;
    }

    /**
     * Método que devolve o nome da carta
     *
     * @return String que contém o nome da carta
     */
    public String getName() {
        return this.name;
    }

    /**
     * Método que devolve a cor da carta
     *
     * @return Enum Color que contém a cor da carta
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Método que devolve todas as possíveis posições relativas de movimento. A posição atual da peça
     * é o ponto de origem (0,0). Uma carta possui as possíveis posições de movimento em relação ao
     * ponto de origem.
     *
     * @return Um array de Position contendo todas as possíveis posições de movimento em relação ao
     *     ponto de origem
     */
    public Position[] getPositions() {
        return this.positions;
    }

    /**
     * Método que cria todas as cartas do jogo, embaralha-as e devolve as 5 que serão utilizadas na
     * partida.
     *
     * @return Vetor de cartas com todas as cartas do jogo
     */
    public static Card[] createCards() {
        Card[] cardList = Cards.getCards();
        return Cards.selectCards(cardList);
    }

    @Override
    public String toString() {
        return "Card{"
                + "name='"
                + this.name
                + '\''
                + ", color="
                + this.color
                + ", positions="
                + Arrays.toString(this.positions)
                + '}';
    }
}
