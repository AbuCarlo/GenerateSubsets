# Generate All Subsets of a Set

I have to implement the dynamic-programming solution the Travelling Salesman Problem for a course I'm taking in my spare time, which necessitates an economical way to iterate through all subsets of cardinality k of a set of cities. I puzzled over this one for a few days, to be honest. The code in both Java and Scala turned out to be very simple. 

I'm grateful to the explanation offered here: <http://www.stefan-pochmann.info/spots/tutorials/sets_subsets/>. In particular, I would  not otherwise have known that no less than Steven Skiena has claimed, "Unfortunately, it is surprisingly difficult to generate subsets in lexicographic order. Unless you have a compelling reason to do so, forget about it." While it wasn't my intention to win an argument, the lexicographical ordering follows naturally from the implementation. I assume your set is sorted and indexed. The subsets, consisting of array indices, will be returned to you in what is then effectively lexicographical order. Or, to put it another way, the unsigned long integers as which the
subsets are implemented are sorted numerically. Let's take an alphabet of the 5 letters from 'A' to 'E'.

```
  val alphabet = ('A' to 'E').toArray
  
  val alphabetSubsets = create(alphabet.size, 3).map(toBitSet).foreach { bitset =>
    // Map the array index contained in the set back to the array element.
    val word = bitset.iterator.map(alphabet(_))
    println(word.mkString)
  }
```
It follows that the only subset in which 'C' is the largest element also contains 'D' and 'E', and will show up last. To wit:

```
ABC
ABD
ABE
ACD
ACE
ADE
BCD
BCE
BDE
CDE
```

