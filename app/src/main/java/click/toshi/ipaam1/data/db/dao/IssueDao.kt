package click.toshi.ipaam1.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import android.support.annotation.CheckResult
import click.toshi.ipaam1.data.db.entity.IssueEntity

import io.reactivex.Flowable

@Dao abstract class IssueDao {
    @CheckResult
    @Query("SELECT * FROM issue")
    abstract fun getAllIssue(): Flowable<List<IssueEntity>>

    @Query("DELETE FROM issue")
    abstract fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(issues: List<IssueEntity>)

    @Transaction open fun clearAndInsert(newIssues: List<IssueEntity>) {
        deleteAll()
        insert(newIssues)
    }
}
