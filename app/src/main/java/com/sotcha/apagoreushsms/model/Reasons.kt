package com.sotcha.apagoreushsms.model

import android.content.Context
import com.sotcha.apagoreushsms.R

data class Reason(
    val id: Int,
    val smallDescription: String,
    val description: String
) {

    companion object {
        fun getReasonsList(context: Context): List<Reason> =

            listOf<Reason>(
                Reason(
                    1,
                    context.getString(R.string.reason_1),
                    context.getString(R.string.reason_1_descrption)
                ),
                Reason(
                    2,
                    context.getString(R.string.reason_2),
                    context.getString(R.string.reason_2_descrption)
                ),
                Reason(
                    3,
                    context.getString(R.string.reason_3),
                    context.getString(R.string.reason_3_descrption)
                ),
                Reason(
                    4,
                    context.getString(R.string.reason_4),
                    context.getString(R.string.reason_4_descrption)
                ),
                Reason(
                    5,
                    context.getString(R.string.reason_5),
                    context.getString(R.string.reason_5_descrption)
                ),
                Reason(
                    6,
                    context.getString(R.string.reason_6),
                    context.getString(R.string.reason_6_descrption)
                )

            )

    }
}





