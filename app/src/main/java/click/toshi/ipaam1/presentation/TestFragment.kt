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

    private lateinit var binding: FragmentAllSessionsBinding

    private val sessionsSection = DateSessionsSection()

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var sessionAlarm: SessionAlarm
    @Inject lateinit var sharedRecycledViewPool: RecyclerView.RecycledViewPool
    @Inject lateinit var allSessionsActionCreator: AllSessionsActionCreator

    @Inject lateinit var sessionsStore: SessionsStore
    private val allSessionsStore: AllSessionsStore by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(AllSessionsStore::class.java)
    }

    private val onFavoriteClickListener = { session: Session.SpeechSession ->
        allSessionsActionCreator.favorite(session)
        sessionAlarm.toggleRegister(session)
    }

    private val onFeedbackListener = { session: Session.SpeechSession ->

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentAllSessionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()

        val progressTimeLatch = ProgressTimeLatch {
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }
        sessionsStore.sessions.observeNonNull(this, { sessions ->
            sessionsSection.updateSessions(sessions, onFavoriteClickListener,
                    onFeedbackListener, true)

        })

        allSessionsStore.refreshLoadState.observe(this, { loadState ->
            progressTimeLatch.loading = loadState is LoadState.Loading
            val errorLoadState = loadState as? LoadState.Error ?: return@observe
            val e = errorLoadState.e

            // If user is offline, not error. So we write log to debug
            Timber.d(e)
            val view = view ?: return@observe
            Snackbar.make(view, R.string.session_fetch_failed, Snackbar.LENGTH_LONG).apply {
                setAction(R.string.session_load_retry) {
                    allSessionsActionCreator.refreshSessions()
                }
            }.show()
        })
        allSessionsActionCreator.refreshSessions()
    }

    override fun onTabReselected() {
        binding.sessionsRecycler.smoothScrollToPosition(0)
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            add(sessionsSection)
            setOnItemClickListener({ item, _ ->
                val sessionItem = item as? SpeechSessionItem ?: return@setOnItemClickListener

            })
        }
        binding.sessionsRecycler.apply {
            adapter = groupAdapter
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

            addOnScrollListener(
                    onScrollStateChanged = { _: RecyclerView?, newState: Int ->
                        if (binding.sessionsRecycler.isGone()) return@addOnScrollListener
                        setDayHeaderVisibility(newState != RecyclerView.SCROLL_STATE_IDLE)
                    },
                    onScrolled = { _, _, _ ->
                        val linearLayoutManager = layoutManager as LinearLayoutManager
                        val firstPosition = linearLayoutManager.findFirstVisibleItemPosition()
                        val dayNumber = sessionsSection.getDateNumberOrNull(firstPosition)
                        dayNumber ?: return@addOnScrollListener
                        val dayTitle = getString(R.string.session_day_title, dayNumber)
                        binding.dayHeader.setTextIfChanged(dayTitle)
                    })
            setLinearDivider(R.drawable.shape_divider_vertical_12dp,
                    layoutManager as LinearLayoutManager)
            recycledViewPool = sharedRecycledViewPool
            (layoutManager as LinearLayoutManager).recycleChildrenOnDetach = true
        }
    }

    private fun setDayHeaderVisibility(visibleDayHeader: Boolean) {
        val transition = TransitionInflater
                .from(context)
                .inflateTransition(R.transition.date_header_visibility)
        TransitionManager.beginDelayedTransition(binding.sessionsConstraintLayout, transition)
        binding.dayHeader.setVisible(visibleDayHeader)
    }

    companion object {
        fun newInstance(): TestFragment = TestFragment()
    }
}
