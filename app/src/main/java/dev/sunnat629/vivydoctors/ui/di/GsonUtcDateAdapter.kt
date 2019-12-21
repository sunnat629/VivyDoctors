package dev.sunnat629.vivydoctors.ui.di

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.lang.reflect.Type
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class GsonUTCDateAdapter : JsonDeserializer<DateTime> {

    companion object{
        const val DATE_FORMAT_UTC = "'D'd'T'hh:mm/'D'd'T'hh:mm"

        const val PATTERN_YYYY_MM_DD = "hh:mm"
    }

    private val dateFormat: DateFormat

    init {
        dateFormat = SimpleDateFormat(DATE_FORMAT_UTC, Locale.getDefault())
    }

    @Synchronized
    override fun deserialize(
        jsonElement: JsonElement,
        type: Type,
        jsonDeserializationContext: JsonDeserializationContext
    ): DateTime {
        try {
            val formatter = DateTimeFormat.forPattern(PATTERN_YYYY_MM_DD)
            return formatter.parseDateTime(jsonElement.asString)
        } catch (e: ParseException) {
            throw JsonParseException(e)
        }
    }
}