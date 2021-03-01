package com.wiyonoaten.composechallenge.wk1puppyadoptionapp.models

import org.threeten.bp.OffsetDateTime

data class Puppy(
    val id: String,
    val name: String,
    val breed: String,
    val birthdate: OffsetDateTime,
    val photoUrl: String? = null,
    val description: String? = null
)
