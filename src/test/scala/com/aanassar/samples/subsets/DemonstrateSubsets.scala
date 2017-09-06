package com.aanassar.samples.subsets

import com.aanassar.samples.subsets.Subsets._

object DemonstrateSubsets extends App {
  
  val bitsets = create(2, 1).map(toBitSet).toArray
  
  println(bitsets.mkString(", "))
  
  val alphabet = ('A' to 'E').toArray
  
  val alphabetSubsets = create(alphabet.size, 3).map(toBitSet).foreach { bitset =>
    // Map the array index contained in the set back to the array element.
    val word = bitset.iterator.map(alphabet(_))
    println(word.mkString)
  }
  
  val cities = (0 until 32).toArray
  
  // This is fast. Iterating over `n choose k` elements will _not_ be fast.
  val citySubsets = create(32, 16)
  
  println(s"First subset of cities where k = 16: ${citySubsets.next}")
}