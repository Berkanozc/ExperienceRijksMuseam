package com.example.exprijksmuseam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exprijksmuseam.databinding.FragmentItemGalleryBinding
import com.example.exprijksmuseam.model.Artist
import com.example.exprijksmuseam.model.ItemInterface
import com.example.exprijksmuseam.model.WorkOfArt

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ItemGalleryFragment : Fragment() {

    private val items: ArrayList<ItemInterface> = arrayListOf()
    private val itemAdapter: ItemAdapter =
        ItemAdapter(items) { redirectToFragment() }

    private var _binding: FragmentItemGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentItemGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Initialize the recycler view with a linear layout manager, adapter
     */
    private fun initViews() {
        binding.rvItems.layoutManager =
            LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        binding.rvItems.adapter = itemAdapter

        // Add random data
        items.add(
            WorkOfArt(
                "Nachtwacht",
                "https://lh3.googleusercontent.com/J-mxAE7CPu-DXIOx4QKBtb0GC4ud37da1QK7CzbTIDswmvZHXhLm4Tv2-1H3iBXJWAW_bHm7dMl3j5wv_XiWAg55VOM=s0",
                "blablbla",
                "nl"
            )
        )

        items.add(
            Artist(
                "Rembrandt van Rijn",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/Rembrandt_van_Rijn_-_Self-Portrait_-_Google_Art_Project.jpg/1200px-Rembrandt_van_Rijn_-_Self-Portrait_-_Google_Art_Project.jpg",
                "blababalbal",
                "nl"
            )
        )

        itemAdapter.notifyDataSetChanged()
    }

    private fun redirectToFragment() {

    }
}