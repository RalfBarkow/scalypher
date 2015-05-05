package com.originate.scalypher.action

import com.originate.scalypher.Query.toQueryWithProperty
import com.originate.scalypher.types.ReferenceableMap
import com.originate.scalypher.types.Referenceable
import com.originate.scalypher.PropertyReference

sealed trait Action {
  def referenceables: Set[Referenceable]
  def toQuery(referenceableMap: ReferenceableMap): String
}

case class Delete(referenceable: Referenceable, property: Option[PropertyReference] = None) extends Action {
  def referenceables: Set[Referenceable] = Set(referenceable)

  def toQuery(referenceableMap: ReferenceableMap): String =
    "DELETE " + toQueryWithProperty(referenceableMap, referenceable, property)
}

case object ReturnAll extends Action {
  def referenceables: Set[Referenceable] = Set()

  def toQuery(referenceableMap: ReferenceableMap): String =
    "RETURN *"
}

case class ReturnReference(referenceable: Referenceable, property: Option[PropertyReference] = None) extends Action {
  def referenceables: Set[Referenceable] =
    Set(referenceable)

  def toQuery(referenceableMap: ReferenceableMap): String =
    "RETURN " + toQueryWithProperty(referenceableMap, referenceable, property)
}

case class ReturnDistinct(referenceable: Referenceable, property: Option[PropertyReference] = None) extends Action {
  def referenceables: Set[Referenceable] =
    Set(referenceable)

  def toQuery(referenceableMap: ReferenceableMap): String =
    "RETURN DISTINCT " + toQueryWithProperty(referenceableMap, referenceable, property)
}
