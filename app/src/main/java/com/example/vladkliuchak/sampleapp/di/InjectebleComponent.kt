package com.example.vladkliuchak.sampleapp.di

/**
 * Created by vladkliuchak on 06.02.18.
 */
interface InjectableComponent<Into> {
    fun inject(into: Into)
}