// task 1
class Container[A](private val value: A) {
  def getContent(): A = {
    return value
  }

  def apply[R](function: A => R): R = {
    function.apply(value)
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    println("> Task 1")

    def reverse(str: String): String = str.reverse

    val c1 = new Container("test")
    println(c1.getContent())
    println(c1.apply(reverse))

    // task 2 & 3
    println("> Task 2")
    trait Maybe[+A] {
      def applyFunction[R](function: A => R): Maybe[R]

      def flatMap[R](function: A => Maybe[R]): Maybe[R]

      def getOrElse[R >: A](default: R): R
    }

    class Yes[A](private val value: A) extends Maybe[A] {
      override def applyFunction[R](function: A => R): Maybe[R] = new Yes(function(value))

      override def flatMap[R](function: A => Maybe[R]): Maybe[R] = function(value)

      override def getOrElse[R >: A](default: R): R = value
    }

    object No extends Maybe[Nothing] {
      override def applyFunction[R](function: Nothing => R): Maybe[R] = this

      override def flatMap[R](function: Nothing => Maybe[R]): Maybe[R] = this

      override def getOrElse[R >: Nothing](default: R): R = default
    }

    val noObject = No
    val yes1Object = new Yes("test2")
    val yes2Object = new Yes(123)

    println(noObject.isInstanceOf[Maybe[Nothing]])
    println(yes1Object.isInstanceOf[Maybe[String]])
    println(yes2Object.isInstanceOf[Maybe[Int]])
    println(noObject.isInstanceOf[Maybe[_]])
    println(yes1Object.isInstanceOf[Maybe[_]])
    println(yes2Object.isInstanceOf[Maybe[_]])

    // task 3 & 4
    println("> Tasks 3 & 4")

    def addDashes(str: String): String = {
      var ret = ""
      for (letter <- str.toCharArray) {
        ret += "letter" + "-"
      }
      ret
    }

    println(yes1Object.getOrElse("yes"))
    println(yes2Object.getOrElse(0))
    println(noObject.getOrElse("no"))

    val r1 = yes1Object.applyFunction(addDashes)
    val r2 = noObject.applyFunction(addDashes)
    println(r1.getOrElse("no value 1"))
    println(r2.getOrElse[String]("no value 2"))

    val r3 = r1.asInstanceOf[Maybe[String]]
    println(r3.getOrElse("no value 3"))

    // nbd10 task 2
    def doSth(test: Maybe[String]): Maybe[String] = {
      test.applyFunction(addDashes)
    }

    println("> Task 2")
    val o1 = new Yes("test")
    val o2 = new Yes(o1)

    val res1 = o2.applyFunction(doSth)
    assert(res1.getClass.equals(o1.getClass))
    assert(res1.getOrElse("no value").getClass.equals(o1.getClass))
    assert(res1.getOrElse("no value").asInstanceOf[Maybe[String]].getOrElse("no value").getClass
      .equals("".getClass))

    val res2 = o2.flatMap(doSth)
    assert(res2.getClass.equals(o2.getClass))
    assert(res2.getOrElse("no value").getClass.equals("".getClass))
  }
}
