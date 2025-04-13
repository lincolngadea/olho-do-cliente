package br.com.aponteaqui.utils

import org.mockito.Mockito

@Suppress("UNCHECKED_CAST")
fun <T> any(): T = Mockito.any<T>() ?: null as T