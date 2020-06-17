package eliezer.antonio.feedreader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pkmmte.pkrss.Article
import com.pkmmte.pkrss.Callback
import com.pkmmte.pkrss.PkRSS
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Callback {

    lateinit var listView:RecyclerView
    lateinit var adapter:RecyclerView.Adapter<ItemAdapter.ItemViewHolder>

    val listaItens = arrayListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layout=LinearLayoutManager(this)
        listView=rv
        listView.layoutManager=layout

        adapter=ItemAdapter(listaItens,this)
        listView.adapter=adapter

    ///carregando o metodo de leitura
        PkRSS.with(this).load("https://rss.tecmundo.com.br/feed").callback(this).async()

    }

    override fun onLoaded(newArticles: MutableList<Article>?) {
        //limpar lista
        listaItens.clear()

        newArticles?.mapTo(listaItens) {
            Item(it.title, it.author, it.date, it.source, it.enclosure.url)
        }
        //notificar ao adapter
        adapter.notifyDataSetChanged()
    }

    override fun onLoadFailed() {

    }

    override fun onPreload() {

    }


}