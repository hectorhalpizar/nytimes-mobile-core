package me.hectorhalpizar.core.nytimes.usecase

/**
 * Every use case should implement this interface
 */
interface BaseUseCase<Input, Output> {
    operator fun invoke(input: Input) : Output
}