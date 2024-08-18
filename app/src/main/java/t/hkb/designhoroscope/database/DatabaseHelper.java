package t.hkb.designhoroscope.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import t.hkb.designhoroscope.database.model.FAVTABLE;
import t.hkb.designhoroscope.database.model.Table;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DB_NAME = "Reminder";
    public static final SQLiteDatabase.CursorFactory DB_FACTORY = null;
    public static final int DB_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, DB_FACTORY, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Table.CREATE_TABLE);
//        sqLiteDatabase.execSQL(FAVTABLE.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table.TABLE_NAME);
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FAVTABLE.TABLE_NAME);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public long insertNote(String star, String formatTime) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Table.COLUMN_STAR, star);
        values.put(Table.COLUMN_TIME, formatTime);

        // insert row
        long id = db.insert(Table.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


    public Table getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Table.TABLE_NAME,
                new String[]{Table.COLUMN_ID, Table.COLUMN_STAR, Table.COLUMN_TIMESTAMP},
                Table.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Table table = new Table(
                cursor.getInt(cursor.getColumnIndex(Table.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Table.COLUMN_STAR)),
                cursor.getString(cursor.getColumnIndex(Table.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return table;
    }

    public  List<Table> getAllNotes() {

        List<Table> tables = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Table.TABLE_NAME + " ORDER BY " +
                Table.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Table table = new Table(cursor.getInt(cursor.getColumnIndex(Table.COLUMN_ID)), cursor.getString(cursor.getColumnIndex(Table.COLUMN_STAR)), cursor.getString(cursor.getColumnIndex(Table.COLUMN_TIMESTAMP)));
                table.setId(cursor.getInt(cursor.getColumnIndex(String.valueOf(Table.COLUMN_ID))));
                table.setStar(cursor.getString(cursor.getColumnIndex(Table.COLUMN_STAR)));
                table.setTime(cursor.getString(cursor.getColumnIndex(Table.COLUMN_TIME)));
                table.setTimestamp(cursor.getString(cursor.getColumnIndex(Table.COLUMN_TIMESTAMP)));
                tables.add(table);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return tables;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Table.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

    public int updateNote(Table table) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Table.COLUMN_STAR, table.getStar());
        values.put(Table.COLUMN_TIME, table.getTime());

        // updating row
        return db.update(Table.TABLE_NAME, values, table.COLUMN_ID + " = ?",
                new String[]{String.valueOf(table.getId())});
    }

    public int deleteNote(Table table) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table.TABLE_NAME, Table.COLUMN_ID + " = ?",
                new String[]{String.valueOf(table.getId())});
        db.close();
        return Integer.parseInt(String.valueOf(table.getId()));
    }


    public long insertFavHoroscopes(){

        return Long.parseLong(null);
    }
}
