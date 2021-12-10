package rand;

/**
 * Generating a random number based on the range provided.
 */
public interface RandomGenerator {

  /**
   * Get the number generated.
   * @param min the minimum value for a range
   * @param max the maximum value for a range
   * @return the integer generated
   * @throws IllegalArgumentException exception if invalid min and max values
   */
  int getRandomNumber(int min, int max) throws IllegalArgumentException;
}
