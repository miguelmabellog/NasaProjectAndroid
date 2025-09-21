package com.migueldev.nasaproject.presentation

import com.migueldev.nasaproject.domain.model.NasaItem
import java.time.LocalDate

data class HomeState(
    val isLoading: Boolean = false,
    val items: List<NasaItem> = emptyList(),
    val errorMessage: String? = null,
    val dateRange: DateRange = DateRange()
) {
    val hasError: Boolean get() = errorMessage != null
    val hasItems: Boolean get() = items.isNotEmpty()
}

data class DateRange(
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null
) {
    val isValid: Boolean
        get() = startDate != null && endDate != null && startDate <= endDate
}



