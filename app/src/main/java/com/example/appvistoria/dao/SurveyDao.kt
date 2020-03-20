package com.example.appvistoria.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appvistoria.data.Survey

@Dao
interface SurveyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)

    fun setSurvey(survey: Survey)

    //@Query("SELECT * from survey_table ORDER BY license_plate ASC")
    @Query("SELECT * from survey_table")
    fun getSurveys() : LiveData<List<Survey>>

    @Query("DELETE FROM survey_table")
    fun deleteAll()
}