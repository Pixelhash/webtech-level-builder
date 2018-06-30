package de.codehat.levelbuilder.model;

/**
 * Represents a tile in the level, which has a {@link TileType}.
 *
 * @author Marc-Niclas H. (codehat)
 */
public class Tile {

    /**
     * The position of the tile in the level.
     */
    private Position position;

    /**
     * The type of this tile.
     */
    private TileType type;

    /* ==================================================== */

    /**
     * The movement type of this type, if it's an enemy.
     * *EXTENSION ATTRIBUTE* -> Can be null!
     */
    private EnemyMovementType enemyMovementType;

    /* ==================================================== */

    /**
     * The appear chance of a speed power-up (carrot).
     * *EXTENSION ATTRIBUTE* -> Can be null!
     */
    private Double appearChance;

    /**
     * The time how long the speed power-up is on the field.
     * *EXTENSION ATTRIBUTE* -> Can be null!
     */
    private Integer timeOnField;

    /**
     * The speed increase of the rabbit, if it collects the power-up.
     * *EXTENSION ATTRIBUTE* -> Can be null!
     */
    private Integer speedIncrease;

    /* ==================================================== */

     /**
     * Creates a new tile.
     *
     * @param row row-coordinate of this tile
     * @param col col-coordinate of this tile
     * @param type the type of this tile
     */
    public Tile(final int row, final int col, final TileType type) {
        this.position = new Position(row, col);
        this.type = type;
    }

    /**
     * Creates a new tile with the enemy extensions.
     *
     * @param row row-coordinate of this tile
     * @param col col-coordinate of this tile
     * @param type the type of this tile
     * @param enemyMovementType the movement type of this tile, if it's an enemy.
     */
    public Tile(final int row, final int col, final TileType type, final EnemyMovementType enemyMovementType) {
        this.position = new Position(row, col);
        this.type = type;
        this.enemyMovementType = enemyMovementType;
    }

    /**
     * Creates a new tile with the speed power-up extensions.
     *
     * @param row row-coordinate of this tile
     * @param col col-coordinate of this tile
     * @param type the type of this tile
     * @param appearChance the chance that this power-up appears on the field.
     * @param timeOnField the time the power-up stays on the field.
     * @param speedIncrease the speed increase of the rabbit, if it collects the power-up.
     */
    public Tile(final int row, final int col, final TileType type, double appearChance, int timeOnField, int speedIncrease) {
        this.position = new Position(row, col);
        this.type = type;
        this.appearChance = appearChance;
        this.timeOnField = timeOnField;
        this.speedIncrease = speedIncrease;
    }

    /**
     * Returns the tile's position.
     *
     * @return the tile's position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the tile's position.
     *
     * @param position the tile's position to set
     */
    public void setPosition(final Position position) {
        this.position = position;
    }

    /**
     * Returns the tile type.
     *
     * @return the tile type
     */
    public TileType getType() {
        return type;
    }

    /**
     * Sets the tile type.
     *
     * @param type the tile type to set.
     */
    public void setType(final TileType type) {
        this.type = type;
    }

    /**
     * Returns the enemy movement type.
     *
     * @return the enemy movement type
     */
    public EnemyMovementType getEnemyMovementType() {
        return enemyMovementType;
    }

    /**
     * Sets the enemy movement type.
     *
     * @param enemyMovementType the enemy movement type to set.
     */
    public void setEnemyMovementType(EnemyMovementType enemyMovementType) {
        this.enemyMovementType = enemyMovementType;
    }

    /**
     * Returns the appear chance of a speed power-up.
     *
     * @return the appear chance.
     */
    public Double getAppearChance() {
        return appearChance;
    }

    /**
     * Sets the appear chance of a speed power-up.
     *
     * @param appearChance the appear chance of a speed power-up.
     */
    public void setAppearChance(Double appearChance) {
        this.appearChance = appearChance;
    }

    /**
     * Returns the time the speed power-up stays on the field.
     *
     * @return the time the power-up stays on the field.
     */
    public Integer getTimeOnField() {
        return timeOnField;
    }

    /**
     * Sets the time the speed power-up stays on the field.
     *
     * @param timeOnField the time the speed power-up stays on the field.
     */
    public void setTimeOnField(Integer timeOnField) {
        this.timeOnField = timeOnField;
    }

    /**
     * Returns the speed increase of the rabbit, if it collects the speed power-up.
     *
     * @return the speed increase of the rabbit, if it collects the speed power-up.
     */
    public Integer getSpeedIncrease() {
        return speedIncrease;
    }

    /**
     * Sets the speed increase of the rabbit, if it collects the speed power-up.
     *
     * @param speedIncrease the speed power-up of the rabbit, if it collects the speed power-up.
     */
    public void setSpeedIncrease(Integer speedIncrease) {
        this.speedIncrease = speedIncrease;
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) {
            return false;
        }
        Tile t = (Tile) o;
        return position.equals(t.position) && type == t.getType();
    }

    @Override
    public String toString() {
        return String.format("{ Pos: %s, Type: %s }", position, type);
    }
}
