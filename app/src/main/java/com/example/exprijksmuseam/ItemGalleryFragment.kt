package com.example.exprijksmuseam

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exprijksmuseam.databinding.FragmentItemGalleryBinding
import com.example.exprijksmuseam.model.ItemInterface
import com.example.exprijksmuseam.network.RijksmuseamApi
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

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

        // Get data from api and store in array items
        RijksmuseamApi.retrofitService.getAll(getString(R.string.rijksmuseam_api_key), true)
            .enqueue(
                object : Callback, retrofit2.Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.e(TAG, "onFailure: Something went wrong with the call")
                        binding.temp.text = "Failure: " + t.message
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        binding.temp.text = response.body()
                    }
                }
            )

        itemAdapter.notifyDataSetChanged()
    }

    private fun redirectToFragment() {

    }
}