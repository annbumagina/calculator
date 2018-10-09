package com.example.ann.calculator


import android.annotation.SuppressLint
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import expression.Expression
import expression.parser.ExpressionParser
import expression.parser.Parser

@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity() {

    private lateinit var eq: TextView
    private lateinit var add: TextView
    private lateinit var sub: TextView
    private lateinit var mul: TextView
    private lateinit var div: TextView
    private lateinit var pt: TextView
    private lateinit var clear: TextView
    private lateinit var del: TextView
    private lateinit var obr: TextView
    private lateinit var cbr: TextView
    private lateinit var sin: TextView
    private lateinit var cos: TextView
    private lateinit var log: TextView
    private lateinit var pow: TextView
    private var n = ArrayList<TextView>()
    private var p: Parser = ExpressionParser()
    private lateinit var e: Expression

    private var expr: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val result = findViewById<TextView>(R.id.expr)

        n.add(findViewById(R.id.n0))
        n.add(findViewById(R.id.n1))
        n.add(findViewById(R.id.n2))
        n.add(findViewById(R.id.n3))
        n.add(findViewById(R.id.n4))
        n.add(findViewById(R.id.n5))
        n.add(findViewById(R.id.n6))
        n.add(findViewById(R.id.n7))
        n.add(findViewById(R.id.n8))
        n.add(findViewById(R.id.n9))
        eq = findViewById(R.id.eq)
        add = findViewById(R.id.add)
        sub = findViewById(R.id.sub)
        mul = findViewById(R.id.mul)
        div = findViewById(R.id.div)
        pt = findViewById(R.id.pt)
        del = findViewById(R.id.del)
        clear = findViewById(R.id.clear)
        obr = findViewById(R.id.obr)
        cbr = findViewById(R.id.cbr)
        var i = 0;
        for (ni in n) {
            { i: Int ->
                ni.setOnClickListener { result.text = result.text.toString() + i.toString()}
            }(i)
            ni.setBackgroundColor(Color.parseColor("#2c3e50"))
            ni.setTextColor(Color.parseColor("#ecf0f1"))
            i++;
        }
        add.setOnClickListener { result.text = result.text.toString() + '+'}
        sub.setOnClickListener { result.text = result.text.toString() + '-'}
        mul.setOnClickListener { result.text = result.text.toString() + '*'}
        div.setOnClickListener { result.text = result.text.toString() + '/'}
        pt.setOnClickListener { result.text = result.text.toString() + '.'}
        del.setOnClickListener { expr = result.text.toString(); result.text = expr.substring(0, expr.length - 1)}
        clear.setOnClickListener { result.text = ""}
        obr.setOnClickListener { result.text = result.text.toString() + '('}
        cbr.setOnClickListener { result.text = result.text.toString() + ')'}
        eq.setOnClickListener {
            expr = result.text.toString()
            try {
                e = p.parse(expr)
                expr = e.evaluate().toString()
            } catch (e : Exception) {
                expr = "error"
            }
            result.text = expr
        }

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            sin = findViewById(R.id.sin)
            cos = findViewById(R.id.cos)
            log = findViewById(R.id.log)
            pow = findViewById(R.id.pow)
            sin.setOnClickListener { result.text = result.text.toString() + "sin"}
            cos.setOnClickListener { result.text = result.text.toString() + "cos"}
            log.setOnClickListener { result.text = result.text.toString() + "//"}
            pow.setOnClickListener { result.text = result.text.toString() + "**"}
        }


        if (savedInstanceState != null) {
            expr = savedInstanceState.getString(CNT)
            result.text = expr
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(CNT, expr)

        super.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "onPause: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(LOG_TAG, "onRestart: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy: ")
    }

    companion object {
        private const val LOG_TAG = "MainActivity"
        private val CNT = MainActivity::class.java.name + ".cnt"
        private val USER = MainActivity::class.java.name + ".usr"
    }
}