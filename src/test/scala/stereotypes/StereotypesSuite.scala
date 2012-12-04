package stereotypes

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.MustMatchers

@RunWith(classOf[JUnitRunner])
class StereotypesSuite extends FunSuite {
  import scalaz.Validation._

  trait Examples extends Stereotypes with MustMatchers {
    val australia  = Stereotype("australia",  "a person from australia",   List("aussie", "legend"))
    val newZealand = Stereotype("newzealand", "a person from new zealand", List("kiwi"))
    val preston    = Stereotype("preston",    "a person from preston",     List("scally"))
    val liverpool  = Stereotype("liverpool",  "a person from liverpool",   List("scouser", "thief"))
    val manchester = Stereotype("manchester", "a person from manchester",  List("manc", "mancunian", "fighter"))

    val stereotypes = List(australia, newZealand, preston, liverpool, manchester)
    def find(term: String) = find(_ equalsIgnoreCase term)(term)
  }

  test("find by description") {
    new Examples {
      find("a person from new zealand") must be === success(newZealand)
    }
  }

  test("find by name") {
    new Examples {
      find("manchester") must be === success(manchester)
    }
  }

  test("find by alias") {
    new Examples {
      find("scouser") must be === success(liverpool)
    }
  }

  test("find is case insensitive") {
    new Examples {
      val expected = success(preston)
      find("scally") must be === expected
      find("SCALLY") must be === expected
      find("ScAlLy") must be === expected
    }
  }

  test("find returns a descriptive message when no stereotype is found") {
    new Examples {
      val error = "Invalid Stereotype [Awesome]. Must be one of [a person from australia, " +
        "a person from liverpool, a person from manchester, a person from new zealand, " +
        "a person from preston, aussie, australia, fighter, kiwi, legend, liverpool, " +
        "manc, manchester, mancunian, newzealand, preston, scally, scouser, thief]"

      find("Awesome").fold(_ must equal(error), unexpected => fail(s"Invalid Stereotype [Awesome]: $unexpected"))
    }
  }
}
