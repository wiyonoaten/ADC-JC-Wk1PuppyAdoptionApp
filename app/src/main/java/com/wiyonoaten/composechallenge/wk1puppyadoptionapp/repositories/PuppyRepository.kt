package com.wiyonoaten.composechallenge.wk1puppyadoptionapp.repositories

import com.wiyonoaten.composechallenge.wk1puppyadoptionapp.models.Puppy
import org.threeten.bp.OffsetDateTime
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

private val FIXED_LIST = listOf(
    Puppy("1", "Shinu", "Shiba Inu", OffsetDateTime.parse("2009-01-09T00:00+00:00"), "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/Taka_Shiba.jpg/440px-Taka_Shiba.jpg"),
    Puppy("2", "Sheppy", "German Shepherd", OffsetDateTime.parse("2016-01-01T00:00+00:00"), "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/German_Shepherd_-_DSC_0346_%2810096362833%29.jpg/440px-German_Shepherd_-_DSC_0346_%2810096362833%29.jpg"),
    Puppy("3", "Poodie", "Poodle", OffsetDateTime.parse("2015-12-23T00:00+00:00"), "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f8/Full_attention_%288067543690%29.jpg/440px-Full_attention_%288067543690%29.jpg"),
    Puppy("4", "Hus-hus", "Siberian Husky", OffsetDateTime.parse("2007-08-08T00:00+00:00"), "https://upload.wikimedia.org/wikipedia/commons/a/a3/Black-Magic-Big-Boy.jpg"),
    Puppy("5", "Bul-bul", "Bulldog", OffsetDateTime.parse("2018-05-23T00:00+00:00"), "https://upload.wikimedia.org/wikipedia/commons/thumb/1/11/Bulldog_adult_male.jpg/440px-Bulldog_adult_male.jpg"),
    Puppy("6", "Chi-chi", "Chihuahua", OffsetDateTime.parse("2019-03-03T00:00+00:00"), "https://upload.wikimedia.org/wikipedia/commons/4/4c/Chihuahua1_bvdb.jpg"),
    Puppy("7", "Pom-pom", "Pomeranian", OffsetDateTime.parse("2006-05-11T00:00+00:00"), "https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Pomeranian.JPG/440px-Pomeranian.JPG"),
    Puppy("8", "Goldie", "Golden Retriever", OffsetDateTime.parse("2012-10-06T00:00+00:00"), "https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/Golden_Retriever_Carlos_%2810581910556%29.jpg/440px-Golden_Retriever_Carlos_%2810581910556%29.jpg"),
    Puppy("9", "Dalmie", "Dalmation", OffsetDateTime.parse("2015-01-10T00:00+00:00"), "https://vetstreet-brightspot.s3.amazonaws.com/ee/140380a73111e0a0d50050568d634f/file/Dalmatian-2-645mk062311.jpg"),
    Puppy("10", "Dobbie", "Dobermann", OffsetDateTime.parse("2012-11-07T00:00+00:00"), "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/Dobermann_handling.jpg/440px-Dobermann_handling.jpg"),
)

class PuppyRepository {

    suspend fun loadPuppyList(): List<Puppy> = suspendCoroutine {
        it.resume(FIXED_LIST)
    }

    suspend fun loadPuppyDetails(id: String): Puppy = suspendCoroutine { continuation ->
        FIXED_LIST.find { it.id == id }?.let {
            continuation.resume(it)
        } ?: continuation.resumeWithException(Exception("Something wrong. Puppy with requested id does not exist!"))
    }
}
