package com.neuymin.currencies.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ValCurs",strict = false)
data class ValCurs(
    @field:Attribute(name = "ID")
    var id: String = "",
    @field:Attribute(name = "DateRange1")
    var daterange1: String = "",
    @field:Attribute(name = "DateRange2")
    var daterange2: String = "",
    @field:Attribute(name = "name")
    var name: String = "",
    @field:ElementList(name = "Record",inline = true)
    var record: List<Record> = ArrayList()
)
