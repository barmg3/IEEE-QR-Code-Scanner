package com.nour.ieeeevent.ui.home

import com.nour.ieeeevent.data.modeles.AttenderEntity

data class HomeUiState(
    val attender : AttenderEntity? = null,
    val errorMessage: String? = null,
)
