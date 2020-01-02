package dev.sunnat629.vivydoctors

import android.content.res.Resources
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.test.annotation.UiThreadTest
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.sunnat629.vivydoctors.ui.main.MainActivity
import dev.sunnat629.vivydoctors.ui.utils.DSConstants.INITIAL_LOAD_SIZE
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class AppInstrumentedTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    private lateinit var activity: MainActivity
    private lateinit var resources: Resources
    private lateinit var bottomNavigation: BottomNavigationView


    @Before
    fun setup() {
        activity = activityTestRule.activity
        resources = activity.resources
        bottomNavigation = activity.findViewById(R.id.bottomNavigation)
        activityTestRule.activity.supportFragmentManager.beginTransaction()
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("dev.sunnat629.vivydoctors", appContext.packageName)
    }

    @Test
    @SmallTest
    fun navigationNameTest() {
        assertEquals(bottomNavigation.menu[0].title, resources.getString(R.string.nav_all_doctor))
        assertEquals(
            bottomNavigation.menu[1].title,
            resources.getString(R.string.nav_recent_doctors)
        )
    }

    @Test
    @SmallTest
    fun doctorRecyclerViewTest() {
        onView(withId(R.id.doctorRecyclerView)).check(matches(isDisplayed()))
        Thread.sleep(WAITING_LONG_TIME) // waiting a while to load the view
        onView(withId(R.id.doctorRecyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        Thread.sleep(WAITING_SHORT_TIME) // waiting a while to see the fragment
        onView(withId(R.id.doctorImage)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun appFlowTest() {
        val doctorRecyclerView =
            activityTestRule.activity.findViewById(R.id.doctorRecyclerView) as RecyclerView?

        Thread.sleep(WAITING_LONG_TIME) // waiting a while to load the view
        // navigate to recent fragment and check empty text
        onView(withId(R.id.recentDoctorsFragment))
            .perform(click())
        onView(withId(R.id.emptyRecentDr)).check(matches(isDisplayed()))

        // back to the doctor list fragment
        pressBack()

        // navigate back to doctor list fragment and click the 1st one after loading the
        onView(withId(R.id.doctorRecyclerView)).check(matches(isDisplayed()))

        // checking is the initial load size of the list is equal or not
        assertEquals(INITIAL_LOAD_SIZE, doctorRecyclerView?.adapter?.itemCount)

        // click the 1st doctor
        doctorRecyclerView?.adapter?.itemCount?.let {
            if (it > 0)
                onView(withId(R.id.doctorRecyclerView))
                    .perform(
                        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                            0,
                            click()
                        )
                    )
        }

        Thread.sleep(WAITING_SHORT_TIME) // waiting a while to see the fragment

        // checking the doctor image is showing or not
        onView(withId(R.id.doctorImage)).check(matches(isDisplayed()))

        // navigate to recent fragment and check empty text
        onView(withId(R.id.recentDoctorsFragment))
            .perform(click())

        // checking the recent doctor list showing or not
        onView(withId(R.id.recentDoctorRecyclerView)).check(matches(isDisplayed()))
        val recentDoctorRecyclerView =
            activityTestRule.activity.findViewById(R.id.recentDoctorRecyclerView) as RecyclerView?

        // checking the recent doctor list size
        assertEquals(1, recentDoctorRecyclerView?.adapter?.itemCount)
    }

    @Test
    @SmallTest
    fun searchDoctorTest() {
        onView(withId(R.id.doctorRecyclerView)).check(matches(isDisplayed()))
        Thread.sleep(WAITING_LONG_TIME) // waiting a while to load the view

        // navigate to recent fragment and check empty text
        onView(withId(R.id.searchDoctors))
            .perform(click())

        onView(withId(R.id.doctorSearchEditText)).perform(clearText(),typeText("ho"))
        val searchRecyclerView =
            activityTestRule.activity.findViewById(R.id.searchDoctorRecyclerView) as RecyclerView?
        Thread.sleep(WAITING_SHORT_TIME) // waiting a while to see the fragment
        assertEquals(2, searchRecyclerView?.adapter?.itemCount)
    }

    @UiThreadTest
    @Test
    @SmallTest
    fun addItemsMenuToBottomNavigationTest() {
        assertEquals(2, bottomNavigation.menu.size()) //  by default it has two menu items
        bottomNavigation.menu.add("Item1")
        bottomNavigation.menu.add("Item2")
        bottomNavigation.menu.add("Item3")
        assertEquals(5, bottomNavigation.menu.size())
        bottomNavigation.menu.removeItem(0)
        bottomNavigation.menu.removeItem(0)
        assertEquals(3, bottomNavigation.menu.size())
    }
    
    companion object {
        const val WAITING_LONG_TIME = 7_000L // Can be decreased or increased the time based internet speed
        const val WAITING_SHORT_TIME = 1000L
    }
}