package pro.yakuraion.confession.data.persistence.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TestEntity(
    @PrimaryKey
    val id: Long,
)
