package ep.main.cardClasses;

import ep.main.elem.Color;
import ep.main.elem.Position;
import ep.main.utils.*;
import java.util.List;

public class Cards {
    private static final int totalCards = 5;

    public static Card[] selectCards(Card[] deck) {
        Card[] randomCards = new Card[totalCards];

        List<Integer> randomIntegers = NumberUtils.getNumbers(totalCards, 0, deck.length - 1);

        for (int i = 0; i < totalCards; i++) {
            randomCards[i] = deck[randomIntegers.get(i)];
        }

        return randomCards;
    }

    public static Card[] getCards() {
        return new Card[] {
                getTiger(),
                getDragon(),
                getFrog(),
                getRabbit(),
                getCrab(),
                getElephant(),
                getGoose(),
                getRooster()
        };
    }

    public static Card getTiger() {
        return new Card(
                CardEnum.TIGER.getName(),
                Color.BLUE,
                new Position[] {new Position(-2, 0), new Position(1, 0)});
    }

    public static Card getDragon() {
        return new Card(
                CardEnum.DRAGON.getName(),
                Color.RED,
                new Position[] {
                        new Position(-1, -2), new Position(-1, 2), new Position(1, -1), new Position(1, 1)
                });
    }

    public static Card getFrog() {
        return new Card(
                CardEnum.FROG.getName(),
                Color.RED,
                new Position[] {new Position(-1, -1), new Position(0, -2), new Position(1, 1)});
    }

    public static Card getRabbit() {
        return new Card(
                CardEnum.RABBIT.getName(),
                Color.BLUE,
                new Position[] {new Position(-1, 1), new Position(0, 2), new Position(1, -1)});
    }

    public static Card getCrab() {
        return new Card(
                CardEnum.CRAB.getName(),
                Color.BLUE,
                new Position[] {new Position(-1, 0), new Position(0, 2), new Position(0, -2)});
    }

    public static Card getElephant() {
        return new Card(
                CardEnum.ELEPHANT.name(),
                Color.RED,
                new Position[] {
                        new Position(-1, -1), new Position(0, -1), new Position(-1, 1), new Position(0, 1)
                });
    }

    public static Card getGoose() {
        return new Card(
                CardEnum.GOOSE.getName(),
                Color.BLUE,
                new Position[] {
                        new Position(0, -1), new Position(-1, -1), new Position(0, 1), new Position(1, 1)
                });
    }

    public static Card getRooster() {
        return new Card(
                CardEnum.ROOSTER.getName(),
                Color.RED,
                new Position[] {
                        new Position(0, -1), new Position(0, 1), new Position(-1, 1), new Position(1, -1)
                });
    }
}
