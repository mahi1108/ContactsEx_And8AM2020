package cubex.mahesh.contactsex_and8am2020

import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CallLog
import android.provider.ContactsContract
import android.widget.CursorAdapter
import android.widget.SimpleCursorAdapter
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var status = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CALL_LOG)
        if(status == PackageManager.PERMISSION_GRANTED)
        {
            readContacts()
        }else{
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_CALL_LOG),
                    123)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            readContacts()
        }else{
            finish()
        }
    }

    fun  readContacts()
    {/*
        var resolver:ContentResolver = contentResolver
        var cp_uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        var c:Cursor = resolver.query(cp_uri,
                null,
                null,null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        var from = arrayOf(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.CONTACT_LAST_UPDATED_TIMESTAMP
        )
        var to = intArrayOf(R.id.name,R.id.number,R.id._date)
        var cAdapter = SimpleCursorAdapter(this,
                R.layout.indi_row,c,from,to,0)
        lview.adapter = cAdapter */
        //Call Log

        var resolver:ContentResolver = contentResolver
        var cp_uri = CallLog.Calls.CONTENT_URI
        var c:Cursor = resolver.query(cp_uri,
                null,
                null,null,
                null)
        var from = arrayOf(
                CallLog.Calls.CACHED_NAME,
                CallLog.Calls.NUMBER,
                CallLog.Calls.TYPE
        )
        var to = intArrayOf(R.id.name,R.id.number,R.id._date)
        var cAdapter = SimpleCursorAdapter(this,
                R.layout.indi_row,c,from,to,0)
        lview.adapter = cAdapter
    }
}
