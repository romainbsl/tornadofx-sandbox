package com.example.demo.view

import com.example.demo.model.PersonKotlin
import com.example.demo.model.PersonKotlinModel
import javafx.scene.control.TableView
import javafx.scene.layout.BorderPane
import tornadofx.*

class MainView : View("Person Editor") {
    override val root = BorderPane()

    var tableview: TableView<PersonKotlin> by singleAssign()

    val persons = listOf(
        PersonKotlin("John", "Manager"),
        PersonKotlin("Jay", "Worker bee")).observable()

    val model: PersonKotlinModel by inject()

    init {
        with(root) {
            center {
                tableview = tableview(persons) {
                    column("Name", PersonKotlin::name)
                    column("Title", PersonKotlin::title)

                    bindSelected(model)
                }
            }

            right {
                form {
                    fieldset("Edit person") {
                        field("Name") {
                            textfield(model.name)
                        }
                        field("Title") {
                            textfield(model.title)
                        }
                        button("Save") {
                            enableWhen(model.dirty)
                            action {
                                save()
                            }
                        }
                        button("Reset").action {
                            model.rollback()
                        }
                    }
                }
            }
        }
    }

    private fun save() {
        // Flush changes from the text fields into the model
        model.commit()

        // The edited person is contained in the model
        val person = model.item

        // A real application would persist the person here
        println("Saving ${person.name} / ${person.title}")

        tableview.refresh()
    }

}