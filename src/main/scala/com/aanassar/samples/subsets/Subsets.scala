package com.aanassar.samples.subsets

import scala.Iterator
import scala.collection.immutable.BitSet

object Subsets {
  
  def toBitSet(l: Long) = BitSet.fromBitMaskNoCopy(Array(l))

  def create(n: Int, k: Int): Iterator[Long] = {
    
    require(n >= 0, "A set cannot have a size < 0");
    require(n <= java.lang.Long.SIZE, "A set cannot have a size > 64");
    
    def recurse(accumulation: Long, start: Int, remaining: Int): Iterator[Long] = {
      // If no bits are left to flip, end the recursion.
      if (remaining == 0)
        Iterator(accumulation)
      else {
      	// Not tail-recursive! Only when we've generated all the subsets 
        // including the current index can we move on to the next possible position.
        // Since we're only concatenating iterators, however, this will still be fast.
        (start to (n - remaining)).iterator.flatMap ({ shift =>
          val flipped = 1L << shift
          recurse(accumulation | flipped, shift + 1, remaining - 1)
        })
      }
    }

    recurse(0, 0, k)
  }                                              
}