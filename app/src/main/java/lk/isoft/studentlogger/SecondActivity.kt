package lk.isoft.studentlogger

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.nio.file.Files
import java.util.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){

        super.onCreate(savedInstanceState)
        setContentView(R.layout.infocollect)
        val cancb = findViewById<Button>(R.id.button2)
        title = "Enter student information"
        val intent1 = Intent(this, MainActivity::class.java)
        cancb.setOnClickListener{
            startActivity(intent1)
        }
        val doneb = findViewById<Button>(R.id.button)
        val path:String = this.getFilesDir().toString()
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val calsel = findViewById<Button>(R.id.button3)
        var datefull = ""
        calsel.setOnClickListener{
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, mYear, mMonth, mDay ->
                val intmonth = mMonth.toInt()
                val intday = mDay.toInt()
                var remonth = ""
                var reday = ""

                if (intmonth < 10){
                    remonth = "0" + mMonth.toString()
                }
                else {
                    remonth = mMonth.toString()
                }
                if (intday < 10){
                    reday = "0" + mDay.toString()
                }
                else {
                    reday = mDay.toString()
                }
                datefull = reday + "." + remonth + "." + mYear
                calsel.setText(""+ datefull)

            }, year, month, day)
            dpd.show()
        }
        val firstname = findViewById<EditText>(R.id.editTextTextPersonName)
        val secondname = findViewById<EditText>(R.id.editTextTextPersonName2)
        val school = findViewById<EditText>(R.id.editTextTextPersonName3)
        doneb.setOnClickListener{
            val nexist = firstname.getText().toString() == "" || secondname.getText().toString() == ""
            if(nexist){
                Toast.makeText(this, "Please fill the required details", Toast.LENGTH_LONG).show()
            }
            else{
                val namei = firstname.getText().toString() +" " + secondname.getText().toString()
                val scli = school.getText().toString()
                val grdi = findViewById<EditText>(R.id.editTextNumber).getText().toString()
                val phi = findViewById<EditText>(R.id.editTextPhone).getText().toString()

                if (savedata(namei, datefull, scli, grdi, phi)){
                    Toast.makeText(this, "Student info saved successfully", Toast.LENGTH_SHORT).show()
                    startActivity(intent1)
                }
                else{
                    Toast.makeText(this, "$namei already exists", Toast.LENGTH_LONG).show()

                }

            }
        }
    }
    fun savedata(fname: String, dob: String, scl: String, grd: String, phn: String): Boolean{
        var file = File(this.getFilesDir().toString() + "/User files/" + fname)
        if(!file.createNewFile()){
            return false
        }
        else{
            file.writeText(dob + "," + scl + "," + grd + "," + phn)
            return true
        }

    }
}