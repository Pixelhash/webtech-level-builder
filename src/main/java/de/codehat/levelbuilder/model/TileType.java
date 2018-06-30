package de.codehat.levelbuilder.model;

import java.awt.Color;
import java.util.Arrays;
import java.util.Optional;

/**
 * Represents the type of a tile.
 *
 * @author Marc-Niclas H. (codehat)
 */
public enum TileType {
    /**
     * The terrain.
     */
    TERRAIN("t", Color.WHITE, new Color(128, 64, 0)), // brown

    /**
     * The hedge.
     */
    HEDGE("h", Color.BLACK, Color.GREEN),

    /**
     * The rabbit (start point).
     */
    RABBIT("r", Color.BLACK, Color.WHITE),

    /**
     * The goal.
     */
    GOAL("g", Color.WHITE, Color.RED),

    /**
     * The fox.
     */
    FOX("f", Color.BLACK, Color.ORANGE),

    /**
     * The speed power-up.
     */
    SPEED_POWERUP("sp", Color.BLACK, Color.YELLOW);

    /**
     * All types as human-readable names.
     */
    public static final String[] TYPES = {
            "Terrain (t)",
            "Hedge (h)",
            "Rabbit (r)",
            "Goal (g)",
            "Fox (f)",
            "Speed Power-up (sp)",
    };

    /**
     * The index in the {@link #TYPES} array of this tile type.
     */
    public static final int TERRAIN_INDEX = 0;

    /**
     * The index in the {@link #TYPES} array of this tile type.
     */
    public static final int HEDGE_INDEX = 1;

    /**
     * The index in the {@link #TYPES} array of this tile type.
     */
    public static final int RABBIT_INDEX = 2;

    /**
     * The index in the {@link #TYPES} array of this tile type.
     */
    public static final int GOAL_INDEX = 3;

    /**
     * The index in the {@link #TYPES} array of this tile type.
     */
    public static final int FOX_INDEX = 4;

    /**
     * The index in the {@link #TYPES} array of this tile type.
     */
    public static final int SPEED_POWERUP_INDEX = 5;

    /**
     * Short name of a type.
     */
    private final String abbreviation;

    /**
     * Color of the button's text.
     */
    private final Color foregroundColor;

    /**
     * Background color of the button in view.
     */
    private final Color backgroundColor;

    /**
     * Defines a tile type with a specific abbreviation.
     *
     * @param abbreviation the short name of that type
     * @param foregroundColor the foreground color of the button
     * @param backgroundColor the background color of the button
     */
    TileType(final String abbreviation, final Color foregroundColor,
             final Color backgroundColor) {
        this.abbreviation = abbreviation;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
    }

    /**
     * Returns the tile type's abbreviation.
     *
     * @return the tile type's abbreviation
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Returns the foreground color for a tile button.
     *
     * @return the foreground color for a tile button
     */
    public Color getForegroundColor() {
        return foregroundColor;
    }

    /**
     * Returns the background color for a tile button.
     *
     * @return the background color for a tile button
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public String toString() {
        return this.name();
    }

    /**
     * Returns the tile type for a given abbreviation.
     *
     * @param abbreviation the abbreviation of a tile
     * @return the corresponding tile type
     */
    @Deprecated
    public static TileType fromAbbreviation(final String abbreviation) {
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
    public static TileType fromIndex(final int index) {
        TileType ret;
        switch (index) {
            case TERRAIN_INDEX:
                ret = TileType.TERRAIN;
                break;
            case HEDGE_INDEX:
                ret = TileType.HEDGE;
                break;
            case RABBIT_INDEX:
                ret = TileType.RABBIT;
                break;
            case GOAL_INDEX:
                ret = TileType.GOAL;
                break;
            case FOX_INDEX:
                ret = TileType.FOX;
                break;
            case SPEED_POWERUP_INDEX:
                ret = TileType.SPEED_POWERUP;
                break;
            default:
                ret = null;
        }
        return ret;
    }
}
