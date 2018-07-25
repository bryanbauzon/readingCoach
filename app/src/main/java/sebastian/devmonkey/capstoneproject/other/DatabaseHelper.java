package sebastian.devmonkey.capstoneproject.other;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "capstone.db";
    private static final String DB_TABLE = "tbl_journal";

    //columns

    private static final String ID = "ID";
    private static final String TITLE = "TITLE";
    private static final String CONTENT = "CONTENT";


    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,TITLE TEXT, CONTENT TEXT)";



    public DatabaseHelper (Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);

        onCreate(sqLiteDatabase);
    }


    //create method to insert data
    public boolean insertData(String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, title);
        contentValues.put(CONTENT, content);

        long result = db.insert(DB_TABLE, null, contentValues);

        return result != -1; //if result = -1 data doesn't insert
    }


    //create method to view data
    public Cursor viewData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT *  FROM " + DB_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        return cursor;

    }



}
