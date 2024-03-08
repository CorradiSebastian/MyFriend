package com.sebastiancorradi.myfriend.db.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.sebastiancorradi.myfriend.data.Cat
import com.sebastiancorradi.myfriend.datasource.repository.db.AppDatabase
import com.sebastiancorradi.myfriend.datasource.repository.db.dao.CatDao
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class CatDAOTest {
    private lateinit var database: AppDatabase
    private lateinit var catDao: CatDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        catDao = database.catDao()
    }
    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun createAllTest() = runTest{

        catDao.createAll(getFakeCats())
        val cats = catDao.getAll(0)
        val expectedCats = getFakeCats().sortedBy { it.internalId }

        Assert.assertEquals(expectedCats.size, cats.size)
        for (i in 0..expectedCats.lastIndex) {
            Assert.assertEquals(expectedCats.get(i), cats.get(i))
        }

    }

    @Test
    fun getCatByIdTest() = runTest{
        val cat = Cat(internalId = 1, id = "2bPYDRuvU70sbgja", tags = listOf("gato", "perritomalvado"), size = 22442, mimetype = "image/jpeg")
        catDao.createAll(listOf(cat))

        val catRetrieved = catDao.getCatById(cat.id)
        Assert.assertEquals(cat, catRetrieved)
    }

    private fun getFakeCats(): List<Cat>{
        return listOf(
            Cat(internalId = 1, id = "2bPYDRuvU70sbgja", tags = listOf("gato", "perritomalvado"), size = 22442, mimetype = "image/jpeg"),
            Cat(internalId = 2, id = "PYDRuvU70sbgja",
                tags = listOf("gato", "perritomalvado"),
                size = 22554,
                mimetype = "",
            )
        ).sortedBy { it.internalId }
    }
}