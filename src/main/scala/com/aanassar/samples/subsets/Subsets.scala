package com.aanassar.samples.subsets

import scala.collection.immutable.BitSet

object Subsets extends App {
  
  def toBitSet(l: Long) = BitSet.fromBitMaskNoCopy(Array(l))

  def create(n: Int, k: Int): Iterator[Long] = {
    
    require(n >= 0, "A set cannot have a size < 0");
    require(n <= java.lang.Long.SIZE, "A set cannot have a size > 64");
    
    def recurse(accumulation: Long, start: Int, remaining: Int): Iterator[Long] = {
      // If no bits are left to flip, end the recursion.
      if (remaining == 0)
        Iterator(accumulation)
      else {
      	// Not tail-recursive!
        (start to (n - remaining)).iterator.flatMap ({ shift =>
          val flipped = 1L << shift
          recurse(accumulation | flipped, shift + 1, remaining - 1)
        })
      }
    }

    recurse(0, 0, k)
  }                                              

  val bitsets = create(2, 1).map(toBitSet).toArray
  
  println(bitsets.mkString(", "))
  
  val names = Array("Tony", "Donavan", "Carlo", "Leo").sorted
  
  val familySubsets = create(names.size, 2).map(toBitSet).foreach { bitset =>
    val clique = bitset.iterator.map(names(_))
    println("Clique: " + clique.mkString(", "))
  }

}