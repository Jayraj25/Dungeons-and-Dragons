package model.dungeon;

class Otyugh implements Monsters {

  private int health;

  /**
   * Create an Otyugh whose health is 100 (full) by default.
   */
  public Otyugh() {
    this.health = 100;
  }

  @Override
  public int getHealth() {
    return this.health;
  }

  @Override
  public int reduceMonsterHealth() {
    this.health /= 2;
    return health;
  }
}
