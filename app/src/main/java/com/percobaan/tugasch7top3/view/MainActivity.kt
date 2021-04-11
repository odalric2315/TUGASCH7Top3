package com.percobaan.tugasch7top3.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.percobaan.mvvmapp.view.MyViewModelFactory
import com.percobaan.tugasch7top3.adapter.Myadapter
import com.percobaan.tugasch7top3.body.PostBody
import com.percobaan.tugasch7top3.databinding.ActivityMainBinding
import com.percobaan.tugasch7top3.databinding.EditPostBinding
import com.percobaan.tugasch7top3.model.PostResponseItem
import com.percobaan.tugasch7top3.network.ApiClient
import com.percobaan.tugasch7top3.repository.RemoteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Myadapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        adapter = Myadapter()
        binding.rvPost.layoutManager = LinearLayoutManager(this)
        binding.rvPost.adapter = adapter

        val repository = RemoteRepository(ApiClient.service())

        val viewModelFactory = MyViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)

        viewModel.getPost()

        adapter.setOnClickListerner {
            showEditDialog(it)
            updatePost()
        }
        adapter.setOnDeleteListerner {
            deletePost()
        }
        getPost()
        binding.floatingActionButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            addPost()
        }

    }

    private fun addPost() {
        val postBody = PostBody("Hello", "ini isi content", "Test")
        ApiClient.service().addPost(postBody).enqueue()
    }

    private fun getPost() {
        ApiClient.service().getPost().enqueue()
    }

    private fun updatePost() {
        val postBody = PostBody("Hello", "Isi content baru", "Update")
        ApiClient.service().updatePost(postBody).enqueue()
    }

    private fun deletePost() {
        val postBody = PostBody("", "", "")
        ApiClient.service().deletePost(postBody).enqueue()
    }

    private fun showEditDialog(postResponseItem: PostResponseItem) {
        val builder = AlertDialog.Builder(this)
        val view = EditPostBinding.inflate(layoutInflater)
        builder.setView(view.root)
        val dialog = builder.create()
        view.etTitle.setText(postResponseItem.title)
        view.etCategories.setText(postResponseItem.categories)
        view.etContent.setText(postResponseItem.content)
        view.btnSave.setOnClickListener {
            val title = view.etTitle.text.toString()
            val categories = view.etCategories.text.toString()
            val content = view.etContent.text.toString()
            val id = PostResponseItem(postResponseItem.id, content, categories, title)
            dialog.dismiss()
        }
        dialog.show()
    }


    private fun <T> Response<T>.enqueue() {
        object : Callback<List<PostResponseItem>> {
            override fun onResponse(
                call: Call<List<PostResponseItem>>,
                response: Response<List<PostResponseItem>>
            ) {

                Toast.makeText(this@MainActivity, response.body()?.size.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<List<PostResponseItem>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

