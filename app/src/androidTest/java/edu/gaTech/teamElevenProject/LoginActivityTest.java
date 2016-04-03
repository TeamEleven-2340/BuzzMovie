package edu.gaTech.teamElevenProject;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.view.View;
import java.util.ArrayList;

/**
 * Created by Juliet on 04/03/16.
 */
public class LoginActivityTest extends AndroidTestCase {

    private DatabaseWrapper db;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        db = new DatabaseWrapper(context, DatabaseWrapper.DATABASE_NAME);
    }
    @Override
    public void tearDown() throws Exception {
        db.close();
        super.tearDown();
    }

    public void testLoginButtonClicked(View v) throws Exception {
        //TODO
    }

}


