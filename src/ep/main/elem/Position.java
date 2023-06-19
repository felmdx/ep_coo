package ep.main.elem;

/** Classe usada para definição de estrutura de posições e movimentos do jogo */
public class Position {
    private final int row;
    private final int col;

    public static final int rowR = 4;
    public static final int rowB = 0;
    public static final int colTemple = 2;

    /**
     * Construtor que define o valor da Linha e da Coluna da posição, baseado no plano cartesiano]
     *
     * @param row Linha
     * @param col Coluna
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Método que devolve o valor do eixo X da posição
     *
     * @return Um valor int representando o eixo X
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Método que devolve o valor do eixo Y da posição
     *
     * @return Um valor int representando o eixo Y
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Método que verifica se a posição possui uma peça de acordo com a configuração INICIAL do jogo
     *
     * @return Um valor booleano
     */
    public Boolean hasPiece() {
        return this.row == rowR || this.row == rowB;
    }

    /**
     * Método que verifica se a posição possui é um templo de acordo configuração do jogo
     *
     * @return Um valor booleano
     */
    public Boolean hasTemple() {
        return this.hasPiece() && this.col == colTemple;
    }

    /**
     * Método que retorna a cor da peça presente na posição, de acordo com a configuração INICIAL do
     * jogo
     *
     * @return Um valor booleano
     */
    public Color getPieceColor() {
        if (this.row == rowB) {
            return Color.BLUE;
        } else if (this.row == rowR) {
            return Color.RED;
        }
        return Color.NONE;
    }

    @Override
    public String toString() {
        return "Position{" + "row=" + this.row + ", col=" + this.col + '}';
    }
}