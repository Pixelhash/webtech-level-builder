package de.codehat.levelbuilder;

import java.util.Arrays;
import java.util.Optional;

public enum LevelType {
    TERRAIN("t"),
    HEDGE("h"),
    START("s"),
    GOAL("g");

    public static final String[] TYPES = { "Terrain (t)", "Hedge (h)", "Start (s)", "Goal (g)" };

    final String abbreviation;
    LevelType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public static LevelType fromAbbreviation(String abbreviation) {
        Optional<LevelType> ret = Arrays.stream(LevelType.values())
            .filter((lt) -> lt.abbreviation.equals(abbreviation)).findFirst();
        return ret.orElse(null);
    }

    public static LevelType fromIndex(int index) {
        LevelType ret;
        switch (index) {
            case 0:
                ret = LevelType.TERRAIN;
                break;
            case 1:
                ret = LevelType.HEDGE;
                break;
            case 2:
                ret = LevelType.START;
                break;
            case 3:
                ret = LevelType.GOAL;
                break;
            default:
                ret = null;
        }
        return ret;
    }
}
