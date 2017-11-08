package com.example.demo.model

import tornadofx.*

// TODO Does work
data class PersonKotlin(var name: String, var title: String)
// TODO Doesn't work
// data class PersonKotlin(var name: String?, var title: String)

class PersonKotlinModel : ItemViewModel<PersonKotlin>() {
  val name = bind { item?.observable(PersonKotlin::name) }
  val title = bind { item?.observable(PersonKotlin::title) }
}
