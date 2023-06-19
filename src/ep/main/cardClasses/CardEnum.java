package ep.main.cardClasses;

/**
 * Enum contendo cartas do jogo.
 *
 */
public enum CardEnum {
    TIGER("TIGER"),
    DRAGON("DRAGON"),
    FROG("FROG"),
    RABBIT("RABBIT"),
    CRAB("CRAB"),
    ELEPHANT("ELEPHANT"),
    GOOSE("GOOSE"),
    ROOSTER("ROOSTER");

    private String name;

    CardEnum(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}