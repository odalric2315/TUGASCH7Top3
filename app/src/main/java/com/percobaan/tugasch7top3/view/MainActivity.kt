package com.percobaan.tugasch7top3.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.percobaan.tugasch7top3.adapter.Myadapter
import com.percobaan.tugasch7top3.body.PostBody
import com.percobaan.tugasch7top3.databinding.ActivityMainBinding
import com.percobaan.tugasch7top3.databinding.EditPostBinding
import com.percobaan.tugasch7top3.model.PostResponse
import com.percobaan.tugasch7top3.model.PostResponseItem
import com.percobaan.tugasch7top3.network.ApiClient
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

//        val repository = RemoteRepository(ApiClient.service())

//        val viewModelFactory = MyViewModelFactory(repository)

//        val viewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)

//        viewModel.getPost()

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
            addPosts()
        }

    }

    private fun addPosts() {
        val postBody = PostBody("Hello", "ini isi content", "Test")
        ApiClient.service().addPosts(postBody).enqueue(object : Callback<PostResponseItem> {
            override fun onResponse(
                call: Call<PostResponseItem>,
                response: Response<PostResponseItem>
            ) {
                Toast.makeText(this@MainActivity, response.body()?.toString(), Toast.LENGTH_SHORT)
                    .show()
                binding.progressBar.visibility = View.GONE
                getPost()
            }

            override fun onFailure(call: Call<PostResponseItem>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getPost(){
       ApiClient.service().getPosts().enqueue(object : Callback<List<PostResponseItem>>{
            override fun onResponse(
                call: Call<List<PostResponseItem>>,
                response: Response<List<PostResponseItem>>
            ) {

                binding.progressBar.visibility = View.GONE
                adapter.setContent(response.body()!!)
                Toast.makeText(this@MainActivity, response.body()?.size.toString(),Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<List<PostResponseItem>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun updatePost() {
        val postBody = PostBody("Hello", "Isi content baru", "Update")
        ApiClient.service().updatePost(postBody).enqueue(object : Callback<PostResponseItem> {
            override fun onResponse(
                call: Call<PostResponseItem>,
                response: Response<PostResponseItem>
            ) {
                Toast.makeText(this@MainActivity, response.body()?.toString(), Toast.LENGTH_SHORT)
                    .show()
                binding.progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<PostResponseItem>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun deletePost() {
        val postBody = PostBody("", "", "")
        ApiClient.service().deletePost(postBody).enqueue(object : Callback<PostResponseItem> {
            override fun onResponse(
                call: Call<PostResponseItem>,
                response: Response<PostResponseItem>
            ) {
                Toast.makeText(this@MainActivity, response.body()?.toString(), Toast.LENGTH_SHORT)
                    .show()
                binding.progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<PostResponseItem>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
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
//            val title = view.etTitle.text.toString()
//            val categories = view.etCategories.text.toString()
//            val content = view.etContent.text.toString()
//            val id = PostResponseItem(postResponseItem.id, content, categories, title)
//            PostResponse(id)
            dialog.dismiss()
        }
        dialog.show()
    }

}

