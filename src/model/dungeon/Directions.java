package model.dungeon;

/**
 * The possible directions in which a player can move from a given location.
 * {@link #NORTH} the north direction
 * {@link #SOUTH} the south direction
 * {@link #EAST} the east direction
 * {@link #WEST} the west direction
 */
public enum Directions {
  NORTH,
  SOUTH,
  EAST,
  WEST;

  /**
   * Get the opposite direction of the direction provided.
   * @return the opposite direction
   */
  public Directions getOppositeDirection() {
    switch (this) {
      case SOUTH:
        return NORTH;
      case EAST:
        return WEST;
      case NORTH:
        return SOUTH;
      case WEST:
        return EAST;
      default:
        throw new IllegalArgumentException("Unexpected Argument");
    }
  }

  @Override
  public String toString() {
    switch (this) {
      case WEST:
        return "West";
      case EAST:
        return "East";
      case NORTH:
        return "North";
      case SOUTH:
        return "South";
      default:
        throw new IllegalArgumentException("Unexpected argument");
    }
  }
}
