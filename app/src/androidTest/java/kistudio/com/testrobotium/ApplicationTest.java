package kistudio.com.testrobotium;

import android.app.Application;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import com.robotium.solo.Solo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */

@RunWith(AndroidJUnit4.class)
public class ApplicationTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);
    private Solo solo;

    @Before
    public void setUp() throws Exception {
        //setUp() is run before a test case is started.
        //This is where the solo object is created.
        solo = new Solo(InstrumentationRegistry.getInstrumentation(),
                activityTestRule.getActivity());
    }

    @Test
    public void startTest(){
        View view = solo.getView(R.id.btnMain);
        if (view!=null) solo.clickOnView(view);
        solo.sleep(10000);
        solo.assertCurrentActivity("Wrong activity",SecondActivity.class);
    }

    @Test
    public void openAndTestMenu(){
        solo.sleep(3000);
        swipeToRight();
        solo.sleep(3000);
    }

    private void swipeToRight() {
        Display display = solo.getCurrentActivity().getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        float xStart = 0 ;
        float xEnd = width / 2;
        solo.drag(xStart, xEnd, height / 2, height / 2, 1);
    }
}