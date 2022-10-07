package com.neuymin.currencies.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "Record", strict = false)
data class Record(
    @field:Attribute(name = "Date")
    var date: String = "",
    @field:Attribute(name = "Id")
    var id: String = "",
    @field:Element(name = "Nominal")
    var nominal: String = "",
    @field:Element(name = "Value")
    var value: String = ""
)

