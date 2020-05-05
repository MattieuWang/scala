import WhatsWrong1.Interface

object WhatsWrong1 {

  trait Interface {
    val city: String
    def support: String = s"Ici c'est $city !"
  }

  case object Supporter extends Interface {
    override val city = "Paris"
  }


  Supporter.city //What does this print ?
  Supporter.support //What does this print and why ? How to fix it ?

  // Supporter.city prints "Paris"
//  but in Supporter.support, $city = null

  /*
  It has a run-time problem. when the trait Interface is interpreted,
  city is initialized as 'null'. So the $city in "val support" is null
  after that city is assigned as "Paris", but it doesn't change the value in "support"

  To fix the problem, we can change the type "val" to "def"

   */


  def main(args: Array[String]): Unit = {
    println(Supporter.city)
    println(Supporter.support)

  }
}
