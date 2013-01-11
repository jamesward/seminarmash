package models

import org.squeryl.{Schema, KeyedEntity}

case class Person(name: String) extends KeyedEntity[Long] {
  val id: Long = 0
}

object AppDB extends Schema {
  val personTable = table[Person]("person")
}
