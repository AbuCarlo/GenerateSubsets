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
}