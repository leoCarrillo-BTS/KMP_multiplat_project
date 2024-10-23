package org.example.project.di

import io.ktor.client.HttpClient
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import org.example.project.data.ExpenseManager
import org.example.project.data.ExpenseRepoImpl
import org.example.project.domain.ExpenseRepository
import org.example.project.presentation.ExpensesViewModel
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

fun appModule(appDatabase: AppDatabase) = module {
    single<HttpClient> { HttpClient{ install(ContentNegotiation) { json() } } }
    single<ExpenseRepository> { ExpenseRepoImpl(appDatabase, get()) }
    factory { ExpensesViewModel(get()) }
}