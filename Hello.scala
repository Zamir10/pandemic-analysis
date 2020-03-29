package cognitive

import java.text.SimpleDateFormat
import java.util.Date

import scala.util.matching.Regex

case class Countries(
                  date: Date,
                  country: String,
                  confirmed: Int,
                  death: Int
                )

object Hello {
  def main(args: Array[String]): Unit = {
    val source = scala.io.Source.fromFile("countries_aggregated.csv")
    val lines = source.getLines().drop(1)
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
    var pattern: Regex = ",(?!(?:[^\"]*\"[^\"]*\")*[^\"]*$)".r
    val data = lines.map { line =>
      val p = pattern.replaceAllIn(line, "").split(",")
      Countries(
       dateFormat.parse(p(0)),
        p(1).toString,
        p(2).toInt,
        p(3).toInt
      )
    }.toArray
    source.close()

    for (country <- data) println(country)
  }
}
