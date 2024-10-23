package org.example.project;

actual class DatabaseDriverFactory {

    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
                schema = AppDatabase.Schema,
                name = "AppDatabase.db"
        )
    }
}