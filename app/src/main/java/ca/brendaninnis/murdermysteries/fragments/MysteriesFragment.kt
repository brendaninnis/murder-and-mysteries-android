package ca.brendaninnis.murdermysteries.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ca.brendaninnis.murdermysteries.utils.MysteryItemClickListener
import ca.brendaninnis.murdermysteries.R
import ca.brendaninnis.murdermysteries.models.Mystery

class MysteriesFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val mMysteries: Array<Mystery> = arrayOf(
            Mystery("Murder and Dragons", 8, 20, R.drawable.murder_and_dragons),
            Mystery("Avada Kedavra", 6, 16, R.drawable.avadakedavra),
            Mystery("The Games Night Murder", 10, 24, R.drawable.gamesnight)
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_mysteries, container, false)

        viewManager = LinearLayoutManager(context)
        viewAdapter = MysteriesAdapter(mMysteries)

        recyclerView = rootView.findViewById<RecyclerView>(R.id.mysteries_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
            addOnItemTouchListener(MysteryItemClickListener(context, this, object : MysteryItemClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    listener?.showMystery(mMysteries[position], view)
                }

                override fun onLongItemClick(view: View?, position: Int) {
                    if (view != null) {
                        listener?.showMystery(mMysteries[position], view)
                    }
                }
            }))
        }

        return rootView;
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun showMystery(mystery: Mystery, view: View)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MysteriesFragment()
    }

    class MysteriesAdapter(val mysteries: Array<Mystery>) : RecyclerView.Adapter<MysteriesAdapter.ViewHolder>() {
        class ViewHolder(public val cardView: View) : RecyclerView.ViewHolder(cardView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val cardView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.mystery_item, parent, false)
            return ViewHolder(cardView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val mystery = mysteries[position]

            with(holder.cardView) {
                with(findViewById<ImageView>(R.id.mystery_splash_image)) {
                    setImageDrawable(context.getDrawable(mystery.splashImageId))
                    transitionName = mystery.name
                }
                findViewById<TextView>(R.id.mystery_title).text = mystery.name
                findViewById<TextView>(R.id.mystery_players).text = context.getString(
                        R.string.mystery_players,
                        mystery.minimumPlayers,
                        mystery.maximumPlayers
                )
            }
        }

        override fun getItemCount(): Int {
            return mysteries.size
        }
    }
}
