package ep.main.game;

import java.util.Objects;

import ep.main.cardClasses.Card;
import ep.main.cardClasses.Cards;
import ep.main.elem.Color;
import ep.main.elem.Piece;
import ep.main.elem.Position;
import ep.main.exceptions.IllegalMovementException;
import ep.main.exceptions.IncorrectTurnOrderException;
import ep.main.exceptions.InvalidCardException;
import ep.main.exceptions.InvalidPieceException;

public class GameImpl implements Game {
    private static final int boardSize = 5;

    private Player redP;
    private Player blueP;
    private Card tableCard;
    private Spot[][] gameBoard;
    private Color nextTurn;
    private Card[] gameCards;

    private static final String invalidPiece = "Não existe nenhuma peça válida nessa posição";
    private static final String wrongTurn =
            "O jogador só pode movimentar suas peças no seu respectivo turno";
    private static final String illegalMove =
            "A peça não pode ser movida para essa nova posição.";

    public GameImpl() {}

    public GameImpl(Player redPlayer, Player bluePlayer) {
        this.redP = redPlayer;
        this.blueP = bluePlayer;
    }

    public GameImpl(Player redPlayer, Player bluePlayer, Card[] cardList) {
        this.redP = redPlayer;
        this.blueP = bluePlayer;
        this.gameCards = Cards.selectCards(cardList);
        this.tableCard = this.gameCards[0];
        this.nextTurn = this.tableCard.getColor();
    }

    public Card[] getGameCards() {
        return this.gameCards;
    }

    /**
     * Método que devolve a cor da posição do tabuleiro. Se possui uma cor, significa que é um templo.
     * Caso contrário, é um espaço normal
     *
     * @param position Posição do tabuleiro
     * @return O enum Color que representa a cor da posição
     */
    @Override
    public Color getSpotColor(Position pos) {
        return this.getSpot(pos).getColor();
    }

    /**
     * Método que devolve a peça que está na posição do tabuleiro
     *
     * @param position Posição do tabuleiro
     * @return Um objeto Piece que representa a peça na posição indicada. Se não tiver peça, devolve
     *     null
     */
    @Override
    public Piece getPiece(Position pos) {
        return Objects.nonNull(this.getSpot(pos))
                ? this.getSpot(pos).getPiece()
                : null;
    }

    /**
     * Método que devolve a carta que está na mesa, que será substituída após a próxima jogada
     *
     * @return Um objeto Card que representa a carta na mesa
     */
    @Override
    public Card getTableCard() {
        return this.tableCard;
    }

    /**
     * Método que devolve as informações sobre o jogador com as peças vermelhas
     *
     * @return Um objeto Player que representa o jogador vermelho
     */
    @Override
    public Player getRedPlayer() {
        return this.redP;
    }

    /**
     * Método que devolve as informações sobre o jogador com as peças azuis
     *
     * @return Um objeto Player que representa o jogador azul
     */
    @Override
    public Player getBluePlayer() {
        return this.blueP;
    }

    /**
     * Método que move uma peça
     *
     * @param card A carta de movimento que será usada
     * @param cardMove A posição para onde a peça irá se mover
     * @param currentPos A posição da atual da peça
     * @exception IncorrectTurnOrderException Caso não seja a vez de um jogador fazer um movimento
     * @exception IllegalMovementException Caso uma peça seja movida para fora do tabuleiro ou para
     *     uma posição onde já tem uma peça da mesma cor
     * @exception InvalidCardException Caso uma carta que não está na mão do jogador seja usada
     * @exception InvalidPieceException Caso uma peça que não está no tabuleiro seja usada
     */
    @Override
    public void makeMove(Card card, Position cardMove, Position thisPos)
            throws IncorrectTurnOrderException, IllegalMovementException, InvalidCardException,
            InvalidPieceException {
        Player actualP = this.nextTurn == Color.RED ? this.redP : this.blueP;
        actualP.cardException(card);

        Spot thisSpot = this.getSpot(thisPos);
        Piece piece = this.getPiece(thisPos);

        if (Objects.isNull(piece)) {
            throw new InvalidPieceException(invalidPiece);
        }
        if (!actualP.getPieceColor().equals(piece.getColor())) {
            throw new IncorrectTurnOrderException(wrongTurn);
        }

        int newRow = thisPos.getRow() + cardMove.getRow();
        int newCol = thisPos.getCol() + cardMove.getCol();
        Spot newSpot = this.getSpot(newRow, newCol);

        boolean newSpotIsValid =
                Objects.nonNull(newSpot)
                        && Objects.nonNull(newSpot.getPiece())
                        && (newSpot.getPiece().getColor() != actualP.getPieceColor());

        if (!this.validPos(newRow, newCol) || !newSpotIsValid) {
            throw new IllegalMovementException(illegalMove);
        }

        newSpot.occupySpot(piece);
        thisSpot.releaseSpot();
        actualP.swapCard(card, this.tableCard);

        this.tableCard = card;
        this.nextTurn = this.nextTurn == Color.RED ? Color.BLUE : Color.RED;
    }

    /**
     * Métodos para verificar se as coordenadas são válidas
     *
     */
    private Spot getSpot(Position pos) {
        return this.getSpot(pos.getRow(), pos.getCol());
    }

    private Spot getSpot(int row, int col) {
        if (!this.validPos(row, col)) {
            return null;
        }
        return this.gameBoard[row][col];
    }

    private Boolean validPos(int row, int col) {
        return (row >= 0 && row < boardSize) && (col >= 0 && col < boardSize);
    }

    /**
     * Método que confere se um jogador de uma determinada cor venceu o jogo. Critérios de vitória: —
     * Derrotou a peça de mestre adversária — Posicionou o seu mestre na posição da base adversária
     *
     * @param color Cor das peças do jogador que confere a condição de vitória
     * @return Um booleano true para caso esteja em condições de vencer e false caso contrário
     */
    @Override
    public boolean checkVictory(Color color) {
        Color looserC = color == Color.RED ? Color.BLUE : Color.RED;
        int looserRow = looserC == Color.RED ? Position.rowR : Position.rowB;

        Piece winner = this.gameBoard[looserRow][Position.colTemple].getPiece();
        if (winner.getColor() == color && winner.isMaster()) {
            return true;
        }

        Piece looserMaster = this.findMaster(looserC);
        return Objects.isNull(looserMaster);
    }

    public void initializeBoard() {
        this.gameBoard = new Spot[this.boardSize][this.boardSize];

        for (int row = 0; row < this.boardSize; row++) {
            for (int col = 0; col < this.boardSize; col++) {
                this.gameBoard[row][col] = this.initializeSpot(row, col);
            }
        }
    }

    /**
     * Métodos de inicialização
     *
     */
    private Spot initializeSpot(int row, int col) {
        Position pos = new Position(row, col);
        Piece piece;

        if (pos.hasTemple()) {
            piece = new Piece(pos.getPieceColor(), Boolean.TRUE);
            return new Spot(piece, pos, piece.getColor());
        }
        if (pos.hasPiece()) {
            piece = new Piece(pos.getPieceColor(), Boolean.FALSE);
            return new Spot(piece, pos, Color.NONE);
        }

        return new Spot(null, pos, Color.NONE);
    }

    /**
     * Métodos para verificar o Master
     *
     */
    private Piece findMaster(Color color) {
        Piece piece;

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                piece = this.gameBoard[row][col].getPiece();

                if (piece.getColor() == color && piece.isMaster()) {
                    return piece;
                }
            }
        }

        return null;
    }

}