package com.wiyonoaten.composechallenge.wk1puppyadoptionapp.models

import org.threeten.bp.OffsetDateTime
import org.threeten.bp.temporal.ChronoUnit

data class Puppy(
    val id: String,
    val name: String,
    val breed: String,
    val birthdate: OffsetDateTime,
    val photoUrl: String? = null,
)
