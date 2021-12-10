package rand;

import java.util.Random;

/**
 * Generating the random number based on different arguments or seed value.
 */
public class RandomGeneratorImpl implements RandomGenerator {

  private final Random rand;

  /**
   * Constructs random number based on seed value.
   * @param seed the seed value
   * */
  public RandomGeneratorImpl(int seed) {
    if (seed == -1) {
      rand = new Random();
    }
    else {
      rand = new Random(seed);
    }
  }

  /**
   * Constructs random number.
   * */
  public RandomGeneratorImpl() {
    rand = new Random();
  }

  /**
   * Get the number generated.
   * @param min the minimum value for a range
   * @param max the maximum value for a range
   * @return the integer generated
   * @throws IllegalArgumentException exception if invalid min and max values
   */
  public int getRandomNumber(int min, int max) throws IllegalArgumentException {
    if (min < 0 || max < min) {
      throw new IllegalArgumentException("Unexpected argument");
    }
    return rand.nextInt(max - min + 1) + min;
  }
}
