package stereotypes

import scalaz._, Scalaz._

case class Stereotype(name: String, description: String, aliases: List[String])

object Stereotype {
  implicit val stereotypeEqual: Equal[Stereotype] = Equal.equalA
  implicit val stereotypeShow: Show[Stereotype] = Show.showA
}

trait Stereotypes {
  def find(term: String): Validation[String, Stereotype]
  val stereotypes: List[Stereotype]

  protected def find(p: String => Boolean)(term: String) =
    stereotypes.find(s => p(s.name) || p(s.description) || s.aliases.exists(p)).toSuccess(message(term))

  private def message(term: String) = s"Invalid Stereotype [$term]. Must be one of [$allowedTerms]"
  private lazy val allowedTerms = stereotypes.flatMap(s => s.name :: s.description :: s.aliases).sorted.mkString(", ")
}
