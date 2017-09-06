package com.aanassar.samples.subsets

import org.apache.commons.math3.util.CombinatoricsUtils
import org.junit.runner.RunWith
import org.scalacheck.Gen
import org.scalacheck.Prop.propBoolean
import org.scalatest.FunSuite
import org.scalatest.Inspectors.forEvery
import org.scalatest.Matchers
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.HavePropertyMatchResult
import org.scalatest.matchers.HavePropertyMatcher
import org.scalatest.prop.Checkers
import org.scalatest.prop.GeneratorDrivenPropertyChecks

@RunWith(classOf[JUnitRunner])
class TestSubsets extends FunSuite with Checkers with Matchers with GeneratorDrivenPropertyChecks {

  def bitcount(expected: Int) =
    new HavePropertyMatcher[Long, Int] {
      def apply(subset: Long) = {
        val actual = java.lang.Long.bitCount(subset)
        HavePropertyMatchResult(
          actual == expected,
          "title",
          expected,
          actual)
      }
    }

  val cardinality = Gen.choose(0, java.lang.Long.SIZE)

  test("0-size subsets") {
    forAll(cardinality) { n =>
      // create() returns an iterator only good once.
      val subsets = Subsets.create(n, 0).toArray
      subsets should have size (1)
      subsets(0) should have(bitcount(0))
    }
  }

  test("Subsets of the empty set") {
    forAll(cardinality) { k =>
      if (k > 0) {
        intercept[IllegalArgumentException] {
          Subsets.create(0, k).toArray
        }
      } else {
        val subsets = Subsets.create(0, k)
        subsets should have size(1)
      }
    }
  }

  test("k = 1") {
    forAll(cardinality) { n =>
      val subsets = Subsets.create(n, 1).toList
      subsets should have size (n)
      forEvery(subsets) { l =>
        l should have(bitcount(1))
      }
    }
  }

  test("Vary n, k") {
    forAll(cardinality, cardinality) { (n, k) =>
      (k < n) ==> {
        val choose = CombinatoricsUtils.binomialCoefficient(n, k)
        val subsets = Subsets.create(n, k).toList
        subsets.size === choose
      }
    }
  }
}