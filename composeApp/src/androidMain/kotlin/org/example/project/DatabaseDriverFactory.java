package org.example.project;

actual class DatabaseDriverFactory {

    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
                schema = AppDatabase.Schema,
                context = context,
                name = "AppDatabase.db"
        )
    }
}