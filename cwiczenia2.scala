object Main {
  def describeTypeOfDay(dayName: String): String = dayName match {
    case "Saturday" | "Sunday" => "weekend"
    case "Monday" | "Tuesday" | "Wednesday" | "Thursday" | "Friday" => "work"
    case _ => "no such day"
  }

  def main(args: Array[String]): Unit = {
    println("Task 2.1")
    println(describeTypeOfDay("Monday"))
    println(describeTypeOfDay("Saturday"))
    println(describeTypeOfDay("Bacon"))

    println("Task 2.2")
    val bank1 = new BankAccount()
    bank1.deposit(5)
    println(bank1.currentBalance)

    val bank2 = new BankAccount(10)
    bank2.withdraw(3.3)
    println(bank2.currentBalance)
    // bank1.currentBalance += 5 // not allowed

    println("Task 2.3")
    val wieslaw = new Person("Wieslaw", "X")
    val grzegorz = new Person("Grzegorz", "Y")
    val andrzej = new Person("Andrzej", "Bush")
    val joe = new Person("Joe", "Doe")
    println(Person.greeting(wieslaw))
    println(Person.greeting(grzegorz))
    println(Person.greeting(andrzej))
    println(Person.greeting(joe))

    println("Task 2.4")
    println(doThreeTimes(5, n => n*n))

    println("Task 2.5")
    val obj1 = new Person2("test", "testing") with Employee {
      override def salary: Double = 5000
    }
    println(obj1.taxToPay)
    val obj2 = new Person2("test", "testing") with Student
    println(obj2.taxToPay)
    val obj3 = new Person2("test", "testing") with Teacher {
      override def salary: Double = 5000
    }
    println(obj3.taxToPay)
    val obj4 = new Person2("test", "testing") with Employee with Student {
      override def salary: Double = 5000
    }
    println(obj4.taxToPay)
    val obj5 = new Person2("test", "testing") with Student with Employee {
      override def salary: Double = 5000
    }
    println(obj5.taxToPay)

  }

  def doThreeTimes(x : Int, f: Int => Int): Int = f(f(f(x)))
}

class BankAccount(initialBalance: Double = 0) {
  private var _currentBalance = initialBalance
  def currentBalance: Double = _currentBalance
  def deposit(amount: Double): Unit = _currentBalance += amount
  def withdraw(amount: Double): Unit = _currentBalance -= amount
}

class Person(var firstName : String, var lastName : String)
object Person {
  def greeting(person : Person) : String = person match {
    case p if p.firstName == "Wieslaw" => "Hello, Wieslaw"
    case p if p.firstName == "Grzegorz" => "Good to see you Grzegorz!"
    case p if p.lastName == "Andrzej" => "Good morning, Andrzej!"
    case p => String.join(" ", p.firstName, p.lastName) + "Who are you?"
  }
}

abstract class Person2(val firstName: String, val lastName : String, val taxToPay : Double = 20)


trait Employee extends Person2 {
  def salary : Double
  override val taxToPay : Double = 0.2 * salary
}

trait Student extends Person2 {
  override val taxToPay : Double = 0
}

trait Teacher extends Employee {
  override val taxToPay : Double = 0.1 * salary
}
