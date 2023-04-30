package com.ingloriousmind.countrieskmm.di

import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.getOriginalKotlinClass
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module


fun initKoinCupertino(
): KoinApplication = initKoin(
    module {
    }
)

actual val platformModule = module {
}

fun Koin.get(objCClass: ObjCClass, qualifier: String?, parameter: Any?): Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz, qualifier?.let { StringQualifier(it) }) { parametersOf(parameter) }
}

fun Koin.get(objCClass: ObjCClass, parameter: Any?): Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz, null) { parametersOf(parameter) }
}

fun Koin.get(objCClass: ObjCClass, qualifier: String?): Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz, qualifier?.let { StringQualifier(it) })
}
