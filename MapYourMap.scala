/**
  * Tell developer names by the department code
  * Expected result:
  * Map(frontend -> List(Remy, Alexandre), analytics -> List(Pierre), api -> List(Noe))
  */
object MapYourMap {

  val devNames = Map("dev1" -> "Pierre", "dev2" -> "Remy", "dev3" -> "Noe", "dev4" -> "Alexandre")
  val devDepartments = Map("dev1" -> "analytics", "dev2" -> "frontend", "dev3" -> "api", "dev4" -> "frontend")

  val namesInDepartments:Map[String, List[String]] = {
    var mapSorted:Map[String,List[String]] = Map()
    for ((k_devDepart,v_devDepart) <- devDepartments){
        if (mapSorted.contains(v_devDepart)){
          var tmp = mapSorted(v_devDepart)
          tmp = devNames(k_devDepart) +: tmp
          mapSorted += (v_devDepart-> tmp)
        }
      else {
          val tmp = List(devNames(k_devDepart))
          mapSorted += (v_devDepart -> tmp)
        }
    }
    mapSorted
  }

  def main(args: Array[String]): Unit = {
    println(namesInDepartments)
  }
}
