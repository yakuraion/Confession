package pro.yakuraion.confession.data.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy")

fun dateToStr(date: LocalDate): String = date.format(formatter)

fun strToDate(str: String): LocalDate = LocalDate.parse(str, formatter)
