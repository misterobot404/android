package ru.diaran.li;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
    private static String DB_NAME = "dbdemos.db3";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;
    Bitmap bitmap=null;
    ImageView iv001;

    final String LOG_TAG = "myLogs";

    Button btnAdd, btnRead, btnClear;
    EditText etName, txtSize, txtWeiht, txtArea;

    DBHelper dbHelper;

    Cursor c;
    Cursor c_;

    int photoColIndex;
    int nameColIndex;
    int areaColIndex;
    int sizeColIndex;
    int weightColIndex;

    List<String> animals = new ArrayList<String>();
    SQLiteDatabase db;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.etName);
        txtSize = (EditText) findViewById(R.id.etEmail);
        txtWeiht = (EditText) findViewById(R.id.etweight);
        txtArea = (EditText) findViewById(R.id.etarea);

        iv001 = (ImageView) findViewById(R.id.iV001);

        // создаем объект для создания и управления версиями БД
        dbHelper = new DBHelper(this);

        // подключаемся к БД
        db = dbHelper.getWritableDatabase();

        String selectQuery = "SELECT DISTINCT SIZE FROM animals";
        c_ = db.rawQuery(selectQuery, null);
        Log.d(LOG_TAG, selectQuery);
        if (!c_.moveToFirst())
            Log.d(LOG_TAG, "0 rows");
        else {
            sizeColIndex = c_.getColumnIndex("SIZE");
            int i = 0;
            do {
                animals.add(Integer.toString(c_.getInt(sizeColIndex)));
                Log.d(LOG_TAG, animals.get(0));
                i++;
            }while (c_.moveToNext());

        }

        selectQuery = "SELECT * FROM animals where SIZE =" + Integer.parseInt(animals.get(0));
        Log.d(LOG_TAG, selectQuery);
        c = db.rawQuery(selectQuery, null);
        if (!c.moveToFirst())
            Log.d(LOG_TAG, "0 rows");
        else {
            photoColIndex = c.getColumnIndex("BMP");
            nameColIndex = c.getColumnIndex("NAME");
            areaColIndex = c.getColumnIndex("AREA");
            sizeColIndex = c.getColumnIndex("SIZE");
            weightColIndex = c.getColumnIndex("WEIGHT");

            byte[] blob = c.getBlob(photoColIndex);
            // Convert the byte array to Bitmap
            int bLen = blob.length;
            byte[] newblob = Arrays.copyOfRange(blob,8, bLen-8);
            bitmap = BitmapFactory.decodeByteArray(newblob, 0, newblob.length);
            iv001.setImageBitmap(bitmap);

            etName.setText(c.getString(nameColIndex));
            txtArea.setText(c.getString(areaColIndex));
            txtSize.setText(c.getString(sizeColIndex));
            txtWeiht.setText(c.getString(weightColIndex));

        }

    }

    @Override
    protected void onDestroy() {
        c.close();

        // закрываем подключение к БД
        dbHelper.close();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        byte[] blob;
        // Convert the byte array to Bitmap
        int bLen;
        byte[] newblob;

        switch (v.getId()) {
            case R.id.btnAdd:
                if(c.moveToPrevious()){

                    blob = c.getBlob(photoColIndex);
                    // Convert the byte array to Bitmap
                    bLen = blob.length;
                    newblob = Arrays.copyOfRange(blob, 8, bLen - 8);
                    bitmap = BitmapFactory.decodeByteArray(newblob, 0, newblob.length);
                    iv001.setImageBitmap(bitmap);


                    etName.setText(c.getString(nameColIndex));
                    txtSize.setText(c.getString(sizeColIndex));
                    txtWeiht.setText(c.getString(weightColIndex));
                    txtArea.setText(c.getString(areaColIndex));
                }
                break;

            case R.id.btnRead:
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Choose an size");
                builder.setCancelable(true);
                String[] array = animals.toArray(new String[animals.size()]);

                builder.setSingleChoiceItems(array, -1,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                String selected = animals.get(item);
                                Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT).show();
                                    String selectQuery = "SELECT * FROM animals where SIZE =" + animals.get(item);
                                    Log.d(LOG_TAG, selectQuery);
                                    c = db.rawQuery(selectQuery, null);
                                    if (!c.moveToFirst())
                                        Log.d(LOG_TAG, "0 rows");
                                    else {
                                        photoColIndex = c.getColumnIndex("BMP");
                                        nameColIndex = c.getColumnIndex("NAME");
                                        areaColIndex = c.getColumnIndex("AREA");
                                        sizeColIndex = c.getColumnIndex("SIZE");
                                        weightColIndex = c.getColumnIndex("WEIGHT");

                                        byte[] blob = c.getBlob(photoColIndex);
                                        // Convert the byte array to Bitmap
                                        int bLen = blob.length;
                                        byte[] newblob = Arrays.copyOfRange(blob,8, bLen-8);
                                        bitmap = BitmapFactory.decodeByteArray(newblob, 0, newblob.length);
                                        iv001.setImageBitmap(bitmap);

                                        etName.setText(c.getString(nameColIndex));
                                        txtArea.setText(c.getString(areaColIndex));
                                        txtSize.setText(c.getString(sizeColIndex));
                                        txtWeiht.setText(c.getString(weightColIndex));
                                    }
                                dialog.cancel();

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();

                break;

            case R.id.btnClear:

                if(c.moveToNext()){

                    blob = c.getBlob(photoColIndex);
                    // Convert the byte array to Bitmap
                    bLen = blob.length;
                    newblob = Arrays.copyOfRange(blob,8, bLen-8);
                    bitmap = BitmapFactory.decodeByteArray(newblob, 0, newblob.length);
                    iv001.setImageBitmap(bitmap);


                    etName.setText(c.getString(nameColIndex));
                    txtSize.setText(c.getString(sizeColIndex));
                    txtWeiht.setText(c.getString(weightColIndex));
                    txtArea.setText(c.getString(areaColIndex));
                    break;
                }
        }
    }

    //Класс для управления БД SQLite
    public class DBHelper extends SQLiteOpenHelper {

        private SQLiteDatabase mDataBase;
        private final Context mContext;
        private boolean mNeedUpdate = false;

        //Конструктор класса DBHelper
        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            if (android.os.Build.VERSION.SDK_INT >= 17)
                DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
            else
                DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
            this.mContext = context;

            copyDataBase();

            this.getReadableDatabase();
        }

        //Процедура обновления БД (срабатывает при ошибке ввада-вывода)
        public void updateDataBase() throws IOException {
            if (mNeedUpdate) {
                File dbFile = new File(DB_PATH + DB_NAME);
                if (dbFile.exists())
                    dbFile.delete();

                copyDataBase();

                mNeedUpdate = false;
            }
        }

        //Процедура проверки наличия файла БД в каталоге приложения
        private boolean checkDataBase() {
            File dbFile = new File(DB_PATH + DB_NAME);
            return dbFile.exists();
        }

        //Процедура создания файла БД в каталоге приложения
        private void copyDataBase() {
            //Если файла БД НЕ существует, то скопировать его из ресурсов
            if (!checkDataBase()) {
                this.getReadableDatabase();
                this.close();
                try {
                    copyDBFile();
                } catch (IOException mIOException) {
                    throw new Error("ErrorCopyingDataBase");
                }
            }
        }

        //Процедура копирования файла из папки Assets в папку приложения
        private void copyDBFile() throws IOException {
            InputStream mInput = mContext.getAssets().open(DB_NAME);
//InputStream mInput = mContext.getResources().openRawResource(R.raw.info);
            OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
            byte[] mBuffer = new byte[1024];
            int mLength;
            while ((mLength = mInput.read(mBuffer)) > 0)
                mOutput.write(mBuffer, 0, mLength);
            mOutput.flush();
            mOutput.close();
            mInput.close();
        }

        //Процедура открытия БД
        public boolean openDataBase() throws SQLException {
            mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            return mDataBase != null;
        }

        @Override
        public synchronized void close() {
            if (mDataBase != null)
                mDataBase.close();
            super.close();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        //Процедура обновления БД при несовпадении версий
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (newVersion > oldVersion)
                mNeedUpdate = true;
        }
    }
}