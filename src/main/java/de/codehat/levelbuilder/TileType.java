package de.codehat.levelbuilder;

import java.util.Arrays;
import java.util.Optional;

/**
 * Represents the type of a tile.
 *
 * @author Marc-Niclas H. (codehat)
 */
public enum TileType {
    TERRAIN("t"),
    HEDGE("h"),
    START("s"),
    GOAL("g");

    /**
     * all types as human-readable names
     */
    public static final String[] TYPES = { "Terrain (t)", "Hedge (h)", "Start (s)", "Goal (g)" };

    /**
     * short name of a type
     */
    final String abbreviation;

    /**
     * Defines a tile type with a specific abbreviation.
     *
     * @param abbreviation the short name of that type
     */
    TileType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     * Returns the tile type for a given abbreviation.
     *
     * @param abbreviation the abbreviation of a tile
     * @return the corresponding tile type
     */
    @Deprecated
    public static TileType fromAbbreviation(String abbreviation) {
        Optional<TileType> ret = Arrays.stream(TileType.values())
            .filter((lt) -> lt.abbreviation.equals(abbreviation)).findFirst();
        return ret.orElse(null);
    }

    /**
     * Returns the tile type for a given index.
     *
     * @param index the index
     * @return the corresponding tile type
     */
    public static TileType fromIndex(int index) {
        TileType ret;
        switch (index) {
            case 0:
                ret = TileType.TERRAIN;
                break;
            case 1:
                ret = TileType.HEDGE;
                break;
            case 2:
                ret = TileType.START;
                break;
            case 3:
                ret = TileType.GOAL;
                break;
            default:
                ret = null;
        }
        return ret;
    }
}
