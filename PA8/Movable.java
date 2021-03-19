package PA8;

/**
 * Name: Bryce Ong
 * ID: A16287711
 * Email: bong@ucsd.edu
 * Sources used: None
 * 
 * This file is used for PA#8 Part 1. It is used to define the movements of
 * Movable objects.
 */

/**
 * This interface contains 1 method that is used to determine
 * where Movable objects will be moved.
 */
public interface Movable {
  /**
   * This abstract method is a template that each Movable type class 
   * will implement with its own behavior.
   */
  public abstract int[] getMove();
}
