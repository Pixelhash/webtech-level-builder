package de.codehat.levelbuilder;

import java.awt.*;
import java.util.Arrays;
import java.util.Optional;

/**
 * Represents the type of a tile.
 *
 * @author Marc-Niclas H. (codehat)
 */
public enum TileType {
    TERRAIN("t", Color.WHITE, new Color(128, 64, 0)), // brown
    HEDGE("h", Color.BLACK, Color.GREEN),
    START("s", Color.WHITE, Color.BLUE),
    GOAL("g", Color.WHITE, Color.RED);

    /**
     * all types as human-readable names
     */
    public static final String[] TYPES = { "Terrain (t)", "Hedge (h)", "Start (s)", "Goal (g)" };

    /**
     * short name of a type
     */
    final String abbreviation;

    /**
     * color of the button's text
     */
    final Color foregroundColor;

    /**
     * background color of the button in view
     */
    final Color backgroundColor;

    /**
     * Defines a tile type with a specific abbreviation.
     *
     * @param abbreviation the short name of that type
     */
    TileType(String abbreviation, Color foregroundColor, Color backgroundColor) {
        this.abbreviation = abbreviation;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
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