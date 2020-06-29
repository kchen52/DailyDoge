package dev.ktown.dailydoge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import dev.ktown.dailydoge.adapters.DogeRecyclerAdapter
import dev.ktown.dailydoge.models.Doge
import dev.ktown.dailydoge.view.TopSpacingItemDecoration
import dev.ktown.dailydoge.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LifecycleObserver {
    private lateinit var dogeRecyclerAdapter: DogeRecyclerAdapter
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        initRecyclerView()

        val smoothScroller = object: LinearSmoothScroller(applicationContext) {
            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }
        viewModel.getDogeObservable().observe(this, Observer<List<Doge>> { dogeChangeList ->
            dogeRecyclerAdapter.addDoges(dogeChangeList)
            dogeRecyclerAdapter.notifyDataSetChanged()
            smoothScroller.targetPosition = dogeRecyclerAdapter.itemCount - 1
            recyclerView.layoutManager?.startSmoothScroll(smoothScroller)
        })

        fab.setOnClickListener {
            viewModel.fetchDoges()
        }
    }

    private fun initRecyclerView() {
        dogeRecyclerAdapter = DogeRecyclerAdapter(mutableListOf())
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(TopSpacingItemDecoration(30))
            adapter = dogeRecyclerAdapter
        }
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}