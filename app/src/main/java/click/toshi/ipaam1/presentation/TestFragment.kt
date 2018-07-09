package click.toshi.ipaam1.presentation

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.transition.TransitionInflater
import android.support.transition.TransitionManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SimpleItemAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import dagger.android.support.DaggerFragment
import click.toshi.ipaam1.R
import click.toshi.ipaam1.databinding.FragmentAllSessionsBinding
import click.toshi.ipaam1.databinding.FragmentTestBinding
import click.toshi.ipaam1.model.LoadState
import click.toshi.ipaam1.model.Session
import click.toshi.ipaam1.presentation.common.view.OnTabReselectedListener
import click.toshi.ipaam1.presentation.sessions.item.DateSessionsSection
import click.toshi.ipaam1.presentation.sessions.item.SpeechSessionItem
import click.toshi.ipaam1.util.ProgressTimeLatch
import click.toshi.ipaam1.util.SessionAlarm
import click.toshi.ipaam1.util.ext.addOnScrollListener
import click.toshi.ipaam1.util.ext.isGone
import click.toshi.ipaam1.util.ext.observe
import click.toshi.ipaam1.util.ext.observeNonNull
import click.toshi.ipaam1.util.ext.setLinearDivider
import click.toshi.ipaam1.util.ext.setTextIfChanged
import click.toshi.ipaam1.util.ext.setVisible
import timber.log.Timber
import javax.inject.Inject

class TestFragment
    : DaggerFragment(), OnTabReselectedListener {

    private lateinit var binding: FragmentTestBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

//    private val allSessionsStore: AllSessionsStore by lazy {
//        ViewModelProviders.of(this, viewModelFactory).get(AllSessionsStore::class.java)
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        setupRecyclerView()

//        val progressTimeLatch = ProgressTimeLatch {
//            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
//        }

//        allSessionsActionCreator.refreshSessions()
    }

    override fun onTabReselected() {
//        binding.sessionsRecycler.smoothScrollToPosition(0)
    }


//    private fun setDayHeaderVisibility(visibleDayHeader: Boolean) {
//        val transition = TransitionInflater
//                .from(context)
//                .inflateTransition(R.transition.date_header_visibility)
//        TransitionManager.beginDelayedTransition(binding.sessionsConstraintLayout, transition)
//        binding.dayHeader.setVisible(visibleDayHeader)
//    }

    companion object {
        fun newInstance(): TestFragment = TestFragment()
    }
}
