package model.dungeon;

/**
 * Different types of treasures which are to be alloted to different locations.
 * {@link #DIAMOND} treasure type diamond
 * {@link #RUBY} treasure type ruby
 * {@link #SAPPHIRE} treasure type sapphire
 * {@link #TOPAZ} treasure type topaz
 */
public enum TreasuresTypes {
  DIAMOND,
  RUBY,
  SAPPHIRE,
  TOPAZ;

  @Override
  public String toString() {
    switch (this) {
      case DIAMOND:
        return "Diamond";
      case TOPAZ:
        return "Topaz";
      case SAPPHIRE:
        return "Sapphire";
      case RUBY:
        return "Ruby";
      default:
        throw new IllegalArgumentException("Unexpected Argument");
    }
  }
}
