package com.wiyonoaten.composechallenge.wk1puppyadoptionapp.utils

import org.threeten.bp.OffsetDateTime
import org.threeten.bp.temporal.ChronoUnit

fun currentAge(birthdate: OffsetDateTime) = ChronoUnit.YEARS.between(birthdate, OffsetDateTime.now())
