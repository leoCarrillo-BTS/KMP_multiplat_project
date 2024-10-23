package org.example.project.data

expect class DatabaseDriverFactory {

    fun createDriver(): SqlDriver

}