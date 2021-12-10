package model.dungeon;

/**
 * Monsters can reside in caves and can kill player if the player arrives
 * where the monster resides.
 */
interface Monsters {

  /**
   * Get the health of monster.
   * @return the health
   */
  int getHealth();

  /**
   * Reduce the monster's health if hit by arrow.
   */
  int reduceMonsterHealth();
}
