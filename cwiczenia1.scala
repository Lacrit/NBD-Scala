import scala.annotation.tailrec
import scala.math.abs

object Main extends App {
   val list = List("poniedziałek", "wtorek", "środa", "czwartek", "piątek", "sobota", "niedziela")
   val listWithZeros = List(1, 2, 0, 3, 4, 0, 13, 0, 5, 6, 0, 0, -6, 0, -3, 0, 7, -1)
   val defOp = ", "
   val defFilterBy = "p"
   val defDiscountPerc = 10
   println( "task 1.a: " + joinListWithFor(list) )
   println( "task 1.b: " + joinListWithFor(filter(list)) )
   println( "task 1.c: " + joinListWithFor(list))
   println( "task 2.a: " + joinListRec(list.tail, list.head))
   println( "task 2.b: " + joinListReverseRec(list))
   println( "task 3: " + joinListTailRec(list))
   println( "task 4.a: " + joinListFoldl(list))
   println( "task 4.b: " + joinListFoldr(list))
   println( "task 4.с: " + joinListFoldl(filter(list)))
  
   val products: Map[String, Double] = Map("Dixit" -> 10.0, "MTG" -> 126634.0, "DnD" -> 21000.0)
  
   println( "task 5:  " + discount(products))
   println( "task 6: " + tupleToString("String", 1, true))
   println( "task 7: " + getPriceOfProduct(products, "Dobble"))
   println( "task 8: " + filterNotRec(listWithZeros))
   println( "task 9: " + addValue(filterNotRec(listWithZeros)))
    println("10: " + filterByInterval(filterNotRec(listWithZeros).map(_.toDouble)))
  
  // task 1.a
  def joinListWithFor(list:List[String], op:String = defOp) : String = {
      var res:String = ""
      for (el <- list.dropRight(1)) res += el + op
      res + list.last
  }
  // task 1.c
  def joinListWithWhile(list:List[String], op:String = defOp) : String = {
      var res:String = ""
      var i = 0
      val listWithoutLast = list.dropRight(1)
      while (i < listWithoutLast.size) {
        res += list(i) + op
        i += 1
      }
      res + list.last
  }
  
  // task 2.a 
  def joinListRec(list:List[String], accum:String, op:String = defOp) : String = {
       if (list.isEmpty) accum
       else joinListRec(list.tail, accum + op + list.head )
  }
  
  // task 2.b
  def joinListReverseRec(list:List[String], op:String = defOp) : String = {
    @tailrec
    def iter(curr:List[String], accum:String) : String = {
       if (curr.isEmpty) accum
       else iter(curr.tail, curr.head + op + accum )
    }
    iter(list.tail, list.head)
  }
  
  // task 3 
  def joinListTailRec(list:List[String], op:String = defOp) : String = {
    @tailrec
    def iter(curr:List[String], accum:String) : String = {
       if (curr.isEmpty) accum
       else iter(curr.tail, accum + op + curr.head )
    }
    iter(list.tail, list.head)
  }
  
  // task 4.1
  def joinListFoldl(list:List[String], op:String = defOp) : String = list.tail.foldLeft(list.head) (_+op+ _) 
  
  // task 4.2
  def joinListFoldr(list:List[String], op:String = defOp) : String = list.dropRight(1).foldRight(list.last) (_+op+ _) 
  
  // task 5
  def discount(map:Map[String, Double], discountPerc:Double = defDiscountPerc) : Map[String, Double] = map.view.mapValues(_*(1 - discountPerc/100)).toMap
  
  // taks 6
  def tupleToString(tuple:(String, Int, Boolean)): String = s"${tuple._1} ${tuple._2} ${tuple._3}"
  
  // task 7
  def getPriceOfProduct(productMap:Map[String, Double], productName:String) : Option[Double] = productMap.get(productName)
  
  // task 8 just to try in a more functional/scala way (?). At least they say so 
  def filterNotRec(list:List[Int], filterBy:Int = 0): List[Int] = list match {
        case Nil => Nil
        case h :: tail =>
          if (h == filterBy) filterNotRec(tail, filterBy) 
          else h :: filterNotRec(tail, filterBy)
  }
  
  // task 9 
  def addValue(list: List[Int], value:Int = 1): List[Int] = list.map(_+value)
  
  // task 10 
  def filterByInterval(list: List[Double], interval:(Int, Int) = (-5, 12)): List[Double] = {
    list.filter(el => el > interval._1 && el < interval._2).map(abs(_))
  }
  
  // task 1.b and 4 helper
  def filter(list:List[String], filterBy:String = defFilterBy)  = list.filter(_.startsWith(filterBy))

  
}
