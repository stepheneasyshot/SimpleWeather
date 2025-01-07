package com.stephen.simpleweather.network

import com.stephen.simpleweather.data.choosedCityList
import com.stephen.simpleweather.model.CityResponse
import com.stephen.simpleweather.model.WeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlin.random.Random

class NetworkRepository(private val httpClient: HttpClient) {

    companion object {
        private const val IS_DEBUG_MODE = true
        private const val BASE_URL = "http://apis.juhe.cn/"
        private const val MY_AUTH_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXX"
        private const val WEATHER = "simpleWeather/query"
        private const val CITY_LIST = "simpleWeather/cityList"
    }

    fun getWeatherDataList() = toResultFlow {
        val resultList = mutableListOf<WeatherResponse>()
        choosedCityList.forEach { city ->
            val response =
                if (IS_DEBUG_MODE) {
                    fakecity = city
                    Json.decodeFromJsonElement<WeatherResponse>(
                        Json.parseToJsonElement(getMockWeatherResult())
                    )
                } else
                    httpClient.get(BASE_URL + WEATHER) {
                        url {
                            parameters.append("city", city)
                            parameters.append("key", MY_AUTH_KEY)
                        }
                    }.body<WeatherResponse>()
            resultList.add(response)
        }
        NetWorkResult.Success(resultList)
    }

    /**
     * 获取支持的城市列表
     */
    fun getSupportedCityList() = toResultFlow {
        val response =
            if (IS_DEBUG_MODE) {
                Json.decodeFromJsonElement<CityResponse>(
                    Json.parseToJsonElement(
                        DEBUG_CITY_LIST
                    )
                )
            } else
                httpClient.get(BASE_URL + CITY_LIST) {
                    url {
                        parameters.append("key", MY_AUTH_KEY)
                    }
                }.body<CityResponse>()
        NetWorkResult.Success(response)
    }
}

const val DEBUG_CITY_LIST =
    "{\"reason\":\"查询成功\",\"result\":[{\"id\":\"1\",\"province\":\"北京\",\"city\":\"北京\",\"district\":\"北京\"},{\"id\":\"2\",\"province\":\"北京\",\"city\":\"北京\",\"district\":\"海淀\"},{\"id\":\"3\",\"province\":\"北京\",\"city\":\"北京\",\"district\":\"朝阳\"},{\"id\":\"4\",\"province\":\"北京\",\"city\":\"北京\",\"district\":\"顺义\"}],\"error_code\":0}"


var i = 0
val randonWeatherInfo = listOf("晴天", "雨天", "雪天", "大风", "雾天", "多云", "阴天")
var fakecity = ""
fun getMockWeatherResult() =
    " {\"reason\":\"查询成功!\",\"result\":{\"city\":\"${fakecity}\",\"realtime\":{\"temperature\":\"${
        Random.nextInt(
            16,
            40
        )
    }\",\"humidity\":\"74\",\"info\":\"${
        randonWeatherInfo[(i++).coerceAtMost(
            randonWeatherInfo.size - 1
        ).apply {
            if (i == randonWeatherInfo.size - 1) i = 0
        }]
    }\",\"wid\":\"02\",\"direct\":\"北风\",\"power\":\"4级\",\"aqi\":\"36\"},\"future\":[{\"date\":\"2024-09-11\",\"temperature\":\"23\\/33℃\",\"weather\":\"多云转阵雨\",\"wid\":{\"day\":\"01\",\"night\":\"03\"},\"direct\":\"北风转持续无风向\"},{\"date\":\"2024-09-12\",\"temperature\":\"23\\/27℃\",\"weather\":\"阴\",\"wid\":{\"day\":\"02\",\"night\":\"02\"},\"direct\":\"西南风\"},{\"date\":\"2024-09-13\",\"temperature\":\"23\\/30℃\",\"weather\":\"阴转多云\",\"wid\":{\"day\":\"02\",\"night\":\"01\"},\"direct\":\"西南风转持续无风向\"},{\"date\":\"2024-09-14\",\"temperature\":\"25\\/32℃\",\"weather\":\"多云转晴\",\"wid\":{\"day\":\"01\",\"night\":\"00\"},\"direct\":\"东北风\"},{\"date\":\"2024-09-15\",\"temperature\":\"26\\/34℃\",\"weather\":\"晴\",\"wid\":{\"day\":\"00\",\"night\":\"00\"},\"direct\":\"东北风\"}]}}"
