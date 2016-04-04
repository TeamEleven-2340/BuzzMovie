package edu.gaTech.teamElevenProject;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import java.util.ArrayList;
/**
 * Created by Daniel on 4/1/2016.
 */
public class UserManagerTest extends AndroidTestCase {
    private DatabaseWrapper db;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        db = new DatabaseWrapper(context, DatabaseWrapper.DATABASENAME);
    }
    @Override
    public void tearDown() throws Exception {
        db.close();
        super.tearDown();
    }

    public void testFindUserById() throws Exception {
        setUp();
        String name = "Name";
        String noName = "noName";
        boolean noExist = true;
        SQLiteDatabase rdb = db.getReadableDatabase();
        UserManager userManager = new UserManager(db, rdb);
        userManager.addUser("Name", "pass");
        ArrayList<User> userList = (ArrayList<User>) userManager.getUserList();
        for (int i = 0; i < userList.size(); i++) {
            if (userManager.getUserList().get(i).getName().equals(noName)) {
                System.out.println(noName + " exists!");
                assertEquals(userManager.findUserById(noName).getName(), noName);
                noExist = false;
            }
        }
        if (noExist) {
            assertNull(userManager.findUserById(noName));
            System.out.println("No user named: " + name);
        }
    }
}