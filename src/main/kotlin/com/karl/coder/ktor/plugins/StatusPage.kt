package com.karl.coder.ktor.plugins


import com.karl.coder.ktor.exception.BusinessException
import com.karl.coder.ktor.extensions.exception
import com.karl.coder.ktor.extensions.respondK
import com.mysql.cj.jdbc.exceptions.CommunicationsException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import org.jetbrains.exposed.exceptions.ExposedSQLException

fun Application.configureStatusPage() {
    //配置异常处理
    install(StatusPages) {

        //配置404请求返回结果
        //statusFile(HttpStatusCode.NotFound, filePattern = "404.json")
        status(HttpStatusCode.NotFound) { call, _ ->
            call.respondK {
                status = 404
                message = "request not found"
            }
        }
        status(HttpStatusCode.Unauthorized) { call, _ ->
            call.respondK {
                status = 401
                message = "验证失败，请重新登录"
            }
        }

        status(HttpStatusCode.InternalServerError) { call, s ->
            call.respondK {
                status = 500
                message = s.description
            }
        }

        exception<BusinessException> { call, e ->
            call.respondK {
                status = e.errorCode
                message = e.errorMessage
            }
        }

        exception<ExposedSQLException> { call, cause ->
            call.respondK {
                status = 207
                message = cause.message.exception("未知数据库错误")
            }
        }

        exception<IllegalArgumentException> { call, cause ->
            if (cause.message != null &&
                cause.message!!.contains("can't be stored to database column because exceeds")
            ) {
                call.respondK {
                    status = 207
                    message = "传入值过大导致数据库存储错误"
                }
            } else {
                call.respondK {
                    status = 500
                    message = "未知服务器错误"
                }
            }

        }

        exception<CommunicationsException> { call, _ ->
            call.respondK {
                status = 207
                message = "数据库连接失败"
            }
        }


    }
}