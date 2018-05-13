package click.toshi.ipaam1.data.db.fixeddata

import click.toshi.ipaam1.R
import click.toshi.ipaam1.data.api.response.mapper.InstantAdapter
import click.toshi.ipaam1.model.Room
import click.toshi.ipaam1.model.Session
import java.util.Date

class SpecialSessions {
    companion object {

        fun getSessions(): List<Session.SpecialSession> {
            var index = 0
            val specialSessionRoom = Room(513, "Hall")
            return listOf(
                    Session.SpecialSession(
                            "100000" + index++,
                            1,
                            Date(
                                    InstantAdapter.parseDateString("2018-02-08T10:00:00")
                                            .toEpochMilli()
                            ),
                            Date(
                                    InstantAdapter.parseDateString("2018-02-08T10:20:00")
                                            .toEpochMilli()
                            ),
                            R.string.session_special_welcome_talk,
                            specialSessionRoom
                    ),
                    Session.SpecialSession(
                            "100000" + index++,
                            1,
                            Date(
                                    InstantAdapter.parseDateString("2018-02-08T11:50:00")
                                            .toEpochMilli()
                            ),
                            Date(
                                    InstantAdapter.parseDateString("2018-02-08T12:50:00")
                                            .toEpochMilli()
                            ),
                            R.string.session_special_lunch,
                            null
                    ),
                    Session.SpecialSession(
                            "100000" + index++,
                            1,
                            Date(
                                    InstantAdapter.parseDateString("2018-02-08T17:40:00")
                                            .toEpochMilli()
                            ),
                            Date(
                                    InstantAdapter.parseDateString("2018-02-08T19:40:00")
                                            .toEpochMilli()
                            ),
                            R.string.session_special_party,
                            specialSessionRoom
                    ),

                    Session.SpecialSession(
                            "100000" + index,
                            2,
                            Date(
                                    InstantAdapter.parseDateString("2018-02-09T11:50:00")
                                            .toEpochMilli()
                            ),
                            Date(
                                    InstantAdapter.parseDateString("2018-02-09T12:50:00")
                                            .toEpochMilli()
                            ),
                            R.string.session_special_lunch,
                            null
                    ))
        }
    }
}
