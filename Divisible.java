/**
 * Name: Bryce Ong
 * ID: A16287711
 * Email: bong@ucsd.edu
 * Sources used: None
 * 
 * This file is used for PA#8 Part 1. It is used to define the division
 * behavior of Divisible objects.
 */

/**
 * This interface contains 1 method that is used to determine
 * where Divisible objects should divide to.
 */
public interface Divisible {
  /**
   * This abstract method is a template that each Divisable type class 
   * will implement with its own behavior.
   */
  public abstract int[] getDivision();
}
