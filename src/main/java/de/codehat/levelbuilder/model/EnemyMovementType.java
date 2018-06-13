package de.codehat.levelbuilder.model;

/**
 * Represents the movement type of an enemy.
 *
 * @author Marc-Niclas H. (codehat)
 */
public enum EnemyMovementType {

    /**
     * Moves horizontal, but turns left first.
     */
    HOR_FIRST_LEFT,

    /**
     * Moves horizontal, but turns right first.
     */
    HOR_FIRST_RIGHT,

    /**
     * Moves vertical, but turns up first.
     */
    VERT_FIRST_UP,

    /**
     * Moves vertical, but turns down first.
     */
    VERT_FIRST_DOWN,

    /**
     * Moves only if the player is in direct sight (no obstacles between them; not diagonal).
     */
    ON_SIGHT;

    /**
     * All types as human-readable names.
     */
    public static final String[] TYPES = {
            "Horizontal First Left",
            "Horizontal First Right",
            "Vertical First Up",
            "Vertical First Down",
            "On Sight",
    };

    /**
     * The index in the {@link #TYPES} array of this tile type.
     */
    public static final int HOR_FIRST_LEFT_INDEX = 0;

    /**
     * The index in the {@link #TYPES} array of this tile type.
     */
    public static final int HOR_FIRST_RIGHT_INDEX = 1;

    /**
     * The index in the {@link #TYPES} array of this tile type.
     */
    public static final int VERT_FIRST_UP_INDEX = 2;

    /**
     * The index in the {@link #TYPES} array of this tile type.
     */
    public static final int VERT_FIRST_DOWN_INDEX = 3;

    /**
     * The index in the {@link #TYPES} array of this tile type.
     */
    public static final int ON_SIGHT_INDEX = 4;

    /**
     * Returns the enemy movement type for a given index.
     *
     * @param index the index
     * @return the corresponding enemy movement type
     */
    public static EnemyMovementType fromIndex(final int index) {
        EnemyMovementType ret;
        switch (index) {
            case HOR_FIRST_LEFT_INDEX:
                ret = EnemyMovementType.HOR_FIRST_LEFT;
                break;
            case HOR_FIRST_RIGHT_INDEX:
                ret = EnemyMovementType.HOR_FIRST_RIGHT;
                break;
            case VERT_FIRST_UP_INDEX:
                ret = EnemyMovementType.VERT_FIRST_UP;
                break;
            case VERT_FIRST_DOWN_INDEX:
                ret = EnemyMovementType.VERT_FIRST_DOWN;
                break;
            case ON_SIGHT_INDEX:
                ret = EnemyMovementType.ON_SIGHT;
                break;
            default:
                ret = null;
        }
        return ret;
    }
}
