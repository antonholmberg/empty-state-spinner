package org.grunkspin.testapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import org.grunkspin.emptystatespinner.EmptyStateSpinner

class MainActivity : AppCompatActivity() {

    private lateinit var makeItSpin: EmptyStateSpinner

    private val options = arrayListOf("Option one", "Option two", "Option three")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeItSpin = findViewById(R.id.makeItSpin)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)
        makeItSpin.setAdapter(adapter, "Empty")
    }
}
