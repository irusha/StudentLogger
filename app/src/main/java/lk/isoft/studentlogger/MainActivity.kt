package lk.isoft.studentlogger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.io.BufferedReader
import java.io.File
import java.io.InputStream

var pwstate = true

class MainActivity : AppCompatActivity(), RecyclerAdapter.OnItemClickListener {

    companion object {
        var i = ""
        var array = mutableListOf<String>("")
        var arrayscl = mutableListOf<String>("")
    }

    val birthday = 0
    val school = 1
    val grade = 2
    val phno = 3

    //var list = mutableListOf<String>("Mahinda","mahattaya", "nattan", "ubalata", "seemore...")
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()
        var f = File(this.getFilesDir().toString() + "/password.att")

        if (id == R.id.action_1) {

            if (f.exists()) {
                val mdview2 = LayoutInflater.from(this).inflate(R.layout.loginscreen, null)
                val mbuilder2 = AlertDialog.Builder(this).setView(mdview2)
                    .setTitle("Please enter the current password")
                val dshow = mbuilder2.show()
                val bn = mdview2.findViewById<EditText>(R.id.editTextTextPassword)
                val lka = mdview2.findViewById<Button>(R.id.button4)
                val br: BufferedReader = File(f.toString()).bufferedReader()
                val istr = br.use { it.readText() }
                lka.setOnClickListener {
                    val pw = bn.getText().toString()

                    if (pw == istr) {
                        val mdview3 =
                            LayoutInflater.from(this).inflate(R.layout.pwchangewindow, null)
                        dshow.dismiss()
                        val mbuilder3 = AlertDialog.Builder(this).setView(mdview3)
                            .setTitle("Set a new password")
                        val dshow2 = mbuilder3.show()
                        val bnn = mdview3.findViewById<EditText>(R.id.npw)
                        val bnn2 = mdview3.findViewById<EditText>(R.id.pwc)
                        val lkna = mdview3.findViewById<Button>(R.id.nps)

                        lkna.setOnClickListener {
                            if (bnn.getText().toString() == bnn2.getText()
                                    .toString() && bnn.getText().toString() != ""
                            ) {
                                f.delete()
                                dshow2.dismiss()
                                f.createNewFile()
                                f.writeText(bnn.getText().toString())

                                Toast.makeText(
                                    this,
                                    "Password changed successfully",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                Toast.makeText(
                                    this,
                                    "Passwords doesn't match or passwords are invalid",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else {
                        Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                val mdview3 = LayoutInflater.from(this).inflate(R.layout.pwchangewindow, null)
                val mbuilder3 =
                    AlertDialog.Builder(this).setView(mdview3).setTitle("Set a new password")
                val dshow2 = mbuilder3.show()
                val bnn = mdview3.findViewById<EditText>(R.id.npw)
                val bnn2 = mdview3.findViewById<EditText>(R.id.pwc)
                val lkna = mdview3.findViewById<Button>(R.id.nps)

                lkna.setOnClickListener {
                    if (bnn.getText().toString() == bnn2.getText().toString() && bnn.getText()
                            .toString() != ""
                    ) {
                        f.delete()
                        dshow2.dismiss()
                        f.createNewFile()
                        f.writeText(bnn.getText().toString())
                        Toast.makeText(this, "Password changed successfully", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(
                            this,
                            "Passwords doesn't match or passwords are invalid",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            return true
        }
        if (id == R.id.action_2) {
            if (f.exists()) {
                val mdview2 = LayoutInflater.from(this).inflate(R.layout.loginscreen, null)
                val mbuilder2 = AlertDialog.Builder(this).setView(mdview2)
                    .setTitle("Please enter the current password")
                val dshow = mbuilder2.show()
                val bn = mdview2.findViewById<EditText>(R.id.editTextTextPassword)
                val lka = mdview2.findViewById<Button>(R.id.button4)
                val br: BufferedReader = File(f.toString()).bufferedReader()
                val istr = br.use { it.readText() }
                lka.setOnClickListener {
                    val pw = bn.getText().toString()

                    if (pw == istr) {
                        Toast.makeText(this, "Successfully deleted", Toast.LENGTH_LONG).show()
                        f.delete().toString()
                        dshow.dismiss()
                    } else {
                        Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "No password has been set", Toast.LENGTH_SHORT).show()
            }
            return true
        }

        return super.onOptionsItemSelected(item)

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)


        val faab = findViewById<FloatingActionButton>(R.id.fab)
        //val dele = findViewById<ImageButton>(R.id.imageButton2)

        faab.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)

        }

        val path: String = this.getFilesDir().toString() + "/User files"

        if (!File(path).exists()) {
            File(this.getFilesDir().toString(), "User files").mkdir()
        }
        var f = File(this.getFilesDir().toString() + "/password.att")
        array.clear()
        arrayscl.clear()
        if (pwstate) {

            if (f.exists()) {
                val mdview2 = LayoutInflater.from(this).inflate(R.layout.loginscreen, null)
                val mbuilder2 =
                    AlertDialog.Builder(this).setView(mdview2).setTitle("Please enter the password")
                val dshow = mbuilder2.show()
                dshow.setCancelable(false)
                dshow.setCanceledOnTouchOutside(false)
                val bn = mdview2.findViewById<EditText>(R.id.editTextTextPassword)
                val lka = mdview2.findViewById<Button>(R.id.button4)
                val br: BufferedReader = File(f.toString()).bufferedReader()

                val istr = br.use { it.readText() }
                //Log.d(pw,istr)

                lka.setOnClickListener {
                    val pw = bn.getText().toString()
                    //savestate().statedata = true
                    if (pw == istr) {
                        pwstate = false
                        File(path).list().forEach {

                            i = it.toString()
                            layoutManager = LinearLayoutManager(this)
                            recyclerView.layoutManager = layoutManager
                            adapter = RecyclerAdapter(this)
                            recyclerView.adapter = adapter
                            array.add(i)

                        }


                        var arrs = array.size
                        for (number in 0..arrs - 1) {
                            var scln = getElement(array[number], school)
                            arrayscl.add(scln)
                        }
                        dshow.dismiss()
                    } else {
                        Toast.makeText(this, "Password invalid", Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                File(path).list().forEach {
                    i = it.toString()
                    layoutManager = LinearLayoutManager(this)
                    recyclerView.layoutManager = layoutManager
                    adapter = RecyclerAdapter(this)
                    recyclerView.adapter = adapter
                    array.add(i)

                }


                var arrs = array.size
                for (number in 0..arrs - 1) {
                    var scln = getElement(array[number], school)
                    arrayscl.add(scln)
                }
            }
        } else {
            File(path).list().forEach {

                i = it.toString()
                layoutManager = LinearLayoutManager(this)
                recyclerView.layoutManager = layoutManager
                adapter = RecyclerAdapter(this)
                recyclerView.adapter = adapter
                array.add(i)
            }
            var arrs = array.size
            for (number in 0..arrs - 1) {
                var scln = getElement(array[number], school)
                arrayscl.add(scln)
            }
        }
    }


    fun getElement(stuNam: String, elementid: Int): String {
        val br: BufferedReader =
            File(this.getFilesDir().toString() + "/User files/" + stuNam).bufferedReader()
        val istr = br.use { it.readText() }
        val ele = istr.split(",").toTypedArray()
        //Log.d("Aaa",ele.contentToString())
        return ele[elementid]
    }

    override fun OnItemClick(position: Int) {
        val mdview = LayoutInflater.from(this).inflate(R.layout.infodialogbox, null)
        val mbuilder = AlertDialog.Builder(this).setView(mdview).setTitle(array[position])
        val dshow = mbuilder.show()
        val b = mdview.findViewById<Button>(R.id.okb)
        val lk = mdview.findViewById<Button>(R.id.delb)
        val age = mdview.findViewById<TextView>(R.id.age)
        val scl = mdview.findViewById<TextView>(R.id.Schl)
        val grd = mdview.findViewById<TextView>(R.id.grd)
        val phn = mdview.findViewById<TextView>(R.id.pnum)
        val age1 = getElement(array[position], birthday)
        val scl1 = getElement(array[position], school)
        val grd1 = getElement(array[position], grade)
        val phn1 = getElement(array[position], phno)
        age.text = "Birthday: " + age1
        scl.text = "School: " + scl1
        grd.text = "Grade: " + grd1
        phn.text = "Phone number: " + phn1
        var sn = array[position]
        b.setOnClickListener {

            dshow.dismiss()

        }
        lk.setOnClickListener {
            dshow.dismiss()

            val file = File(this.getFilesDir().toString() + "/User files/" + array[position])

            if (file.delete()) {

                array.removeAt(position)
                arrayscl.removeAt(position)
                adapter?.notifyItemRemoved(position)
                adapter?.notifyItemRangeChanged(position, array.size)

                val parentLayout: View = findViewById(R.id.rootlayout)
                val sb = Snackbar.make(parentLayout, "$sn removed successfully",Snackbar.LENGTH_LONG)
                sb.setAction("Undo", View.OnClickListener {
                    savedata(
                        sn,
                        age1,
                        scl1,
                        grd1,
                        phn1
                    )
                    array.add(sn)
                    arrayscl.add(getElement(sn, school))
                    adapter?.notifyItemChanged(array.size-1)
                    adapter?.notifyItemRangeChanged(position, array.size)
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()

                })
                sb.show()

            }

        }

        //adapter.notifyItemChanged(position)
    }

    fun savedata(fname: String, dob: String, scl: String, grd: String, phn: String): Boolean {
        var file = File(this.getFilesDir().toString() + "/User files/" + fname)
        if (!file.createNewFile()) {
            return false
        } else {
            file.writeText(dob + "," + scl + "," + grd + "," + phn)
            return true
        }


    }

}
