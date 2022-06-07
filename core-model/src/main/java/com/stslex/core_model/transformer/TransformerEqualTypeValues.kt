package com.stslex.core_model.transformer

import com.stslex.core.Transformer
import com.stslex.core.ValueState

interface TransformerEqualTypeValues<in T, out U> : Transformer.ToUI<T, ValueState<T>, ValueState<U>> {

    class Base<in T, out U : Any>(
        private val transformer: Transformer.ToUI<T, T, ValueState<U>>
    ) : TransformerEqualTypeValues<T, U> {

        override fun transform(firstData: T, secondData: ValueState<T>): ValueState<U> =
            secondData.transform(transformer, firstData)

        override fun transform(exception: Exception): ValueState<U> = ValueState.Failure(exception)

        override fun transform(): ValueState<U> = ValueState.Loading
    }
}