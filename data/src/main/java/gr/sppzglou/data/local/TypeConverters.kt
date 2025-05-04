package gr.sppzglou.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import gr.sppzglou.data.local.entities.LicenseEntity
import gr.sppzglou.data.local.entities.MeaningEntity
import gr.sppzglou.data.local.entities.PhoneticEntity

class Converters {
    private val gson = Gson()

    // --- PhoneticEntity List ---
    @TypeConverter
    fun fromPhoneticList(value: List<PhoneticEntity>): String =
        gson.toJson(value)

    @TypeConverter
    fun toPhoneticList(value: String): List<PhoneticEntity> {
        val type = object : TypeToken<List<PhoneticEntity>>() {}.type
        return gson.fromJson(value, type)
    }

    // --- MeaningEntity List ---
    @TypeConverter
    fun fromMeaningList(value: List<MeaningEntity>): String =
        gson.toJson(value)

    @TypeConverter
    fun toMeaningList(value: String): List<MeaningEntity> {
        val type = object : TypeToken<List<MeaningEntity>>() {}.type
        return gson.fromJson(value, type)
    }

    // --- String List (sourceUrls, synonyms, antonyms) ---
    @TypeConverter
    fun fromStringList(value: List<String>): String =
        gson.toJson(value)

    @TypeConverter
    fun toStringList(value: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }

    // --- LicenseEntity ---
    @TypeConverter
    fun fromLicense(value: LicenseEntity?): String? =
        value?.let { gson.toJson(it) }

    @TypeConverter
    fun toLicense(value: String?): LicenseEntity? =
        value?.let {
            val type = object : TypeToken<LicenseEntity>() {}.type
            gson.fromJson(it, type)
        }
}

