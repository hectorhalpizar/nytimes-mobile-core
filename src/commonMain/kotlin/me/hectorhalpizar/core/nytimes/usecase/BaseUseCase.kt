package me.hectorhalpizar.core.nytimes.usecase

/**
 * Every use case should implement this interface
 *
 * @param Input The input type required for this [BaseUseCase]
 * @param Output The output type expected for this [BaseUseCase]
 */
interface BaseUseCase<Input, Output> {
    suspend operator fun invoke(input: Input) : Output
}