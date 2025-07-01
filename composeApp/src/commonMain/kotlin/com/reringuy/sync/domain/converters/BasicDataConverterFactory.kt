package com.reringuy.sync.domain.converters

import com.reringuy.sync.utils.ApiResponse
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.Converter
import de.jensklingenberg.ktorfit.converter.KtorfitResult
import de.jensklingenberg.ktorfit.converter.TypeData
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

class ApiResponseConverterFactory : Converter.Factory {
    override fun suspendResponseConverter(
        typeData: TypeData,
        ktorfit: Ktorfit,
    ): Converter.SuspendResponseConverter<HttpResponse, *>? {
        val outerType = typeData.typeInfo.type
        val typeArgs = typeData.typeArgs

        if (outerType == ApiResponse::class && typeArgs.isNotEmpty()) {
            val innerTypeInfo = typeArgs.first().typeInfo
            return object : Converter.SuspendResponseConverter<HttpResponse, ApiResponse<Any?>> {
                override suspend fun convert(result: KtorfitResult): ApiResponse<Any?> {
                    return when (result) {
                        is KtorfitResult.Failure -> {
                            ApiResponse.error(result.throwable)
                        }

                        is KtorfitResult.Success -> {
                            val body: Any = result.response.body(typeInfo = innerTypeInfo)
                            ApiResponse.success(body)
                        }
                    }
                }
            }
        }
        return null
    }
}

